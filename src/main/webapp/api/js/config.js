// config

var app = angular.module('app').config(
		[
				'$controllerProvider',
				'$compileProvider',
				'$filterProvider',
				'$provide',
				function($controllerProvider, $compileProvider,
						$filterProvider, $provide) {

					// lazy controller, directive and service
					app.controller = $controllerProvider.register;
					app.directive = $compileProvider.directive;
					app.filter = $filterProvider.register;
					app.factory = $provide.factory;
					app.service = $provide.service;
					app.constant = $provide.constant;
					app.value = $provide.value;
				} ])

.filter("asDate", function() {
	return function(input) {
		return new Date(input);
	}
}).config(function($mdDateLocaleProvider) {
	$mdDateLocaleProvider.formatDate = function(date) {
		return date ? moment(date).format('DD/MM/YYYY') : '';
	};
	$mdDateLocaleProvider.parseDate = function(dateString) {
		  var m = moment(dateString, 'DD/MM/YYYY', true);
		  return m.isValid() ? m.toDate() : new Date(NaN);
		};
	
}).config([ '$translateProvider', function($translateProvider) {
	// Register a loader for the static files
	// So, the module will search missing translation tables under the specified
	// urls.
	// Those urls are [prefix][langKey][suffix].
	$translateProvider.useStaticFilesLoader({
		prefix : 'l10n/',
		suffix : '.js'
	});
	// Tell the module what language to use by default
	$translateProvider.preferredLanguage('en');
	// Tell the module to store the language in the local storage
	$translateProvider.useLocalStorage();
} ]);

var underscore = angular.module('underscore', []);
underscore.factory('_', function() {
	return window._; // assumes underscore has already been loaded on the
	// page
});