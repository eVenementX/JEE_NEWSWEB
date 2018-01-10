<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: eVenement
  Date: 02.01.2018
  Time: 13:45
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
      <ul class="nav navbar-nav navbar-right">
        <li class="active"><a href="index.jsp">Główna</a></li>
        <li><a href="AddNews">Dodaj</a></li>
          <c:choose>
              <c:when test="${sessionScope.user != null}">
                  <li><a href="logout">Wyloguj się</a></li>
              </c:when>
              <c:otherwise>
                  <li><a href="login">Zaloguj się</a></li>
              </c:otherwise>
          </c:choose>



      </ul>

      </ul>
      <form class="navbar-form navbar-left">
        <div class="form-group" >
            <input type="text" name="search" class="form-control" placeholder="Szukaj">
        </div>
        <button type="submit" class="btn btn-default ">Szukaj</button>

      </form>

    </div>
  </div>
</nav>
<c:if test="${requestScope.newsList != null}" >
    <c:forEach var="news" items="${requestScope.newsList}">
<div class="container">
    <div class="row bs-callout bs-callout-primary">
        <div class="col col-md-1 col-sm-2">
            <a href="${pageContext.request.contextPath}/vote?news_id=${news.id}&vote=VOTE_UP" class="btn btn-block btn-primary btn-success"><span class="glyphicon glyphicon-arrow-up"></span>  </a>
            <div class="well well-sm centered"><c:out value="${news.upVote - news.downVote}"/></div>
            <a href="${pageContext.request.contextPath}/vote?news_id=${news.id}&vote=VOTE_DOWN" class="btn btn-block btn-primary btn-warning"><span class="glyphicon glyphicon-arrow-down"></span>  </a>
        </div>
        <div class="col col-md-11 col-sm-10">
           <h3 class="centered"><c:out value="${news.name}"></c:out></h3>
            <h6><small>Dodane przez:<c:out value="${news.user.username}"></c:out> , Dnia: <fmt:formatDate value="${news.timestamp}" pattern="dd/MM/YYYY"/></small></h6>
            <p>
            <c:out value="${news.description}"></c:out>
            </p>
            <button  class="btn btn-default btn-group-sm">Przejdź do newsa</button>
        </div>
    </div>
</div>
</c:forEach>
</c:if>

<div class="footer"> <strong>infoSwiat developed by DA</strong></div>
<body>
<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
</body>
</html>
