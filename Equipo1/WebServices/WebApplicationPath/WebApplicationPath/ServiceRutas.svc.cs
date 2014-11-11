using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.Text;
using WebApplicationPath.Model;

namespace WebApplicationPath
{
    // NOTA: puede usar el comando "Rename" del menú "Refactorizar" para cambiar el nombre de clase "ServiceRutas" en el código, en svc y en el archivo de configuración a la vez.
    public class ServiceRutas : IServiceRutas
    {
        public Ruta[] GetRutaByLinea(int linea)
        {
            Ruta[] rObj = null;
            try
            {
                equipo1Entities eq = new equipo1Entities();
                rObj = (from e in eq.rutas
                        where e.linea == linea
                        select new Ruta
                        {
                            Id = e.idRutas,
                            //Numero = e.numRuta,
                            Linea = e.linea,
                            Nombre = e.nombre,
                            Latitud = e.latitud,
                            Longitud = e.longitud,
                            Afluencia = e.afluencia
                        }).ToArray();

            }
            catch (Exception ex) { }
            return rObj;
        }

        public Ruta[] GetRutas()
        {
            Ruta[] rObj = null;
            try
            {
                equipo1Entities eq = new equipo1Entities();
                rObj = (from e in eq.rutas
                               select new Ruta
                               {
                                   Id = e.idRutas,
                                   //Numero = e.numRuta,
                                   Linea = e.linea,
                                   Nombre = e.nombre,
                                   Latitud = e.latitud,
                                   Longitud = e.longitud,
                                   Afluencia = e.afluencia
                               }).ToArray();
                
            }
            catch (Exception ex) {  }
            return rObj;
        }

        public Ruta[] GetRutaByName(String nombre)
        {
            Ruta[] rObj = null;
            try
            {
            equipo1Entities eq = new equipo1Entities();
                rObj = (from e in eq.rutas where e.nombre == nombre
                               select new Ruta
                               {
                                   Id = e.idRutas,
                                   //Numero = e.numRuta,
                                   Linea = e.linea,
                                   Nombre = e.nombre,
                                   Latitud = e.latitud,
                                   Longitud = e.longitud,
                                   Afluencia = e.afluencia
                               }).ToArray();
                
            }
            catch (Exception ex) {  }
            return rObj;
        }

        public Estacion[] GetEstacionByLatLong()
        {
            Estacion[] rObj = null;
            try
            {
                equipo1Entities eq = new equipo1Entities();
                rObj = (from e in eq.rutas
                        select new Estacion
                        {
                            Nombre = e.nombre,
                            Latitud = e.latitud,
                            Longitud = e.longitud
                        }).ToArray();

            }
            catch (Exception ex) { }
            return rObj;
        }

        public Horario[] GetHorarios()
        {
            Horario[] rObj = null;
            try
            {
                equipo1Entities eq = new equipo1Entities();
                rObj = (from e in eq.horarios
                        select new Horario
                        {
                            Id = e.idHorarios,
                            Dia = e.dia,
                            Inicio = e.hInicio,
                            Fin = e.hFin
                        }).ToArray();

            }
            catch (Exception ex) { }
            return rObj;
        }
    }
}
