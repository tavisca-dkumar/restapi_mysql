package com.example.demo.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;

import java.awt.PageAttributes.MediaType;
import java.util.Arrays;
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.hamcrest.CoreMatchers.equalTo;
//
//import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.entity.Todo;
import com.example.demo.repository.TodoRepository;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
//import com.example.demo.entity.Todo;
import org.junit.Assert;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class todoControllerIntegrationTest {
	
	@LocalServerPort
	private int port;
	@Autowired
	private TestRestTemplate rest;
	@Autowired
	private TodoRepository repo;
	@org.junit.Before
	public void data()
	{
		repo.save(new Todo(1,"10-09-2019","do bath"));
	}
	
	@Test
    public void getAllTodoList() throws Exception {
        ResponseEntity<List> response =
                rest.getForEntity("http://localhost:" + port + "/todo", List.class);
        Assert.assertEquals(HttpStatus.OK.value(),response.getStatusCodeValue());
       
    }
	
	@Test
    public void getTodoById() throws Exception {
        ResponseEntity<Todo> response =
                rest.getForEntity("http://localhost:" + port + "/todo/1", Todo.class);
        Assert.assertEquals(HttpStatus.OK.value(),response.getStatusCodeValue());
        Todo actual = response.getBody();
        System.out.println(actual.getTodo());
        
       
    }
	@Test
    public void getTodoByUnknownId() throws Exception {
        ResponseEntity<Todo> response =
                rest.getForEntity("http://localhost:" + port + "/todo/4", Todo.class);
        Assert.assertEquals(HttpStatus.NOT_FOUND.value(),response.getStatusCodeValue());
        Todo actual = response.getBody();
        System.out.println(actual.getTodo());
        
       
    }
	@Test
	public void addTodoItem() throws Exception {
		Todo item=new Todo(2,"10-09-2019","Do brush");
		HttpHeaders headers=new HttpHeaders();
		//headers.setAccept(Arrays.asList(org.springframework.http.MediaType.APPLICATION_JSON));
		HttpEntity<Todo> request=new HttpEntity<Todo>(item);
		Todo actual=rest.postForObject("http://localhost:" + port + "/todo",request,Todo.class);	
		System.out.println(actual);
		assertEquals("Do brush",actual.getTodo());
	}
	
	@Test
	public void updateTodoItem() throws Exception{
		Todo item=new Todo(1,"10-09-2019","Do brush");
		HttpHeaders headers=new HttpHeaders();
		HttpEntity<Todo> request=new HttpEntity<Todo>(item);
		ResponseEntity <Todo> response=rest.exchange("http://localhost:" + port + "/todo", HttpMethod.PUT,request,Todo.class );
		Assert.assertEquals(HttpStatus.CREATED.value(),response.getStatusCodeValue());
	}
	
	@Test
	public void deleteTodoItem() throws Exception{
		
		HttpHeaders headers=new HttpHeaders();
		HttpEntity request=new HttpEntity(headers);
		ResponseEntity <Todo> response=rest.exchange("http://localhost:" + port + "/todo/1", HttpMethod.DELETE,request,Todo.class );
		Assert.assertEquals(HttpStatus.OK.value(),response.getStatusCodeValue());
	}

	@Test
	public void deleteTodoUnknownItem() throws Exception{
		
		HttpHeaders headers=new HttpHeaders();
		HttpEntity request=new HttpEntity(headers);
		ResponseEntity <Todo> response=rest.exchange("http://localhost:" + port + "/todo/3", HttpMethod.DELETE,request,Todo.class );
		Assert.assertEquals(HttpStatus.NOT_FOUND.value(),response.getStatusCodeValue());
	}

}
