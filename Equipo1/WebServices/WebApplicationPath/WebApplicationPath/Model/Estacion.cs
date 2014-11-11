using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Runtime.Serialization;

namespace WebApplicationPath.Model
{
    [DataContract]
    public class Estacion
    {
        [DataMember(Name = "nombre")]
        public String Nombre { get; set; }
        [DataMember(Name = "latitud")]
        public String Latitud { get; set; }
        [DataMember(Name = "longitud")]
        public String Longitud { get; set; }
    }
}