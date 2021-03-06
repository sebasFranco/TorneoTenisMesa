<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="_head.jsp" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Crear nueva apuesta</title>
    </head>
    <body id="page-top" class="index background">
        <jsp:include page="/apostador/menuApostador.jsp" />
        <section >
            <div class="container">
                <div class="row">
                    <div class="col-md-10 col-md-offset-1">
                        <c:if test="${idApuesta>0}">
                            <div class="alert alert-success" id="msgCreacion">
                                Apuesta Creada
                            </div>
                        </c:if>
                        <form class="form-horizontal" action="" method="post" name="addUser" id='crearApuestaForm'>

                            <div class="form-group">
                                <label for="apuesta" class="col-sm-4 control-label">Valor de la apuesta</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="apuestaVal" name="apuesta" placeholder="Valor apuesta">
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <label for="ganador" class="col-sm-4 control-label">Ganador</label>
                                <div class="col-sm-8">
                                    <input type="radio" class="form-horizontal" name="ganador" value="${usuario1.idUsuario}" checked> ${usuario1.cedula} ${usuario1.nombre} ${usuario1.apellido}<br>
                                    <input type="radio" class="form-horizontal" name="ganador" value="${usuario2.idUsuario}"> ${usuario2.cedula} ${usuario2.nombre} ${usuario2.apellido}<br>
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <label for="puntaje" class="col-sm-4 control-label">Puntaje</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="apuestaPun" name="puntaje" placeholder="Puntaje" alt="Ejemplo: 0-1">
                                </div>
                            </div>

                            <div class="form-group text-right">
                                <div class="col-sm-offset-4 col-sm-8">
                                    <button id="crearApuesta" type="button" class="btn btn-success btn-lg">Crear</button>
                                </div>
                                
                            </div>

                        </form>
                    </div>
                </div>
            </div>
        </section>
        <jsp:include page="_footer.jsp"/>
        <script type="text/javascript" src="/TorneoTenisMesa/assets/js/apCrearApuesta.js"></script>
    </body>
</html>