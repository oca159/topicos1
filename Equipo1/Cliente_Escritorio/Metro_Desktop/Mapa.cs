using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using GMap.NET.WindowsForms;
using GMap.NET;
using GMap.NET.MapProviders;
using Metro_Desktop.Modelo;
using Metro_Desktop.Shortest_Path;

namespace Metro_Desktop
{
    public partial class Mapa : Form
    {
        Graph grafico = new Graph();
        GMapOverlay overlayOne;
        public static Ruta[] llenado = Deserializar.shake();
        GMapOverlay salidaActual;
        GMapOverlay destinoActual;

        public Mapa()
        {
            InitializeComponent();
        }

        private void Mapa_Load(object sender, EventArgs e)
        {
            String latitud;
            String longitud;
            //generaGrafico(grafico);
            //grafico.shortest_path("Observatorio", "Universidad").ForEach(x => Console.WriteLine(x));
            foreach (Ruta r in llenado)
            {
                Salida.Items.Add(r.Nombre);
                Salida.ValueMember = r.Id.ToString();
                Salida.DisplayMember = r.Nombre;
                Destino.Items.Add(r.Nombre);
                Destino.ValueMember = r.Id.ToString();
                Destino.DisplayMember = r.Nombre;
            }
        }

        private void mapExplorer_Load(object sender, EventArgs e)
        {
            DibujaMapa();
            //DibujaTodosMarcadores();
            DibujaTodasLineas();
        }

        private void DibujaMapa()
        {
            mapExplorer.Position = new PointLatLng(19.408478, -99.103074);
            mapExplorer.MapProvider = GMapProviders.GoogleTerrainMap;
            mapExplorer.MinZoom = 3;
            mapExplorer.MaxZoom = 17;
            mapExplorer.Zoom = 10;
            mapExplorer.Manager.Mode = AccessMode.ServerAndCache;
            overlayOne = new GMapOverlay(mapExplorer, "OverlayOne");
            mapExplorer.Overlays.Add(overlayOne);
        }

        private GMapOverlay DibujaMarcador(GMapOverlay actual, double latitud, double longitud, String name)
        {
            mapExplorer.Overlays.Remove(actual);
            GMapOverlay overlayMarcador = new GMapOverlay(mapExplorer, "OverlayMarcador");
            mapExplorer.Overlays.Remove(overlayMarcador);
            PointLatLng nuevo = new PointLatLng(latitud, longitud);
            GMap.NET.WindowsForms.Markers.GMapMarkerGoogleGreen nuevoMarcador = new GMap.NET.WindowsForms.Markers.GMapMarkerGoogleGreen(nuevo);
            nuevoMarcador.ToolTipText = name;
            overlayMarcador.Markers.Add(nuevoMarcador);
            mapExplorer.Overlays.Add(overlayMarcador);
            return overlayMarcador;
        }

        private void DibujaTodosMarcadores()
        {
            GMapOverlay overlayMarcadores = new GMapOverlay(mapExplorer, "OverlayMarcadores");
            List<GMap.NET.WindowsForms.Markers.GMapMarkerGoogleGreen> Lista_Marcadores = new List<GMap.NET.WindowsForms.Markers.GMapMarkerGoogleGreen>();
            int numero_linea = 1;
            List<String> Lista_Nombres = Deserializar.regresaNombres();
            int indice = 0;
            while (numero_linea < 12)
            {
                foreach (PointLatLng x in Deserializar.regresaCoordenadas(numero_linea))
                {
                    //Lista_Marcadores.Add(new GMap.NET.WindowsForms.Markers.GMapMarkerGoogleGreen(x));
                    //overlayMarcadores.Markers.Add(new GMap.NET.WindowsForms.Markers.GMapMarkerGoogleGreen(x));
                    GMap.NET.WindowsForms.Markers.GMapMarkerGoogleGreen z = new GMap.NET.WindowsForms.Markers.GMapMarkerGoogleGreen(x);
                    z.ToolTipText = Lista_Nombres[indice];
                    overlayMarcadores.Markers.Add(z);
                    indice++;
                }
                numero_linea++;
            }
            mapExplorer.Overlays.Add(overlayMarcadores);
        }

