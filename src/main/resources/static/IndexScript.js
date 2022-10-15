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
            showInformation(information);
    }
    });

 }
function showInformation(items){
     if(items==undefined)
     return;
    let myTable='<div class="container"><div class="row">';
    for(i=0;i<items.length;i++){
        let data = items[i];
        let convertJSON = JSON.stringify(data);
        myTable+= '<div class="card m2" style="width: 18rem;">';
        myTable+= '<div class="card-body">';
        myTable+= '<h5 class="card-title">'+data.id+'</h5>';
        myTable+= '<h6 class="card-subtitle mb-2 test-muted">'+data.name+'</h6>';
        myTable+= '<h3 class="card-subtitle mb-2 test-muted">'+data.developer+'</h3>';
        myTable+= '<h6 class="card-subtitle mb-2 test-muted">'+data.year+'</h6>';
        myTable+= '<h6 class="card-subtitle mb-2 test-muted">'+data.description+'</h6>';
        myTable+= '<p class="card-text">'+data.name+'</p>';
        myTable += "<td><button  class='btn btn-primary mb-3' onclick='showInformationInFields("+ convertJSON + ")'>Edit</button></td>";
        myTable+= '<button class="btn btn-danger" onclick="deletElementById('+data.id+')">Delete</button>';
        myTable+= '</div>';
        myTable+= '</div>';

    }
    myTable += " </div></div>";
    $("#resultado").empty();
    $("#resultado").append(myTable);
}
function showInformationInFields(data){
      $("#id").val(data.id);
      $("#name").val(data.name);
      $("#developer").val(data.developer);
      $("#year").val(data.year);
      $("#description").val(data.description);
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
            $("#id").val("");
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
    let myData = {
            id:$("#id").val(),
            name:$("#name").val(),
            developer:$("#developer").val(),
            year:$("#year").val(),
            description:$("#description").val(),
        };
    let dataToSend = JSON.stringify(myData);
    $.ajax({
        url: "/api/Game/update",
        type: "PUT",
        data: dataToSend,
        contentType: "application/JSON",
        datatype: "JSON",
        success: function (respuesta) {
            console.log(respuesta);
            $("#result").empty();
            $("#id").empty(),
                        $("#result").empty();
                        $("#name").val("");
                        $("#developer").val("");
                        $("#year").val("");
                        $("#description").val("");
            getInformation();
            alert("as been update");
        }
    });
}
function deletElementById(elementId) {
    let myData = {
        id: elementId
    };
    let dataToSend = JSON.stringify(myData);
    $.ajax({
        url: "/api/Game/"+ elementId,
        type: "DELETE",
        data: dataToSend,
        contentType: "application/JSON",
        datatype: "JSON",
        success: function (respuesta) {
            $("#result").empty();
            getInformation();
            alert("Se ha eliminado");
        }
    });
 }


