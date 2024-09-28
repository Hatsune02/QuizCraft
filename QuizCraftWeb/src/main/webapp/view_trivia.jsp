<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${trivia.idTrivia}</title>
    <link type="text/css" rel="stylesheet" href="css/style_user_page.css"/>
    <link type="text/css" rel="stylesheet" href="css/style_user_trivias.css">
</head>
<body>
<%@ include file="navbar.jsp" %>

<div class="container-trivia-details">
    <h1>Detalles de la Trivia</h1>

    <h2>Nombre: ${trivia.name}</h2>
    <p>ID: ${trivia.idTrivia}</p>
    <p>Tema: ${trivia.topic}</p>
    <p>Tiempo por pregunta: ${trivia.questionTime}</p>
    <p>Creador: ${trivia.createUser}</p>
    <p>Fecha de creación: ${trivia.getCreateDateString()}</p>
    <p>Cantidad de componentes: ${trivia.components.size()}</p>

    <h3>Componentes</h3>
    <ul>
        <c:forEach var="component" items="${trivia.components}">
            <li>
                <strong>ID: ${component.id}</strong>
                <ul>
                    <li>Trivia: ${component.trivia}</li>
                    <li>Clase: ${component.dbClass()}</li>
                    <li>Índice: ${component.index}</li>
                    <li>Texto Visible: ${component.visibleText}</li>

                    <!-- Mostrar las opciones, si existen -->
                    <c:if test="${component.clase == 3 or component.clase == 4 or component.clase == 6}">
                        <li>Opciones:
                            <ul>
                                <c:forEach var="option" items="${component.options}">
                                    <li>${option}</li>
                                </c:forEach>
                            </ul>
                        </li>
                    </c:if>
                    <c:if test="${component.line != 0 and component.columns != 0}">
                        <li>Linea: ${component.line}</li>
                        <li>Columnas: ${component.columns}</li>
                    </c:if>

                    <li>Respuestas:
                        <ul>
                            <c:forEach var="ans" items="${component.answer}">
                                <li>${ans}</li>
                            </c:forEach>
                        </ul>
                    </li>
                </ul>
            </li>
            <br>
        </c:forEach>
    </ul>

    <a href="trivias">Volver a la lista</a>
</div>

</body>
</html>
