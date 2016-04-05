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
	
	this.findUnidadesCentralizadorasByTipo = function(tipoResiduos) {
		return $http.get("unidCentralizadoras/" + tipoResiduos)
			.then(
				function(response) {
					return response.data
			}, function(httpError) {
					throw httpError.status;
			});
	}
	
	this.findUnidadesCentralizadorasByTipoAndUser = function(tipoResiduos, user) {
		return $http.post("unidCentralizadoras/" + tipoResiduos, user)
			.then(
				function(response) {
					return response.data
			}, function(httpError) {
					throw httpError.status;
			});
	}
	
	
	this.getUnid = function(unidCentralizadoraId) {
		return $http.get("unidCentralizadoras/unid/"+unidCentralizadoraId)
			.then(
					function(response) {
						return response
					}, function(httpError) {	
						throw httpError;
					});
	}
	
	this.deleteUnid = function(unidCentralizadoraId) {
		return $http.delete("unidCentralizadoras/unid/"+unidCentralizadoraId)
			.then(
					function(response) {
						return response
					}, function(httpError) {	
						throw httpError;
					});
	}
	
	
} ]);