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
	public Expenses GetAReceivedPayment(Connection con , int person_id);//list
	public List<Expenses> getAllReceivedPayment(Connection con,int user_id);
	public void DeleteAReceivedPayment(Connection con ,  int person_id);//list
	public void updateTotalExpenses(Connection con ,TotalExpenses texp);
	public TotalExpenses getTotalExpenses(Connection con ,int user_id);
	public void createTotalExpenses(Connection con ,TotalExpenses txpenses);
	
	
	
	
	
}
