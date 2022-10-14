$(document).ready(function(){
    getInformation();
});

function getInformation() {
    $.ajax({
        url: "/api/Game/all",
        type: "GET",
        datatype: "JSON",
        success: function (information) {
            console.log(information);
            console.log(information.items);
            console.log(information[0]);
            showInformation(information);
    }
    });

 }
function showInformation(items){
     if(items==undefined)
     return;
    let myTable='<div class="container"><div class="row">';
    for(i=0;i<items.length;i++){
        myTable+=`
        <div class="card m2" style="width: 18rem;">
           <div class="card-body">
            <h5 class="card-title">${items[i].id}</h5>
            <h6 class="card-subtitle mb-2 test-muted">${items[i].name}</h6>
             <h3 class="card-subtitle mb-2 test-muted">${items[i].developer}</h3>
              <h6 class="card-subtitle mb-2 test-muted">${items[i].year}</h6>
             <h6 class="card-subtitle mb-2 test-muted">${items[i].description}</h6>

             <p class="card-text">${items[i].name}</p>
            <button class="btn btn-danger" onclick="deletElementById(${items[i].id})">Delete</button>
          </div>
        </div>
         `
    }
    myTable += " </div></div>";
    //$("#result."+className).empty();
    $("#resultado").append(myTable);
}

function saveInformation() {

    let myData = {
    name:$("#name").val(),
    developer:$("#developer").val(),
    year:$("#year").val(),
    description:$("#description").val(),
    };
    let dataToSend=JSON.stringify(myData);
    $.ajax({
        url: "/api/Game/save" ,

        type: "POST",
        data: dataToSend,
        contentType: "application/JSON",
        datatype: "JSON",
        success: function () {

            $("#result").empty();
            $("#name").val("");
            $("#developer").val("");
            $("#year").val("");
            $("#description").val("");
            getInformation();
            alert("Has been saved");
        }
    });
}

function editInformation() {
    let table = $("#table." + className + " input");
    let myData = {};
    for (i = 0; i < table.length; i++) {
        let key = table[i].id;
        myData[key] = $("#" + table[i].id + "." + className).val();
    }
    let dataToSend = JSON.stringify(myData);
    $.ajax({
        url: "https://g66791ff9529f18-games.adb.us-phoenix-1.oraclecloudapps.com/ords/admin/games/games" + routeTable,

        type: "PUT",
        data: dataToSend,
        contentType: "application/JSON",
        datatype: "JSON",
        success: function () {

            $("#result").empty();
            for (i = 0; i < table.length; i++) {
                $("#" + table[i].id + "." + className).val("");
            }
            getInformation(routeTable, className);
            alert("Se ha actualizado");
        }
    });
}
function deletElementById(elemetnId,className,routeTable) {
    let myData = {
        id: elemetnId
    };
    let dataToSend = JSON.stringify(myData);
    $.ajax({
        url: "https://g66791ff9529f18-games.adb.us-phoenix-1.oraclecloudapps.com/ords/admin/games/games" + routeTable,
        type: "DELETE",
        data: dataToSend,
        contentType: "application/JSON",
        datatype: "JSON",
        success: function (respuesta) {
            $("#result").empty();
            getInformation(routeTable,className);
            alert("Se ha eliminado");
        }
    });
 }


