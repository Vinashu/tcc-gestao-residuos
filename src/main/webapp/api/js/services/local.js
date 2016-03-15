'use strict';
/**
 * 
 */

app.service('localSvc', [ '$http', function($http) {
	
	this.findLocais = function() {
		return $http.get("locais/")
			.then(
				function(response) {
					return response.data
				}, function(httpError) {
					throw httpError.status;
				});
	}
	
	this.findLocaisColetaServicosSaude = function() {
		return $http.get("locais/locaisColetaServicosSaude")
			.then(
				function(response) {
					return response.data
				}, function(httpError) {
					throw httpError.status;
				});
	}
	
	this.findLocaisColetaSolidos = function() {
		return $http.get("locais/locaisColetaSolidos")
			.then(
				function(response) {
					return response.data
				}, function(httpError) {
					throw httpError.status;
				});
	}
	
	this.findLocaisColeta= function() {
		return $http.get("locais/locaisColeta")
			.then(
				function(response) {
					return response.data
				}, function(httpError) {
					throw httpError.status;
				});
	}
	
	this.listNotLab = function() {
		return $http.get("locais/notLab")
			.then(
				function(response) {
					return response.data
				}, function(httpError) {
					throw httpError.status;
				});
	}
	
	this.createLocalColeta = function(localColeta) {
		return $http.post("locais/localColeta", localColeta)
			.then(
				function(response) {
					return response.data
				}, function(httpError) {
					throw httpError.status;
				});
	}
}]);