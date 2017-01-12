var mod = angular.module('app',['ngResource']);
  mod.controller('user',["$scope","$resource",function($scope,$resource, Res)
  {
	  /* START Статус подписана */
	  $scope.suc = function(Request){

		  if(Request.status == "Подписана")
			  return "success";
	  };
	  /* END Статус подписана */
	  
	  /* START Справочник способ сдачи денежной наличности */
	  var money = document.location + "dictionaries/money";
	  $scope.money = $resource(money).query();
	  /* END Справочник способ сдачи денежной наличности */

	  /* START Справочник переодичность оказания услуг */
	  var period = document.location + "dictionaries/period";
	  $scope.periods = $resource(period).query();
	  /* END Справочник переодичность оказания услуг */
	  
	  /* START Поиск по дате и инициализация даты */
	  var minDate = new Date();
	  minDate.setDate(1);
	  minDate.setMonth(0);
	  $scope.min = minDate;

	  var maxDate = new Date();
	  maxDate.setDate(31);
	  maxDate.setMonth(11);
	  $scope.max = maxDate;
	  $scope.byRange = function (fieldName, minValue, maxValue) {
		  var minDate = Date.parse(minValue);
		  var maxDate = Date.parse(maxValue);
		  var curDate = new Date();
		  
		  return function predicateFunc(item) {
			  curDate.setDate(item[fieldName].slice(1,2));
			  curDate.setMonth(item[fieldName].slice(4,5)-1);
			  curDate.setFullYear(item[fieldName].slice(7,10));
			  return minDate <= Date.parse(curDate) && Date.parse(curDate) <= maxDate;
		  };
	  };
	  /* END Поиск по дате и инициализация даты */
	  
	  /*START Получить заявки */
	  var getCookie = function(name) {
		  var value = "; " + document.cookie;
		  var parts = value.split("; " + name + "=");
		  if (parts.length == 2) return parts.pop().split(";").shift();
	  }
	  var all = document.location + "requests/session/:session";
	  var allParam = {session:"@session"};
	  $scope.requests = $resource(all,allParam).query({session:getCookie("session")});
	  /* END Получить заявки */
	  
	  /* START Выход */
	  var ResLogOut = $resource(document.location + 'logout');
	  $scope.logout = function(){
		  ResLogOut.save(function(data){

			  var cookie = "session=0";
			  document.cookie = cookie;
			  location.href = location.href;
		  });
	  }
	  /* END Выход */

	  /* START Клик по строке формы */
	  $scope.clickRow = function(req){
		  var x = document.getElementById("typeRequest");
		  x.remove(1);
		  //Сохранение выбранной заявки
		  $scope.curReq = req;

		  $scope.bank = req.bank;
		  $scope.inn = parseInt(req.inn);
		  $scope.kpp = req.kpp;
		  $scope.nameOrganization = req.nameOrganization;
		  $scope.ogrn = parseInt(req.ogrn);
		  $scope.nameEmploye = req.nameEmploye;
		  $scope.telephoneEmploye = req.telephoneEmploye;
		  $scope.bankDetails = req.bankDetails;
		  $scope.accountNumber = req.accountNumber;
		  $scope.bik = req.bik;
		  $scope.bankNumber = req.bankNumber;
		  $scope.nameBank = req.nameBank;
		  $scope.swift = req.swift;
		  $scope.status = req.status;

		  var objects = document.location + "requests/objects/id/:id";
		  var objectsParam = {id:"@id"};
		  $scope.objects = $resource(objects,objectsParam).query({id:req.id});
		  $scope.selectedItem = "Изменить";
	  }
	  /* END Клик по строке формы */

	  /* START Клик Новая заявка */
	  $scope.addReq = function(){
		  var nameBank = document.getElementById("nameBank");
		  $scope.bank = nameBank.text;
		  var x = document.getElementById("typeRequest");
		  x.remove(2);
		  x.remove(2);
		  $scope.selectedItem = "Добавить";
	  }
	  /* END Клик Новая заявка */


	  /* START Отправка объекта инкассации */
	  $scope.sendIncas = function(){
	
		  //Объект инкассации
		  $scope.objIncas = {};
		  var tel = $scope.telephoneHead;
		  if(tel[0] == '+')
			  tel=tel.substr(1,tel.length)

		  //Год месяц число начала обслуживания
		  $scope.year = new Date($scope.date).getFullYear();
		  $scope.month = ((new Date($scope.date).getMonth()+1) < 10) ? "0" + (new Date($scope.date).getMonth()+1) : new Date($scope.date).getMonth()+1;
		  $scope.day = (new Date($scope.date).getDate() < 10) ? "0" + new Date($scope.date).getDate() : new Date($scope.date).getDate();

		  //Итоговая дата
		  var resDate = "" + $scope.day + $scope.month + $scope.year;

		  //Часы, минуты сдачи наличных
		  $scope.hour = (new Date($scope.time).getHours()<10) ? "0" + new Date($scope.time).getHours() : new Date($scope.time).getHours();
		  $scope.minute = (new Date($scope.time).getMinutes()<10) ? "0"+new Date($scope.time).getMinutes() : new Date($scope.time).getMinutes();

		  //Создание объекта инкассации
		  $scope.objIncas.time = "" + $scope.hour + $scope.minute;
		  $scope.objIncas.typeOfPutting = $scope.typeOfPutting;
		  $scope.objIncas.periodOfService = $scope.periodOfService;
		  $scope.objIncas.dayOfWeek = $scope.dayOfWeek;
		  $scope.objIncas.countOfMoney = $scope.countOfMoney;
		  $scope.objIncas.codeOfCurrency = $scope.codeOfCurrency;
		  $scope.objIncas.telephoneHead = $scope.telephoneHead;
		  $scope.objIncas.date = resDate;
		  
		  var req = $resource(document.location + "requests/objects");
		  req.save({req_id:$scope.curReq.id, time:$scope.objIncas.time, typeOfPutting:$scope.objIncas.typeOfPutting, periodOfService:$scope.objIncas.periodOfService, dayOfWeek:$scope.objIncas.dayOfWeek, countOfMoney:$scope.objIncas.countOfMoney, codeOfCurrency:$scope.objIncas.codeOfCurrency, telephoneHead:$scope.objIncas.telephoneHead, date:resDate});
	  }

	  /* START Отправка заявки */
	  $scope.submitNewRequest = function() {
		  if ($scope.status == "Подписана") {
			  alert("Заявка уже подписана, изменить данные нельзя")
			  location.href = location.href;
		  }
		  else {
			  var req = $resource(document.location + "requests");
			  var reqUpd = $resource(document.location + "requests/id/:id", {id: "@id"}, {save: {method: 'PUT'}});
			  var reqDel = $resource(document.location + "requests/user/:user/id/:id", {
				  user: "@user",
				  id: "@id"
			  }, {remove: {method: 'DELETE'}});
			  var user = $resource(
				  document.location + 'login/id', {}, {
					  get: {
						  method: 'GET',
						  transformResponse: function (data, headers) {
							  //MESS WITH THE DATA
							  response = {}
							  response.data = data;
							  response.headers = headers();
							  return response;
						  }
					  }
				  }
			  );
			  var userId;

			  user.get(function (data) {
				  userId = data.data;
				  //POST
				  if ($scope.selectedItem == "Добавить")
					  req.save({
						  bank: $scope.bank,
						  inn: $scope.inn,
						  kpp: $scope.kpp,
						  nameOrganization: $scope.nameOrganization,
						  ogrn: $scope.ogrn,
						  nameEmploye: $scope.nameEmploye,
						  telephoneEmploye: $scope.telephoneEmploye,
						  bankDetails: $scope.bankDetails,
						  accountNumber: $scope.accountNumber,
						  bik: $scope.bik,
						  bankNumber: $scope.bankNumber,
						  nameBank: $scope.nameBank,
						  swift: $scope.swift,
						  user: userId
					  });
				  //PUT
				  if ($scope.selectedItem == "Изменить")
					  reqUpd.save({id: $scope.curReq.id}, {
						  bank: $scope.bank,
						  inn: $scope.inn,
						  kpp: $scope.kpp,
						  nameOrganization: $scope.nameOrganization,
						  ogrn: $scope.ogrn,
						  nameEmploye: $scope.nameEmploye,
						  telephoneEmploye: $scope.telephoneEmploye,
						  bankDetails: $scope.bankDetails,
						  accountNumber: $scope.accountNumber,
						  bik: $scope.bik,
						  bankNumber: $scope.bankNumber,
						  nameBank: $scope.nameBank,
						  swift: $scope.swift,
						  user: userId
					  });
				  //DELETE
				  if ($scope.selectedItem == "Удалить")
					  reqDel.remove({user: userId, id: $scope.curReq.id});
				  location.href = location.href;
			  })

		  }
	  }
	  /* END Отправка заявки */
}])