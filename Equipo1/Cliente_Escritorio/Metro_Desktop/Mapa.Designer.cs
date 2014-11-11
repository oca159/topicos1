namespace Metro_Desktop
{
    partial class Mapa
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
            this.mapExplorer = new GMap.NET.WindowsForms.GMapControl();
            this.destino_Ubica = new System.Windows.Forms.TextBox();
            this.salida_Ubica = new System.Windows.Forms.TextBox();
            this.Salida = new System.Windows.Forms.ComboBox();
            this.button1 = new System.Windows.Forms.Button();
            this.Destino = new System.Windows.Forms.ComboBox();
            this.label2 = new System.Windows.Forms.Label();
            this.label1 = new System.Windows.Forms.Label();
            this.RutaCorta = new System.Windows.Forms.TextBox();
            this.SuspendLayout();
            // 
            // mapExplorer
            // 
            this.mapExplorer.Bearing = 0F;
            this.mapExplorer.CanDragMap = true;
            this.mapExplorer.GrayScaleMode = false;
            this.mapExplorer.LevelsKeepInMemmory = 5;
            this.mapExplorer.Location = new System.Drawing.Point(12, 12);
            this.mapExplorer.MarkersEnabled = true;
            this.mapExplorer.MaxZoom = 2;
            this.mapExplorer.MinZoom = 2;
            this.mapExplorer.MouseWheelZoomType = GMap.NET.MouseWheelZoomType.MousePositionAndCenter;
            this.mapExplorer.Name = "mapExplorer";
            this.mapExplorer.NegativeMode = false;
            this.mapExplorer.PolygonsEnabled = true;
            this.mapExplorer.RetryLoadTile = 0;
            this.mapExplorer.RoutesEnabled = true;
            this.mapExplorer.ShowTileGridLines = false;
            this.mapExplorer.Size = new System.Drawing.Size(995, 443);
            this.mapExplorer.TabIndex = 0;
            this.mapExplorer.Zoom = 0D;
            this.mapExplorer.Load += new System.EventHandler(this.mapExplorer_Load);
            // 
            // destino_Ubica
            // 
            this.destino_Ubica.Location = new System.Drawing.Point(1022, 162);
            this.destino_Ubica.Name = "destino_Ubica";
            this.destino_Ubica.Size = new System.Drawing.Size(283, 20);
            this.destino_Ubica.TabIndex = 23;
            // 
            // salida_Ubica
            // 
            this.salida_Ubica.Location = new System.Drawing.Point(1022, 92);
            this.salida_Ubica.Name = "salida_Ubica";
            this.salida_Ubica.Size = new System.Drawing.Size(283, 20);
            this.salida_Ubica.TabIndex = 22;
            // 
            // Salida
            // 
            this.Salida.FormattingEnabled = true;
            this.Salida.Location = new System.Drawing.Point(1021, 64);
            this.Salida.Name = "Salida";
            this.Salida.Size = new System.Drawing.Size(284, 21);
            this.Salida.TabIndex = 21;
            this.Salida.SelectedIndexChanged += new System.EventHandler(this.Salida_SelectedIndexChanged);
            // 
            // button1
            // 
            this.button1.Location = new System.Drawing.Point(1025, 199);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(195, 23);
            this.button1.TabIndex = 20;
            this.button1.Text = "Generar Ruta";
            this.button1.UseVisualStyleBackColor = true;
            this.button1.Click += new System.EventHandler(this.button1_Click);
            // 
            // Destino
            // 
            this.Destino.FormattingEnabled = true;
            this.Destino.Location = new System.Drawing.Point(1021, 134);
            this.Destino.Name = "Destino";
            this.Destino.Size = new System.Drawing.Size(284, 21);
            this.Destino.TabIndex = 19;
            this.Destino.SelectedIndexChanged += new System.EventHandler(this.Destino_SelectedIndexChanged);
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(1022, 118);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(43, 13);
            this.label2.TabIndex = 18;
            this.label2.Text = "Destino";
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(1022, 39);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(82, 13);
            this.label1.TabIndex = 17;
            this.label1.Text = "Punto de Salida";
            // 
            // RutaCorta
            // 
            this.RutaCorta.Location = new System.Drawing.Point(1025, 248);
            this.RutaCorta.Name = "RutaCorta";
            this.RutaCorta.Size = new System.Drawing.Size(280, 20);
            this.RutaCorta.TabIndex = 24;
            // 
            // Mapa
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1317, 490);
            this.Controls.Add(this.RutaCorta);
            this.Controls.Add(this.destino_Ubica);
            this.Controls.Add(this.salida_Ubica);
            this.Controls.Add(this.Salida);
            this.Controls.Add(this.button1);
            this.Controls.Add(this.Destino);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.mapExplorer);
            this.Name = "Mapa";
            this.Text = "Metro D.F.";
            this.Load += new System.EventHandler(this.Mapa_Load);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private GMap.NET.WindowsForms.GMapControl mapExplorer;
        private System.Windows.Forms.TextBox destino_Ubica;
        private System.Windows.Forms.TextBox salida_Ubica;
        private System.Windows.Forms.ComboBox Salida;
        private System.Windows.Forms.Button button1;
        private System.Windows.Forms.ComboBox Destino;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.TextBox RutaCorta;
    }
}