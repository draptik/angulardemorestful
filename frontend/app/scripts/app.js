'use strict';

angular.module('ngdemo', [])
  .config(function ($routeProvider, $httpProvider) {
    $routeProvider
      .when('/dummy', {templateUrl: 'views/dummy.html', controller: 'DummyCtrl'})
      .when('/user-list', {templateUrl: 'views/user-list.html', controller: 'UserListCtrl'})
      .when('/user-detail/:id', {templateUrl: 'views/user-detail.html', controller: 'UserDetailCtrl'})
      .when('/user-creation', {templateUrl: 'views/user-creation.html', controller: 'UserCreationCtrl'})
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl'
      })
      .otherwise({
        redirectTo: '/'
      });

      // http://stackoverflow.com/questions/17289195/angularjs-post-data-to-external-rest-api
      $httpProvider.defaults.useXDomain = true;
      delete $httpProvider.defaults.headers.common['X-Requested-With'];
  });
