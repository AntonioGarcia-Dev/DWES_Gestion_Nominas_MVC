<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Actualizar Empleado</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estilos.css">
</head>
<body>
    <div class="container">
        <h2>Actualizar Datos de: ${empleado.nombre}</h2>
        <form action="${pageContext.request.contextPath}/empleado" method="post">
            <input type="hidden" name="accion" value="actualizar">
            <!-- Mandamos el DNI oculto porque es la clave primaria y la necesitamos para el UPDATE -->
            <input type="hidden" name="dni" value="${empleado.dni}">

            <div class="form-group">
                <label>DNI (No modificable):</label>
                <!-- Usamos 'disabled' para que el usuario no pueda teclear aquí -->
                <input type="text" value="${empleado.dni}" disabled>
            </div>
            <div class="form-group">
                <label>Nombre (No modificable):</label>
                <input type="text" value="${empleado.nombre}" disabled>
            </div>
            <div class="form-group">
                <label>Sexo (No modificable):</label>
                <input type="text" value="${empleado.sexo}" disabled>
            </div>

            <div class="form-group">
                <label for="categoria">Categoría (1-10):</label>
                <input type="number" id="categoria" name="categoria" min="1" max="10" value="${empleado.categoria}" required>
            </div>
            <div class="form-group">
                <label for="anyos">Años Trabajados:</label>
                <input type="number" id="anyos" name="anyos" min="0" value="${empleado.anyos}" required>
            </div>

            <div style="text-align: center; margin-top: 20px;">
                <button type="submit" class="btn" style="display: inline-block; width: auto; padding: 10px 30px; background-color: #28a745;">Guardar Cambios</button>
            </div>
        </form>
        <div style="text-align: center;">
            <a href="${pageContext.request.contextPath}/empleado?accion=buscarModificar" class="btn btn-volver" style="margin-right: 15px;">Buscar Otro</a>
            <a href="${pageContext.request.contextPath}/index.jsp" class="btn btn-volver">Volver al Menú Principal</a>
        </div>
    </div>
</body>
</html>