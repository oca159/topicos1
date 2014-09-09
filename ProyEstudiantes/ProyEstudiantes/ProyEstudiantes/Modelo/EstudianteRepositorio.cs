using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace ProyEstudiantes.Modelo
{
    class EstudianteRepositorio : IRepositorio <Estudiante>
    {
        public static List<Estudiante> estudiantes = new List<Estudiante>();
        public bool Add(Estudiante e) 
        {
            bool result = true;
            try
            {
                estudiantes.Add(e);
                //....
            }
            catch
            { 
                //...
            }
            return result;
        }
        public bool Delete(Estudiante e) 
        {
            bool result = true;
            try
            {
                estudiantes.Remove(e);
                //....
            }
            catch
            {
                //...
            }
            return result;
        }
        public List<Estudiante> GetAll() 
        {
            List<Estudiante> result = null;
            try
            {
                result = estudiantes;
                //...
            }
            catch
            {
                //...
            }
            return result;
        }
    }
}
