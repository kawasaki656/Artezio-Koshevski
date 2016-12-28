angular.module('app').factory('Res', function() {
	return {
		req:"http://localhost:8080/requests/:id/bank/:bank/inn/:inn/kpp/:kpp/nameOrganization/:nameOrganization/ogrn/:ogrn/nameEmploye/:nameEmploye/telephoneEmploye/:telephoneEmploye/bankDetails/:bankDetails/accountNumber/:accountNumber/bik/:bik/bankNumber/:bankNumber/nameBank/:nameBank/swift/:swift",
		reqParam:{id:"@id", bank:"@bank", inn:"@inn", kpp:"@kpp", nameOrganization:"@nameOrganization", ogrn:"@ogrn", nameEmploye:"@nameEmploye", telephoneEmploye:"@telephoneEmploye", bankDetails:"@bankDetails", accountNumber:"@accountNumber", bik:"@bik", bankNumber:"@bankNumber", nameBank:"@nameBank", swift:"@swift"},
		all:"http://localhost:8080/requests",
		objects:"http://localhost:8080/requests/objects/:id",
		objectsParam:{id:"@id"},
		lastId:"http://localhost:8080/requests/lastid",
		obj:"http://localhost:8080//requests/objects/:id/time/:time/type/:type:/period/:period/day/:day/money/:money/code/:code/head/:head/date/:date",
		objParam:{id:"@id", time:"@time", type:"@type", period:"@period", day:"@day", money:"@money", code:"@code", head:"@head", date:"@date"},
		reqId:"http://localhost:8080/requests/:id",
		reqIdParam:{id:"@id"},
		user:"http://localhost:8080/authorization/user/:user/pass/:pass",
		userParam:{user:"@user", pass:"@pass"}
	}
});