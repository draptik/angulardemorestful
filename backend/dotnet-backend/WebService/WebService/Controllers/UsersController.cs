using System.Collections.Generic;
using System.Web.Http;
using System.Web.Http.Cors;
using WebService.Models;
using WebService.Service;

namespace WebService.Controllers
{
    [EnableCors(origins: "http://localhost:9000", headers: "*", methods: "*")]
    public class UsersController : ApiController
    {
        private readonly IUserService userService;

        public UsersController(IUserService userService)
        {
            this.userService = userService;
        }

        public ICollection<User> Get()
        {
            return this.userService.GetAllUsers();
        }

        public User Get(int id)
        {
            return this.userService.GetById(id);
        }

        public User Put(User user)
        {
            return this.userService.UpdateUser(user);
        }

        public User Post(User user)
        {
            return this.userService.CreateNewUser(user);
        }

        public void Delete(int id)
        {
            this.userService.RemoveUserById(id);
        }
    }
}