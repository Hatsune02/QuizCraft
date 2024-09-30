<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="es">
<head>
    <link type="text/css" rel="stylesheet" href="css/style_user_page.css"/>
    <title>Solicitudes</title>
</head>
<body>
<%@ include file="navbar.jsp" %>

<!-- Contenedor principal -->
<div class="container">
    <!-- Tabs -->
    <div class="tabs">
        <button class="tab-button active" onclick="openTab('request')">Enviar Solicitud</button>
        <button class="tab-button" onclick="openTab('response')">Respuestas del Servidor</button>
    </div>

    <!-- Contenido de las Tabs -->
    <div id="request" class="tab-content active">
        <h2>Enviar Solicitud</h2>
        <form action="request" method="post">
            <div class="editor">
                <div class="line-numbers"></div>
                <textarea id="solicitude" name="solicitude" placeholder='<?xson version="1.0" ?>
<!realizar_solicitud: "REQUERIMIENTO_USUARIO" >
    { "SOLICITUD":[{
        "PARAMETRO_1": "",
        "PARAMETRO_2": ""
    }
    ]}
<fin_solicitud_realizada!>' rows="19" cols="110" wrap="off"  required><%= request.getAttribute("solicitude") != null ? request.getAttribute("solicitude") : "" %></textarea>
            </div>
            <div class="cursor-position">Línea 1, Columna 1</div>
            <br>
            <input type="submit" value="Enviar Solicitud">
        </form>
    </div>

    <div id="response" class="tab-content">
        <h2>Respuestas del Servidor</h2>
        <textarea id="serverResponse" readonly rows="19" cols="110">${serverResponse}</textarea>
    </div>
</div>
<script src="js/user_request_script.js"></script>
<script type="module" src=""></script>
</body>
</html>
