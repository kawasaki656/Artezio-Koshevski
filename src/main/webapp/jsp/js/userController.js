var mod = angular.module('app',['ngResource']);
  mod.controller('user',["$scope","$resource",function($scope,$resource, Res)
  {
	  var ResReg = $resource(document.location + "/registration");
	  var ResLogin = $resource(
          document.location + '/login', {}, {
        save: {
            method: 'POST',
            transformResponse: function(data, headers){
                response = {}
                response.data = data;
                response.headers = headers();
                return response;
            }
        }
    }

    );
      
      /* START Авторизация */
	  $scope.registration = function(){
          if($scope.pass == $scope.pass2)
			ResReg.save({"login":$scope.login,"pass":$scope.pass});
	  }
      /* END Авторизация */

	  /* START Авторизация */
	  $scope.authorization = function(){
		  var now = Date.now() + "";
		  var res = ResLogin.save({"login":$scope.loginIn,"pass":$scope.passIn,"session":now},function(data){
			  //if(data.data == "true"){

			  var cookie = "session=" + now;
			  document.cookie = cookie;
			  document.cookie = "name="+$scope.loginIn;

			  document.location.reload();

			  //
		  });
	  }
	  /* END Авторизация */

  }])