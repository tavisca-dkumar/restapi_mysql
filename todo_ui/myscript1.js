var todo=[];
var counter=1;

var StaticTodo='<input type="text" class="search" list="datalist" id ="search" onkeyup="autoSuggestion(this.value)"><datalist id="datalist"></datalist><input type="button" class="add" id="add" value ="add" onclick="addToDo()"><table id="toDoTable"><tr><th>todoId</th><th>DATE</th><th>TO_DO</th><th>ACTIONS</th></tr></table>';
function addingElementToArray(o)
{
  console.log(o);
  	if(o=="")
  		return false;
    if(todo.length==0){
        todo.push(o);
        return true;
      }
    for(let ele of todo)
    {
      if(o==ele){
        console.log(o);
        return false;
        }
    }
      todo.push(o);
      return true
}
function deletingElementToArray(element)
{
  let index=todo.indexOf(element);
  if(index!=-1)
    todo.splice(index,1);
  
}
function autoSuggestion(value)
{
  //todoAraayLength=todo.length;
  document.getElementById('datalist').innerHTML='';
 // let l=value.length;
  for(var ele of todo)
  {
      if(ele.indexOf(value)>-1)
      {
        var node = document.createElement("option"); 
        var val = document.createTextNode(ele); 
        node.appendChild(val);
        document.getElementById("datalist").appendChild(node);
      }
  }

}

function toDoTable() {
      console.log("working");
      document.getElementById("main").innerHTML=StaticTodo;  
    }
    function createButton(){
      let editButton=document.createElement("input");
      editButton.setAttribute("type","button");
      editButton.setAttribute("id","editButton");
      editButton.setAttribute("class","editButton");
      editButton.setAttribute("value","EDIT");
      editButton.setAttribute("onclick","editTodo(this)");
      return editButton;
    }
    function getDate(){
      var d = new Date();
      let datedata=d.getDate().toString()+"-"+d.getMonth().toString()+"-"+d.getFullYear().toString();
      return datedata;
    }
    function todoRowMaker(date,text,todoId){
        addingElementToArray(text);
        let element = document.getElementById("toDoTable");
        let tr=document.createElement("tr");
        let td0=document.createElement("td");
        let td1=document.createElement("td");
        let td2=document.createElement("td");
        let td3=document.createElement("td");
        let id=document.createTextNode(todoId);
        td0.appendChild(id);
        let datedata=document.createTextNode(date);
        td1.appendChild(datedata);
        let textData=document.createTextNode(text);
        td2.appendChild(textData);
        var editButton = createButton();
        let deleteButton=document.createElement("input");
        deleteButton.setAttribute("type","button");
        deleteButton.setAttribute("id","deleteButton");
        deleteButton.setAttribute("class","deleteButton");
        deleteButton.setAttribute("value","DELETE");
        deleteButton.setAttribute("onclick","deleteTodo(this)");
        td3.appendChild(editButton);
        td3.appendChild(deleteButton);
        tr.appendChild(td0);
        tr.appendChild(td1);
        tr.appendChild(td2);
        tr.appendChild(td3);
        element.appendChild(tr);

  }
  function getRequest(){
    var xhrGet=new XMLHttpRequest();
    xhrGet.open("GET","http://localhost:8080/todo/");
    xhrGet.onload= function(){
      todo=[];
      toDoTable();
      let data=JSON.parse(this.response);
      for(let ele of data){
        console.log(ele.date);
        todoRowMaker(ele.tododate,ele.todo,ele.todoid);
      }
    }
    xhrGet.send();
  }
  function postRequest(date,text,id){
    var xhrPost=new XMLHttpRequest();
    xhrPost.open("POST","http://localhost:8080/todo/");
    xhrPost.onload=function(){
      getRequest();
    } 
    xhrPost.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    xhrPost.send(JSON.stringify({  "tododate":date, "todo":text, "todoid":id } ));
  }
  function addToDo(){
      let text=document.getElementById("search").value;
      let date=getDate();
      if(addingElementToArray(text))
      postRequest(date,text,counter++);
      //StaticTodo=document.getElementById("main").innerHTML;
    }
    function deleteTodo(o)
    {
      var td=o.parentNode;
      var tr=td.parentNode;
      var url ="http://localhost:8080/todo/"+tr.firstChild.textContent;
      var xhrDelete= new XMLHttpRequest();
      xhrDelete.open("DELETE",url,true);
      xhrDelete.onload=function(){
        if(confirm("Do you really want to delete"))
        {
          prevText=tr.firstChild.nextSibling.firstChild.textContent;
          deletingElementToArray(prevText);
          tr.parentNode.removeChild(tr);
          getRequest();
        }

      }
      xhrDelete.send(null);
    }
    function login()
    {
      document.getElementById("main").innerHTML='<div class="login" style ="background-color: #dfe3ee"><form><label><b>Username</b></label><input type="text" class="uname" ><br><label ><b>Password</b></label><input type="password" class="name"><br><button type="submit" class="loginButton">Login</button></form></div>';
    }
    function editTodo(o){
      var td=o.parentNode;
      var tr=td.parentNode;
      prevText=tr.firstChild.nextSibling.nextSibling.firstChild.textContent;
      console.log(prevText +"value");
      var button=document.createElement("input");
      button.setAttribute("type","button");
      button.setAttribute("id","update");
      button.setAttribute("class","editButton");
      button.setAttribute("value","SAVE");
      button.setAttribute("onclick","update(this,prevText)");
      td.replaceChild(button,td.firstChild);
      var text =document.createElement("input");
      text.setAttribute("type","text");
      text.setAttribute("id","updateText");
      text.setAttribute("class","updatetextbox")
      tr.replaceChild(text,tr.firstChild.nextSibling.nextSibling);
    }
    function update(o,prevText){
      console.log(prevText);
      var text = document.getElementById("updateText").value;
      if(!addingElementToArray(text))
        text=prevText;
      var tr=o.parentNode.parentNode;
      let id=tr.firstChild.textContent;
      let date=tr.firstChild.nextSibling.textContent;
      postRequest(date,text,id);
      
    }
