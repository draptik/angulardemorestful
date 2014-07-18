using System.Net.Http.Headers;
using System.Web.Http;
using Microsoft.Practices.Unity;
using WebService.IoC;
using WebService.Service;

namespace WebService
{
    public static class WebApiConfig
    {
        public static void Register(HttpConfiguration config)
        {
            // IoC container
            //
            // http://www.asp.net/web-api/overview/extensibility/using-the-web-api-dependency-resolver
            var container = new UnityContainer();
            // Note: for this demo we want the user service to be a singleton ('ContainerControlledLifetimeManager' in Unity syntax)
            container.RegisterType<IUserService, UserService>(new ContainerControlledLifetimeManager());
            config.DependencyResolver = new UnityResolver(container);

            // Web API configuration and services
            
            config.EnableCors();

            // Return JSON instead of XML http://stackoverflow.com/a/13277616/1062607
            config.Formatters.JsonFormatter.SupportedMediaTypes.Add(new MediaTypeHeaderValue("text/html"));

            // Web API routes
            config.MapHttpAttributeRoutes();

            const string baseUrl = "ngdemo/web";

            config.Routes.MapHttpRoute(
                name: "DefaultApi",
                routeTemplate: baseUrl + "/{controller}/{id}",
                defaults: new { id = RouteParameter.Optional }
            );
        }
    }
}
