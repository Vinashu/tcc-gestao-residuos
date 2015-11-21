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
		'underscore',
		function($scope, $http, unidCentralizadoraSvc, campusSvc,labSvc, $state, $stateParams, Flash,
				$mdDialog, $location, underscore) {

			$scope.unidCentralizadora = {};
			$scope.unidCentralizadora.laboratorios = [];
			
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
				
				$scope.unidCentralizadora = unidCentralizadora;
				
				$scope.addRemoveLab = function(lab) {
					var idx = unidCentralizadora.laboratorios.indexOf(lab);
			        if (idx > -1) {
			        	unidCentralizadora.laboratorios.remove(lab);
			        }else{
			        	unidCentralizadora.laboratorios.push(lab);
			        }
				}
				
				$scope.exists = function (item, list) {
			        return list.indexOf(item) > -1;
			      };
				
				$scope.carregaLabs = function() {
					labSvc.list().then(function(labList) {
						$scope.laboratorios = labList;
					})
				};
				$scope.carregaLabs();
				
				$scope.closeDialog = function() {
					$mdDialog.hide();
				}
				
				
			}
			
			$scope.getCampi = function() {
				campusSvc.getCampi().then(function(campiData) {
					$scope.campi = campiData;
				});
			}
			$scope.getCampi();
			
			
			
		} ]);