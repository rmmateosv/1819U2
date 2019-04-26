package Ejercicio__BD_Ofertas;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class Modelo {

private	Connection conexion=null;
	
	public Modelo() {
		try {
			Class.forName("com.musql.jdbc.driver");
			conexion=DriverManager.getConnection("root","root", "jdbc:mysql://localhost:3306/ofertas");
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
			PreparedStatement consulta=conexion.prepareStatement("select * from usuario where nombre=? and clave=sha2(?,0)");
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
	
	
}
