package system;

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

	
	public void CreateUser(User u){
		User user = new User(u.getId(),u.getFirstName(),u.getLastName(),u.getPassword(),u.getEmail());	
		UsersDBManager.getInstance().CreateNewUser(con.getConnectionFromPool(), user);
			System.out.println(user);		
				
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
