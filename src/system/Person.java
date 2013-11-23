package system;

public class Person {
 
	private int id;
	private String firstName;
	private String lastName;
	private String relationship;
	private String address;
	private String phone;
	private String email;
	private String comment;
	private int user_id;
	private Person person;
	
	
	public Person(int id, String firstName, String lastName,
			String relationship, String address, String phone, String email,
			String comment, int user_id) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.relationship = relationship;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.comment = comment;
		this.user_id = user_id;
	}

	public Person(){
		
	}
	
public Person(Person perso){
		this.person = person;
	}
	
	
	public Person(int id){
		this.id=id;
	}
	public Person(String firstName ,String lastName ,int user_id){
		this.firstName = firstName;
		this.lastName=lastName;
		this.user_id = user_id;
	
	}
	
public Person(String firstName ,String lastName, String relationship){
		this.firstName = firstName;
		this.lastName=lastName;
		this.relationship = relationship;
	}
public Person(String firstName ,String lastName, String relationship, int id){
	this.id = id;
	this.firstName = firstName;
	this.lastName=lastName;
}


	public Person getPerson() {
	return person;
}

public void setPerson(Person person) {
	this.person = person;
}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getRelationship() {
		return relationship;
	}


	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}


	public int getUser_id() {
		return user_id;
	}


	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", firstName=" + firstName + ", lastName="
				+ lastName + ", relationship=" + relationship + ", address="
				+ address + ", phone=" + phone + ", email=" + email
				+ ", comment=" + comment + ", user_id=" + user_id + ", person="
				+ person + "]";
	}


//	@Override
//	public String toString() {
//		return "Person [id=" + id + ", firstName=" + firstName + ", lastName="
//				+ lastName + ", relationship=" + relationship + ", address="
//				+ address + ", phone=" + phone + ", email=" + email
//				+ ", comment=" + comment + ", user_id=" + user_id + "]";
//	}
	

	
	
	
	
}