'use strict';

/* Controllers */

angular
		.module('app')
		.controller(
				'AppCtrl',
				[
						'$scope',
						'$translate',
						'$localStorage',
						'$window',
						'$http',
						'$rootScope',
						'Flash',
						function($scope, $translate, $localStorage, $window, $http, $rootScope, Flash) {
							
							$http.defaults.headers.common["X-XSRF-TOKEN"] = $window.csrfToken;
							
							var authenticate = function(credentials, callback) {
								
								var headers = credentials ? {
									authorization : "Basic "
											+ btoa(credentials.username + ":"
													+ credentials.password)
								} : {};

								$http.post('users/user/', {
									headers : headers
								}).success(function(data) {
									if (data.name) {
										$rootScope.authenticated = true;
										$scope.loggedUser = data;
									} else {
										$rootScope.authenticated = false;
									}
									callback && callback();
								}).error(function() {
									$rootScope.authenticated = false;
									callback && callback();
								});

							}
							$scope.loggedUser={};
							authenticate();
							$scope.credentials = {};
							$scope.login = function() {
								authenticate($scope.credentials, function() {
									if ($rootScope.authenticated) {
										$location.path("/");
										$scope.error = false;
									} else {
										//$location.path("/public/login");
										$scope.error = true;
									}
								});
							};

							// add 'ie' classes to html
							var isIE = !!navigator.userAgent.match(/MSIE/i);
							isIE
									&& angular.element($window.document.body)
											.addClass('ie');
							isSmartDevice($window)
									&& angular.element($window.document.body)
											.addClass('smart');

							// config
							$scope.app = {
								name : 'SisGRUEM - Sistema de Gestão de Resíduos UEM',
								version : '2.0.3',
								// for chart colors
								color : {
									primary : '#7266ba',
									info : '#23b7e5',
									success : '#27c24c',
									warning : '#fad733',
									danger : '#f05050',
									light : '#e8eff0',
									dark : '#3a3f51',
									black : '#1c2b36'
								},
								settings : {
									themeID : 1,
									navbarHeaderColor : 'bg-black',
									navbarCollapseColor : 'bg-white-only',
									asideColor : 'bg-black',
									headerFixed : true,
									asideFixed : false,
									asideFolded : false,
									asideDock : false,
									container : false
								}
							}

							// save settings to local storage
							if (angular.isDefined($localStorage.settings)) {
								$scope.app.settings = $localStorage.settings;
							} else {
								$localStorage.settings = $scope.app.settings;
							}
							$scope.$watch('app.settings', function() {
								if ($scope.app.settings.asideDock
										&& $scope.app.settings.asideFixed) {
									// aside dock and fixed must set the header
									// fixed.
									$scope.app.settings.headerFixed = true;
								}
								// for box layout, add background image
								$scope.app.settings.container ? angular
										.element('html').addClass('bg')
										: angular.element('html').removeClass(
												'bg');
								// save to local storage
								$localStorage.settings = $scope.app.settings;
							}, true);

							// angular translate
							$scope.lang = {
								isopen : false
							};
							$scope.langs = {
								en : 'English',
								de_DE : 'German',
								it_IT : 'Italian'
							};
							$scope.selectLang = $scope.langs[$translate
									.proposedLanguage()]
									|| "English";
							$scope.setLang = function(langKey, $event) {
								// set the current lang
								$scope.selectLang = $scope.langs[langKey];
								// You can change the language during runtime
								$translate.use(langKey);
								$scope.lang.isopen = !$scope.lang.isopen;
							};

							function isSmartDevice($window) {
								// Adapted from
								// http://www.detectmobilebrowsers.com
								var ua = $window['navigator']['userAgent']
										|| $window['navigator']['vendor']
										|| $window['opera'];
								// Checks for iOs, Android, Blackberry, Opera
								// Mini, and Windows mobile devices
								return (/iPhone|iPod|iPad|Silk|Android|BlackBerry|Opera Mini|IEMobile/)
										.test(ua);
							}

						} ]);
