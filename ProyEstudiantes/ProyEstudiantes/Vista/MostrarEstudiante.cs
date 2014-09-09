using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace ProyEstudiantes.Vista
{
    public partial class MostrarEstudiante : Form
    {
        private Controlador.ControladorEstudiante controlador = new Controlador.ControladorEstudiante();
        public MostrarEstudiante()
        {
            InitializeComponent();
            controlador.MostrarDatosEnTabla(dataGridView1);
           
        }

        private void dataGridView1_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {

        }

        private void MostrarEstudiante_Load(object sender, EventArgs e)
        {

        }

        private void button1_Click(object sender, EventArgs e)
        {
            Close();
        }
    }
}
