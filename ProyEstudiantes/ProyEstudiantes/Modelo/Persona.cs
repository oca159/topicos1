using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace ProyEstudiantes
{
    public class Persona
    {
        public int Id { get; set; }
        public String Nombre { get; set; }
        public String APaterno { get; set; }
        public String AMaterno { get; set; }

        private int edad;
        public int Edad
        {
            get
            {
                return edad;
            }

            set
            {
                edad = value;
            }
        }
    }
}
