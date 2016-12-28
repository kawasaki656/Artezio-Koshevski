//resource {id}/time/{time}/type/{type}/period/{period}/day/{day}/money/{money}/code/{code}/head/{head}/date/{date}
var mod = angular.module('app',['ngResource']);
mod.controller('control',function($scope,$resource,Res,Dic)
{
	$scope.sortReverse=true;

	/*Инициализация объекта инкасации*/

	//Время сдачи наличных
	$scope.time=0;
	$scope.hour = 0;
	$scope.minute = 0;

	$scope.typeOfPutting = "";
	$scope.periodOfService = "";
	$scope.dayOfWeek = "";
	$scope.countOfMoney = 0;
	$scope.codeOfCurrency = "BYN";
	$scope.telephoneHead = "";

	//Дата начала обслуживания
	$scope.date = new Date();
	$scope.year = 0;
	$scope.month = 0;
	$scope.day = 0;

	//Объект инкассации
	$scope.objIncas = {};

	/* Отправка заявки на инкассацию */
	$scope.submitNewRequest = function(){

		//POST
		if($scope.selectedItem == "Добавить"){
			var req = $resource(Res.req,Res.reqParam);
			req.save({id:$scope.lastId.lastId, bank:$scope.bank, inn:$scope.inn, kpp:$scope.kpp, nameOrganization:$scope.nameOrganization, ogrn:$scope.ogrn, nameEmploye:$scope.nameEmploye, telephoneEmploye:$scope.telephoneEmploye, bankDetails:$scope.bankDetails, accountNumber:$scope.accountNumber, bik:$scope.bik, bankNumber:$scope.bankNumber, nameBank:$scope.nameBank, swift:$scope.swift});
			//$scope.requests.push({ id:$scope.lastId.lastId,name:$scope.name, phone: $scope.phone });
			$scope.dispSuccess = "block";
			setTimeout(function(){location.href=location.href},1000);

		}

		//PUT
		if($scope.selectedItem == "Изменить"){

			var req = $resource(Res.req,Res.reqParam,{save:{method:'PUT'}});
			req.save({id:$scope.curReq.id, bank:$scope.bank, inn:$scope.inn, kpp:$scope.kpp, nameOrganization:$scope.nameOrganization, ogrn:$scope.ogrn, nameEmploye:$scope.nameEmploye, telephoneEmploye:$scope.telephoneEmploye, bankDetails:$scope.bankDetails, accountNumber:$scope.accountNumber, bik:$scope.bik, bankNumber:$scope.bankNumber, nameBank:$scope.nameBank, swift:$scope.swift});
			location.href=location.href
		}

		//DELETE
		if($scope.selectedItem == "Удалить"){

			var req = $resource(Res.reqId,Res.reqIdParam);
			req.delete({id:$scope.curReq.id});
			location.href=location.href
		}

	}

	//Добавить заявку
	$scope.addReq = function(){
		$scope.visTableIncas = "none";
		$scope.visNewRequest = "block";
		$scope.selectedItem = "Добавить";
	}

	$scope.changePeriodOfService = function(){
		$scope.dayOfWeek="block";
	}

	//Добавление объекта инкассации
	$scope.addIncas = function(){
		if($scope.curReq.id != undefined && $scope.selectedItem=="Изменить"){
			$scope.dispWarning = "none";
			$scope.visNewRequest = "none";
			$scope.visAddIncas = "block";
		}
		else $scope.dispWarning = "block";

	}

	//Отправка объекта инкассации
	$scope.sendIncas = function(){
		$scope.visAddSuccess = "visible";
		$scope.visNewRequest = "block";
		$scope.visAddIncas = "none";
		switch($scope.typeOfPutting){
			case Dic.type1: $scope.typeOfPutting=1; break;
			case Dic.type2: $scope.typeOfPutting=2; break;
			case Dic.type3: $scope.typeOfPutting=3; break;
			default: $scope.typeOfPutting=0;
		}
		switch($scope.periodOfService){
			case Dic.period1: $scope.periodOfService=1;break;
			case Dic.period2: $scope.periodOfService=2;break;
			case Dic.period3: $scope.periodOfService=3;break;
			case Dic.period4: $scope.periodOfService=4;break;
			case Dic.period5: $scope.periodOfService=5;break;
			case Dic.period6: $scope.periodOfService=6;break;
			default:$scope.periodOfService=0;
		}
		switch($scope.dayOfWeek){
			case Dic.day1: $scope.dayOfWeek=1;break;
			case Dic.day2: $scope.dayOfWeek=2;break;
			case Dic.day3: $scope.dayOfWeek=3;break;
			case Dic.day4: $scope.dayOfWeek=4;break;
			case Dic.day5: $scope.dayOfWeek=5;break;
			case Dic.day6: $scope.dayOfWeek=6;break;
			case Dic.day7: $scope.dayOfWeek=7;break;
			default:$scope.dayOfWeek=0;
		}
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

		//POST объекта
		var req = $resource(Res.obj,Res.objParam);

		req.save({id:$scope.curReq.id, time:$scope.objIncas.time, type:$scope.objIncas.typeOfPutting, period:$scope.objIncas.periodOfService, day:$scope.objIncas.dayOfWeek, money:$scope.objIncas.countOfMoney, code:$scope.objIncas.codeOfCurrency, head:$scope.objIncas.telephoneHead, date:resDate});

	}

	//Инициализация
	$scope.selectedItem = "Изменить";
	$scope.status="Новая";
	$scope.curReq = {};

	//JSON таблицы
	$scope.requests = $resource(Res.all).query();


	//Клик по строке формы
	$scope.clickRow = function(req){
		//Сохранение выбранной заявки
		$scope.curReq = req;
		$scope.visTableIncas = "none";
		$scope.visNewRequest = "block";

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

		$scope.objects = $resource(Res.objects,Res.objectsParam).query({id:req.id});
	}


	//Последний ID заявки
	var last = $resource(Res.lastId);
	$scope.lastId = last.get();


	//Изменение типа заявки
	$scope.update = function(){
		if($scope.selectedItem == "Добавить"){
			$scope.objects = [];
			$scope.bank = "";
			$scope.inn = "";
			$scope.kpp = "";
			$scope.nameOrganization = "";
			$scope.ogrn = "";
			$scope.nameEmploye = "";
			$scope.telephoneEmploye = ""
			$scope.bankDetails = "";
			$scope.accountNumber = "";
			$scope.bik = "";
			$scope.bankNumber = "";
			$scope.nameBank = "";
			$scope.swift = "";
		}
		if($scope.selectedItem == "Удалить"){
			$scope.objects = $resource(Res.objects,Res.objectsParam).query({id:$scope.curReq.id})
			$scope.bank = $scope.curReq.bank;
			$scope.inn = parseInt($scope.curReq.inn);
			$scope.kpp = $scope.curReq.kpp;
			$scope.nameOrganization = $scope.curReq.nameOrganization;
			$scope.ogrn = parseInt($scope.curReq.ogrn);
			$scope.nameEmploye = $scope.curReq.nameEmploye;
			$scope.telephoneEmploye = $scope.curReq.telephoneEmploye;
			$scope.bankDetails = $scope.curReq.bankDetails;
			$scope.accountNumber = $scope.curReq.accountNumber;
			$scope.bik = $scope.curReq.bik;
			$scope.bankNumber = $scope.curReq.bankNumber;
			$scope.nameBank = $scope.curReq.nameBank;
			$scope.swift = $scope.curReq.swift;

		}
		if($scope.selectedItem == "Изменить"){
			$scope.objects = $resource(Res.objects,Res.objectsParam).query({id:$scope.curReq.id})
			$scope.bank = $scope.curReq.bank;
			$scope.inn = parseInt($scope.curReq.inn);
			$scope.kpp = $scope.curReq.kpp;
			$scope.nameOrganization = $scope.curReq.nameOrganization;
			$scope.ogrn = parseInt($scope.curReq.ogrn);
			$scope.nameEmploye = $scope.curReq.nameEmploye;
			$scope.telephoneEmploye = $scope.curReq.telephoneEmploye;
			$scope.bankDetails = $scope.curReq.bankDetails;
			$scope.accountNumber = $scope.curReq.accountNumber;
			$scope.bik = $scope.curReq.bik;
			$scope.bankNumber = $scope.curReq.bankNumber;
			$scope.nameBank = $scope.curReq.nameBank;
			$scope.swift = $scope.curReq.swift;
		}
	}

	//Отображение полей
	$scope.visTableIncas = "table";
	$scope.visNewRequest = "none";
	$scope.visAddIncas = "none";
	$scope.visAddSuccess = "hidden";
	$scope.dayOfWeek = "none";
	$scope.dispSuccess = "none";
	$scope.dispWarning = "none";



});
