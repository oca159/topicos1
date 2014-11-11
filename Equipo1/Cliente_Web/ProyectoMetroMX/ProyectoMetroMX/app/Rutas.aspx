<%@ Page Title="" Language="C#" MasterPageFile="~/Principal.Master" AutoEventWireup="true"
    CodeBehind="Rutas.aspx.cs" Inherits="ProyectoMetroMX.Rutas" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
    <div class="container">
        <aside class="right-side">
        <h1 class="text-center text-info">Mapa de las rutas del metro en el DF</h1>
        <br />
        <div id="googleMap" style="width: 500px; height: 380px;" align="center"></div>
    </aside>
    </div>
    <script src="js/jquery-2.1.1.min.js" type="text/javascript"></script>
    <script src="js/bootstrap.min.js" type="text/javascript"></script>
    <script src="js/AdminLTE/app.js" type="text/javascript"></script>
    <script src="http://maps.googleapis.com/maps/api/js?key=AIzaSyDY0kkJiTPVd2U7aTOAwhc9ySH6oHxOIYM&sensor=true"></script>
   <%-- <script type="text/javascript">
        arreglo1 = [];
        arreglo2 = [];
        arreglo3 = [];
        arreglo4 = [];
        arreglo5 = [];
        arreglo6 = [];
        arreglo7 = [];
        arreglo8 = [];
        arreglo9 = [];
        arreglo10 = [];
        arreglo11 = [];
        $.getJSON("http://equipo1.azurewebsites.net/ServiceRutas.svc/GetRutas", function (tareas) {
            if (tareas.length > 0) {
                var array = [];
                for (var i = 0; i < tareas.length; i++) {
                    var aux = $.map(tareas[i], function (value, index) {
                        return [value];
                    });
                    array.push(aux);
                    //$('#example2').dataTable().fnAddData(array);
                } //fin del for

                for (var i = 0; i < array.length; i++) {
                    //lleno los 11 arreglos con sus lineas correspondientes
                    switch (array[i][3]) {
                        case 1:
                            arreglo1.push(new google.maps.LatLng(array[i][2], array[i][4]));
                            break;
                        case 2:
                            arreglo2.push(new google.maps.LatLng(array[i][2], array[i][4]));
                            break;
                        case 3:
                            arreglo3.push(new google.maps.LatLng(array[i][2], array[i][4]));

                            break;
                        case 4:
                            arreglo4.push(new google.maps.LatLng(array[i][2], array[i][4]));

                            break;
                        case 5:
                            arreglo5.push(new google.maps.LatLng(array[i][2], array[i][4]));

                            break;
                        case 6:
                            arreglo6.push(new google.maps.LatLng(array[i][2], array[i][4]));

                            break;
                        case 7:
                            arreglo7.push(new google.maps.LatLng(array[i][2], array[i][4]));
                            break;
                        case 8:
                            arreglo8.push(new google.maps.LatLng(array[i][2], array[i][4]));
                            break;
                        case 9:
                            arreglo9.push(new google.maps.LatLng(array[i][2], array[i][4]));
                            break;
                        case 10:
                            arreglo10.push(new google.maps.LatLng(array[i][2], array[i][4]));
                            break;
                        case 11:
                            arreglo11.push(new google.maps.LatLng(array[i][2], array[i][4]));
                            break;
                    }
                } //fin del for
            }
        });
        function initialize() {
            console.log(arreglo1);
            var mapProp = {
                center: new google.maps.LatLng(19.398237, -99.200363),
                zoom: 10,
                mapTypeId: google.maps.MapTypeId.ROADMAP
            };
            var map = new google.maps.Map(document.getElementById("googleMap"), mapProp);

            var myTrip1 = arreglo1;
            var flightPath1 = new google.maps.Polyline({
                path: myTrip1,
                strokeColor: "#FF00CC",
                strokeOpacity: 0.8,
                strokeWeight: 4
            });
            var myTrip2 = arreglo2;
            var flightPath2 = new google.maps.Polyline({
                path: myTrip2,
                strokeColor: "#0000ff",
                strokeOpacity: 0.8,
                strokeWeight: 4
            });
            var myTrip3 = arreglo3;
            var flightPath3 = new google.maps.Polyline({
                path: myTrip3,
                strokeColor: "#999927",
                strokeOpacity: 0.8,
                strokeWeight: 4
            });
            var myTrip4 = arreglo4;
            var flightPath4 = new google.maps.Polyline({
                path: myTrip4,
                strokeColor: "#59c8c4",
                strokeOpacity: 0.8,
                strokeWeight: 4
            });
            var myTrip5 = arreglo5;
            var flightPath5 = new google.maps.Polyline({
                path: myTrip5,
                strokeColor: "#FFFF00",
                strokeOpacity: 0.8,
                strokeWeight: 4
            });
            var myTrip6 = arreglo6;
            var flightPath6 = new google.maps.Polyline({
                path: myTrip6,
                strokeColor: "#FF0000",
                strokeOpacity: 0.8,
                strokeWeight: 4
            });

            var myTrip7 = arreglo7;
            var flightPath7 = new google.maps.Polyline({
                path: myTrip7,
                strokeColor: "#f7880a",
                strokeOpacity: 0.8,
                strokeWeight: 4
            });

            var myTrip8 = arreglo8;
            var flightPath8 = new google.maps.Polyline({
                path: myTrip8,
                strokeColor: "#27996B",
                strokeOpacity: 0.8,
                strokeWeight: 4
            });

            var myTrip9 = arreglo9;
            var flightPath9 = new google.maps.Polyline({
                path: myTrip9,
                strokeColor: "#934342",
                strokeOpacity: 0.8,
                strokeWeight: 4
            });

            var myTripA = arreglo10;
            var flightPathA = new google.maps.Polyline({
                path: myTripA,
                strokeColor: "#A3277C",
                strokeOpacity: 0.8,
                strokeWeight: 4
            });

            var myTripB = arreglo11;
            var flightPathB = new google.maps.Polyline({
                path: myTripB,
                strokeColor: "#048A66",
                strokeOpacity: 0.8,
                strokeWeight: 4
            });
            console.log(arreglo1);
            flightPath1.setMap(map);
            flightPath2.setMap(map);
            flightPath3.setMap(map);
            flightPath4.setMap(map);
            flightPath5.setMap(map);
            flightPath6.setMap(map);
            flightPath7.setMap(map);
            flightPath8.setMap(map);
            flightPath9.setMap(map);
            flightPathA.setMap(map);
            flightPathB.setMap(map);
        };
        google.maps.event.addDomListener(window, 'load', initialize);

    </script>--%>
    <script>
        //linea1
        var Observatorior = new google.maps.LatLng(19.398237, -99.200363);
        var Tacubayar = new google.maps.LatLng(19.403439, -99.187102);
        var Juenacatlanr = new google.maps.LatLng(19.41289, -99.182167);
        var Chapultepec = new google.maps.LatLng(19.42083, -99.176943);
        var Sevillar = new google.maps.LatLng(19.421916, -99.17058);
        var Insurgentesr = new google.maps.LatLng(19.423292, -99.163177);
        var Cuauhtemocr = new google.maps.LatLng(19.425862, -99.154701);
        var Balderasr = new google.maps.LatLng(19.42744, -99.149036);
        var Salto_del_Aguar = new google.maps.LatLng(19.426813, -99.142213);
        var Isabel_la_Catolicar = new google.maps.LatLng(19.426732, -99.137685);
        var Pino_Suarezr = new google.maps.LatLng(19.425336, -99.132943);
        var Mercedr = new google.maps.LatLng(19.425558, -99.124639);
        var Candelariar = new google.maps.LatLng(19.428837, -99.119511);
        var San_Lazaror = new google.maps.LatLng(19.430213, -99.114833);
        var Moctezumar = new google.maps.LatLng(19.427218, -99.110305);
        var Balbuenar = new google.maps.LatLng(19.423231, -99.102302);
        var Blvd_Puerto_Aereor = new google.maps.LatLng(19.41967, -99.09595);
        var Gomez_Fariasr = new google.maps.LatLng(19.416472, -99.09035);
        var Zaragozar = new google.maps.LatLng(19.412344, -99.08241);
        var Pantitlanr = new google.maps.LatLng(19.415359, -99.072132);
        //linea2
        var Cuatro_Caminosr = new google.maps.LatLng(19.459592, -99.215899);
        var Panteonesr = new google.maps.LatLng(19.458763, -99.203153);
        var Tacubar = new google.maps.LatLng(19.459349, -99.189205);
        var Cuitlahuacr = new google.maps.LatLng(19.457448, -99.182038);
        var Popotlar = new google.maps.LatLng(19.452147, -99.1747);
        var Colegio_Militarr = new google.maps.LatLng(19.449354, -99.172254);
        var Normalr = new google.maps.LatLng(19.4444, -99.1674);
        var San_Cosmer = new google.maps.LatLng(19.441706, -99.161096);
        var Revolucionr = new google.maps.LatLng(19.441706, -99.161096);
        var Hidalgor = new google.maps.LatLng(19.437295, -99.147105);
        var Bellas_Artesr = new google.maps.LatLng(19.436243, -99.141945);
        var Allender = new google.maps.LatLng(19.435514, -99.137406);
        var Zocalor = new google.maps.LatLng(19.433248, -99.1329);
        //var  Pino_Suarezr= new google.maps.LatLng (19.425336,-99.132943);
        var San_Antonio_Abadr = new google.maps.LatLng(19.418597, -99.134145);
        var Chabacanor = new google.maps.LatLng(19.406438, -99.135754);
        var Viaductor = new google.maps.LatLng(19.400808, -99.136891);
        var Xolar = new google.maps.LatLng(19.3952, -99.1377);
        var Villa_de_Cortesr = new google.maps.LatLng(19.38753, -99.138994);
        var Nativitasr = new google.maps.LatLng(19.379474, -99.140217);
        var Portalesr = new google.maps.LatLng(19.3698, -99.1416);
        var Ermitar = new google.maps.LatLng(19.361883, -99.142942);
        var General_Anayar = new google.maps.LatLng(19.353259, -99.145002);
        var Tasquenar = new google.maps.LatLng(19.344168, -99.142685);

        //linea3
        var Indios_Verdesr = new google.maps.LatLng(19.495358, -99.119468);
        var Deportivo_18_de_Marzor = new google.maps.LatLng(19.483747, -99.126549);
        var Potreror = new google.maps.LatLng(19.47691, -99.132171);
        var La_Razar = new google.maps.LatLng(19.470153, -99.136891);
        var Tlatelolcor = new google.maps.LatLng(19.454979, -99.142814);
        var Guerreror = new google.maps.LatLng(19.445146, -99.145389);
        //var  Hidalgor= new google.maps.LatLng (19.437295,-99.147105);
        var Juarezr = new google.maps.LatLng(19.433167, -99.147792);
        //var  Balderasr= new google.maps.LatLng (19.42744,-99.149036);
        var Ninos_Heroesr = new google.maps.LatLng(19.419508, -99.150581);
        var Hospital_Generalr = new google.maps.LatLng(19.413578, -99.153886);
        var Centro_Medicor = new google.maps.LatLng(19.406637, -99.155753);
        var Etiopiar = new google.maps.LatLng(19.395586, -99.156268);
        var Eugeniar = new google.maps.LatLng(19.406637, -99.155753);
        var Division_del_Norter = new google.maps.LatLng(19.380021, -99.158885);
        var Zapatar = new google.maps.LatLng(19.370952, -99.164937);
        var Coyoacanr = new google.maps.LatLng(19.361417, -99.170709);
        var Viverosr = new google.maps.LatLng(19.353724, -99.176052);
        var Miguel_A_de_Quevedor = new google.maps.LatLng(19.346395, -99.18103);
        var Copilcor = new google.maps.LatLng(19.335887, -99.176652);
        var Universidadr = new google.maps.LatLng(19.324427, -99.17397);

        //linea4
        var Martin_Carrerar = new google.maps.LatLng(19.484921, -99.104404);
        var Talismanr = new google.maps.LatLng(19.47428, -99.108009);
        var Bondojitor = new google.maps.LatLng(19.46461, -99.111958);
        var Consulador = new google.maps.LatLng(19.457893, -99.113932);
        var Canal_del_Norter = new google.maps.LatLng(19.449112, -99.116507);
        var Morelosr = new google.maps.LatLng(19.438974, -99.118266);
        //var  Candelariar= new google.maps.LatLng (19.428837,-99.119511);
        var Fray_Servandor = new google.maps.LatLng(19.421633, -99.120541);
        var Jamaicar = new google.maps.LatLng(19.409004, -99.122171);
        var Santa_Anitar = new google.maps.LatLng(19.40273, -99.121699);

        //linea5
        var Politecnicor = new google.maps.LatLng(19.50082, -99.149294);
        var Inst_del_Petroleor = new google.maps.LatLng(19.489573, -99.14526);
        var Autobuses_del_Norter = new google.maps.LatLng(19.478973, -99.140668);
        //var  La_Razar= new google.maps.LatLng (19.470153,-99.136891);
        var Misteriosr = new google.maps.LatLng(19.463315, -99.130797);
        var Valle_Gomezr = new google.maps.LatLng(19.458742, -99.119296);
        //var  Consulador= new google.maps.LatLng (19.457893,-99.113932);
        var Eduardo_Molinar = new google.maps.LatLng(19.451378, -99.105434);
        var Aragonr = new google.maps.LatLng(19.451236, -99.095886);
        var Oceaniar = new google.maps.LatLng(19.445672, -99.087238);
        var Terminal_Aerear = new google.maps.LatLng(19.433734, -99.087667);
        var Hangaresr = new google.maps.LatLng(19.424142, -99.087496);
        //var  Pantitlanr= new google.maps.LatLng (19.415359,-99.072132);

        //linea6
        var El_Rosarior = new google.maps.LatLng(19.504541, -99.20002);
        var Tezozomocr = new google.maps.LatLng(19.495116, -99.196286);
        var Azcapotzalcor = new google.maps.LatLng(19.490989, -99.186416);
        var Ferreriar = new google.maps.LatLng(19.490746, -99.173841);
        var Norte_45r = new google.maps.LatLng(19.488683, -99.162769);
        var Vallejor = new google.maps.LatLng(19.490625, -99.15586);
        //var  Inst_del_Petroleor= new google.maps.LatLng (19.489573,-99.14526);
        var Lindavistar = new google.maps.LatLng(19.487712, -99.13466);
        //var  Deportivo_18_de_Marzor= new google.maps.LatLng (19.483747,-99.126549);
        var La_Villa_Basilicar = new google.maps.LatLng(19.481522, -99.117923);
        //var Martin_Carrerar = new google.maps.LatLng(19.484921, -99.104404);

        //linea7
        //var  El_Rosarior= new google.maps.LatLng (19.504541,-99.20002);
        var Aquiles_Serdanr = new google.maps.LatLng(19.490423, -99.19487);
        var Camaronesr = new google.maps.LatLng(19.479135, -99.190063);
        var Refineriar = new google.maps.LatLng(19.469951, -99.190578);
        //var  Tacubar= new google.maps.LatLng (19.459349,-99.189205);
        var San_Joaquinr = new google.maps.LatLng(19.445793, -99.191823);
        var Polancor = new google.maps.LatLng(19.433511, -99.191029);
        var Auditorior = new google.maps.LatLng(19.425498, -99.191995);
        var Constituyentesr = new google.maps.LatLng(19.411858, -99.191265);
        //var  Tacubayar= new google.maps.LatLng (19.403439,-99.187102);
        var San_Pedro_de_los_Pinosr = new google.maps.LatLng(19.391275, -99.186051);
        var San_Antonior = new google.maps.LatLng(19.384757, -99.186308);
        var Mixcoacr = new google.maps.LatLng(19.375891, -99.187531);
        var Barranca_del_Muertor = new google.maps.LatLng(19.360648, -99.190149);

        //linea8
        var Garibaldir = new google.maps.LatLng(19.444458, -99.139734);
        //var  Bellas_Artesr= new google.maps.LatLng (19.436243,-99.141945);
        var San_Juan_de_Letranr = new google.maps.LatLng(19.431305, -99.141569);
        //var  Salto_del_Aguar= new google.maps.LatLng (19.426813,-99.142213);
        var Doctoresr = new google.maps.LatLng(19.421612, -99.143372);
        var Obrerar = new google.maps.LatLng(19.413558, -99.144187);
        //var  Chabacanor= new google.maps.LatLng (19.406438,-99.135754);
        var La_Vigar = new google.maps.LatLng(19.406495, -99.126248);
        //var  Santa_Anitar= new google.maps.LatLng (19.40273,-99.121699);
        var Coyuyar = new google.maps.LatLng(19.398521, -99.113545);
        var Iztacalcor = new google.maps.LatLng(19.398521, -99.113545);
        var Apatlacor = new google.maps.LatLng(19.379292, -99.109597);
        var Aculcor = new google.maps.LatLng(19.374069, -99.108095);
        var Escuadron_201r = new google.maps.LatLng(19.36492, -99.109554);
        var Atlalilcor = new google.maps.LatLng(19.356134, -99.101315);
        var Iztapalapar = new google.maps.LatLng(19.357834, -99.093461);
        var Cerro_de_la_Estrellar = new google.maps.LatLng(19.356012, -99.085522);
        var U_A_M_Ir = new google.maps.LatLng(19.351234, -99.074707);
        var Const_de_1917r = new google.maps.LatLng(19.34593, -99.063892);

        //linea9
        //var  Tacubayar= new google.maps.LatLng (19.403439,-99.187102);
        var Patriotismor = new google.maps.LatLng(19.406212, -99.178905);
        var Chilpancingor = new google.maps.LatLng(19.406131, -99.168477);
        //var  Centro_Medicor= new google.maps.LatLng (19.406637,-99.155753);
        var Lazaro_Cardenasr = new google.maps.LatLng(19.40696, -99.144874);
        //var  Chabacanor= new google.maps.LatLng (19.406438,-99.135754);
        //var  Jamaicar= new google.maps.LatLng (19.409004,-99.122171);
        var Mixiuhcar = new google.maps.LatLng(19.408478, -99.112902);
        var Velodromor = new google.maps.LatLng(19.408478, -99.103074);
        var Ciudad_Deportivar = new google.maps.LatLng(19.408357, -99.091229);
        var Pueblar = new google.maps.LatLng(19.407142, -99.082432);
        //var  Pantitlanr= new google.maps.LatLng (19.415359,-99.072132);
        //var  Pantitlanr= new google.maps.LatLng (19.415359,-99.072132);

        //lineaA
        var Agricola_Orientalr = new google.maps.LatLng(19.404835, -99.069901);
        var Canal_de_San_Juanr = new google.maps.LatLng(19.398683, -99.059365);
        var Tepalcatesr = new google.maps.LatLng(19.391335, -99.046383);
        var Guelataor = new google.maps.LatLng(19.385162, -99.03574);
        var Penon_Viejor = new google.maps.LatLng(19.37332, -99.017093);
        var Acatitlar = new google.maps.LatLng(19.364737, -99.005892);
        var Santa_Martar = new google.maps.LatLng(19.36, -98.994863);
        var Los_Reyesr = new google.maps.LatLng(19.358968, -98.976903);
        var La_Pazr = new google.maps.LatLng(19.350526, -98.960745);

        //lineaB
        var Buenavistar = new google.maps.LatLng(19.446603, -99.153199);
        //var  Guerreror= new google.maps.LatLng (19.445146,-99.145389);
        //var  Garibaldir= new google.maps.LatLng (19.444458,-99.139734);
        var Lagunillar = new google.maps.LatLng(19.443386, -99.131334);
        var Tepitor = new google.maps.LatLng(19.442495, -99.12333);
        //var  Morelosr= new google.maps.LatLng (19.438974,-99.118266);
        //var  San_Lazaror= new google.maps.LatLng (19.430213,-99.114833);
        var Rcardo_Flores_Magonr = new google.maps.LatLng(19.436567, -99.103675);
        var Romero_Rubior = new google.maps.LatLng(19.436567, -99.103675);
        //var  Oceaniar= new google.maps.LatLng (19.445672,-99.087238);
        var Deportivo_Oceaniar = new google.maps.LatLng(19.451034, -99.07917);
        var Bosque_de_Aragonr = new google.maps.LatLng(19.458115, -99.069192);
        var Villa_de_Aragonr = new google.maps.LatLng(19.461656, -99.061704);
        var Nezahualcoyotlr = new google.maps.LatLng(19.473066, -99.054537);
        var Impulsorar = new google.maps.LatLng(19.485851, -99.048915);
        var Rio_de_los_Remediosr = new google.maps.LatLng(19.490908, -99.046597);
        var Muzquizr = new google.maps.LatLng(19.501629, -99.042048);
        var Ecatepecr = new google.maps.LatLng(19.515301, -99.035997);
        var Olimpicar = new google.maps.LatLng(19.521328, -99.033422);
        var Plaza_Aragonr = new google.maps.LatLng(19.528447, -99.030118);
        var Ciudad_Aztecar = new google.maps.LatLng(19.534595, -99.0275);
        //linea12
        var Tlahuacr = new google.maps.LatLng(19.286059, -99.014618);
        var Tlaltencor = new google.maps.LatLng(19.294385, -99.024655);
        var Zapotitlanr = new google.maps.LatLng(19.296635, -99.034240);
        var Nopalerar = new google.maps.LatLng(19.299992, -99.045927);
        var Olivosr = new google.maps.LatLng(19.304204, -99.059231);
        var Tezoncor = new google.maps.LatLng(19.306284, -99.065365);
        var Periferico_Orienter = new google.maps.LatLng(19.317554, -99.074541);
        var Calle11r = new google.maps.LatLng(19.320612, -99.085924);
        var Lomas_Estrellar = new google.maps.LatLng(19.322114, -99.095704);
        var San_Andres_Tomatlanr = new google.maps.LatLng(19.328084, -99.104379);
        var Culhuacanr = new google.maps.LatLng(19.336791, -99.108915);
        var Mexicatltzingor = new google.maps.LatLng(19.357649, -99.121884);
        var Eje_Centralr = new google.maps.LatLng(19.361306, -99.151513);
        var Parque_de_los_Venadosr = new google.maps.LatLng(19.370716, -99.158803);
        var Hospital_20_noviembrer = new google.maps.LatLng(19.372031, -99.171041);
        var Insurgentes_surr = new google.maps.LatLng(19.373570, -99.178723);
        var Mixcoac = new google.maps.LatLng(19.376181, -99.187885);

        function initialize() {
            var mapProp = {
                center: new google.maps.LatLng(19.398237, -99.200363),
                zoom: 10,
                mapTypeId: google.maps.MapTypeId.ROADMAP
            };
            var map = new google.maps.Map(document.getElementById("googleMap")
  , mapProp);

            var myTrip1 = [Observatorior, Tacubayar, Juenacatlanr, Chapultepec, Sevillar, Insurgentesr, Cuauhtemocr, Balderasr, Salto_del_Aguar, Isabel_la_Catolicar, Pino_Suarezr, Mercedr, Candelariar, San_Lazaror, Moctezumar, Balbuenar, Blvd_Puerto_Aereor, Gomez_Fariasr, Zaragozar, Pantitlanr];
            var flightPath1 = new google.maps.Polyline({
                path: myTrip1,
                strokeColor: "#FF00CC",
                strokeOpacity: 0.8,
                strokeWeight: 4
            });
            var myTrip2 = [Cuatro_Caminosr, Panteonesr, Tacubar, Cuitlahuacr, Popotlar, Colegio_Militarr, Normalr, San_Cosmer, Revolucionr, Hidalgor, Bellas_Artesr, Allender, Zocalor, Pino_Suarezr, San_Antonio_Abadr, Chabacanor, Viaductor, Xolar, Villa_de_Cortesr, Nativitasr, Portalesr, Ermitar, General_Anayar, Tasquenar];
            var flightPath2 = new google.maps.Polyline({
                path: myTrip2,
                strokeColor: "#0000ff",
                strokeOpacity: 0.8,
                strokeWeight: 4
            });
            var myTrip3 = [Indios_Verdesr, Deportivo_18_de_Marzor, Potreror, La_Razar, Tlatelolcor, Guerreror, Hidalgor, Juarezr, Balderasr, Ninos_Heroesr, Hospital_Generalr, Centro_Medicor, Etiopiar, Eugeniar, Division_del_Norter, Zapatar, Coyoacanr, Viverosr, Miguel_A_de_Quevedor, Copilcor, Universidadr];
            var flightPath3 = new google.maps.Polyline({
                path: myTrip3,
                strokeColor: "#999927",
                strokeOpacity: 0.8,
                strokeWeight: 4
            });
            var myTrip4 = [Martin_Carrerar, Talismanr, Bondojitor, Consulador, Canal_del_Norter, Morelosr, Candelariar, Jamaicar, Santa_Anitar];
            var flightPath4 = new google.maps.Polyline({
                path: myTrip4,
                strokeColor: "#59c8c4",
                strokeOpacity: 0.8,
                strokeWeight: 4
            });
            var myTrip5 = [Pantitlanr, Hangaresr, Terminal_Aerear, Oceaniar, Eduardo_Molinar, Consulador, Valle_Gomezr, Misteriosr, La_Razar, Autobuses_del_Norter, Inst_del_Petroleor, Politecnicor];
            var flightPath5 = new google.maps.Polyline({
                path: myTrip5,
                strokeColor: "#FFFF00",
                strokeOpacity: 0.8,
                strokeWeight: 4
            });
            var myTrip6 = [El_Rosarior, Tezozomocr, Azcapotzalcor, Ferreriar, Norte_45r, Vallejor, Inst_del_Petroleor, Lindavistar, Deportivo_18_de_Marzor, La_Villa_Basilicar, Martin_Carrerar];
            var flightPath6 = new google.maps.Polyline({
                path: myTrip6,
                strokeColor: "#FF0000",
                strokeOpacity: 0.8,
                strokeWeight: 4
            });

            var myTrip7 = [El_Rosarior, Aquiles_Serdanr, Camaronesr, Refineriar, Tacubar, San_Joaquinr, Polancor, Auditorior, Constituyentesr, Tacubayar, San_Pedro_de_los_Pinosr, San_Antonior, Mixcoacr, Barranca_del_Muertor];
            var flightPath7 = new google.maps.Polyline({
                path: myTrip7,
                strokeColor: "#f7880a",
                strokeOpacity: 0.8,
                strokeWeight: 4
            });

            var myTrip8 = [Garibaldir, Bellas_Artesr, San_Juan_de_Letranr, Salto_del_Aguar, Doctoresr, Obrerar, Chabacanor, La_Vigar, Santa_Anitar, Coyuyar, Iztacalcor, Apatlacor, Aculcor, Escuadron_201r, Atlalilcor, Iztapalapar, Cerro_de_la_Estrellar, U_A_M_Ir, Const_de_1917r];
            var flightPath8 = new google.maps.Polyline({
                path: myTrip8,
                strokeColor: "#27996B",
                strokeOpacity: 0.8,
                strokeWeight: 4
            });

            var myTrip9 = [Tacubayar, Patriotismor, Chilpancingor, Centro_Medicor, Lazaro_Cardenasr, Chabacanor, Jamaicar, Mixiuhcar, Velodromor, Ciudad_Deportivar, Pueblar, Pantitlanr];
            var flightPath9 = new google.maps.Polyline({
                path: myTrip9,
                strokeColor: "#934342",
                strokeOpacity: 0.8,
                strokeWeight: 4
            });

            var myTripA = [Pantitlanr, Agricola_Orientalr, Canal_de_San_Juanr, Tepalcatesr, Guelataor, Penon_Viejor, Acatitlar, Santa_Martar, Los_Reyesr, La_Pazr];
            var flightPathA = new google.maps.Polyline({
                path: myTripA,
                strokeColor: "#A3277C",
                strokeOpacity: 0.8,
                strokeWeight: 4
            });

            var myTripB = [Ciudad_Aztecar, Plaza_Aragonr, Ecatepecr, Olimpicar, Muzquizr, Rio_de_los_Remediosr, Impulsorar, Nezahualcoyotlr, Villa_de_Aragonr, Bosque_de_Aragonr, Deportivo_Oceaniar, Oceaniar, Romero_Rubior, Rcardo_Flores_Magonr, San_Lazaror, Morelosr, Tepitor, Lagunillar, Garibaldir, Guerreror, Buenavistar];
            var flightPathB = new google.maps.Polyline({
                path: myTripB,
                strokeColor: "#048A66",
                strokeOpacity: 0.8,
                strokeWeight: 4
            });

            var myTrip12 = [Tlahuacr, Tlaltencor, Zapotitlanr, Nopalerar, Olivosr, Tezoncor, Periferico_Orienter, Calle11r, Lomas_Estrellar, San_Andres_Tomatlanr, Culhuacanr, Atlalilcor,
                          Mexicatltzingor, Ermitar, Eje_Centralr, Parque_de_los_Venadosr, Zapatar, Hospital_20_noviembrer, Insurgentes_surr, Mixcoac];
            var flightPath12 = new google.maps.Polyline({
                path: myTrip12,
                strokeColor: "#b8860b",
                strokeOpacity: 0.8,
                strokeWeight: 4
            });

            flightPath1.setMap(map);
            flightPath2.setMap(map);
            flightPath3.setMap(map);
            flightPath4.setMap(map);
            flightPath5.setMap(map);
            flightPath6.setMap(map);
            flightPath7.setMap(map);
            flightPath8.setMap(map);
            flightPath9.setMap(map);
            flightPathA.setMap(map);
            flightPathB.setMap(map);
            flightPath12.setMap(map);
        }



        google.maps.event.addDomListener(window, 'load', initialize);
    </script>
     <script type="text/javascript">
        
</asp:Content>
