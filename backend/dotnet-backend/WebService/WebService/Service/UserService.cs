using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Linq;
using WebService.Models;

namespace WebService.Service
{
    public class UserService : IUserService
    {
        public UserService()
        {
            this.Users = new Collection<User>();
            this.CreateUsers();
        }

        private ICollection<User> Users { get; set; }

        public ICollection<User> GetAllUsers()
        {
            return this.Users;
        }

        public User GetById(int userId)
        {
            return this.Users.SingleOrDefault(x => x.Id.Equals(userId));
        }

        public User UpdateUser(User user)
        {
            var u = this.Users.SingleOrDefault(x => x.Id.Equals(user.Id));
            if (u != null) {
                u.FirstName = user.FirstName;
                u.LastName = user.LastName;
            }
            return u;
        }

        public User CreateNewUser(User user)
        {
            var newUser = new User
            {
                Id = this.Users.Max(x => x.Id) + 1,
                FirstName = user.FirstName,
                LastName = user.LastName
            };

            this.Users.Add(newUser);

            return newUser;
        }

        public void RemoveUserById(int userId)
        {
            this.Users.Remove(this.Users.SingleOrDefault(x => x.Id.Equals(userId)));
        }

        private void CreateUsers()
        {
            const int numberOfUsers = 10;
            for (int id = 1; id <= numberOfUsers; id++) {
                this.Users.Add(new User {Id = id, FirstName = "Foo" + id, LastName = "Bar" + id});
            }
        }
    }
}