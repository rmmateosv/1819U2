package Ejercicio_BD_Farmacia;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Principal {

	public static Scanner leer=new Scanner(System.in);
	public static Modelo bd=new Modelo();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		if(bd.getConexion()!=null) {
			
			// EL CLIENTE PUDE VER TODAS LAS OFERTAS Y VER LAS OFERTAS DE UN CLIENTE.
			int opcion=0;
			do {
				
				System.out.println("0-Salir.");
				System.out.println("1-Alta Medicamento.");
				System.out.println("2-Mostrar Medicamentos.");
				System.out.println("3-Alta Venta.");
				System.out.println("4-Entrega Pedido.");
				
				opcion=leer.nextInt();leer.nextLine();
				Medicamento m;
				
				switch(opcion) {
				case 1:
					//Pedimos los datos.
					m=new Medicamento();
					System.out.println("Introduce el nombre del medicamento: ");
					m.setNombre(leer.nextLine());
					System.out.println("Stock MINIMO del medicamento: ");
					m.setStockMin(leer.nextInt());leer.nextLine();
					System.out.println("Stock MAXIMO del medicamento: ");
					m.setStockMax(leer.nextInt());leer.nextLine();
					System.out.println("Stock REAL  del medicamento: ");
					m.setStockReal(leer.nextInt());leer.nextLine();
					//Mostramos proveedores: Si se introduce un 0 se crea uno nuevo.
					System.out.println("Introduce el codigo del proveedor \n0-Crear un nuevo proveedor.");
					bd.MostrarProveedor();
					m.setProveedor(new Proveedor());
					m.getProveedor().setCodigo(leer.nextInt());leer.nextLine();
					if(m.getProveedor().getCodigo()==0) {
						System.out.println("Introduce el nombre del proveedor: ");
						m.getProveedor().setNombre(leer.nextLine());
						
						if(!bd.CrearProveedor(m.getProveedor())) {
							System.err.println("ERROR AL CREAR EL PROVEEDOR.");
							break;
						}
					}
					//CREAMOS LOS MEDICAMENTOS.
					if(!bd.crearMedicamento(m)) {
						System.err.print("ERROR AL CREAR EL MEDICAMENTO.");
					}
					break;
				case 2:
					//Mostramos los datos del medicamento.
					bd.mostrarMedicamentos(true);
					break;
				case 3:
					bd.mostrarMedicamentos(false);
					
					m=new Medicamento();
					System.out.println("Introduce el codigo del medicamento: ");
					m.setId(leer.nextInt());leer.nextLine();
					//Obtenemos los datos de los medicamentos que se van a vender.
					m=bd.obtnerMedicamento(m.getId());
					if(m==null) {
						System.err.println("ERROR: EL MEDICAMENTO NO EXISTE.");
					}else {
						//Comprobamos si hay stock.
						System.out.println("Introduce la cantidad que va a retirar: ");
						int cantidad=leer.nextInt();leer.nextLine();
						if(m.getStockReal()>=cantidad) {
							
							if(!bd.altaVenta(m,cantidad)) {
								System.err.println("ERROR: No se ha podido realizar la venta.");
							}else {
								if(m.getStockReal()-cantidad>=m.getStockMin()) {
									
									if(!bd.altaPedido(m,cantidad)) {
										System.err.println("ERROR: No se ha podido hacer el pedido.");
									}
								}
						}
					}
						else {
							System.err.println("ERROR: No hay cantidad suficiente de stock.");
						}
							
				}
					break;
				case 4:
					
					bd.obtenerPedido();
					System.out.println("Introduce el codigo del pedido: ");
					int codigo=leer.nextInt();leer.nextLine();
					if(!bd.entregarPedido(codigo)) {
						System.err.println("ERROR: al marcar el pedido como entregado.");
					}
					
					break;
				}
				
			}while(opcion!=0);
			
		}else {
			System.err.println("Error al conectar con la base de datos.");
		}
	}
}
