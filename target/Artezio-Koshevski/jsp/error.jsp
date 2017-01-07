<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html ng-app="app">
<head>
    <meta charset="UTF-8">
    <title>Регистрация</title>
    <link rel="stylesheet" href="<c:url value="/jsp/css/style.css" />" media="screen" type="text/css" />
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.0/angular.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.0/angular-resource.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="<c:url value="/jsp/js/userController.js" />"></script>
</head>

<body ng-controller="user">

<div class="alert alert-danger alert-dismissable fade in" style="position:absolute;top:0px;align:middle;width:100%">
    <a href="" class="close" data-dismiss="alert" aria-label="close">&times;</a>
    <strong>Ошибка:</strong> Комбинация логин/пароль не распознана
</div>

<div id="login">
    <div class="flip">
        <div class="form-signup">
            <h1>Регистрация</h1>
            <fieldset>
                <p class="login-msg"></p>
                <form id="login1" ng-submit="registration()">
                    <input type="text" placeholder="Введите Ваш будущий логин..." class="outline" ng-model="login" required />
                    <input type="password" placeholder="Ваш сложный пароль..." ng-model="pass" class="notoutline" required />
                    <input type="password" placeholder="Повторите пароль" class="outline" required />
                    <input type="submit" value="Зарегистрироваться" />

                </form>
                <a href="" class="flipper">Уже зарегистрированы? Войти.</a>
            </fieldset>
        </div>


        <div class="form-login">
            <h1>Авторизация</h1>
            <fieldset>
                <form method="post" ng-submit="authorization()">
                    <input type="text" placeholder="Логин или Email" ng-model="loginIn" required />
                    <input type="password" placeholder="Пароль" ng-model="passIn" required />
                    <input type="submit" value="ВОЙТИ" />
                    <p><a href="" class="flipper">Нет аккаунта? Регистрация.</a><br>
                </form>
            </fieldset>
        </div>
    </div>
</div>

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
<script>
    $('a.flipper').click(function(){
        $('.flip').toggleClass('flipped');
    });
    $('#login1').submit(function(){
        $('.flip').toggleClass('flipped');
    });

</script>

</body>
</html>