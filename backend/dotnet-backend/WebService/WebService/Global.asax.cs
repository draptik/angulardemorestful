using System.Web;
using System.Web.Http;
using Newtonsoft.Json.Serialization;

namespace WebService
{
    public class WebApiApplication : HttpApplication
    {
        protected void Application_Start()
        {
            GlobalConfiguration.Configure(WebApiConfig.Register);

            // lower case property names in serialized JSON: http://stackoverflow.com/a/22130487/1062607
            GlobalConfiguration.Configuration
                .Formatters
                .JsonFormatter
                .SerializerSettings
                .ContractResolver = new CamelCasePropertyNamesContractResolver();
        }
    }
}