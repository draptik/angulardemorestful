'use strict';

describe('Controller: DummyCtrl', function () {

  // TODO! The tested module ('dummyCtrl') has a dependency on the module 'dummyFactory'.
  // Since I don't know how to inject mocked dependencies using karma/jasmin
  // yet, I'll bootstrap the complete application by loading the 'ngdemoApp' module...
  beforeEach(module('ngdemoApp'));

  // load the controller's module
  beforeEach(module('ngdemoApp.dummyCtrl'));

  var DummyCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    DummyCtrl = $controller('DummyCtrl', {
      $scope: scope
    });
  }));

  it('should attach a bla string to the scope', function () {
    expect(scope.bla).toBe('bla from controller');
  });
});
