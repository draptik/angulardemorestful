'use strict';

angular.module('ngdemoApp', [
  'ngdemoApp.services',
  'ngdemoApp.controllers'
  ])
.config(function ($routeProvider, $httpProvider) {
  $routeProvider.when('/dummy', {templateUrl: 'views/dummy.html', controller: 'DummyCtrl'});
  $routeProvider.when('/user-list', {templateUrl: 'views/user-list.html', controller: 'UserListCtrl'});
  $routeProvider.when('/user-detail/:id', {templateUrl: 'views/user-detail.html', controller: 'UserDetailCtrl'});
  $routeProvider.when('/user-creation', {templateUrl: 'views/user-creation.html', controller: 'UserCreationCtrl'});
  $routeProvider.otherwise({redirectTo: '/dummy'});

  /* CORS... */
  /* http://stackoverflow.com/questions/17289195/angularjs-post-data-to-external-rest-api */
  $httpProvider.defaults.useXDomain = true;
  delete $httpProvider.defaults.headers.common['X-Requested-With'];
});
