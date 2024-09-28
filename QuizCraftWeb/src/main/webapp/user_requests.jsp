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
            <textarea name="solicitude" placeholder='<?xson version="1.0" ?>
<!realizar_solicitud: "REQUERIMIENTO_USUARIO" >
    { "SOLICITUD":[{
        "PARAMETRO_1": "",
        "PARAMETRO_2": ""
    }
    ]}
<fin_solicitud_realizada!>' rows="19" cols="110"  required></textarea><br><br>
            <input type="submit" value="Enviar Solicitud">
        </form>
    </div>

    <div id="response" class="tab-content">
        <h2>Respuestas del Servidor</h2>
        <textarea id="serverResponse" readonly rows="19" cols="110">${serverResponse}</textarea>
    </div>
</div>

<script>
    function openTab(tabName) {
        var i, tabcontent, tabbuttons;
        tabcontent = document.getElementsByClassName("tab-content");
        for (i = 0; i < tabcontent.length; i++) {
            tabcontent[i].style.display = "none";
        }
        tabbuttons = document.getElementsByClassName("tab-button");
        for (i = 0; i < tabbuttons.length; i++) {
            tabbuttons[i].className = tabbuttons[i].className.replace(" active", "");
        }
        document.getElementById(tabName).style.display = "block";
        event.currentTarget.className += " active";
    }
    document.getElementsByClassName("tab-button")[0].click(); // Open the first tab by default
</script>
<script type="module" src=""></script>
</body>
</html>
