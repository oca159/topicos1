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
            this.SuspendLayout();
            // 
            // tNombre
            // 
            this.tNombre.Location = new System.Drawing.Point(145, 23);
            this.tNombre.Name = "tNombre";
            this.tNombre.Size = new System.Drawing.Size(100, 20);
            this.tNombre.TabIndex = 0;
            // 
            // tMaterno
            // 
            this.tMaterno.Location = new System.Drawing.Point(145, 104);
            this.tMaterno.Name = "tMaterno";
            this.tMaterno.Size = new System.Drawing.Size(100, 20);
            this.tMaterno.TabIndex = 2;
            this.tMaterno.TextChanged += new System.EventHandler(this.textBox2_TextChanged);
            // 
            // tPaterno
            // 
            this.tPaterno.Location = new System.Drawing.Point(145, 63);
            this.tPaterno.Name = "tPaterno";
            this.tPaterno.Size = new System.Drawing.Size(100, 20);
            this.tPaterno.TabIndex = 1;
            // 
            // lNombre
            // 
            this.lNombre.AutoSize = true;
            this.lNombre.Location = new System.Drawing.Point(36, 26);
            this.lNombre.Name = "lNombre";
            this.lNombre.Size = new System.Drawing.Size(47, 13);
            this.lNombre.TabIndex = 3;
            this.lNombre.Text = "Nombre:";
            this.lNombre.Click += new System.EventHandler(this.label1_Click);
            // 
            // lAMaterno
            // 
            this.lAMaterno.AutoSize = true;
            this.lAMaterno.Location = new System.Drawing.Point(36, 104);
            this.lAMaterno.Name = "lAMaterno";
            this.lAMaterno.Size = new System.Drawing.Size(89, 13);
            this.lAMaterno.TabIndex = 4;
            this.lAMaterno.Text = "Apellido Materno:";
            // 
            // lAPaterno
            // 
            this.lAPaterno.AutoSize = true;
            this.lAPaterno.Location = new System.Drawing.Point(36, 66);
            this.lAPaterno.Name = "lAPaterno";
            this.lAPaterno.Size = new System.Drawing.Size(87, 13);
            this.lAPaterno.TabIndex = 5;
            this.lAPaterno.Text = "Apellido Paterno:";
            // 
            // bAceptar
            // 
            this.bAceptar.Location = new System.Drawing.Point(14, 227);
            this.bAceptar.Name = "bAceptar";
            this.bAceptar.Size = new System.Drawing.Size(75, 23);
            this.bAceptar.TabIndex = 6;
            this.bAceptar.Text = "Aceptar";
            this.bAceptar.UseVisualStyleBackColor = true;
            this.bAceptar.Click += new System.EventHandler(this.bAceptar_Click);
            // 
            // bGuardar
            // 
            this.bGuardar.Location = new System.Drawing.Point(107, 227);
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
            this.lPromGeneral.Location = new System.Drawing.Point(36, 147);
            this.lPromGeneral.Name = "lPromGeneral";
            this.lPromGeneral.Size = new System.Drawing.Size(94, 13);
            this.lPromGeneral.TabIndex = 8;
            this.lPromGeneral.Text = "Promedio General:";
            // 
            // tPromGeneral
            // 
            this.tPromGeneral.Location = new System.Drawing.Point(146, 144);
            this.tPromGeneral.Name = "tPromGeneral";
            this.tPromGeneral.Size = new System.Drawing.Size(100, 20);
            this.tPromGeneral.TabIndex = 3;
            // 
            // lMatricula
            // 
            this.lMatricula.AutoSize = true;
            this.lMatricula.Location = new System.Drawing.Point(36, 188);
            this.lMatricula.Name = "lMatricula";
            this.lMatricula.Size = new System.Drawing.Size(53, 13);
            this.lMatricula.TabIndex = 10;
            this.lMatricula.Text = "Matricula:";
            // 
            // tMatricula
            // 
            this.tMatricula.Location = new System.Drawing.Point(146, 185);
            this.tMatricula.Name = "tMatricula";
            this.tMatricula.Size = new System.Drawing.Size(100, 20);
            this.tMatricula.TabIndex = 4;
            // 
            // bMostrar
            // 
            this.bMostrar.Location = new System.Drawing.Point(197, 227);
            this.bMostrar.Name = "bMostrar";
            this.bMostrar.Size = new System.Drawing.Size(75, 23);
            this.bMostrar.TabIndex = 12;
            this.bMostrar.Text = "Mostrar";
            this.bMostrar.UseVisualStyleBackColor = true;
            this.bMostrar.Click += new System.EventHandler(this.bMostrar_Click);
            // 
            // fEstudiantes
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(284, 288);
            this.Controls.Add(this.bMostrar);
            this.Controls.Add(this.tMatricula);
            this.Controls.Add(this.lMatricula);
            this.Controls.Add(this.tPromGeneral);
            this.Controls.Add(this.lPromGeneral);
            this.Controls.Add(this.bGuardar);
            this.Controls.Add(this.bAceptar);
            this.Controls.Add(this.lAPaterno);
            this.Controls.Add(this.lAMaterno);
            this.Controls.Add(this.lNombre);
            this.Controls.Add(this.tPaterno);
            this.Controls.Add(this.tMaterno);
            this.Controls.Add(this.tNombre);
            this.Name = "fEstudiantes";
            this.Text = "Estudiantes";
            this.ResumeLayout(false);
            this.PerformLayout();

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
    }
}

