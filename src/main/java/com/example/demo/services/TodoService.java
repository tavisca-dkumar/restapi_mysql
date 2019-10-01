package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Todo;
import com.example.demo.exception.TodoNotFoundException;
import com.example.demo.repository.TodoRepository;

@Service
public class TodoService extends RuntimeException{
	@Autowired
	TodoRepository repo;
	
	public List<Todo> listOfTodos(){
		return (List<Todo>) repo.findAll();
	}
	
	public Todo findTodo(int id) {
		Optional<Todo> optionalTodo=repo.findById(id);
		if(optionalTodo.isPresent())
			return optionalTodo.get();
		else
			throw new TodoNotFoundException("todo not found");
	}
	
	public void addTodoItem(Todo todoObj) {
		repo.save(todoObj);
	}
	
	public void updateTodoItem(Todo todoObj) {
		int id=todoObj.getTodoid();
		Optional<Todo> optionalTodo=repo.findById(id);
		if(optionalTodo.isPresent()) {
			repo.deleteById(id);
			repo.save(todoObj);
		}
		else
			throw new TodoNotFoundException("todo not found");	
	}

	public void deleteTodoItem(int id) {
		Optional<Todo> optionalTodo=repo.findById(id);
		if(optionalTodo.isPresent()) {
			repo.deleteById(id);
			
		}
		else
			throw new TodoNotFoundException("todo not found to delete");
	}
}
