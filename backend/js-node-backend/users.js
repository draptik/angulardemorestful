


function User(id, firstName, lastName) {
	this.id = id;
	this.firstName = firstName;
	this.lastName = lastName;
};

function UserRepository() {

	this.users = [];

	this.createUsers = function() {
		var numberOfUsers = 10;
		for (var i = 0; i < numberOfUsers; i++) {
			var id = i + 1;
			this.users.push(new User(id, 'Foo' + id, 'Bar' + id));
		};
		return this.users;
	};

	this.getMaxUserId = function() {
		return Math.max.apply(Math, this.users.map(function(user) { 
			return user.id; 
		}));
	};

	this.getNumberOfUsers = function() {
		return this.users.length;
	};

	this.getAll = function() {
		return this.users;
	};

	this.getById = function(id) {
		var foundUser = false;
		for (var i = 0; i < users.length; i++) {
			var user = users[i];
			if (user.id === id) {
				foundUser = true;
				return user;
			};
		};
		if (!foundUser) {
			return 'user with id ' + id + ' not found.';
		};
	};

	this.addNewUser = function(firstName, lastName) {
		var newUser = new User(this.getMaxUserId() + 1, firstName, lastName);
		this.users.push(newUser);
		return this.getById(newUser.id);
	};

	this.changeUser = function(id, firstName, lastName) {
		var user = this.getById(id);
		user.firstName = firstName;
		user.lastName = lastName;
		return user;
	};

	this.deleteUser = function(id) {
		this.users.splice(id, 1);
	};
};


/*
console.log('PrintLine Testing... :-(');

// create users 'test'
var userRepository = new UserRepository();
var users = userRepository.createUsers();
console.log('testCreateUsers: number of initial users: ' + users.length);

// new user 'test'
var newUser = userRepository.addNewUser('testFirstName', 'testLastName');
console.log('testAddNewUser: new user: id=' + newUser.id + ' firstName:' + newUser.firstName + ' lastName:' + newUser.lastName);
console.log('testAddNewUser: number of users (after adding a user): ' + userRepository.getNumberOfUsers());

// change user 'test'
var changedUser = userRepository.changeUser(1, 'changedFirstName', 'changedLastName');
console.log('testChangeUser: changed user: id=' + changedUser.id + ' firstName:' + changedUser.firstName + ' lastName:' + changedUser.lastName);
var u = userRepository.getById(1);
console.log('testChangeUser: user: id=' + u.id + ' firstName:' + u.firstName + ' lastName:' + u.lastName);
console.log('testChangeUser: number of users (after changing a user): ' + userRepository.getNumberOfUsers());

// delete user 'test'
userRepository.deleteUser(1);
console.log('testDeleteUser: number of users (after deleting a user): ' + userRepository.getNumberOfUsers());

// get all 'test'
var allUsers = userRepository.getAll();
console.log('testGetAll: size of array: ' + allUsers.length);
*/