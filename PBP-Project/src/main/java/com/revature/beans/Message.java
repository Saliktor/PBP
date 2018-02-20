package com.revature.beans;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "message")
public class Message {

	@Id
	@SequenceGenerator(name="message_pk",sequenceName="message_seq", allocationSize=1)
	@GeneratedValue(generator="message_pk", strategy=GenerationType.SEQUENCE)
	@JsonIgnore
	int id;
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name = "GAMEID")
	@JsonIgnore
	Game game;
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name = "USERID")
	@JsonIgnore
	UserAccount user;
	Timestamp timePosted;
	String messageContent;
	
	public Message() {
		super();
	
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}


	public UserAccount getUser() {
		return user;
	}

	public void setUser(UserAccount user) {
		this.user = user;
	}

	public Timestamp getTimeMade() {
		return timePosted;
	}

	public void setTimeMade(Timestamp timeMade) {
		this.timePosted = timeMade;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Message [id=");
		builder.append(id);
		builder.append(", timeMade=");
		builder.append(timePosted);
		builder.append(", messageContent=");
		builder.append(messageContent);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((messageContent == null) ? 0 : messageContent.hashCode());
		result = prime * result + ((timePosted == null) ? 0 : timePosted.hashCode());
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
		Message other = (Message) obj;
		if (id != other.id)
			return false;
		if (messageContent == null) {
			if (other.messageContent != null)
				return false;
		} else if (!messageContent.equals(other.messageContent))
			return false;
		if (timePosted == null) {
			if (other.timePosted != null)
				return false;
		} else if (!timePosted.equals(other.timePosted))
			return false;
		return true;
	}

	
}