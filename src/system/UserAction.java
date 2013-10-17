package system;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import dataManagers.ExpensesDBManager;
import dataManagers.PersonDBManager;
import dataManagers.UsersDBManager;
import dataManagers.WeddingConnectionPoolManager;


public class UserAction {
 
	private int id;
	private Expenses expenses;
	private Person person;
	private User user;
	protected WeddingConnectionPoolManager con;
	
	public UserAction (User user){
		this.user = user;
		
	}
	public UserAction(int id, Expenses expenses, Person person, User user) {
		super();
		this.id = id;
		this.expenses = expenses;
		this.person = person;
		this.user = user;
	}
	
	public UserAction(){
	
	}
	
public UserAction(Person person, User user) {
	super();
	this.person = person;
	this.user = user;
}

	
	public User getUser(String email){	
	User user = new User(email);	
	WeddingConnectionPoolManager con = new WeddingConnectionPoolManager();
	try {
		user = UsersDBManager.getInstance().GetUser(con.getConnectionFromPool(), user.getEmail());
		System.out.println("the user from userAction getUser() method--(user)--->>> " + user);
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
			
			System.out.println(" from (user UpdateAuser ) UPDATED ...u..." + u);
		}else {
			System.out.println(" from (user UpdateAuser )no user found ......");
		}
	  }
	
	/**this method is similar to "CreatePersonAndCreateReceivedPayment". it allowed just to 
	 * create person  **/
	public void CreatePerson(Person p){
		WeddingConnectionPoolManager con = new WeddingConnectionPoolManager();
		Person pr = new Person(p.getId(),p.getFirstName(),p.getLastName(),p.getRelationship(),p.getAddress(),p.getPhone(),p.getEmail(),p.getComment(),user.getId());
		try {
			PersonDBManager.getInstance().CreateNewPerson(con.getConnectionFromPool(), pr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("person    : " + pr);

	}
	
	public void updatePerson(Person p){
		WeddingConnectionPoolManager con = new WeddingConnectionPoolManager();
		p  = new Person(p.getId(),p.getFirstName(),p.getLastName(),p.getRelationship(),p.getAddress(),p.getPhone(),p.getEmail(),p.getComment(),user.getId());
		PersonDBManager.getInstance().UpdateAPerson(con.getConnectionFromPool(), p);
		System.out.println("from uac updatePerson --- > : " +p);
		
	}
	

	public void CreatePersonAndCreateReceivedPayment(Expenses exp, Person p,TotalExpenses toxp){
		WeddingConnectionPoolManager con = new WeddingConnectionPoolManager();
		/**this condition should validate if the person already exist in the person table. if exist , do not insert it as a new person
		 * and avoid duplicate person ***/
		if(PersonDBManager.getInstance().GetPerson(con.getConnectionFromPool(), p.getFirstName(), p.getLastName(), p.getRelationship()) == null){
		Person pr = new Person(p.getId(),p.getFirstName(),p.getLastName(),p.getRelationship(),p.getAddress(),p.getPhone(),p.getEmail(),p.getComment(),user.getId());	
		PersonDBManager.getInstance().CreateNewPerson(con.getConnectionFromPool(), pr);
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
	pr = new Person(pr.getId(),pr.getFirstName(),pr.getLastName(),pr.getRelationship(),pr.getAddress(),pr.getPhone(),pr.getEmail(),pr.getComment(),user.getId());

	exp = new Expenses(exp.getId(), user.getId(),pr.getFirstName(),pr.getLastName() , pr.getId(),
			exp.getReceived_payment(), exp.getPayback_payment(), exp.getPayment_type(),
			exp.getEventType(),exp.getPayback_payment_eventType(), exp.getEventAddress(), exp.getComment(), sqlDate);
	ExpensesDBManager.getInstance().UpdateAReceivedPayment(con.getConnectionFromPool(), exp);
	PersonDBManager.getInstance().UpdateAPerson(con.getConnectionFromPool(), pr);
	System.out.println("userAction (updateReceivedPayment -  method)- > the updated Expenses param = " + exp);
}
	

	public List<Expenses> getAllReceivedPayment(int user_id){
		WeddingConnectionPoolManager con = new WeddingConnectionPoolManager();
		List<Expenses> allExpenses = new ArrayList<Expenses>();
		allExpenses = ExpensesDBManager.getInstance().getAllReceivedPayment(con.getConnectionFromPool(), user_id);
		
		for (Expenses exp : allExpenses){
			System.out.println(exp);
		}
		return allExpenses;
	}
	
	
}