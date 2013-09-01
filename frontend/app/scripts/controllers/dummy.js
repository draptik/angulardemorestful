'use strict';

angular.module('ngdemoApp')
.controller('DummyCtrl', function ($scope, dummyFactory) {
  $scope.bla = 'bla from controller';
  $scope.foo = dummyFactory.someMethod();
});
