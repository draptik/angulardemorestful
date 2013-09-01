'use strict';

angular.module('ngdemoApp.dummy', ['ngdemoApp.service'])
.controller('DummyCtrl', function ($scope, dummyFactory) {
  $scope.bla = 'bla from controller';
  $scope.foo = dummyFactory.firstName;
});
