querySchemaModel = {};

querySchemaModelInit = function (data) {
    querySchemaModel.id = data.id;
    querySchemaModel.queryName = data.queryName;
    querySchemaModel.dimension = data.dimension;
    querySchemaModel.metric = data.metric;
    querySchemaModel.startIndexing = data.startIndexing;
};

sqlQuery = function (userId) {
    if (!querySchemaModel.query) {
        alert("Have a blank field!");
        return;
    }

    var request = {};
    request.sqlQuery = querySchemaModel.query;

    $.ajax({
        url: "/api/v1.0/query/visual/" + userId + "/sqlQuery",
        type: "POST",
        data: JSON.stringify(request),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
    }).done(function (data) {
        if (data.code == "success") {
            document.getElementById("_octopus-sql-query-result").value = data.body;

            console.log("sql query success");
        } else {
            var errorMessage = data.message ? "sql query fail. message : " + data.message : "sql query fail. retry please";
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
        this.sqlQuery();
    }.bind(this));

    $(function() {
        $('textarea[data-editor]').each(function() {
            var textarea = $(this);
            var mode = textarea.data('editor');
            var editDiv = $('<div>', {
                position: 'absolute',
                width: textarea.width(),
                height: textarea.height(),
                'class': textarea.attr('class')
            }).insertBefore(textarea);
            textarea.css('display', 'none');
            var editor = ace.edit(editDiv[0]);
            editor.renderer.setShowGutter(textarea.data('gutter'));
            editor.getSession().setValue(textarea.val());
            editor.getSession().setMode("ace/mode/" + mode);
            editor.setTheme("ace/theme/textmate");
            editor.getSession().on('change', function(){
                querySchemaModel.query = editor.getSession().getValue();
            });
            // copy back to textarea on form submit...
            textarea.closest('form').submit(function() {
                textarea.val(editor.getSession().getValue());
            })
        });
    });

    initField(userId);
};

init();