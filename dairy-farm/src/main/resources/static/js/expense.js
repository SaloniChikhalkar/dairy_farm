const API = "http://localhost:8080/api/expenses";

window.onload = function () {

    loadExpenses();

    document.getElementById("expenseDate").valueAsDate = new Date();

};

// ================= LOAD ALL EXPENSES =================

function loadExpenses() {

    fetch(API)

        .then(response => response.json())

        .then(data => {

            let table = document.getElementById("expenseBody");

            table.innerHTML = "";

            data.forEach(expense => {

                table.innerHTML += `

                <tr>

                    <td>${expense.expenseId}</td>

                    <td>${expense.expenseDate}</td>

                    <td>${expense.category}</td>

                    <td>${expense.description}</td>

                    <td>₹ ${expense.amount}</td>

                    <td>

                        <button class="edit"
                        onclick="editExpense(${expense.expenseId})">
                        Edit
                        </button>

                        <button class="delete"
                        onclick="deleteExpense(${expense.expenseId})">
                        Delete
                        </button>

                    </td>

                </tr>

                `;

            });

        });

}

// ================= SAVE / UPDATE =================

document.getElementById("expenseForm").addEventListener("submit", function (e) {

    e.preventDefault();

    let expense = {

        expenseDate: document.getElementById("expenseDate").value,

        category: document.getElementById("category").value,

        description: document.getElementById("description").value,

        amount: parseFloat(document.getElementById("amount").value)

    };

    let id = document.getElementById("expenseId").value;

    let method = id ? "PUT" : "POST";

    let url = id ? API + "/" + id : API;

    fetch(url, {

        method: method,

        headers: {

            "Content-Type": "application/json"

        },

        body: JSON.stringify(expense)

    })

    .then(() => {

        alert(id ? "Expense Updated Successfully" : "Expense Added Successfully");

        clearForm();

        loadExpenses();

    });

});

// ================= EDIT EXPENSE =================

function editExpense(id) {

    fetch(API + "/" + id)

        .then(response => response.json())

        .then(expense => {

            document.getElementById("expenseId").value = expense.expenseId;
            document.getElementById("expenseDate").value = expense.expenseDate;
            document.getElementById("category").value = expense.category;
            document.getElementById("description").value = expense.description;
            document.getElementById("amount").value = expense.amount;

            window.scrollTo({
                top: 0,
                behavior: "smooth"
            });

        });

}

// ================= DELETE EXPENSE =================

function deleteExpense(id) {

    if (confirm("Are you sure you want to delete this expense?")) {

        fetch(API + "/" + id, {

            method: "DELETE"

        })

        .then(() => {

            alert("Expense Deleted Successfully");

            loadExpenses();

        });

    }

}

// ================= SEARCH =================

function searchExpense() {

    let category = document.getElementById("searchCategory").value.toLowerCase();

    let date = document.getElementById("searchDate").value;

    let rows = document.querySelectorAll("#expenseBody tr");

    rows.forEach(row => {

        let rowCategory = row.cells[2].innerText.toLowerCase();

        let rowDate = row.cells[1].innerText;

        let categoryMatch = rowCategory.includes(category);

        let dateMatch = (date === "") || (rowDate === date);

        row.style.display = (categoryMatch && dateMatch) ? "" : "none";

    });

}

// Live Search

document.getElementById("searchCategory")
.addEventListener("keyup", searchExpense);

document.getElementById("searchDate")
.addEventListener("change", searchExpense);

// ================= CLEAR SEARCH =================

function clearSearch() {

    document.getElementById("searchCategory").value = "";

    document.getElementById("searchDate").value = "";

    searchExpense();

}

// ================= CLEAR FORM =================

function clearForm() {

    document.getElementById("expenseForm").reset();

    document.getElementById("expenseId").value = "";

    document.getElementById("expenseDate").valueAsDate = new Date();

}

// ================= EXPORT TO EXCEL =================

function exportExcel() {

    let table = document.getElementById("expenseTable");

    let html = table.outerHTML;

    let url = "data:application/vnd.ms-excel," + encodeURIComponent(html);

    let link = document.createElement("a");

    link.href = url;

    link.download = "Expense_Report.xls";

    document.body.appendChild(link);

    link.click();

    document.body.removeChild(link);

}

// ================= EXPORT TO PDF =================

function exportPDF() {

    const { jsPDF } = window.jspdf;

    let doc = new jsPDF();

    doc.setFontSize(16);

    doc.text("Expense Management Report", 14, 15);

    doc.autoTable({

        html: "#expenseTable",

        startY: 25,

        theme: "grid",

        headStyles: {

            fillColor: [25, 135, 84]

        }

    });

    doc.save("Expense_Report.pdf");

}