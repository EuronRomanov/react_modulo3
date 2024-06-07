package com.krakedev.inventarios.bdd;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import com.krakedev.inventarios.entidades.DetallePedido;
import com.krakedev.inventarios.entidades.HistorialStock;
import com.krakedev.inventarios.entidades.Pedido;
import com.krakedev.inventarios.entidades.Producto;
import com.krakedev.inventarios.excepciones.KrakedevDevException;
import com.krakedev.inventarios.utils.ConexionBDD;

public class PedidosBDD {
	public void insertar(Pedido pedido) throws KrakedevDevException  {
		Connection con=null;
		ResultSet rsClave=null;
		PreparedStatement ps=null;
		PreparedStatement psDet=null;
	
		String proceidmientoSql="INSERT INTO cabecera_pedido(cod_proveedor, fecha, estado)VALUES ( ?, ?, ?);";
		
		int codigoCabecera=0;
		Date fechaActual=new Date();
		java.sql.Date fechaSQL=new java.sql.Date(fechaActual.getTime());
		try {
			 con=ConexionBDD.obtenerConexion();
			 ps=con.prepareStatement(proceidmientoSql,Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, pedido.getProveedor().getIdentificador());
			ps.setDate(2, fechaSQL);
			ps.setString(3, "S");
			ps.executeUpdate();
			
			rsClave=ps.getGeneratedKeys();
			
			if (rsClave.next()) {
				codigoCabecera=rsClave.getInt(1);
			}
			
			ArrayList<DetallePedido>detallesPedido=pedido.getDetalles();
			DetallePedido det;
			for (int i = 0; i < detallesPedido.size(); i++) {
				det=detallesPedido.get(i);
				psDet=con.prepareStatement("insert into detalle_pedido(cabecera_pedido,producto,cantidad,cantidad_recibida,subtotal) values (?,?,?,?,?)");
				psDet.setInt(1, codigoCabecera);
				psDet.setInt(2, det.getProducto().getCodigo());
				psDet.setInt(3, det.getCantidadSolicitada());
				psDet.setInt(4, 0);
				BigDecimal pv=det.getProducto().getPreciVenta();
				BigDecimal cantidad=new BigDecimal(det.getCantidadSolicitada());
				BigDecimal subtotal=pv.multiply(cantidad);
				psDet.setBigDecimal(5, subtotal);
				psDet.executeUpdate();
				
				
			}
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
	
	
	public void actuaizar(Pedido pedido) throws KrakedevDevException  {
		Connection con=null;
		ResultSet rsClave=null;
		PreparedStatement ps=null;
		PreparedStatement psDet=null;
		HistorialStockBDD historialStockBDD=new HistorialStockBDD();
		String proceidmientoSql="UPDATE cabecera_pedido SET estado=? WHERE numero_pedido=?";
		
		int codigoCabecera=0;
		Date fechaActual=new Date();
		java.sql.Date fechaSQL=new java.sql.Date(fechaActual.getTime());
		try {
			 con=ConexionBDD.obtenerConexion();
			 ps=con.prepareStatement(proceidmientoSql);
			ps.setString(1, "R");
			ps.setInt(2, pedido.getCodigo());
			
			ps.executeUpdate();
			
			
			
			
			
			ArrayList<DetallePedido>detallesPedido=pedido.getDetalles();
			DetallePedido det;
			for (int i = 0; i < detallesPedido.size(); i++) {
				det=detallesPedido.get(i);
				psDet=con.prepareStatement("UPDATE detalle_pedido SET cantidad_recibida=?,subtotal=? WHERE codigo=?");
				psDet.setInt(1, det.getCantidadRecibida());
				BigDecimal pv=det.getProducto().getPreciVenta();
				BigDecimal cantidad=new BigDecimal(det.getCantidadRecibida());
				BigDecimal subtotal=pv.multiply(cantidad);
				psDet.setBigDecimal(2, subtotal);
				psDet.setInt(3, det.getCodigo());
				psDet.executeUpdate();
				HistorialStock historialStock=new HistorialStock();
				historialStock.setFecha(fechaActual);
				historialStock.setReference("Pedido "+pedido.getCodigo());
				historialStock.setProducto(det.getProducto());
				historialStock.setCantidad(det.getCantidadRecibida());
				historialStockBDD.insertar(historialStock);
			}
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
