connect = function () {
    var dbType = document.getElementById("_octopus-db-system").value;
    var ip = document.getElementById("_octopus-ip").value;
    var port = document.getElementById("_octopus-port").value;
    var userName = document.getElementById("_octopus-username").value;
    var password = document.getElementById("_octopus-password").value;

    if (!dbType || !ip || !port || !userName || !password) {
        alert("Have a blank field!");
        return;
    }

    var request = {};
    request.dbType = dbType;
    request.ip = ip;
    request.port = port;
    request.userName = userName;
    request.password = password;

    $.ajax({
        url: "/api/v1.0/dbConnect",
        type: "POST",
        data: JSON.stringify(request),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
    }).done(function(data) {
        if(data.code == "success") {
            alert("DB connection success");
            window.location.href = "/user/login";
        } else {
            alert("DB connection fail : " + data.message);
        }
    }).fail(function() {
        alert( "error" );
    });
};

init = function () {
    document.getElementById("_octopus-connect").addEventListener("click", this.connect);

};

init();