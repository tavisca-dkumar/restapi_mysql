package com.example.demo.entity;

import javax.persistence.*;

@Table(name="todolist")
@Entity
public class Todo {
	@Id
	@Column(name="todo_id")
	private int todoid; 
	@Column(name="todo_date")
	private String tododate;
	@Column(name="todo")
	private String todo;
	public Todo() {}
	public Todo(int todoid, String tododate, String todo) {
	
		this.todoid = todoid;
		this.tododate = tododate;
		this.todo = todo;
	}
	public int getTodoid() {
		return todoid;
	}
	public void setTodoid(int todoid) {
		this.todoid = todoid;
	}
	public String getTododate() {
		return tododate;
	}
	public void setTododate(String tododate) {
		this.tododate = tododate;
	}
	public String getTodo() {
		return todo;
	}
	public void setTodo(String todo) {
		this.todo = todo;
	}

	
	

}
