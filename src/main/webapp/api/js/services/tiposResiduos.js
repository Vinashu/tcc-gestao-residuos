/**
 * 
 */

app.service('tiposResiduosSvc', [ '$http', function($http) {
	
	this.getTiposResiduos = function() {
		return $http.get("tiposResiduos/")
			.then(
				function(response) {
					return response.data
				}, function(httpError) {
					throw httpError.status;
				});
	}
	
}]);