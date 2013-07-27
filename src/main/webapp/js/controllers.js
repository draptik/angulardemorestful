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

app.controller('UserListCtrl', ['$scope', 'UsersFactory', 'UserFactory', '$location',
    function ($scope, UsersFactory, UserFactory, $location) {

        $scope.users = UsersFactory.query();

        // callback for ng-click 'editUser':
        $scope.editUser = function (userId) {
            $location.path('/user-detail/' + userId);
        }

        // callback for ng-click 'deleteUser':
        $scope.deleteUser = function (userId) {
            UserFactory.delete({ id: userId });
            $scope.users = UsersFactory.query();
        }

        $scope.users = UsersFactory.query();
    }]);

app.controller('UserDetailCtrl', ['$scope', '$routeParams', 'UserFactory', '$location',
    function ($scope, $routeParams, UserFactory, $location) {

        $scope.user = UserFactory.show({id: $routeParams.id});

        // callback for ng-click 'updateUser':
        $scope.updateUser = function () {
            UserFactory.update($scope.user);
            $location.path('/user-list');
        }

        // callback for ng-click 'cancel':
        $scope.cancel = function () {
            $location.path('/user-list');
        }
    }]);

app.controller('UserCreationCtrl', ['$scope', 'UsersFactory',
    function ($scope, UsersFactory) {

        // callback for ng-click 'createNewUser':
        $scope.createNewUser = function () {
            UsersFactory.create($scope.user);
        }
    }]);
