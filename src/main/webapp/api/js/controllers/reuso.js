'use strict';
/**
 * 
 */
app
		.controller(
				'reusoCtrl',
				[
						'$scope',
						'reusoSvc',
						'$state',
						'$stateParams',
						'Flash',
						'$mdDialog',
						'$location',
						'$q',
						'_',
						'unidadeMedidaSvc',
						'campusSvc',
						'unidCentralizadoraSvc',
						'coletaResiduosQuimicosSvc',
						function($scope, reusoSvc, $state, $stateParams,
								Flash, $mdDialog, $location, $q, _, unidadeMedidaSvc,campusSvc, 
								unidCentralizadoraSvc, coletaResiduosQuimicosSvc) {

							// Inicialização da Página
							$scope.search = {};
							$scope.reusoCount = 0;
							$scope.reusoPerPage = 20;
							$scope.pagination = {
								current : 1
							};
							$scope.campi = [];
							$scope.materiaisDisp = [];
							$scope.materiaisDispReuso = [];
							
							$scope.getCampi = function() {
								campusSvc.getCampi().then(function(campiData) {
									$scope.campi = campiData;
								});
							}
							$scope.getCampi();
							
							$scope.getMateriaisDisp = function() {
								reusoSvc.getMateriaisDisp()
									.then(function(materiaisData) {
										$scope.materiaisDisp = materiaisData;
									})
							}
							$scope.getMateriaisDisp();
							
							$scope.buscaInicial = function(page) {
								reusoSvc.findReusos(--page)
										.then(function(reusosData) {
													$scope.materiaisDispReuso = reusosData.content;
													$scope.coletasCount = reusosData.totalElements;
													$scope.pageChanged = $scope.buscaInicial;
													
													$scope.materiaisDispReuso.forEach(function(item) {
														unidadeMedidaSvc.getUnidadeMedidaInfos(item.materialColetado.unidadeMedida)
															.then(function(unidMedidaInfos) {
																item.materialColetado.unidadeMedida = unidMedidaInfos;
															});
													});
												},
												function(error) {
													Flash.create('error',"Erro ao pesquisar materiais de reuso: " + error.statusText,"custom-class");
												});
							}
							$scope.buscaInicial(1);


							// Pesquisa reusos
							$scope.findMateriaisReuso = function(page) {
								
								reusoSvc.findReusosMaterialCampus($scope.search, --page)
									.then(function(reusosData) {
													$scope.materiaisDispReuso = reusosData.content;
													$scope.reusoCount = reusosData.totalElements;
													$scope.pageChanged = $scope.findMateriaisReuso;
										},
										function(error) {
											Flash.create('error',"Erro ao pesquisar coletas: "+ error.statusText,"custom-class");
										})
							}
							

							// Novo reuso

							$scope.addReuso = function() {
								$scope.novaReuso = {};
								$mdDialog
										.show({
											clickOutsideToClose : true,
											scope : $scope,
											preserveScope : true,
											controller : 'novaReusoCtrl',

											templateUrl : 'tpl/reuso/novaMaterialReuso.html'
										});
							}
							
							$scope.novaReusoCtrl = function($scope, $mdDialog) {
								$scope.unidadesCentralizadoras = [];
								$scope.unidCentralizadora = {};
								$scope.search = {};
								$scope.coletasCount = 0;
								$scope.coletasPerPage = 20;
								$scope.pagination = {
									current : 1
								};
								$scope.materiaisSelecionados = [];
								
								$scope.carregaUnidadesCentralizadoras = function() {
									unidCentralizadoraSvc
											.findUnidadesCentralizadorasByTipoAndUser(
													'QUIMICO', $scope.loggedUser.principal)
											.then(
													function(unidades) {
														$scope.unidadesCentralizadoras = unidades;
														$scope.unidCentralizadora = _.first(unidades);
													},
													function(error) {
														Flash.create('error',
																"Não foi possível carregar as Unidades Centralizadoras: "+ error,"custom-class");
													})
								}
								$scope.carregaUnidadesCentralizadoras();
								
								$scope.findColetasUnid = function(page) {
									$scope.search.unidCentralizadoraId = $scope.unidCentralizadora.id;
									$scope.search.labId = -1;
									coletaResiduosQuimicosSvc
											.findColetasMesAnoLabUnid($scope.search, --page)
											.then(function(coletasData) {
														$scope.coletas = coletasData.content;
														$scope.coletasCount = coletasData.totalElements;
														$scope.pageChanged = $scope.findColetasUnid;
													},
													function(error) {
														Flash.create('error',"Erro ao pesquisar coletas: "+ error.statusText,"custom-class");
													})
								}
								
								$scope.closeDialog = function() {
									$mdDialog.hide();
								}
								
								$scope.selecionarMaterial = function(material, lab) {
									var novoMaterialReuso = { materialColetado : material, origem : lab};
									if (!_.any($scope.materiaisSelecionados,  function(item){ return _.isEqual(item.materialColetado, novoMaterialReuso.materialColetado); })) {
										$scope.materiaisSelecionados.push(novoMaterialReuso);
									}
								}
								
								$scope.salvarReusos = function() {
									$scope.addReusos().then(function(cont) {
										Flash.create('success', 'Materiais disponibilizados para reuso com sucesso', 'custom-class');
										$scope.closeDialog();
										$scope.buscaInicial($scope.pagination.current);
									});	
									
								}
								
								$scope.addReusos = function() {
									
									return $q(function(resolve, reject) {
										var cont = 0;
										_.each($scope.materiaisSelecionados, function(materialReuso) {
											var novoMaterialReuso = { 	
													materialColetado : materialReuso.materialColetado,
													responsavel : $scope.loggedUser.principal,
													entrada: new Date(),
													saida: null,
													consumidor: null,
													campus: materialReuso.origem.campus,
													status: 'Disponível'
												};
							
											reusoSvc.isMaterialDisponivelReuso(materialReuso.materialColetado.id)
												.then(function(disponivel) {	
													if(disponivel){
														reusoSvc.addMaterialReuso(novoMaterialReuso)
															.then(function(result) {
																resolve(cont++);
															}, function(error) {
															});
													}
												});
										});
									});
								}
								
								$scope.getUnidMedidaInfos = function(unid) {
									
									if (unid === 'LITRO') {
										return 'L';
									} else if (unid === 'UNIDADE') {
										return 'Unid.';
									}else if (unid === 'QUILO') {
										return 'kg';
									}
								}
							}
							
							/*
							 * view reuso
							 */
							
							$scope.viewReuso = function(reuso) {
								$scope.viewReuso = reuso;
								$mdDialog
										.show({
											clickOutsideToClose : true,
											scope : $scope,
											preserveScope : true,
											controller : 'viewReusoCtrl',
											locals : { reuso : reuso },

											templateUrl : 'tpl/reuso/view.html'
										});
							}
							
							$scope.viewReusoCtrl = function($scope, $mdDialog) {
								
								
							}

							
							
							
							/*
							 * delete coleta
							 */
							$scope.deleteMaterialReuso = function(reuso) {
								var confirm = $mdDialog.confirm().title(
								'Tem certeza que deseja excluir o material: '+ reuso.materialColetado.material.descricao + ' ?').ariaLabel(
								'Excluir material de reuso').ok('Sim').cancel('Cancelar');

								$mdDialog.show(confirm).then(function() {
									reusoSvc.deleteReusoByMaterialColetadoId(reuso.materialColetado.id)
										.then(function(result) {
											Flash.create('success',"Material excluído com sucesso " +result);
											$scope.pageChanged($scope.pagination.current);
										})
								});
							}
							
						} ]);