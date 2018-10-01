querySchemaModel = {};

querySchemaModelInit = function (data) {
    querySchemaModel.id = data.id;
    querySchemaModel.queryName = data.queryName;
    querySchemaModel.dimension = data.dimension;
    querySchemaModel.metric = data.metric;
    querySchemaModel.startIndexing = data.startIndexing;
};

querySchemaSave = function (userId) {
    var name = document.getElementById("_octopus-query-schema-name").value;
    var dimension = document.getElementById("_octopus-dimension").value;
    var metrics = document.getElementById("_octopus-metric").value;

    if (!name || !dimension) {
        alert("Have a blank field!");
        return;
    }

    var request = {};
    request.queryName = name;
    request.dimension = dimension;
    request.metric = metrics;

    $.ajax({
        url: "/api/v1.0/query/" + userId,
        type: "POST",
        data: JSON.stringify(request),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
    }).done(function (data) {
        if (data.code == "success") {
            this.querySchemaModelInit(data.body);

            alert("Update & Save Success");
        } else {
            var errorMessage = data.message ? "save fail. message : " + data.message : "save fail. retry please";
            alert(errorMessage);
        }
    }.bind(this)).fail(function () {
        alert("error");
    });
};

controlIndexingJob = function (userId) {
    //url
    var url = querySchemaModel.startIndexing
        ? "/api/v1.0/query/indexing/delete/" + userId + "/" + this.querySchemaModel.id
        : "/api/v1.0/query/indexing/create/" + userId + "/" + this.querySchemaModel.id;

    var name = document.getElementById("_octopus-query-schema-name").value;
    var dimension = document.getElementById("_octopus-dimension").value;
    var metrics = document.getElementById("_octopus-metric").value;

    if (!name || !dimension) {
        alert("Have a blank field!");
        return;
    }

    var request = {};
    request.queryName = name;
    request.dimension = dimension;
    request.metric = metrics;

    $.ajax({
        url: url,
        type: "POST",
        data: JSON.stringify(request),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
    }).done(function (data) {
        if (data.code == "success") {
            //ui
            querySchemaModel.startIndexing = querySchemaModel.startIndexing ? false : true;

            document.getElementById("_octopus-query-schema-start-indexing-btn").innerText =
                querySchemaModel.startIndexing ? "Delete Indexing" : "Start Indexing";

            this.initLogStash(userId);


        } else {
            var errorMessage = data.message ? "save fail. message : " + data.message : "save fail. retry please";
            alert(errorMessage);
        }
    }.bind(this)).fail(function () {
        alert("error");
    });
};

initLogStashField = function (data) {
    document.getElementById("_octopus-logstash-query-name").value =
        querySchemaModel.startIndexing ? querySchemaModel.queryName : "";

    document.getElementById("_octopus-logstash-secretKey").value =
        querySchemaModel.startIndexing ? data.secretKey : "";

    document.getElementById("_octopus-logstash-url").value =
        querySchemaModel.startIndexing ? data.url : "";

    document.getElementById("_octopus-logstash-example").value =
        querySchemaModel.startIndexing ? data.example : "";
};

initLogStash = function (userId) {
    $.ajax({
        url: "/api/v1.0/logstash/" + userId + "/" + querySchemaModel.queryName,
        type: "GET",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
    }).done(function (data) {
        if (data.code == "success") {
            var body = data.body;
            if (body) {
                this.initLogStashField(body);
            }
        } else {
            var errorMessage = data.message ? "init LogStash fail. message : " + data.message : "init LogStash fail. retry please";
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

    document.getElementById("_octopus-query-schema-start-indexing-btn").innerText =
        querySchemaModel.startIndexing ? "Delete Indexing" : "Start Indexing";
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

                if (querySchemaModel.startIndexing) {
                    this.initLogStash(userId);
                }
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

    document.getElementById("_octopus-query-schema-save-btn").addEventListener("click", function () {
        this.querySchemaSave(userId)
    }.bind(this));

    document.getElementById("_octopus-query-schema-start-indexing-btn").addEventListener("click", function () {
        this.controlIndexingJob(userId)
    }.bind(this));

    initField(userId);
};

init();