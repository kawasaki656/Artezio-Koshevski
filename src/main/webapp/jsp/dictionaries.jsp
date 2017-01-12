<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html ng-app="app">
<head>
    <title>Заявка на инкассацию</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<link rel="stylesheet" href="<c:url value="/jsp/css/style.css" />" media="screen" type="text/css" />
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.0/angular.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.0/angular-resource.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="<c:url value="/jsp/js/dictionariesController.js" />"></script>
</head>
<body ng-controller="dictionaries">
	
	<div class="container">

		<nav class="navbar navbar-default">
			<div class="container-fluid" id="menu">

				<div class="navbar-header" style="padding-top: 15px">

					<a class="rreg" href="/"><span class="glyphicon glyphicon-pencil" style="margin-right: 10px"></span> Заявки </a>

					<a class="rreg" href="/dictionaries" style="margin-left: 10px"> Cправочники </a>

				</div>
				<form class="navbar-form navbar-right" ng-submit="logout()">
					<span style="color:white;font-size:9pt" id="userName"></span>
					<button type="submit" class="btn btn-link" style="color:white">Выход</button>
				</form>
			</div>
		</nav>

<div class="col-sm-6">			
	<div class="panel panel-success">
	  <div class="panel-heading">
		<a class="panel-title" data-toggle="collapse" href="#collapse1"><b>Инкассаторская услуга</b></a>
	  </div>
	  <div id="collapse1" class="panel-collapse collapse">
		  <div class="panel-body">
			<div class="input-group" ng-repeat="service in services" style="margin-bottom:5px">
			<span class="input-group-addon">{{service.id}}</span>
			<input ng-model="service.value" type="text" class="form-control" name="msg" placeholder="Изменить" ng-blur="blurService(service.id,service.value)" >
			</div>
			
			<br>
			
			<div class="input-group">
				<form class="form-horizontal">
					<div class="form-group">
						<div class="col-sm-8">
							<input type="text" class="form-control" ng-model="valueService" >
						</div>
						<button type="button" class="btn btn-link" ng-click="addService()">Добавить</button>
					</div>
				</form>
			</div>
		  </div>
	  </div>
	</div>
</div>

<div class="col-sm-6">			
	<div class="panel panel-success">
	  <div class="panel-heading">
		<a class="panel-title" data-toggle="collapse" href="#collapse2"><b>Тип заявки</b></a>
	  </div>
	  <div id="collapse2" class="panel-collapse collapse">
		  <div class="panel-body">
			<div class="input-group" ng-repeat="type in types" style="margin-bottom:5px">
			<span class="input-group-addon">{{type.id}}</span>
			<input ng-model="type.value" type="text" class="form-control" name="msg" placeholder="Изменить" ng-blur="blurType(type.id,type.value)">
			</div>
			
			<br>
			
			<div class="input-group">
				<form class="form-horizontal">
					<div class="form-group">
						<div class="col-sm-8">
							<input type="text" class="form-control" ng-model="valueType">
						</div>
						<button type="button" class="btn btn-link" ng-click="addType()">Добавить</button>
					</div>
				</form>
			</div>
		  </div>
	  </div>
	</div>
</div>

<div class="col-sm-6">			
	<div class="panel panel-success">
	  <div class="panel-heading">
		<a class="panel-title" data-toggle="collapse" href="#collapse3"><b>Способ сдачи денежной наличности</b></a>
	  </div>
	  <div id="collapse3" class="panel-collapse collapse">
		  <div class="panel-body">
			<div class="input-group" ng-repeat="cent in money" style="margin-bottom:5px">
			<span class="input-group-addon">{{cent.id}}</span>
			<input ng-model="cent.value" type="text" class="form-control" name="msg" placeholder="Изменить" ng-blur="blurMoney(cent.id,cent.value)">
			</div>
			
			<br>
			
			<div class="input-group">
				<form class="form-horizontal">
					<div class="form-group">
						<div class="col-sm-8">
							<input type="text" class="form-control" ng-model="valueMoney">
						</div>
						<button type="button" class="btn btn-link" ng-click="addMoney()">Добавить</button>
					</div>
				</form>
			</div>
		  </div>
	  </div>
	</div>
</div>

<div class="col-sm-6">			
	<div class="panel panel-success">
	  <div class="panel-heading">
		<a class="panel-title" data-toggle="collapse" href="#collapse4"><b>Переодичность оказания инкассаторских услуг</b></a>
	  </div>
	  <div id="collapse4" class="panel-collapse collapse">
		  <div class="panel-body">
			<div class="input-group" ng-repeat="period in periods" style="margin-bottom:5px">
			<span class="input-group-addon">{{period.id}}</span>
			<input ng-model="period.value" type="text" class="form-control" name="msg" placeholder="Изменить" ng-blur="blurPeriod(period.id,period.value)">
			</div>
			
			<br>
			
			<div class="input-group">
				<form class="form-horizontal">
					<div class="form-group">
						<div class="col-sm-8">
							<input type="text" class="form-control" ng-model="valuePeriod">
						</div>
						<button type="button" class="btn btn-link" ng-click="addPeriod()">Добавить</button>
					</div>
				</form>
			</div>
		  </div>
	  </div>
	</div>
</div>



<script>
$(document).ready(function(){
    $('[data-toggle="tooltip"]').tooltip({trigger: "focus",delay: 1000});   
});
</script>

</body>
</html>
