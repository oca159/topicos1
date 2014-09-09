namespace ProyEstudiantes
{
    partial class fEstudiantes
    {
        /// <summary>
        /// Variable del diseñador requerida.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Limpiar los recursos que se estén utilizando.
        /// </summary>
        /// <param name="disposing">true si los recursos administrados se deben eliminar; false en caso contrario, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Código generado por el Diseñador de Windows Forms

        /// <summary>
        /// Método necesario para admitir el Diseñador. No se puede modificar
        /// el contenido del método con el editor de código.
        /// </summary>
        private void InitializeComponent()
        {
            this.tNombre = new System.Windows.Forms.TextBox();
            this.tMaterno = new System.Windows.Forms.TextBox();
            this.tPaterno = new System.Windows.Forms.TextBox();
            this.lNombre = new System.Windows.Forms.Label();
            this.lAMaterno = new System.Windows.Forms.Label();
            this.lAPaterno = new System.Windows.Forms.Label();
            this.bAceptar = new System.Windows.Forms.Button();
            this.bGuardar = new System.Windows.Forms.Button();
            this.lPromGeneral = new System.Windows.Forms.Label();
            this.tPromGeneral = new System.Windows.Forms.TextBox();
            this.lMatricula = new System.Windows.Forms.Label();
            this.tMatricula = new System.Windows.Forms.TextBox();
            this.bMostrar = new System.Windows.Forms.Button();
            this.tabControl1 = new System.Windows.Forms.TabControl();
            this.tabPage1 = new System.Windows.Forms.TabPage();
            this.tabPage2 = new System.Windows.Forms.TabPage();
            this.BEliminar = new System.Windows.Forms.Button();
            this.label11 = new System.Windows.Forms.Label();
            this.label10 = new System.Windows.Forms.Label();
            this.label9 = new System.Windows.Forms.Label();
            this.label8 = new System.Windows.Forms.Label();
            this.label7 = new System.Windows.Forms.Label();
            this.label6 = new System.Windows.Forms.Label();
            this.label5 = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.cmbMatricula = new System.Windows.Forms.ComboBox();
            this.label1 = new System.Windows.Forms.Label();
            this.tabControl1.SuspendLayout();
            this.tabPage1.SuspendLayout();
            this.tabPage2.SuspendLayout();
            this.SuspendLayout();
            // 
            // tNombre
            // 
            this.tNombre.Location = new System.Drawing.Point(234, 26);
            this.tNombre.Name = "tNombre";
            this.tNombre.Size = new System.Drawing.Size(100, 20);
            this.tNombre.TabIndex = 0;
            // 
            // tMaterno
            // 
            this.tMaterno.Location = new System.Drawing.Point(234, 107);
            this.tMaterno.Name = "tMaterno";
            this.tMaterno.Size = new System.Drawing.Size(100, 20);
            this.tMaterno.TabIndex = 2;
            this.tMaterno.TextChanged += new System.EventHandler(this.textBox2_TextChanged);
            // 
            // tPaterno
            // 
            this.tPaterno.Location = new System.Drawing.Point(234, 66);
            this.tPaterno.Name = "tPaterno";
            this.tPaterno.Size = new System.Drawing.Size(100, 20);
            this.tPaterno.TabIndex = 1;
            // 
            // lNombre
            // 
            this.lNombre.AutoSize = true;
            this.lNombre.Location = new System.Drawing.Point(125, 29);
            this.lNombre.Name = "lNombre";
            this.lNombre.Size = new System.Drawing.Size(47, 13);
            this.lNombre.TabIndex = 3;
            this.lNombre.Text = "Nombre:";
            this.lNombre.Click += new System.EventHandler(this.label1_Click);
            // 
            // lAMaterno
            // 
            this.lAMaterno.AutoSize = true;
            this.lAMaterno.Location = new System.Drawing.Point(125, 107);
            this.lAMaterno.Name = "lAMaterno";
            this.lAMaterno.Size = new System.Drawing.Size(89, 13);
            this.lAMaterno.TabIndex = 4;
            this.lAMaterno.Text = "Apellido Materno:";
            // 
            // lAPaterno
            // 
            this.lAPaterno.AutoSize = true;
            this.lAPaterno.Location = new System.Drawing.Point(125, 69);
            this.lAPaterno.Name = "lAPaterno";
            this.lAPaterno.Size = new System.Drawing.Size(87, 13);
            this.lAPaterno.TabIndex = 5;
            this.lAPaterno.Text = "Apellido Paterno:";
            // 
            // bAceptar
            // 
            this.bAceptar.Location = new System.Drawing.Point(103, 230);
            this.bAceptar.Name = "bAceptar";
            this.bAceptar.Size = new System.Drawing.Size(75, 23);
            this.bAceptar.TabIndex = 6;
            this.bAceptar.Text = "Aceptar";
            this.bAceptar.UseVisualStyleBackColor = true;
            this.bAceptar.Click += new System.EventHandler(this.bAceptar_Click);
            // 
            // bGuardar
            // 
            this.bGuardar.Location = new System.Drawing.Point(196, 230);
            this.bGuardar.Name = "bGuardar";
            this.bGuardar.Size = new System.Drawing.Size(75, 23);
            this.bGuardar.TabIndex = 7;
            this.bGuardar.Text = "Guardar";
            this.bGuardar.UseVisualStyleBackColor = true;
            this.bGuardar.Click += new System.EventHandler(this.bGuardar_Click);
            // 
            // lPromGeneral
            // 
            this.lPromGeneral.AutoSize = true;
            this.lPromGeneral.Location = new System.Drawing.Point(125, 150);
            this.lPromGeneral.Name = "lPromGeneral";
            this.lPromGeneral.Size = new System.Drawing.Size(94, 13);
            this.lPromGeneral.TabIndex = 8;
            this.lPromGeneral.Text = "Promedio General:";
            // 
            // tPromGeneral
            // 
            this.tPromGeneral.Location = new System.Drawing.Point(235, 147);
            this.tPromGeneral.Name = "tPromGeneral";
            this.tPromGeneral.Size = new System.Drawing.Size(100, 20);
            this.tPromGeneral.TabIndex = 3;
            // 
            // lMatricula
            // 
            this.lMatricula.AutoSize = true;
            this.lMatricula.Location = new System.Drawing.Point(125, 191);
            this.lMatricula.Name = "lMatricula";
            this.lMatricula.Size = new System.Drawing.Size(53, 13);
            this.lMatricula.TabIndex = 10;
            this.lMatricula.Text = "Matricula:";
            // 
            // tMatricula
            // 
            this.tMatricula.Location = new System.Drawing.Point(235, 188);
            this.tMatricula.Name = "tMatricula";
            this.tMatricula.Size = new System.Drawing.Size(100, 20);
            this.tMatricula.TabIndex = 4;
            // 
            // bMostrar
            // 
            this.bMostrar.Location = new System.Drawing.Point(286, 230);
            this.bMostrar.Name = "bMostrar";
            this.bMostrar.Size = new System.Drawing.Size(75, 23);
            this.bMostrar.TabIndex = 12;
            this.bMostrar.Text = "Mostrar";
            this.bMostrar.UseVisualStyleBackColor = true;
            this.bMostrar.Click += new System.EventHandler(this.bMostrar_Click);
            // 
            // tabControl1
            // 
            this.tabControl1.Controls.Add(this.tabPage1);
            this.tabControl1.Controls.Add(this.tabPage2);
            this.tabControl1.Location = new System.Drawing.Point(278, 12);
            this.tabControl1.Name = "tabControl1";
            this.tabControl1.SelectedIndex = 0;
            this.tabControl1.Size = new System.Drawing.Size(478, 324);
            this.tabControl1.TabIndex = 13;
            this.tabControl1.SelectedIndexChanged += new System.EventHandler(this.tabControl1_SelectedIndexChanged);
            // 
            // tabPage1
            // 
            this.tabPage1.Controls.Add(this.tNombre);
            this.tabPage1.Controls.Add(this.bMostrar);
            this.tabPage1.Controls.Add(this.tMaterno);
            this.tabPage1.Controls.Add(this.tMatricula);
            this.tabPage1.Controls.Add(this.tPaterno);
            this.tabPage1.Controls.Add(this.lMatricula);
            this.tabPage1.Controls.Add(this.lNombre);
            this.tabPage1.Controls.Add(this.tPromGeneral);
            this.tabPage1.Controls.Add(this.lAMaterno);
            this.tabPage1.Controls.Add(this.lPromGeneral);
            this.tabPage1.Controls.Add(this.lAPaterno);
            this.tabPage1.Controls.Add(this.bGuardar);
            this.tabPage1.Controls.Add(this.bAceptar);
            this.tabPage1.Location = new System.Drawing.Point(4, 22);
            this.tabPage1.Name = "tabPage1";
            this.tabPage1.Padding = new System.Windows.Forms.Padding(3);
            this.tabPage1.Size = new System.Drawing.Size(470, 298);
            this.tabPage1.TabIndex = 0;
            this.tabPage1.Text = "Registro";
            this.tabPage1.UseVisualStyleBackColor = true;
            this.tabPage1.Click += new System.EventHandler(this.tabPage1_Click);
            // 
            // tabPage2
            // 
            this.tabPage2.Controls.Add(this.BEliminar);
            this.tabPage2.Controls.Add(this.label11);
            this.tabPage2.Controls.Add(this.label10);
            this.tabPage2.Controls.Add(this.label9);
            this.tabPage2.Controls.Add(this.label8);
            this.tabPage2.Controls.Add(this.label7);
            this.tabPage2.Controls.Add(this.label6);
            this.tabPage2.Controls.Add(this.label5);
            this.tabPage2.Controls.Add(this.label4);
            this.tabPage2.Controls.Add(this.label3);
            this.tabPage2.Controls.Add(this.label2);
            this.tabPage2.Controls.Add(this.cmbMatricula);
            this.tabPage2.Controls.Add(this.label1);
            this.tabPage2.Location = new System.Drawing.Point(4, 22);
            this.tabPage2.Name = "tabPage2";
            this.tabPage2.Padding = new System.Windows.Forms.Padding(3);
            this.tabPage2.Size = new System.Drawing.Size(470, 298);
            this.tabPage2.TabIndex = 1;
            this.tabPage2.Text = "Consulta";
            this.tabPage2.UseVisualStyleBackColor = true;
            this.tabPage2.Click += new System.EventHandler(this.tabPage2_Click);
            // 
            // BEliminar
            // 
            this.BEliminar.Location = new System.Drawing.Point(197, 239);
            this.BEliminar.Name = "BEliminar";
            this.BEliminar.Size = new System.Drawing.Size(75, 23);
            this.BEliminar.TabIndex = 12;
            this.BEliminar.Text = "Eliminar";
            this.BEliminar.UseVisualStyleBackColor = true;
            // 
            // label11
            // 
            this.label11.AutoSize = true;
            this.label11.Location = new System.Drawing.Point(182, 190);
            this.label11.Name = "label11";
            this.label11.Size = new System.Drawing.Size(41, 13);
            this.label11.TabIndex = 11;
            this.label11.Text = "label11";
            // 
            // label10
            // 
            this.label10.AutoSize = true;
            this.label10.Location = new System.Drawing.Point(182, 99);
            this.label10.Name = "label10";
            this.label10.Size = new System.Drawing.Size(41, 13);
            this.label10.TabIndex = 10;
            this.label10.Text = "label10";
            // 
            // label9
            // 
            this.label9.AutoSize = true;
            this.label9.Location = new System.Drawing.Point(87, 190);
            this.label9.Name = "label9";
            this.label9.Size = new System.Drawing.Size(51, 13);
            this.label9.TabIndex = 9;
            this.label9.Text = "Promedio";
            this.label9.Click += new System.EventHandler(this.label9_Click);
            // 
            // label8
            // 
            this.label8.AutoSize = true;
            this.label8.Location = new System.Drawing.Point(182, 130);
            this.label8.Name = "label8";
            this.label8.Size = new System.Drawing.Size(35, 13);
            this.label8.TabIndex = 8;
            this.label8.Text = "label8";
            // 
            // label7
            // 
            this.label7.AutoSize = true;
            this.label7.Location = new System.Drawing.Point(182, 159);
            this.label7.Name = "label7";
            this.label7.Size = new System.Drawing.Size(35, 13);
            this.label7.TabIndex = 7;
            this.label7.Text = "label7";
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Location = new System.Drawing.Point(182, 66);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(35, 13);
            this.label6.TabIndex = 6;
            this.label6.Text = "label6";
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(88, 159);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(50, 13);
            this.label5.TabIndex = 5;
            this.label5.Text = "Matricula";
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(52, 128);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(86, 13);
            this.label4.TabIndex = 4;
            this.label4.Text = "Apellido Materno";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(54, 97);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(84, 13);
            this.label3.TabIndex = 3;
            this.label3.Text = "Apellido Paterno";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(94, 66);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(44, 13);
            this.label2.TabIndex = 2;
            this.label2.Text = "Nombre";
            // 
            // cmbMatricula
            // 
            this.cmbMatricula.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.cmbMatricula.FormattingEnabled = true;
            this.cmbMatricula.Location = new System.Drawing.Point(151, 24);
            this.cmbMatricula.Name = "cmbMatricula";
            this.cmbMatricula.Size = new System.Drawing.Size(121, 21);
            this.cmbMatricula.TabIndex = 1;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(94, 27);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(51, 13);
            this.label1.TabIndex = 0;
            this.label1.Text = "Consulta:";
            // 
            // fEstudiantes
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(831, 361);
            this.Controls.Add(this.tabControl1);
            this.Name = "fEstudiantes";
            this.Text = "Registro";
            this.Load += new System.EventHandler(this.fEstudiantes_Load);
            this.tabControl1.ResumeLayout(false);
            this.tabPage1.ResumeLayout(false);
            this.tabPage1.PerformLayout();
            this.tabPage2.ResumeLayout(false);
            this.tabPage2.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.TextBox tNombre;
        private System.Windows.Forms.TextBox tMaterno;
        private System.Windows.Forms.TextBox tPaterno;
        private System.Windows.Forms.Label lNombre;
        private System.Windows.Forms.Label lAMaterno;
        private System.Windows.Forms.Label lAPaterno;
        private System.Windows.Forms.Button bAceptar;
        private System.Windows.Forms.Button bGuardar;
        private System.Windows.Forms.Label lPromGeneral;
        private System.Windows.Forms.TextBox tPromGeneral;
        private System.Windows.Forms.Label lMatricula;
        private System.Windows.Forms.TextBox tMatricula;
        private System.Windows.Forms.Button bMostrar;
        private System.Windows.Forms.TabControl tabControl1;
        private System.Windows.Forms.TabPage tabPage1;
        private System.Windows.Forms.TabPage tabPage2;
        private System.Windows.Forms.Label label10;
        private System.Windows.Forms.Label label9;
        private System.Windows.Forms.Label label8;
        private System.Windows.Forms.Label label7;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.ComboBox cmbMatricula;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label11;
        private System.Windows.Forms.Button BEliminar;
    }
}

