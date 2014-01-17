package system;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import dataManagers.UsersDBManager;
import dataManagers.WeddingConnectionPoolManager;



public class MyWeddingManager {
	protected WeddingConnectionPoolManager con;
	private static MyWeddingManager instance = new MyWeddingManager();

	
	public static MyWeddingManager getInstance() {
		return instance;
	}
	
	
	
	private MyWeddingManager() {
		con = new WeddingConnectionPoolManager();
	}
	
	// this method should receive object "User" and Object "Photo"
	public void uploadUserPhoto(User u, InputStream ips){
		UserAction uac =  login(u.getEmail() , u.getPassword());
		if (uac != null){
		u = UsersDBManager.getInstance().GetUser(con.getConnectionFromPool(), u.getEmail());
		if(ips != null){
		UsersDBManager.getInstance().saveUserPhoto(con.getConnectionFromPool(), u, ips );
		 }else{
			 System.out.println("no Image");
		  }
		}
	}
	
	public void CreateUser(User u,InputStream ips){
		User user = new User(u.getId(),u.getFirstName(),u.getLastName(),u.getPassword(),u.getEmail());	
		UsersDBManager.getInstance().CreateNewUser(con.getConnectionFromPool(), user);
		uploadUserPhoto(user, ips);
	}
	
	public UserAction login(String email , String password){		
		User user = new User(password , email);
		user = UsersDBManager.getInstance().GetUser(con.getConnectionFromPool(), user.getEmail());		
		if (user != null && password.equals(user.getPassword()) && email.equals(user.getEmail())){
			user = new User(user.getId(), user.getFirstName(), user.getLastName(), user.getPassword(), user.getEmail());
		System.out.println("Wellcome  : "+ user.getFirstName());
		return new UserAction(user);
		}else{
			System.err.println("Invalid user");
		return null;
		}
	} 
	
	
	
	
}
