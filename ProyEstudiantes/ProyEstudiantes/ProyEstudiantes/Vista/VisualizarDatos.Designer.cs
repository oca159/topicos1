namespace ProyEstudiantes.Vista
{
    partial class VisualizarDatos
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.dTablaDatos = new System.Windows.Forms.DataGridView();
            this.Nombre = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.APaterno = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.AMaterno = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.PromGeneral = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.Matricula = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.bCerrar = new System.Windows.Forms.Button();
            ((System.ComponentModel.ISupportInitialize)(this.dTablaDatos)).BeginInit();
            this.SuspendLayout();
            // 
            // dTablaDatos
            // 
            this.dTablaDatos.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dTablaDatos.Columns.AddRange(new System.Windows.Forms.DataGridViewColumn[] {
            this.Nombre,
            this.APaterno,
            this.AMaterno,
            this.PromGeneral,
            this.Matricula});
            this.dTablaDatos.Location = new System.Drawing.Point(32, 54);
            this.dTablaDatos.Name = "dTablaDatos";
            this.dTablaDatos.Size = new System.Drawing.Size(544, 150);
            this.dTablaDatos.TabIndex = 0;
            this.dTablaDatos.CellContentClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.dTablaDatos_CellContentClick);
            // 
            // Nombre
            // 
            this.Nombre.HeaderText = "Nombre";
            this.Nombre.Name = "Nombre";
            // 
            // APaterno
            // 
            this.APaterno.HeaderText = "Apellido Paterno";
            this.APaterno.Name = "APaterno";
            // 
            // AMaterno
            // 
            this.AMaterno.HeaderText = "Apellido Materno";
            this.AMaterno.Name = "AMaterno";
            // 
            // PromGeneral
            // 
            this.PromGeneral.HeaderText = "Promedio General";
            this.PromGeneral.Name = "PromGeneral";
            // 
            // Matricula
            // 
            this.Matricula.HeaderText = "Matricula";
            this.Matricula.Name = "Matricula";
            // 
            // bCerrar
            // 
            this.bCerrar.Location = new System.Drawing.Point(266, 226);
            this.bCerrar.Name = "bCerrar";
            this.bCerrar.Size = new System.Drawing.Size(75, 23);
            this.bCerrar.TabIndex = 1;
            this.bCerrar.Text = "Cerrar";
            this.bCerrar.UseVisualStyleBackColor = true;
            this.bCerrar.Click += new System.EventHandler(this.button1_Click);
            // 
            // VisualizarDatos
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(606, 258);
            this.Controls.Add(this.bCerrar);
            this.Controls.Add(this.dTablaDatos);
            this.Name = "VisualizarDatos";
            this.Text = "VisualizarDatos";
            ((System.ComponentModel.ISupportInitialize)(this.dTablaDatos)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.DataGridView dTablaDatos;
        private System.Windows.Forms.DataGridViewTextBoxColumn Nombre;
        private System.Windows.Forms.DataGridViewTextBoxColumn APaterno;
        private System.Windows.Forms.DataGridViewTextBoxColumn AMaterno;
        private System.Windows.Forms.DataGridViewTextBoxColumn PromGeneral;
        private System.Windows.Forms.DataGridViewTextBoxColumn Matricula;
        private System.Windows.Forms.Button bCerrar;
    }
}