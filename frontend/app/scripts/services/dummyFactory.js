'use strict';

var srv = angular.module('ngdemoApp.factory', ['ngResource']);

srv.factory('dummyFactory', function ($resource) {
  return $resource('http://localhost\\:8080/ngdemo/web/dummy', {}, {
    query: { method: 'GET', params: {} }
  });
});
