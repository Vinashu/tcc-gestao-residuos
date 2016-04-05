'use strict';
/**
 * 
 */
app.controller('labCtrl', [
		'$scope',
		'$http',
		'labSvc',
		'campusSvc',
		'$state',
		'$stateParams',
		'Flash',
		'$mdDialog',
		'$location',
		function($scope, $http, labSvc, campusSvc, $state, $stateParams, Flash, $mdDialog,
				$location) {

			$scope.laboratorios = [];
			$scope.labsCount = 0;
			$scope.labsPerPage = 20;

			$scope.pagination = {
				current : 0
			};

			$scope.carregaLabs = function(page) {
				if ($location.path().indexOf('/edit') <= 0) {
					labSvc.listPagenation(page).then(function(labList) {
						$scope.laboratorios = labList.content;
						$scope.labsCount = labList.totalElements;
					})
				}
			}
			$scope.carregaLabs(0);

			$scope.pageChanged = function(newPage) {
				$scope.carregaLabs(newPage);
			};

			$scope.showLabDetails = function(lab) {
				$mdDialog.show({
					clickOutsideToClose : true,
					scope : $scope,
					preserveScope : true,
					controller : 'dialogCtrl',
					locals : {
						lab : lab
					},
				
					templateUrl : 'tpl/laboratorios/view.html'
				});
			}

			$scope.dialogCtrl = function($scope, $mdDialog, lab) {
				$scope.closeDialog = function() {
					$mdDialog.hide();
				}
				$scope.lab = lab;
			}

			$scope.submitLab = function() {
				var idSavedLab;
				$scope.lab.dataAtualizacao = null;
				$scope.lab.tipoResiduos = null;
				labSvc.saveLab($scope.lab).then(
						function successCallBack(labData) {
							idSavedLab = labData.id;
							$state.go('app.laboratorios.edit', {
								'labId' : idSavedLab,
								'action' : 'edit'
							}).then(
									function successCallback(data) {
										Flash.create('success', 'Laboratório '
												+ labData.nome
												+ ' criado com sucesso',
												'custom-class');
									});

						},
						function errorCallback(error) {
							Flash.create('error',
									'Não foi possível cadastrar o laboratório '
											+ error, 'custom-class');
						});
			};
			
			$scope.editLab = function(labId) {
				$state.go('app.laboratorios.edit', {
					'labId' : labId,
					'action' : 'edit'
				});
			}
			
			$scope.showConfirmDialog = function(ev) {
				var confirm = $mdDialog.confirm().title(
						'Cancelar cadastro de laboratório?').ariaLabel(
						'Cancelar cadastro').ok('Ok').cancel('Fechar');

				$mdDialog.show(confirm).then(function() {
					$state.go('app.laboratorios');
				});
			};

			$scope.getCampi = function() {
				campusSvc.getCampi().then(function(campiData) {
					$scope.campi = campiData;
				});
			}

			if ($stateParams.labId != null) {
				labSvc.getLab($stateParams.labId)
						.then(
								function successCallback(labData) {
									$scope.lab = labData.data;
								},
								function(error) {
									Flash.create('warning',
											'Laboratório não encontrado -'
													+ error.statusText,
											'custom-class');
								});
			} else {

			}
			$scope.getCampi();

		} ]);
