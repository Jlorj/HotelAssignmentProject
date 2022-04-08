package Assignment;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class serializingClass implements Serializable {


	serializingClass(){

	    
	}

//	   public static void main(String [] args) {
//		  //Invoke this part to read an existing CSV file
		   Rooms rooms = new Rooms();
//		    String file = "RoomsInformation.csv";
//		    BufferedReader reader = null;
//		    String line = "";
//		    try{
//		        reader = new BufferedReader(new FileReader(file));
//		        // room number is from 1-48 but array is zero indexed
//		        line = reader.readLine();
//		        while((line = reader.readLine()) != null){
//		            String[] row = line.split(",");
//		            rooms.addRoom(Integer.parseInt(row[0]), row[1], row[2], Boolean.parseBoolean(row[3]), Boolean.parseBoolean(row[4]), Boolean.parseBoolean(row[5]));
//		        }
//		    }
//		    catch(Exception e){
//		        e.printStackTrace();
//		    }
//		    finally{
//		        try{
//		            reader.close();
//		        }catch(IOException e){
//		            e.printStackTrace();
//		        }
//		    }
//	      
	
		    
		    //Invoke this part to create new ser file
		    
//	      try {
//	         FileOutputStream fileOut = new FileOutputStream("employee.ser");
//	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
//	         out.writeObject(rooms);
//	         out.close();
//	         fileOut.close();
//	         System.out.printf("Serialized data is saved in employee.ser");
//	      } catch (IOException i) {
//	         i.printStackTrace();
//	      }
//	   }
//	
//
//		   Rooms rooms = null;
//		      try {
//		         FileInputStream fileIn = new FileInputStream("/tmp/employee.ser");
//		         ObjectInputStream in = new ObjectInputStream(fileIn);
//		         rooms = (Rooms) in.readObject();
//		         in.close();
//		         fileIn.close();
//		      } catch (IOException i) {
//		         i.printStackTrace();
//		         return;
//		      } catch (ClassNotFoundException c) {
//		         System.out.println("Employee class not found");
//		         c.printStackTrace();
//		         return;
//		      }
//		      System.out.println(rooms);
		   
		   
//}
	   
}

