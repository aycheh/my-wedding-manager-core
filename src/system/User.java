package system;

public class User {
	private int id;
	private String firstName;
	private String lastName;
	private String password;
	private String email;
	private int image;	
	
	
	
	public User(int id, String firstName, String lastName, String password,
			String email) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
	}

	public User(String email){
		this.email = email;
	}
	
	public User(String firstName, String email){
		this.firstName = firstName;
		this.email = email;
	}
	
	
	public User(int id ,int photo){
		this.image = photo;
		this.id = id;
	}
	
	public int getPhoto(){
		return image;
	}
	
	public void setPhoto(int photo){
		this.image = photo;
	
	
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



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName="
				+ lastName + ", password=" + password + ", email=" + email
				+ ", photo=" + image + "]";
	}



//	@Override
//	public String toString() {
//		return "Users [id=" + id + ", firstName=" + firstName + ", lastName="
//				+ lastName + ", password=" + password + ", email=" + email
//				+ "]";
//	}
	
	
	
}
