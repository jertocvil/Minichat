package es.jertocvil.minichat;

import java.io.IOException;
import java.net.UnknownHostException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import es.jertocvil.minichat.utils.InvalidNickException;

public class LoginActivity extends Activity {
	/** Called when the activity is first created. */

	ChatClient c;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

	}

	public void conectar(View view) {
		EditText server = (EditText) findViewById(R.id.serverTxt);
		EditText port = (EditText) findViewById(R.id.portTxt);
		EditText nick = (EditText) findViewById(R.id.nickTxt);

		Intent startChat = new Intent(this, ChatWindowActivity.class);
		startChat.putExtra("server", server.getText().toString());
		startChat.putExtra("port", port.getText().toString());
		startChat.putExtra("nick", nick.getText().toString());
		this.startActivity(startChat);
		
		
	}

}