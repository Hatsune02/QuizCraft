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
        <div class="editor">
            <div class="line-numbers"></div>
            <textarea id="solicitude" name="importedContent" rows="26" cols="110">${importedContent}</textarea>
        </div>
        <br><br>
        <input type="submit" value="Guardar Trivia" required>
    </form>
</div>
<script>
    const textarea = document.getElementById("solicitude");
    const lineNumbers = document.querySelector(".line-numbers");
    const cursorPositionDisplay = document.querySelector(".cursor-position"); // Nuevo contenedor para la posición

    textarea.addEventListener("input", updateLineNumbers);
    textarea.addEventListener("scroll", syncScroll);
    textarea.addEventListener("keyup", updateCursorPosition); // Escuchar eventos de teclas para actualizar posición

    function updateLineNumbers() {
        const lines = textarea.value.split('\n').length; // Contar líneas
        lineNumbers.innerHTML = ''; // Limpiar números de línea
        for (let i = 1; i <= lines; i++) {
            lineNumbers.innerHTML += i + '<br>'; // Añade cada número de línea
        }
    }

    function syncScroll() {
        lineNumbers.scrollTop = textarea.scrollTop; // Sincroniza el scroll
    }

    function updateCursorPosition() {
        const cursorPosition = textarea.selectionStart; // Obtener la posición del cursor
        const textBeforeCursor = textarea.value.substring(0, cursorPosition); // Texto antes del cursor
        const lineNumber = textBeforeCursor.split('\n').length; // Calcular línea
        const columnNumber = cursorPosition - textBeforeCursor.lastIndexOf('\n'); // Calcular columna
        cursorPositionDisplay.textContent = `Línea ${lineNumber}, Columna ${columnNumber}`; // Actualizar el texto
    }

    // Inicializa números de línea y posición al cargar
    updateLineNumbers();
    updateCursorPosition();
</script>
</body>
</html>
