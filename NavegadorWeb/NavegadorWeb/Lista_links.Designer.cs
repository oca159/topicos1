namespace NavegadorWeb
{
    partial class Lista_links
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
            this.lista = new System.Windows.Forms.ListView();
            this.Titulos = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.Link = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.SuspendLayout();
            // 
            // lista
            // 
            this.lista.Columns.AddRange(new System.Windows.Forms.ColumnHeader[] {
            this.Titulos,
            this.Link});
            this.lista.FullRowSelect = true;
            this.lista.Location = new System.Drawing.Point(28, 12);
            this.lista.Name = "lista";
            this.lista.Size = new System.Drawing.Size(537, 317);
            this.lista.TabIndex = 0;
            this.lista.UseCompatibleStateImageBehavior = false;
            this.lista.ItemSelectionChanged += new System.Windows.Forms.ListViewItemSelectionChangedEventHandler(this.lista_ItemSelectionChanged);
            this.lista.SelectedIndexChanged += new System.EventHandler(this.lista_SelectedIndexChanged);
            // 
            // Titulos
            // 
            this.Titulos.Text = "Titulos";
            this.Titulos.Width = 200;
            // 
            // Link
            // 
            this.Link.Text = "Link";
            this.Link.Width = 200;
            // 
            // Lista_links
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(592, 363);
            this.Controls.Add(this.lista);
            this.Name = "Lista_links";
            this.Text = "Lista_links";
            this.Load += new System.EventHandler(this.Lista_links_Load);
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.ListView lista;
        private System.Windows.Forms.ColumnHeader Titulos;
        private System.Windows.Forms.ColumnHeader Link;

    }
}