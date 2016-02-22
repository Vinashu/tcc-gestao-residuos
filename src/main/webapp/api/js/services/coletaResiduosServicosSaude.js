'use strict';
/**
 * 
 */

app.service('coletaResiduosServicosSaudeSvc', [ '$http', function($http) {

	this.submitColeta = function(novaColeta) {
		return $http.post("coletaResiduosServicosSaude/", novaColeta)
		.then(
				function(response) {
					return response.data
				}, function(httpError) {
					throw httpError;
				});
	}
	
	this.findColetasMesAnoLocalUnid = function(search, page) {
		return $http.post("coletaResiduosServicosSaude/find/" + page, search)
		.then(
				function(response) {
					return response.data
				}, function(httpError) {
					throw httpError;
				});
	
	}
	
	this.findColetasUnid = function(unidId, page) {
		return $http.post("coletaResiduosServicosSaude/findAll/" + page, unidId)
		.then(
				function(response) {
					return response.data
				}, function(httpError) {
					throw httpError;
				});
	
	}
	
	this.deleteColeta = function(coletaId) {
		return $http.delete("coletaResiduosServicosSaude/coleta/" + coletaId)
		.then(function(response) {
			return response.data
		}, function(httpError) {
			throw httpError;
		});
	}
	
	
}]);