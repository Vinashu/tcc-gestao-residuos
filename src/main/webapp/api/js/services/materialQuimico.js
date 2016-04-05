'use strict';
/**
 * 
 */

app.service('materialQuimicoSvc', [ '$http', function($http) {
	
	this.getMateriais = function() {
		return $http.get("materiais/")
			.then(
				function(response) {
					return response.data
				}, function(httpError) {
					throw httpError.status;
				});
	}
	
	this.getMaterialByDescricao = function(descricao) {
		return $http.get("materiais/material/" + descricao)
			.then(
				function(response) {
					return response.data
				}, function(httpError) {
					throw httpError.status;
				});
	}
	
	this.getMaterialDiversos = function() {
		return $http.get("materiais/material/materialDiverso")
			.then(
				function(response) {
					return response.data
				}, function(httpError) {
					throw httpError.status;
				});
	}
	this.getTiposMaterial = function() {
		return $http.get("materiais/tiposMaterial")
			.then(
				function(response) {
					return response.data
				}, function(httpError) {
					throw httpError.status;
				});
	}
	
	this.saveMaterial = function(material) {
		return $http.post("materiais/", material)
			.then(
				function(response) {
					return response.data
				}, function(httpError) {
					throw httpError.status;
				});
	}
	
	this.deleteMaterial = function(materialId) {
		return $http.delete("materiais/" + materialId)
			.then(
				function(response) {
					return response.data
				}, function(httpError) {
					throw httpError.status;
				});
	}
	
}]);