        private void DibujaTodasLineas()
        {
            List<GMapRoute> resultado = arregloLineas();
            GMapOverlay lineas = new GMapOverlay(mapExplorer, "lineas");
            foreach (GMapRoute x in resultado)
            {
                lineas.Routes.Add(x);
            }
            mapExplorer.Overlays.Add(lineas);
        }

        private void generaGrafico(Graph g)
        {
            g.add_vertex("observatorio", new Dictionary<string, Double>() { { "tacubaya", 1.47652166469724 } });
            g.add_vertex("tacubaya", new Dictionary<string, Double>() { { "juanacatlan", 0.5734519178351764 } });
            g.add_vertex("juanacatlan", new Dictionary<string, Double>() { { "chapultepec", 0.5973386026830494 } });
            g.add_vertex("chapultepec", new Dictionary<string, Double>() { { "sevilla", 0.7073508157614314 } });
            g.add_vertex("sevilla", new Dictionary<string, Double>() { { "insurgentes", 0.8230197978469072 } });
            g.add_vertex("insurgentes", new Dictionary<string, Double>() { { "cuauhtemoc", 0.9429928073570758 } });
            g.add_vertex("cuauhtemoc", new Dictionary<string, Double>() { { "balderas", 0.6301413019318206 } });
            g.add_vertex("balderas", new Dictionary<string, Double>() { { "salto_del_agua", 0.758287523172765 } });
            g.add_vertex("salto_del_agua", new Dictionary<string, Double>() { { "isabel_la_catolica", 0.5031765452336948 } });
            g.add_vertex("isabel_la_catolica", new Dictionary<string, Double>() { { "pino_suarez", 0.5275305596705688 } });
            g.add_vertex("pino_suarez", new Dictionary<string, Double>() { { "merced", 0.9227912427496711 } });
            g.add_vertex("merced", new Dictionary<string, Double>() { { "candelaria", 0.5727702009726973 } });
            g.add_vertex("candelaria", new Dictionary<string, Double>() { { "san_lazaro", 0.5204076061982988 } });
            g.add_vertex("san_lazaro", new Dictionary<string, Double>() { { "moctezuma", 0.5059278187475528 } });
            g.add_vertex("moctezuma", new Dictionary<string, Double>() { { "balbuena", 0.8920943999822051 } });
            g.add_vertex("balbuena", new Dictionary<string, Double>() { { "blvd_puerto_aereo", 0.7086353494873293 } });
            g.add_vertex("blvd_puerto_aereo", new Dictionary<string, Double>() { { "gomez_farias", 0.6248299538966746 } });
            g.add_vertex("gomez_farias", new Dictionary<string, Double>() { { "zaragoza", 0.8853023558670671 } });
            g.add_vertex("zaragoza", new Dictionary<string, Double>() { { "pantitlan", 1.1433664040206437 } });
            g.add_vertex("tacubaya", new Dictionary<string, Double>() { { "observatorio", 1.47652166469724 } });
            g.add_vertex("juanacatlan", new Dictionary<string, Double>() { { "tacubaya", 0.5734519178351764 } });
            g.add_vertex("chapultepec", new Dictionary<string, Double>() { { "juanacatlan", 0.5973386026830494 } });
            g.add_vertex("sevilla", new Dictionary<string, Double>() { { "chapultepec", 0.7073508157614314 } });
            g.add_vertex("insurgentes", new Dictionary<string, Double>() { { "sevilla", 0.8230197978469072 } });
            g.add_vertex("cuauhtemoc", new Dictionary<string, Double>() { { "insurgentes", 0.9429928073570758 } });
            g.add_vertex("balderas", new Dictionary<string, Double>() { { "cuauhtemoc", 0.6301413019318206 } });
            g.add_vertex("salto_del_agua", new Dictionary<string, Double>() { { "balderas", 0.758287523172765 } });
            g.add_vertex("isabel_la_catolica", new Dictionary<string, Double>() { { "salto_del_agua", 0.5031765452336948 } });
            g.add_vertex("pino_suarez", new Dictionary<string, Double>() { { "isabel_la_catolica", 0.5275305596705688 } });
            g.add_vertex("merced", new Dictionary<string, Double>() { { "pino_suarez", 0.9227912427496711 } });
            g.add_vertex("candelaria", new Dictionary<string, Double>() { { "merced", 0.5727702009726973 } });
            g.add_vertex("san_lazaro", new Dictionary<string, Double>() { { "candelaria", 0.5204076061982988 } });
            g.add_vertex("moctezuma", new Dictionary<string, Double>() { { "san_lazaro", 0.5059278187475528 } });
            g.add_vertex("balbuena", new Dictionary<string, Double>() { { "moctezuma", 0.8920943999822051 } });
            g.add_vertex("blvd_puerto_aereo", new Dictionary<string, Double>() { { "balbuena", 0.7086353494873293 } });
            g.add_vertex("gomez_farias", new Dictionary<string, Double>() { { "blvd_puerto_aereo", 0.6248299538966746 } });
            g.add_vertex("zaragoza", new Dictionary<string, Double>() { { "gomez_farias", 0.8853023558670671 } });
            g.add_vertex("pantitlan", new Dictionary<string, Double>() { { "zaragoza", 1.1433664040206437 } });


        }

