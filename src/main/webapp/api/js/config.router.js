'use strict';

/**
 * Config for the router
 */
angular
		.module('app')
		.run(
				[ '$rootScope', '$state', '$stateParams', '$http',
						function($rootScope, $state, $stateParams, $http) {
							$rootScope.$state = $state;
							$rootScope.$stateParams = $stateParams;

						} ])
		.config(
				[
						'$stateProvider',
						'$urlRouterProvider',
						'JQ_CONFIG',
						'MODULE_CONFIG',
						function($stateProvider, $urlRouterProvider, JQ_CONFIG,
								MODULE_CONFIG) {

							var layout = "tpl/blocks/material.layout.html";
							$urlRouterProvider.otherwise('/app/dashboard-v3');

							$stateProvider
									.state('app', {
										abstract : true,
										url : '/app',
										templateUrl : layout
									})
									.state(
											'app.dashboard-v3',
											{
												url : '/dashboard-v3',
												templateUrl : 'tpl/app_dashboard_v3.html',
												resolve : load([ 'js/controllers/dashboard.js' ])
											})

									// componentes

									/*
									 * Laboratórios
									 */
									.state(
											'app.laboratorios',
											{
												url : '/laboratorios',
												template : '<div ui-view class="wrapper-md"></div>',
												resolve : load([
														'js/controllers/laboratorio.js',
														'js/services/laboratorio.js',
														'js/services/campus.js' ])
											})
									.state(
											'app.laboratorios.pesquisar',
											{
												url : '/',
												params : {
													'page' : 0
												},
												templateUrl : 'tpl/laboratorios/pesquisar.html'
											})
									.state(
											'app.laboratorios.edit',
											{
												url : '/edit/{labId}',
												params : {
													'labId' : null
												},
												templateUrl : 'tpl/laboratorios/edit.html'
											})
									.state(
											'app.laboratorios.novo',
											{
												url : '/edit',
												templateUrl : 'tpl/laboratorios/edit.html'
											})

									/*
									 * Unidades Centralizadoras
									 */
									.state(
											'app.unidCentralizadoras',
											{
												url : '/unidCentralizadoras',
												template : '<div ui-view class="wrapper-md"></div>',
												resolve : load([
														'js/controllers/unidCentralizadora.js',
														'js/services/unidCentralizadora.js',
														'js/services/campus.js',
														'js/services/laboratorio.js',
														'js/services/local.js'])
											})
									.state(
											'app.unidCentralizadoras.pesquisar',
											{
												url : '/',
												templateUrl : 'tpl/unidCentralizadoras/pesquisar.html'
											})
									.state(
											'app.unidCentralizadoras.edit',
											{
												url : '/edit/{unidCentralizadoraId}',
												params : {
													'unidCentralizadoraId' : null
												},
												templateUrl : 'tpl/unidCentralizadoras/edit.html'
											})
									.state(
											'app.unidCentralizadoras.novo',
											{
												url : '/edit',
												templateUrl : 'tpl/unidCentralizadoras/edit.html'
											})

									/*
									 * Coleta de Resíduos
									 */
									.state(
											'app.coletaResiduos',
											{
												url : '/coletaResiduos',
												template : '<div ui-view class="wrapper-md"></div>',
												resolve : load([
														'js/services/laboratorio.js',
														'js/services/unidCentralizadora.js',
														'js/services/local.js' ])
											})

									/*
									 * sólidos
									 */
									.state(
											'app.coletaResiduos.solidos',
											{
												url : '/solidos',
												templateUrl : 'tpl/coletaResiduos/solidos.html',
												resolve : load([
														'js/controllers/coletaResiduosSolidos.js',
														'js/services/coletaResiduosSolidos.js' ])
											})
									.state(
											'app.coletaResiduos.solidos.novaColeta',
											{
												url : '/solidos',
												templateUrl : 'tpl/coletaResiduos/solidos.html',
												params : {
													'novaColeta' : true
												},
												resolve : load([
														'js/controllers/coletaResiduosSolidos.js',
														'js/services/coletaResiduosSolidos.js' ])
											})

									/*
									 * quimicos
									 */

									.state(
											'app.coletaResiduos.quimicos',
											{
												url : '/quimicos',
												templateUrl : 'tpl/coletaResiduos/quimicos.html',
												resolve : load([
														'js/controllers/coletaResiduosQuimicos.js',
														'js/services/coletaResiduosQuimicos.js' ])
											})
									.state(
											'app.coletaResiduos.quimicos.novaColeta',
											{
												url : '/quimicos/edit',
												templateUrl : 'tpl/coletaResiduos/novaColetaQuimico.html',
												params : {
													'novaColeta' : true,
													'editColeta' : true,
													'unidCentralizadoraId': null
												},
												parent : 'app.coletaResiduos',
												resolve : load([
														'js/controllers/novaColetaResiduosQuimicos.js',
														'js/services/coletaResiduosQuimicos.js',
														'js/services/material.js',
														'js/services/unidadeMedida.js'])
											})
									.state(
											'app.coletaResiduos.quimicos.editColeta',
											{
												url : '/quimicos/edit/{coletaId}',
												templateUrl : 'tpl/coletaResiduos/novaColetaQuimico.html',
												params : {
													'novaColeta' : false,
													'editColeta' : true,
													'coletaId' : null,
													'unidCentralizadoraId': null
												},
												parent : 'app.coletaResiduos',
												resolve : load([
														'js/controllers/novaColetaResiduosQuimicos.js',
														'js/services/coletaResiduosQuimicos.js',
														'js/services/material.js',
														'js/services/unidadeMedida.js'])
											})		

									// pages
									.state(
											'app.page',
											{
												url : '/page',
												template : '<div ui-view class="fade-in-down"></div>'
											})

									.state(
											'access.signin',
											{
												url : '/signin',
												templateUrl : 'tpl/page_signin.html',
												resolve : load([ 'js/controllers/signin.js' ])
											})
									.state(
											'access.signup',
											{
												url : '/signup',
												templateUrl : 'tpl/page_signup.html',
												resolve : load([ 'js/controllers/signup.js' ])
											})

									.state('access.404', {
										url : '/404',
										templateUrl : 'tpl/page_404.html'
									})
									.state('layout', {
										abstract : true,
										url : '/layout',
										templateUrl : 'tpl/layout.html'
									})
									.state(
											'layout.fullwidth',
											{
												url : '/fullwidth',
												views : {
													'' : {
														templateUrl : 'tpl/layout_fullwidth.html'
													},
													'footer' : {
														templateUrl : 'tpl/layout_footer_fullwidth.html'
													}
												},
												resolve : load([ 'js/controllers/vectormap.js' ])
											})
									.state(
											'layout.mobile',
											{
												url : '/mobile',
												views : {
													'' : {
														templateUrl : 'tpl/layout_mobile.html'
													},
													'footer' : {
														templateUrl : 'tpl/layout_footer_mobile.html'
													}
												}
											})

							function load(srcs, callback) {
								return {
									deps : [
											'$ocLazyLoad',
											'$q',
											function($ocLazyLoad, $q) {
												var deferred = $q.defer();
												var promise = false;
												srcs = angular.isArray(srcs) ? srcs
														: srcs.split(/\s+/);
												if (!promise) {
													promise = deferred.promise;
												}
												angular
														.forEach(
																srcs,
																function(src) {
																	promise = promise
																			.then(function() {
																				if (JQ_CONFIG[src]) {
																					return $ocLazyLoad
																							.load(JQ_CONFIG[src]);
																				}
																				angular
																						.forEach(
																								MODULE_CONFIG,
																								function(
																										module) {
																									if (module.name == src) {
																										name = module.name;
																									} else {
																										name = src;
																									}
																								});
																				return $ocLazyLoad
																						.load(name);
																			});
																});
												deferred.resolve();
												return callback ? promise
														.then(function() {
															return callback();
														}) : promise;
											} ]
								}
							}

						} ]);
