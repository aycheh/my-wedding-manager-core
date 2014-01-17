package dataManagers;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import system.User;

public class UsersDBManager implements UsersManager {
	private static UsersDBManager instance;
	private UsersDBManager() {
	}

	public static UsersDBManager getInstance() {
		if (instance == null) {
			instance = new UsersDBManager();
		}
		return instance;
	}
	
	
	@Override
	public void CreateNewUser(Connection con, User u) {
		// TODO Exception handling 
		try {
			String sql = "insert into Users value (?,?,?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1,u.getId());
			pstmt.setString(2,u.getFirstName());
			pstmt.setString(3,u.getLastName());
			pstmt.setString(4,u.getPassword());
			pstmt.setString(5, u.getEmail());
			
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			rs.next();
			
		} catch (Exception e) {
			System.out.println("creating user filed, try again");
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void UpdateAuser(Connection con,  User u) {
		// TODO Exception handling 
		try {
			String sql = "update Users set first_name = ? , last_name = ? , password = ? , email = ?  where id = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(5,u.getId());
			pstmt.setString(1,u.getFirstName());
			pstmt.setString(2,u.getLastName());
			pstmt.setString(3,u.getPassword());
			pstmt.setString(4, u.getEmail());
			
			pstmt.executeUpdate();
			System.out.println(" SQL UpdateAuser : User successfully updated "); 
		} catch (Exception e) {
			System.out.println("updating user filed, try again");
			e.printStackTrace();
		}
	}

	
	
	@Override
	public User GetUser(Connection con, String email){
		// TODO Exception handling 
		User UserToReturn = null;
		try {
			PreparedStatement pstmt = con.prepareStatement("select * from Users where email = ?");
			pstmt.setString(1, email);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				UserToReturn = new User(rs.getString(1));
				UserToReturn.setId(rs.getInt(1));
				UserToReturn.setFirstName(rs.getString(2));
				UserToReturn.setLastName(rs.getString(3));
				UserToReturn.setPassword(rs.getString(4));
				UserToReturn.setEmail(rs.getString(5));	
			}
			
		} catch (SQLException e) {
			System.out.println("No User found , try again");
			e.printStackTrace();
		}
		return UserToReturn;
		
	}

	@Override
	public void saveUserPhoto(Connection con, User u , InputStream ips) {
		try {
            // constructs SQL statement
            String sql = "INSERT INTO user_image (user_id, first_name, last_name, photo) values (?, ?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, u.getId());
            statement.setString(2, u.getFirstName());
            statement.setString(3, u.getLastName());
             
            if (ips != null) {
                // fetches input stream of the upload file for the blob column
                statement.setBlob(4, ips);
            }
 
            // sends the statement to the database server
            int row = statement.executeUpdate();
            if (row > 0) {
                System.out.println("File uploaded and saved into database");
            }
        } catch (SQLException ex) {
           
            ex.printStackTrace();
        }
	}

}
