/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
function handleLoginRequest(xhr, status, args) {  
    if(args.validationFailed || !args.loggedIn) {  
        jQuery('#dialog').effect("shake", {
            times:3
        }, 100);  
    } else {  
        dlg.hide();  
    }  
}  
function handleMessages(xhr, status, args) {  
    if(args.isValid) {
        errorWidget.show();
    } else {
        errorWidget.hide();
    }
}   

function open_win(archivo,titulo){
    window.open(archivo, "_blank", 'height=800,width=1024,top=10, dependent=yes, menubar=no, toolbar=no,location=no');
}

