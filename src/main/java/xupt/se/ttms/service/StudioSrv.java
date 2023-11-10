package xupt.se.ttms.service;

import java.sql.SQLException;
import java.util.List;

import xupt.se.ttms.idao.DAOFactory;
import xupt.se.ttms.idao.iStudioDAO;
import xupt.se.ttms.model.Studio;

public class StudioSrv {
	private iStudioDAO stuDAO=DAOFactory.creatStudioDAO();

	public List<Studio> QuerybyId(int id){
		return  stuDAO.selectbyid(id);
	}
	public int add(Studio stu) throws SQLException {
		return stuDAO.insert(stu);
	}
	
	public int modify(Studio stu){
		return stuDAO.update(stu); 		
	}
	
	public int delete(int ID){
		return stuDAO.delete(ID); 		
	}
	
	public List<Studio> Fetch(String condt){
		return stuDAO.select(condt);		
	}
	
	public List<Studio> FetchAll(){
		return stuDAO.select("");		
	}

	public int insert_seat(int studio_id,int row ,int col,int seat_status) throws SQLException {
		return  stuDAO.insert_seat(studio_id,row,col,seat_status);
	}

	public int delect_seat(int studio_id) throws SQLException {
		return  stuDAO.delect_seat(studio_id);
	}

	public List<Studio> Fetchsched(String name){
		return stuDAO.selectsched(name);
	}
}
