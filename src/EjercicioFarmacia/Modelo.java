package EjercicioFarmacia;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Modelo {
	Connection conexion=null;
	public Modelo() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/farmacia", 
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

	public void MostrarProveedores() {
		// TODO Auto-generated method stub
		Statement consulta;
		try {
			consulta = conexion.createStatement();
			ResultSet r = consulta.executeQuery("select * from proveedor");
			while(r.next()) {
				Proveedor p = new Proveedor();
				p.setCodigo(r.getInt(1));
				p.setNombre(r.getString(2));
				p.mostrar();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public boolean crearProveedor(Proveedor proveedor) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			PreparedStatement consulta = conexion.prepareStatement(
					"insert into proveedor values (null,?)",
					Statement.RETURN_GENERATED_KEYS);
			consulta.setString(1, proveedor.getNombre());
			int r = consulta.executeUpdate();
			if(r==1) {
				//Recuperamos el código del proveedor creado
				ResultSet codigo = consulta.getGeneratedKeys();
				if(codigo.next()) {
					proveedor.setCodigo(codigo.getInt(1));
					resultado=true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean crearMedicamento(Medicamento m) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			PreparedStatement consulta = conexion.prepareStatement(
					"insert into medicamento values (null,?,?,?,?,?)");
			consulta.setString(1, m.getNombre());
			consulta.setInt(2, m.getStockMin());
			consulta.setInt(3, m.getStockMax());
			consulta.setInt(4, m.getStockReal());
			consulta.setInt(5, m.getProveedor().getCodigo());
			int r = consulta.executeUpdate();
			if(r==1) {
				resultado=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public void mostrarMedicamentos(boolean detalle) {
		// TODO Auto-generated method stub
		try {
			//Mostrar medicamento y su proveedor
			Statement consulta = conexion.createStatement();
			ResultSet medicamentos = consulta.executeQuery(
					"select * "
					+ "from medicamento m join proveedor p "
					+ "on m.proveedor = p.codigo "
					+ "order by m.codigo");
			while(medicamentos.next()) {
				Medicamento  m = new Medicamento();
				m.setId(medicamentos.getInt(1));
				m.setNombre(medicamentos.getString(2));
				m.setStockMin(medicamentos.getInt(3));
				m.setStockMax(medicamentos.getInt(4));
				m.setStockReal(medicamentos.getInt(5));
				m.setProveedor(new Proveedor());
				m.getProveedor().setCodigo(medicamentos.getInt(6));
				m.getProveedor().setNombre(medicamentos.getString(8));
				System.out.println("--- Medicamento ----------------");
				m.mostrar();
				if(detalle) {
					System.out.println("------->Datos Proveedor");
					m.getProveedor().mostrar();
					System.out.println("------->Pedidos");
					ArrayList<Pedido> pedidos = new ArrayList<>();
					pedidos = obtenerPedidos(m);
					for(Pedido p:pedidos) {
						p.mostrar();
					}
					System.out.println("------->Ventas");
					ArrayList<Venta> ventas = new ArrayList<>();
					ventas = obtenerVentas(m);
					for(Venta v:ventas) {
						v.mostrar();
					}
					System.out.println("--------------------------------");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private ArrayList<Pedido> obtenerPedidos(Medicamento m) {
		// TODO Auto-generated method stub
		ArrayList<Pedido> resultado = new ArrayList<>();
		try {
			PreparedStatement consulta = conexion.prepareStatement(
					"select * from pedido "
					+ "where medicamento = ?");
			consulta.setInt(1, m.getId());
			ResultSet r = consulta.executeQuery();
			while(r.next()) {
				Pedido p = new Pedido();
				p.setCodigo(r.getInt(1));
				p.setFecha(r.getDate(2));
				p.setUnidades(r.getInt(3));
				p.setMedicamento(m);
				resultado.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}
	private ArrayList<Venta> obtenerVentas(Medicamento m) {
		// TODO Auto-generated method stub
		ArrayList<Venta> resultado = new ArrayList<>();
		try {
			PreparedStatement consulta = conexion.prepareStatement(
					"select * from venta "
					+ "where medicamento = ?");
			consulta.setInt(1, m.getId());
			ResultSet r = consulta.executeQuery();
			while(r.next()) {
				Venta v = new Venta();
				v.setCodigo(r.getInt(1));
				v.setFecha(r.getDate(2));
				v.setUnidades(r.getInt(3));
				v.setMedicamento(m);
				resultado.add(v);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public Medicamento obtenerMedicamento(int id) {
		// TODO Auto-generated method stub
		Medicamento resultado = null;
		try {
			PreparedStatement consulta = conexion.prepareStatement(
					"select * from medicamento "
					+ "where codigo = ?");
			consulta.setInt(1, id);
			ResultSet r = consulta.executeQuery();
			if(r.next()) {
				resultado = new Medicamento();
				resultado.setId(r.getInt(1));
				resultado.setNombre(r.getString(2));
				resultado.setStockMin(r.getInt(3));
				resultado.setStockMax(r.getInt(4));
				resultado.setStockReal(r.getInt(5));
				resultado.setProveedor(new Proveedor());
				resultado.getProveedor().setCodigo(r.getInt(6));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean altaVenta(Medicamento m, int cantidad) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			PreparedStatement consulta = conexion.prepareStatement(
					"insert into venta values(null,?,?,?)");
			consulta.setDate(1, new Date(new java.util.Date().getTime()));
			consulta.setInt(2, cantidad);
			consulta.setInt(3, m.getId());
			int r = consulta.executeUpdate();
			if(r==1) {
				consulta = conexion.prepareStatement(
						"update medicamento set stockReal = stockReal - ? "
						+ "where codigo = ?");
				consulta.setInt(1, cantidad);
				consulta.setInt(2, m.getId());
				r=consulta.executeUpdate();
				if(r==1) {
					resultado =true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean altaPedido(Medicamento m, int cantidad) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			PreparedStatement consulta = conexion.prepareStatement(
					"insert into pedido values(null,?,?,?)");
			consulta.setDate(1, new Date(new java.util.Date().getTime()));
			consulta.setInt(2, m.getStockMax()-(m.getStockReal()-cantidad));
			consulta.setInt(3, m.getId());
			int r = consulta.executeUpdate();
			if(r==1) {
				resultado =true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public void mostrarPedidos() {
		// TODO Auto-generated method stub
		try {
			Statement consulta = conexion.createStatement();
			ResultSet r = consulta.executeQuery(
					"select * from pedido where entregado = false");
			while(r.next()) {
				Pedido p = new Pedido();
				p.setCodigo(r.getInt(1));
				p.setFecha(r.getDate(2));
				p.setUnidades(r.getInt(3));
				p.setMedicamento(new Medicamento());
				p.getMedicamento().setId(r.getInt(4));
				p.mostrar();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public boolean entregarPedido(int codigo) {
		// TODO Auto-generated method stub
		boolean resultado =false;
		try {
			PreparedStatement consulta = conexion.prepareStatement(
					"update pedido "
					+ "set entregado = true "
					+ "where codigo = ? and "
					+ "entregado = false");
			consulta.setInt(1, codigo);
			int r = consulta.executeUpdate();
			if(r==1) {
				PreparedStatement info = conexion.prepareStatement(
						"select * from pedido " + 
						"where codigo = ?");
				info.setInt(1, codigo);
				ResultSet rInfo = info.executeQuery();
				
				if(rInfo.next()) {
					consulta = conexion.prepareStatement(
							"update medicamento "
							+ "set stockReal = stockReal + ? "
							+ "where codigo = ?");
					consulta.setInt(1, rInfo.getInt(4));
					consulta.setInt(2, rInfo.getInt(5));
					if(r==1) {
						resultado=true;
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}
}
