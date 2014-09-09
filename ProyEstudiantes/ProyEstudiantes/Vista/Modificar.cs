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
    public partial class Modificar : Form
    {
        private int Id;
        private string Nombre;
        private string APaterno;
        private string AMaterno;
        //string Edad;
        private string Matricula;
        private string Promedio;
        private DataGridView Datos;
        private Controlador.DriverOperations DO = new Controlador.DriverOperations();
        public Modificar(DataGridView datos,string id, string nombre, string apaterno, string amaterno, string matricula, string promedio)
        {
            InitializeComponent();
            Datos = datos;
            Id = Int32.Parse(id);
            Nombre = nombre;
            APaterno = apaterno;
            AMaterno = amaterno;
            Matricula = matricula;
            Promedio = promedio;
            System.Console.Write(nombre);
            txt1.Text = nombre;
            txt2.Text = apaterno;
            txt3.Text = amaterno;
            txt4.Text = matricula;
            txt5.Text = promedio;
        }

        private void label7_Click(object sender, EventArgs e)
        {

        }

        private void Modificar_Load(object sender, EventArgs e)
        {

        }

        private void button1_Click(object sender, EventArgs e)
        {
            
            Nombre = txt1.Text;
            APaterno = txt2.Text;
            AMaterno = txt3.Text;
            Matricula = txt4.Text;
            Promedio = txt5.Text;
            ProyEstudiantes.Modelo.EstudianteRepositorio IRE = new ProyEstudiantes.Modelo.EstudianteRepositorio();
            IRE.Update(Id, Nombre, APaterno, AMaterno, Promedio, Matricula);
            DO.MostrarDatosEnTabla(Datos);
            Close();
        }
    }
}
