'use strict';
/**
 * 
 */

app.service('materialQuimicoSvc', [ '$http', function($http) {
	
	this.getMateriais = function() {
		return $http.get("material/")
			.then(
				function(response) {
					return response.data
				}, function(httpError) {
					throw httpError.status;
				});
	}
	
	this.getTiposMaterial = function() {
		return $http.get("material/tiposMaterial")
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
	
	this.deleteMaterial = function(materialId) {
		return $http.delete("material/" + materialId)
			.then(
				function(response) {
					return response.data
				}, function(httpError) {
					throw httpError.status;
				});
	}
	
}]);