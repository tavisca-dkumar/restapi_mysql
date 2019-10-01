package com.example.demo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entity.Todo;
import com.example.demo.exception.TodoNotFoundException;
import com.example.demo.services.TodoService;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class TodoController {
	
	@Autowired
	TodoService service;
	
	@GetMapping("/todo")
	public ResponseEntity<List<Todo>> getAllTodos(){
		List<Todo> todos=service.listOfTodos();
		return new ResponseEntity<List<Todo>>(todos,HttpStatus.OK);
	}
	@GetMapping("/todo/{id}")
	public ResponseEntity<Todo> getTodoById(@PathVariable("id") int id){
		try {
			return new ResponseEntity<Todo>(service.findTodo(id),HttpStatus.OK);
			}catch(TodoNotFoundException exception) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND,"todo not found");
			}
	}
	
	@PostMapping("/todo")
	public ResponseEntity<?> addTodo(@RequestBody Todo todoObj){
		service.addTodoItem(todoObj);
		return new ResponseEntity<Todo>(todoObj,HttpStatus.CREATED);
	}
	
	@PutMapping("/todo")
	public ResponseEntity<?> updateTodo(@RequestBody Todo todoObj){
		try {
			service.updateTodoItem(todoObj);
			return new ResponseEntity<>(HttpStatus.CREATED);
			}catch(TodoNotFoundException exception) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND,"todo not found to update");
			}
	}
	
	@DeleteMapping("/todo/{id}")
	public ResponseEntity<?> deleteTodo(@PathVariable("id") int id){
		try {
			service.deleteTodoItem(id);
			return new ResponseEntity<>(HttpStatus.OK);
			}catch(TodoNotFoundException exception) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND,"todo not found to delete");
			}
	}
	
	
	
	
	

}
