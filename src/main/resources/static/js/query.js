querySchemaModel = {};

querySchemaModelInit = function (data) {
    querySchemaModel.id = data.id;
    querySchemaModel.queryName = data.queryName;
    querySchemaModel.dimension = data.dimension;
    querySchemaModel.metric = data.metric;
    querySchemaModel.startIndexing = data.startIndexing;
};

nativeQuery = function (userId) {
    var nativeQuery = document.getElementById("_octopus-native-query").value;

    if (!nativeQuery) {
        alert("Have a blank field!");
        return;
    }

    var request = {};
    request.nativeQuery = nativeQuery;

    $.ajax({
        url: "/api/v1.0/query/visual/" + userId + "/nativeQuery",
        type: "POST",
        data: JSON.stringify(request),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
    }).done(function (data) {
        if (data.code == "success") {
            document.getElementById("_octopus-native-query-result").value = data.body;

            console.log("native query success");
        } else {
            var errorMessage = data.message ? "native query fail. message : " + data.message : "native query fail. retry please";
            console.log(errorMessage);
        }
    }.bind(this)).fail(function () {
        alert("error");
    });
};

initRegistdDruidBroker = function (userId) {
    $.ajax({
        url: "/api/v1.0/admin/server/druid/broker",
        type: "GET",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
    }).done(function (data) {
        if (data.code == "success") {
            var body = data.body;
            if (body) {
                document.getElementById("_octopus-druid-broker-url").value = data.body.ip + ":" + data.body.port;
            }
        } else {
            var errorMessage = data.message ? "find query schema fail. message : " + data.message : "find query schema fail. retry please";
            console.log(errorMessage);
        }
    }.bind(this)).fail(function () {
        alert("error");
    });
};

initFieldValue = function (data) {
    document.getElementById("_octopus-query-schema-name").value = data.queryName;
    document.getElementById("_octopus-dimension").value = data.dimension;
    document.getElementById("_octopus-metric").value = data.metric;

    this.querySchemaModelInit(data);
};

initField = function (userId) {
    $.ajax({
        url: "/api/v1.0/query/" + userId,
        type: "GET",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
    }).done(function (data) {
        if (data.code == "success") {
            var body = data.body;
            if (body && body.length > 0) {
                this.initFieldValue(body[0]);

                this.initRegistdDruidBroker(userId);
            }
        } else {
            var errorMessage = data.message ? "find query schema fail. message : " + data.message : "find query schema fail. retry please";
            console.log(errorMessage);
        }
    }.bind(this)).fail(function () {
        alert("error");
    });
}

init = function () {

    var userId = "kang0921ok";

    document.getElementById("_octopus-query-request-btn").addEventListener("click", function () {
        this.nativeQuery();
    }.bind(this));

    initField(userId);
};

init();