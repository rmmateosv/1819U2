package Ejercicio__BD_Ofertas;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



public class Modelo {

private	Connection conexion=null;
	
	public Modelo() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexion=DriverManager.getConnection("jdbc:mysql://localhost:3306/ofertas","root","root");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public Connection getConexion() {
		return conexion;
	}

	public void setCinexion(Connection conexion) {
		this.conexion = conexion;
	}


	public Usuario obtenerUsuario(String usu, String contras) {
		
		Usuario resultado=null;
		
		try {
			PreparedStatement consulta=conexion.prepareStatement("select * from usuarios where nombre=? and clave=sha2(?,0)");
			//Registramos los parametros.
			consulta.setString(1, usu);
			consulta.setString(2, contras);
			//Ahora ejecutamos la consulta.
			ResultSet registro=consulta.executeQuery();
			//Si se devuelve 1 registro el usuario y la contraseña son correctos con lo que se puede hacer el login.
			if(registro.next()) {
				//Creamos un objeto usuario con los datos del usuario con el que se accede.
				resultado = new Usuario();
				resultado.setNombre(registro.getString(1));
				resultado.setTipo(registro.getString(3));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return resultado;
	}


	public boolean altaUsuario(Usuario u) {
		// TODO Auto-generated method stub
		boolean resultado=false;
		try {
			
			PreparedStatement consulta=conexion.prepareStatement("insert into usuarios values(?,sha2(?,0),?)");
			//Rellenamos los parametros.
			consulta.setString(1, u.getNombre());
			consulta.setString(2, u.getClave());
			consulta.setString(3, u.getTipo());
			
			//Ejecutamos la consulta.
			int r=consulta.executeUpdate();
			//Chequeamos si va bien r=1, Nº de filas insertadas
			if(r==1) {
				resultado=true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}


	public void mostrarOfertas(String nombre) {
		// TODO Auto-generated method stub
		PreparedStatement consulta;
		
			try {
				if(nombre==null) {
				consulta=conexion.prepareStatement("select * from ofertas where  fechaI<=curdate() and fechaF>=curdate()");
				
				
				}else {
					consulta=conexion.prepareStatement("select * from ofertas where establec=? and fechaI<=curdate() and fechaF>=curdate()");
					consulta.setString(1, nombre);
				}
				
				ResultSet r=consulta.executeQuery();
				while(r.next()){
					Oferta o=new Oferta();
					o.setId(r.getInt(1));
					o.setFechaI(r.getDate(2));
					o.setFechaF(r.getDate(3));
					o.setEstablec(r.getString(4));
					o.setDescrip(r.getString(5));
					o.mostrar();
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		
	}


	public ArrayList<String> obtenerEstablecConOfertas() {
		// TODO Auto-generated method stub
		ArrayList<String> resultado=new ArrayList<>();
		
	try {
		/*Obtenemos los establecimientos con ofertas en vigor.*/
		PreparedStatement consulta=conexion.prepareStatement("select distinct(establec) from ofertas where fechaI<=curdate() and fechaF>=curdate()");
		//Ejecutamos la consulta 
		ResultSet r=consulta.executeQuery();
		//Introducimos en el resultado todos lo establecimientos devueltos.
		while(r.next()) {
			resultado.add(r.getString(1));
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
		
		return resultado;
	}


	public boolean altaOferta(Oferta o) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
