using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.Net;
using System.Web;
namespace NavegadorWeb
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void BAceptar_Click(object sender, EventArgs e)
        {
            var c = new WebClient();
            string q = txtBusqueda.Text;
            string json = c.DownloadString("https://www.googleapis.com/customsearch/v1?key=AIzaSyBZgRDxkJlAO9I5FJK9PzOkA6cM2Wlrrc4&cx=012978004457506759055:hwqaznnytyi&q=" + q);
            //Console.WriteLine(json);
            var objetos = new System.Web.Script.Serialization.JavaScriptSerializer().Deserialize<object>(json);
            //System.Console.WriteLine(objetos);
            Dictionary<string,object> dictionary = (Dictionary<string,object>) objetos;
            var qtmp = dictionary.Where(x => x.Key.Equals("items"));
            
            var qtmp1 = from t in dictionary
                        where t.Key.Equals("items")
                        select t;
            List<string> titulos = new List<string>();
            List<string> links = new List<string>();
            foreach (var x in qtmp)
            {
                //Console.WriteLine("{0}: {1} ", x.Key, x.Value);
                Object[] lista = (Object[])x.Value;
                foreach (Dictionary<string,object> d in lista)
                {
                    var qtmp2 = from t in d
                                where t.Key.Equals("title")
                                select t;
                    var qtmp3 = from t in d
                                where t.Key.Equals("link")
                                select t;
                    foreach (var j in qtmp2)
                    {
                        string v = (string)j.Value;
                        //Console.WriteLine("{0}: {1} ", j.Key, j.Value);
                        titulos.Add(v);
                    }
                    foreach (var y in qtmp3)
                    {
                        string v1 = (string)y.Value;
                        //Console.WriteLine("{0}: {1} ", y.Key, y.Value);
                        links.Add(v1);
                    }
                }
            }
            for (int i = 0; i < titulos.Count; i++)
            {
                Console.WriteLine("{0} : {1}",titulos[i],links[i]);
            }
            Lista_links l = new Lista_links(titulos,links);
            l.Show();
        }
    }
}
