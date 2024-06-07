package com.krakedev.inventarios.bdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.krakedev.inventarios.utils.ConexionBDD;

import com.krakedev.inventarios.entidades.Proveedor;
import com.krakedev.inventarios.entidades.TipoDocumento;
import com.krakedev.inventarios.excepciones.KrakedevDevException;



public class ProveedoresBDD {

	public ArrayList<Proveedor>buscar(String letra) throws KrakedevDevException {
		ArrayList<Proveedor> proveedores=new ArrayList<>();
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Proveedor proveedor=null;
		
		String proceidmientoSql="Select pr.identificador, pr.tipo_documento, td.descripcion, pr.nombre, pr.telefono, pr.correo, pr.direccion"
				+ " from proveedores pr,tipo_documento td"
				+ " where pr.tipo_documento=td.codigo_td AND upper(nombre) like ?";
		try {
			con=ConexionBDD.obtenerConexion();
			 ps=con.prepareStatement(proceidmientoSql);
			 ps.setString(1, "%"+letra.toUpperCase()+"%");
			rs= ps.executeQuery();
			while(rs.next()){
				String identificador=rs.getString("identificador");
				String codigoTipoDocumento=rs.getString("tipo_documento");
				String descripcion=rs.getString("descripcion");
				String nombre=rs.getString("nombre");
				String telefono=rs.getString("telefono");
				String correo=rs.getString("correo");
				String direccion=rs.getString("direccion");
				TipoDocumento tipoDocumento=new TipoDocumento(codigoTipoDocumento, descripcion);
				proveedor=new Proveedor(identificador, tipoDocumento, nombre, telefono, correo, direccion);
				
				proveedores.add(proveedor);
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
		
		return proveedores;
	}
	
	
	public void insertar(Proveedor proveedor) throws KrakedevDevException  {
		Connection con=null;
		String proceidmientoSql="INSERT INTO proveedores(identificador, tipo_documento, nombre, telefono, correo, direccion) VALUES (?, ?, ?, ?, ?, ?)";
		try {
			 con=ConexionBDD.obtenerConexion();
			PreparedStatement ps=con.prepareStatement(proceidmientoSql);
			ps.setString(1, proveedor.getIdentificador());
			ps.setString(2, proveedor.getTipoDocumento().getCodigo());
			ps.setString(3, proveedor.getNombre());
			ps.setString(4, proveedor.getTelefono());
			ps.setString(5, proveedor.getCorreo());
			ps.setString(6, proveedor.getDireccion());
			
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
