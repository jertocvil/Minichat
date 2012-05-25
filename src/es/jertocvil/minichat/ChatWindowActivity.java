package es.jertocvil.minichat;

import java.io.IOException;
import java.net.UnknownHostException;

import es.jertocvil.minichat.utils.InvalidNickException;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class ChatWindowActivity extends Activity {
	
	private String server, nick, port;
	private ChatClient c;
	
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
			c = new ChatClient(server, Integer.parseInt(port), nick);

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
        
    }

    @Override
    public void onStop(){
    	super.onStop();
    }
    
    @Override
    public void onDestroy(){
    	
    }
    
    
    
}
