'use strict';

angular.module('ngdemoApp', [
  'ngdemoApp.mainCtrl',
  'ngdemoApp.dummyCtrl',
  'ngdemoApp.factory'
])
.config(function ($routeProvider, $httpProvider) {
  $routeProvider
  .when('/dummy', {
    templateUrl: 'views/dummy.html',
    controller: 'DummyCtrl'
  })
  .when('/', {
    templateUrl: 'views/main.html',
    controller: 'MainCtrl'
  })
  .when('/user', {
    templateUrl: 'views/user-list.html',
    controller: 'UserListCtrl'
  })
  .otherwise({
    redirectTo: '/'
  });

  // http://stackoverflow.com/questions/17289195/angularjs-post-data-to-external-rest-api
  $httpProvider.defaults.useXDomain = true;
  delete $httpProvider.defaults.headers.common['X-Requested-With'];
});
