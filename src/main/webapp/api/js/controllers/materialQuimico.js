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
		
		$scope.submitMaterial = function() {
			materialQuimicoSvc.novoMaterial($scope.material)
				.then(function(novoMaterialData) {
					Flash.create('success', 'Material '
												+ materialData.descricao
												+ ' criado com sucesso',
												'custom-class');
				}, function(error) {
					Flash.create('error', 'Não foi possível criar o material '
							+ $scope.material.descricao + ". "
							+ error + '. Verifique os campos e tente novamente',
							'custom-class');
				});
		}
		
		
		
		
		}]);
