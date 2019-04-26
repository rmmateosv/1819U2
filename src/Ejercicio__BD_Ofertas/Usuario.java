package Ejercicio__BD_Ofertas;

import java.util.ArrayList;

public class Usuario {

	private String nombre;
	private ArrayList<Oferta> ofertas=new ArrayList<>();
	private String tipo;
	
	//Constructor de la clase Usuario.
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
	
	//Hacemos un metodo mostrar de los usuarios.
	public void mostrar() {
		System.out.println("Datos de los usuarios: "+nombre+
				"\tTipo: "+tipo);
		//Si el usuario es de tipo establecimiento "E" se mostraran las ofertas.
		if(tipo.equals("E")) {
			System.out.println("Ofertas: ");
			for(Oferta o:ofertas) {
				o.mostrar();
			}
		}
	}
	
	
}
