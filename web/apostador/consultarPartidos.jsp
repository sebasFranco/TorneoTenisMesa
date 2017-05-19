<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="_head.jsp" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Torneo de tenis de mesa-Admin</title>          
        <style type="text/css">
            .square {
            width: 20px;
            height: 10px;
            border: 1px solid black;
         }
         .square-red{
            background-color: red;
         }
         .square-green{
            background-color: green;
         }
        </style>
    </head>
    <body id="page-top" class="index background">
        <jsp:include page="/apostador/menuApostador.jsp" />
        <section >
            <div class="container">
                <div class="row">
                    <div class="col-md-10 col-md-offset-1">
                        
                        <c:if test="${errorLogin!=null}">
                            <div class="alert alert-danger"><c:out value="${errorLogin}" /></div>
                        </c:if>
                            <div class="row">
                                <div class="col-md-4">
                                    <h3 class="">Partidos</h3>
                                </div>
                            </div>
                        
                        <c:choose>
                            <c:when test="${partidos==null}">
                                No hay partidos
                            </c:when>
                            <c:otherwise>
                                <table class="table table-condensed">
                                    <thead>
                                        <tr>
                                            <th>Id Partido</th>
                                            <th>Fecha</th>
                                            <th>Jugador 1</th>
                                            <th>Jugador 2</th>
                                            <th>Juez</th>
                                            <th>&nbsp;</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${partidos}" var="partido">
                                        <tr>
                                            <td>${partido.idPartido}</td>
                                            <td>${partido.fechaHora}</td>
                                            <td>${partido.idJugador1}</td>
                                            <td>${partido.idJugador2}</td>
                                            <td>${partido.idArbitro}</td>
                                            <td>
                                                <a class="btn btn-info btn-xs" href="/TorneoTenisMesa/Apostador/CrearApuesta?idPartido=${partido.idPartido}">Apostar</a>
                                            </td>
                                        </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </section>
        <jsp:include page="_footer.jsp"/>
    </body>
</html>