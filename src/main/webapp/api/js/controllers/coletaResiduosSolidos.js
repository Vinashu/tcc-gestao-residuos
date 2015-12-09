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
			
			$scope.addNovaColeta = function() {
				$mdDialog.show({
					clickOutsideToClose : true,
					scope : $scope,
					preserveScope : true,
					controller : 'novaColetaCtrl',
				
					templateUrl : 'tpl/coletaResiduos/novaColetaSolido.html'
				});
			}
			

			$scope.novaColetaCtrl = function($scope, $mdDialog) {
				$scope.closeDialog = function() {
					$mdDialog.hide();
				}
				
				$scope.novaColeta = {};
				$scope.locais = [];
				
				$scope.carregaLocais = function() {
					localSvc.findLocais().then(
							function(locaisData) {
								$scope.locais = locaisData;
							})
				}
				$scope.carregaLocais();
				
				$scope.salvarColeta = function() {
					//Implementar post
				}
				
			}
			
			if ($stateParams.novaColeta) {
				$scope.addNovaColeta();
			}
			
			

		} ]);