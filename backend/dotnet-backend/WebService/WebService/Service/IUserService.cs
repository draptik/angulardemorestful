using System.Collections.Generic;
using WebService.Models;

namespace WebService.Service
{
    public interface IUserService
    {
        ICollection<User> GetAllUsers();
        User GetById(int userId);
        User UpdateUser(User user);
        User CreateNewUser(User user);
        void RemoveUserById(int userId);
    }
}