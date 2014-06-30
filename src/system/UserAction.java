package system;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import dataManagers.ExpensesDBManager;
import dataManagers.PersonDBManager;
import dataManagers.UsersDBManager;
import dataManagers.WeddingConnectionPoolManager;


public class UserAction {
	private User user;
	
	public UserAction (User user){
		this.user = user;
		
	}

	public UserAction(){
	
	}

	public User getUser(String email){	
	User user = new User(email);	
	WeddingConnectionPoolManager con = new WeddingConnectionPoolManager();
	try {
		user = UsersDBManager.getInstance().GetUser(con.getConnectionFromPool(), user.getEmail());
	} catch (Exception e) {
		System.err.println("user not fornd");
		e.printStackTrace();
	}
	return user;
	}

	public Person getPerson(int id){
		Person p = new Person(id);
		WeddingConnectionPoolManager con = new WeddingConnectionPoolManager();
		try {
			p = PersonDBManager.getInstance().GetPersonByID(con.getConnectionFromPool(), id);
			System.out.println("GetPersonByID" + p);
		} catch (Exception e) {
			System.err.println("GetPersonByID Not Found");
			e.printStackTrace();
		}
		return p;
	}
	
	
	
	public Expenses getExpensesByID(int id){
		Expenses exp = new Expenses(id);
		WeddingConnectionPoolManager con = new WeddingConnectionPoolManager();
		try {
			exp = ExpensesDBManager.getInstance().GetExpensesByID(con.getConnectionFromPool(), exp.getId());
			System.out.println("from uac , getExpensesByID +++++++++++++++++" + exp);
		} catch (Exception e) {
			System.err.println("getExpensesByID Not found");
			e.printStackTrace();
		}
		return exp;
	}
	
	
	
	public void Updateuser(User u){
		WeddingConnectionPoolManager con = new WeddingConnectionPoolManager();
		if ((UsersDBManager.getInstance().GetUser(con.getConnectionFromPool(),user.getEmail())) != null){
			UsersDBManager.getInstance().UpdateAuser(con.getConnectionFromPool(), u);
		}else {
			//TODO throw exceptions 
		System.out.println("You are not alowed to update thid user");
		}
	  }
	
	
	
	
	
	
	/**this method is similar to "CreatePersonAndCreateReceivedPayment". it allowed just to 
	 * create person  **/
	public void CreatePerson(Person p){
		WeddingConnectionPoolManager con = new WeddingConnectionPoolManager(); 
		 Person p1 = new  Person(p.getId(), p.getFirstName(), p.getLastName(), p.getRelationship(),
				 p.getAddress(), p.getPhone(), p.getEmail(), p.getComment(), user.getId());
		 System.out.println("p1 : " + p1);
		 System.out.println("p : " + p);
		// TODO - should to iterate  on the results that returns when calling to "getpersons" method.
		PersonDBManager.getInstance().CreateNewPerson(con.getConnectionFromPool(), p1);	
	}
	
	
	// TODO ===>> get the person_id sum how !!! (from GUI OR sum how!!) 
	public Person updatePerson(Person pr){	
		Person old_p = new Person(pr.getId());
		Person new_p = new Person(pr.getId(), pr.getFirstName(), pr.getLastName(),
				pr.getRelationship(),pr.getAddress(),pr.getPhone(),pr.getEmail(),pr.getComment(),pr.getUser_id());
		System.out.println("new_p  : " +new_p);
		WeddingConnectionPoolManager con = new WeddingConnectionPoolManager();
		try {
			old_p = PersonDBManager.getInstance().GetPersonByID(con.getConnectionFromPool(), old_p.getId());
			System.out.println("old_p :" + old_p);
			if(old_p !=null){
			System.out.println("second new_p  to update :" +new_p);
			PersonDBManager.getInstance().UpdateAPerson(con.getConnectionFromPool(), new_p);
			ExpensesDBManager.getInstance().updatePersonParamOnExpensesTable(con.getConnectionFromPool(),
					new_p.getFirstName(), new_p.getLastName(), new_p.getId(), new_p.getUser_id());
			}
		} catch (Exception e) {
			System.err.println("GetPersonByID Not Found" + e.getMessage());
			e.printStackTrace();
		}
		return old_p;
		
	}
	

	public void CreatePersonAndCreateReceivedPayment(Expenses exp, Person p,TotalExpenses toxp){
		WeddingConnectionPoolManager con = new WeddingConnectionPoolManager();
		/**this condition should validate if the person already exist in the person table. if exist , do not insert it as a new person
		 * and avoid duplicate person ***/
		
		if(CreatePersonsByFLRUId(p) == null){
		Person pr = new Person(p.getId(),p.getFirstName(),p.getLastName(),p.getRelationship(),p.getAddress(),p.getPhone(),
				p.getEmail(),p.getComment(),p.getUser_id());	
		CreatePersonsByFLRUId(pr);
		}
		/**creating new person if not exist**/
		p = new Person(p.getId(),p.getFirstName(),p.getLastName(),p.getRelationship(),p.getAddress(),p.getPhone(),p.getEmail(),p.getComment(),user.getId());
		p =  PersonDBManager.getInstance().GetPerson(con.getConnectionFromPool(), p.getFirstName(), p.getLastName(),p.getRelationship());
		exp = new Expenses(exp.getId(), exp.getUser_id(),p.getFirstName(),p.getLastName() , p.getId(),
				exp.getReceived_payment(), exp.getPayback_payment(), exp.getPayment_type(),
				exp.getEventType(),exp.getPayback_payment_eventType(), exp.getEventAddress(), exp.getComment(), exp.getDate());
		ExpensesDBManager.getInstance().CreateAReceivedPayment(con.getConnectionFromPool(), exp);
		/**After creating the person and creating the Received/incoming Payment, this method should create total Expenses/
		 * incoming money in "Total_income_and_expenses" table. it also will check first if the user already
		 * have a total?! , if user have total the method only will update the total**/
		if(ExpensesDBManager.getInstance().getTotalExpenses(con.getConnectionFromPool(), exp.getUser_id()) == null){
			toxp = new TotalExpenses(0, exp.getReceived_payment(), exp.getPayback_payment(), exp.getUser_id());
			ExpensesDBManager.getInstance().createTotalExpenses(con.getConnectionFromPool(), toxp);	
		}else {
			toxp = ExpensesDBManager.getInstance().getTotalExpenses(con.getConnectionFromPool(), exp.getUser_id());
			toxp.setTotalReceived((toxp.getTotalReceived()) + (exp.getReceived_payment()));
			toxp.setTotalExpenses((toxp.getTotalExpenses()) + (exp.getPayback_payment()));
			ExpensesDBManager.getInstance().updateTotalExpenses(con.getConnectionFromPool(), toxp);
		}
		
	}
	
