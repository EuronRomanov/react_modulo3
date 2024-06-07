package com.krakedev.inventarios.bdd;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import com.krakedev.inventarios.entidades.DetallePedido;
import com.krakedev.inventarios.entidades.DetalleVenta;
import com.krakedev.inventarios.entidades.HistorialStock;
import com.krakedev.inventarios.entidades.Pedido;
import com.krakedev.inventarios.entidades.Venta;
import com.krakedev.inventarios.excepciones.KrakedevDevException;
import com.krakedev.inventarios.utils.ConexionBDD;

public class VentasBDD {
	
	public void insertar(Venta venta) throws KrakedevDevException  {
		Connection con=null;
		ResultSet rsClave=null;
		ResultSet detClave=null;
		PreparedStatement ps=null;
		PreparedStatement psDet=null;
		HistorialStockBDD historialStockBDD=new HistorialStockBDD();
		String proceidmientoSql="INSERT INTO cabecera_ventas(fecha,total_sin_iva,iva,total) VALUES ( ?, ?, ?, ?);";
		
		int codigoCabecera=0;
		int codigoDetalle=0;
		Date fechaActual=new Date();
		
		Timestamp fechaHoraActual=new Timestamp(fechaActual.getTime());
		BigDecimal valorInicial=new BigDecimal(0);
		BigDecimal valorIva=new BigDecimal(1.12);
		try {
			 con=ConexionBDD.obtenerConexion();
			 ps=con.prepareStatement(proceidmientoSql,Statement.RETURN_GENERATED_KEYS);
			ps.setTimestamp(1, fechaHoraActual);
			ps.setBigDecimal(2, valorInicial);
			ps.setBigDecimal(3, valorInicial);
			ps.setBigDecimal(4, valorInicial);
			ps.executeUpdate();
			
			rsClave=ps.getGeneratedKeys();
			
			if (rsClave.next()) {
				codigoCabecera=rsClave.getInt(1);
			}
			
			
			 BigDecimal sumaTotal = BigDecimal.ZERO;
			 BigDecimal sumaTotalIva = BigDecimal.ZERO;
			 BigDecimal sumaTotalNoIva = BigDecimal.ZERO;
			ArrayList<DetalleVenta>detallesVenta=venta.getDetalles();
			DetalleVenta det;
			for (int i = 0; i < detallesVenta.size(); i++) {
				det=detallesVenta.get(i);
				psDet=con.prepareStatement("insert into detalle_ventas(cabecera_ventas,producto,cantidad,precio_venta,subtotal,subtotal_iva) values (?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
				psDet.setInt(1, codigoCabecera);
				psDet.setInt(2, det.getProducto().getCodigo());
				psDet.setInt(3, det.getCantidad());
				BigDecimal pv=det.getProducto().getPreciVenta();
				psDet.setBigDecimal(4, pv);
				
				BigDecimal cantidad=new BigDecimal(det.getCantidad());
				BigDecimal subtotal=pv.multiply(cantidad);
				psDet.setBigDecimal(5, subtotal);
				
				if ( det.getProducto().isTieneIva()) {
					BigDecimal total=subtotal.multiply(valorIva);
					sumaTotalIva =sumaTotalIva.add(total);
					sumaTotal=sumaTotal.add(total);
					
					psDet.setBigDecimal(6,total );
				} else {
					sumaTotal=sumaTotal.add(subtotal);
					sumaTotalNoIva =sumaTotalNoIva.add(subtotal);
					psDet.setBigDecimal(6, subtotal);
				}
				psDet.executeUpdate();
				HistorialStock historialStock=new HistorialStock();
				historialStock.setFecha(fechaActual);
				historialStock.setReference("VENTA "+codigoCabecera);
				historialStock.setProducto(det.getProducto());
				historialStock.setCantidad((det.getCantidad()*(-1)));
				historialStockBDD.insertar(historialStock);
				
				
			}
			Venta ventaUpdate =new Venta();
			ventaUpdate.setCodigo(codigoCabecera);
			ventaUpdate.setTotal(sumaTotal);
			ventaUpdate.setIva(sumaTotalIva);
			ventaUpdate.setTotal_sin_iva(sumaTotalNoIva);
			actualizar(ventaUpdate);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new KrakedevDevException("Error al insertar Venta");
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
	
	
	public void actualizar(Venta venta) throws KrakedevDevException  {
		Connection con=null;
		PreparedStatement ps=null;
		
	
		String proceidmientoSql="UPDATE cabecera_ventas SET total_sin_iva=?,iva=?,total=? WHERE codigo=?";
		
		
		try {
			 con=ConexionBDD.obtenerConexion();
			 ps=con.prepareStatement(proceidmientoSql);
			ps.setBigDecimal(1, venta.getTotal_sin_iva());
			ps.setBigDecimal(2, venta.getIva());
			ps.setBigDecimal(3, venta.getTotal());
			ps.setInt(4, venta.getCodigo());
			ps.executeUpdate();
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new KrakedevDevException("Error al insertar Venta");
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
