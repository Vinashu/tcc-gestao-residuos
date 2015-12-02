'use strict';
/**
 * 
 */

app.service('unidCentralizadoraSvc', [ '$http', function($http) {
	
	this.saveUnidCentralizadora = function(unidCentralizadora) {
		return $http.post("unidCentralizadoras/", unidCentralizadora)
			.then(
					function(response) {
						return response.data
					}, function(httpError) {
						throw httpError.status;
					});
	}
	
	this.findUnidadesCentralizadoras = function() {
		return $http.get("unidCentralizadoras/")
			.then(
				function(response) {
					return response.data
			}, function(httpError) {
					throw httpError.status;
			});
	}
	
	
} ]);