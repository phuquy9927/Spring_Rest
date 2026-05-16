package vn.hoidanit.todo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "todos")
public class Todo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	String username;
	boolean isCompeleted;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isCompeleted() {
		return isCompeleted;
	}

	public void setCompeleted(boolean isCompeleted) {
		this.isCompeleted = isCompeleted;
	}

	@Override
	public String toString() {
		return "Todo [id=" + id + ", username=" + username + ", isCompeleted=" + isCompeleted + "]";
	}

	public Todo(String username, boolean isCompeleted) {

		this.username = username;
		this.isCompeleted = isCompeleted;
	}

	public Todo() {

	}

}
