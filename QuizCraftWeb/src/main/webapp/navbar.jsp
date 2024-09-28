<div class="navbar">
    <div class="navbar-left">
        <span class="app-name"><strong>QuizCraft</strong></span>
        <a href="trivias" class="nav-link"><strong>Trivias</strong></a>
        <a href="request" class="nav-link"><strong>Solicitudes</strong></a>
        <a href="reports" class="nav-link"><strong>Reportes</strong></a>
    </div>
    <div class="navbar-right">
        <div class="logout-dropdown">
            <button class="dropdown-btn">${user.username} &#9662;</button>
            <div class="dropdown-content">
                <a href="" class="data">${user.name}</a>
                <a href="" class="data">${user.institution}</a>
                <a href="logout" class="data">Cerrar sesion</a>
            </div>
        </div>
    </div>
</div>
