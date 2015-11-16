'use strict';
/**
 * 
 */
app.controller('labCtrl', [
		'$scope',
		'$http',
		'labSvc',
		'$state',
		'$stateParams',
		function($scope, $http, labSvc, $state, $stateParams) {
			
			var novoLab = {};
			if ($stateParams.labId != null) {
				labSvc.getLab($stateParams.labId).then(function successCallback(labData) {
					$scope.lab = labData;
				});
			}

			$scope.submitLab = function() {
				var idSavedLab;
				$scope.lab.dataAtualizacao = null;
				$scope.lab.id = null;
				$scope.lab.tipoResiduos = null;
				labSvc.saveLab($scope.lab).then(
						function successCallBack(labData) {
							idSavedLab = labData.id;
							$scope.ajaxRequest.success = true;
							$state.go('app.laboratorios.edit', {
								'labId' : idSavedLab,
							}).then(function successCallback(data) {
								
							});

						}, function errorCallback(error) {
							
						});
			}

			$scope.getCampi = function() {
				labSvc.getCampi().then(function(campiData) {
					$scope.campi = campiData;
				});
			}
			$scope.getCampi();
			
			
			 $scope.ajaxRequest = {
					    error: false,
					    success: false,
					    inProgress: false
					  };
		} ]);
