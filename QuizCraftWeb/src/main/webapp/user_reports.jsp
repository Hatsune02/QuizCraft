<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Reportes</title>
    <link type="text/css" rel="stylesheet" href="css/style_user_page.css"/>
    <link type="text/css" rel="stylesheet" href="css/style_reports.css"/>
</head>
<body>
<%@ include file="navbar.jsp" %>
<div class="container-reports">
    <!-- Área para mostrar las tablas de reportes -->
    <div class="table-area" id="tableArea">
        <h2>Reportes de Trivias</h2>
        <table id="reportTable">
            <thead>
            <tr>
                <th>Trivia</th>
                <th>Tema</th>
                <th>Preguntas</th>
                <th>Usuario</th>
                <th>Tiempo</th>
                <th>Punteo</th>
            </tr>
            </thead>
            <tbody>
            <!-- Aquí se llenarán los datos enviados desde el servlet -->
            <c:forEach var="report" items="${reports}">
                <tr>
                    <td>${report.nameTrivia}</td>
                    <td>${report.topic}</td>
                    <td>${report.questions}</td>
                    <td>${report.username}</td>
                    <td>${report.time}</td>
                    <td>${report.score}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <!-- Área tipo terminal para ingresar comandos -->
    <div class="terminal-area">
        <form method="POST" action="reports">
            <!-- Área de texto donde se mantiene el historial de comandos -->
            <textarea id="terminalLog" name="commandHistory" class="terminal-log" readonly>${commandHistory}</textarea>

            <!-- Campo oculto para enviar el historial completo de la terminal -->
            <input type="hidden" name="fullHistory" value="${commandHistory}" />

            <input type="text" name="command" class="terminal-input" placeholder="Escribe el comando aquí...">
        </form>
    </div>
</div>
<script>
    window.onload = function() {
        var terminalLog = document.getElementById("terminalLog");
        terminalLog.scrollTop = terminalLog.scrollHeight;  // Desplaza el scroll hacia abajo
    };
</script>
</body>
</html>
