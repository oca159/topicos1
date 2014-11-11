package Model;

public class Ruta {

	private int id;
	private int linea;
	private String nombre;
	private String latitud;
	private String longitud;
	private String afluencia;
	
	public String getLongitud() {
		return longitud;
	}
	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}
	
	public String getAfluencia() {
		return afluencia;
	}
	public void setAfluencia(String afluencia) {
		this.afluencia = afluencia;
	}
	public String getLatitud() {
		return latitud;
	}
	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	private int getLinea() {
		return linea;
	}
	private void setLinea(int linea) {
		this.linea = linea;
	}
	private int getId() {
		return id;
	}
	private void setId(int id) {
		this.id = id;
	}	
	
}
