/* http://blog.modulus.io/nodejs-and-express-create-rest-api */

// require('underscore');

var express = require('express');

var app = express();
app.use(express.bodyParser());

//CORS middleware
var allowCrossDomain = function(req, res, next) {
  res.header('Access-Control-Allow-Origin', '*');
  res.header('Access-Control-Allow-Methods', 'GET,PUT,POST,DELETE');
  res.header('Access-Control-Allow-Headers', 'Content-Type, X-XSRF-TOKEN');

  next();
}

app.use(allowCrossDomain);


/* we'll use the same port as tomcat... */
var MY_PORT = 8080; // default: 4730

/* TODO: Create a Js 'class' or 'module' container for 'UserService'...*/

/* init users... ------------------------------------- */
var users = [];
var numberOfUsers = 10;
for (var i = 1; i <= numberOfUsers; i++) {
  var user = { id: i, firstName: 'Foo' + i, lastName: 'Bar' + i};
  users.push(user);
};

var getMaxUserId = function(array) {
  return Math.max.apply(Math, array.map(function(o) { 
    return o.id; 
  }));
};

// var getById = function(id) {
//   return find(users, function(user) { 
//     return user.id === id
//   });
// };

/* REST API =========================================== */
var baseUrl = '/ngdemo/web';

/* GET ALL -------------------------------------------- */
app.get(baseUrl + '/users', function(req, res) {
	res.json(users);
});

/* GET Dummy ------------------------------------------ */
app.get(baseUrl + '/dummy', function(req, res) {
  res.json({id: 0, firstName: 'JonFromREST', lastName: 'DoeFromREST'});
});

/* GET By Id ------------------------------------------ */
app.get(baseUrl + '/users/:id', function(req, res) {
  if(users.length <= req.params.id || req.params.id < 0) {
    res.statusCode = 404;
    return res.send('Error 404: No user found');
  }

  res.json(users[req.params.id - 1]);
});


/* POST Create ---------------------------------------- */
app.post(baseUrl + '/users', function(req, res) {
  if(!req.body.hasOwnProperty('firstName') || !req.body.hasOwnProperty('lastName')) {
    res.statusCode = 400;
    return res.send('Error 400: POST syntax incorrect.');
  }

  var newUser = {
    id: getMaxUserId(users) + 1,
    firstName : req.body.firstName,
    lastName : req.body.lastName
  };

  users.push(newUser);
  res.json(newUser);
});

/* PUT (Update) --------------------------------------- */
app.put(baseUrl + '/users/:id', function (req, res) {
  if(!req.body.hasOwnProperty('id') || !req.body.hasOwnProperty('firstName') || !req.body.hasOwnProperty('lastName')) {
    res.statusCode = 400;
    return res.send('Error 400: PUT syntax incorrect.');
  }
  
  var id = req.params.id - 1;
  users[id].firstName = req.body.firstName;
  users[id].lastName = req.body.lastName;

  res.json(users[id]);
});

/* DELETE --------------------------------------------- */
app.delete(baseUrl + '/users/:id', function(req, res) {
  if(users.length <= req.params.id) {
    res.statusCode = 404;
    return res.send('Error 404: No user found');
  }

  var index = req.params.id - 1;

  // TODO find index with id...
  
  users.splice(index, 1);
  res.json(true);
});

/* ==================================================== */

app.listen(process.env.PORT || MY_PORT);
