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
            this.bCerrar = new System.Windows.Forms.Button();
            this.bEliminar = new System.Windows.Forms.Button();
            this.bModificar = new System.Windows.Forms.Button();
            ((System.ComponentModel.ISupportInitialize)(this.dTablaDatos)).BeginInit();
            this.SuspendLayout();
            // 
            // dTablaDatos
            // 
            this.dTablaDatos.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dTablaDatos.Location = new System.Drawing.Point(32, 54);
            this.dTablaDatos.MultiSelect = false;
            this.dTablaDatos.Name = "dTablaDatos";
            this.dTablaDatos.ReadOnly = true;
            this.dTablaDatos.SelectionMode = System.Windows.Forms.DataGridViewSelectionMode.FullRowSelect;
            this.dTablaDatos.Size = new System.Drawing.Size(544, 150);
            this.dTablaDatos.TabIndex = 0;
            this.dTablaDatos.CellContentClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.dTablaDatos_CellContentClick);
            // 
            // bCerrar
            // 
            this.bCerrar.Location = new System.Drawing.Point(283, 225);
            this.bCerrar.Name = "bCerrar";
            this.bCerrar.Size = new System.Drawing.Size(75, 23);
            this.bCerrar.TabIndex = 1;
            this.bCerrar.Text = "Cerrar";
            this.bCerrar.UseVisualStyleBackColor = true;
            this.bCerrar.Click += new System.EventHandler(this.button1_Click);
            // 
            // bEliminar
            // 
            this.bEliminar.Location = new System.Drawing.Point(132, 225);
            this.bEliminar.Name = "bEliminar";
            this.bEliminar.Size = new System.Drawing.Size(75, 23);
            this.bEliminar.TabIndex = 2;
            this.bEliminar.Text = "Eliminar";
            this.bEliminar.UseVisualStyleBackColor = true;
            this.bEliminar.Click += new System.EventHandler(this.bEliminar_Click);
            // 
            // bModificar
            // 
            this.bModificar.Location = new System.Drawing.Point(434, 225);
            this.bModificar.Name = "bModificar";
            this.bModificar.Size = new System.Drawing.Size(75, 23);
            this.bModificar.TabIndex = 3;
            this.bModificar.Text = "Modificar";
            this.bModificar.UseVisualStyleBackColor = true;
            this.bModificar.Click += new System.EventHandler(this.bModificar_Click);
            // 
            // VisualizarDatos
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(606, 258);
            this.Controls.Add(this.bModificar);
            this.Controls.Add(this.bEliminar);
            this.Controls.Add(this.bCerrar);
            this.Controls.Add(this.dTablaDatos);
            this.Name = "VisualizarDatos";
            this.Text = "VisualizarDatos";
            ((System.ComponentModel.ISupportInitialize)(this.dTablaDatos)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.DataGridView dTablaDatos;
        private System.Windows.Forms.Button bCerrar;
        private System.Windows.Forms.Button bEliminar;
        private System.Windows.Forms.Button bModificar;
    }
}