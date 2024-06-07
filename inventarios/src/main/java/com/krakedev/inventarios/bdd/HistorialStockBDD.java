package com.krakedev.inventarios.bdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.krakedev.inventarios.entidades.HistorialStock;

import com.krakedev.inventarios.excepciones.KrakedevDevException;
import com.krakedev.inventarios.utils.ConexionBDD;

public class HistorialStockBDD {
	public void insertar(HistorialStock historialStock) throws KrakedevDevException  {
		Connection con=null;
		String proceidmientoSql="INSERT INTO historial_stock(fecha,reference,producto,cantidad) VALUES (?, ?, ?, ?)";
		Timestamp fechaHoraActual=new Timestamp(historialStock.getFecha().getTime());
		try {
			 con=ConexionBDD.obtenerConexion();
			PreparedStatement ps=con.prepareStatement(proceidmientoSql);
			ps.setTimestamp(1, fechaHoraActual);
			ps.setString(2, historialStock.getReference());
			ps.setInt(3, historialStock.getProducto().getCodigo());
			ps.setInt(4, historialStock.getCantidad());
			
			
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new KrakedevDevException("Error al insertar Proveedor");
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
