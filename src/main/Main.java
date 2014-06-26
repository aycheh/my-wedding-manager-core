package main;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.ObjectInputStream.GetField;
import java.sql.Connection;

import dataManagers.UsersDBManager;
import dataManagers.UsersManager;
import dataManagers.WeddingConnectionPoolManager;
import system.Expenses;
import system.MyWeddingManager;
import system.Person;
import system.TotalExpenses;
import system.User;
import system.UserAction;

import java.sql.Date;
import java.util.BitSet;

import javax.imageio.ImageIO;

public class Main {

	/**
	 * @param args
	 */

	public static void main(String[] args) {
		MyWeddingManager my = MyWeddingManager.getInstance();
		UserAction uac = my.login( "asher@gmail.com","1234");
		//uac.searchPerson("%asher%" , "%a%",3);
		
		/**uploadUserPhoto**/
//		InputStream ips = null;
//		File image = new File("/home/asher/Pictures/2012/05/היליי ופזית באמבטיה/CIMG0410.JPG");
//		
//		try {
//			ips = new FileInputStream(image);
//			System.out.println(image.length()/1000);
//			User user = new User(1,"asher","Aycheh","1234","asher@gmail.com");
//			my.uploadUserPhoto(user, ips);
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		/**Get User Photo**/
		User user = new User("asher@gmail.com");
		System.out.println("GetUserPhoto(user)");
		my.GetUserPhoto(user,uac);
		
		/**CreateUser with photo**/
//		InputStream ips = null;
//		File image = new File("/home/asher/Pictures/2012/05/13/CIMG0311.JPG");
//		try {
//			ips = new FileInputStream(image);
//			User u = new User(0, "aba", "sheli", "1234", "abnatan@gmail.org");
//			//my.uploadUserPhoto(u, ips);
//			my.CreateUser(u ,ips);
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		InputStream ips = new BufferedInputStream(new FileInputStream(path));
//      BufferedImage image = ImageIO.read(ips);
		
//		WeddingConnectionPoolManager con = new WeddingConnectionPoolManager();
//		User u = new User(0,"asher","aycheh","12345","asher@gmail.com");
//		UsersDBManager.getInstance().UpdateAuser(con.getConnectionFromPool() , u);

//----------------------------------------		
//		User u = new User(0, "נדאו", "יוסי", "1234", "TED@gmail.com");
//		my.CreateUser(u);
//--------------------------------	
//		UserAction uac = new UserAction();
//		uac.getUser("asher@gmail.com");	
//-------------------------------------------
		//User u = new User(0,"Tekle","seffefe","1234","tekle@hotmaol.com");
		//uac.Updateuser(u);
//---------------------------------------------
		//Person pr = new Person(0,"Tigi","Aycheh","a sister","New York 2","054-4450272","Mesi@gmail.com","3 creating person",0);
		//uac.CreatePerson(pr);
		
//		User ur = uac.getUser("asher@gmail.com");
		//Person pr = new Person(29,"dandin","setu","ant","natania","054-4450272","wq@gmail.com","3 creating person",ur.getId());
		//uac.CreatePersonsByFLRUId(pr);
		//uac.getallPersons(3);
		
		
		
		
//---------------------------------------------
		//User u = uac.getUser("mes@gmail.com");
		//Person pr = new Person(29,"Tekle","seffefe","abro","Tal aviv","054-4450272","tekle@gmail.com"," acording to id",1);
		//uac.updatePerson(pr);
		//uac.getallPersons(3);
////------------------------------------------------
		//Person pr = new Person(0,"yy","yy","yy","USA","054-4330272","yy@gmail.net","yy per+toa+py",7);
		//System.out.println("ssssssssssssssrrrrrrrr " + pr);
		// long ts = System.currentTimeMillis();
		// java.sql.Date sqlDate = new Date(ts);
		//// Expenses exp = new Expenses(0, pr.getUser_id(),pr.getFirstName(), pr.getLastName(), pr.getId(), 200, 200,"","", "master card", "birthday", "Bar Mizva","Nataniya", "birthday it was good", sqlDate);
		//// Expenses exp = new Expenses(0, 1, pr.getId(), 2, 4, "no money ", "birthday", "Bar Mizva","Nataniya", "birthday it was good", sqlDate);
		// Expenses exp = new Expenses(0, pr.getUser_id(), pr.getFirstName(), pr.getLastName(), pr.getId(), 900, 700, "master card", "birthday", "Bar Mizva","Nataniya", "natania", sqlDate);
		// TotalExpenses toxp = new TotalExpenses(exp.getUser_id());
		//uac.CreatePersonAndCreateReceivedPayment(exp, pr, toxp);
		//System.out.println(exp);
//----------------------------------------------------
//ALTER TABLE contacts ADD email VARCHAR(60);
// ALTER TABLE contacts ADD email VARCHAR(60) AFTER name;
	    //payback_payment_eventType 
//-----------------------------------------------
		//User ur = new User("Eden@gmail.com");
		//uac.getUser(ur.getEmail());
		//Person pr = new Person(32,"Almogi","getshee","dother","roshon la zion","054-4450272","Al@gmail.co.il","Traing to update person",3);
		//uac.updatePerson(pr);
		//uac.getPerson(pr.getId());
		// long ts = System.currentTimeMillis();
		// java.sql.Date sqlDate = new Date(ts);
		
		 //Expenses exp = new Expenses(1, 0, 1, 600, 750, "check", "wedding", "Bar Mizva","Nataniya", "it was good", sqlDate);
		// Expenses exp = new Expenses(88, 1, pr.getFirstName(), pr.getLastName(), 29, 100, 101, "master card", "birthday", "Bar Mizva","Nataniya", "natania", sqlDate);
		// uac.updateReceivedPayment(exp, pr);

		 //uac.uacUpdateUserPramOnAllReceivedPayment(ur.getId(), pr);

		 
//		  int person_id = 31;
//		  String personFirstName = "mesyAyche";
//		  String personLastName = "seffefeaychehh";
//		  String relationship = "dother";
//		  String pesronAddress = "Hedera";
//		  String personPhone = "050-1111";
//		  String personEmail = "mesyAyche@walla.com";
//		  String pesronComment = "from main the last test";  
//		  //exp
//		  int expenses_id = 88;
//		  double payback_payment = 8888;
//		  double received_payment = 7777;
//		  //double
//		  String payment_type = "check";
//		  String eventType = "eventType";
//		  String payback_payment_eventType = "payback_payment_eventType"; 
//		  String eventAddress = "Risho L eventAddress";
//		  String expensescomment = "expen ++";
//		  
//		  Person Personto = null;
//		  Person p = new Person(31);
//		  Personto = uac.getPerson(p.getId());
//		  System.out.println("asssssssssssssssssssssssssassssssssssssssss"  + Personto);
		  
		  
//		  User ur = new User("a@b.co.il");
//		  ur = uac.getUser(ur.getEmail());
//
//		  	Person p = new Person(person_id, 
//		  			personFirstName,
//		  			personLastName, 
//		  			relationship,
//		  			pesronAddress, 
//		  			personPhone, 
//		  			personEmail, 
//		  			pesronComment, 
//		  			ur.getId());
//System.out.println("+++++++++++++++++++++++++++++++ ="+p);		  	
//			Expenses exp = new Expenses(expenses_id,
//					ur.getId(),
//					p.getFirstName(),
//					p.getLastName(), 
//					p.getId(),
//					received_payment, 
//					payback_payment, 
//					payment_type, 
//					eventType, 
//					payback_payment_eventType, 
//					eventAddress, 
//					expensescomment,
//					sqlDate);
//     		uac.updateReceivedPayment(exp, p);
     		//uac.updatePerson(p);
     	//	==========================
     		
     		
		//	uac.getExpensesByID(88);
//----------------------------------------------
	//	uac.getAllReceivedPayment(2);

	//============================
		//if(arrayList != null && !arrayList.isEmpty()) {}	
//		if(allPersons != null && !allPersons.isEmpty() ) {
//			System.out.println("person to create :) :)");
//			System.out.println("break; 2");
	// !allPersons.contains(ps)
//			break;
		//(allPersons != null && !allPersons.isEmpty())
	//if(lList1.Equals(lList))
//=============================
		
	}

}

