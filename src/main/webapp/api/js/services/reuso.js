'use strict';
/**
 * 
 */

app.service('reusoSvc', [ '$http', function($http) {

	this.addMaterialReuso = function(reuso) {
		return $http.post("reusos/", reuso)
		.then(
				function(response) {
					return response.data
				}, function(httpError) {
					throw httpError;
				});
	}
	
	this.findReusosMaterialCampus = function(search, page) {
		return $http.post("reusos/find/" + page, search)
		.then(
				function(response) {
					return response.data
				}, function(httpError) {
					throw httpError;
				});
	
	}
	
	this.findReusos = function(page) {
		return $http.get("reusos/findAll/" + page)
		.then(
				function(response) {
					return response.data
				}, function(httpError) {
					throw httpError;
				});
	}
	
	this.getMateriaisDisp = function() {
		return $http.get("reusos/findMateriais")
		.then(
				function(response) {
					return response.data
				}, function(httpError) {
					throw httpError;
				});
	}
	
	this.getReusoByMaterialColetadoId = function(materialColetadoId) {
		return $http.get("reusos/reuso/" + materialColetadoId)
		.then(
				function(response) {
					return response.data
				}, function(httpError) {
					throw httpError;
				});
	
	}
	
	this.isMaterialDisponivelReuso = function(materialColetadoId) {
		return $http.get("reusos/reuso/" + materialColetadoId)
		.then(
				function(response) {
					return _.isEmpty(response.data);
				}, function(httpError) {
					throw httpError;
				});
	
	}
	
	this.deleteReusoByMaterialColetadoId = function(materialColetadoId) {
		return $http.delete("reusos/reuso/" + materialColetadoId)
		.then(
				function(response) {
					return response.data
				}, function(httpError) {
					throw httpError;
				});
	
	}
	
	this.deleteReuso = function(reusoId) {
		return $http.delete("reusos/" + reusoId)
		.then(function(response) {
			return response.data
		}, function(httpError) {
			throw httpError;
		});
	}
	
	
}]);