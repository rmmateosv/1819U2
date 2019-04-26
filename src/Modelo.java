import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Modelo {
	
	private Connection conexion = null;
	
	
	public Modelo() {
		try {
			Class.forName("com.mysql.jdbc.driver");
			conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ofertas", 
					"root", "root");
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

	public void setConexion(Connection conexion) {
		this.conexion = conexion;
	}

	public Usuario obtenerUsuario(String usu, String contras) {
		// TODO Auto-generated method stub
		Usuario resultado = null;
		try {
			//Crear la consulta
			PreparedStatement consulta = conexion.prepareStatement(
					"select * from usuarios "
					+ "where nombre = ? and"
					+ "clave = sha2(?,0)");
			//Registrar parámetros
			consulta.setString(1, usu);
			consulta.setString(2, contras);
			//Ejecutamos consulta
			ResultSet registros = consulta.executeQuery();
			//Si se devuelve 1 registro, el usuario y la contraseña
			//son correctos por lo que se puede hacer login
			if(registros.next()) {
				//Creamos un objeto usuario con los datos
				//del usuario con el que se accede
				resultado = new Usuario();
				resultado.setNombre(registros.getString(1));
				resultado.setTipo(registros.getString(3));
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}
	
	
}
