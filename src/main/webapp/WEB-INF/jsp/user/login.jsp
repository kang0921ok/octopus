<!DOCTYPE html>
<html lang="ko">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link href="/resources/css/signin.css" rel="stylesheet">

    <title>login</title>
</head>
<body class="text-center">
<form class="form-signin">
    <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
    <label for="_octopus-email" class="sr-only">Email address</label>
    <input type="text" id="_octopus-email" class="form-control" placeholder="Email address" required autofocus>
    <label for="_octopus-password" class="sr-only">Password</label>
    <input type="password" id="_octopus-password" class="form-control" placeholder="Password" required>
    <div class="checkbox mb-3">
        <label>
            <input type="checkbox" id="_octopus-remember-me" value="remember-me"> Remember me
        </label>
    </div>
    <p id="_octopus-login-btn" class="btn btn-lg btn-primary btn-block">Sign in</p>
    <p class="mt-5 mb-3 text-muted">&copy; 2018~</p>
    <div class="join-register">
        <a href="/user/join">Join</a>
    </div>
</form>

<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="/resources/js/login.js"></script>
</body>
</html>