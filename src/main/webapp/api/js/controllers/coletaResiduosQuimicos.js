'use strict';
/**
 * 
 */
app
		.controller(
				'coletaResiduosQuimicosCtrl',
				[
						'$scope',
						'unidCentralizadoraSvc',
						'localSvc',
						'coletaResiduosQuimicosSvc',
						'$state',
						'$stateParams',
						'Flash',
						'$mdDialog',
						'$location',
						'_',
						function($scope, unidCentralizadoraSvc, localSvc,
								coletaResiduosQuimicosSvc, $state,
								$stateParams, Flash, $mdDialog, $location, _) {

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
										.findUnidadesCentralizadoras()
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
								coletaResiduosQuimicosSvc
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

							// Pesquisa Coletas
							$scope.findColetasUnid = function(page) {
								$scope.search.unidCentralizadoraId = $scope.unidCentralizadora.id;
								$scope.search.localId = _
										.first($scope.unidCentralizadora.locais).id;
								coletaResiduosQuimicosSvc
										.findColetasMesAnoLabUnid(
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

						} ]);