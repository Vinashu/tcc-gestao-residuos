'use strict';
/**
 * 
 */
app
		.controller(
				'coletaResiduosSolidosCtrl',
				[
						'$scope',
						'unidCentralizadoraSvc',
						'localSvc',
						'coletaResiduosSolidosSvc',
						'$state',
						'$stateParams',
						'Flash',
						'$mdDialog',
						'$location',
						'_',
						function($scope, unidCentralizadoraSvc, localSvc,
								coletaResiduosSolidosSvc, $state, $stateParams,
								Flash, $mdDialog, $location, _) {

							// Inicialização da Página
							$scope.unidCentralizadora = {};
							$scope.unidadesCentralizadoras = [];
							$scope.search = {};
							$scope.coletasCount = 0;
							$scope.coletasPerPage = 20;
							$scope.pagination = {
								current : 1
							};
							
							$scope.carregaUnidadesCentralizadoras = function() {
								unidCentralizadoraSvc
										.findUnidadesCentralizadorasByTipo(
												'SOLIDO')
										.then(
												function(unidades) {
													$scope.unidadesCentralizadoras = unidades;
													$scope.unidCentralizadora = _
															.first(unidades);
													$scope.buscaInicial(1);
													$scope.pageChanged = $scope.buscaInicial;
												},
												function(error) {
													Flash.create('error',
															"Não foi possível carregar as Unidades Centralizadoras: "
																	+ error,
															"custom-class");
												})
							}

							$scope.buscaInicial = function(page) {
								coletaResiduosSolidosSvc
										.findColetasUnid(
												$scope.unidCentralizadora.id,
												--page)
										.then(
												function(coletasData) {
													$scope.coletas = coletasData.content;
													$scope.coletasCount = coletasData.totalElements;
												},
												function(error) {
													Flash
															.create(
																	'error',
																	"Erro ao pesquisar coletas: "
																			+ error.statusText,
																	"custom-class");
												})
							}

							$scope.carregaUnidadesCentralizadoras();

							if ($stateParams.novaColeta) {
								$scope.addNovaColeta();
							}

							// Pesquisa Coletas
							$scope.findColetasUnid = function(page) {
								$scope.search.unidCentralizadoraId = $scope.unidCentralizadora.id;
								
								coletaResiduosSolidosSvc
										.findColetasMesAnoLocalUnid(
												$scope.search, --page)
										.then(
												function(coletasData) {
													$scope.coletas = coletasData.content;
													$scope.coletasCount = coletasData.totalElements;
													$scope.pageChanged = $scope.findColetasUnid;
												},
												function(error) {
													Flash
															.create(
																	'error',
																	"Erro ao pesquisar coletas: "
																			+ error.statusText,
																	"custom-class");
												})
							}
							
							$scope.limparCampos = function() {
								$scope.search = {};
								$scope.search.localId = -1;
								$scope.buscaInicial(1);
							}

							// Nova Coleta

							$scope.addNovaColeta = function() {
								$scope.novaColeta = {};
								$mdDialog
										.show({
											clickOutsideToClose : true,
											scope : $scope,
											preserveScope : true,
											controller : 'novaColetaCtrl',

											templateUrl : 'tpl/coletaResiduos/novaColetaSolido.html'
										});
							}
							
							$scope.novaColetaCtrl = function($scope, $mdDialog) {
								
								$scope.novaColeta.unidadeCentralizadora = $scope.unidCentralizadora;
								
								$scope.locais = [];
								$scope.carregaLocais = function() {
									localSvc.findLocais().then(
											function(locaisData) {
												$scope.locais = locaisData;
											})
								}
								$scope.carregaLocais();
								
								$scope.salvarColeta = function() {
									coletaResiduosSolidosSvc
										.submitColeta($scope.novaColeta)
										.then(function(savedColeta) {
											Flash.create('success','Coleta ' + savedColeta.os + ' criada com sucesso', 'custom-class');
												$mdDialog.hide();
												$scope.novaColeta = {};
												$scope.buscaInicial(1);
											},
											function(error) {
												Flash.create('error',"Erro ao criar coleta" + ": "+ error.statusText,"custom-class");
											});
								}
								
								$scope.closeDialog = function() {
									$mdDialog.hide();
								}

							}
							
							/*
							 * edit coleta
							 */
							$scope.editColeta = function(editColeta) {
								
								$mdDialog.show({
											clickOutsideToClose : true,
											scope : $scope,
											preserveScope : true,
											controller : 'editColetaCtrl',
											locals : { 
												editColeta : editColeta,
													isEditing: true
																	},

											templateUrl : 'tpl/coletaResiduos/novaColetaSolido.html'
										});
							}

							$scope.editColetaCtrl = function($scope, $mdDialog, editColeta) {
								
								$scope.novaColeta = editColeta;
								$scope.novaColeta.dataColeta = new Date($scope.novaColeta.dataColeta);
								
								$scope.locais = [];
								$scope.carregaLocais = function() {
									localSvc.findLocais().then(
											function(locaisData) {
												$scope.locais = locaisData;
											})
								}
								$scope.carregaLocais();
								
								$scope.salvarColeta = function() {
									coletaResiduosSolidosSvc
										.submitColeta($scope.novaColeta)
										.then(function(savedColeta) {
											Flash.create('success','Coleta ' + savedColeta.os + ' editada com sucesso', 'custom-class');
												$mdDialog.hide();
												$scope.novaColeta = {};
												$scope.buscaInicial(1);
											},
											function(error) {
												Flash.create('error',"Erro ao editar coleta" + ": "+ error.statusText,"custom-class");
											});
								}
								
								$scope.closeDialog = function() {
									$mdDialog.hide();
								}

							}
							
							/*
							 * delete coleta
							 */
							
							$scope.deleteColeta = function(coleta) {
								var confirm = $mdDialog.confirm().title(
								'Tem certeza que deseja excluir a coleta de resíduos sólidos OS: '+ coleta.os + ' ?').ariaLabel(
								'Excluir Coleta de Resíduos Sólidos').ok('Sim').cancel('Cancelar');

								$mdDialog.show(confirm).then(function() {
									coletaResiduosSolidosSvc.deleteColeta(coleta.id)
										.then(function(result) {
											Flash.create('success',"Coleta Excluída com sucesso " +result);
											$scope.pageChanged($scope.pagination.current);
										})
								});
							}
							
						} ]);