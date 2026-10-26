<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Buscar Empleado a Modificar</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estilos.css">
</head>
<body>
    <div class="container">
        <h2>Modificar Datos de Empleado</h2>
        <form action="${pageContext.request.contextPath}/empleado" method="post">
            <input type="hidden" name="accion" value="mostrarModificar">
            <div class="form-group">
                <label for="dni">Introduce el DNI del empleado a modificar:</label>
                <input type="text" id="dni" name="dni" required placeholder="Ej: 32000031R">
            </div>
            <div style="text-align: center; margin-top: 20px;">
                <button type="submit" class="btn" style="display: inline-block; width: auto; padding: 10px 30px;">Buscar Empleado</button>
            </div>
        </form>
        <div style="text-align: center;">
            <a href="${pageContext.request.contextPath}/index.jsp" class="btn btn-volver">Volver al Menú Principal</a>
        </div>
    </div>
</body>
</html>