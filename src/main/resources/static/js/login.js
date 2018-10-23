login = function () {
    var email = document.getElementById("_octopus-email").value;
    var password = document.getElementById("_octopus-password").value;

    if (!email || !password) {
        alert("Have a blank field!");
        return;
    }

    var request = {};
    request.userId = email;
    request.userPwd = password;

    $.ajax({
        url: "/api/v1.0/user/login",
        type: "POST",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(request),
        dataType: "json",
    }).done(function (data) {
    	console.log(data);
        if (data.code == "success") {
            window.location.href = "/dashboard";
        } else {
            alert("login fail. retry please");
        }
    }).fail(function (xhr) {
    	console.log(xhr.statusText);
        alert("error");
    });
};


init = function () {
    document.getElementById("_octopus-login-btn").addEventListener("click", this.login);
};

init();