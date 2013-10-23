package dataManagers;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import system.Expenses;
import system.Person;
import system.TotalExpenses;
import system.User;

public interface ExpensesManager {

	
	public void CreateAReceivedPayment(Connection con , Expenses exp );
	public void UpdateAReceivedPayment(Connection con ,  Expenses exp);
	
	public Expenses GetExpensesByID(Connection con , int id);
	public List<Expenses> getAllReceivedPayment(Connection con,int user_id);
	public void DeleteAReceivedPayment(Connection con ,  int person_id);//list
	public void updateTotalExpenses(Connection con ,TotalExpenses texp);
	public TotalExpenses getTotalExpenses(Connection con ,int user_id);
	public void createTotalExpenses(Connection con ,TotalExpenses txpenses);
	public void UpdatePersonPramOnAllReceivedPayment(Connection con,Expenses exp);
	public void updatePersonParamOnExpensesTable(Connection con , 
			String person_firstName, String person_lastName,int person_id,int user_id);
	
	
	
	
	
	
}
