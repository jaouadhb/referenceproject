package fr.reference.it.referenceproject.security.jwt.model;


import java.io.Serializable;
import java.util.Date;


public class JwtTokenResponse implements Serializable {

	private static final long serialVersionUID = 8317676219297719109L;

	private  String token;
	private  String username;
	private  String lastName;
	private  String firstName;
	private  String email;
	private  Date dateNaissance;
	private String role;

	public JwtTokenResponse(String token){
		this.token = token;
	}

	public JwtTokenResponse(String token, String username, String lastName, String firstName, String email, Date dateNaissance, String role) {
		this.token = token;
		this.username = username;
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.dateNaissance = dateNaissance;
		this.role = role;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
