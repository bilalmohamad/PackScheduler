package edu.ncsu.csc216.pack_scheduler.user;

/**
 * Creates a user for registration
 * @author seth,kyle,qihao
 *
 */
public abstract class User { 
	/** Student first name */
	private String firstName;
	/** Student last name */
	private String lastName;
	/** student id */
	private String id;
	/** student email address */
	private String email;
	/** student hashed password */
	private String password;
	
	/**
	 * Constructor for the user
	 * @param firstName first name
	 * @param lastName last name
	 * @param id unity id
	 * @param email email address
	 * @param password password
	 */
	public User(String firstName, String lastName, String id, String email, String password) {
		setFirstName(firstName);
		setLastName(lastName);
		setId(id);
		setEmail(email);
		setPassword(password);
	}

	/**
	 * get the student First name
	 * 
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * get the student last name
	 * 
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * get the student id
	 * 
	 * @return the student id
	 */
	public String getId() {
		return id;
	}

	/**
	 * get the student email
	 * 
	 * @return the email
	 */
	public String getEmail() { 
		return email;
	}

	/**
	 * set the student new email
	 * 
	 * @param email
	 *            the email to set
	 * @throws IllegalArgumentException
	 *             if email is null, do not have a "."or"@" and the "." in the
	 *             string is earlier than the "@" character
	 */
	public void setEmail(String email) {
		int checkCh = 0;
		int checkPoint = 0;
		int chPosition = 0;
		int pointPosition = 0;
		if (email == null || email.equals("")) {
			throw new IllegalArgumentException("Invalid email");
		}
		for (int i = 0; i < email.length(); i++) {
			if (email.charAt(i) == '@') {
				checkCh++;
				chPosition = i;
			}
			if (email.charAt(i) == '.') {
				checkPoint++;
				pointPosition = i;
			}
		}
		if (checkCh != 1) {
			throw new IllegalArgumentException("Invalid email");
		}
		if (checkPoint == 0) {
			throw new IllegalArgumentException("Invalid email");
		}
		if (chPosition > pointPosition) {
			throw new IllegalArgumentException("Invalid email");
		}
		this.email = email;
	}

	/**
	 * get the student password
	 * 
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * set the student new password
	 * 
	 * @param password
	 *            the password to set
	 * @throws IllegalArgumentException
	 *             if password is null
	 */
	public void setPassword(String password) {
		if (password == null || password.equals("")) {
			throw new IllegalArgumentException("Invalid password");
		}
		this.password = password;
	}

	/**
	 * set new student first name
	 * 
	 * @param firstName
	 *            the firstName to set
	 * @throws IllegalArgumentException
	 *             if first name is null
	 */
	public void setFirstName(String firstName) {
		if (firstName == null || firstName.equals("")) {
			throw new IllegalArgumentException("Invalid first name");
		}
		this.firstName = firstName;
	}

	/**
	 * set student new last name
	 * 
	 * @param lastName
	 *            the lastName to set
	 * @throws IllegalArgumentException
	 *             if last name is null
	 */
	public void setLastName(String lastName) {
		if (lastName == null || lastName.equals("")) {
			throw new IllegalArgumentException("Invalid last name");
		}
		this.lastName = lastName;
	}

	/**
	 * * set student new id
	 * 
	 * @param id
	 *            the id to set
	 * @throws IllegalArgumentException
	 *             if id is null
	 */
	protected void setId(String id) {
		if (id == null || id.equals("")) {
			throw new IllegalArgumentException("Invalid id");
		}
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}

	/**
	 * Determines if two objects are equal
	 * 
	 * @param obj	the object used for comparison
	 * 
	 * @return	true	if the objects are the same
	 * 			false	if the objects are different
	 */
	@Override
	public boolean equals(Object obj) {  
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	} 

}