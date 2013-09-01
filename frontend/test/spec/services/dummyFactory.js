'use strict';

describe('Service: dummyFactory', function () {

  // load the service's module
  beforeEach(module('ngdemoApp'));

  // instantiate service
  var dummyFactory;
  beforeEach(inject(function (_dummyFactory_) {
    dummyFactory = _dummyFactory_;
  }));

  it('should do something', function () {
    expect(!!dummyFactory).toBe(true);
  });

});
