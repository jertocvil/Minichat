package es.jertocvil.minichat;

import java.io.IOException;
import java.net.UnknownHostException;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import es.jertocvil.minichat.utils.InvalidNickException;

public class ChatWindowActivity extends Activity implements OnClickListener {
	
	private String server, nick, port;
	private ChatClient c;
	private Button b;
	private EditText txtMensajes, txtEnviar;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ventana);
        
        Bundle extras = getIntent().getExtras();
        
        server = extras.getString("server");
        nick = extras.getString("nick");
        port = extras.getString("port");
        
        
		try {
			if (nick.equals(""))
				throw new InvalidNickException("",
						InvalidNickException.NICK_EN_BLANCO);
			c = new ChatClient(server, Integer.parseInt(port), nick, this);

		} catch (UnknownHostException e) {
			Toast.makeText(getApplicationContext(),
					"El servidor introducido no es válido.", Toast.LENGTH_SHORT)
					.show();
			this.finish();
		} catch (IOException e) {
			Toast.makeText(getApplicationContext(),
					"No se ha podido conectar al servidor.", Toast.LENGTH_SHORT)
					.show();
			this.finish();
		} catch (NumberFormatException e) {
			Toast.makeText(getApplicationContext(),
					"Introduce correctamente el número de puerto.",
					Toast.LENGTH_SHORT).show();
			this.finish();
		} catch (InvalidNickException e) {
			Toast.makeText(getApplicationContext(),
					"El nick no puede estar en blanco.", Toast.LENGTH_SHORT)
					.show();
			this.finish();
		}
        
		txtEnviar = (EditText)super.findViewById(R.id.txtEnviar);
		
		txtMensajes = (EditText)super.findViewById(R.id.txtMensajes);
		txtMensajes.setBackgroundColor(Color.TRANSPARENT);
		txtMensajes.setTextColor(Color.WHITE);
		
		b = (Button)super.findViewById(R.id.btnEnviar);
		b.setOnClickListener(this);
		
		
    }
    
    public void muestraMensaje (String s){
    	super.runOnUiThread(new UIupdate(s, txtMensajes));
    }
    
	@Override
	public void onClick(View v) {
		
		c.enviaMensaje(txtEnviar.getText().toString());
		
	}
    

    
    private class UIupdate implements Runnable {

		private String msg;
		private EditText e;
		
		public UIupdate(String s, EditText e){
			msg = s;
			this.e = e;
		}
		
		public void run() {
			e.append("\n" + msg);
		}
		
	}




    
}
