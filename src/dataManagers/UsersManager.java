package dataManagers;

import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;

import system.User;
import system.UserImage;



public interface UsersManager {

	public void CreateNewUser(Connection con , User u);
	public void UpdateAuser(Connection con ,  User u);
	public User GetUser(Connection con , String email);
	public void saveUserPhoto(Connection con ,User u ,InputStream ips);
	public UserImage GetUserImage(Connection con ,int user_id);
	
	
}
