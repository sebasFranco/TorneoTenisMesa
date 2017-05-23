$('#crearApuesta').click(function (){
    var valApuesta = $("input[name='apuesta']").val().trim();
    var valGanador = $("input[name='ganador']").val().trim();
    var valPuntaje = $("input[name='puntaje']").val().trim();
    var tieneError = false;
    var strError = '';
    
    if(valApuesta==='' || valGanador==='' || valPuntaje===''){
        strError = 'Todos los campos deben estar llenos';
        tieneError = true;
    }
    if(isNaN(valApuesta)){
        strError += '\nEl valor de la apuesta no puede tener caracteres especiales';
        tieneError = true;
    }
    if(valPuntaje.search(/[0-9]{1,2}[-]{1}[0-9]{1,2}/g)!==0){
        strError += '\nEl puntaje debe ser del tipo: 0-21 o 21-0';
        tieneError = true;
    }
    
    if (tieneError){
        alert(strError);
    }else{
        $('#crearApuestaForm').submit();
    }
    
});
    
$("#msgCreacion").fadeTo(2000, 500).slideUp(500, function(){
    $("#msgCreacion").alert('close');
});