/**how to convert blob into bytearray**/
//loadset.getString("image"); 
//
//byte[] bytearray; 
//
//Blob blobimg=loadset.getBlob("image"); 
//
//if(blobimg!= null) 
//
//{ 
//
//BufferedInputStream bis = new BufferedInputStream(blobimg.getBinaryStream()); 
//
//ByteArrayOutputStream bao = new ByteArrayOutputStream(); 
//
//byte[] buffer = new byte[4096]; 
//
//int length = 0; 
//
//while ((length = bis.read(buffer)) != -1) 
//
//{ 
//
//JOptionPane.showMessageDialog (null,"writing image"); 
//
//bao.write(buffer, 0, length); 
//
//} 
//
//bao.close(); 
//
//bis.close(); 
//
//bytearray = bao.toByteArray(); 
//
//ImageIcon icon=new ImageIcon(bytearray); 
//
//imagelabel.setIcon(icon); 
//
//} 
/** another option is **/ 
//he mySql blob class has the following function :
//
//blob.getBytes
//
//use it like this:
//
////(assuming you have a ResultSet named RS)
//Blob blob = rs.getBlob("SomeDatabaseField");
//
//int blobLength = (int) blob.length();  
//byte[] blobAsBytes = blob.getBytes(1, blobLength);
//
////release the blob and free up memory. (since JDBC 4.0)
//blob.free();





