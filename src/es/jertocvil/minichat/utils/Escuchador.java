package es.jertocvil.minichat.utils;

import java.io.IOException;
import java.io.InputStream;


public class Escuchador implements Runnable{
    
    private InputStream entrada;
    private Participante part;
    private boolean seguir;
    
    public Escuchador(InputStream ie, Participante p){
        entrada = ie;
        part = p;
        seguir = true;
        new Thread(this).start();
    }

    private String recibeMensaje() throws IOException {
        return IO.leeLinea(this.entrada);
} // ()

    public void parar(){
        seguir = false;
    }
    
    public void run() {
        try{
        	String msg;
        while(seguir){
        	msg = recibeMensaje();
        	
        	if(msg != "" && msg != null)
            part.muestraMensaje(msg);
        }
        } catch(Exception e){
            part.acabar();
        }
    }
    
}
