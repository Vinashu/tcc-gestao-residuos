'use strict';
/**
 * 
 */

app.service('coletaResiduosSolidosSvc', [ '$http', function($http) {

	this.submitColeta = function(novaColeta) {
		return $http.post("coletaResiduosSolidos/", novaColeta)
		.then(
				function(response) {
					return response.data
				}, function(httpError) {
					throw httpError;
				});
	}
	
	this.findColetasMesAnoLocalUnid = function(search, page) {
		return $http.post("coletaResiduosSolidos/find/" + page, search)
		.then(
				function(response) {
					return response.data
				}, function(httpError) {
					throw httpError;
				});
	
	}
	
	this.findColetasUnid = function(unidId, page) {
		return $http.post("coletaResiduosSolidos/findAll/" + page, unidId)
		.then(
				function(response) {
					return response.data
				}, function(httpError) {
					throw httpError;
				});
	
	}
	
	
	
	
}]);