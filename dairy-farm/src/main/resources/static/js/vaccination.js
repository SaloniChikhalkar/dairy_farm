const api="http://localhost:8080/api/vaccinations";
const animalApi="http://localhost:8080/api/animals";

let editId=null;

window.onload=function(){

document.getElementById("vaccinationDate").value=
new Date().toISOString().split("T")[0];

loadAnimals();
loadVaccinations();

};

function loadAnimals(){

fetch(animalApi)

.then(res=>res.json())

.then(data=>{

let select=document.getElementById("animal");

select.innerHTML="<option value=''>Select Animal</option>";

data.forEach(animal=>{

select.innerHTML+=`
<option value="${animal.animalId}">
${animal.tagNumber} - ${animal.breed}
</option>
`;

});

});

}

function saveVaccination(){

const vaccination={

animal:{
animalId:document.getElementById("animal").value
},

vaccineName:document.getElementById("vaccineName").value,

vaccinationDate:document.getElementById("vaccinationDate").value,

nextDueDate:document.getElementById("nextDueDate").value,

veterinarian:document.getElementById("veterinarian").value,

remarks:document.getElementById("remarks").value

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

body:JSON.stringify(vaccination)

})

.then(res=>res.json())

.then(data=>{

alert("Vaccination Saved Successfully");

clearForm();

loadVaccinations();

});

}

function loadVaccinations(){

fetch(api)

.then(res=>res.json())

.then(data=>{

let table=document.getElementById("vaccinationTable");

table.innerHTML="";

data.forEach(v=>{

table.innerHTML+=`

<tr>

<td>${v.vaccinationId}</td>

<td>${v.animal.tagNumber}</td>

<td>${v.vaccineName}</td>

<td>${v.vaccinationDate}</td>

<td>${v.nextDueDate}</td>

<td>${v.veterinarian}</td>

<td>${v.remarks}</td>

<td>

<button class="editBtn" onclick="editVaccination(${v.vaccinationId})">Edit</button>

<button class="deleteBtn" onclick="deleteVaccination(${v.vaccinationId})">Delete</button>

</td>

</tr>

`;

});

});

}

function editVaccination(id){

fetch(api+"/"+id)

.then(res=>res.json())

.then(v=>{

editId=id;

document.getElementById("animal").value=v.animal.animalId;
document.getElementById("vaccineName").value=v.vaccineName;
document.getElementById("vaccinationDate").value=v.vaccinationDate;
document.getElementById("nextDueDate").value=v.nextDueDate;
document.getElementById("veterinarian").value=v.veterinarian;
document.getElementById("remarks").value=v.remarks;

document.getElementById("saveBtn").innerHTML="Update";

});

}

function deleteVaccination(id){

if(confirm("Delete Vaccination Record?")){

fetch(api+"/"+id,{
method:"DELETE"
})

.then(()=>{

alert("Deleted Successfully");

loadVaccinations();

});

}

}

function clearForm(){

editId=null;

document.getElementById("vaccinationForm").reset();

document.getElementById("vaccinationDate").value=
new Date().toISOString().split("T")[0];

document.getElementById("saveBtn").innerHTML="Save";

}