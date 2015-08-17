/**
 * 
 */

angular
		.module('loginApp')
		.controller(
				'LoginCtrl',
				[
						'$scope',
						'$http',
						function($scope, $http) {

							$scope.onLogin = function() {
								console.log('Attempting login with username '
										+ $scope.vm.username + ' and password '
										+ $scope.vm.password);

								$scope.vm.submitted = true;

								if ($scope.form.$invalid) {
									return;
								}

								$scope.login($scope.vm.userName,
										$scope.vm.password);

								$scope.login = function(username, password) {
									var postData = "username="+username+"&password="+password;

									$http(
											{
												method : 'POST',
												url : '/authenticate',
												data : postData,
												headers : {
													"Content-Type" : "application/x-www-form-urlencoded",
													"X-Login-Ajax-call" : 'true'
												}
											})
											.then(
													function(response) {
														if (response.data == 'ok') {
															window.location
																	.replace('/index.html');
														} else {
															$scope.vm.errorMessages = [];
															$scope.vm.errorMessages
																	.push({
																		description : 'Access denied'
																	});
														}
													});
								}

							};

						} ]);