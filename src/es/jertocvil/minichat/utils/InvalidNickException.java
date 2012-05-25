package es.jertocvil.minichat.utils;

public class InvalidNickException extends Exception {
	
	public static final int NICK_REPETIDO = 0;
	public static final int NICK_EN_BLANCO = 1;
	
	
	public InvalidNickException(String nick, int cause){
		super("El nick introducido \""+nick+"\" " + getCause(cause));
	}
	
	private static String getCause(int cause){
		String s = "";
		switch(cause){
		case NICK_REPETIDO:
			s+= "está repetido";
			break;
		case NICK_EN_BLANCO:
			s+= "está en blanco";
			break;
		default:
			s+= "es inválido";	
		}
		return s;
	}
	
}
