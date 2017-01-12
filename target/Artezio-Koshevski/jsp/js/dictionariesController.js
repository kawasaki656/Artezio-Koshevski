var mod = angular.module('app',['ngResource']);
  mod.controller('dictionaries',["$scope","$resource",function($scope,$resource, Res){

	  var service = $resource(document.location + "/services");
	  var servicePut = $resource(document.location + "/services",{},{save:{method:'PUT'}});
	  var type = $resource(document.location + "/typerequests");
	  var typePut = $resource(document.location + "/typerequests",{},{save:{method:'PUT'}});
	  var money = $resource(document.location + "/money");
	  var moneyPut = $resource(document.location + "/money",{},{save:{method:'PUT'}});
	  var period = $resource(document.location + "/period");
	  var periodPut = $resource(document.location + "/period",{},{save:{method:'PUT'}});
	  var ResLogOut = $resource(document.URL.substring(0,document.URL.length-13) + '/logout');
	  
	 /* START Service */
	 $scope.services = service.query();
	  
	 $scope.addService = function(){
		 service.save({"id":0,"value":$scope.valueService},function(){
			location.reload();
		 });
	 }
	 
	 $scope.blurService = function(id,upd){
		 servicePut.save({"id":id,"value":upd})
		 }
	/* END Service */ 
	
	/* START Type */
	 $scope.types = type.query();
	 
	 $scope.addType = function(){
		 type.save({"id":0,"value":$scope.valueType},function(){
			location.reload(); 
		 });
	 }
	 
	 $scope.blurType = function(id,upd){
		 typePut.save({"id":id,"value":upd})
		 }
	/* END Type */ 
	
	/* START Send Money */

	 $scope.money = money.query();
	  
	 $scope.addMoney = function(){
		 money.save({"id":0,"value":$scope.valueMoney},function(){
			location.reload();
		 });
	 }
	 
	 $scope.blurMoney = function(id,upd){
		 moneyPut.save({"id":id,"value":upd})
		 }
	/* END Send Money */ 
	
	/* START Period */
	 $scope.periods = period.query();
	 
	 $scope.addPeriod = function(){
		 period.save({"id":0,"value":$scope.valuePeriod},function(){
			location.reload();
		 });
	 }
	 
	 $scope.blurPeriod = function(id,upd){
		 periodPut.save({"id":id,"value":upd})
		 }
	/* END Period */

	  /* START Выход */
	  $scope.logout = function(){
		  ResLogOut.save(function(data){

			  var cookie = "session=0";
			  document.cookie = cookie;
			  location.href="http://"+document.domain;
			  
		  });
	  }
	  /* END Выход */
		 
  }])