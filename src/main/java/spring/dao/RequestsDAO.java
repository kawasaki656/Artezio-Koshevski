package spring.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import spring.model.*;

import spring.storage.StorageObject;
import spring.storage.StorageReq;
import spring.storage.StorageUsers;
import org.springframework.stereotype.Component;

@Component
public class RequestsDAO {

	public static final String DRIVER = "com.mysql.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost/collection?"
			+ "useUnicode=true&"
			+ "characterEncoding=UTF-8";
	public static final String USER = "name";
	public static final String PASSWORD = "name";

	public static LastId lastId;
	private static List<ObjIncas> objectsIncas;

	private static List<Request> Requests;

	{
		lastId = new LastId(1);
	}

	public RequestsDAO() {
		try {
			StorageObject.init(DRIVER, URL, USER, PASSWORD);
			StorageReq.init(DRIVER, URL, USER, PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Requests = new ArrayList(StorageReq.readAll());

		objectsIncas = new ArrayList(StorageObject.readAll());

	}

	public List getObjects(){return new ArrayList(StorageObject.readAll());}
	public List<Request> getReqs(){
		if(StorageUsers.curId == 0)
			return new ArrayList(StorageReq.readAll());
		else return new ArrayList(StorageReq.readAllOfUser());
	}
	public List getObjectsById(Integer id){

		List<ObjIncas> res = new ArrayList<ObjIncas>();
		for(ObjIncas c : objectsIncas){
			if(c.getId().equals(id))
				res.add(c);
		}
		return res;
	}

	public LastId getLastId(){
		return lastId;
	}

	public ObjIncas getObjIncas(Integer id){
		for(ObjIncas c : objectsIncas){
			if(c.getId().equals(id))
				return c;
		}
		return null;
	}

	public Request getReq(int id){
		for(Request c : getReqs()){
			if(c.getId().equals(id))
				return c;
		}
		return null;
	}

	public Request updateReq(Request Request) {

		for (Request c : getReqs()) {
			if (c.getId().equals(Request.getId())) {
				try {
					StorageReq.update(Request);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return Request;
			}
		}

		return null;
	}

	public Request createReq(Request obj){
		lastId.setlastId(getLastId().getlastId()+1);
		try {
			StorageReq.create(obj);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}

	public void deleteReq(int id){
		try {
			StorageReq.delete(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public ObjIncas createObjIncas(ObjIncas obj){

		objectsIncas.add(obj.getId(),obj);
		try {
			StorageObject.create(obj);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}

}