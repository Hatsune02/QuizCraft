/*  JS TAB*/
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

/*  JS TEXT AREA*/

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