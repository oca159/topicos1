using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;

namespace EstudianteJSON
{
    // NOTA: puede usar el comando "Rename" del menú "Refactorizar" para cambiar el nombre de clase "Service1" en el código, en svc y en el archivo de configuración.
    public class Service1 : IService1
    {
        public string GetData(int value)
        {
            return string.Format("You entered: {0}", value);
        }

        public CompositeType GetDataUsingDataContract(CompositeType composite)
        {
            if (composite == null)
            {
                throw new ArgumentNullException("composite");
            }
            if (composite.BoolValue)
            {
                composite.StringValue += "Suffix";
            }
            return composite;
        }

        public Estudiante[] GetEstudiantes()
        {
            Estudiante e1 = new Estudiante();
            e1.Nombre = "Osvaldo";
            e1.Carrera = "Informatica";
            e1.Edad = 21;

            Estudiante e2 = new Estudiante();
            e2.Nombre = "Cristian";
            e2.Carrera = "Medicina";
            e2.Edad = 22;

            List<Estudiante> estudiantes = new List<Estudiante>();
            estudiantes.Add(e1);
            estudiantes.Add(e2);
            return estudiantes.ToArray();
        }
    }
}
