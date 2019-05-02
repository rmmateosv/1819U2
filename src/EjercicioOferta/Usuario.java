package EjercicioOferta;
import java.util.ArrayList;

public class Usuario {
	private String nombre, clave;
	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	private String tipo;
	private ArrayList<Oferta> ofertas=new ArrayList<>();
	
	
	public Usuario() {
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public ArrayList<Oferta> getOfertas() {
		return ofertas;
	}

	public void setOfertas(ArrayList<Oferta> ofertas) {
		this.ofertas = ofertas;
	}
	
	public void mostrar() {
		System.out.println("Datos de usuario:"+nombre + 
				"\tTipo:"+tipo);
		//Si el usuario es establecimiento, mostramos sus ofertas
		if(tipo.equals("E")) {
			System.out.println("Ofertas:");
			for(Oferta o:ofertas) {
				o.mostrar();
			}
			
		}
	}
	
}
