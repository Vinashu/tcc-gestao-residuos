'use strict';
/**
 * 
 */
app
		.controller(
				'unidCentralizadoraCtrl',
				[
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
						'_',
						function($scope, $http, unidCentralizadoraSvc,
								campusSvc, labSvc, $state, $stateParams, Flash,
								$mdDialog, $location, _) {

							$scope.unidCentralizadora = {};
							$scope.unidCentralizadora.laboratorios = [];

							$scope.submitUnidCentralizadora = function() {
								unidCentralizadoraSvc.saveUnidCentralizadora($scope.unidCentralizadora)
										.then(function(savedUnidCentralizadora) {
													var idSavedUnidCentralizadora = savedUnidCentralizadora.id;
													$state.go('app.unidCentralizadoras.edit',
																	{
																		'unidCentralizadoraId' : idSavedUnidCentralizadora
																	})
															.then(function(data) {
																Flash.create('success', 'Unidade Centralizadora '
																		+ savedUnidCentralizadora.nome
																		+ ' criada com sucesso',
																		'custom-class');
																	})
												}, function(error) {
													Flash.create('error', "Não foi possível salvar a Unidade Centralizadora");
												});
							}

							$scope.addLabs = function(unidCentralizadora) {
								$mdDialog
										.show({
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

							$scope.dialogLabsCtrl = function($scope, $mdDialog,
									unidCentralizadora) {

								$scope.unidCentralizadora = unidCentralizadora;

								var unidCentralizadoraAux = _
										.clone(unidCentralizadora);

								$scope.addRemoveLab = function(lab) {
									if (!_.isUndefined(_.findWhere(
											unidCentralizadora.laboratorios, {
												id : lab.id
											}))) {
										unidCentralizadora.laboratorios = _.without(
												unidCentralizadora.laboratorios,_.findWhere(
																		unidCentralizadora.laboratorios,
																		{
																			id : lab.id
																		}));
									} else {
										unidCentralizadora.laboratorios
												.push(lab);
									}
								}

								$scope.isLabFromUnid = function(lab) {
									return !_.isUndefined(_.findWhere(
											unidCentralizadora.laboratorios, {
												id : lab.id
											}));
								};

								$scope.carregaLabs = function() {
									$scope.laboratorios = [];
									labSvc.list().then(function(labList) {
										$scope.laboratorios = labList;
									})
								};
								$scope.carregaLabs();

								$scope.closeDialog = function() {
									$scope.unidCentralizadora = unidCentralizadoraAux;
									$mdDialog.hide();
								}

								$scope.salvarLabs = function() {
									$mdDialog.hide();
								}

							}
							
							if ($stateParams.unidCentralizadoraId != null) {
								unidCentralizadoraSvc.getUnid($stateParams.unidCentralizadoraId)
										.then(
												function successCallback(unidData) {
													$scope.unidCentralizadora = unidData.data;
												},
												function(error) {
													Flash.create('warning',
															'Unidade não encontrado -' + $stateParams.unidCentralizadoraId + 'Erro:'
																	+ error.statusText,
															'custom-class');
												});
							} else {

							}

							$scope.getCampi = function() {
								campusSvc.getCampi().then(function(campiData) {
									$scope.campi = campiData;
								});
							}
							$scope.getCampi();

						} ]);