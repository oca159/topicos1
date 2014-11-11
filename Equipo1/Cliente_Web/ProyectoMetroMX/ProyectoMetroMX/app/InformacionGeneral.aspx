<%@ Page Title="" Language="C#" MasterPageFile="~/Principal.Master" AutoEventWireup="true" CodeBehind="InformacionGeneral.aspx.cs" Inherits="ProyectoMetroMX.InformacionGeneral" %>
<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
<div class="container">
    <aside class="right-side">
        <h1 class="text-center text-info">Tabla con toda la información de las estaciones</h1>
        <br />
        <div class="box-body table-responsive">
            <table id="example2" class="table table-bordered table-striped">
            <thead>
                <tr>
                    <th>Latitud</th>
                    <th>Longitud</th>
                    <th>Nombre</th>
                </tr>
            </thead>
            <tbody>
                
            </tbody>
            <tfoot>
                <tr>
                    <th>Latitud</th>
                    <th>Longitud</th>
                    <th>Nombre</th>
                </tr>
            </tfoot>
            </table>
        </div>
    </aside>
</div>
    <script src="js/jquery-2.1.1.min.js" type="text/javascript"></script>
    <script src="js/bootstrap.min.js" type="text/javascript"></script>
    <script src="js/plugins/datatables/jquery.dataTables.js" type="text/javascript"></script>
    <script src="js/plugins/datatables/dataTables.bootstrap.js" type="text/javascript"></script>
    <script src="js/AdminLTE/app.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $.getJSON("http://equipo1.azurewebsites.net/ServiceRutas.svc/GetEstacionByLatLong", function (tareas) {
                console.log(tareas[0]);
                if (tareas.length > 0) {
                    for (var i = 0; i < tareas.length; i++) {
                        var array = $.map(tareas[i], function (value, index) {
                            return [value];
                        });
                        $('#example2').dataTable().fnAddData(array);
                    }
                }
            });
        });
    </script>
</asp:Content>
