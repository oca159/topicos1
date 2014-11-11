using System;
using System.Collections.Generic;
using System.Linq;
using System.Windows.Forms;

namespace Metro_Desktop
{
    static class Program
    {
        /// <summary>
        /// The main entry point for the application.
        /// </summary>
        [STAThread]
        static void Main()
        {
            //Deserializar.shake();
            Application.EnableVisualStyles();
            Application.SetCompatibleTextRenderingDefault(false);
            Application.Run(new Mapa());
        }
    }
}
