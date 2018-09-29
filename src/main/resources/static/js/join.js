join = function () {
    var email = document.getElementById("_octopus-email").value;
    var password = document.getElementById("_octopus-password").value;
    var passwordConfirm = document.getElementById("_octopus-password-confirm").value;

    if (!email || !password || !passwordConfirm) {
        alert("Have a blank field!");
        return;
    }

    if(password !== passwordConfirm){
        alert("Fail! confirm password");
        return;
    }

    var request = {};
    request.userId = email;
    request.userPwd = password;

    $.ajax({
        url: "/user/join",
        type: "POST",
        data: JSON.stringify(request),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
    }).done(function (data) {
        if (data.code == "success") {
            alert("Join Success");
            window.location.href = "/user/login";
        } else {
            var errorMessage = data.message ? "join fail. message : " + data.message : "join fail. retry please";
            alert(errorMessage);
        }
    }).fail(function () {
        alert("error");
    });
};


init = function () {
    document.getElementById("_octopus-join-btn").addEventListener("click", this.join);

};

init();