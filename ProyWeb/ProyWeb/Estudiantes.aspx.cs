using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace ProyWeb
{
    public partial class Estudiantes : System.Web.UI.Page
    {
        private int x;
        private string nombre, apaterno, amaterno, matricula, promedio;
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected void BGuardar_Click(object sender, EventArgs e)
        {   
            nombre = TxtNombre.Text;
            apaterno = TxtAPaterno.Text;
            amaterno = TxtAMaterno.Text;
            matricula = TxtMatricula.Text;
            promedio = TxtPromedio.Text;

            ProyEstudiantes.Controlador.DriverOperations Controlador = new ProyEstudiantes.Controlador.DriverOperations(nombre,apaterno,amaterno, matricula, promedio);
            Controlador.Guardar();
            
            //try
            //{

            //    x = Convert.ToInt32(ViewState["x"].ToString());
                
                
            //}
            //catch {
            //    //para tener valores estaticos a nivel global de aplicacion
            //    Application["contador"] = 10;
            //    //para mantener los datos dentro del usuario
            //    Session["xs"] = "Valor";
            //    //para mantener los datos dentro de la misma vista
            //    ViewState["x"] = x++;
            //    TxtNombre.Text = x.ToString();

            //}
        }
    }
}