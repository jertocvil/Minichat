package es.jertocvil.minichat;

import java.io.IOException;
import java.io.Serializable;
import java.net.UnknownHostException;

import android.app.Activity;
import android.widget.EditText;
import es.jertocvil.minichat.utils.Participante;

@SuppressWarnings("serial")
public class ChatClient extends Participante {
	
	private EditText t;

	protected ChatClient(String host, int puerto, String nick)
			throws UnknownHostException, IOException
			{
		super(host, puerto, nick);

		
	}
	
	protected void muestraMensaje(String mensaje){
		
		
	//	Toast.makeText(a.getApplicationContext(), mensaje, Toast.LENGTH_SHORT);

		//a.runOnUiThread(new UIupdate(mensaje));
	
	
		
	}
	
	private class UIupdate implements Runnable {

		private String msg;
		
		public UIupdate(String s){
			msg = s;
		}
		
		public void run() {
			t.append("\n" + msg );
		}
		
	}

}
