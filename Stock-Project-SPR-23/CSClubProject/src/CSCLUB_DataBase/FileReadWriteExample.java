package CSCLUB_DataBase;

import java.io.IOException;
import java.io.File;

public class FileReadWriteExample {
	 public static void main(String[] args) {
	        // Create a File object
		   DataBase db=new DataBase();
		   Util.load(db);
		   Lot lot=db.get("lot 1");
	       db.put("lot0",new Lot("lot0"));
	       db.put("lot 1",new Lot("lot 1"));
	       db.put("lot 2",new Lot("lot 2"));
	       Util.save(db);	       
	 }	 
}