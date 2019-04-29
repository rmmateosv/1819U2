import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Ejercicio1 {
	public static Scanner t = new Scanner(System.in);
	public static Modelo bd = new Modelo();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(bd.getConexion()!=null) {
			//Pedimos usuario y contraseña
			System.out.println("Usuario:");
			String usu = t.nextLine();
			System.out.println("Contraseña:");
			String contras = t.nextLine();
			Usuario u = bd.obtenerUsuario(usu,contras);
			if(u==null) {
				System.out.println("Usuario erróneo");
			}
			else {
				if (u.getTipo().equals("A")) {
					menuAdmin();
				}
				if (u.getTipo().equals("E")) {
				
					menuEstablec(u);
				}
				if (u.getTipo().equals("C")) {
					
					menuCliente();
				}
			}
		}
		else {
			System.out.println("Error al establcer la conexión con la BD");
		}
	}

	private static void menuCliente() {
		// TODO Auto-generated method stub
		int opcion = 0;
		do {
			System.out.println("0-Salir");
			System.out.println("1-Ver todas las ofertas");
			System.out.println("2-Ver ofertas de un cliente");
			opcion=t.nextInt();t.nextLine();
			switch(opcion) {
			case 1:
				bd.mostrarOfertas(null);
				break;
			case 2:
				//Obtenemos los establecimientos que tienen ofertas en vigor
				ArrayList<String> establec = bd.obtenerEtablecConOfertas();
				//Mostramos los establecimientos
				for(int i=0;i<establec.size();i++) {
					System.out.println(i+".-"+establec.get(i));
				}
				//Pedimos al usuario que introduzca el nº de establecimiento
				//Del que desea ver las ofertas
				System.out.println("Número de establecimiento");
				int num = t.nextInt();t.nextLine();
				//Mostramos las ofertas del establecimento que corresponde con
				//el número introducido por el usuario 
				bd.mostrarOfertas(establec.get(num));
				break;
			}
			
		}while(opcion!=0);
	}

	private static void menuEstablec(Usuario u) {
		// TODO Auto-generated method stub
		int opcion = 0;
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		do {
			System.out.println("0-Salir");
			System.out.println("1-Alta Oferta");
			System.out.println("2-Modificar Oferta");
			System.out.println("3-Borrar Oferta");
			System.out.println("4-Consultar Ofertas");
			opcion=t.nextInt();t.nextLine();
			Oferta o;
			switch(opcion) {
			case 1:
				o=new Oferta();
				//Pedimos datos
				try {
					System.out.println("Fecha Inicio (yyyymmdd)");
					java.util.Date fecha = df.parse(t.nextLine());
					o.setFechaI(new Date(fecha.getTime()));
					
					System.out.println("Fecha Fin (yyyymmdd)");
					fecha = df.parse(t.nextLine());
					o.setFechaF(new Date(fecha.getTime()));
					
					o.setEstablec(u.getNombre());
					
					System.out.println("Descripción de la oferta");
					o.setDescrip(t.nextLine());
					
					//Creamos la oferta
					if(!bd.altaOferta(o)) {
						System.out.println("Error al crear la oferta");
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					System.out.println("Formato de fecha incorrecto");
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
		// TODO Auto-generated method stub
		int opcion = 0;
		do {
			System.out.println("0-Salir");
			System.out.println("1-Alta Usuario");
			opcion=t.nextInt();t.nextLine();
			switch(opcion) {
			case 1:
				//Pedimos datos usuario a crear
				Usuario u = new Usuario();
				System.out.println("Introduce el nombre");
				u.setNombre(t.nextLine());
				System.out.println("Introduce la clave");
				u.setClave(t.nextLine());
				System.out.println("Introduce el tipo (A-E-C)");
				String tipo = t.nextLine().toUpperCase();
				if(tipo.equals("A") || tipo.equals("E") || tipo.equals("C") ) {
					u.setTipo(tipo);
					if(!bd.altaUsuario(u)) {
						System.out.println("Erro al crear el usuario");
					}
				}
				else {
					System.out.println("Tipo incorrecto");
				}
				break;
			}
			
		}while(opcion!=0);
	}

}
