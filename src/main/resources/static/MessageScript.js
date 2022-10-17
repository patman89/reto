$(document).ready(function(){
    getInformation();
});

function getInformation() {
    $.ajax({
        url: "/api/Message/all",
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
        myTable+= '<h5 class="card-title">'+data.idMessage+'</h5>';
        myTable+= '<h6 class="card-subtitle mb-2 test-muted">'+data.messageText+'</h6>';
        myTable+= '<p class="card-text">'+data.messageText+'</p>';
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
      $("#id").val(data.idMessage);
      $("#message").val(data.messageText);

}
function saveInformation() {

    let myData = {

        messageText:$("#message").val(),

    };
    let dataToSend=JSON.stringify(myData);
    $.ajax({
        url: "/api/Message/save" ,

        type: "POST",
        data: dataToSend,
        contentType: "application/JSON",
        datatype: "JSON",
        success: function () {

            $("#result").empty();
            $("#id").val("");
            $("#message").val("");
            getInformation();
            alert("Has been saved");
        }
    });
}

function editInformation() {
    let myData = {
            idMessage:$("#id").val(),
            messageText:$("#message").val(),

        };
    let dataToSend = JSON.stringify(myData);
    $.ajax({
        url: "/api/Message/update",
        type: "PUT",
        data: dataToSend,
        contentType: "application/JSON",
        datatype: "JSON",
        success: function (respuesta) {
            console.log(respuesta);
            $("#result").empty();
            $("#id").empty(),

                        $("#message").val("");

            getInformation();
            alert("as been update");
        }
    });
}
function deletElementById(elementId) {
    let myData = {
        idMessage: elementId
    };
    let dataToSend = JSON.stringify(myData);
    $.ajax({
        url: "/api/Message/"+ elementId,
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


