package main;

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

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MyWeddingManager my = MyWeddingManager.getInstance();
		UserAction uac = my.login( "a@b.co.il","1234");
		
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
//		User u = new User(9,"a","ay","1234","asher@hotmaol.com");
//		uac.Updateuser(u);
//---------------------------------------------
//		Person pr = new Person(0,"Mesi","Aycheh","a sister","New York 2","054-4450272","Mesi@gmail.com","3 creating person",0);
//		uac.CreatePerson(pr);
//---------------------------------------------
		//Person pr = new Person(0,"asher_main","Aycheh_mail","a sister","Tal aviv","054-4450272","Mesi@gmail.com"," acording to id",0);
		//uac.updatePerson(pr);
////------------------------------------------------
//		Person pr = new Person(0,"yy","yy","yy","USA","054-4330272","yy@gmail.net","yy per+toa+py",7);
//		System.out.println("ssssssssssssssrrrrrrrr " + pr);
//		 long ts = System.currentTimeMillis();
//		 java.sql.Date sqlDate = new Date(ts);
//		//// Expenses exp = new Expenses(0, pr.getUser_id(),pr.getFirstName(), pr.getLastName(), pr.getId(), 200, 200,"","", "master card", "birthday", "Bar Mizva","Nataniya", "birthday it was good", sqlDate);
//		//// Expenses exp = new Expenses(0, 1, pr.getId(), 2, 4, "no money ", "birthday", "Bar Mizva","Nataniya", "birthday it was good", sqlDate);
//		 Expenses exp = new Expenses(0, pr.getUser_id(), pr.getFirstName(), pr.getLastName(), pr.getId(), 10, 10, "master card", "birthday", "Bar Mizva","Nataniya", "natania", sqlDate);
//		 TotalExpenses toxp = new TotalExpenses(exp.getUser_id());
//		uac.CreatePersonAndCreateReceivedPayment(exp, pr, toxp);
//		System.out.println(exp);
//----------------------------------------------------
//ALTER TABLE contacts ADD email VARCHAR(60);
// ALTER TABLE contacts ADD email VARCHAR(60) AFTER name;
	    //payback_payment_eventType 
//-----------------------------------------------
		
//		Person pr = new Person(0,"Mesi","Aycheh","a sister","New York 2","054-4450272","Mesi@gmail.co.il","3 update person",0);
		 long ts = System.currentTimeMillis();
		 java.sql.Date sqlDate = new Date(ts);
		//Expenses exp = new Expenses(1, 0, 1, 600, 750, "check", "wedding", "Bar Mizva","Nataniya", "it was good", sqlDate);
//		 Expenses exp = new Expenses(88, pr.getUser_id(), pr.getFirstName(), pr.getLastName(), pr.getId(), 10, 10, "master card", "birthday", "Bar Mizva","Nataniya", "natania", sqlDate);
//		 uac.updateReceivedPayment(exp, pr);
		  int person_id = 31;
		  String personFirstName = "mesyAyche";
		  String personLastName = "seffefeaychehh";
		  String relationship = "dother";
		  String pesronAddress = "Hedera";
		  String personPhone = "050-1111";
		  String personEmail = "mesyAyche@walla.com";
		  String pesronComment = "from main the last test";  
		  //exp
		  int expenses_id = 88;
		  double payback_payment = 8888;
		  double received_payment = 7777;
		  //double
		  String payment_type = "check";
		  String eventType = "eventType";
		  String payback_payment_eventType = "payback_payment_eventType"; 
		  String eventAddress = "Risho L eventAddress";
		  String expensescomment = "expen ++";
		  
//		  Person Personto = null;
//		  Person p = new Person(31);
//		  Personto = uac.getPerson(p.getId());
//		  System.out.println("asssssssssssssssssssssssssassssssssssssssss"  + Personto);
		  
		  
		  User ur = new User("a@b.co.il");
		  ur = uac.getUser(ur.getEmail());

		  	Person p = new Person(person_id, 
		  			personFirstName,
		  			personLastName, 
		  			relationship,
		  			pesronAddress, 
		  			personPhone, 
		  			personEmail, 
		  			pesronComment, 
		  			ur.getId());
System.out.println("+++++++++++++++++++++++++++++++ ="+p);		  	
			Expenses exp = new Expenses(expenses_id,
					ur.getId(),
					p.getFirstName(),
					p.getLastName(), 
					p.getId(),
					received_payment, 
					payback_payment, 
					payment_type, 
					eventType, 
					payback_payment_eventType, 
					eventAddress, 
					expensescomment,
					sqlDate);
     	//	uac.updateReceivedPayment(exp, p);
     	//	uac.updatePerson(p);
     	//	==========================
     		
     		
		//	uac.getExpensesByID(88);
//----------------------------------------------
	//	uac.getAllReceivedPayment(2);

		
		
	}

}