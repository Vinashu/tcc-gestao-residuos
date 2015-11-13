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