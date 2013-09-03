'use strict';

angular.module('ngdemoApp.dummyCtrl', [])
.controller('DummyCtrl', function ($scope, dummyFactory) {
  $scope.bla = 'bla from controller';
  $scope.foo = dummyFactory.firstName;
});
