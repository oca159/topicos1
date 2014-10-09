using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Servidor
{
    public class Dato
    {
        public string archivo { get; set; }
        public long longitud { get; set; }
        public Dato()
        {
            archivo = "";
            longitud = 0;
        }
    }
}
