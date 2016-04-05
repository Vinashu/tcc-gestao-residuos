'use strict';
/**
 * 
 */

app.service('materialColetadoSvc', [ '$http', function($http) {

	this.updateMaterialColetado = function(materialColetado) {
		materialColetado.unidadeMedida = materialColetado.unidadeMedida.unidade;
		return $http.post("materiaisColetados/", materialColetado)
			.then(
				function(response) {
					return response.data
				}, function(httpError) {
					throw httpError.status;
				});
	}
}]);