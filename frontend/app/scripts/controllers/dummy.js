'use strict';

angular.module('ngdemoApp')
  .controller('DummyCtrl', ['$scope', function ($scope) {
    $scope.bla = 'bla from controller';
  }]);
