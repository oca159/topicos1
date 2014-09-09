using System;
using System.Windows.Forms;

namespace ProyEstudiantes.Vista
{
    public partial class VisualizarDatos : Form
    {
        private Controlador.DriverOperations DO = new Controlador.DriverOperations();
        private string id_estudiante;
        private string Nombre;
        private string APaterno;
        private string AMaterno;
        //string Edad;
        private string Matricula;
        private string Promedio;
        public VisualizarDatos()
        {
            InitializeComponent();
            DO.MostrarDatosEnTabla(dTablaDatos);
        }

        private void dTablaDatos_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {
            //..
        }

        private void button1_Click(object sender, EventArgs e)
        {
            Close();  
        }

        private void bEliminar_Click(object sender, EventArgs e)
        {
            int index = dTablaDatos.CurrentCell.RowIndex;
            //System.Console.Write(index.ToString());
            id_estudiante = dTablaDatos.Rows[index].Cells[0].Value.ToString();
            Nombre = dTablaDatos.Rows[index].Cells[1].Value.ToString();
            APaterno = dTablaDatos.Rows[index].Cells[2].Value.ToString();
            AMaterno = dTablaDatos.Rows[index].Cells[3].Value.ToString();
            //Edad = dTablaDatos.Rows[index].Cells[4].Value.ToString();
            Matricula = dTablaDatos.Rows[index].Cells[5].Value.ToString();
            Promedio = dTablaDatos.Rows[index].Cells[6].Value.ToString();
            System.Console.Write(id_estudiante);
            ProyEstudiantes.Modelo.EstudianteRepositorio IRE = new ProyEstudiantes.Modelo.EstudianteRepositorio();
            ProyEstudiantes.Modelo.Estudiante estudiante = IRE.GetById(Int32.Parse(id_estudiante));
            DO.EliminarEstudiante(estudiante);
            //DO.EliminarEstudiante(Int32.Parse(id_estudiante));
            DO.MostrarDatosEnTabla(dTablaDatos);
        }

        private void bModificar_Click(object sender, EventArgs e)
        {
            int index = dTablaDatos.CurrentCell.RowIndex;
            id_estudiante = dTablaDatos.Rows[index].Cells[0].Value.ToString();
            Nombre = dTablaDatos.Rows[index].Cells[1].Value.ToString();
            APaterno = dTablaDatos.Rows[index].Cells[2].Value.ToString();
            AMaterno = dTablaDatos.Rows[index].Cells[3].Value.ToString();
            Matricula = dTablaDatos.Rows[index].Cells[5].Value.ToString();
            Promedio = dTablaDatos.Rows[index].Cells[6].Value.ToString();
            Form modificar = new Modificar(dTablaDatos,id_estudiante, Nombre,APaterno,AMaterno,Matricula,Promedio);
            modificar.Show();
            //Close();
            
        }

        
    }
}
