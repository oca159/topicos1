using System;
using System.Collections.Generic;
using System.Linq;
using System.Windows.Forms;
using System.Text;
using System.Data;

namespace ProyEstudiantes.Modelo
{
    class EstudianteRepositorio : IRepositorio <Estudiante>
    {
        public static List<Estudiante> estudiantes = new List<Estudiante>();
        Modelo.MySQLConexion conexion = new Modelo.MySQLConexion();

        public bool Add(Estudiante e) 
        {
            bool result = true;
            string consulta;
            try
            {
                estudiantes.Add(e);
                consulta = String.Format("insert into estudiante (Nombre,APaterno,AMaterno,Matricula,Promedio_General) values ('{0}','{1}','{2}','{3}','{4}')", e.Nombre,e.APaterno,e.AMaterno,e.Matricula,e.PromedioGeneral);
                conexion.NonQuery(consulta);

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
            string consulta = "";
            try
            {
                estudiantes.Remove(e);
                consulta = String.Format("delete from estudiante where ID_Estudiante={0};",e.Id);
                conexion.NonQuery(consulta);
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
            string query = "select * from estudiante;";
            DataSet dr = conexion.Query(query, "estudiantes");

            List<Estudiante> estudiantes = dr.Tables[0].Rows.Cast<DataRow>().
                        Select(

                        x =>

                        new Estudiante
                        {
                            Id = Int32.Parse(x[0].ToString()),
                            Nombre = x[1].ToString(),
                            APaterno = x[2].ToString(),
                            AMaterno = x[3].ToString(),
                            Edad = 10,
                            Matricula = x[5].ToString(),
                            PromedioGeneral = x[6].ToString()
                        }
            ).ToList<Estudiante>();
            return estudiantes; 
        }

        public Estudiante GetById(int id)
        {
            Estudiante usuario = null;
            string query = "select * from estudiante where ID_Estudiante = {0};";
            query = String.Format(query, id);
            System.Console.Write(query);
            DataSet dr = conexion.Query(query, "estudiantes");

            usuario = dr.Tables[0].Rows.Cast<DataRow>().
                        Select(
                             x =>

                         new Estudiante
                         {
                             Id = Int32.Parse(x[0].ToString()),
                             Nombre = x[1].ToString(),
                             APaterno = x[2].ToString(),
                             AMaterno = x[3].ToString(),
                             Edad = 10,
                             Matricula = x[5].ToString(),
                             PromedioGeneral = x[6].ToString()
                         }
            ).ToList<Estudiante>()[0];
            return usuario; 
        }

        public bool Update(int id, string nombre, string apaterno, string amaterno, string promedio, string matricula, int edad = 10)
        {
            bool result = true;
            string consulta = "";
            try
            {
                //estudiantes.Remove(e);
                consulta = String.Format("update estudiante set Nombre='{0}', APaterno='{1}', Amaterno='{2}', Edad=10, Matricula='{3}', Promedio_General='{4}' where ID_Estudiante={5};", nombre,apaterno,amaterno,matricula,promedio,id);
                System.Console.Write(consulta);
                conexion.NonQuery(consulta);
                //....
            }
            catch
            {
                //...
            }
            return result;
        }
    }
}
