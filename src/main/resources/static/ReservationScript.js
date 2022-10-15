$(document).ready(function(){
    getInformation();
});

function getInformation() {
    $.ajax({
        url: "/api/Reservation/all",
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
        myTable+= '<h5 class="card-title">'+data.idReservation+'</h5>';
        myTable+= '<h6 class="card-subtitle mb-2 test-muted">'+data.startDate+'</h6>';
        myTable+= '<h3 class="card-subtitle mb-2 test-muted">'+data.devolutionDate+'</h3>';
        myTable+= '<h6 class="card-subtitle mb-2 test-muted">'+data.status+'</h6>';
        myTable+= '<h6 class="card-subtitle mb-2 test-muted">'+data.score+'</h6>';
        myTable+= '<p class="card-text">'+data.startDate+'</p>';
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
      $("#startDate").val(data.startDate);
      $("#devolutionDate").val(data.devolutionDate);
      $("#status").val(data.status);
      $("#score").val(data.score);
}
function saveInformation() {

    let myData = {

        startDate:$("#startDate").val(),
        devolutionDate:$("#devolutionDate").val(),
        status:$("#status").val(),
        score:$("#score").val(),
    };
    let dataToSend=JSON.stringify(myData);
    $.ajax({
        url: "/api/Reservation/save" ,

        type: "POST",
        data: dataToSend,
        contentType: "application/JSON",
        datatype: "JSON",
        success: function () {

            $("#result").empty();
            $("#id").val("");
            $("#startDate").val("");
            $("#devolutionDate").val("");
            $("#status").val("");
            $("#score").val("");
            getInformation();
            alert("Has been saved");
        }
    });
}

function editInformation() {
    let myData = {
            idReservation:$("#id").val(),
            startDate:$("#startDate").val(),
            devolutionDate:$("#devolutionDate").val(),
            status:$("#status").val(),
            score:$("#score").val(),
        };
    let dataToSend = JSON.stringify(myData);
    $.ajax({
        url: "/api/Reservation/update",
        type: "PUT",
        data: dataToSend,
        contentType: "application/JSON",
        datatype: "JSON",
        success: function (respuesta) {
            console.log(respuesta);
            $("#result").empty();
            $("#id").empty(),
                        $("#result").empty();
                        $("#startDate").val("");
                        $("#devolutionDate").val("");
                        $("#status").val("");
                        $("#score").val("");
            getInformation();
            alert("as been update");
        }
    });
}
function deletElementById(elementId) {
    let myData = {
        idReservation: elementId
    };
    let dataToSend = JSON.stringify(myData);
    $.ajax({
        url: "/api/Reservation/"+ elementId,
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


