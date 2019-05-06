package Ejercicio_BD_Farmacia;

public class Proveedor {

	private int codigo;
	private String nombre;
	
	public Proveedor() {
		
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	//METODOS DE ESTA CLASE PROVEEDOR.
/*---------------------------------------------------------------------------------------*/
public void mostrar() {
	System.out.print(codigo+"-"+nombre);
}
}
