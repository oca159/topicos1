<%@ Page Title="" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="Estudiantes.aspx.cs" Inherits="ProyWeb.Estudiantes" %>
<asp:Content ID="Content1" ContentPlaceHolderID="HeadContent" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
        <div class="row">
            <div class="col-md-6">
                <asp:Label ID="Label1" runat="server" Text="Nombre:"></asp:Label>
                <br />
                <asp:TextBox ID="TxtNombre" runat="server" Width="145px"></asp:TextBox>
                <br />
                <asp:Label ID="Label2" runat="server" Text="Apellido Paterno:"></asp:Label>
                <br />
                <asp:TextBox ID="TxtAPaterno" runat="server"></asp:TextBox>
                <br />
                <asp:Label ID="Label3" runat="server" Text="Apellido Materno: "></asp:Label>
                <br />
                <asp:TextBox ID="TxtAMaterno" runat="server"></asp:TextBox>
                <br />
                <asp:Label ID="Label4" runat="server" Text="Promedio: "></asp:Label>
                <br />
                <asp:TextBox ID="TxtPromedio" runat="server"></asp:TextBox>
                <br />
                <asp:Label ID="Label5" runat="server" Text="Matricula: "></asp:Label>
                <br />
                <asp:TextBox ID="TxtMatricula" runat="server"></asp:TextBox>
                <br />
                <br />
                <asp:Button ID="BGuardar" runat="server" onclick="BGuardar_Click" 
                    Text="Guardar" CssClass="btn btn-success" />
                <br />
            </div>
            <%--<div class="col-md-6">
                <table class="table table-bordered table-striped">
                    <thead>
                        <tr align="center">
                            <th>Nombre</th>
                            <th>Apellido Paterno</th>
                            <th>Apellido Materno</th>
                            <th>Matricula</th>
                            <th>Promedio General</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>1</td>
                            <td>2</td>
                            <td>3</td>
                            <td>4</td>
                            <td>5</td>
                        </tr>
                    </tbody>
                    
                </table>
            </div>--%>
        </div>
</asp:Content>
