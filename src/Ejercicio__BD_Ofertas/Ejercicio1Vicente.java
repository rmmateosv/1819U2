package Ejercicio__BD_Ofertas;

import java.util.Scanner;

public class Ejercicio1Vicente {

	public static Scanner leer=new Scanner(System.in);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Modelo bd=new Modelo();
		
		if(bd.getConexion()!=null) {
			
			//Primero comprobamos si existe el usuario pidiendo los datos.
			System.out.println("Usuario: ");
			String usu=leer.nextLine();
			System.out.println("Contraseña: ");
			String contras=leer.nextLine();
			Usuario u=bd.obtenerUsuario(usu,contras);
			if(u==null) {
				System.err.println("ERROR DE USUARIO");
			}else {
				
			}
		}else {
			System.err.println("Error al conectar con la base de datos.");
		}
		
		
	}

}
