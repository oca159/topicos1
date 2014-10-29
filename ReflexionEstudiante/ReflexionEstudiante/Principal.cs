using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.Drawing;
using System.Runtime.InteropServices;

namespace ReflexionEstudiante
{
    class Principal
    {
        [STAThread]
        static void Main(string[] args)
        {
            Ventana v = new Ventana();
            Application.EnableVisualStyles();
            Application.Run(v);
        }
    }
}
