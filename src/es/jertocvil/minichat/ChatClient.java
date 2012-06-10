package es.jertocvil.minichat;

import java.io.IOException;
import java.net.UnknownHostException;

import android.widget.Toast;
import es.jertocvil.minichat.utils.Participante;

public class ChatClient extends Participante {
	
	private ChatWindowActivity a;

	protected ChatClient(String host, int puerto, String nick, ChatWindowActivity a)
			throws UnknownHostException, IOException
			{
		super(host, puerto, nick);
		this.a = a;
		
	}
	
	protected void muestraMensaje(String mensaje){
		
		
	//	Toast.makeText(a.getApplicationContext(), mensaje, Toast.LENGTH_SHORT);

		a.muestraMensaje(mensaje);
	
	
		
	}
	
	

}
