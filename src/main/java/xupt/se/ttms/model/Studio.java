package xupt.se.ttms.model;


import java.security.PublicKey;

public class Studio {
	private int id=0      ; 
	private String name="" ;
	private int rowCount=0;
	private int colCount=0;
	private String introduction=""; //
	
	public Studio(){

	}

	public Studio(int ID)
	{
		this.id=ID;
	}
	public Studio(int ID, String name, int rowCount, int colCount, String intro){
		id = ID;
		this.name=name;
		this.rowCount = rowCount;
		this.colCount = colCount;
		introduction = intro;		
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setID(int ID){
		this.id=ID;
	}
	
	public int getID(){
		return id;
	}
	
	public void setName(String name){
		this.name=name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setRowCount(int count){
		this.rowCount=count;
	}
	
	public int getRowCount(){
		return rowCount;
	}
	public void setColCount(int count){
		this.colCount=count;
	}
	
	public int getColCount(){
		return colCount;
	}
	
	public void setIntroduction(String intro){
		this.introduction=intro;
	}
	
	public String getIntroduction(){
		return introduction;
	}

	@Override
	public String toString() {
		return "Studio{" +
				"id=" + id +
				", name='" + name + '\'' +
				", rowCount=" + rowCount +
				", colCount=" + colCount +
				", introduction='" + introduction + '\'' +
				'}';
	}
}
