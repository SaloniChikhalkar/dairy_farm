const milkAPI="http://localhost:8080/api/milk";
const animalAPI="http://localhost:8080/api/animals";

let editId=null;

window.onload=function(){

loadAnimals();

loadMilk();


    // Set today's date automatically
    document.getElementById("collectionDate").value = new Date().toISOString().split("T")[0];

    loadAnimals();
    loadMilk();


};

function loadAnimals(){

fetch(animalAPI)

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

document.getElementById("morningQty").addEventListener("input",calculateTotal);
document.getElementById("eveningQty").addEventListener("input",calculateTotal);

function calculateTotal(){

let m=parseFloat(document.getElementById("morningQty").value)||0;

let e=parseFloat(document.getElementById("eveningQty").value)||0;

document.getElementById("totalQty").value=m+e;

}

function saveMilk(){

const milk={

animal:{
animalId:document.getElementById("animal").value
},

collectionDate:document.getElementById("collectionDate").value,

morningQty:document.getElementById("morningQty").value,

eveningQty:document.getElementById("eveningQty").value

};

let method="POST";
let url=milkAPI;

if(editId!=null){

method="PUT";

url=milkAPI+"/"+editId;

}

fetch(url,{

method:method,

headers:{
"Content-Type":"application/json"
},

body:JSON.stringify(milk)

})

.then(res=>res.json())

.then(data=>{

alert("Saved Successfully");

clearForm();

loadMilk();

});

}

function loadMilk(){

fetch(milkAPI)

.then(res=>res.json())

.then(data=>{

let table=document.getElementById("milkTable");

table.innerHTML="";

data.forEach(milk=>{

table.innerHTML+=`

<tr>

<td>${milk.milkId}</td>

<td>${milk.animal.tagNumber}</td>

<td>${milk.collectionDate}</td>

<td>${milk.morningQty}</td>

<td>${milk.eveningQty}</td>

<td>${milk.totalQty}</td>

<td>

<button class="editBtn" onclick="editMilk(${milk.milkId})">Edit</button>

<button class="deleteBtn" onclick="deleteMilk(${milk.milkId})">Delete</button>

</td>

</tr>

`;

});

});

}

function editMilk(id){

fetch(milkAPI+"/"+id)

.then(res=>res.json())

.then(milk=>{

editId=milk.milkId;

document.getElementById("animal").value=milk.animal.animalId;

document.getElementById("collectionDate").value=milk.collectionDate;

document.getElementById("morningQty").value=milk.morningQty;

document.getElementById("eveningQty").value=milk.eveningQty;

document.getElementById("totalQty").value=milk.totalQty;

document.getElementById("saveBtn").innerHTML="Update";

});

}

function deleteMilk(id){

if(confirm("Delete Record?")){

fetch(milkAPI+"/"+id,{

method:"DELETE"

})

.then(()=>{

alert("Deleted");

loadMilk();

});

}

}

function clearForm(){

    editId = null;

    document.getElementById("milkForm").reset();

    // Set today's date again
    document.getElementById("collectionDate").value = new Date().toISOString().split("T")[0];

    document.getElementById("totalQty").value = "";

    document.getElementById("saveBtn").innerHTML = "Save";

}