//package com.example.demo.web;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import java.util.ArrayList;
//
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.skyscreamer.jsonassert.JSONAssert;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.mock.web.MockHttpServletResponse;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.RequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import com.example.demo.entity.Todo;
//import com.example.demo.services.TodoService;
//
//@RunWith(SpringRunner.class)
//@WebMvcTest(TodoController.class)
//public class TodoControllerTest {
//	
//	@Autowired
//	private MockMvc mockMvc;
//	
//	@MockBean
//	private TodoService service;
//	
//	@Test
//	public void getAllTodoList() throws Exception
//	{
//		ArrayList<Todo> mockTodoList=new ArrayList<Todo>(){{
//            add(new Todo(1,"10-09-2019","do brush"));
//            add(new Todo(2,"10-09-2019","do bath") );
//        }};
//        Mockito.when(service.listOfTodos()).thenReturn(mockTodoList);
//        RequestBuilder requestBuilder= MockMvcRequestBuilders.get("/todo").accept(MediaType.APPLICATION_JSON);
//        MvcResult result=mockMvc.perform(requestBuilder).andReturn();
//        MockHttpServletResponse res=result.getResponse();
//        JSONAssert.assertEquals("[{'todo':'do brush','tododate':'10-09-2019','todoid':1},{'todo':'do bath','tododate':'10-09-2019','todoid':2}]",result.getResponse().getContentAsString(),false);
//        Assert.assertEquals(HttpStatus.OK.value(),res.getStatus());
//	}
//	
//	@Test
//	public void getTodoById() throws Exception {
//		Todo mockTodo=new Todo(1,"10-09-2019","do brush");
//		Mockito.when(service.findTodo(1)).thenReturn(mockTodo);
//		RequestBuilder requestBuilder= MockMvcRequestBuilders.get("/todo/1").accept(MediaType.APPLICATION_JSON);
//		MvcResult result=mockMvc.perform(requestBuilder).andReturn();
//        MockHttpServletResponse res=result.getResponse();
//        JSONAssert.assertEquals("{'todo':'do brush','tododate':'10-09-2019','todoid':1}",result.getResponse().getContentAsString(),false);
//        Assert.assertEquals(HttpStatus.OK.value(),res.getStatus());
//	}
//	
//	@Test
//    public void canAddTodoItem()throws Exception
//    {
//        ObjectMapper objectMapper=new ObjectMapper();
//        Todo mockTodo=new Todo(1,"10-09-2019","do brush");
//        RequestBuilder requestBuilder= MockMvcRequestBuilders.post("/todo").accept(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(mockTodo)).contentType(MediaType.APPLICATION_JSON);
//        MvcResult result=mockMvc.perform(requestBuilder).andReturn();
//        MockHttpServletResponse res=result.getResponse();
//        Assert.assertEquals(HttpStatus.CREATED.value(),res.getStatus());
//    }
//
//	@Test
//    public void canUpdateTodoItem()throws Exception
//    {
//        ObjectMapper objectMapper=new ObjectMapper();
//        Todo mockTodo=new Todo(1,"10-09-2019","do brush");
//        RequestBuilder requestBuilder= MockMvcRequestBuilders.post("/todo").accept(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(mockTodo)).contentType(MediaType.APPLICATION_JSON);
//        MvcResult result=mockMvc.perform(requestBuilder).andReturn();
//        MockHttpServletResponse res=result.getResponse();
//        Assert.assertEquals(HttpStatus.CREATED.value(),res.getStatus());
//    }
//}
