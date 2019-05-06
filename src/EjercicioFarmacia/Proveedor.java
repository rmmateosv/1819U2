package EjercicioFarmacia;

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
	
	public void mostrar() {
		System.out.println(codigo+"-"+nombre);
	}
}
