'use strict';
/**
 * 
 */
app.controller('locaisColetaCtrl', [
		'$scope',
		'campusSvc',
		'$state',
		'$stateParams',
		'Flash',
		'$mdDialog',
		'$location',
		'tiposResiduosSvc',
		'localSvc',
		function($scope, campusSvc, $state, $stateParams, Flash, $mdDialog,	$location, tiposResiduosSvc, localSvc) {
			
			$scope.locaisColeta = [];
			$scope.novoLocalColeta = {};
			$scope.novoLocalColeta.tipoResiduo = {};
			$scope.campi = [];
			$scope.tiposResiduos = [];
			
			
			$scope.getCampi = function() {
				campusSvc.getCampi().then(function(campiData) {
					$scope.campi = campiData;
				});
			}
			$scope.getCampi();
			
			$scope.getTiposResiduos = function() {
				tiposResiduosSvc.getTiposResiduos().then(function(tiposResiduosData) {
					$scope.tiposResiduos = tiposResiduosData;
				});
			}
			$scope.getTiposResiduos();
			
			$scope.carregaLocais = function() {
				localSvc.findLocaisColeta()
					.then(function(locaisData) {
						$scope.locaisColeta = locaisData;
					})
			}
			$scope.carregaLocais();
			
			$scope.submitLocal = function() {
				localSvc.createLocalColeta($scope.novoLocalColeta)
					.then(function(localColetaSaved) {
						Flash.create('success', 'Local de Coleta '
								+ localColetaSaved.nome
								+ ' criado com sucesso',
								'custom-class');
						$scope.carregaLocais();
					}, function(error) {
						Flash.create('error',
								'Não foi possível cadastrar o local '
										+ error, 'custom-class');
					})
			}
			
			$scope.deleteLocal = function(local) {
				localSvc.deleteLocal(local)
					.then(function(localColetaSaved) {
						Flash.create('success', 'Local de Coleta '
								+ local.nome
								+ ' excluído com sucesso',
								'custom-class');
						$scope.carregaLocais();
					}, function(error) {
						Flash.create('error',
								'Não foi possível excluir o local '
										+ error, 'custom-class');
					})
			}
			
			$scope.getTipoResiduosStr = function(tipo) {
				var tipoStr = _.find($scope.tiposResiduos, function( t ) {
					return t.tipoResiduos == tipo;
				});
				return tipoStr.tipo;
			}
			
			
		}]);