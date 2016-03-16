'use strict';
/**
 * 
 */

app.service('roleSvc', [ '$http', function($http) {
	
	this.getRoles = function() {
		return $http.get("role/")
			.then(
				function(response) {
					return response.data
				}, function(httpError) {
					throw httpError.status;
				});
	}
	
}]);