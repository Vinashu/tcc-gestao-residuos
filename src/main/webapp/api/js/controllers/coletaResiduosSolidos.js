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

							// Nova Coleta

							$scope.novaColeta = {};
							$scope.addNovaColeta = function() {
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
								$scope.closeDialog = function() {
									$mdDialog.hide();
								}

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
											.then(
													function(savedColeta) {
														Flash
																.create(
																		'success',
																		'Coleta '
																				+ savedColeta.os
																				+ ' criada com sucesso',
																		'custom-class');
														$mdDialog.hide();
														$scope.novaColeta = {};
														$scope.buscaInicial(1);
													},
													function(error) {
														Flash
																.create(
																		'error',
																		"Erro ao criar coleta"
																				+ ": "
																				+ error.statusText,
																		"custom-class");
													});
								}

							}

						} ]);