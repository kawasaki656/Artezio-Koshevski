<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html ng-app="app">
<head>
	<title>Админ панель</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<link rel="stylesheet" href="<c:url value="/jsp/css/style.css" />" media="screen" type="text/css" />
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.0/angular.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.0/angular-resource.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="https://yastatic.net/jquery/1.6.4/jquery.min.js"></script>
	<script src="<c:url value="/jsp/js/adminController.js" />"></script>
	<script>
		function getCookie(name) {
			var value = "; " + document.cookie;
			var parts = value.split("; " + name + "=");
			if (parts.length == 2) return parts.pop().split(";").shift();
		}
		$( document ).ready(function(){
			$("#userName").text(getCookie("name"));
			$("#addReq").click(function(){
				$("#requests").fadeOut("slow", function(){
					$(this).next().fadeIn("fast");
				})
			})
			$("#addObject").click(function(){
				var type = document.getElementById("typeRequest");
				if(type.value != "Добавить")
					$("#InformationRequest").fadeOut("slow", function(){
						$(this).next().fadeIn("fast");
					})
			})
			$("#addObjectForm").submit(function(){
				$("#addObjectForm").fadeOut("slow", function(){
					$(this).prev().fadeIn("fast");
				})
			})
			$("#tableRequests").click(function(){
				$("#requests").fadeOut("slow", function(){
					$(this).next().fadeIn("fast");
				})
			})
		});
	</script>
</head>
<body ng-controller="admin">
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

	<!--	!!!!! Список заявок на инкассацию !!!!!	-->
	<div id="requests" class="col-sm-12" style="display:block">

		<div class="col-sm-4" style="margin-left:-14px">
			<form>
				<div class="form-group">
					<div class="input-group">

						<div class="input-group-addon">
							<h4 class="glyphicon glyphicon-calendar"></h4>
						</div>
						<input type="date" class="form-control" placeholder="С" ng-model="min">
						<input type="date" class="form-control" placeholder="По" ng-model="max">
					</div>
				</div>
			</form>
		</div>


		<table class="table table-hover table-bordered" style="cursor:default">
			<thead>
			<tr>
				<th>
					<a href="" ng-click="sortReverse=!sortReverse">
						Номер заявки
						<span ng-show="!sortReverse" class="glyphicon glyphicon-chevron-down"></span>
						<span ng-show="sortReverse" class="glyphicon glyphicon-chevron-up"></span>
					</a>
				</th>
				<th>Дата заявки</th>
				<th>Статус ЭД</th>
				<th>Тип заявки</th>
				<th>Подпись</th>
			</tr>
			</thead>
			<tbody id="tableRequests">
			<tr  ng-repeat="Request in requests | filter:byRange('date', min, max) | orderBy:'id':sortReverse" ng-class="suc(Request)" ng-click="clickRow(Request)">
				<td>{{Request.id }}</td>
				<td>{{Request.date}}</td>
				<td>{{Request.status}}</td>
				<td>{{Request.type}}</td>
				<td><input class="form-control btn-link" value="Подписать" ng-click="clickSign(Request.id)" style="height:20px;cursor:default"/></td>
			</tr>
			</tbody>
		</table>
	</div>



	<!--	!!!!! Информация и заявке !!!!!	-->
	<div style="display:none" id="InformationRequest">
		<div style="margin-top:10px">
			<div class="panel-group ">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" href="#collapse3">Данные <span class="glyphicon glyphicon-chevron-right"></span></a>
						</h4>
					</div>
					<div id="collapse3" class="panel-collapse collapse ">



						<div class="panel-body">
							<div class="col-sm-6">
								<b>Подразделение банка: </b>{{bank}}
							</div>
							<div class="col-sm-6">
								<b>ИНН/КИО: </b>{{inn}}
							</div>
							<div class="col-sm-6">
								<b>КПП: </b>{{kpp}}
							</div>
							<div class="col-sm-6">
								<b>Полное наименование организации: </b>{{nameOrganization}}
							</div>
							<div class="col-sm-6">
								<b>ОГРН: </b>{{ogrn}}
							</div>
							<div class="col-sm-6">
								<b>Имя уполномоченного сотрудника: </b>{{nameEmploye}}
							</div>
							<div class="col-sm-6">
								<b>Номер телефона уполномоченного: </b>{{telephoneEmploye}}
							</div>
							<div class="col-sm-6">
								<b>Иные реквизиты банка: </b>{{bankDetails}}
							</div>
							<div class="col-sm-6">
								<b>Номер счёта зачисления: </b>{{accountNumber}}
							</div>
							<div class="col-sm-6">
								<b>БИК: </b>{{bik}}
							</div>
							<div class="col-sm-6">
								<b>Номер счёта банка: </b>{{bankNumber}}
							</div>
							<div class="col-sm-6">
								<b>Наименование банка: </b>{{nameBank}}
							</div>
							<div class="col-sm-6">
								<b>SWIFT: </b>{{swift}}
							</div>
						</div>



					</div>
				</div>
			</div>

		</div>


		<div class="panel-group" style="margin-top:10px">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4 class="panel-title">
						<a data-toggle="collapse" href="#collapse4">Объекты <span class="glyphicon glyphicon-chevron-right"></span></a>
					</h4>
				</div>
				<div id="collapse4" class="panel-collapse collapse">



					<div class="panel-body">
						<div class="col-sm-4" ng-repeat="obj in objects" style="margin-bottom:30px">
							<p class="control-label col-sm-12" style="text-align:left;font-size:12pt">Добавленный объект</p>
							<div class="col-sm-12" style="border-top:1px dotted gray;padding-top:10px">
								<p><b>Желательное время сдачи наличных: </b>{{obj.time}}</p>
								<p><b>Способ сдачи денежной наличности: </b>{{obj.typeOfPutting}}</p>
								<p><b>Переодичность оказания услуг: </b>{{obj.periodOfService}}</p>
								<p><b>День недели: </b>{{obj.dayOfWeek}}</p>
								<p><b>Предполагаемый оъем денежной наличности: </b>{{obj.countOfMoney}}</p>
								<p><b>Код валюты: </b>{{obj.codeOfCurrency}}</p>
								<p><b>Руководитель объекта: </b>{{obj.telephoneHead}}</p>
								<p><b>Желательная дата начала обслуживания: </b>{{obj.date}}</p>
							</div>
						</div>
					</div>



				</div>
			</div>
		</div>
		<input class="btn btn-success" value="Подписать" ng-click="clickSign(curReq.id)"/>

	</div>


</div>

</body>
</html>
