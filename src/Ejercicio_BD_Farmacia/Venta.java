package Ejercicio_BD_Farmacia;

import java.sql.Date;

public class Venta {

	private int codigo;
	private Date fecha;
	private int unidades;
	private Medicamento medicamento;
	
	public Venta() {
		
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getUnidades() {
		return unidades;
	}

	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}

	public Medicamento getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(Medicamento medicamento) {
		this.medicamento = medicamento;
	}

	
	
	
	
	
	//METODOS DE LA CLASE VENTA.
/*--------------------------------------------------------------------------------------------------------*/	
	
	public void mostrar() {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
