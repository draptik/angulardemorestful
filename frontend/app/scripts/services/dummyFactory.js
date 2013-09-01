'use strict';

angular.module('ngdemoApp.service', [])
  .factory('dummyFactory', function () {
    // Service logic
    // ...

    var meaningOfLife = 42;

    // Public API here
    return {
      someMethod: function () {
        return meaningOfLife;
      }
    };
  });
