<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Resultado Salario</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estilos.css">
</head>
<body>
    <div class="container">
        <h2>Resultado de la Consulta</h2>

        <c:choose>
            <c:when test="${salario != -1}">
                <div style="text-align: center; font-size: 1.2em; margin: 30px 0;">
                    <p>El salario del empleado con DNI <strong>${dni}</strong> es:</p>
                    <p style="font-size: 2em; color: #0056b3; font-weight: bold; margin: 10px 0;">${salario} €</p>
                </div>
            </c:when>
            <c:otherwise>
                <div class="error-msg" style="margin-top: 20px;">
                    No se encontró ningún salario para el DNI: <strong>${dni}</strong>.<br>
                    Comprueba que el empleado existe en la base de datos.
                </div>
            </c:otherwise>
        </c:choose>

        <div style="text-align: center; margin-top: 30px;">
            <a href="${pageContext.request.contextPath}/empleado?accion=buscarSalario" class="btn btn-volver" style="margin-right: 15px;">Nueva Consulta</a>
            <a href="${pageContext.request.contextPath}/index.jsp" class="btn btn-volver">Volver al Menú Principal</a>
        </div>
    </div>
</body>
</html>