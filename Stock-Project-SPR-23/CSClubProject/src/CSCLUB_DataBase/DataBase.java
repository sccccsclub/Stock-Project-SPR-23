package CSCLUB_DataBase;
import java.util.TreeMap;

public class DataBase {
	private static DataBase x=null;
	private static TreeMap<String,Lot> map=new TreeMap<>();
	
	DataBase(){
		
	}
	
	public static DataBase getInstance() {
		if(x==null) {
			x=new DataBase();
		}
		return x;
	}
	
	public void display() {
		System.out.println(map);
	}
	
	public void display2() {
		System.out.println(map);
	}
	
	public void put(String key,Lot value) {
		map.put(key, value);
	}
	
	public TreeMap<String,Lot> getTree() {
		return map;
	}
	
	public void setTree(TreeMap<String,Lot> map){
		if(map!=null)this.map=map;
	}	
	
	public Lot get(String key) {
		return map.get(key);
	}
}