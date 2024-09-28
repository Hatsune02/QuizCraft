<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Trivias</title>
    <link type="text/css" rel="stylesheet" href="css/style_user_page.css"/>
    <link type="text/css" rel="stylesheet" href="css/style_user_trivias.css"/>
</head>
<body>
<%@ include file="navbar.jsp" %>

<div class="container-trivias">
    <div class="button-container">
        <h1 class="label_trivia">Mis Trivias</h1>
        <form action="trivias" method="post" enctype="multipart/form-data" style="margin: 0;">
            <input type="hidden" name="action" value="import">
            <input type="file" name="triviaFile" accept=".xtriv" required style="display: none;" id="fileInput">
            <button type="button" class="btn" onclick="document.getElementById('fileInput').click();">Importar</button>
        </form>
    </div>

    <!-- Itera sobre la lista de trivias y crea una tarjeta para cada una -->
    <c:forEach var="trivia" items="${triviaList}">
        <div class="card">
            <div class="card-header">
                <h3>${trivia.getIdTrivia()}</h3>
            </div>
            <div class="card-body">
                <p>Nombre: ${trivia.getName()}</p>
                <p>Tiempo: ${trivia.getQuestionTime()}</p>
                <p>Tema: ${trivia.getTopic()}</p>
                <p>Creador: ${trivia.getCreateUser()}</p>
                <p>Fecha Creacion: ${trivia.getCreateDateString()}</p>
            </div>
            <div class="card-footer">
                <form action="trivias" method="get" style=" margin: 0px;">
                    <input type="hidden" name="id_export" value="${trivia.getIdTrivia()}">
                    <button type="submit">Exportar</button>
                </form>
                <form action="trivias" method="get" style=" margin: 0px;">
                    <input type="hidden" name="id" value="${trivia.getIdTrivia()}">
                    <button type="submit">Ver más</button>
                </form>
            </div>

        </div>
    </c:forEach>
</div>
<script>
    // Evento que se dispara cuando se selecciona un archivo
    document.getElementById('fileInput').addEventListener('change', function() {
        if (this.files.length > 0) {
            this.form.submit(); // Envía el formulario
        }
    });
</script>
</body>
</html>
