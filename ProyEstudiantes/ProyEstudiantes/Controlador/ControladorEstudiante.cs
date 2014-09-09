using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;


namespace ProyEstudiantes.Controlador
{
    public class ControladorEstudiante 
    {
        private ProyEstudiantes.Modelo.Estudiante estudiante = new Modelo.Estudiante();
        private ProyEstudiantes.Modelo.EstudianteRepositorio repositorio = new Modelo.EstudianteRepositorio();

        public ControladorEstudiante(String nombre, String APaterno, String AMaterno)
        {
            estudiante.Nombre = nombre;
            estudiante.APellidoPaterno = APaterno;
            estudiante.APellidoMaterno = AMaterno;
            
        }
        public ControladorEstudiante() { }

        public void Guardar()
        {

            repositorio.Add(estudiante);
            
        }

        public void MostrarDatosEnTabla(DataGridView tablaDatos)
        {
            List<Modelo.Estudiante> ListaEstudiante = new List<Modelo.Estudiante>();
            ListaEstudiante = repositorio.GetAll();
            System.Console.Write(ListaEstudiante.Count);
            foreach(Modelo.Estudiante Estudiante in ListaEstudiante)
            {
                DataGridViewRow fila = (DataGridViewRow)tablaDatos.Rows[0].Clone();
                fila.Cells[0].Value = Estudiante.Nombre;
                fila.Cells[1].Value = Estudiante.APellidoPaterno;
                fila.Cells[2].Value = Estudiante.APellidoMaterno;
                tablaDatos.Rows.Add(fila);
                System.Console.Write(Estudiante.Nombre);
            }
        }
    }

   

}
