connectTestAndSave = function (type) {
    var selectedType = document.getElementById("_octopus-" + type + "-select").value;
    var ip = document.getElementById("_octopus-" + type + "-ip").value;
    var port = document.getElementById("_octopus-" + type + "-port").value;

    if (!selectedType || !ip || !port) {
        alert("Have a blank field!");
        return;
    }

    var request = {};
    request.adminServerType = selectedType;
    request.ip = ip;
    request.port = port;
    request.updateUserId = "admin";

    $.ajax({
        url: "/api/v1.0/admin/server",
        type: "POST",
        data: JSON.stringify(request),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
    }).done(function (data) {
        if (data.code == "success") {
            alert("Save Success");
        } else {
            var errorMessage = data.message ? "save fail. message : " + data.message : "save fail. retry please";
            alert(errorMessage);
        }
    }).fail(function () {
        alert("error");
    });
};

convertType = function (type) {
    switch (type) {
        case "DRUID_OVERLOAD" : {
            return "druid-overlord";
        }
        case "DRUID_BROKER" : {
            return "druid-broker";
        }
        case "KAFKA" : {
            return "kafka";
        }
    }

    return "";
};

initFieldValue = function (type, data) {
    document.getElementById("_octopus-" + type + "-select").value = data.adminServerType;
    document.getElementById("_octopus-" + type + "-ip").value = data.ip;
    document.getElementById("_octopus-" + type + "-port").value = data.port;
};

initField = function () {
    $.ajax({
        url: "/api/v1.0/admin/server",
        type: "GET",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
    }).done(function (data) {
        if (data.code == "success") {
            var body = data.body;
            if (body && body.length > 0) ;
            for (var i = 0; i < body.length; i++) {
                var type = this.convertType(body[i].adminServerType);
                this.initFieldValue(type, body[i]);
            }
        } else {
            var errorMessage = data.message ? "find server list fail. message : " + data.message : "find server list fail. retry please";
            console.log(errorMessage);
        }
    }.bind(this)).fail(function () {
        alert("error");
    });
}

init = function () {
    document.getElementById("_octopus-druid-overlord-connect-btn").addEventListener("click", function () {
        this.connectTestAndSave("druid-overlord")
    }.bind(this));
    document.getElementById("_octopus-druid-broker-connect-btn").addEventListener("click", function () {
        this.connectTestAndSave("druid-broker")
    }.bind(this));
    document.getElementById("_octopus-kafka-connect-btn").addEventListener("click", function () {
        this.connectTestAndSave("kafka")
    }.bind(this));

    initField();
};

init();