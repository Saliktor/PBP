package com.revature.beans;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.stereotype.Component;


@Component
@Entity
@Table(name="UserAccount")
@Inheritance(strategy=InheritanceType.JOINED)
public class UserAccount {
	@Id
	@SequenceGenerator(name="user_pk",sequenceName="useraccount_seq", allocationSize=1)
	@GeneratedValue(generator="user_pk", strategy=GenerationType.SEQUENCE)
	int id;
	
	@JsonIgnore
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	Set<Player> players;
	
	@Column(name = "username", nullable = false, unique = true)
	String username;
	String password;
	String email;
	int isAdmin; 		// 0 for no, 1 for yes
	int isBanned;		// 0 for no, 1 for yes
	int isMuted;		// 0 for no, 1 for yes
	
	public UserAccount() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}

	public int getIsBanned() {
		return isBanned;
	}

	public void setIsBanned(int isBanned) {
		this.isBanned = isBanned;
	}

	public int getIsMuted() {
		return isMuted;
	}

	public void setIsMuted(int isMuted) {
		this.isMuted = isMuted;
	}
	
	public Set<Player> getPlayers() {
		return players;
	}

	public void setPlayers(Set<Player> players) {
		this.players = players;
	}
	
	public void addPlayer(Player player) {
		this.players.add(player);
	}

	@Override
	public String toString() {
		return "UserAccount [id=" + id + ", players=" + players + ", username=" + username + ", password=" + password
				+ ", email=" + email + ", isAdmin=" + isAdmin + ", isBanned=" + isBanned + ", isMuted=" + isMuted + "]";
	}

	
}