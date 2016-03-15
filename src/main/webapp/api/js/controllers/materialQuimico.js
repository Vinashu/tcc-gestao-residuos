'use strict';
/**
 * 
 */
app.controller('materialQuimicoCtrl', [
        'materialQuimicoSvc',                               
		'$scope',
		'$http',
		'$state',
		'$stateParams',
		'Flash',
		'$mdDialog',
		'$location',
		function(materialQuimicoSvc ,$scope, $http, $state, $stateParams, Flash, $mdDialog,
				$location) {
			
		$scope.novoMaterial = {};
		$scope.materiais = [];
		$scope.tiposMaterial = [];
		$scope.isCreating = false;
		
		$scope.carregaMateriais = function() {
			materialQuimicoSvc.getMateriais()
				.then(function(materiaisData) {
					$scope.materiais = materiaisData;
				});
		}
		
		$scope.carregaTiposMaterial = function() {
			materialQuimicoSvc.getTiposMaterial()
				.then(function(tiposData) {
					$scope.tiposMaterial = tiposData;
				});
		}
		
		$scope.carregaMateriais();
		$scope.carregaTiposMaterial();
		
		
		$scope.novoMaterialCtrl = function($scope, $mdDialog) {
			$scope.submitMaterial = function() {
				materialQuimicoSvc.saveMaterial($scope.novoMaterial)
					.then(function(novoMaterialData) {
						Flash.create('success', 'Material '
													+ novoMaterialData.descricao
													+ ' salvo com sucesso',
													'custom-class');
						$scope.closeDialog();
						$scope.carregaMateriais();
					}, function(error) {
						Flash.create('error', 'Não foi possível salvar o material '
								+ $scope.novoMaterial.descricao + ". "
								+ error + '. Verifique os campos e tente novamente',
								'custom-class');
					});
			}
			

			$scope.closeDialog = function() {
				$mdDialog.hide();
				$scope.novoMaterial = {};
			}
			
		}
		
		$scope.edit = function(materialEdit) {
			$scope.novoMaterial = materialEdit;
			$mdDialog
					.show({
						clickOutsideToClose : true,
						scope : $scope,
						preserveScope : true,
						
						controller : 'novoMaterialCtrl',
						
						templateUrl : 'tpl/materiais/novoMaterial.html'
					});
			
		}
		
		$scope.addMaterial = function() {
			$scope.novoMaterial = {};
			$mdDialog
					.show({
						clickOutsideToClose : true,
						scope : $scope,
						preserveScope : true,
						
						controller : 'novoMaterialCtrl',
						templateUrl : 'tpl/materiais/novoMaterial.html'
					});
			
		}
		
		
		}]);
