package CSCLUB_DataBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.TreeMap;


public class Util {
	public final static String LOTFILELOCATION="Lot.dat";
	public static void load(DataBase tree) {//We should not have to return a tree because these are reference.
		File file=new File(LOTFILELOCATION);
		
		try {
			if(!file.exists())return;
			 FileInputStream fileStream =new FileInputStream(file);
	         ObjectInputStream in = new ObjectInputStream(fileStream);
	             
	         tree.setTree((TreeMap<String,Lot>) in.readObject());
	             
	         in.close();
	         fileStream.close();
		 }catch(FileNotFoundException e){
		 	e.printStackTrace();	
		 }catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void save(DataBase tree) {
		File file=new File(LOTFILELOCATION);
		try {
			FileOutputStream fileStream = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileStream);
	             
            out.writeObject(tree.getTree());
	             
	        out.close();
	        fileStream.close();
		 }catch(FileNotFoundException e){
		 	e.printStackTrace();	
		 }catch(Exception e) {
			e.printStackTrace();
		 }
	}	
}