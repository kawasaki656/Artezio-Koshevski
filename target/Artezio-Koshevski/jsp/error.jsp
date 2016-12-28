<%--
  Created by IntelliJ IDEA.
  User: Саша
  Date: 26.12.2016
  Time: 18:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html ng-app="app">
<head>
    <title>Заявка на инкассацию</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.0/angular.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.0/angular-resource.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="<c:url value="/jsp/js/controller.js" />"></script>
    <script src="<c:url value="/jsp/js/resources.js" />"></script>
    <script src="<c:url value="/jsp/js/dictionary.js" />"></script>
</head>
<body ng-controller="control">

<div class="container">
    <!--	!!!!! Авторизация !!!!!	-->
    <nav class="navbar navbar-default" >
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="#">Регистрация заявок на инкассацию</a>
            </div>
            <form class="navbar-form navbar-right" action="http://localhost:8080/" method="post">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Логин" name="login">
                    <input type="password" class="form-control" placeholder="Пароль" name="pass">
                </div>
                <button type="submit" class="btn btn-default" >Войти</button>
            </form>
        </div>
    </nav>

    <div class="alert alert-danger" ><b>Комбинация логин/пароль не определена</b></div>
</div>

<script>
    $(document).ready(function(){
        $('[data-toggle="tooltip"]').tooltip({trigger: "focus",delay: 1000});
    });
</script>

</body>
</html>
