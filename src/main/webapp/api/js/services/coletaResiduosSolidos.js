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
	
	this.findColetasUnid = function(mes, ano, local, unidCentralizadora) {
		var params = { mes : mes, ano: ano, local: local, unidCentralizadora : unidCentralizadora};
		return $http.post("coletaResiduosSolidos/find", params)
		.then(
				function(response) {
					return response.data
				}, function(httpError) {
					throw httpError;
				});
	
	}
	
}]);