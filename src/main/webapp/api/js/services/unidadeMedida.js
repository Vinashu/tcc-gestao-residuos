'use strict';
/**
 * 
 */

app.service('unidadeMedidaSvc', [ '$http', function($http) {
	
	this.getUnidadesMedida = function() {
		return $http.get("unidadeMedida/")
			.then(
				function(response) {
					var jsonUnids = [];
					response.data.forEach(function(unid) {
						jsonUnids.push(JSON.parse(unid));
					})
					return jsonUnids;
				}, function(httpError) {
					throw httpError.status;
				});
	}
	
	this.getUnidadeMedidaInfos = function(unid) {
		return $http.get("unidadeMedida/" + unid)
			.then(
				function(response) {
					return response.data;
			}, function(httpError) {
					throw httpError.status;
			});
	}
}]);	