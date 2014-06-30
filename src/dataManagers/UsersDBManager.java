package dataManagers;

import java.io.InputStream;
import java.io.OutputStream;
import java.security.acl.LastOwnerException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import system.User;
import system.UserImage;

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
			if (rs != null ){
				rs.close();
			}if (pstmt != null){
				pstmt.close();
			}if (con != null){
				con.close();
			}
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
			if (pstmt != null){
				pstmt.close();
			}if (con != null){
				con.close();
			}
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
			if (rs != null ){
				rs.close();
			}if (pstmt != null){
				pstmt.close();
			}if (con != null){
				con.close();
			}
			
		} catch (SQLException e) {
			System.out.println("No User found , try again");
			e.printStackTrace();
		}
		//System.out.println("check if con = null = " + con);
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
        	if (statement != null){
				statement.close();
			}if (con != null){
				con.close();
				System.out.println("connection saveUserPhoto closed");
			}
        } catch (SQLException ex) {
           
            ex.printStackTrace();
        }
	}

	@Override
	public UserImage GetUserImage(Connection con, int user_id) {
		// TODO Auto-generated method stub
		UserImage user_imageToreturn = null;
		try {
			PreparedStatement pstmt = con.prepareStatement("select * from user_image where user_id = ?");
			pstmt.setInt(1, user_id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
		       user_imageToreturn = new UserImage(rs.getInt(2));
		       user_imageToreturn.setId(rs.getInt(1));
		       user_imageToreturn.setUser_id(rs.getInt(2));
		       user_imageToreturn.setFirst_name(rs.getString(3));
		       user_imageToreturn.setLast_name(rs.getString(4));
		       user_imageToreturn.setPhoto(rs.getBlob(5));
			}
			if (rs != null ){
				rs.close();
				System.out.println("rs GetUserImage closed");
			}if (pstmt != null){
				pstmt.close();
				System.out.println("pstmt GetUserImage closed");
			}if (con != null){
				con.close();
				System.out.println("connection GetUserImage closed");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user_imageToreturn;
	}

}
