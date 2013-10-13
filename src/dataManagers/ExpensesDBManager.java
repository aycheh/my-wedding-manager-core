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
import system.TotalExpenses;
import system.User;

public class ExpensesDBManager implements ExpensesManager {
	private static ExpensesDBManager instance;
	
	private ExpensesDBManager() {
	}

	public static ExpensesDBManager getInstance() {
		if (instance == null) {
			instance = new ExpensesDBManager();
		}
		return instance;
	}

	@Override
	public void CreateAReceivedPayment(Connection con, Expenses exp) {
		// TODO Auto-generated method stub
		try {
			String sql = "insert into Expenses value (?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1,exp.getId());
			pstmt.setInt(3, exp.getUser_id());
			pstmt.setInt(2	,exp.getPerson_id());
			pstmt.setDouble(6,exp.getReceived_payment());
			pstmt.setDouble(7,exp.getPayback_payment());
			pstmt.setString(8,exp.getPayment_type());
			pstmt.setString(9,exp.getEventType());
			pstmt.setString(10,exp.getPayback_payment_eventType());
			pstmt.setString(11,exp.getEventAddress());
			pstmt.setString(12,exp.getComment());
			pstmt.setDate(13,exp.getDate());
			pstmt.setString(4,exp.getPerson_firstName());
			pstmt.setString(5,exp.getPerson_lastName());
			
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			rs.next();
	
		} catch (SQLException e) {
			System.out.println("creating Expenses filed, try again");
			e.printStackTrace();
		}
		
	}
	
	

	@Override
	public void UpdateAReceivedPayment(Connection con, Expenses exp) {
		// TODO Auto-generated method stub
		try {
			String sql = "update Expenses set " +
					"received_payment = ? ," +
					"payback_payment = ? ," +
					"payment_type = ? ," +
					"eventType = ?," +
					"payback_payment_eventType =? ," +
					"eventAddress =? ," +
					"comment = ? ," +
					"date=? where id = ?" ;
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setDouble(1,exp.getReceived_payment());
			pstmt.setDouble(2, exp.getPayback_payment());
			pstmt.setString(3, exp.getPayment_type());
			pstmt.setString(4,exp.getEventType());
			pstmt.setString(5,exp.getPayback_payment_eventType());
			pstmt.setString(6,exp.getEventAddress());
			pstmt.setString(7,exp.getComment());
			pstmt.setDate(8,exp.getDate());
			pstmt.setInt(9,exp.getId());
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.err.println("From Sql --- > No UpdateAReceivedPayment found");
			e.printStackTrace();
		}
		
	
		
	}

	@Override
	public Expenses GetAReceivedPayment(Connection con, int person_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void DeleteAReceivedPayment(Connection con, int person_id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Expenses> getAllReceivedPayment(Connection con,int user_id) {
		// TODO Auto-generated method stub
System.out.println("List<Expenses> getAllReceivedPayment(Connection con,int user_id)");
		String query = "select * from Expenses where user_id = ?";
		
	    List<Expenses> expenses = new ArrayList<Expenses>();
	    try {
	    	PreparedStatement pstmt = con.prepareStatement(query);
	    	pstmt.setInt(1, user_id );
	    	ResultSet rs = pstmt.executeQuery();
	        while (rs.next()) {
	            int id = rs.getInt("id");
	            int user_id1 = rs.getInt("user_id");
	            String person_firstName = rs.getString("person_firstName");
	            String person_lastName = rs.getString("person_lastName");
	            int person_id1 = rs.getInt("person_id");
	            Double received_payment = rs.getDouble("received_payment");
	            Double payback_payment = rs.getDouble("payback_payment");
	            String payment_type = rs.getString("payment_type");
	            String eventType = rs.getString("eventType");
	            String payback_payment_eventType = rs.getString("payback_payment_eventType");
	            String eventAddress = rs.getString("eventAddress");
	            String comment = rs.getString("comment");
	            Date date = rs.getDate("date");
	            
//	   		 long ts = System.currentTimeMillis();
//	   		 java.sql.Date sqlDate = new Date(ts);
	    
//	            System.out.println("\n"+deposit_id + "\t" + client_id +
//	                               "\t" + balance + "\t" + type +
//	                               "\t" + estimated_balance + "\t" + opning_date + "\t" + closing_date);
	          
	          expenses.add(new Expenses(id, user_id1,person_firstName, person_lastName,person_id1, received_payment,payback_payment, payment_type,eventType, payback_payment_eventType, eventAddress, comment,(java.sql.Date) date));
	           
	        }  
	    } catch (SQLException e ) {
	    	System.out.println(query);
	    }
		return expenses;
	}

	@Override
	public void updateTotalExpenses(Connection con, TotalExpenses texp) {
		// TODO Auto-generated method stub
		try {
			String sql = "update Total_income_and_expenses set total_received = ? , Total_expenses = ? where user_id = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setDouble(2,texp.getTotalExpenses());
			pstmt.setDouble(1, texp.getTotalReceived());
			pstmt.setInt(3, texp.getUser_id());
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.err.println("From Sql --- > No user for TotalExpenses found");
			e.printStackTrace();
		}
		
	}

	@Override
	public TotalExpenses getTotalExpenses(Connection con, int user_id) {
		// TODO Auto-generated method stub
		TotalExpenses expensesToReturn = null;
		try {
			
			PreparedStatement pstmt = con.prepareStatement("select * from Total_income_and_expenses where user_id = ?");
			pstmt.setInt(1, user_id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				expensesToReturn = new TotalExpenses(rs.getInt(1));
				expensesToReturn.setId(rs.getInt(1));
				expensesToReturn.setUser_id(rs.getInt(2));
				expensesToReturn.setTotalReceived(rs.getDouble(3));
				expensesToReturn.setTotalExpenses(rs.getDouble(4));
				
			}
			
		} catch (SQLException e) {
			System.out.println("No User found , try again");
			e.printStackTrace();
		}
		System.out.println("The user expenses To Return is --->>"+ expensesToReturn);
		return expensesToReturn;
		
	}

	@Override
	public void createTotalExpenses(Connection con, TotalExpenses texp) {
		// TODO Auto-generated method stub
		try {
			String sql = "insert into Total_income_and_expenses value (?,?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1,texp.getId());
			pstmt.setInt(2,texp.getUser_id());
			pstmt.setDouble(3, texp.getTotalReceived());
			pstmt.setDouble(4,texp.getTotalExpenses());
			
			
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			rs.next();
			
		} catch (Exception e) {
			System.out.println("creating createTotalExpenses filed, try again");
			e.printStackTrace();
		}
	}
	

	

}
