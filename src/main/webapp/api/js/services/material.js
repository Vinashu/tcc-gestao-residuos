'use strict';
/**
 * 
 */

app.service('materialSvc', [ '$http', function($http) {
	
	this.getMateriais = function() {
		return $http.get("material/")
			.then(
				function(response) {
					return response.data
				}, function(httpError) {
					throw httpError.status;
				});
	}
	
	this.saveMaterial = function(material) {
		return $http.post("material/", material)
			.then(
				function(response) {
					return response.data
				}, function(httpError) {
					throw httpError.status;
				});
	}
	
}]);