package spring.storage;

import spring.model.Request;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Саша on 26.12.2016.
 */
public class StorageReq {
    private static String jdbcUrl = null;
    private static String jdbcUser = null;
    private static String jdbcPassword = null;

    public static void init(String jdbcDriver,
                            String jdbcUrl,
                            String jdbcUser,
                            String jdbcPassword)
            throws ClassNotFoundException {
        Class.forName(jdbcDriver);
        StorageReq.jdbcUrl = jdbcUrl;
        StorageReq.jdbcUser = jdbcUser;
        StorageReq.jdbcPassword = jdbcPassword;
    }

    public static Collection<Request> readAll()  {
        String sql = "SELECT `id`, `bank`, `inn`, `kpp`, `nameOrganization`, `ogrn`, `nameEmploye`, `telephoneEmploye`, `bankDetails`, `accountNumber`, `bik`, `bankNumber`, `nameBank`, `swift`, `date`, `status`, `type` "
                + "FROM `requests`";
        Connection c = null;
        Statement s = null;
        ResultSet r = null;
        Collection<Request> objects = new ArrayList<Request>();
        try {
            c = getConnection();
            s = c.createStatement();
            r = s.executeQuery(sql);

            while(r.next()) {
                String type = "";
                String status = "";
                if(r.getString("type").equals("1"))
                    type="Инкассация";
                if(r.getString("type").equals("2"))
                    type="Изменение реквизитов";
                if(r.getString("status").equals("1"))
                    status="Создана";
                Request object = new Request(r.getInt("id"),r.getString("bank"),r.getString("inn"),r.getString("kpp"),
                        r.getString("nameOrganization"),r.getString("ogrn"),r.getString("nameEmploye"),
                        r.getString("telephoneEmploye"),r.getString("bankDetails"),r.getString("accountNumber"),r.getString("bik"),r.getString("bankNumber"),
                        r.getString("nameBank"),r.getString("swift"));
                object.addHidden(r.getString("date"),status,type);
                objects.add(object);
            }

        } catch (SQLException e){    }
        return objects;
    }

    public static Collection<Request> readAllOfUser()  {
        String sql = "SELECT `id`, `bank`, `inn`, `kpp`, `nameOrganization`, `ogrn`, `nameEmploye`, `telephoneEmploye`, `bankDetails`, `accountNumber`, `bik`, `bankNumber`, `nameBank`, `swift`, `date`, `status`, `type` "
                + "FROM `requests` "
                +"WHERE `user` = ?";
        Connection c = null;
        PreparedStatement s = null;
        ResultSet r = null;
        Collection<Request> objects = new ArrayList<Request>();
        try {
            c = getConnection();
            s = c.prepareStatement(sql);
            s.setInt(1,StorageUsers.curId);
            r = s.executeQuery();

            while(r.next()) {
                String type = "";
                String status = "";
                if(r.getString("type").equals("1"))
                    type="Инкассация";
                if(r.getString("type").equals("2"))
                    type="Изменение реквизитов";
                if(r.getString("status").equals("1"))
                    status="Создана";
                Request object = new Request(r.getInt("id"),r.getString("bank"),r.getString("inn"),r.getString("kpp"),
                        r.getString("nameOrganization"),r.getString("ogrn"),r.getString("nameEmploye"),
                        r.getString("telephoneEmploye"),r.getString("bankDetails"),r.getString("accountNumber"),r.getString("bik"),r.getString("bankNumber"),
                        r.getString("nameBank"),r.getString("swift"));
                object.addHidden(r.getString("date"),status,type);
                objects.add(object);
            }

        } catch (SQLException e){    }
        return objects;
    }

    public static void create(Request object) throws SQLException {
        String sql = "INSERT INTO `requests` "
                + "(`id`, `bank`, `inn`, `kpp`, `nameOrganization`, `ogrn`, `nameEmploye`, `telephoneEmploye`, `bankDetails`, `accountNumber`, `bik`, `bankNumber`, `nameBank`, `swift`, `date`, `status`, `type`,`user`) "
                + "VALUES "
                + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Connection c = null;
        PreparedStatement s = null;
        try {
            c = getConnection();
            s = c.prepareStatement(sql);
            s.setInt(1, object.getId());
            s.setString(2, object.getBank());
            s.setString(3, object.getInn());
            s.setString(4, object.getKpp());
            s.setString(5, object.getNameOrganization());
            s.setString(6, object.getOgrn());
            s.setString(7, object.getNameEmploye());
            s.setString(8, object.getTelephoneEmploye());
            s.setString(9, object.getBankDetails());
            s.setString(10, object.getAccountNumber());
            s.setString(11, object.getBik());
            s.setString(12, object.getBankNumber());
            s.setString(13, object.getNameBank());
            s.setString(14, object.getSwift());
            s.setString(15, object.getDate());
            s.setString(16, object.getStatus());
            s.setString(17, object.getType());
            s.setInt(18, StorageUsers.curId);
            s.executeUpdate();
        }
        finally {

        }
    }

    public static void update(Request object) throws SQLException {
        String sql = "UPDATE `requests` SET "
                + "`bank`=?, `inn`=?, `kpp`=?, `nameOrganization`=?, `ogrn`=?, `nameEmploye`=?, `telephoneEmploye`=?, `bankDetails`=?, `accountNumber`=?, `bik`=?, `bankNumber`=?, `nameBank`=?, `swift`=?, `date`=?, `status`=?, `type`=?, `user`=? "
                + "WHERE `id` = ?";
        Connection c = null;
        PreparedStatement s = null;
        try {
            c = getConnection();
            s = c.prepareStatement(sql);
            s.setString(1, object.getBank());
            s.setString(2, object.getInn());
            s.setString(3, object.getKpp());
            s.setString(4, object.getNameOrganization());
            s.setString(5, object.getOgrn());
            s.setString(6, object.getNameEmploye());
            s.setString(7, object.getTelephoneEmploye());
            s.setString(8, object.getBankDetails());
            s.setString(9, object.getAccountNumber());
            s.setString(10, object.getBik());
            s.setString(11, object.getBankNumber());
            s.setString(12, object.getNameBank());
            s.setString(13, object.getSwift());
            s.setString(14, object.getDate());
            s.setString(15, object.getStatus());
            s.setString(16, object.getType());
            s.setInt(17, StorageUsers.curId);
            s.setInt(18, object.getId());

            s.executeUpdate();
        }
        finally {

        }
    }
/*
    public static String count() throws SQLException {
        String sql = "SELECT COUNT(`id`) AS `all` FROM `requests`";
        Connection c = null;
        Statement s = null;
        ResultSet r = null;
        try {
            c = getConnection();
            s = c.createStatement();
            r = s.executeQuery(sql);
            return r.getArray(1);
    }
        catch(SQLException e) {
            return null;
        }
    }*/

    public static void delete(int id) throws SQLException {
        String sql = "DELETE FROM `requests` "
                + "WHERE `id` = ?";
        Connection c = null;
        PreparedStatement s = null;
        try {
            c = getConnection();
            s = c.prepareStatement(sql);
            s.setInt(1, id);
            s.executeUpdate();
        } finally {
        }
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcUrl,
                jdbcUser,
                jdbcPassword);
    }
}
