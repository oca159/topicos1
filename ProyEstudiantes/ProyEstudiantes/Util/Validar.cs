using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.Text.RegularExpressions;

namespace ProyEstudiantes.Util
{
    class Validar
    {
        public static bool Valida(Control.ControlCollection controles)
        {
            Regex pattern = new Regex("[a-zA-Z0-9.]+");
            foreach(Control c in controles)
            {

                //System.Console.Write("Entre al foreach");
                if (c is TextBox)
                {
                    System.Console.Write(c.Text);
                    if (pattern.IsMatch(c.Text))
                    {
                        if (pattern.Replace(c.Text, String.Empty).Length == 0)
                        {
                            System.Console.Write("Correcto");
                        }
                        else
                        {
                            return false;
                        }
                    }
                    else
                    {
                        //MessageBox.Show("Metiste caracteres invalidos");
                        return false;
                    }
                }
            }
            return true;
        }
    }
}
