using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using MySql.Data.MySqlClient;
using System.Data;

namespace ProyEstudiantes.Controlador
{
    class DriverOperations
    {
        private ProyEstudiantes.Modelo.Estudiante et = new Modelo.Estudiante();
        private ProyEstudiantes.Modelo.EstudianteRepositorio IRE = new Modelo.EstudianteRepositorio();
        //private MySqlConnection conexion = new MySqlConnection("server=localhost;User Id=root;password=12345@@~~;Persist Security Info=True;database=tsci");
        private MySqlDataAdapter adaptador;
        private MySqlConnection connection;
        private String sql;
        private MySqlCommand comando = new MySqlCommand();
        Modelo.MySQLConexion conexion = new Modelo.MySQLConexion();

        public DriverOperations() {
            MySqlConnectionStringBuilder connBuilder = new MySqlConnectionStringBuilder();
            connBuilder.Database = "tsci";
            connBuilder.UserID = "root";
            connBuilder.Server = "localhost";
            connBuilder.Password = "12345";
            connBuilder.Port = 3307;
            connection = new MySqlConnection(connBuilder.ToString());
            MySqlCommand cmd = connection.CreateCommand();
        }

        public DriverOperations(string n, string ap, string am)
        {
            et.Nombre = n;
            et.APaterno = ap;
            et.AMaterno = am;
        }

        public DriverOperations(string n, string ap, string am, string m, string pg)
        {
            et.Nombre = n;
            et.APaterno = ap;
            et.AMaterno = am;
            et.Matricula = m;
            et.PromedioGeneral = pg;
        }

        public void GuardarDriverOperations() { 
            IRE.Add(et);
        }

        public void EliminarEstudiante(ProyEstudiantes.Modelo.Estudiante estudiante)
        {
            //string consulta = "";
            IRE.Delete(estudiante);
            //consulta = String.Format("delete from estudiante where ID_Estudiante={0};", id);
            //conexion.NonQuery(consulta);
        }

        public void MostrarDatosEnTabla(DataGridView dTablaDatos)
        {
           
            connection.Open();
            MySqlDataAdapter mdaDatos = new MySqlDataAdapter("SELECT * FROM estudiante;", connection);
            DataTable dtDatos = new DataTable();
            mdaDatos.Fill(dtDatos);
            dTablaDatos.DataSource = dtDatos;
            
            connection.Close();
        }

        public void actualiza(ProyEstudiantes.Modelo.Estudiante estudiante)
        {

        }
        
        
    }
}
