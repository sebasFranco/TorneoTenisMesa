$('#crearApuesta').click(function (){
    var valApuesta = $("input[name='apuesta']").val().trim();
    var tieneError = false;

    if(valApuesta==''){
        alert("Todos los campos deben estar llenos");
        tieneError = true
    }
    if(isNaN(valApuesta)){
        alert("El valor de la apuesta no puede tener caracteres especiales");
        tieneError = true
    }
    
    if(!tieneError) $('#crearApuestaForm').submit()
});
    
$("#msgCreacion").fadeTo(2000, 500).slideUp(500, function(){
    $("#msgCreacion").alert('close');
});