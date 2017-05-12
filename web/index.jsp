<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Torneo tenis de mesa</title>
    <jsp:include page="_head.jsp" />
    

</head>

<body id="page-top">
    <jsp:include page="_mainNav.jsp" />
    <header>
        <div class="container">
            <div class="row">
                <div class="col-sm-7">
                    <div class="header-content">
                        <div class="header-content-inner">
                            <h1>Torneo tenis de mesa</h1>
                            <!--<a href="#download" class="btn btn-outline btn-xl page-scroll">Start Now for Free!</a>-->
                        </div>
                    </div>
                </div>
                <div class="col-sm-5">
                    <div class="device-container">
                        <div class="device-mockup iphone6_plus portrait white">
                            <div class="device">
                                <div class="screen">
                                    <!-- Demo image for screen mockup, you can put an image here, some HTML, an animation, video, or anything else! -->
                                    <img src="/TorneoTenisMesa/assets/pic/bg-cta.jpg" class="img-responsive" alt="">
                                </div>
                                <div class="button">
                                    <!-- You can hook the "home button" to some JavaScript events or just remove it -->
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </header>

    <section id="admin" class="cta bg-primary">
        <div class="cta-content">
            <div class="container">
                <h2>Admin</h2>
                <a href="iniciarSesionVista.jsp?tipo=admin" class="btn btn-outline btn-xl page-scroll">Iniciar sesion</a>
            </div>
        </div>
    </section>

    <section id="jugador" class="cta bg-primary">
        <div class="cta-content">
            <div class="container">
                <h2>Jugador</h2>
                <a href="iniciarSesionVista.jsp?tipo=jugador" class="btn btn-outline btn-xl page-scroll">Iniciar sesion</a>
            </div>
        </div>
    </section>

    <section class="cta bg-primary" id="arbitro">
        <div class="cta-content">
            <div class="container">
                <h2>Arbitro</h2>
                <a href="iniciarSesionVista.jsp?tipo=arbitro" class="btn btn-outline btn-xl page-scroll">Iniciar sesion</a>
            </div>
        </div>
    </section>

    <section id="apostador" class="cta bg-primary">
        <div class="cta-content">
            <div class="container">
                <h2>Apostador</h2>
                <a href="iniciarSesionVista.jsp?tipo=apostador" class="btn btn-outline btn-xl page-scroll">Iniciar sesion</a>
            </div>
        </div>

        <div class="col-md-8">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-6">
                        <div class="feature-item">
                            <h3></h3>
                            <p></p>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="feature-item">
                            <i class="icon-lock-open text-primary"></i>
                            <h3>Registrese</h3>
                            <a href="Apostador/CrearUsuario" class="btn btn-outline btn page-scroll">Crear nuevo usuario</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <footer>
        <div class="container">
            <p>&copy; 2016 Start Bootstrap. All Rights Reserved.</p>
            <ul class="list-inline">
                <li>
                    <a href="#">Privacy</a>
                </li>
                <li>
                    <a href="#">Terms</a>
                </li>
                <li>
                    <a href="#">FAQ</a>
                </li>
            </ul>
        </div>
    </footer>
    <jsp:include page="_footer.jsp"/>
</body>
</html>
