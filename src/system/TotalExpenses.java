package system;

public class TotalExpenses {
	private int id;
	private Double TotalReceived;
	private Double TotalExpenses;
	private  int user_id;
	
	
	public TotalExpenses(int id, Double totalReceived, Double totalExpenses,
			int user_id) {
		super();
		this.id = id;
		TotalReceived = totalReceived;
		TotalExpenses = totalExpenses;
		this.user_id = user_id;
	}
	
	
	
	public TotalExpenses(int user_id) {
		this.user_id = user_id;
	}
	
	


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Double getTotalReceived() {
		return TotalReceived;
	}


	public void setTotalReceived(Double totalReceived) {
		TotalReceived = totalReceived;
	}


	public Double getTotalExpenses() {
		return TotalExpenses;
	}


	public void setTotalExpenses(Double totalExpenses) {
		TotalExpenses = totalExpenses;
	}


	public int getUser_id() {
		return user_id;
	}


	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}


	@Override
	public String toString() {
		return "TotalExpenses [id=" + id + ", TotalReceived=" + TotalReceived
				+ ", TotalExpenses=" + TotalExpenses + ", user_id=" + user_id
				+ "]";
	}
	
	
	
	
	
}
