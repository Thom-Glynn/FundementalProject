'use strict';

let addToTableBtn = document.querySelector('#addToTable');


let postData = (data) => {
    let newData = document.createElement('p');
    let bodyData = data.price;
    let titleData = data.itemName;
    newData.innerHTML = `Item name: ${titleData} Price: ${bodyData}`;
    let divToAddto = document.querySelector('#div1');
    divToAddto.appendChild(newData);
}
const table = document.querySelector('#tableData');

let deleteStuff = (id) => {
    fetch(`http://localhost:8080/ShoppingList/delete/${id}`, {
    method: 'DELETE'
})
.then((data) =>console.log(`requested all food with JSON response ${data}`))
.catch((error) => console.log(error))
location.reload();
}

let actuallyChangeStuff = (id, value, data ) =>{
    let myObject = data;
    myObject.quantity = value;
    fetch(`http://localhost:8080/ShoppingList/update/${id}`, {
    method: `PUT`, // Declaring the method
    headers: {
        "Content-type": "application/json" // Header
    },
    body: JSON.stringify(myObject) // What data to post
})
.then((response) => response.json())
.then((data) => console.log(`request succesful with JSON response ${data.title} steve`))
.catch( (error) => console.log(error));
location.reload();
}

let changeStuff = (id, value) =>{
    fetch(`http://localhost:8080/ShoppingList/read/byId/${id}`)
    .then((response) => {
        if (response.status !== 200) {
            console.log(`Status: ${response.status}`);
            return;
        }
        response.json()
            .then((data) => {
                actuallyChangeStuff(id,value, data)
            })
            .catch((error) => console.log(`Error: ${error}`));
    });
}

let addtoList = (data) => {
    for(let j of data){
        let idVal = j.id;
        let itemNameVal = j.itemName;
        let quantityVal = j.quantity;
        let priceVal = j.price;
        let deleteBtn = document.createElement('button');
        let changeBtn = document.createElement('button');
        deleteBtn.setAttribute('id', `btn${idVal}`);
        changeBtn.setAttribute('id', `chgBtn${idVal}`);
        deleteBtn.setAttribute('class', 'aDeleteButton');
        changeBtn.setAttribute('class', 'aDeleteButton');
        deleteBtn.innerHTML = "Delete";        
        changeBtn.innerHTML = "Update";        
        let fullRow = document.createElement('tr');
        let idCol = document.createElement('th');
        idCol.setAttribute('scope', 'row');
        let itemNameCol = document.createElement('td');
        let quantityCol = document.createElement('td');
        let priceCol = document.createElement('td');
        let buttontd = document.createElement('td');
        quantityCol.setAttribute("contenteditable", `true`);
        idCol.innerHTML = idVal;
        itemNameCol.innerHTML = itemNameVal;
        quantityCol.innerHTML = quantityVal;
        priceCol.innerHTML = priceVal;
        buttontd.appendChild(deleteBtn);
        buttontd.appendChild(changeBtn);
        fullRow.appendChild(idCol);
        fullRow.appendChild(itemNameCol);
        fullRow.appendChild(quantityCol);
        fullRow.appendChild(priceCol);
        fullRow.appendChild(buttontd);
        table.appendChild(fullRow);
console.log(quantityCol.innerHTML);
        deleteBtn.addEventListener('click', function () {
            deleteStuff(idVal)
        });
        changeBtn.addEventListener('click', function () {
            changeStuff(idVal, Number(quantityCol.innerHTML));
        });
    }
};

let postPrice = (data) => {
    let total = 0;
    let pricePara = document.querySelector('#totalPrice');
    for (let i of data) {
        total = total + (Number(i.quantity) * Number(i.price));
    }

    pricePara.innerHTML = `Total Price £${total}`;
}

fetch(`http://localhost:8080/ShoppingList/read`)
    .then((response) => {
        if (response.status !== 200) {
            console.log(`Status: ${response.status}`);
            return;
        }
        response.json()
            .then((data) => {
                console.log(data);
                postPrice(data);
                addtoList(data);
            })
            .catch((error) => console.log(`Error: ${error}`));
    });


addToTableBtn.addEventListener('click', function () {
    let itemName = document.querySelector('#itemNameBox');
    let quantity = document.querySelector('#quantityBox');
    let price = document.querySelector('#priceBox');
    console.log(itemName);
    console.log(itemName.value);
    console.log(typeof itemName.value);
    console.log(quantity + "this");
    console.log(price);
    let newShoppingItem = {
        "itemName": itemName.value,
        "quantity": quantity.value,
        "price": price.value
    };
    console.log(newShoppingItem);
    fetch(`http://localhost:8080/ShoppingList/create`, { 
    method: `POST`, // Declaring the method
    headers: {
        "Content-type": "application/json" // Header
    },
    body: JSON.stringify(newShoppingItem) // What data to post
})
.then((response) => response.json())
.then((data) => console.log(`request succesful with JSON response ${data.title} steve`))
.catch( (error) => console.log(error));    
//location.reload();
});