'use strict';
/**
 * 
 */
app.controller('labCtrl', ['$scope','$http', 'labSvc', function($scope, $http, labSvc) {
	
	 $scope.submitLab = function() {
		var idSavedLab;
		$scope.lab.dataAtualizacao = null;
		$scope.lab.id = null;
		$scope.lab.tipoResiduos = null;
		//var laboratorio = angular.toJson($scope.lab, false);
		labSvc.saveLab($scope.lab)
			.then(function(labData) {
				idSavedLab = labData.id;
			});
	}
	
	$scope.getCampi = function() {
		labSvc.getCampi()
			.then(function(campiData) {
				$scope.campi = campiData;
			});
	}
	$scope.getCampi();
}]);
