'use strict';
/**
 * 
 */
app
		.controller(
				'novaColetaResiduosQuimicosCtrl',
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
						'materialSvc',
						'unidadeMedidaSvc',
						function($scope, unidCentralizadoraSvc, localSvc,
								coletaResiduosQuimicosSvc, $state,
								$stateParams, Flash, $mdDialog, $location, _, materialSvc, unidadeMedidaSvc ) {
							
							$scope.isCreating = $stateParams.novaColeta;
							$scope.isEditing = $stateParams.editColeta;

							$scope.materiais = [];
							$scope.unidadesMedida = [];
							
							$scope.novaColeta = {};
							$scope.novaColeta.dataColeta = new Date();
							$scope.novaColeta.materiaisColetados = [];
							$scope.novoMaterialColetado = {};
							$scope.isShowNovoMaterialInputs = false;
							
							/*
							 * Inicialização
							 */
							$scope.carregaUnidCentralizadora = function() {
								var unidCentralizadoraId = $state.params.unidCentralizadoraId;
								
								unidCentralizadoraSvc.getUnid(unidCentralizadoraId)
									.then(function(unid) {
										$scope.novaColeta.unidadeCentralizadora = unid.data;
								});
							}
							
							$scope.carregaMateriais = function() {
								materialSvc.getMateriais()
									.then(function(materiais) {
										$scope.materiais = materiais;
									})
							}
							
							$scope.carregaUnidadesMedidas = function() {
								unidadeMedidaSvc.getUnidadesMedida()
									.then(function(unidadesMedidas) {
										$scope.unidadesMedida = unidadesMedidas;
									})
							}
							
							$scope.carregaUnidCentralizadora();
							$scope.carregaMateriais();
							$scope.carregaUnidadesMedidas();
							
							
							/*
							 * controle material coletado
							 */
							
							$scope.verificaColetasLabMesAno = function() {
								if (!_.isEmpty($scope.novaColeta.laboratorio) && _.isDate($scope.novaColeta.dataColeta) && $scope.isCreating) {
									
									var coletasCount = 0;
									
									$scope.search = {};
									$scope.search.unidCentralizadoraId = $scope.novaColeta.unidadeCentralizadora.id;
									$scope.search.labId = JSON.parse($scope.novaColeta.laboratorio).id;
									$scope.search.mes = $scope.novaColeta.dataColeta.getMonth() + 1;
									$scope.search.ano = $scope.novaColeta.dataColeta.getFullYear();
									
									coletaResiduosQuimicosSvc
											.findColetasMesAnoLabUnid($scope.search, 0)
											.then(function(coletasData) {
												    coletasCount = coletasData.totalElements;
												    if (coletasCount > 0) {
														Flash.create('error', 'Já exite um registro para de coleta de resíduos para o laborátorio ' + JSON.parse($scope.novaColeta.laboratorio).nome + ' para o período de '+ $scope.search.mes +'/'+$scope.search.ano );
														return false;
													}else{
														return true;
													}
												},
												function(error) {
													Flash.create('error',"Erro ao verificar coletas do laboratorio " + $scope.novaColeta.laboratorio.nome + "no mês " + $scope.search.mes + "erro: " + error.statusText,"custom-class");
											}).then(function(result) {
												return result;
											});
								}else{
									return true;
								}
							}
							
							$scope.showNovoMaterialInputs = function() {
								if (_.isEmpty($scope.novaColeta.laboratorio) || !_.isDate($scope.novaColeta.dataColeta)) {
									Flash.create('error','Selecione laboratório e data da coleta antes de adicionar os materiais coletados','custom-class');
									$scope.isShowNovoMaterialInputs = false;
								}else{
									$scope.isShowNovoMaterialInputs = $scope.verificaColetasLabMesAno();
								}
							}
							
							$scope.addMaterialColetado = function() {
							
								var novoMaterialColetado = {};
								novoMaterialColetado.material = JSON.parse($scope.novoMaterialColetado.material);
								novoMaterialColetado.quantidade = $scope.novoMaterialColetado.quantidade;
								novoMaterialColetado.unidadeMedida = JSON.parse($scope.novoMaterialColetado.unidadeMedida);
								
								$scope.novaColeta.materiaisColetados.push(novoMaterialColetado);
								
								$scope.novoMaterialColetado = {};
							
							}
							
							$scope.atualizaMaterialColetado = function(materialColetadoOld, materialColetadoNew) {
								$scope.deleteMaterialColetado(materialColetadoOld);
								
								var novoMaterialColetado = {};
								novoMaterialColetado.material = JSON.parse(materialColetadoNew.material);
								novoMaterialColetado.quantidade = materialColetadoNew.quantidade;
								novoMaterialColetado.unidadeMedida =JSON.parse(materialColetadoNew.unidadeMedida);
								
								$scope.novaColeta.materiaisColetados.push(novoMaterialColetado);
							}
							
							$scope.deleteMaterialColetado = function(materialColetado) {
								 var materiaisColetadosAux = _($scope.novaColeta.materiaisColetados).filter(function(item) {
								     return item !== materialColetado
								});
								 $scope.novaColeta.materiaisColetados = materiaisColetadosAux; 
							}
							
							$scope.editarMaterialColetado = function(materialColetado) {
								
								$mdDialog
								.show({
									clickOutsideToClose : true,
									scope : $scope,
									preserveScope : true,
									controller : 'editarMaterialColetadoCtrl',
									locals : { materialColetado : materialColetado },
										

									template : '<form class="form-horizontal"><div class="controls form-inline" > '
										+ ' <div class="input-group" style="width: 32%;"> ' 
				     					+ ' <select class="form-control" ng-model="editMaterialColetado.material"  placeholder="Material" name="size" >'
				     					+ '	<option value="{{editMaterialColetado.material}}">{{materialSelecionadoStr}}</option>'
				     					+ '	<option ng-repeat="material in materiais" value="{{material}}">{{material.descricao}}</option>'
				     					+ '	</select> '
				     					+ '	</div>'
				     					+ ' <div class="input-group" style="width: 23%;">'
				     					+ ' <input flex type="text" class="form-control" ng-model="editMaterialColetado.quantidade"  placeholder="Quantidade" > '
				     					+ ' </div> '
				     					+ ' <div class="input-group" style="width: 25%;"> '
				     					+ ' <select flex class="form-control" ng-model="editMaterialColetado.unidadeMedida"  ng-init="editMaterialColetado.unidadeMedida" placeholder="Unidade Medida" name="size" > '
				     					+ '	<option value="{{editMaterialColetado.unidadeMedida}}">{{unidMedidaStr}}</option>'
				     					+ ' <option ng-repeat="unidMedida in unidadesMedida" value="{{unidMedida}}">{{unidMedida.nome}}</option> '
				     					+ ' </select> '
				     					+ ' </div> '
				     					+ ' <button type="button" ng-click="updateMaterialColetado()" class="btn btn-primary" style="width: 14%;">Ok</button> ' 
				     					+ '  </div> '
				     					+ ' </form>'
								});
								
								
							}
							
							$scope.editarMaterialColetadoCtrl = function($scope, $mdDialog,
									materialColetado) {
								
								var materialColetadoAux = _.clone(materialColetado);
								$scope.editMaterialColetado = {};
								
								$scope.materialSelecionadoStr = materialColetadoAux.material.descricao;
								$scope.unidMedidaStr = materialColetadoAux.unidadeMedida.nome;
								
								$scope.editMaterialColetado.material = JSON.stringify(materialColetadoAux.material);
								$scope.editMaterialColetado.quantidade = materialColetadoAux.quantidade;
								$scope.editMaterialColetado.unidadeMedida = JSON.stringify(materialColetadoAux.unidadeMedida);
								
								$scope.updateMaterialColetado = function() {
									$scope.atualizaMaterialColetado(materialColetado, $scope.editMaterialColetado);
									$mdDialog.hide();
								}
							}
							
							/*
							 * Salvar Coleta
							 */
								
							$scope.submitColeta = function() {
								
								var lab = _.clone($scope.novaColeta.laboratorio);
								$scope.novaColeta.laboratorio = JSON.parse($scope.novaColeta.laboratorio);
								coletaResiduosQuimicosSvc.submitColeta($scope.novaColeta)
									.then(function(savedColeta) {
										$scope.novaColeta= {};
										$scope.carregaUnidCentralizadora();
										
										Flash.create('success',
														'Coleta de Resíduos Químicos de '
																+ lab.nome
																+ ' criada com sucesso','custom-class');
									}, function(error) {
										Flash.create('error',
												'Ocorreu um erro: '+ error
														+ 'verifique os campos e tente novamente, por favor','custom-class');
									});
							}
							
						}]);