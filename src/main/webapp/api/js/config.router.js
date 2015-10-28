'use strict';

/**
 * Config for the router
 */
angular
		.module('app')
		.run(
				[ '$rootScope', '$state', '$stateParams',
						function($rootScope, $state, $stateParams) {
							$rootScope.$state = $state;
							$rootScope.$stateParams = $stateParams;
						} ])
		.config(
				[
						'$stateProvider',
						'$urlRouterProvider',
						'$httpProvider',
						'JQ_CONFIG',
						'MODULE_CONFIG',
						function($stateProvider, $urlRouterProvider,
								$httpProvider, JQ_CONFIG, MODULE_CONFIG) {
							
							$httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
							var layout = "tpl/app.html";
							if (window.location.href.indexOf("material") > 0) {
								layout = "tpl/blocks/material.layout.html";
								$urlRouterProvider
										.otherwise('/app/dashboard-v3');
							} else {
								$urlRouterProvider
										.otherwise('/app/dashboard-v1');
							}

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
												resolve : load([ 'js/controllers/chart.js' ])
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

									.state(
											'app.material',
											{
												url : '/material',
												template : '<div ui-view class="wrapper-md"></div>',
												resolve : load([ 'js/controllers/material.js' ])
											})
									.state(
											'app.material.button',
											{
												url : '/button',
												templateUrl : 'tpl/material/button.html'
											})
									.state('app.material.color', {
										url : '/color',
										templateUrl : 'tpl/material/color.html'
									})
									.state('app.material.icon', {
										url : '/icon',
										templateUrl : 'tpl/material/icon.html'
									})
									.state('app.material.card', {
										url : '/card',
										templateUrl : 'tpl/material/card.html'
									})
									.state('app.material.form', {
										url : '/form',
										templateUrl : 'tpl/material/form.html'
									})
									.state('app.material.list', {
										url : '/list',
										templateUrl : 'tpl/material/list.html'
									})
									.state(
											'app.material.ngmaterial',
											{
												url : '/ngmaterial',
												templateUrl : 'tpl/material/ngmaterial.html'
											});

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
							$httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';

						} ]);
