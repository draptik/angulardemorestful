Jersey
======

Setting up RESTful web service using jersey library: http://www.mkyong.com/webservices/jax-rs/jersey-hello-world-example/
Setting up RESTful web service with jersey and returning JSON: // http://www.mkyong.com/webservices/jax-rs/json-example-with-jersey-jackson/

Guice and Jersey: https://sites.google.com/a/athaydes.com/renato-athaydes//posts/jersey_guice_rest_api

Another REST tutorial by Vogella: http://www.vogella.com/articles/REST/


Jackson
=======

Problem: Return lists from the REST API using Jersey results in something like this:

{"user": [{ "firstName" : "Foo1", "lastName" . "Bar1" } , { "firstName": ... }]}

This in not the array that AngularJS wants. It should look like this (no "user" tag at the beginning):

[{ "firstName" : "Foo1", "lastName" . "Bar1" } , { "firstName": ... }]


This site describes the problem: http://statelessprime.blogspot.de/2012/12/jax-rs-as-json-provider-for-angularjs.html

Simplest solution: Using Jackson. Found here: http://stackoverflow.com/questions/2199453/how-can-i-customize-serialization-of-a-list-of-jaxb-objects-to-json

How to integrate Jackson with Guice: http://blog.palominolabs.com/2011/08/15/a-simple-java-web-stack-with-guice-jetty-jersey-and-jackson/

