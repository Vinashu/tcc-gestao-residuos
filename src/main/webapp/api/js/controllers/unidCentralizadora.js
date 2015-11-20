'use strict';
/**
 * 
 */
app.controller('unidCentralizadoraCtrl', [
		'$scope',
		'$http',
		'unidCentralizadoraSvc',
		'campusSvc',
		'labSvc',
		'$state',
		'$stateParams',
		'Flash',
		'$mdDialog',
		'$location',
		function($scope, $http, unidCentralizadoraSvc, campusSvc,labSvc, $state, $stateParams, Flash,
				$mdDialog, $location) {

			
			
			$scope.addLabs = function(unidCentralizadora) {
				$mdDialog.show({
					clickOutsideToClose : true,
					scope : $scope,
					preserveScope : true,
					controller : 'dialogLabsCtrl',
					locals : {
						unidCentralizadora : unidCentralizadora
					},
				
					templateUrl : 'tpl/unidCentralizadoras/addLabs.html'
				});
			}

			$scope.dialogLabsCtrl = function($scope, $mdDialog, unidCentralizadora) {
				$scope.closeDialog = function() {
					$mdDialog.hide();
				}
				$scope.unidCentralizadora = unidCentralizadora;
			}
			
			$scope.getCampi = function() {
				campusSvc.getCampi().then(function(campiData) {
					$scope.campi = campiData;
				});
			}
			$scope.getCampi();
			
			$scope.getLabs = function(unidCentralizadora) {
				labSvc.
			}
			
			
		} ]);