<%@page import="java.util.List"%>
<%@page import="com.emergentes.modelo.Estudiante"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    List<Estudiante> estudiantes = (List<Estudiante>) request.getAttribute("estudiantes");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            .contenedor {
                width: 500px;
                margin: 0 auto;
                padding: 20px;
                border: 1px solid #ccc;
                text-align: center;
            }
            h1 {
                text-align: center;
            }
        </style>
    </head>
    <body>
        <div class="contenedor" style="border: 2px solid red; color: blue;">
            <p>SEGUNDO PARCIAL TEM-742<br>
                Nombre: SANTOS YSRRAEL TARQUI FERNANDEZ<br>
                C.I.: 6011689 L.P.</p>
        </div>

        <h1 style="text-align: center; color: blue;">REGISTRO DIA DEL INTERNET</h1>

        <div style="text-align: center;">
            <p><a href="inicio?action=add">Nuevo</a></p>
        </div>

        <div>
            <table border="1" style="margin: auto;">
                <tr style="text-align: center;">
                    <th style="width: 40px;">Id</th>
                    <th style="width: 170px;">Nombres</th>
                    <th style="width: 170px;">Apellidos</th>
                    <th style="width: 100px;">Seminario</th>
                    <th style="width: 100px;">Confirmado</th>
                    <th style="width: 150px;">Fecha inscripción</th>
                    <th style="width: 50px;"></th>
                    <th style="width: 70px;"></th>
                </tr>

                <c:forEach var="item" items="${estudiantes}">
                    <tr>
                        <td style="text-align: center;">${item.id}</td>
                        <td>${item.nombre}</td>
                        <td>${item.apellido}</td>
                        <td>${item.seminario}</td>
                        <td style="text-align: center;"><input type="checkbox" name="seleccionar" value="${item.id}"></td>
                        <td style="text-align: center;">${item.fecha}</td>
                        <td style="text-align: center;"><a href="inicio?action=edit&id=${item.id}">Editar</a></td>
                        <td style="text-align: center;"><a href="inicio?action=delete&id=${item.id}" onclick="return(confirm('Esta seguro de eliminar'))">Eliminar</a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>
