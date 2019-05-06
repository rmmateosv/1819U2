package Ejercicio_BD_Farmacia;

public class Medicamento {

	private int id;
	private String nombre;
	private int stockMin,stockMax,stockReal;
	private Proveedor proveedor;
	
	public Medicamento() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getStockMin() {
		return stockMin;
	}

	public void setStockMin(int stockMin) {
		this.stockMin = stockMin;
	}

	public int getStockMax() {
		return stockMax;
	}

	public void setStockMax(int stockMax) {
		this.stockMax = stockMax;
	}

	public int getStockReal() {
		return stockReal;
	}

	public void setStockReal(int stockReal) {
		this.stockReal = stockReal;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	//METODOS DE ESTA CLASE MEDICAMENTO.
/*------------------------------------------------------------------------------*/
	public void mostrar() {
		// MOSTRAREMOS LOS MEDICAMENTOS.
		System.out.println("Codigo: "+id+" "+nombre+
				"\tMin: "+stockMin+
				"\tMax: "+stockMax+
				"\tReal: "+stockReal);
	}
	
	
	
}
