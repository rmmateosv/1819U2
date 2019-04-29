package Ejercicio__BD_Ofertas;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Ejercicio1Vicente {

	public static Scanner leer=new Scanner(System.in);
	public static Modelo bd=new Modelo();
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		
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
	/*DEPENDIENDO DE LA LETRA QUE TECLEE EL USUARIO, SE INTRODUCIRA EN UN MENU O EN OTRO.
	 * SIENDO PARA EL PRIMER MENU EL DE ADMINISTRADOR-
	 * EL SEGUNDO MENU EL DE ESTABLECIMIENTO-
	 * Y EL TERCER MENU EL DE LOS CLIENTES.*/		
				
				if(u.getTipo().equals("A")) {
					menuAdmin();
				}
				if(u.getTipo().equals("E")) {
					menuEstablecimiento(u);
				}
				if(u.getTipo().equals("C")) {
					menuCliente();
				}
				
			}
		}else {
			System.err.println("Error al conectar con la base de datos.");
		}
		
		
	}

	private static void menuCliente() {
		// EL CLIENTE PUDE VER TODAS LAS OFERTAS Y VER LAS OFERTAS DE UN CLIENTE.
		int opcion=0;
		do {
			
			System.out.println("0-Salir.");
			System.out.println("1-Ver Ofertas.");
			System.out.println("2-Ver Ofertas de un Establecimiento.");
			opcion=leer.nextInt();leer.nextLine();
			switch(opcion) {
			case 1:bd.mostrarOfertas(null);
				break;
			case 2:
				//Creamos un array list de establecimientos.
				ArrayList<String> establec=bd.obtenerEstablecConOfertas();
				for(int i=0;i<establec.size();i++) {
					System.out.println(i+".-"+establec.get(i));
				}
				//Pedimos el usuario que introduzca el nº de establecimiento que desea ver las ofertas.
				System.out.println("Nombre del establecimiento: ");
				int num=leer.nextInt();leer.nextLine();
				/*Mostramos las ofertasd del establecimiento que corresponde con el numero introducido*/
				bd.mostrarOfertas(establec.get(num));
				break;
			}
			
		}while(opcion!=0);
	}

	private static void menuEstablecimiento(Usuario u) {
		// SU MENU DA DE ALTA OFERTAS,, MODIFICAR OFERTAS, BORRAR OFERTAS, CONSULTAR OFERTAS.
		int opcion=0;
		SimpleDateFormat df=new SimpleDateFormat("yyyyMMdd");
		do {
			
			System.out.println("0-Salir.");
			System.out.println("1-Alta Ofertas.");
			System.out.println("2-Modificar Ofertas.");
			System.out.println("3-Borrar Ofertas.");
			System.out.println("4-Consultar Ofertas.");
			opcion=leer.nextInt();leer.nextLine();
			Oferta o;
			switch(opcion) {
			case 1:
				
				o=new Oferta();
				
				try {
					//Esta linea de codigo hace que lo que metamos por teclado lo convierte en una fecha de java util.
					System.out.println("Fecha Inicio: (yyyymmdd)");
					java.util.Date fecha=df.parse(leer.nextLine());
					o.setFechaI(new Date(fecha.getTime()));
					
					//HACEMOS LOS MISMO CON LA FECHA FIN.
					System.out.println("Fecha FIN: (yyyymmdd)");
					 fecha=df.parse(leer.nextLine());
					o.setFechaI(new Date(fecha.getTime()));
					
					o.setEstablec(u.getNombre());
					System.out.println("Descripcion de la oferta: ");
					o.setDescrip(leer.nextLine());
					
					//creamos la oferta.
					if(!bd.altaOferta(o)) {
						System.err.println("ERROR AL CREAR LA OFERTA");
					}
					
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					System.err.println("FORMATO DE FECHA INCORRECTO.");
				}
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			}
			
		}while(opcion!=0);
	}

	private static void menuAdmin() {
		// SU MENU PUEDE HACER ALTA DE USUARIOS.
		int opcion=0;
		do {
			
			System.out.println("0-Salir.");
			System.out.println("1-Dar de alta a un USUARIO.");
			opcion=leer.nextInt();leer.nextLine();
			switch(opcion) {
			case 1:
				//Pedimos los datos del usuario.
				Usuario u=new Usuario();
				System.out.println("Introduce el nombre del nuevo usuario: ");
				u.setNombre(leer.nextLine());
				System.out.println("Introduce la nueva clave: ");
				u.setClave(leer.nextLine());
				System.out.println("Introduce el tipo (A-E-C)");
				String tipo=leer.nextLine().toUpperCase();
				if((tipo.equals("A")) || (tipo.equals("E")) || (tipo.equals("C"))){
					u.setTipo(tipo);
					if(!bd.altaUsuario(u)) {
						System.err.println("ERROR AL CREAR EL USUARIO.");
					}
				}else {
					System.err.println("ERROR DE TIPO.");
				}
				break;
			}
			
		}while(opcion!=0);
		
	}

}
