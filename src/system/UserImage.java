package system;

public class UserImage {
	private int id;
	private int user_id;
	private String first_name;
	private String last_name;
	private int photo;
	
	public UserImage(int id, int user_id, String first_name, String last_name,
			int photo) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.photo = photo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public int getPhoto() {
		return photo;
	}

	public void setPhoto(int photo) {
		this.photo = photo;
	}

	@Override
	public String toString() {
		return "UserImage [id=" + id + ", user_id=" + user_id + ", first_name="
				+ first_name + ", last_name=" + last_name + ", photo=" + photo
				+ "]";
	}
	
	

}
