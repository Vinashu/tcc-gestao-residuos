/**
 * 
 */
angular.module('underscore', []);
angular.module('underscore').service('_', [ '$window', function($window) {
	return $window._;
} ]);