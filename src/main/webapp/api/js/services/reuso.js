'use strict';
/**
 * 
 */

app.service('reusoSvc', [ '$http', function($http) {

	this.submitColeta = function(novaColeta) {
		return $http.post("reuso/", novaColeta)
		.then(
				function(response) {
					return response.data
				}, function(httpError) {
					throw httpError;
				});
	}
	
	this.findColetasMesAnoLocalUnid = function(search, page) {
		return $http.post("reuso/find/" + page, search)
		.then(
				function(response) {
					return response.data
				}, function(httpError) {
					throw httpError;
				});
	
	}
	
	this.findColetasUnid = function(unidId, page) {
		return $http.post("reuso/findAll/" + page, unidId)
		.then(
				function(response) {
					return response.data
				}, function(httpError) {
					throw httpError;
				});
	
	}
	
	this.deleteColeta = function(coletaId) {
		return $http.delete("reuso/coleta/" + coletaId)
		.then(function(response) {
			return response.data
		}, function(httpError) {
			throw httpError;
		});
	}
	
	
}]);