'use strict';

/* Services */

var services = angular.module('ngdemo.services', ['ngResource']);

services.factory('UserFactory', function ($resource) {
    return $resource('/ngdemo/web/dummy', {}, {
        query: {
            method: 'GET',
            params: {},
            isArray: false
        }
    })
});
