using System.Web.Http;
using System.Web.Http.Cors;
using WebService.Models;

namespace WebService.Controllers
{
    [EnableCors(origins: "http://localhost:9000", headers: "*", methods: "*")]
    public class DummyController : ApiController
    {
        public Dummy Get()
        {
            return new Dummy {Id = 0, FirstName = "JonFromREST", LastName = "Doe"};
        }
    }
}