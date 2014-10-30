using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.Drawing;
using System.Reflection;

namespace ReflexionEstudiante
{
    public class Ventana : Form
    {
        public Ventana()
        {
            this.Size = new Size(600, 600);
            this.StartPosition = FormStartPosition.CenterScreen;
            Button bEstudiante = new Button();
            bEstudiante.SetBounds(300, 500, 100, 30);
            bEstudiante.Text = "Estudiante";
            bEstudiante.Name = "bEstudiante";
            bEstudiante.Click += new EventHandler(button1_Click);
            this.Controls.Add(bEstudiante);
            Button bProfesor = new Button();
            bProfesor.SetBounds(410, 500, 100, 30);
            bProfesor.Text = "Profesor";
            bProfesor.Name = "bProfesor";
            bProfesor.Click += new EventHandler(button1_Click);
            this.Controls.Add(bProfesor);
            this.Text = "Formulario con reflexión";
        }

        protected void button1_Click(object sender, EventArgs e)
        {
            Form f1 = new Form();
            int i = 10;
            Console.Clear();
            f1.StartPosition = FormStartPosition.CenterScreen;
            Assembly asm = Assembly.LoadFile(@"c:\RepositorioEstudiantes.dll");
            Type[] types = asm.GetTypes();
            var estudiante = types.GetValue(1);
            List<string> tipos = new List<string>();
            tipos.Clear();
            Button receptor = (Button)sender;
            Type persona = asm.GetType("RepositorioEstudiantes."+receptor.Text);
            ConstructorInfo personaConstructor = null;
            List<TextBox> campos = new List<TextBox>();
            campos.Clear();
            Object obj = null;
            if (receptor.Text == "Estudiante")
            {
                obj = Activator.CreateInstance(persona);
                personaConstructor = persona.GetConstructor(new[] { typeof(string), typeof(string), typeof(string), typeof(int), typeof(string), typeof(string) });
                
            }
                



            foreach (Type t in types)
            {
                MemberInfo[] po = t.GetMembers();
                foreach (MemberInfo p in po)
                {
                    if (p.ReflectedType.ToString().Equals("RepositorioEstudiantes."+receptor.Text))
                    {
                        if (p.MemberType.ToString().Equals("Property"))
                        {
                            string words = p.ToString();

                            string[] split = words.Split(new Char[] { ' ' });
                            bool ok = false;

                            foreach (string s in split)
                            {


                                if (ok)
                                {
                                    //Label dinamicos
                                    Label l1 = new Label();
                                    l1.Location = new Point(10, i);
                                    l1.AutoSize = true;
                                    l1.Visible = true;
                                    l1.Text = s + ":";
                                    l1.Name = "L" + s;
                                    f1.Controls.Add(l1);

                                    //Texbox dinamicos

                                    TextBox t1 = new TextBox();
                                    t1.Location = new Point(75, i);
                                    t1.AutoSize = true;
                                    t1.Visible = true;
                                    t1.Name = "T" + s;
                                    campos.Add(t1);
                                    f1.Controls.Add(t1);

                                    i = i + 25;
                                    if (s.Trim() != "")
                                        Console.WriteLine(s);
                                }
                                else
                                {
                                    tipos.Add(s);
                                }
                                ok = !ok;
                            } //fin del foreach del split
                        } //fin del if que obtiene la propiedad
                    } //fin del if de estudiante
                }//fin del foreach que itera sobre la informacion de los miembros de las clases
            }//fin del foreach que itera sobre los tipos de la dll
            List<object> datos = new List<object>();
            if (receptor.Text == "Estudiante")
            {
                foreach (var t in campos)
                {
                    TextBox n = (TextBox) t;
                    datos.Add(n.Text);

                }
                //object personaObject = personaConstructor.Invoke(obj, datos.ToArray());
                //MethodInfo personaMethod = persona.GetMethod("");
            }
   

            //Boton para agregar estudiantes
            Button bAgregar = new Button();
            bAgregar.Location = new Point(75, i + 20);
            bAgregar.Text = "Agregar";
            bAgregar.Name = "bAgregar";
            bAgregar.AutoSize = true;
            f1.Controls.Add(bAgregar);

            //boton para cerrar form
            Button bCancelar = new Button();
            bCancelar.Location = new Point(155, i + 20);
            bCancelar.Text = "Cancelar";
            bCancelar.Name = "bCancelar";
            bCancelar.AutoSize = true;
            bCancelar.Click += new EventHandler(bCancelar_Click);
            f1.Controls.Add(bCancelar);
            f1.Text = "Formulario de " + receptor.Text;
            //f1.Size = new Size(350, 350);
            f1.AutoSize = true;
            f1.ShowDialog();
        }

        protected void bCancelar_Click(object sender, EventArgs e)
        {
            Button b = (Button)sender;
            Form f = (Form)b.Parent;
            f.Close();
            Console.Clear();
        }
    }
}
