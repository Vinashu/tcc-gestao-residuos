'use strict';
/**
 * 
 */
app.controller('coletaResiduosSolidosCtrl', [
		'$scope',
		'unidCentralizadoraSvc',
		'localSvc',
		'coletaResiduosSolidosSvc',
		'$state',
		'$stateParams',
		'Flash',
		'$mdDialog',
		'$location',
		'_',
		function($scope, unidCentralizadoraSvc, localSvc,
				coletaResiduosSolidosSvc, $state, $stateParams, Flash,
				$mdDialog, $location, _) {

			$scope.unidCentralizadora = {};
			$scope.unidadesCentralizadoras = [];
			
			$scope.carregaUnidadesCentralizadoras = function() {
				unidCentralizadoraSvc.findUnidadesCentralizadoras().then(
						function(unidades) {
							$scope.unidadesCentralizadoras = unidades;
							$scope.unidCentralizadora = _.first(unidades);
						},
						function(error) {
							Flash.create('error',
									"Não foi possível carregar as Unidades Centralizadoras: "
											+ error, "custom-class");
						})
			}
			$scope.carregaUnidadesCentralizadoras();
			
			

		} ]);