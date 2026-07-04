const API = "http://localhost:8080/api/milk-sales";
const CUSTOMER_API = "http://localhost:8080/api/customers";

window.onload = function () {

    loadCustomers();
    loadSales();

    document.getElementById("saleDate").valueAsDate = new Date();

    document.getElementById("quantity").addEventListener("input", calculateTotal);
    document.getElementById("price").addEventListener("input", calculateTotal);

    document.getElementById("searchCustomer")
        .addEventListener("keyup", searchSales);

    document.getElementById("searchDate")
        .addEventListener("change", searchSales);

    document.getElementById("saleForm")
        .addEventListener("submit", saveSale);

};


// ====================== LOAD CUSTOMERS ======================

function loadCustomers() {

    fetch(CUSTOMER_API)
        .then(res => res.json())
        .then(data => {

            let customer = document.getElementById("customer");

            customer.innerHTML = '<option value="">Select Customer</option>';

            data.forEach(c => {

                customer.innerHTML += `

                <option value="${c.customerId}">
                    ${c.customerName}
                </option>

                `;

            });

        });

}


// ====================== LOAD SALES ======================

function loadSales() {

    fetch(API)

        .then(res => res.json())

        .then(data => {

            let table = document.getElementById("saleTable");

            table.innerHTML = "";

            data.forEach(s => {

                table.innerHTML += `

                <tr>

                    <td>${s.saleId}</td>

                    <td>${s.customer.customerName}</td>

                    <td>${s.saleDate}</td>

                    <td>${s.quantity}</td>

                    <td>${s.pricePerLiter}</td>

                    <td>${s.totalAmount}</td>

                    <td>${s.paymentStatus}</td>

                    <td>

                        <button class="edit"
                        onclick="editSale(${s.saleId})">
                        Edit
                        </button>

                        <button class="delete"
                        onclick="deleteSale(${s.saleId})">
                        Delete
                        </button>

                    </td>

                </tr>

                `;

            });

        });

}


// ====================== AUTO TOTAL ======================

function calculateTotal() {

    let qty = parseFloat(document.getElementById("quantity").value) || 0;

    let price = parseFloat(document.getElementById("price").value) || 0;

    let total = qty * price;

    document.getElementById("total").value = total.toFixed(2);

}


// ====================== SAVE / UPDATE ======================

function saveSale(e) {

    e.preventDefault();

    let id = document.getElementById("saleId").value;

    let sale = {

        customer: {

            customerId: document.getElementById("customer").value

        },

        saleDate: document.getElementById("saleDate").value,

        quantity: document.getElementById("quantity").value,

        pricePerLiter: document.getElementById("price").value,

        totalAmount: document.getElementById("total").value,

        paymentStatus: document.getElementById("payment").value

    };

    let method = id ? "PUT" : "POST";

    let url = id ? API + "/" + id : API;

    fetch(url, {

        method: method,

        headers: {

            "Content-Type": "application/json"

        },

        body: JSON.stringify(sale)

    })

    .then(res => {

        if (!res.ok) {

            throw new Error("Unable to save data");

        }

        return res.text();

    })

    .then(() => {

        alert(id ? "Milk Sale Updated Successfully"
                 : "Milk Sale Saved Successfully");

        clearForm();

        loadSales();

    })

    .catch(err => {

        alert(err.message);

    });

}

// ====================== EDIT SALE ======================

function editSale(id) {

    fetch(API + "/" + id)

        .then(res => res.json())

        .then(sale => {

            document.getElementById("saleId").value = sale.saleId;

            document.getElementById("customer").value =
                sale.customer.customerId;

            document.getElementById("saleDate").value =
                sale.saleDate;

            document.getElementById("quantity").value =
                sale.quantity;

            document.getElementById("price").value =
                sale.pricePerLiter;

            document.getElementById("payment").value =
                sale.paymentStatus;

            calculateTotal();

            window.scrollTo({

                top: 0,

                behavior: "smooth"

            });

        })

        .catch(err => {

            alert("Unable to load record.");

            console.log(err);

        });

}


// ====================== DELETE SALE ======================

function deleteSale(id) {

    if (!confirm("Are you sure you want to delete this sale?")) {

        return;

    }

    fetch(API + "/" + id, {

        method: "DELETE"

    })

    .then(res => {

        if (!res.ok) {

            throw new Error("Delete failed.");

        }

        return res.text();

    })

    .then(() => {

        alert("Milk Sale Deleted Successfully.");

        loadSales();

    })

    .catch(err => {

        alert(err.message);

    });

}


// ====================== SEARCH CUSTOMER & DATE ======================

function searchSales() {

    let customer = document
        .getElementById("searchCustomer")
        .value
        .toLowerCase();

    let date = document
        .getElementById("searchDate")
        .value;

    let rows = document.querySelectorAll("#saleTable tr");

    rows.forEach(row => {

        let customerName =
            row.cells[1].innerText.toLowerCase();

        let saleDate =
            row.cells[2].innerText;

        let customerMatch =
            customerName.includes(customer);

        let dateMatch =
            date === "" || saleDate === date;

        row.style.display =
            customerMatch && dateMatch
            ? ""
            : "none";

    });

}


// ====================== CLEAR SEARCH ======================

function clearSearch() {

    document.getElementById("searchCustomer").value = "";

    document.getElementById("searchDate").value = "";

    searchSales();

}


// ====================== CLEAR FORM ======================

function clearForm() {

    document.getElementById("saleForm").reset();

    document.getElementById("saleId").value = "";

    document.getElementById("saleDate").valueAsDate =
        new Date();

    document.getElementById("total").value = "";

}

// ====================== EXPORT TO EXCEL ======================

function exportExcel() {

    let table = document.getElementById("salesTable");

    let html = table.outerHTML;

    let url = "data:application/vnd.ms-excel," + encodeURIComponent(html);

    let link = document.createElement("a");

    link.href = url;

    link.download = "MilkSalesReport.xls";

    document.body.appendChild(link);

    link.click();

    document.body.removeChild(link);

}


// ====================== EXPORT TO PDF ======================

function exportPDF() {

    const { jsPDF } = window.jspdf;

    let doc = new jsPDF("p", "pt");

    doc.setFontSize(18);
    doc.text("Milk Sales Report", 40, 40);

    doc.autoTable({

        html: "#salesTable",

        startY: 70,

        theme: "grid",

        headStyles: {

            fillColor: [25, 135, 84]

        },

        styles: {

            halign: "center",

            fontSize: 10

        }

    });

    doc.save("MilkSalesReport.pdf");

}


// ====================== REFRESH TABLE ======================

function refreshTable() {

    clearSearch();

    loadSales();

}


// ====================== RESET FORM ======================

function resetForm() {

    clearForm();

    calculateTotal();

}


// ====================== FORMAT DATE ======================

function formatDate(date) {

    if (!date) return "";

    let d = new Date(date);

    let day = String(d.getDate()).padStart(2, "0");

    let month = String(d.getMonth() + 1).padStart(2, "0");

    let year = d.getFullYear();

    return `${year}-${month}-${day}`;

}


// ====================== SUCCESS MESSAGE ======================

function showSuccess(message) {

    alert(message);

}


// ====================== ERROR MESSAGE ======================

function showError(message) {

    alert(message);

}


// ====================== PAGE REFRESH ======================

window.addEventListener("pageshow", function () {

    loadSales();

});


// ====================== END OF FILE ======================