        private List<GMapRoute> arregloLineas()
        {
            List<GMapRoute> rutas_lineas = new List<GMapRoute>();
            int lineas = 1;
            while (lineas < 12)
            {
                foreach (PointLatLng coordenadas in Deserializar.regresaCoordenadas(lineas))
                {
                    GMapRoute agrega = new GMapRoute(Deserializar.regresaCoordenadas(lineas), "Linea " + lineas.ToString());

                    switch (lineas)
                    {
                        case (1):
                            agrega.Stroke = new Pen(Color.Pink, 3);
                            break;
                        case (2):
                            agrega.Stroke = new Pen(Color.Blue, 3);
                            break;
                        case (3):
                            agrega.Stroke = new Pen(Color.LightSeaGreen, 3);
                            break;
                        case (4):
                            agrega.Stroke = new Pen(Color.GreenYellow, 3);
                            break;
                        case (5):
                            agrega.Stroke = new Pen(Color.Yellow, 3);
                            break;
                        case (6):
                            agrega.Stroke = new Pen(Color.Red, 3);
                            break;
                        case (7):
                            agrega.Stroke = new Pen(Color.Orange, 3);
                            break;
                        case (8):
                            agrega.Stroke = new Pen(Color.ForestGreen, 3);
                            break;
                        case (9):
                            agrega.Stroke = new Pen(Color.Black, 3);
                            break;
                        case (10):
                            agrega.Stroke = new Pen(Color.Purple, 3);
                            break;
                        case (11):
                            agrega.Stroke = new Pen(Color.Gray, 3);
                            break;
                    }
                    rutas_lineas.Add(agrega);
                }
                lineas++;
            }
            return rutas_lineas;
        }

        private void Salida_SelectedIndexChanged(object sender, EventArgs e)
        {
            foreach (Ruta r in llenado)
            {
                if (r.Nombre == Salida.SelectedItem)
                {
                    salida_Ubica.Text = "( " + r.Latitud + ", " + r.Longitud + ")";
                    salidaActual = DibujaMarcador(salidaActual, Convert.ToDouble(r.Latitud),Convert.ToDouble(r.Longitud), r.Nombre);
                    break;
                }
            }
        }

        private void Destino_SelectedIndexChanged(object sender, EventArgs e)
        {
            foreach (Ruta r in llenado)
            {
                if (r.Nombre == Destino.SelectedItem)
                {
                    destino_Ubica.Text = "( " + r.Latitud + ", " + r.Longitud + ")";
                    destinoActual = DibujaMarcador(destinoActual, Convert.ToDouble(r.Latitud), Convert.ToDouble(r.Longitud), r.Nombre);
                    break;
                }
            }
        }

        private void button1_Click(object sender, EventArgs e)
        {
            generaGrafico(grafico);
            String resultado = "";
            grafico.shortest_path(Salida.SelectedItem.ToString(), Destino.SelectedItem.ToString()).ForEach(x => Console.WriteLine(x));
            RutaCorta.Text = resultado;
        }
    }
}
