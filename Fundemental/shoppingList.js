'use strict';

let deleteStuff = (id) => {
    console.log(id);
    let rowtoColour = document.querySelector(`#row${id}`);
    let checkBox = document.querySelector(`#btn${id}`)
    if (checkBox.checked)
    {
        rowtoColour.setAttribute('class', 'pickedUp');
    }else{
        rowtoColour.setAttribute('class', 'putBack')
    }    
}
const table = document.querySelector('#tableData');
let addtoList = (data) => {
    for(let j of data){
        console.log("hello");
        let idVal = j.id;
        let itemNameVal = j.itemName;
        let quantityVal = j.quantity;
        let priceVal = j.price;
        let deleteBtn = document.createElement('input');
        let changeBtn = document.createElement('input');
        deleteBtn.type = "checkbox"
        deleteBtn.setAttribute('id', `btn${idVal}`);
        deleteBtn.setAttribute('class', 'aDeleteButton');
        deleteBtn.innerHTML = "Delete";
        let fullRow = document.createElement('tr');
        fullRow.setAttribute('id', `row${idVal}`);
        let idCol = document.createElement('th');
        idCol.setAttribute('scope', 'row');
        let itemNameCol = document.createElement('td');
        let quantityCol = document.createElement('td');
        let priceCol = document.createElement('td');
        let buttontd = document.createElement('td');
        idCol.innerHTML = idVal;
        itemNameCol.innerHTML = itemNameVal;
        quantityCol.innerHTML = quantityVal;
        priceCol.innerHTML = priceVal;
        buttontd.appendChild(deleteBtn);
        fullRow.appendChild(idCol);
        fullRow.appendChild(itemNameCol);
        fullRow.appendChild(quantityCol);
        fullRow.appendChild(priceCol);
        fullRow.appendChild(buttontd);
        table.appendChild(fullRow);

        deleteBtn.addEventListener('click', function () {
            deleteStuff(idVal)
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