	public void updateReceivedPayment(Expenses exp, Person pr){
	WeddingConnectionPoolManager con = new WeddingConnectionPoolManager();
	long ts = System.currentTimeMillis();
	java.sql.Date sqlDate = new Date(ts);
	
	pr = new Person(pr.getId(),pr.getFirstName(),pr.getLastName(),pr.getRelationship(),pr.getAddress(),pr.getPhone(),
			pr.getEmail(),pr.getComment(),pr.getUser_id());
	exp = new Expenses(exp.getId(), exp.getUser_id(),pr.getFirstName(),pr.getLastName() , pr.getId(),
			exp.getReceived_payment(), exp.getPayback_payment(), exp.getPayment_type(),
			exp.getEventType(),exp.getPayback_payment_eventType(), exp.getEventAddress(), 
			exp.getComment(), sqlDate);
	ExpensesDBManager.getInstance().UpdateAReceivedPayment(con.getConnectionFromPool(), exp);	
	ExpensesDBManager.getInstance().UpdatePersonPramOnAllReceivedPayment(con.getConnectionFromPool(), exp);

}
	

	
	
	
	public List<Expenses> getAllReceivedPayment(int user_id){
		WeddingConnectionPoolManager con = new WeddingConnectionPoolManager();
		List<Expenses> allExpenses = new ArrayList<Expenses>();
		allExpenses = ExpensesDBManager.getInstance().getAllReceivedPayment(con.getConnectionFromPool(),
				user_id);		
				for (Expenses exp : allExpenses){
						System.out.println(exp);
					}
				System.out.println("sdsdsdsdsdsdsd");
		return allExpenses;
	}
	
public List<Person> getallPersons (int user_id){
	WeddingConnectionPoolManager con = new WeddingConnectionPoolManager();
	List<Person> Persons = new ArrayList<Person>();
	Persons = PersonDBManager.getInstance().getAllPersonsByUserId(con.getConnectionFromPool(), user_id);

	for(Person pr: Persons){
		System.out.println("FROM JAVA UAC  : "+pr);
	}
	
	return Persons;	
}
	
	

	public List<Person> CreatePersonsByFLRUId (Person p){
		WeddingConnectionPoolManager con = new WeddingConnectionPoolManager();		
		List<Person> allPersons = new ArrayList<Person>();
		
		Person p3 = new Person(p.getId(), p.getFirstName(),
				p.getLastName(), p.getRelationship(), p.getAddress(), p.getPhone(), p.getEmail(),
				p.getComment(), p.getUser_id());
		allPersons = (PersonDBManager.getInstance().getAllPersonsByFLRUId(con.getConnectionFromPool(),
				p.getFirstName(), p.getLastName(), p.getRelationship(),p.getUser_id()));
		
				 if (allPersons.isEmpty()){
					 PersonDBManager.getInstance().CreateNewPerson(con.getConnectionFromPool(), p3);
					}else {							
						for (Person  ps : allPersons ){
							if(        ps.getUser_id() != p3.getUser_id() 
									|| !ps.getFirstName().equals(p.getFirstName())
									|| !ps.getLastName().equals(p3.getLastName())
									|| !ps.getRelationship().equals(p.getRelationship())){									
								PersonDBManager.getInstance().CreateNewPerson(con.getConnectionFromPool(), p3);
								break;
							}else {
								System.out.println("Pesron is alredy exist on youe list : " + ps);
								ps =  null;
								return null;
					}
				}	
		}	
				return allPersons;
	}
	
	public List<Person> searchPerson(String FirstName , String lastName, int user_id){	
		if (FirstName == null && !lastName.isEmpty() || lastName==null && !FirstName.isEmpty()){
			System.err.println("FirstName or lastName are empty : please insert seatch term in the fild" );
			return null;
		}
		
		Person pr = new Person(FirstName,lastName, user.getId());		
		List<Person> persons = new ArrayList<Person>();		
		WeddingConnectionPoolManager con = new WeddingConnectionPoolManager();
		persons = PersonDBManager.getInstance().searchPersonsName(con.getConnectionFromPool(),
				pr.getFirstName(), pr.getLastName(),user_id);	
		if (persons.isEmpty()){
			System.err.println("There is no person matches your search term, please try a gaim .." );
			return null;
		}
		for (Person pr1 : persons){
			if(user_id != pr1.getUser_id()){
				System.err.println("no person found, please try a gaim .." );
				return null;	
			}
		
		}
		return persons;
	}
	
	
	
}


