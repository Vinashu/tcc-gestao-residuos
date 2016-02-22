'use strict';
/**
 * 
 */
app
		.controller(
				'coletaResiduosServicosSaudeCtrl',
				[
						'$scope',
						'unidCentralizadoraSvc',
						'localSvc',
						'coletaResiduosServicosSaudeSvc',
						'$state',
						'$stateParams',
						'Flash',
						'$mdDialog',
						'$location',
						'_',
						function($scope, unidCentralizadoraSvc, localSvc,
								coletaResiduosServicosSaudeSvc, $state, $stateParams,
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
							
							$scope.coletas = [
								                  {
								                	  dataColeta : 'janeiro',
								                	  local : {nome : 'HUM'},
								                	  residuosInfectantesA : 2389.0,
								                	  residuosQuimicosB : 2419.2,
								                	  residuosRadioativosC : 0.0,
								                	  residuosComunsD : 1291.0,
								                	  residuosReciclaveisD : 223.2,
								                	  residuosPerfuroCortantesE : 4598.0  
								                  },
								                  {
								                	  dataColeta : 'fevereiro',
								                	  local : {nome : 'HUM'},
								                	  residuosInfectantesA : 1235.0,
								                	  residuosQuimicosB : 5434.2,
								                	  residuosRadioativosC : 0.0,
								                	  residuosComunsD : 2354.0,
								                	  residuosReciclaveisD : 345.2,
								                	  residuosPerfuroCortantesE : 7467.0  
								                  },
								                  {
								                	  dataColeta : 'março',
								                	  local : {nome : 'HUM'},
								                	  residuosInfectantesA : 2356.0,
								                	  residuosQuimicosB : 2342.2,
								                	  residuosRadioativosC : 0.0,
								                	  residuosComunsD : 4636.0,
								                	  residuosReciclaveisD : 234.2,
								                	  residuosPerfuroCortantesE : 654.0  
								                  },
								                  {
								                	  dataColeta : 'abril',
								                	  local : {nome : 'HUM'},
								                	  residuosInfectantesA : 2425.0,
								                	  residuosQuimicosB : 6453.2,
								                	  residuosRadioativosC : 0.0,
								                	  residuosComunsD : 4333.0,
								                	  residuosReciclaveisD : 345.2,
								                	  residuosPerfuroCortantesE : 2346.0  
								                  }
								                  
							                  ];
							
							$scope.carregaUnidadesCentralizadoras = function() {
								unidCentralizadoraSvc
										.findUnidadesCentralizadorasByTipo(
												'SERVICOSAUDE')
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
								coletaResiduosServicosSaudeSvc
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
								
								coletaResiduosServicosSaudeSvc
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

											templateUrl : 'tpl/coletaResiduos/novaColetaServicoSaude.html'
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
									coletaResiduosServicosSaudeSvc
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

											templateUrl : 'tpl/coletaResiduos/novaColetaServicoSaude.html'
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
									coletaResiduosServicosSaudeSvc
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
									coletaResiduosServicosSaudeSvc.deleteColeta(coleta.id)
										.then(function(result) {
											Flash.create('success',"Coleta Excluída com sucesso " +result);
											$scope.pageChanged($scope.pagination.current);
										})
								});
							}
							
						} ]);