using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Runtime.Serialization;

namespace WebApplicationPath.Model
{
    [DataContract]
    public class Horario
    {
        [DataMember(Name = "id")]
        public int? Id { get; set; }        
        [DataMember(Name = "dia")]
        public String Dia { get; set; }
        [DataMember(Name = "inicio")]
        public int? Inicio { get; set; }
        [DataMember(Name = "fin")]
        public int? Fin { get; set; }        
    }
}