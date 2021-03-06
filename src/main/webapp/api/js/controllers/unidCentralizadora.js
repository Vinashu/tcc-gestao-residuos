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
						'localSvc',
						'userSvc',
						function($scope, $http, unidCentralizadoraSvc,
								campusSvc, labSvc, $state, $stateParams, Flash,
								$mdDialog, $location, _, localSvc, userSvc) {

							$scope.unidCentralizadora = {};
							$scope.unidCentralizadora.locais = [];
							$scope.unidadesCentralizadoras = [];
							$scope.users = [];
							$scope.isEditing = false;
							$scope.titulo = "Nova Unidade Centralizadora";
							
							$scope.carregaUnidCentralizadoras = function() {
								unidCentralizadoraSvc.findUnidadesCentralizadoras()
									.then(function(unidsData) {
										$scope.unidadesCentralizadoras = unidsData;
									})
							}
							$scope.carregaUnidCentralizadoras();

							$scope.carregaTecnicosColeta = function() {
								userSvc.listByRole('TECNICOCOLETA')
									.then(function(usersData) {
										$scope.users = usersData;
									});
							}
							$scope.carregaTecnicosColeta();
							
							$scope.submitUnidCentralizadora = function() {
								unidCentralizadoraSvc
										.saveUnidCentralizadora(
												$scope.unidCentralizadora)
										.then(function(savedUnidCentralizadora) {
													var idSavedUnidCentralizadora = savedUnidCentralizadora.id;
													$state.go('app.unidCentralizadoras.edit',
																	{'unidCentralizadoraId' : idSavedUnidCentralizadora})
															.then(function(data) {
																		Flash.create('success',
																						'Unidade Centralizadora '
																								+ savedUnidCentralizadora.nome
																								+ ' criada com sucesso','custom-class');
																	})
												},function(error) {
													Flash.create('error',
																	"Não foi possível salvar a Unidade Centralizadora");
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
											unidCentralizadora.locais, {
												id : lab.id
											}))) {
										unidCentralizadora.locais = _
												.without(
														unidCentralizadora.locais,
														_
																.findWhere(
																		unidCentralizadora.locais,
																		{
																			id : lab.id
																		}));
									} else {
										unidCentralizadora.locais.push(lab);
									}
								}

								$scope.isLabFromUnid = function(lab) {
									return !_.isUndefined(_.findWhere(
											unidCentralizadora.locais, {
												id : lab.id
											}));
								};

								$scope.carregaLabs = function() {
									$scope.locais = [];
									if (unidCentralizadora.tipoResiduos != "QUIMICO") {
										localSvc.listNotLab().then(function(localList) {
											$scope.locais = localList;
										})
									} else {
										labSvc.list().then(function(labList) {
											$scope.locais = labList;
										})
									}
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
							
							$scope.showUnidDetails = function(unid) {
								$state.go('app.unidCentralizadoras.edit', { unidCentralizadoraId : unid.id});
							}
							
							$scope.edit = function() {
								$scope.isEditing = false;
							}
							
							$scope.showConfirmDialog = function(ev) {
								var confirm = $mdDialog.confirm().title(
										'Cancelar cadastro de Unidade Centralizadora?').ariaLabel(
										'Cancelar cadastro').ok('Ok').cancel('Fechar');

								$mdDialog.show(confirm).then(function() {
									$state.go('app.unidCentralizadoras.pesquisar');
								});
							};
							
							$scope.delete = function(ev) {
								var confirm = $mdDialog.confirm().title(
										'Tem certeza que deseja excluir a Unidade Centralizadora?').ariaLabel(
										'Cancelar cadastro').ok('Ok').cancel('Fechar');

								$mdDialog.show(confirm).then(function() {
									unidCentralizadoraSvc.deleteUnid($scope.unidCentralizadora.id)
										.then(function(success) {
											Flash.create('success',
													'Unidade Centralizadora excluída com sucesso' ,'custom-class');
											$state.go('app.unidCentralizadoras.pesquisar');
										},function(error) {
											Flash.create('error',
											"Não foi possível excluir a Unidade Centralizadora");
										});
									
								});
							};

							if ($stateParams.unidCentralizadoraId != null) {
								unidCentralizadoraSvc
										.getUnid(
												$stateParams.unidCentralizadoraId)
										.then(
												function successCallback(
														unidData) {
													$scope.unidCentralizadora = unidData.data;
													$scope.isEditing = true;
													$scope.titulo = "Unidade Centralizadora "+ 	$scope.unidCentralizadora.id;
												},
												function(error) {
													Flash
															.create(
																	'warning',
																	'Unidade não encontrado -'
																			+ $stateParams.unidCentralizadoraId
																			+ 'Erro:'
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