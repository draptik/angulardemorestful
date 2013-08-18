'use strict';

// Declare app level module which depends on filters, and services
angular.module('ngdemo', ['ngdemo.filters', 'ngdemo.services', 'ngdemo.directives', 'ngdemo.controllers'])
	.config(['$routeProvider', '$httpProvider', function ($routeProvider, $httpProvider) {
        $routeProvider.when('/dummy', {templateUrl: 'partials/dummy.html', controller: 'DummyCtrl'});
        $routeProvider.when('/user-list', {templateUrl: 'partials/user-list.html', controller: 'UserListCtrl'});
        $routeProvider.when('/user-detail/:id', {templateUrl: 'partials/user-detail.html', controller: 'UserDetailCtrl'});
        $routeProvider.when('/user-creation', {templateUrl: 'partials/user-creation.html', controller: 'UserCreationCtrl'});
        $routeProvider.otherwise({redirectTo: '/dummy'});

        // http://stackoverflow.com/questions/17289195/angularjs-post-data-to-external-rest-api
        $httpProvider.defaults.useXDomain = true;
		delete $httpProvider.defaults.headers.common['X-Requested-With'];
    }]);
