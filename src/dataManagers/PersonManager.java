package dataManagers;

import java.sql.Connection;
import java.util.List;

import system.Expenses;
import system.Person;
import system.User;
public interface PersonManager {

	

    public void CreateNewPerson(Connection con , Person p);
	public void UpdateAPerson(Connection con ,  Person p);
	public void UpdateAPersonFirstLastName(Connection con ,  Person p);
	public Person GetPerson(Connection con , String firstName , String lastName,String relationship);
	public void DeleteAPerson(Connection con ,  String firstName , String lastName,String relationship);
	public  Person GetPersonByID(Connection con ,int id);
	public List<Person> getAllPersonsByFLRUId(Connection con,String firstName , String lastName,String relationship, int user_id);

}