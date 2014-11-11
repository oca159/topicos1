using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Web.Script.Serialization;
using System.Net;
using Metro_Desktop.Modelo;
using GMap.NET.WindowsForms;
using GMap.NET;
using GMap.NET.MapProviders;
using System.Device.Location;
using Metro_Desktop.Shortest_Path;


namespace Metro_Desktop
{
    class Deserializar
    {
        public static WebClient c = new WebClient();
        public static string json = c.DownloadString("http://equipo1.azurewebsites.net/ServiceRutas.svc/GetRutas");

        public static Ruta[] shake()
        {
            var c = new WebClient();
            JavaScriptSerializer JSC = new JavaScriptSerializer();
            Ruta[] rARutas = JSC.Deserialize<Ruta[]>(json);            
            foreach(Ruta r in rARutas)
            {
                Console.WriteLine(r.Nombre);
            }
            return rARutas;
        }

        public static Graph regresaPesos(Graph grafico)
        {
            Graph resultado = grafico;
            int contador = 0;
            int lineaactual = 1;
            GeoCoordinate distanciaAntes = new GeoCoordinate();
            GeoCoordinate distanciaActual = new GeoCoordinate();
            GeoCoordinate distanciaSiguiente = new GeoCoordinate();
            var c = new WebClient();
            JavaScriptSerializer serial = new JavaScriptSerializer();
            List<Ruta> listaRutas = serial.Deserialize<List<Ruta>>(json);
            List<PointLatLng> listaPuntos = new List<PointLatLng>();
            string nombrePunto;
            foreach (Ruta x in listaRutas)
            {
                if (x.Linea == lineaactual)
                {
                    nombrePunto = x.Nombre;
                    List<Dictionary<string, double>> prueba = new List<Dictionary<string, double>>();
                    Dictionary<string, double> pruebainterna = new Dictionary<string, double>();
                    //while (x.Nombre == nombrePunto)
                    //{
                        if (contador == 0)
                        {
                            distanciaAntes.Latitude = Convert.ToDouble(x.Latitud);
                            distanciaAntes.Longitude = Convert.ToDouble(x.Longitud);
                            distanciaActual.Latitude = Convert.ToDouble(x.Latitud);
                            distanciaActual.Longitude = Convert.ToDouble(x.Longitud);
                            //pruebainterna.Add(nombrePunto, distanciaAntes.GetDistanceTo(distanciaActual));
                            //prueba.Add(pruebainterna);
                        }
                        else if (contador == 1)
                        {
                            distanciaAntes = distanciaActual;
                            distanciaActual.Latitude = Convert.ToDouble(x.Latitud);
                            distanciaActual.Longitude = Convert.ToDouble(x.Longitud);
                            pruebainterna.Add(nombrePunto, distanciaAntes.GetDistanceTo(distanciaActual));
                            prueba.Add(pruebainterna);
                        }
                        else if (contador == 2)
                        {
                            distanciaActual.Latitude = Convert.ToDouble(x.Latitud);
                            distanciaActual.Longitude = Convert.ToDouble(x.Longitud);
                            distanciaAntes = distanciaActual;
                            pruebainterna.Add(nombrePunto, distanciaAntes.GetDistanceTo(distanciaActual));
                            prueba.Add(pruebainterna);
                        }
                        contador++;
                    //}
                    foreach (Dictionary<string, double> ejemplo in prueba)
                    {
                        grafico.add_vertex(nombrePunto, ejemplo);
                    }
                }
                lineaactual++;
            }
            return resultado;
        }

        public static List<Double> regresaPesos()
        {
            List<Double> resultado = new List<Double>();
            int contador = 0;
            int linea = 1;
            double latitud1 = 0;
            double longitud1 = 0;
            double latitud2 = 0;
            double longitud2 = 0;
            GeoCoordinate distancia1 = new GeoCoordinate();
            GeoCoordinate distancia2 = new GeoCoordinate();
            List<PointLatLng> coordenadas = regresaCoordenadas(linea);
            while (linea < 12)
            {
                foreach (PointLatLng x in coordenadas)
                {
                    if (contador == 0)
                    {
                        distancia1.Latitude = x.Lat;
                        distancia1.Longitude = x.Lng;
                    }
                    else if (contador % 2 == 0)
                    {
                        resultado.Add(distancia1.GetDistanceTo(distancia2));
                        distancia1.Latitude = distancia2.Latitude;
                        distancia1.Longitude = distancia2.Longitude;
                        distancia2.Latitude = x.Lat;
                        distancia2.Longitude = x.Lng;
                    }
                    else if (contador == 1)
                    {
                        distancia2.Latitude = x.Lat;
                        distancia2.Longitude = x.Lng;
                    }
                    else if (contador % 2 == 1)
                    {
                        resultado.Add(distancia1.GetDistanceTo(distancia2));
                        distancia1.Latitude = x.Lat;
                        distancia1.Longitude = x.Lng;
                    }
                    contador++;
                }
                linea++;
            }
            return resultado;
        }

        public static List<PointLatLng> regresaCoordenadas(int linea)
        {
            var c = new WebClient();
            JavaScriptSerializer serial = new JavaScriptSerializer();
            List<Ruta> listaRutas = serial.Deserialize<List<Ruta>>(json);
            List<PointLatLng> listaPuntos = new List<PointLatLng>();
            double latitud;
            double longitud;
            foreach (Ruta r in listaRutas)
            {
                if (r.Linea == linea)
                {
                    latitud = Convert.ToDouble(r.Latitud);
                    longitud = Convert.ToDouble(r.Longitud);
                    listaPuntos.Add(new PointLatLng(latitud, longitud));
                }
            }
            return listaPuntos;
        }

        public static List<String> regresaNombres()
        {
            JavaScriptSerializer serial = new JavaScriptSerializer();
            List<Ruta> listaRutas = serial.Deserialize<List<Ruta>>(json);
            List<String> nombres = new List<String>();
            foreach (Ruta r in listaRutas)
            {
                //if (r.Linea == linea)
                //{
                    nombres.Add(r.Nombre);
                //}
            }

            return nombres;
        }
        
        public static List<String> regresaNombres(int linea)
        {
            JavaScriptSerializer serial = new JavaScriptSerializer();
            List<Ruta> listaRutas = serial.Deserialize<List<Ruta>>(json);
            List<String> nombres = new List<String>();
            foreach (Ruta r in listaRutas)
            {
                if (r.Linea == linea)
                {
                nombres.Add(r.Nombre);
                }
            }

            return nombres;
        }
    }
}
