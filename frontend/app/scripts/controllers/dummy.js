'use strict';

angular.module('ngdemo')
  .controller('DummyCtrl', ['$scope', function ($scope) {
    $scope.bla = 'bla from controller';
  }]);
