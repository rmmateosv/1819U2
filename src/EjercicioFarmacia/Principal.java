package EjercicioFarmacia;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Principal {
	public static Scanner t = new Scanner(System.in);
	public static Modelo bd = new Modelo();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(bd.getConexion()!=null) {
			int opcion =0 ;
			do {
				System.out.println("0-Salir");
				System.out.println("1-Alta medicamento");
				System.out.println("2-Mostrar Medicamentos");
				System.out.println("3-Alta Venta");
				System.out.println("4-Mostrar Ventas");
				System.out.println("5-Mostrar Pedido");
				opcion=t.nextInt();t.nextLine();
				Medicamento m;
				
				switch(opcion) {
				case 1:
					//Pedimos datos
					m=new Medicamento();
					System.out.println("Introduce nombre");
					m.setNombre(t.nextLine());
					System.out.println("Stock mínimo");
					m.setStockMin(t.nextInt());t.nextLine();
					System.out.println("Stock máximo");
					m.setStockMax(t.nextInt());t.nextLine();
					System.out.println("Stock real");
					m.setStockReal(t.nextInt());t.nextLine();
					
					//Mostramos proveedores - 0 si proveedor nuevo
					System.out.println("Introduce código proveedor\n0-Crear Proveedor");
					bd.MostrarProveedores();
					//Si 0->Pedimos datos del proveedor y creamos
					m.setProveedor(new Proveedor());
					m.getProveedor().setCodigo(t.nextInt());t.nextLine();
					if(m.getProveedor().getCodigo()==0) {
						System.out.println("Nombre proveedor");
						m.getProveedor().setNombre(t.nextLine());
						if(!bd.crearProveedor(m.getProveedor())) {
							System.out.println("Error al crear el proveedor");
							break;
						}
					}
					//Creamos medicamento
					if(!bd.crearMedicamento(m)) {
						System.out.println("Error al crear el medicamento");
					}
					
					break;
				case 2:
					bd.mostrarMedicamentos();
					break;
				case 3:
					break;
				case 4:
					break;
				}
				
			}while(opcion!=0);
		}
		else {
			System.out.println("Error al establcer la conexión con la BD");
		}
	}

	
}
