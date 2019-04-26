import java.util.Scanner;

public class Ejercicio1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Modelo bd = new Modelo();
		Scanner t = new Scanner(System.in);
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
				
			}
		}
		else {
			System.out.println("Error al establcer la conexión con la BD");
		}
	}

}
