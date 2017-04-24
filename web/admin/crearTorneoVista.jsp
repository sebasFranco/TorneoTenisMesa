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
                    <div class="col-md-10 col-md-offset-1">
                        <h3 class="text-center">Crear Torneo</h3>
                        <form class="form-horizontal" action="/TorneoTenisMesa/admin/CrearTorneoCtrl" method="post" name="addUser" id='crearTorneoForm'>
                            <div id="customer_fields" class="user_fields">
                            <div class="form-group">
                                <label for="nombre" class="col-sm-4 control-label">Nombre</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="nombre" name="nombre" placeholder="Nombre">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="estructura" class="col-sm-4 control-label">Estructura</label>
                                <div class="col-sm-8">
                                    <select name="estructura" id="estructura" class="form-control">
                                        <option value="">--</option>
                                        <option value="1">Arbol</option>
                                        <option value="2">Cuadros</option>
                                    </select>
                                    <input name="nombreEstructura" type="hidden" id="nombreEstructura" value=""/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="numeroJugadores" class="col-sm-4 control-label">Numero Jugadores</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="numeroJugadores" name="numeroJugadores" placeholder="Numero Jugadores">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="numeroMesas" class="col-sm-4 control-label">Numero Mesas</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="numeroMesas" name="numeroMesas" placeholder="Numero Mesas">
                                </div>
                            </div>
                                <div class="form-group text-right">
                                    <div class="col-sm-offset-4 col-sm-8">
                                        <button id="crearTorneo" type="button" class="btn btn-success btn-lg">Crear</button>
                                    </div>

                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </section>
        <jsp:include page="_footer.jsp"/>
        <script type="text/javascript">
            $("#estructura").change(function (){
                var nombreEstructura = $('#estructura option:selected').html();
                $('#nombreEstructura').val(nombreEstructura);
            });
        </script>
        <script type="text/javascript">
            var cantJ;
            $(document).ready(function (){
                $.ajax({
                    url: "http://localhost:8084/TorneoTenisMesa/NumeroJugadoresCtrl",
                    type: 'POST',
                    dataType: "json",
                    data: {},
                    success: function(data, textStatus){
                        cantJ=data;
                    },
                    error: function(data, textStatus, extra) {
                        var errorText = data.responseDetails;
                        if (errorText == undefined){
                            errorText = data.responseText;
                        }
                        alert(errorText);
                    }
                });
            });
            
            $("#crearTorneo").click(function () {
                var nombre = $("input[name='nombre']").val().trim();
                var estructura = $("select[name='estructura']").val();
                var numeroJugadores = $("input[name='numeroJugadores']").val().trim();
                var numeroMesas = $("input[name='numeroMesas']").val().trim()
                var error = false;
                   
                if(nombre==''||estructura=='--'||numeroJugadores==''||numeroMesas==''){
                    alert("Todos los campos deben estar llenos");
                    error = true;
                }
                             
                if(isNaN(numeroJugadores)){
                    alert("El número de jugadores no debe contener letras o caracteres especiales");
                    error = true
                }
                
                if(isNaN(numeroMesas)){
                    alert("El número de mesas no debe contener letras o caracteres especiales");
                    error = true
                }
                
                var numA = Math.pow(2,(Math.floor(((Math.log(numeroJugadores)))/(Math.log(2)))));
                var numC;
                var x;
                
                for(var i=numeroJugadores;i>0;i--){
                    x=i%4;
                    if(x==0){
                        numC=i;
                        break;
                    }
                }
                
                if(numeroJugadores<8){
                    alert("El numero minimo de jugadores para crear un torneo debe ser 8");
                    error = true
                }else if(numeroJugadores>cantJ){
                    alert("El numero maximo de jugadores para crear un torneo no debe ser mayor a: "+cantJ);
                    error = true
                }else if(estructura==1 && numeroJugadores>numA){
                    alert("Para la esturctura arbol el numero maximo de jugadores no puede ser mayor a: "+numA);
                    error = true
                }else if(estructura==2 && numeroJugadores>numC){
                    alert("Para la esturctura cuadros el numero maximo de jugadores no puede ser mayor a: "+numC);
                    error = true
                }
                
                if(error==false){
                    $('#crearTorneoForm').submit();
                }                
            });
        </script>
    </body>
</html>