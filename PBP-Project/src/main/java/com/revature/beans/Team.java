package com.revature.beans;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Team {

	@Id
	int id;
	String teamName;
	@OneToMany(fetch=FetchType.EAGER, mappedBy="team")
	Set<Player> players = new HashSet<>();
}
