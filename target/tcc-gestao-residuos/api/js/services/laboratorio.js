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
	
	this.listPagenation = function(page) {
		return $http.get("laboratorios/"+page)
			.then(function(response) {
				return response.data
			}, function(httpError) {
				throw httpError;
			});
	}
	
	this.list = function() {
		return $http.get("laboratorios/")
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
	
} ]);