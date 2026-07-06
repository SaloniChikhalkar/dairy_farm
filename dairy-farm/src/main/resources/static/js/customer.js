const API = "http://localhost:8080/api/customers";

window.onload = function () {
    loadCustomers();
};

// ================= LOAD CUSTOMERS =================

function loadCustomers() {

    fetch(API)
        .then(res => res.json())
        .then(data => {

            let table = document.getElementById("customerBody");
            table.innerHTML = "";

            data.forEach(c => {

                table.innerHTML += `
                <tr>
                    <td>${c.customerId}</td>
                    <td>${c.customerName}</td>
                    <td>${c.mobileNumber}</td>
                    <td>${c.email}</td>
                    <td>${c.address}</td>

                    <td>

                        <button class="edit"
                        onclick="editCustomer(${c.customerId})">
                        Edit
                        </button>

                        <button class="delete"
                        onclick="deleteCustomer(${c.customerId})">
                        Delete
                        </button>

                    </td>

                </tr>
                `;

            });

        });

}

// ================= SAVE / UPDATE =================

document.getElementById("customerForm")
.addEventListener("submit", function (e) {

    e.preventDefault();

    let customer = {

        customerName: document.getElementById("customerName").value,

        mobileNumber: document.getElementById("mobileNumber").value,

        email: document.getElementById("email").value,

        address: document.getElementById("address").value

    };

    let id = document.getElementById("customerId").value;

    let method = id ? "PUT" : "POST";

    let url = id ? API + "/" + id : API;

    fetch(url, {

        method: method,

        headers: {

            "Content-Type": "application/json"

        },

        body: JSON.stringify(customer)

    })

    .then(() => {

        alert(id ? "Customer Updated Successfully" : "Customer Added Successfully");

        clearForm();

        loadCustomers();

    });

});

// ================= EDIT =================

function editCustomer(id) {

    fetch(API + "/" + id)

    .then(res => res.json())

    .then(c => {

        document.getElementById("customerId").value = c.customerId;

        document.getElementById("customerName").value = c.customerName;

        document.getElementById("mobileNumber").value = c.mobileNumber;

        document.getElementById("email").value = c.email;

        document.getElementById("address").value = c.address;

        window.scrollTo({

            top:0,

            behavior:"smooth"

        });

    });

}

// ================= DELETE =================

function deleteCustomer(id) {

    if(confirm("Are you sure you want to delete this customer?")){

        fetch(API + "/" + id,{

            method:"DELETE"

        })

        .then(()=>{

            alert("Customer Deleted Successfully");

            loadCustomers();

        });

    }

}

// ================= SEARCH =================

function searchCustomer(){

    let input=document.getElementById("searchCustomer")
    .value.toLowerCase();

    let rows=document.querySelectorAll("#customerBody tr");

    rows.forEach(row=>{

        let name=row.cells[1].innerText.toLowerCase();

        row.style.display=name.includes(input) ? "" : "none";

    });

}

// ================= CLEAR SEARCH =================

function clearSearch(){

    document.getElementById("searchCustomer").value="";

    searchCustomer();

}

// ================= CLEAR FORM =================

function clearForm(){

    document.getElementById("customerForm").reset();

    document.getElementById("customerId").value="";

}

// ================= EXPORT EXCEL =================

function exportExcel(){

    let table=document.getElementById("customerTable");

    let html=table.outerHTML;

    let url='data:application/vnd.ms-excel,' + encodeURIComponent(html);

    let link=document.createElement("a");

    link.href=url;

    link.download="CustomerReport.xls";

    link.click();

}

// ================= EXPORT PDF =================

function exportPDF(){

    const { jsPDF } = window.jspdf;

    let doc=new jsPDF();

    doc.text("Customer Report",14,15);

    doc.autoTable({

        html:"#customerTable",

        startY:25

    });

    doc.save("CustomerReport.pdf");

}