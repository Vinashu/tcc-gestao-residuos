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
						'unidadeMedidaSvc',
						function($scope, unidCentralizadoraSvc, localSvc,
								coletaResiduosQuimicosSvc, $state,
								$stateParams, Flash, $mdDialog, $location, _, unidadeMedidaSvc) {

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
										.findUnidadesCentralizadorasByTipoAndUser(
												'QUIMICO', $scope.loggedUser.principal)
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

							// nova coleta

							$scope.addNovaColeta = function() {
								$state.go('app.coletaResiduos.quimicos.novaColeta',
												{
													'novaColeta' : true,
													'editColeta' : true,
													'unidCentralizadoraId' : $scope.unidCentralizadora.id
												})
										.then(function(success) {
												}, function(error) {
												});
							}
							
							//delete coleta
							
							$scope.deleteColeta = function(coleta) {
								var confirm = $mdDialog.confirm().title(
								'Tem certeza que deseja excluir a coleta de resíduos do laboratório '+ coleta.laboratorio.nome + ' ?').ariaLabel(
								'Excluir Coleta de Resíduos Químicos').ok('Sim').cancel('Cancelar');

								$mdDialog.show(confirm).then(function() {
									coletaResiduosQuimicosSvc.deleteColeta(coleta.id)
										.then(function(result) {
											Flash.create('success',"Coleta Excluída com sucesso " +result);
											$scope.pageChanged($scope.pagination.current);
										})
								});
							}
							
							
							$scope.editColeta = function(coleta) {
								$state.go('app.coletaResiduos.quimicos.editColeta',
										{
											'novaColeta' : false,
											'editColeta' : true,
											'coletaId': coleta.id,
											'unidCentralizadoraId' : $scope.unidCentralizadora.id
										})
								.then(function(success) {
										}, function(error) {
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
								
								/*LOOP INFINITO... CAUSE?!
								 * var infos = unidadeMedidaSvc.getUnidadeMedidaInfos(unid);
									.then(function(infos) {
										return infos;
									});
								return infos;	*/
							}

						} ]);