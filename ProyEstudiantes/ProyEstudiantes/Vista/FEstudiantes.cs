using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using ProyEstudiantes.Vista;

namespace ProyEstudiantes
{
    public partial class FEstudiantes : Form
    {
        public FEstudiantes()
        {
            InitializeComponent();
        }

        private void BAceptar_Click(object sender, EventArgs e)
        {

            ProyEstudiantes.Controlador.ControladorEstudiante controlador = new Controlador.ControladorEstudiante(TNombre.Text, TApellidoP.Text,TApellidoM.Text);
            controlador.Guardar();
            

        }

        private void FEstudiantes_Load(object sender, EventArgs e)
        {

        }

        private void BCancelar_Click(object sender, EventArgs e)
        {
            Close();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            Form mostrarDatos = new MostrarEstudiante();
            mostrarDatos.Show();
        }
    }
}
