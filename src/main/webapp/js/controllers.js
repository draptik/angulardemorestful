'use strict';

/* Controllers */

var app = angular.module('ngdemo.controllers', []);


// Clear browser cache (in development mode)
//
// http://stackoverflow.com/questions/14718826/angularjs-disable-partial-caching-on-dev-machine
app.run(function ($rootScope, $templateCache) {
    $rootScope.$on('$viewContentLoaded', function () {
        $templateCache.removeAll();
    });
});


app.controller('DummyCtrl', ['$scope', 'DummyFactory', function ($scope, DummyFactory) {
    $scope.bla = 'bla from controller';
    DummyFactory.get({}, function (dummyFactory) {
        $scope.firstname = dummyFactory.firstName;
    })
}]);

app.controller('UserListCtrl', ['$scope', 'UserFactory', function ($scope, UserFactory) {
    $scope.users = UserFactory.query();
}]);

app.controller('UserDetailCtrl', ['$scope', '$routeParams', 'UserFactory', function ($scope, $routeParams, UserFactory) {
    $scope.user = UserFactory.get({id: $routeParams.id});
}]);

app.controller('UserCreationCtrl', ['$scope', 'UserFactory', function ($scope, UserFactory) {
    // '$scope.createNewUser' is the event handler
    $scope.createNewUser = function () {
        UserFactory.save($scope.user);
    }
}]);
