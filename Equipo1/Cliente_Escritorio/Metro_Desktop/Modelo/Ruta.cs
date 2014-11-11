using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Runtime.Serialization;
using System.Runtime.Serialization.Json;

namespace Metro_Desktop.Modelo
{
    [DataContract]
    public class Ruta
    {
        [DataMember(Name = "id")]
        public int? Id { get; set; }
        [DataMember(Name = "numero")]
        public int? Numero { get; set; }
        [DataMember(Name = "linea")]
        public int? Linea { get; set; }
        [DataMember(Name = "nombre")]
        public String Nombre { get; set; }
        [DataMember(Name = "latitud")]
        public String Latitud { get; set; }
        [DataMember(Name = "longitud")]
        public String Longitud { get; set; }
        [DataMember(Name = "afluencia")]
        public String Afluencia { get; set; }
        

    }
}
