<%@ Page Title="" Language="C#" MasterPageFile="~/Principal.Master" AutoEventWireup="true"
    CodeBehind="Default.aspx.cs" Inherits="ProyectoMetroMX.app.Default" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
<div class="container">
    <aside class="right-side">
        <h1 class="text-center text-info">Información básica</h1>
        <div class="jumbotron">
            <div class="row">
                <div class="col-md-12">
                    <img src="img/metro3.png" alt="MetroDF" />
                    <h2>Más que una simple aplicación...</h2>
                    <p>Red Metro MX es un proyecto sin fines de lucro que surgió con la intención de ser la aplicación número en el tema de obtención de rutas óptimas, localización de estaciones y visualización de horarios.</p>
                    <p>
                        <a class="btn btn-primary btn-lg" href="#" role="button">Ver más.</a>
                    </p>
                </div>
            </div>
        </div>
    </aside>
</div>
    <script src="js/jquery-2.1.1.min.js" type="text/javascript"></script>
    <script src="js/bootstrap.min.js" type="text/javascript"></script>
    <script src="js/AdminLTE/app.js" type="text/javascript"></script>
</asp:Content>
