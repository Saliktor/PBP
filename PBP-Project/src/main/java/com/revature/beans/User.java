package com.revature.beans;

public class User {
	String firstname;
	String lastname;
	String username;
	String password;
	String email;
	int id;
	boolean isAdmin;
	boolean isBanned;
	boolean isMuted;
	
	
	public User() {
		super();
		this.isAdmin = false;
		this.isBanned = false;
		this.isMuted = false;
	}
	
	
	public User(String firstname, String lastname, String username, String password, String email, int id,
			boolean isAdmin, boolean isBanned, boolean isMuted) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		this.email = email;
		this.id = id;
		this.isAdmin = isAdmin;
		this.isBanned = isBanned;
		this.isMuted = isMuted;
	}


	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public boolean isBanned() {
		return isBanned;
	}
	public void setBanned(boolean isBanned) {
		this.isBanned = isBanned;
	}
	public boolean isMuted() {
		return isMuted;
	}
	public void setMuted(boolean isMuted) {
		this.isMuted = isMuted;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstname == null) ? 0 : firstname.hashCode());
		result = prime * result + id;
		result = prime * result + (isAdmin ? 1231 : 1237);
		result = prime * result + (isBanned ? 1231 : 1237);
		result = prime * result + (isMuted ? 1231 : 1237);
		result = prime * result + ((lastname == null) ? 0 : lastname.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}
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
		if (firstname == null) {
			if (other.firstname != null)
				return false;
		} else if (!firstname.equals(other.firstname))
			return false;
		if (id != other.id)
			return false;
		if (isAdmin != other.isAdmin)
			return false;
		if (isBanned != other.isBanned)
			return false;
		if (isMuted != other.isMuted)
			return false;
		if (lastname == null) {
			if (other.lastname != null)
				return false;
		} else if (!lastname.equals(other.lastname))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "User [firstname=" + firstname + ", lastname=" + lastname + ", username=" + username + ", password="
				+ password + ", email=" + email + ", id=" + id + ", isAdmin=" + isAdmin + ", isBanned=" + isBanned
				+ ", isMuted=" + isMuted + "]";
	}
	
	
	
	
}
