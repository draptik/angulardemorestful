/* http://blog.modulus.io/nodejs-and-express-create-rest-api */

var express = require('express');

var app = express();
app.use(express.bodyParser());

var MY_PORT = 4730;

var users = [];
var numberOfUsers = 10;
for (var i = 1; i <= numberOfUsers; i++) {
  var user = { id: i, firstName: 'Foo' + i, lastName: 'Bar' + i};
  users.push(user);
};

/* REST API =========================================== */

/* GET ALL -------------------------------------------- */
app.get('/users', function(req, res) {
	res.json(users);
});

/* GET Dummy ------------------------------------------ */
app.get('/user', function(req, res) {
  res.json(users[0]);
});

/* GET By Id ------------------------------------------ */
app.get('/user/:id', function(req, res) {
  if(users.length <= req.params.id || req.params.id < 0) {
    res.statusCode = 404;
    return res.send('Error 404: No user found');
  }

  res.json(users[req.params.id]);
});

/* POST Create ---------------------------------------- */
app.post('/user', function(req, res) {
  if(!req.body.hasOwnProperty('firstName') ||
     !req.body.hasOwnProperty('lastName')) {
    res.statusCode = 400;
    return res.send('Error 400: Post syntax incorrect.');
  }

  /* TODO: add correct id... */
  var newUser = {
    firstName : req.body.firstName,
    lastName : req.body.lastName
  };

  users.push(newUser);
  res.json(newUser);
});


/* DELETE --------------------------------------------- */
app.delete('/user/:id', function(req, res) {
  if(users.length <= req.params.id) {
    res.statusCode = 404;
    return res.send('Error 404: No user found');
  }

  users.splice(req.params.id, 1);
  res.json(true);
});

/* ==================================================== */

app.listen(process.env.PORT || MY_PORT);
