<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Listado de Empleados</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estilos.css">
</head>
<body>
    <div class="container">
        <h2>Plantilla de Empleados</h2>
        <table>
            <thead>
                <tr>
                    <th>DNI</th>
                    <th>Nombre</th>
                    <th>Sexo</th>
                    <th>Categoría</th>
                    <th>Años Trabajados</th>
                </tr>
            </thead>
            <tbody>
                <!-- Recorremos la lista de empleados enviada por el Servlet -->
                <c:forEach var="emp" items="${empleados}">
                    <tr>
                        <td>${emp.dni}</td>
                        <td>${emp.nombre}</td>
                        <td>${emp.sexo}</td>
                        <td>${emp.categoria}</td>
                        <td>${emp.anyos}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <div style="text-align: center;">
            <a href="${pageContext.request.contextPath}/index.jsp" class="btn btn-volver">Volver al Menú Principal</a>
        </div>
    </div>
</body>
</html>