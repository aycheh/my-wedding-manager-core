package dataManagers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import system.Expenses;
import system.Person;
import system.User;

public class PersonDBManager implements PersonManager {

	private static PersonDBManager instance;

	private PersonDBManager() {
	}

	public static PersonDBManager getInstance() {
		if (instance == null) {
			instance = new PersonDBManager();
		}
		return instance;
	}

	@Override
	public void CreateNewPerson(Connection con, Person p) {
		// TODO Auto-generated method stub
		System.out.println("CreateNewPerson(Connection con, Person p)");
		String sql = "insert into Person value (?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, p.getId());
			pstmt.setString(2, p.getFirstName());
			pstmt.setString(3, p.getLastName());
			pstmt.setString(4, p.getRelationship());
			pstmt.setString(5, p.getAddress());
			pstmt.setString(6, p.getEmail());
			pstmt.setString(7, p.getPhone());
			pstmt.setInt(8, p.getUser_id());
			pstmt.setString(9, p.getComment());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			rs.next();
System.out.println( "SQL srom insert person - rs  ...." + rs);
		} catch (SQLException e) {
			System.out.println("creating peson filed, try again");
			e.printStackTrace();
		}
	}

	@Override
	public void UpdateAPerson(Connection con, Person p) {
		// TODO Auto-generated method stub
		System.out.println("UpdateAPerson(Connection con, Person p");
		String sql = "update Person set " + "first_name =? ,"
				+ "last_name =?, " + "relationship =?, " + "address = ? ,"
				+ "email = ?, " + "phone = ?, " + "user_id = ? ,"
				+ "comment = ? " + "where id= ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(9, p.getId());
			pstmt.setString(1, p.getFirstName());
			pstmt.setString(2, p.getLastName());
			pstmt.setString(3, p.getRelationship());
			pstmt.setString(4, p.getAddress());
			pstmt.setString(5, p.getEmail());
			pstmt.setString(6, p.getPhone());
			pstmt.setInt(7, p.getUser_id());
			pstmt.setString(8, p.getComment());
			pstmt.executeUpdate();
			System.out.println("SQL : pstmt.executeUpdate()" + pstmt);
		} catch (SQLException e) {
			System.out.println("creating peson filed, try again");
			e.printStackTrace();
		}
	}

	@Override
	public Person GetPerson(Connection con, String firstName, String lastName,
			String relationship) {
		// TODO Auto-generated method stub
		Person PersonToReturn = null;
		try {
			PreparedStatement pstmt = con
					.prepareStatement("select * from Person where first_name = ? and last_name = ? and relationship = ? ");
			pstmt.setString(1, firstName);
			pstmt.setString(2, lastName);
			pstmt.setString(3, relationship);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				PersonToReturn = new Person(firstName, lastName, relationship);
				PersonToReturn.setId(rs.getInt(1));
				PersonToReturn.setFirstName(rs.getString(2));
				PersonToReturn.setLastName(rs.getString(3));
				PersonToReturn.setRelationship(rs.getString(4));
				PersonToReturn.setAddress(rs.getString(5));
				PersonToReturn.setEmail(rs.getString(6));
				PersonToReturn.setPhone(rs.getString(7));
				PersonToReturn.setUser_id(rs.getInt(8));
				PersonToReturn.setComment(rs.getString(9));
				System.out.println("FROM PersonToReturn QUERY :" + PersonToReturn);
			}
		} catch (Exception e) {
			System.out
					.println("From person SQL : -> No person found, try again");
			e.printStackTrace();
		}
		return PersonToReturn;
	}

	@Override
	public void DeleteAPerson(Connection con, String firstName,
			String lastName, String relationship) {
		// TODO Auto-generated method stub

	}

	@Override
	public Person GetPersonByID(Connection con, int id) {
		Person PersonByIDToReturn = null;
		try {
			PreparedStatement pstmt = con
					.prepareStatement("select * from Person where id = ?");
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				PersonByIDToReturn = new Person(id);
				PersonByIDToReturn.setId(rs.getInt(1));
				PersonByIDToReturn.setFirstName(rs.getString(2));
				PersonByIDToReturn.setLastName(rs.getString(3));
				PersonByIDToReturn.setRelationship(rs.getString(4));
				PersonByIDToReturn.setAddress(rs.getString(5));
				PersonByIDToReturn.setEmail(rs.getString(6));
				PersonByIDToReturn.setPhone(rs.getString(7));
				PersonByIDToReturn.setUser_id(rs.getInt(8));
				PersonByIDToReturn.setComment(rs.getString(9));
				System.out.println("PersonByIDToReturn  = "
						+ PersonByIDToReturn);
				System.out.println("ResultSet rs = pstmt.executeQuery() ");
			}
		} catch (Exception e) {
			System.out
					.println("From person SQL : -> No person found, try again");
			e.printStackTrace();
		}
		return PersonByIDToReturn;
	}

	@Override
	public List<Person> getAllPersonsByFLRUId(Connection con, String firstName,
			String lastName, String relationship,int user_id)  {
		// TODO Auto-generated method stub
		
		String query = "select * from Person where first_name = ? and last_name = ? and relationship = ? and user_id =?";
		
	    List<Person> allPersons = new ArrayList<Person>();
	    try {
	    	PreparedStatement pstmt = con.prepareStatement(query);
	    	pstmt.setString(1, firstName );
	    	pstmt.setString(2, lastName );
	    	pstmt.setString(3, relationship );
	    	pstmt.setInt(4, user_id);
	    	ResultSet rs = pstmt.executeQuery();
	        while (rs.next()) {
	            int id = rs.getInt("id");
	            int user_id1 = rs.getInt("user_id");
	            String first_name1 = rs.getString("first_name");
	            String last_name1 = rs.getString("last_name");
	            String relationship1 = rs.getString("relationship");	            
	            String address = rs.getString("address");  
	            String email = rs.getString("email");	            
	            String phone = rs.getString("phone");  
	            String comment = rs.getString("comment");

//System.out.println("SQL QUERY FOR - allPersons :::::>>" + allPersons);	            
	   		// long ts = System.currentTimeMillis();
	   		// java.sql.Date sqlDate = new Date(ts);
	    
//	            System.out.println("\n"+id + "\t" + first_name1 +
//	                               "\t" + last_name1 + "\t" + relationship1 +
//	                               "\t" + address + "\t" + phone + "\t" + email + "\t" + comment
//	            + "\t" + user_id);
//	          
	            allPersons.add(new Person(id, first_name1, last_name1, 
	            		relationship1, address, phone, email, comment, user_id1));
	            
	          
	        }  
	    } catch (SQLException e ) {
	    	System.out.println(query);
	    }
	    
		return allPersons;
	
	}

	@Override
	public List<Person> GetUserPersonsList(Connection con, int user_id) {
			String query = "select * from  Person where user_id =?";
		
	    List<Person> allPersons = new ArrayList<Person>();
	    try {
	    	PreparedStatement pstmt = con.prepareStatement(query);

	    	pstmt.setInt(1, user_id);
	    	ResultSet rs = pstmt.executeQuery();
	        while (rs.next()) {
	            int id = rs.getInt("id");
	            int user_id1 = rs.getInt("user_id");
	            String first_name1 = rs.getString("first_name");
	            String last_name1 = rs.getString("last_name");
	            String relationship1 = rs.getString("relationship");	            
	            String address = rs.getString("address");  
	            String email = rs.getString("email");	            
	            String phone = rs.getString("phone");  
	            String comment = rs.getString("comment");

//System.out.println("SQL QUERY FOR - allPersons :::::>>" + allPersons);	            
	   		// long ts = System.currentTimeMillis();
	   		// java.sql.Date sqlDate = new Date(ts);
	    
//	            System.out.println("\n"+id + "\t" + first_name1 +
//	                               "\t" + last_name1 + "\t" + relationship1 +
//	                               "\t" + address + "\t" + phone + "\t" + email + "\t" + comment
//	            + "\t" + user_id);
//	          
	            allPersons.add(new Person(id, first_name1, last_name1, 
	            		relationship1, address, phone, email, comment, user_id1));
	            
	          
	        }  
	    } catch (SQLException e ) {
	    	System.out.println(query);
	    }
	    
		return allPersons;
	}

	
	
	
}