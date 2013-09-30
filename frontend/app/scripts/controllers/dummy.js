'use strict';

angular.module('ngdemoApp.dummyCtrl', [])
.controller('DummyCtrl', function ($scope, $log, dummyFactory) {
  $log.log('DummyCtrl loaded...');

  $scope.bla = 'bla from controller';

  dummyFactory.query({}, function (data) {
    $scope.foo = data.firstName;
  });
});
