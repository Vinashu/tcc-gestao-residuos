'use strict';
/**
 * 
 */

app.service('localSvc', [ '$http', function($http) {
	
	this.findLocais = function() {
		return $http.get("locais/")
			.then(
				function(response) {
					return response.data
				}, function(httpError) {
					throw httpError.status;
				});
	}
	
	this.listNotLab = function() {
		return $http.get("locais/notLab")
			.then(
				function(response) {
					return response.data
				}, function(httpError) {
					throw httpError.status;
				});
	}
}]);