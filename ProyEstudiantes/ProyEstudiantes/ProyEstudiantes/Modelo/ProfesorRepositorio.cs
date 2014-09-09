using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace ProyEstudiantes.Modelo
{
    class ProfesorRepositorio : IRepositorio <Profesor>
    {
        public static List<Profesor> profesores = new List<Profesor>();
        public bool Add(Profesor e)
        {
            bool result = true;
            try
            {
                profesores.Add(e);
                //....
            }
            catch
            {
                //...
            }
            return result;
        }
        public bool Delete(Profesor e)
        {
            bool result = true;
            try
            {
                profesores.Remove(e);
                //....
            }
            catch
            {
                //...
            }
            return result;
        }
        public List<Profesor> GetAll()
        {
            List<Profesor> result = null;
            try
            {
                result = profesores;
            }
            catch
            {
                //....
            }
            return result;
        }
    }
}
