'use strict';
/**
 * 
 */

app.service('userSvc', [ '$http', function($http) {
	
	this.list = function() {
		return $http.get("users/")
			.then(function(response) {
				return response.data
			}, function(httpError) {
				throw httpError;
			});
	}
	
	this.submitUsuario = function(user) {
		return $http.post("users/", user)
			.then(function(response) {
				return response.data
			}, function(httpError) {
				throw httpError;
			});
	}
	
}]);