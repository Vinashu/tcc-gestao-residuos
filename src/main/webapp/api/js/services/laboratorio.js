'use strict';
/**
 * 
 */

app.service('labSvc', [ '$http', function($http) {

	this.campi = function() {
		$http.get('campus/').success(function(response) {
			return response;
		});
	}
} ]);