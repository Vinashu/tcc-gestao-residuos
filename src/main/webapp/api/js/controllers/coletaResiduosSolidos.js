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
			
		
			$scope.findColetasUnid = function() {
				coletaResiduosSolidosSvc.findColetasUnid($scope.search.mes, $scope.search.ano, $scope.search.local, $scope.unidCentralizadora)
					.then(function(coletasData) {
						$scope.coletas = coletasData;
					}, function(error) {
						Flash.create('error',
								"Erro ao pesquisar coletas: "
										+ error, "custom-class");
					})
			}
			
			$scope.novaColeta = {};
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
				
				$scope.novaColeta.unidadeCentralizadora = $scope.unidCentralizadora;
				$scope.locais = [];
				
				$scope.carregaLocais = function() {
					localSvc.findLocais().then(
							function(locaisData) {
								$scope.locais = locaisData;
							})
				}
				$scope.carregaLocais();
				
				$scope.salvarColeta = function() {
					coletaResiduosSolidosSvc.submitColeta($scope.novaColeta)
						.then(function(savedColeta) {
							Flash.create('success', 'Coleta '
									+ savedColeta.OS
									+ ' criada com sucesso',
									'custom-class');
							$mdDialog.hide();
							$scope.novaColeta = {};
						}, function(error) {
							
						});
				}
				
			}
			
			if ($stateParams.novaColeta) {
				$scope.addNovaColeta();
			}
			
			

		} ]);