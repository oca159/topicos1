using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Net;            //   Paso 1
using System.Net.Sockets;    //   Paso 1

namespace Cliente
{
    class Program
    {
        static void Main(string[] args)
        {
            Conectar();
        }
        public static void Conectar()
        {
            Socket miPrimerSocket = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);
            // paso 2 - creamos el socket
            IPEndPoint miDireccion = new IPEndPoint(IPAddress.Parse("127.0.0.1"), 1234);
            //paso 3 - Acá debemos poner la Ip del servidor, y el puerto de escucha del servidor
            try
            {
                miPrimerSocket.Connect(miDireccion); // Conectamos                
                Console.WriteLine("Conectado con exito");
                miPrimerSocket.Close();
            }
            catch (Exception error)
            {
                Console.WriteLine("Error: {0}", error.ToString());
            }
            Console.WriteLine("Presione cualquier tecla para terminar");
            Console.ReadLine();
        }
    }
}