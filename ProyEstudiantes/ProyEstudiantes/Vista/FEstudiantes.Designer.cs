namespace ProyEstudiantes
{
    partial class FEstudiantes
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
            this.BAceptar = new System.Windows.Forms.Button();
            this.TApellidoP = new System.Windows.Forms.TextBox();
            this.TNombre = new System.Windows.Forms.TextBox();
            this.TApellidoM = new System.Windows.Forms.TextBox();
            this.BCancelar = new System.Windows.Forms.Button();
            this.LNombre = new System.Windows.Forms.Label();
            this.LApellidoP = new System.Windows.Forms.Label();
            this.LApellidoM = new System.Windows.Forms.Label();
            this.button1 = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // BAceptar
            // 
            this.BAceptar.Location = new System.Drawing.Point(41, 181);
            this.BAceptar.Name = "BAceptar";
            this.BAceptar.Size = new System.Drawing.Size(75, 23);
            this.BAceptar.TabIndex = 0;
            this.BAceptar.Text = "Aceptar";
            this.BAceptar.UseVisualStyleBackColor = true;
            this.BAceptar.Click += new System.EventHandler(this.BAceptar_Click);
            // 
            // TApellidoP
            // 
            this.TApellidoP.Location = new System.Drawing.Point(128, 88);
            this.TApellidoP.Name = "TApellidoP";
            this.TApellidoP.Size = new System.Drawing.Size(100, 20);
            this.TApellidoP.TabIndex = 1;
            // 
            // TNombre
            // 
            this.TNombre.Location = new System.Drawing.Point(128, 50);
            this.TNombre.Name = "TNombre";
            this.TNombre.Size = new System.Drawing.Size(100, 20);
            this.TNombre.TabIndex = 2;
            // 
            // TApellidoM
            // 
            this.TApellidoM.Location = new System.Drawing.Point(128, 128);
            this.TApellidoM.Name = "TApellidoM";
            this.TApellidoM.Size = new System.Drawing.Size(100, 20);
            this.TApellidoM.TabIndex = 3;
            // 
            // BCancelar
            // 
            this.BCancelar.Location = new System.Drawing.Point(153, 181);
            this.BCancelar.Name = "BCancelar";
            this.BCancelar.Size = new System.Drawing.Size(75, 23);
            this.BCancelar.TabIndex = 4;
            this.BCancelar.Text = "Cancelar";
            this.BCancelar.UseVisualStyleBackColor = true;
            this.BCancelar.Click += new System.EventHandler(this.BCancelar_Click);
            // 
            // LNombre
            // 
            this.LNombre.AutoSize = true;
            this.LNombre.Location = new System.Drawing.Point(78, 57);
            this.LNombre.Name = "LNombre";
            this.LNombre.Size = new System.Drawing.Size(44, 13);
            this.LNombre.TabIndex = 5;
            this.LNombre.Text = "Nombre";
            // 
            // LApellidoP
            // 
            this.LApellidoP.AutoSize = true;
            this.LApellidoP.Location = new System.Drawing.Point(38, 91);
            this.LApellidoP.Name = "LApellidoP";
            this.LApellidoP.Size = new System.Drawing.Size(84, 13);
            this.LApellidoP.TabIndex = 6;
            this.LApellidoP.Text = "Apellido Paterno";
            // 
            // LApellidoM
            // 
            this.LApellidoM.AutoSize = true;
            this.LApellidoM.Location = new System.Drawing.Point(36, 131);
            this.LApellidoM.Name = "LApellidoM";
            this.LApellidoM.Size = new System.Drawing.Size(86, 13);
            this.LApellidoM.TabIndex = 7;
            this.LApellidoM.Text = "Apellido Materno";
            // 
            // button1
            // 
            this.button1.Location = new System.Drawing.Point(153, 226);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(75, 23);
            this.button1.TabIndex = 8;
            this.button1.Text = "button1";
            this.button1.UseVisualStyleBackColor = true;
            this.button1.Click += new System.EventHandler(this.button1_Click);
            // 
            // FEstudiantes
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(284, 261);
            this.Controls.Add(this.button1);
            this.Controls.Add(this.LApellidoM);
            this.Controls.Add(this.LApellidoP);
            this.Controls.Add(this.LNombre);
            this.Controls.Add(this.BCancelar);
            this.Controls.Add(this.TApellidoM);
            this.Controls.Add(this.TNombre);
            this.Controls.Add(this.TApellidoP);
            this.Controls.Add(this.BAceptar);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedSingle;
            this.Name = "FEstudiantes";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Estudiantes";
            this.Load += new System.EventHandler(this.FEstudiantes_Load);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Button BAceptar;
        private System.Windows.Forms.TextBox TApellidoP;
        private System.Windows.Forms.TextBox TNombre;
        private System.Windows.Forms.TextBox TApellidoM;
        private System.Windows.Forms.Button BCancelar;
        private System.Windows.Forms.Label LNombre;
        private System.Windows.Forms.Label LApellidoP;
        private System.Windows.Forms.Label LApellidoM;
        private System.Windows.Forms.Button button1;

    }
}

