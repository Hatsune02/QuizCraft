<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link type="text/css" rel="stylesheet" href="css/style_user_page.css"/>
</head>
<body>
<div class="navbar">
    <div class="app-name">QuizCraft</div>
    <div class="user-info">
        <span class="username">USER</span>
        <div class="logout-dropdown">
            <button class="dropdown-btn">Cerrar sesión</button>
            <div class="dropdown-content">
                <a href="#">Cerrar sesión</a>
            </div>
        </div>
    </div>
</div>

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
        <form>
                <textarea placeholder='<?xson version="1.0" ?>
<!realizar_solicitud: "REQUERIMIENTO_USUARIO" >
    { "SOLICITUD":[{
        "PARAMETRO_1": "",
        "PARAMETRO_2": ""
    }
    ]}
<fin_solicitud_realizada!>' rows="15" cols="70" required></textarea><br><br>
            <input type="submit" value="Enviar Solicitud">
        </form>
    </div>

    <div id="response" class="tab-content">
        <h2>Respuestas del Servidor</h2>
        <textarea readonly rows="15" cols="70">Aquí aparecerán las respuestas del servidor...</textarea>
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
