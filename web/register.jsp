<%--
  Created by IntelliJ IDEA.
  User: eVenement
  Date: 06.01.2018
  Time: 18:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Informacje ze świata</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/styles.css" type="text/css" rel="stylesheet">
</head>
<nav class="navbar navbar-fixed-top navbar-inverse">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <a class="navbar-brand" href="index.jsp">InfoSwiat</a>
        </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="active"><a href="index.jsp">Główna</a></li>
                <li><a href="#">Dodaj</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">


                <li><a href="login.jsp">Zaloguj</a></li>

            </ul>
            <form class="navbar-form navbar-left">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Szukaj">
                </div>
                <button type="submit" class="btn btn-default">Szukaj</button>
            </form>

        </div>
    </div>
</nav>
<div class="container">
    <div class="col-sm-6 col-md-4 col-md-offset-4">
        <form class="form-signin" action="#" method="post">
            <h2 class="form-signin-heading">Rejestracja</h2>
            <input type="email" name="inputEmail" class="form-control" placeholder="Adres email" required autofocus>
            <input type="text" name="inputUsername" class="form-control" placeholder="Nazwa użytkoniwka" required autofocus>
            <input type="password" name="inputPassword" class="form-control" placeholder="Password" required>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Zarejestruj się</button>
        </form>
    </div>
</div>
<div class="footer"> <strong>infoSwiat developed by DA</strong></div>
<body>
<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
</body>
</html>
