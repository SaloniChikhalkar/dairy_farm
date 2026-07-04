const api="http://localhost:8080/api/employees";

let editId=null;

window.onload=function(){

loadEmployees();

document.getElementById("joiningDate").value=
new Date().toISOString().split("T")[0];

};

function saveEmployee(){

const employee={

employeeName:document.getElementById("employeeName").value,

gender:document.getElementById("gender").value,

mobile:document.getElementById("mobile").value,

address:document.getElementById("address").value,

joiningDate:document.getElementById("joiningDate").value,

salary:document.getElementById("salary").value,

role:document.getElementById("role").value,

status:document.getElementById("status").value

};

let method="POST";
let url=api;

if(editId!=null){

method="PUT";
url=api+"/"+editId;

}

fetch(url,{

method:method,

headers:{

"Content-Type":"application/json"

},

body:JSON.stringify(employee)

})

.then(res=>res.json())

.then(data=>{

alert("Saved Successfully");

clearForm();

loadEmployees();

});

}

function loadEmployees(){

fetch(api)

.then(res=>res.json())

.then(data=>{

let table=document.getElementById("employeeTable");

table.innerHTML="";

data.forEach(emp=>{

table.innerHTML+=`

<tr>

<td>${emp.employeeId}</td>

<td>${emp.employeeName}</td>

<td>${emp.gender}</td>

<td>${emp.mobile}</td>

<td>${emp.role}</td>

<td>${emp.salary}</td>

<td>${emp.status}</td>

<td>

<button class="editBtn" onclick="editEmployee(${emp.employeeId})">Edit</button>

<button class="deleteBtn" onclick="deleteEmployee(${emp.employeeId})">Delete</button>

</td>

</tr>

`;

});

});

}

function editEmployee(id){

fetch(api+"/"+id)

.then(res=>res.json())

.then(emp=>{

editId=id;

document.getElementById("employeeName").value=emp.employeeName;
document.getElementById("gender").value=emp.gender;
document.getElementById("mobile").value=emp.mobile;
document.getElementById("address").value=emp.address;
document.getElementById("joiningDate").value=emp.joiningDate;
document.getElementById("salary").value=emp.salary;
document.getElementById("role").value=emp.role;
document.getElementById("status").value=emp.status;

document.getElementById("saveBtn").innerHTML="Update";

});

}

function deleteEmployee(id){

if(confirm("Delete Employee?")){

fetch(api+"/"+id,{

method:"DELETE"

})

.then(()=>{

loadEmployees();

});

}

}

function clearForm(){

editId=null;

document.getElementById("employeeForm").reset();

document.getElementById("joiningDate").value=
new Date().toISOString().split("T")[0];

document.getElementById("saveBtn").innerHTML="Save";

}