<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Trivia Importada</title>
    <link type="text/css" rel="stylesheet" href="css/style_user_page.css"/>
    <link type="text/css" rel="stylesheet" href="css/style_user_trivias.css"/>
</head>
<body>
<%@ include file="navbar.jsp" %>

<div class="container">
    <h1>Contenido Importado</h1>
    <form action="trivias" method="post">
        <textarea name="importedContent" rows="23" cols="110">${importedContent}</textarea><br><br>
        <input type="submit" value="Guardar Trivia" required>
    </form>
</div>

</body>
</html>
