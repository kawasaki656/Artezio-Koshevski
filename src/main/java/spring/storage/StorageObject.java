package spring.storage;

import spring.model.ObjIncas;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Саша on 25.12.2016.
 */
public class StorageObject {
    private static String jdbcUrl = null;
    private static String jdbcUser = null;
    private static String jdbcPassword = null;

    public static void init(String jdbcDriver,
                            String jdbcUrl,
                            String jdbcUser,
                            String jdbcPassword)
            throws ClassNotFoundException {
        Class.forName(jdbcDriver);
        StorageObject.jdbcUrl = jdbcUrl;
        StorageObject.jdbcUser = jdbcUser;
        StorageObject.jdbcPassword = jdbcPassword;
    }

    public static Collection<ObjIncas> readAll()  {
        String sql = "SELECT `id`, `time`, `typeOfPutting`, `periodOfService`, `dayOfWeek`, `countOfMoney`, `codeOfCurrency`, `telephoneHead`, `date` "
                + "FROM `objects`";
        Connection c = null;
        Statement s = null;
        ResultSet r = null;
        Collection<ObjIncas> objects = new ArrayList<ObjIncas>();
        try {
            c = getConnection();
            s = c.createStatement();
            r = s.executeQuery(sql);

            while(r.next()) {
                ObjIncas object = new ObjIncas(r.getInt("id"),r.getString("time"),r.getString("typeOfPutting"),r.getString("periodOfService"),
                        r.getString("dayOfWeek"),r.getString("countOfMoney"),r.getString("codeOfCurrency"),
                        r.getString("telephoneHead"),r.getString("date"));
                objects.add(object);
            }

        } catch (SQLException e){    }
        return objects;
    }

    public static void create(ObjIncas object) throws SQLException {
        String sql = "INSERT INTO `objects` "
                + "(`id`, `time`, `typeOfPutting`, `periodOfService`, `dayOfWeek`, `countOfMoney`, `codeOfCurrency`, `telephoneHead`, `date`) "
                + "VALUES "
                + "(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Connection c = null;
        PreparedStatement s = null;
        try {
            c = getConnection();
            s = c.prepareStatement(sql);
            s.setInt(1, object.getId());
            s.setString(2, object.getTime());
            s.setString(3, object.getTypeOfPutting());
            s.setString(4, object.getPeriodOfService());
            s.setString(5, object.getDayOfWeek());
            s.setString(6, object.getCountOfMoney());
            s.setString(7, object.getCodeOfCurrency());
            s.setString(8, object.getTelephoneHead());
            s.setString(9, object.getDate());
            s.executeUpdate();
        }
        finally {

        }
        }

    public static void update(ObjIncas object) throws SQLException {
        String sql = "UPDATE `objects` SET "
                + "`time`=?, `typeOfPutting`=?, `periodOfService`=?, `dayOfWeek`=?, `countOfMoney`=?, `codeOfCurrency`=?, `telephoneHead`=?, `date`=? "
                + "WHERE `id` = ?";
        Connection c = null;
        PreparedStatement s = null;
        try {
            c = getConnection();
            s = c.prepareStatement(sql);
            s.setString(1, object.getTime());
            s.setString(2, object.getTypeOfPutting());
            s.setString(3, object.getPeriodOfService());
            s.setString(4, object.getDayOfWeek());
            s.setString(5, object.getCountOfMoney());
            s.setString(6, object.getCodeOfCurrency());
            s.setString(7, object.getTelephoneHead());
            s.setString(8, object.getDate());
            s.setInt(9, object.getId());
            s.executeUpdate();
        }
        finally {

        }
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcUrl,
                jdbcUser,
                jdbcPassword);
    }


}
