<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>QuizCraftWeb</title>
    <link href="css/style_login.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="navbar">
    <div class="app-name"><strong>QuizCraft</strong></div>
</div>

<!-- Contenedor principal -->
<div class="container">
    <!-- Área de texto -->
    <div class="form-container">
        <h2>Iniciar sesión</h2>
        <form action="login" method="post">
            <textarea placeholder='<?xson version="1.0" ?>
<!realizar_solicitud: "LOGIN_USUARIO" >
    { "DATOS_USUARIO":[{
        "USUARIO": "",
        "PASSWORD": ""
    }
    ]}
<fin_solicitud_realizada!>' rows="10" cols="50" id="request" name="request" required></textarea>
            <br><br>
            <input type="submit" value="Enviar">
        </form>
    </div>
</div>
</body>
</html>