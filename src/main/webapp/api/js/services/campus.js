'use strict';
/**
 * 
 */

app.service('campusSvc', [ '$http', function($http) {
	
	this.getCampi = function() {
		return $http.get("campus/")
			.then(
				function(response) {
					return response.data
				}, function(httpError) {
					throw httpError.status;
				});
	}
	
}]);