using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;
using WebApplicationPath.Model;

namespace WebApplicationPath
{
    // NOTA: puede usar el comando "Rename" del menú "Refactorizar" para cambiar el nombre de interfaz "IServiceRutas" en el código y en el archivo de configuración a la vez.
    [ServiceContract]
    public interface IServiceRutas
    {
        [OperationContract]
        [WebGet(
            UriTemplate = "GetRutaByLinea?linea={linea}",
            ResponseFormat = WebMessageFormat.Json,
            RequestFormat = WebMessageFormat.Json
        )]
        Ruta[] GetRutaByLinea(int linea);

        [OperationContract]
        [WebGet(
            UriTemplate = "GetRutas",
            ResponseFormat = WebMessageFormat.Json,
            RequestFormat = WebMessageFormat.Json
        )]
        Ruta[] GetRutas();

        [OperationContract]
        [WebGet(
            UriTemplate = "GetRutaByName?nombre={nombre}",
            ResponseFormat = WebMessageFormat.Json,
            RequestFormat = WebMessageFormat.Json
        )]
        Ruta[] GetRutaByName(String nombre);

        [OperationContract]
        [WebGet(
            UriTemplate = "GetEstacionByLatLong",
            ResponseFormat = WebMessageFormat.Json,
            RequestFormat = WebMessageFormat.Json
        )]
        Estacion[] GetEstacionByLatLong();

        [OperationContract]
        [WebGet(
            UriTemplate = "GetHorarios",
            ResponseFormat = WebMessageFormat.Json,
            RequestFormat = WebMessageFormat.Json
        )]
        Horario[] GetHorarios();
    }
}
