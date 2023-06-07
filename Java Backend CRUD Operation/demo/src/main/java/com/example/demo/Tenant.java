package com.example.demo;

// Creating an entity Employee
public class Tenant {

	public Tenant() {}

	// Parameterized Constructor
	// to assign the values
	// to the properties of
	// the entity
	public Tenant(
		Integer id, String firstName,
		String lastName, String email,
		Integer mobNumber, Integer govermentId,
		String address, String purpose)
	{

		super();

		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.govermentId = govermentId;
		this.mobNumber = mobNumber;
		this.address = address;
		this.purpose = purpose;
		
		

		 
	}

	 private Integer id;
	 private Integer govermentId;
	 private Integer mobNumber;
	 private String firstName;
	 private String lastName;
	 private String email;
	 private String purpose;
	 private String address;
	 
	 

	// Overriding the toString method
	// to find all the values
	@Override
 public String toString()
	
 {
		return "Tenant [id="
			+ id + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
			+ email + ", mobNumber= " + mobNumber + ", govermentId= " 
			+ govermentId + ", address= " + address + ", purpose= " + purpose + "]";

		 
	}

	// Getters and setters of
	// the properties
	public Integer getId()
	{

		return id;
	}

	public String getFirstName()
	{

		return firstName;
	}


	public String getLastName()
	{

		return lastName;
	}


	public String getEmail()
	{

		return email;
	}
	public Integer getMobNumber()
	{

		return mobNumber;
	}
	public Integer getGovermentId()
	{

		return govermentId;
	}
	public String getAddress()
	{

		return address;
	}
	public String getpurpose()
	{

		return purpose;
	}
	


}

