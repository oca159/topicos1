using System;
using System.Windows.Forms;
using ProyEstudiantes.Vista;

namespace ProyEstudiantes
{
    public partial class fEstudiantes : Form
    {
        public fEstudiantes()
        {
            InitializeComponent();
        }

        private void label1_Click(object sender, EventArgs e)
        {

        }

        private void textBox2_TextChanged(object sender, EventArgs e)
        {

        }

        private void bAceptar_Click(object sender, EventArgs e)
        {                        
            Application.Exit();
            //Guardar...
        }

        private void bMostrar_Click(object sender, EventArgs e)
        {
            Form fVD = new VisualizarDatos();
            fVD.Show();
        }

        private void bGuardar_Click(object sender, EventArgs e)
        {
            ProyEstudiantes.Controlador.DriverOperations DO = new Controlador.DriverOperations(tNombre.Text, tPaterno.Text, tMaterno.Text, tMatricula.Text, tPromGeneral.Text);
            DO.GuardarDriverOperations();
        }
    }
}
