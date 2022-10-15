$(document).ready(function(){
    getInformation();
});

function getInformation() {
    $.ajax({
        url: "/api/Client/all",
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
        myTable+= '<h5 class="card-title">'+data.idClient+'</h5>';
        myTable+= '<h6 class="card-subtitle mb-2 test-muted">'+data.name+'</h6>';
        myTable+= '<h3 class="card-subtitle mb-2 test-muted">'+data.email+'</h3>';
        myTable+= '<h6 class="card-subtitle mb-2 test-muted">'+data.password+'</h6>';
        myTable+= '<h6 class="card-subtitle mb-2 test-muted">'+data.age+'</h6>';
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
      $("#email").val(data.email);
      $("#password").val(data.password);
      $("#age").val(data.age);
}
function saveInformation() {

    let myData = {

        name:$("#name").val(),
        email:$("#email").val(),
        password:$("#password").val(),
        age:$("#age").val(),
    };
    let dataToSend=JSON.stringify(myData);
    $.ajax({
        url: "/api/Client/save" ,

        type: "POST",
        data: dataToSend,
        contentType: "application/JSON",
        datatype: "JSON",
        success: function () {

            $("#result").empty();
            $("#id").val("");
            $("#name").val("");
            $("#email").val("");
            $("#password").val("");
            $("#age").val("");
            getInformation();
            alert("Has been saved");
        }
    });
}

function editInformation() {
    let myData = {
            idClient:$("#id").val(),
            name:$("#name").val(),
            email:$("#email").val(),
            password:$("#password").val(),
            age:$("#age").val(),
        };
    let dataToSend = JSON.stringify(myData);
    $.ajax({
        url: "/api/Client/update",
        type: "PUT",
        data: dataToSend,
        contentType: "application/JSON",
        datatype: "JSON",
        success: function (respuesta) {
            console.log(respuesta);
            $("#result").empty();
            $("#id").empty(),

                        $("#name").val("");
                        $("#email").val("");
                        $("#password").val("");
                        $("#age").val("");
            getInformation();
            alert("as been update");
        }
    });
}
function deletElementById(elementId) {
    let myData = {
        idClient: elementId
    };
    let dataToSend = JSON.stringify(myData);
    $.ajax({
        url: "/api/Client/"+ elementId,
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


