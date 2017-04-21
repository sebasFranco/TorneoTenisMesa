/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function() {
    $('.user_types').click(function (){
        var selectedUser = $(this).val();
        $('.user_fields').addClass('hide');
        var tipoJugador = null;
        if(selectedUser == 'jugador'){
            tipoJugador = 1;
            $('#customer_fields').removeClass('hide'); 
        }else if(selectedUser == 'arbitro'){
            tipoJugador = 2;
            $('#customer_fields').removeClass('hide');
        }else if(selectedUser == 'admin'){
            tipoJugador = 3;
            $('#customer_fields').removeClass('hide');
        }else if(selectedUser == 'apostador'){
            tipoJugador = 4;
            $('#customer_fields').removeClass('hide');
        }
        $('#login_fields').removeClass('hide');
        $('#tipoJugador').val(tipoJugador);
    });    
});

$('#crearUsuario').click(function (){
        var cc = $("input[name='cc']").val().trim();
        var nombre = $("input[name='nombre']").val().trim();
        var apellido = $("input[name='apellido']").val().trim();
        var telefono = $("input[name='telefono']").val().trim();
        var nombreUsuario = $("input[name='nombreUsuario']").val().trim();
        var clave = $("input[name='clave']").val();
        var tieneError = false;
        
        if(cc==''||nombre==''||apellido==''||telefono==''||nombreUsuario==''||clave==''){
            alert("Todos los campos deben estar llenos");
            tieneError = true
        }
        if(isNaN(cc)){
            alert("La cedula de ciudadania no debe contener letras o caracteres especiales");
            tieneError = true
        }
        if(isNaN(telefono)){
            alert("El telefono no debe contener letras o caracteres especiales");
            tieneError = true
        }
        if(nombre.search(/[0-9]/g)!=-1&&(nombre.search(/[0-9]/g)!=nombre.length)){
            alert("El nombre no debe contener numeros");
            tieneError = true
        }
        if(apellido.search(/[0-9]/g)!=-1&&(apellido.search(/[0-9]/g)!=apellido.length)){
            alert("El apellido no debe contener numeros");
            tieneError = true
        }
        if(!tieneError) $('#crearUsuarioForm').submit()
    });
    
   $("#msgCreacion").fadeTo(2000, 500).slideUp(500, function(){
    $("#msgCreacion").alert('close');
});