'use strict';
/**
 * 
 */
app.controller('usersCtrl', [
		'$scope',
		'$http',
		'userSvc',
		'campusSvc',
		'$state',
		'$stateParams',
		'Flash',
		'$mdDialog',
		'$location',
		'roleSvc',
		function($scope, $http, userSvc, campusSvc, $state, $stateParams, Flash, $mdDialog,
				$location, roleSvc) {
			
			$scope.usuarios = [];
			$scope.campi = [];
			$scope.user = {};
			$scope.roles = [];
			
			$scope.user = $scope.loggedUser.principal;
			
			$scope.listUsuarios = function() {
				userSvc.list().then(function(usersData) {
					$scope.usuarios = usersData;
				});
			}
			$scope.listUsuarios();
			
			$scope.getCampi = function() {
				campusSvc.getCampi().then(function(campiData) {
					$scope.campi = campiData;
				});
			}
			$scope.getCampi();
			
			$scope.getRoles= function() {
				roleSvc.getRoles().then(function(rolesData) {
					$scope.roles = rolesData;
				});
			}
			
			$scope.addNovoUsuario = function() {
				$mdDialog
				.show({
					clickOutsideToClose : true,
					scope : $scope,
					preserveScope : true,
					
					controller : 'novoUsuarioCtrl',
					
					templateUrl : 'tpl/usuarios/novoUsuario.html'
				});
		
			}
			
			$scope.novoUsuarioCtrl = function($scope, $mdDialog) {
				$scope.novoUsuario = {};
				
				$scope.getRoles();
				
				$scope.submitNovoUsuario = function() {
					userSvc.submitUsuario($scope.novoUsuario)
						.then(function(savedUsuarioData) {
							Flash.create('success', 'Usuário '
													+ novoUsuarioData.name
													+ ' criado com sucesso',
													'custom-class');
							$scope.closeDialog();
							$scope.listUsuarios();
						},function(error){
							Flash.create('error', 'Não foi possível criar usuário '
									+ $scope.novoUsuario.name + ". \n"
									+ error + '. Verifique os campos e tente novamente',
									'custom-class');
						});
				}
			}
			
		}]);
		