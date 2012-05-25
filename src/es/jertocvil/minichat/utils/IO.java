/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.jertocvil.minichat.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author Jordi
 */
public class IO {

    //.........................................................................
    //.........................................................................
    private static final int LF = 10; // line feed
    private static final int CR = 13; // carriage return
    private static final byte[] SALTO_DE_LINEA = {CR, LF}; // array con 2 casillas: tiene CR y LF

    //.........................................................................
    //.........................................................................
    public static int copia(InputStream entrada, OutputStream salida) throws IOException {
        int leido = entrada.read();
        int cont = 0;
        while (leido != -1) {
            salida.write(leido);
            cont++;
            leido = entrada.read();
        }
        return cont;
    }

    //.........................................................................
    //.........................................................................
    public static String leeLinea(InputStream entrada) throws IOException {

        ByteArrayOutputStream acumulador = new ByteArrayOutputStream();
        int unByte = 0;
        int byteAnterior = 0;

        // leo con read, guardo en unByte y comparo con -1
        while ((unByte = entrada.read()) != -1) {

            if (unByte == CR) {
                // si veo un CR, no lo pongo en el acumulador
                // pero lo guardo
                byteAnterior = CR; // == unByte
            } else if (byteAnterior == CR && unByte == LF) {
                // si había visto un CR y ahora veo un LF
                // => he detectado un salto de lína
                // => termino el bucle
                break;

            } else if (byteAnterior == CR && unByte != LF) {
                // si había visto un CR pero este no es LF
                // no es un salto de línea y
                // como no puse el CR, pongo ahora los dos
                acumulador.write(byteAnterior); // que es CR
                acumulador.write(unByte); // que no es LF
            } else {
                // ninguno de los anteriores casos
                // pongo el byte leído
                acumulador.write(unByte);
                byteAnterior = unByte;
            }

        } // while

        // devuelvo los bytes acumulados, pasados a texto
        return acumulador.toString();
    } // ()

    //.........................................................................
    //.........................................................................
    public static void escribeLinea(String linea, OutputStream salida) throws IOException {
        salida.write(linea.getBytes());
        salida.write(SALTO_DE_LINEA);
    } // ()
}
