package system;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;

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
		System.out.println("Image saved succssefully"); 
		 }else{
			 System.out.println("no Image"); 
		  }
		}
	}
	
	public UserImage GetUserPhoto(User u,UserAction uac){
		UserImage user_image = new UserImage(u.getId());
		//UserAction uac =  login(u.getEmail() , u.getPassword());
		if (uac !=null){
			user_image =	UsersDBManager.getInstance().GetUserImage(con.getConnectionFromPool(),user_image.getUser_id());
			System.out.println("user_image --- " + user_image);
		}
		return user_image;
	}
	
	public void CreateUser(User u,InputStream ips){
		User user = new User(u.getId(),u.getFirstName(),u.getLastName(),u.getPassword(),u.getEmail());
		UserAction uac =  login(user.getEmail() ,user.getPassword());
		if (uac == null){
		try {
			UsersDBManager.getInstance().CreateNewUser(con.getConnectionFromPool(), user);
			uploadUserPhoto(user, ips);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}else{
		System.out.println("user already have a photo");
	  }
		
	}
	
	public UserAction login(String email , String password){		
		User user = new User(password , email);
		user = UsersDBManager.getInstance().GetUser(con.getConnectionFromPool(), user.getEmail());		
		if (user != null && password.equals(user.getPassword()) && email.equals(user.getEmail())){
			user = new User(user.getId(), user.getFirstName(), user.getLastName(), user.getPassword(), user.getEmail());
		System.out.println("Wellcome  : "+ user.getFirstName());
		GetUserPhoto(user,new UserAction(user));
		return new UserAction(user);
		}else{
			System.err.println("Invalid user");
		return null;
		}
	} 
	
	
	
	
}
