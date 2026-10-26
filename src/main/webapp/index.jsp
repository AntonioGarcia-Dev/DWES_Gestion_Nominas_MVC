<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Gestión de Nóminas</title>
    <!-- Enlazamos la hoja de estilos -->
    <link rel="stylesheet" type="text/css" href="css/estilos.css">
</head>
<body>
    <div class="container">
        <h1>Sistema de Gestión de Nóminas</h1>
        <h2>Menú Principal</h2>

        <div class="menu">
            <a href="empleado?accion=listar" class="btn">1. Mostrar todos los empleados</a>
            <a href="empleado?accion=buscarSalario" class="btn">2. Mostrar salario de un empleado</a>
            <a href="empleado?accion=buscarModificar" class="btn">3. Modificar datos de un empleado</a>
        </div>
    </div>
</body>
</html>