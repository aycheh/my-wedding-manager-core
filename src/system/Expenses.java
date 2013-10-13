package system;

import java.sql.Date;

public class Expenses {
	private int id;
	private int user_id;
	private String person_firstName;
	private String person_lastName;
	private int person_id;
	private double received_payment;
	private double payback_payment;
	private String payment_type;
	private String eventType;
	private String payback_payment_eventType;
	private String eventAddress;
	private String comment;
	private Date date;
	
	
	public Expenses(int id, int user_id, String person_firstName,String person_lastName, int person_id,
			double received_payment, double payback_payment,
			String payment_type, String eventType,String payback_payment_eventType, String eventAddress,
			String comment, Date date) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.person_lastName = person_lastName;
		this.person_lastName = person_lastName;
		this.person_id = person_id;
		this.received_payment = received_payment;
		this.payback_payment = payback_payment;
		this.payment_type = payment_type;
		this.eventType = eventType;
		this.payback_payment_eventType = payback_payment_eventType;
		this.eventAddress = eventAddress;
		this.comment = comment;
		this.date = date;
		
		
		
	}
	
	public String getPerson_firstName() {
		return person_firstName;
	}

	public void setPerson_firstName(String person_firstName) {
		this.person_firstName = person_firstName;
	}

	public String getPerson_lastName() {
		return person_lastName;
	}

	public void setPerson_lastName(String person_lastName) {
		this.person_lastName = person_lastName;
	}

	public Expenses(){

	}
	



	public Expenses(int id) {
		super();
		this.id = id;
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


	public int getPerson_id() {
		return person_id;
	}


	public void setPerson_id(int person_id) {
		this.person_id = person_id;
	}


	public double getReceived_payment() {
		return received_payment;
	}


	public void setReceived_payment(double received_payment) {
		this.received_payment = received_payment;
	}


	public double getPayback_payment() {
		return payback_payment;
	}


	public void setPayback_payment(double payback_payment) {
		this.payback_payment = payback_payment;
	}


	public String getPayment_type() {
		return payment_type;
	}


	public void setPayment_type(String payment_type) {
		this.payment_type = payment_type;
	}


	public String getEventType() {
		return eventType;
	}


	public void setEventType(String eventType) {
		this.eventType = eventType;
	}


	public String getEventAddress() {
		return eventAddress;
	}


	public void setEventAddress(String eventAddress) {
		this.eventAddress = eventAddress;
	}


	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}

	
	

	public String getPayback_payment_eventType() {
		return payback_payment_eventType;
	}

	public void setPayback_payment_eventType(String payback_payment_eventType) {
		this.payback_payment_eventType = payback_payment_eventType;
	}

	@Override
	public String toString() {
		return "Expenses [id=" + id + ", user_id=" + user_id
				+ ", person_firstName=" + person_firstName
				+ ", person_lastName=" + person_lastName + ", person_id="
				+ person_id + ", received_payment=" + received_payment
				+ ", payback_payment=" + payback_payment + ", payment_type="
				+ payment_type + ", eventType=" + eventType
				+ ", payback_payment_eventType=" + payback_payment_eventType
				+ ", eventAddress=" + eventAddress + ", comment=" + comment
				+ ", date=" + date + "]";
	}

	

	
	

	
	
	
	

	
	
	
	
	
}
