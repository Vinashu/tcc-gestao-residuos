'use strict';
/**
 * 
 */

app.service('coletaResiduosQuimicosSvc', [
		'$http',
		function($http) {

			this.submitColeta = function(novaColeta) {
				var materiais = _.clone(novaColeta.materiaisColetados);
				novaColeta.materiaisColetados.forEach(function(item) {
					item.unidadeMedida = item.unidadeMedida.unidade;
				});
				return $http.post("coletaResiduosQuimicos/", novaColeta)
					.then(function(response) {
							response.data.materiaisColetados = materiais;
							return response.data
						}, function(httpError) {
							throw httpError;
						});
			}

			this.findColetasMesAnoLabUnid = function(search, page) {
				return $http.post("coletaResiduosQuimicos/find/" + page, search)
						.then(function(response) {
							return response.data
						}, function(httpError) {
							throw httpError;
						});

			}

			this.findColetasUnid = function(unidId, page) {
				return $http.post("coletaResiduosQuimicos/findAll/" + page, unidId)
					.then(function(response) {
							return response.data
						}, function(httpError) {
							throw httpError;
						});
			}
			
			this.deleteColeta = function(coletaId) {
				return $http.delete("coletaResiduosQuimicos/coleta/" + coletaId)
				.then(function(response) {
					return response.data
				}, function(httpError) {
					throw httpError;
				});
			}
			
			this.findColeta = function(coletaId) {
				return $http.get("coletaResiduosQuimicos/coleta/" + coletaId)
				.then(function(response) {
					return response.data;
				}, function(httpError) {
					throw httpError;
				});
			}

		} ]);