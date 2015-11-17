'use strict';
/**
 * 
 */

app.service('labSvc', [ '$http', function($http) {

	this.saveLab = function(lab) {
		return $http.post("laboratorios/", lab)
			.then(
					function(response) {
						return response.data
					}, function(httpError) {
						throw httpError.status;
					});
	}
	
	this.list = function(page) {
		return $http.get("laboratorios/"+page)
			.then(function(response) {
				return response.data
			}, function(httpError) {
				throw httpError;
			});
	}
	
	this.getLab = function(labId) {
		return $http.get("laboratorios/lab/"+labId)
			.then(
					function(response) {
						return response
					}, function(httpError) {	
						throw httpError;
					});
	}
	
	this.getCampi = function() {
		return $http.get("campus/")
			.then(
				function(response) {
					return response.data
				}, function(httpError) {
					throw httpError.status;
				});
	}
	
	
	
} ]);