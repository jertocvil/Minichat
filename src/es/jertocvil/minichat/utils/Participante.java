package es.jertocvil.minichat.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public abstract class Participante {

    private Socket elSocket;
    private String hostServidor;
    private String nick;
    private InputStream entrada;
    private OutputStream salida;
    private Escuchador miEscuchador;
    private int puerto;

    protected Participante(String host, int puerto, String nick) throws UnknownHostException, IOException{
        hostServidor = host;
        this.puerto = puerto;
        this.nick = nick;
        abrirConexion();
        iniciarSesion();
        miEscuchador = new Escuchador(entrada, this);

    }
    
    void acabar() {
        if (elSocket == null) {
    // Si elSocket es null, simplemente acabo.
    // Lo compruebo, por si llaman
    // a acabar() antes haber abierto la conexión
    //
            System.exit(0);
        }
        try {
// si el socket no es null cierro todo y paro el
// thread del escuchador
            miEscuchador.parar();
            entrada.close();
            salida.close();
            elSocket.close();
            elSocket = null;

            System.exit(0);
        } catch (Exception ex) {
        }
    } // ()

    public void enviaMensaje(String mensaje) {
        if (mensaje == null || mensaje.length() == 0) {
            return;
        } else if(mensaje.equals("SALIR")){
            acabar();
            return;
        }
        try {
            IO.escribeLinea(nick + "> " +mensaje, salida);
        } catch (IOException ex) {

            acabar();
        }
    }

    private void abrirConexion() throws UnknownHostException, IOException{
        elSocket = new Socket(hostServidor,puerto);
        entrada = elSocket.getInputStream();
        salida = elSocket.getOutputStream();
    }
    
    private void iniciarSesion() throws IOException{
        IO.escribeLinea(nick, salida);
        String linea = IO.leeLinea(entrada);
        if(!linea.equals("OK")){
            acabar();
        }
    }
    
    
    
    protected abstract void muestraMensaje(String mensaje);
    
}
