package edu.ncsu.csc216.pack_scheduler.user;

/**
 * individual user that can interact with the whole system
 * 
 * @author Xin Rao
 * @author Justin Irene
 * @author Anthony Strapp
 *
 */
public abstract class User {
	/**
	 * String that represents the student's first name
	 */
	private String firstName;
	
	/**
	 * String that represents the student's last name
	 */
	private String lastName;
	
	/**
	 * String that represents the student's id
	 */
	private String id;
	
	/**
	 * String that represents the student's email address
	 */
	private String email;
	
	/**
	 * String that represents an obscured version of the student's password
	 */
	private String password;

	/**
	 * constructor of user
	 * @param firstName first name
	 * @param lastName lastt name
	 * @param id id
	 * @param email email
	 * @param password password
	 */
	public User(String firstName, String lastName, String id, 
				String email, String password){
		setFirstName(firstName);
		setLastName(lastName);
		setId(id);
		setEmail(email);
		setPassword(password);
	}
	
	/**
	 * Returns the first name of the student
	 * 
	 * @return the first name of the student
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name of the student equal to the parameter, firstName
	 * 
	 * @param firstName first name of the student
	 */
	public void setFirstName(String firstName) {
		if(firstName == null || firstName.length() == 0){
			throw new IllegalArgumentException("Invalid first name");
		}
		this.firstName = firstName;
	}

	/**
	 * Returns the last name of the student
	 * 
	 * @return the last name of the student
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name of the student equal to the parameter, lastName
	 * 
	 * @param lastName last name of the student
	 */
	public void setLastName(String lastName) {
		if(lastName == null || lastName.length() == 0){
			throw new IllegalArgumentException("Invalid last name");
		}
		this.lastName = lastName;
	}

	/**
	 * Returns the id of the student
	 * 
	 * @return the id of the student
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id of the student equal to the parameter, id
	 * 
	 * @param id id of the student
	 */
	private void setId(String id) {
		if(id == null || id.length() == 0){
			throw new IllegalArgumentException("Invalid id");
		}
		this.id = id;
	}

	/**
	 * Returns the email address of the student
	 * 
	 * @return the email address of the student
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email address of the student equal to the parameter, email 
	 * 
	 * @param email email address of the student
	 */
	public void setEmail(String email) {
		if(email == null || email.length() == 0){
			throw new IllegalArgumentException("Invalid email");
		}
		if(email.indexOf('@') == -1 || email.indexOf('.') == -1){
			throw new IllegalArgumentException("Invalid email");
		}
		int indexOfDot = 0;
		for(int i = 0; i < email.length(); i++){
			if(email.charAt(i) == '.'){
				indexOfDot = i;
			}
		}
		if(email.indexOf('@') > indexOfDot){
			throw new IllegalArgumentException("Invalid email");
		}
		this.email = email;
	}

	/**
	 * Returns the password of the student
	 * 
	 * @return the password of the student
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password of the student equal to the parameter, hashPW
	 * 
	 * @param hashPW password of the student
	 */

	public void setPassword(String hashPW) {
		if(hashPW == null || hashPW.length() == 0){
			throw new IllegalArgumentException("Invalid password");
		}
		this.password = hashPW;
	}

	/**
	 * Returns integer hash code of Student object
	 * 
	 * @return integer hash code of Student object
	 */
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
	 * Returns whether or not one Student object is equal to another Student object
	 * 
	 * @param obj an object, a Student object in this case
	 * @return whether or not the parameter, obj, is equal to another object
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
