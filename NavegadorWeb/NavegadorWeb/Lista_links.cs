using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace NavegadorWeb
{
    public partial class Lista_links : Form
    {
        private List<string> titulos;
        private List<string> links;
        public Lista_links()
        {
            InitializeComponent();
        }

        public Lista_links(List<string> titulos, List<string>links)
        {
            this.titulos = titulos;
            this.links = links;
            InitializeComponent();
        }

        private void Lista_links_Load(object sender, EventArgs e)
        {
            lista.View = View.Details;
            lista.GridLines = true;
            string[] arr= new string[2];
            ListViewItem itm;
            for (int i = 0; i < titulos.Count; i++)
            {
                arr[0] = titulos[i];
                arr[1] = links[i];
                itm = new ListViewItem(arr);
                lista.Items.Add(itm);
            }
            //System.Diagnostics.Process.Start("http://www.microsoft.com");   
        }

        private void lista_SelectedIndexChanged(object sender, EventArgs e)
        {

        }

        private void lista_ItemSelectionChanged(object sender, ListViewItemSelectionChangedEventArgs e)
        {
            foreach (var x in lista.SelectedItems)
            {
                ListViewItem j = (ListViewItem)x;
                //Console.WriteLine(j.SubItems[1].Text);
                System.Diagnostics.Process.Start(j.SubItems[1].Text);   
            }
        }
    }
}
