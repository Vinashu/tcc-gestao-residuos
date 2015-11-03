'use strict';
/**
 * 
 */
app.controller('labCtrl', ['$scope','$http', 'labSvc', function($scope, $http, labSvc) {
	
	$scope.campi = labSvc.campi();
	
}]);
