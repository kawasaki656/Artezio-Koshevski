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
	<script src="https://yastatic.net/jquery/1.6.4/jquery.min.js"></script>
	<script src="<c:url value="/jsp/js/regUserController.js" />"></script>
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
				$("#addRequestForm").fadeOut("slow", function(){
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
<body ng-controller="user">
	<span class="glyphicons glyphicons-pencil"></span>
	<div class="container">
		<nav class="navbar navbar-default">
  			<div class="container-fluid" id="menu">

    			<div class="navbar-header" >
		
      				<a class="navbar-brand" href="/" id="rreg"><span class="glyphicon glyphicon-pencil"></span> Регистрация заявок на инкассацию </a>
	  
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


			<table class="table table-hover table-bordered" >
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
				</tr>
				</thead>
				<tbody id="tableRequests">
				<tr ng-repeat="Request in requests | filter:byRange('date', min, max) | orderBy:'id':sortReverse" ng-click="clickRow(Request)">
					<td>{{Request.id }}</td>
					<td>{{Request.date}}</td>
					<td>{{Request.status}}</td>
					<td>{{Request.type}}</td>
				</tr>
				</tbody>

			</table>

			<div class="col-sm-12" style="padding-left:0;padding-right:0">
				<input id="addReq" class="form-control btn-default" style="border:dashed;border-color:#BDBDBD;border-width:1px" type="button" value="НОВАЯ ЗАЯВКА" ng-click="addReq()"/>
			</div>
		</div>


		
<!--	!!!!! Новая заявка на инкассацию !!!!!	-->
		<div style="display:none" id="addRequestForm">
			<form class="form-horizontal"  ng-submit="submitNewRequest()">
			  <div class="page-header form-group">
				  <div class="col-sm-6">
					  <h2 class="control-label col-sm-5" style="text-align:left">Новая заяка</h2>
							<div class="col-sm-5">
							  <select  class="form-control" id="typeRequest" style="margin-top:26px" ng-model="selectedItem" data-toggle="tooltip" data-placement="right" title="Тип заявки, выбираемый при формировании ЭД">
								<option value="Добавить">Добавить</option>
								<option value="Изменить">Изменить</option>
								<option value="Удалить">Удалить</option>
							  </select>

						  </div>
				  </div>
				  <br>
			  </div>


			  <div class="form-group">
					<label class="control-label col-sm-2">Подразделение банка:</label>
					<div class="col-sm-4">
						<select id="nameBank" class="form-control" data-toggle="tooltip" title="Подразделения банка, где обслуживается клиент" ng-model="bank" required>
							<option>БеларусБанк</option>
							<option>БелинвестБанк</option>
							<option>МоскваМинск</option>
						</select>
					</div>

				  <label class="control-label col-sm-2" >ИНН/КИО:</label>
				  <div class="col-sm-4">
					<input class="form-control" type="number" ng-model="inn" />
				  </div>

			  </div>

			  <div class="form-group">
				  <label class="control-label col-sm-2">КПП:</label>
				  <div class="col-sm-4">
					<input type="text" maxlength="9" class="form-control" data-toggle="tooltip" data-placement="right" ng-model="kpp" required/>
				  </div>

				  <label class="control-label col-sm-2">Полное наименование организации:</label>
				  <div class="col-sm-4">
					<input type="text" class="form-control" data-toggle="tooltip" data-placement="right" ng-model="nameOrganization" />
				  </div>
			  </div>

			  <div class="form-group">
					<label class="control-label col-sm-2">ОГРН:</label>
					<div class="col-sm-4">
						<input type="number" class="form-control" ng-model="ogrn" />
					</div>

					<label class="control-label col-sm-2">Имя уполномоченного сотрудника:</label>
						  <div class="col-sm-4">
							<input type="text" class="form-control" data-toggle="tooltip" data-placement="right" title="ФИО, Должность" ng-model="nameEmploye" />
						  </div>
					  </div>

			  <div class="form-group">
				  <label class="control-label col-sm-2">Номер телефона уполномоченного:</label>
				  <div class="col-sm-4">
					<input type="text" class="form-control" data-toggle="tooltip" data-placement="right" title="Номер телефона для связи" ng-model="telephoneEmploye" />
				  </div>

				<label class="control-label col-sm-2">Иные реквизиты банка:</label>
				  <div class="col-sm-4">
					<input type="text" class="form-control" data-toggle="tooltip" data-placement="right" title="Указывается дополнительная информация" ng-model="bankDetails" />
				  </div>
			  </div>
			  <div class="form-group">
				  <label class="control-label col-sm-2" >Номер счёта зачисления:</label>
				  <div class="col-sm-4">
					<input maxlength="20" class="form-control" data-toggle="tooltip" data-placement="right" title="Номер счета клиента в банке, на который требуется зачислить инкассированные денежные средства" ng-model="accountNumber" />
				  </div>

					<label class="control-label col-sm-2">БИК:</label>
				  <div class="col-sm-4">
					<input type="text" maxlength="9" class="form-control" data-toggle="tooltip" data-placement="right" title="БИК банка, в который требуется инкассировать денежные средства" ng-model="bik" />
				  </div>
			  </div>

			  <div class="form-group">
				  <label class="control-label col-sm-2" >Номер счёта банка:</label>
				  <div class="col-sm-4">
					<input maxlength="20" class="form-control" data-toggle="tooltip" data-placement="right" title="Счет и реквизиты банка, на который производится перечисление проинкассированных средств. " ng-model="bankNumber" />
				  </div>

					<label class="control-label col-sm-2">Наименование банка:</label>
				  <div class="col-sm-4">
					<input type="text" class="form-control" data-toggle="tooltip" data-placement="right" title="Наименование банка, в который требуется инкассировать денежные средства" ng-model="nameBank" />
				  </div>
			  </div>

			  <div class="form-group">
				  <label class="control-label col-sm-2" >SWIFT:</label>
				  <div class="col-sm-4">
					<input class="form-control" data-toggle="tooltip" data-placement="right" title="SWIFT банка, в который требуется инкассировать денежные средства" pattern="^([a-zA-Z\d]{8})|([a-zA-Z\d]{12})$" maxlength="12" ng-model="swift" />
				  </div>

				  <label class="control-label col-sm-2" >Объект инкассации:</label>
				  <div class="col-sm-4">
					<button type="button" class="btn btn-link" id="addObject"><span class="glyphicon glyphicon-plus"> </span> Добавить</button>
				  </div>
			  </div>
			  <div class="form-group">
				  <div class="col-sm-2">
					<input class="form-control btn-success" type="submit" />
				  </div>
			  </div>
		  </form>
		</div>

<!--	!!!!! Добавить объект инкассации !!!!!	-->
		<div class="col-sm-12" style="display:none" id="addObjectForm">
			<div class="page-header col-sm-12">
				<div >
					<h2 class="control-label col-sm-5" style="text-align:left">Добавить объект инкассации:</h2>
				</div><br>
			</div>
			<form class="form-horizontal" ng-submit="sendIncas()" >
				<div class="form-group">
					  <label class="control-label col-sm-2">Желательное время сдачи наличных:</label>
					  <div class="col-sm-4">
						<input class="form-control" type="time" ng-model="time" required></input>
					  </div>

					  <label class="control-label col-sm-2">Способ сдачи денежной наличности:</label>
					  <div class="col-sm-4">
							<select class="form-control"  ng-model="typeOfPutting" data-toggle="tooltip">
								<option>По объявлению на взнос наличными</option>
								<option>В инкассаторских сумках</option>
								<option>Через службу инкассации</option>
							</select>
					  </div>
				</div>

				<div class="form-group">
					  <label class="control-label col-sm-2" >Переодичность оказания услуг:</label>
						  <div class="col-sm-4">
							<select class="form-control"  ng-model="periodOfService" >
								<option>Ежедневно</option>
								<option>Рабочие дни</option>
								<option>Через день</option>
								<option>День недели</option>
								<option>По заявке</option>
								<option>По звонку</option>
							</select>
						  </div>

					  <label class="control-label col-sm-2" >День недели:</label>
						  <div class="col-sm-4">
							<select class="form-control"  ng-model="dayOfWeek" >
								<option>Понедельник</option>
								<option>Вторник</option>
								<option>Среда</option>
								<option>Четверг</option>
								<option>Пятница</option>
								<option>Суббота</option>
								<option>Воскресенье</option>
							</select>
						  </div>
				</div>

				<div class="form-group">
					  <label class="control-label col-sm-2" >Предполагаемый оъем денежной наличности:</label>
					  <div class="col-sm-4">
						<input class="form-control" data-toggle="tooltip" data-placement="right" title="Сумма предназначенная клиентом к инкассации. М.б. как в рублях так и в ин. валюте" type="number" ng-model="countOfMoney" required/>
					  </div>

					  <label class="control-label col-sm-2">Код валюты:</label>
					  <div class="col-sm-4">
						<input type="text" class="form-control" data-toggle="tooltip" data-placement="right" title="Буквенный код валюты (в соответствии со стандартом ISO 4217)" maxlength="3" ng-model="codeOfCurrency" required/>
					  </div>
				</div>

				<div class="form-group">
					  <label class="control-label col-sm-2" >Руководитель объекта:</label>
					  <div class="col-sm-4">
						<input class="form-control" data-toggle="tooltip" data-placement="right" title="Контактный телефон руководителя точки юр. лица, по которой подразделение инкассации производит инкассацию"  ng-model="telephoneHead" required/>
					  </div>

					  <label class="control-label col-sm-2">Желательная дата начала обслуживания:</label>
					  <div class="col-sm-4">
						<input type="date" class="form-control" data-toggle="tooltip" data-placement="right" title="ДД.ММ.ГГГГ" ng-model="date" required/>
					  </div>
				</div>

				<div class="form-group">
					  <div class="col-sm-2">
						<input class="form-control btn-success" type="submit" value="Добавить" />
					  </div>
				</div>

				<div class="form-group" style="margin-top:10px">
					<div class="panel-group">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a data-toggle="collapse" href="#collapse1">Добавленные объекты <span class="glyphicon glyphicon-chevron-right"></span></a>
								</h4>
							</div>
							<div id="collapse1" class="panel-collapse collapse">
								<div class="panel-body">
									<p ng-repeat="obj in objects"><b>Руководитель: </b> {{obj.telephoneHead}}, <b>примерный объем сдачи: </b> {{obj.countOfMoney}}, <b>с переодичностью: </b> {{obj.periodOfService}}</p>
								</div>
							</div>
						</div>
					</div>

				</div>


			</form>
		</div>

	</div>

</body>
</html>
