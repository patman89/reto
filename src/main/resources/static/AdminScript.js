$(document).ready(function(){
    getInformation();
});

function getInformation() {
    $.ajax({
        url: "/api/Admin/all",
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
        myTable+= '<h3 class="card-subtitle mb-2 test-muted">'+data.password+'</h3>';
        myTable+= '<h6 class="card-subtitle mb-2 test-muted">'+data.email+'</h6>';
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
      $("#password").val(data.password);
      $("#email").val(data.email);
     }
function saveInformation() {

    let myData = {

        name:$("#name").val(),
        developer:$("#password").val(),
        year:$("#email").val(),

    };
    let dataToSend=JSON.stringify(myData);
    $.ajax({
        url: "/api/Admin/save" ,

        type: "POST",
        data: dataToSend,
        contentType: "application/JSON",
        datatype: "JSON",
        success: function () {

            $("#result").empty();
            $("#id").val("");
            $("#name").val("");
            $("#password").val("");
            $("#email").val("");
            getInformation();
            alert("Has been saved");
        }
    });
}

function editInformation() {
    let myData = {
            id:$("#id").val(),
            name:$("#name").val(),
            password:$("#password").val(),
            email:$("#email").val(),
         };
    let dataToSend = JSON.stringify(myData);
    $.ajax({
        url: "/api/Admin/update",
        type: "PUT",
        data: dataToSend,
        contentType: "application/JSON",
        datatype: "JSON",
        success: function (respuesta) {
            console.log(respuesta);
            $("#result").empty();
            $("#id").empty(),
                        $("#name").val("");
                        $("#password").val("");
                        $("#email").val("");
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
        url: "/api/Admin/"+ elementId,
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


