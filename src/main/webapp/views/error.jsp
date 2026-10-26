<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Error</title>
    <!-- Usamos pageContext para asegurar que la ruta del CSS no se rompa -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estilos.css">
</head>
<body>
    <div class="container">
        <h1>Ha ocurrido un problema</h1>
        <div class="error-msg">
            ${error != null ? error : "Error inesperado al procesar la solicitud."}
        </div>
        <div style="text-align: center;">
            <a href="${pageContext.request.contextPath}/index.jsp" class="btn btn-volver">Volver al Menú Principal</a>
        </div>
    </div>
</body>
</html>