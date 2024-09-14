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
    <h1 class="label_trivia">Mis Trivias</h1>

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

</body>
</html>
