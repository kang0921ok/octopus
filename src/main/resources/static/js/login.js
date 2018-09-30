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
        data: JSON.stringify(request),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
    }).done(function (data) {
        if (data.code == "success") {
            window.location.href = "/dashboard";
        } else {
            alert("login fail. retry please");
        }
    }).fail(function () {
        alert("error");
    });
};


init = function () {
    document.getElementById("_octopus-login-btn").addEventListener("click", this.login);
};

init();