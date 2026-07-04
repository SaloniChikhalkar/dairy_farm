const API_URL = "http://localhost:8080/api/animals";

let editId = null;

// Load animals when page opens
window.onload = function () {
    loadAnimals();
};

// ================= LOAD ALL ANIMALS =================
function loadAnimals() {

    fetch(API_URL)
        .then(res => res.json())
        .then(data => {

            let table = document.getElementById("animalTable");
            table.innerHTML = "";

            data.forEach(animal => {

                table.innerHTML += `
                <tr>
                    <td>${animal.animalId}</td>
                    <td>${animal.tagNumber}</td>
                    <td>${animal.animalType}</td>
                    <td>${animal.breed}</td>
                    <td>${animal.gender}</td>
                    <td>${animal.weight}</td>
                    <td>${animal.status}</td>

                    <td>
                        <button class="editBtn"
                            onclick="editAnimal(${animal.animalId})">
                            Edit
                        </button>

                        <button class="deleteBtn"
                            onclick="deleteAnimal(${animal.animalId})">
                            Delete
                        </button>
                    </td>
                </tr>
                `;
            });

        })
        .catch(err => console.log(err));

}

// ================= SAVE / UPDATE =================

function saveAnimal() {

    let tagNumber = document.getElementById("tagNumber").value.trim();

    if(tagNumber===""){
        alert("Tag Number is required");
        return;
    }

    const animal = {

        tagNumber: tagNumber,
        animalType: document.getElementById("animalType").value,
        breed: document.getElementById("breed").value,
        gender: document.getElementById("gender").value,
        birthDate: document.getElementById("birthDate").value,
        purchaseDate: document.getElementById("purchaseDate").value,
        purchasePrice: document.getElementById("purchasePrice").value,
        weight: document.getElementById("weight").value,
        healthStatus: document.getElementById("healthStatus").value,
        status: document.getElementById("status").value

    };

    let method = "POST";
    let url = API_URL;

    if(editId != null){

        method = "PUT";
        url = API_URL + "/" + editId;

    }

    fetch(url,{

        method:method,

        headers:{
            "Content-Type":"application/json"
        },

        body:JSON.stringify(animal)

    })

    .then(res=>res.json())

    .then(data=>{

        if(editId==null)
            alert("Animal Added Successfully");
        else
            alert("Animal Updated Successfully");

        clearForm();

        loadAnimals();

    })

    .catch(err=>console.log(err));

}

// ================= EDIT =================

function editAnimal(id){

    fetch(API_URL+"/"+id)

    .then(res=>res.json())

    .then(animal=>{

        editId = animal.animalId;

        document.getElementById("animalId").value = animal.animalId;
        document.getElementById("tagNumber").value = animal.tagNumber;
        document.getElementById("animalType").value = animal.animalType;
        document.getElementById("breed").value = animal.breed;
        document.getElementById("gender").value = animal.gender;
        document.getElementById("birthDate").value = animal.birthDate;
        document.getElementById("purchaseDate").value = animal.purchaseDate;
        document.getElementById("purchasePrice").value = animal.purchasePrice;
        document.getElementById("weight").value = animal.weight;
        document.getElementById("healthStatus").value = animal.healthStatus;
        document.getElementById("status").value = animal.status;

        document.getElementById("saveBtn").innerText="Update Animal";

    });

}

// ================= DELETE =================

function deleteAnimal(id){

    if(confirm("Are you sure you want to delete this animal?")){

        fetch(API_URL+"/"+id,{

            method:"DELETE"

        })

        .then(()=>{

            alert("Animal Deleted");

            loadAnimals();

        });

    }

}

// ================= CLEAR =================

function clearForm(){

    editId = null;

    document.getElementById("animalForm").reset();

    document.getElementById("animalId").value="";

    document.getElementById("saveBtn").innerText="Save Animal";

}