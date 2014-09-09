using System;
using System.Windows.Forms;
using ProyEstudiantes.Vista;
using System.Collections.Generic;

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
            //ProyEstudiantes.Controlador.DriverOperations DO = new Controlador.DriverOperations(tNombre.Text, tPaterno.Text, tMaterno.Text, tMatricula.Text, tPromGeneral.Text);
            //DO.GuardarDriverOperations();
            if (Util.Validar.Valida(this.tabControl1.TabPages[0].Controls))
            {
                ProyEstudiantes.Controlador.DriverOperations DO = new Controlador.DriverOperations(tNombre.Text, tPaterno.Text, tMaterno.Text, tMatricula.Text, tPromGeneral.Text);
                DO.GuardarDriverOperations();
            }
            else
            {
                MessageBox.Show(this, "Campos incorrectos", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        private void tabPage1_Click(object sender, EventArgs e)
        {

        }

        private void label9_Click(object sender, EventArgs e)
        {

        }

        private void tabPage2_Click(object sender, EventArgs e)
        {

        }

        private void tabControl1_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (tabControl1.SelectedIndex == 1)
            {
                Modelo.EstudianteRepositorio rEstudiante = new Modelo.EstudianteRepositorio();
                List<Modelo.Estudiante>estudiantes = rEstudiante.GetAll();
                //MessageBox.Show(estudiantes[0].Edad);
                cmbMatricula.DataSource = rEstudiante.GetAll();
                //cmbMatricula.SelectedText = "Matricula";
                //cmbMatricula.SelectedValue = "Id";
                //MessageBox.Show("Cambio de pestaña");

            }
        }

        private void fEstudiantes_Load(object sender, EventArgs e)
        {

        }
    }
}
