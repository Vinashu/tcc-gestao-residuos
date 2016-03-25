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
	
	this.listByRole = function(role) {
		return $http.get("users/" + role )
			.then(function(response) {
				return response.data
			}, function(httpError) {
				throw httpError;
			});
	}
	
	this.submitUsuario = function(user) {
		user.password = "1234";
		user.dob = new Date();
		return $http.post("users/", user)
			.then(function(response) {
				return response.data
			}, function(httpError) {
				throw httpError;
			});
	}
	
}]);