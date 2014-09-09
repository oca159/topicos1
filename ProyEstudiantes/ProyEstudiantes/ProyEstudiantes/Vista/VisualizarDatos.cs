using System;
using System.Windows.Forms;

namespace ProyEstudiantes.Vista
{
    public partial class VisualizarDatos : Form
    {
        private Controlador.DriverOperations DO = new Controlador.DriverOperations();
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

        
    }
}
