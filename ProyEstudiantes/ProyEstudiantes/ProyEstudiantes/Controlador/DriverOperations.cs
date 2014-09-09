using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace ProyEstudiantes.Controlador
{
    class DriverOperations
    {
        private ProyEstudiantes.Modelo.Estudiante et = new Modelo.Estudiante();
        private ProyEstudiantes.Modelo.EstudianteRepositorio IRE = new Modelo.EstudianteRepositorio();

        public DriverOperations() { }

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

        public void GuardarDriverOperations() { IRE.Add(et);  }

        public void MostrarDatosEnTabla(DataGridView dTablaDatos)
        {
            List<Modelo.Estudiante> lEst = new List<Modelo.Estudiante>();
            lEst = IRE.GetAll();

            foreach (Modelo.Estudiante Estudiante in lEst)
            {
                DataGridViewRow row = (DataGridViewRow)dTablaDatos.Rows[0].Clone();
                row.Cells[0].Value = Estudiante.Nombre;
                row.Cells[1].Value = Estudiante.APaterno;
                row.Cells[2].Value = Estudiante.AMaterno;
                row.Cells[3].Value = Estudiante.PromedioGeneral;
                row.Cells[4].Value = Estudiante.Matricula;

                dTablaDatos.Rows.Add(row);
            }
        }
        
        
    }
}
