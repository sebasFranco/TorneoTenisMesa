<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="_head.jsp" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Torneo de tenis de mesa-Admin</title>
    </head>
    <body id="page-top" class="index background">
        <jsp:include page="menuAdmin.jsp" />
        <section >
            <div class="container">
                <div class="row">
                    <h2 class="text-center">Definir Jugadores</h2>
                    <form class="form-horizontal" action="/TorneoTenisMesa/admin/DefinirUsuariosTorneoCtrl" method="post" name="crearTorneo" id="crearTorneoForm">
                        <div class="col-md-5">
                                <div class="form-group">
                                    <select name="jugadores" class="form-control" id="jugadores">
                                        <c:forEach items="${jugadores}" var="jugador">
                                            <option value="${jugador.idUsuario}">${jugador.nombre}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            <div class="form-group">
                                <button type="button" id="agregarJugador" class="btn btn-success">Agregar Jugador</button>
                                <button type="button" id="removerJugador" class="btn btn-success">Remover Jugador</button>
                            </div>
                            <div class="form-group">
                                <select name="jugadoresSeleccionados" class="form-control" multiple id="jugadoresSeleccionados">
                                    </select>
                            </div>
                            <div class="form-group">
                                <button type="button" id="confirmarJugadores" class="btn btn-success">Confirmar Jugadores</button>
                            </div>

                        </div>
                        <div class="col-md-5 col-md-offset-2 ">
                            <div class="form-group">
                                    <select name="arbitros" class="form-control"  id="arbitros">
                                        <c:forEach items="${arbitros}" var="arbitro">
                                            <option value="${arbitro.idUsuario}">${arbitro.nombre}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            <div class="form-group">
                                <button type="button" id="agregarArbitro" class="btn btn-success">Agregar Arbitro</button>
                                <button type="button" id="removerArbitro" class="btn btn-success">Remover Arbitro</button>
                            </div>
                            <div class="form-group">
                                <select name="arbitrosSeleccionados" class="form-control" id="arbitrosSeleccionados">
                                </select>
                            </div>
                            <div class="form-group">
                                <button type="button" id="confirmarJugadores" class="btn btn-success">Confirmar Jugadores</button>
                            </div>
                        </div>
                        <button type="button" id="submit">Submit</button>
                    </form>
                </div>
            </div>
        </section>
        <jsp:include page="_footer.jsp"/>
        <script type="text/javascript">
            var cantidadJugadores = ${cantidadJugadores};
            var cantidadMesas = ${cantidadMesas};
            $('#submit').click(function (){
                $('#jugadoresSeleccionados option').each(function (i,e){
                    var input = '<input name="jugadoresSelec[]" value="' + $(e).val() + '"/>';
                    $('#crearTorneoForm').append(input);
                });
            });
            $("#crearTorneo").click(function () {
                $('#crearTorneoForm').submit();
            });
            
             $('#agregarJugador').click(function(e) {
            var selectedOpts = $('#jugadores option:selected');
            if (selectedOpts.length == 0) {
                alert("Nada para mover");
            }

            $('#jugadoresSeleccionados').append($(selectedOpts).clone());
            $(selectedOpts).remove();
        });
        $('#removerJugador').click(function(e) {
            var selectedOpts = $('#jugadoresSeleccionados option:selected');
            if (selectedOpts.length == 0) {
                alert("Nada para mover");
            }

            $('#jugadores').append($(selectedOpts).clone());
            $(selectedOpts).remove();
        });
        $('#agregarArbitro').click(function(e) {
            var selectedOpts = $('#arbitros option:selected');
            if (selectedOpts.length == 0) {
                alert("Nada para mover");
            }

            $('#arbitrosSeleccionados').append($(selectedOpts).clone());
            $(selectedOpts).remove();
        });
        $('#removerArbitro').click(function(e) {
            var selectedOpts = $('#arbitrosSeleccionados option:selected');
            if (selectedOpts.length == 0) {
                alert("Nada para mover");
            }

            $('#arbitros').append($(selectedOpts).clone());
            $(selectedOpts).remove();
        });
        </script>
    </body>
</html>