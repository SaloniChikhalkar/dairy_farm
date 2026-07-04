const api = "http://localhost:8080/api/feed";

let editId = null;

window.onload = function () {

    loadFeed();

    document.getElementById("purchaseDate").value =
        new Date().toISOString().split("T")[0];
};

function saveFeed() {

    const feed = {

        feedName: document.getElementById("feedName").value,
        feedType: document.getElementById("feedType").value,
        quantity: parseFloat(document.getElementById("quantity").value),
        unit: document.getElementById("unit").value,
        purchasePrice: parseFloat(document.getElementById("purchasePrice").value),
        supplier: document.getElementById("supplier").value,
        purchaseDate: document.getElementById("purchaseDate").value,
        expiryDate: document.getElementById("expiryDate").value,
        status: document.getElementById("status").value

    };

    let method = "POST";
    let url = api;

    if (editId != null) {
        method = "PUT";
        url = api + "/" + editId;
    }

    fetch(url, {

        method: method,

        headers: {
            "Content-Type": "application/json"
        },

        body: JSON.stringify(feed)

    })

    .then(res => res.json())

    .then(data => {

        alert("Feed Saved Successfully");

        clearForm();

        loadFeed();

    });

}

function loadFeed() {

    fetch(api)

    .then(res => res.json())

    .then(data => {

        let table = document.getElementById("feedTable");

        table.innerHTML = "";

        data.forEach(feed => {

            table.innerHTML += `

<tr>

<td>${feed.feedId}</td>

<td>${feed.feedName}</td>

<td>${feed.feedType}</td>

<td>${feed.quantity}</td>

<td>${feed.unit}</td>

<td>${feed.purchasePrice}</td>

<td>${feed.supplier}</td>

<td>${feed.status}</td>

<td>

<button class="editBtn" onclick="editFeed(${feed.feedId})">Edit</button>

<button class="deleteBtn" onclick="deleteFeed(${feed.feedId})">Delete</button>

</td>

</tr>

`;

        });

    });

}

function editFeed(id) {

    fetch(api + "/" + id)

    .then(res => res.json())

    .then(feed => {

        editId = id;

        document.getElementById("feedName").value = feed.feedName;
        document.getElementById("feedType").value = feed.feedType;
        document.getElementById("quantity").value = feed.quantity;
        document.getElementById("unit").value = feed.unit;
        document.getElementById("purchasePrice").value = feed.purchasePrice;
        document.getElementById("supplier").value = feed.supplier;
        document.getElementById("purchaseDate").value = feed.purchaseDate;
        document.getElementById("expiryDate").value = feed.expiryDate;
        document.getElementById("status").value = feed.status;

        document.getElementById("saveBtn").innerHTML = "Update";

    });

}

function deleteFeed(id) {

    if (confirm("Delete Feed Record?")) {

        fetch(api + "/" + id, {

            method: "DELETE"

        })

        .then(() => {

            alert("Feed Deleted Successfully");

            loadFeed();

        });

    }

}

function clearForm() {

    editId = null;

    document.getElementById("feedForm").reset();

    document.getElementById("purchaseDate").value =
        new Date().toISOString().split("T")[0];

    document.getElementById("saveBtn").innerHTML = "Save";

}