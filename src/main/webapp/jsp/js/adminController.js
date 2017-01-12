var mod = angular.module('app',['ngResource']);
  mod.controller('admin',["$scope","$resource",function($scope,$resource, Res)
  {
	  var all = document.location + "requests";
	  $scope.requests = $resource(all).query();
	  
	  var sign = $resource(document.location + "requests/id/:id/status",{id:"@id"},{save:{method:'PUT'}});

	  /* START Клик подписать */
	  $scope.clickSign = function(id){
		  sign.save({"id":id});
		  location.reload();
	  }
	  /* END Клик подписать */
	  
	  /* START Класс подписи */
	  $scope.suc = function(Request){
			  if(Request.status == "Подписана")
				  return "success";
	  };
	  /* END Класс подписи */

	  /* START Клик по строке формы */
	  $scope.clickRow = function(req){
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

		  var objects = document.location + "requests/objects/id/:id";
		  var objectsParam = {id:"@id"};
		  $scope.objects = $resource(objects,objectsParam).query({id:req.id});
		  $scope.selectedItem = "Изменить";
	  }
	  /* END Клик по строке формы */

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

	  /* START Выход */
	  var ResLogOut = $resource(document.location + '/logout');
	  $scope.logout = function(){
		  ResLogOut.save(function(data){

			  var cookie = "session=0";
			  document.cookie = cookie;
			  location.href = location.href;
		  });
	  }
	  /* END Выход */
	  
	  
}])