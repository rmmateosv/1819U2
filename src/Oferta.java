import java.sql.Date;

public class Oferta {
	private int id;
	private Date fechaI, fechaF;
	private String establec,descrip;
	
	public Oferta() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFechaI() {
		return fechaI;
	}

	public void setFechaI(Date fechaI) {
		this.fechaI = fechaI;
	}

	public Date getFechaF() {
		return fechaF;
	}

	public void setFechaF(Date fechaF) {
		this.fechaF = fechaF;
	}

	public String getEstablec() {
		return establec;
	}

	public void setEstablec(String establec) {
		this.establec = establec;
	}

	public String getDescrip() {
		return descrip;
	}

	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}
	
	public void mostrar() {
		System.out.println("Id:"+id+
				"\tFechaI:"+fechaI.toString()+
				"\tFechaF:"+fechaF.toString()+
				"\tEstablecimiento:"+establec+
				"\tDescripción:"+descrip);
	}
	
}
