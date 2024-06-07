package com.krakedev.inventarios.bdd;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.krakedev.inventarios.entidades.Categoria;
import com.krakedev.inventarios.entidades.Producto;
import com.krakedev.inventarios.entidades.Proveedor;
import com.krakedev.inventarios.entidades.TipoDocumento;
import com.krakedev.inventarios.entidades.UnidadDeMedida;
import com.krakedev.inventarios.excepciones.KrakedevDevException;
import com.krakedev.inventarios.utils.ConexionBDD;

public class ProductoBDD {
	public ArrayList<Producto>buscar(String letra) throws KrakedevDevException {
		ArrayList<Producto> productos=new ArrayList<>();
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Producto producto=null;
		
		String proceidmientoSql="SELECT prod.codigo_producto, prod.nombre as nombre_producto,"
				+ "udm.codigo_udm as nombre_udm,udm.descripcion as descripcion_udm,"
				+ "cast(prod.precio_venta as decimal(6,2)), prod.tiene_iva, cast(prod.coste as decimal(5,4)), "
				+ "prod.cod_categoria, cat.nombre as nombre_categoria,stock"
				+ "	FROM productos prod,unidades_medida udm,categorias cat"
				+ "	where prod.codigo_udm=udm.codigo_udm"
				+ "	and prod.cod_categoria=cat.codigo_cat"
				+ " and upper(prod.nombre) like ?";
		try {
			con=ConexionBDD.obtenerConexion();
			 ps=con.prepareStatement(proceidmientoSql);
			 ps.setString(1, "%"+letra.toUpperCase()+"%");
			rs= ps.executeQuery();
			while(rs.next()){
				int codigoProducto=rs.getInt("codigo_producto");
				String nombreProducto=rs.getString("nombre_producto");
				String nombreUnidadMedida=rs.getString("nombre_udm");
				String descripcionUnidadMedida=rs.getString("descripcion_udm");
				BigDecimal precioVenta=rs.getBigDecimal("precio_venta");
				boolean tieneIva=rs.getBoolean("tiene_iva");
				BigDecimal coste=rs.getBigDecimal("coste");
				int codigoCategoria=rs.getInt("cod_categoria");
				String nombreCategoria=rs.getString("nombre_categoria");
				int stock=rs.getInt("stock");
				
				UnidadDeMedida unidadMedida=new UnidadDeMedida();
				unidadMedida.setNombre(nombreUnidadMedida);
				unidadMedida.setDescripcion(descripcionUnidadMedida);
				
				Categoria categoria=new Categoria();
				categoria.setCodigo(codigoCategoria);
				categoria.setNombre(nombreCategoria);
				
				producto=new Producto(codigoProducto, nombreProducto, unidadMedida, precioVenta, tieneIva, coste, categoria, stock);
				productos.add(producto);
			}
			 
		} catch (KrakedevDevException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new KrakedevDevException("Error de consulta Detalle "+e.getMessage());
		}
		
		return productos;
	}
	
	public void insertar(Producto producto) throws KrakedevDevException  {
		Connection con=null;
		String proceidmientoSql="INSERT INTO productos( nombre, codigo_udm, precio_venta, tiene_iva, coste, cod_categoria, stock)VALUES ( ?, ?, ?, ?, ?, ?, ?)";
		try {
			 con=ConexionBDD.obtenerConexion();
			PreparedStatement ps=con.prepareStatement(proceidmientoSql);
			ps.setString(1, producto.getNombre());
			ps.setString(2, producto.getUnidadMedida().getNombre());
			ps.setBigDecimal(3, producto.getPreciVenta());
			ps.setBoolean(4, producto.isTieneIva());
			ps.setBigDecimal(5, producto.getCoste());
			ps.setInt(6, producto.getCategoria().getCodigo());
			ps.setInt(7, producto.getStock());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new KrakedevDevException("Error al insertar Producto");
		} catch (KrakedevDevException e) {
			// TODO Auto-generated catch block
			throw e;
		}finally {
			if (con!=null) {
				try {
					con.close()
;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			}
		}
	}
}
