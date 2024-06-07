package com.krakedev.inventarios.servicios;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import com.krakedev.inventarios.bdd.VentasBDD;

import com.krakedev.inventarios.entidades.Venta;
import com.krakedev.inventarios.excepciones.KrakedevDevException;

@Path("ventas")
public class ServiciosVentas {
	 @Path("guardar")
	 @POST
	@Consumes(MediaType.APPLICATION_JSON)
	 public Response insertar(Venta venta) {
		
		 VentasBDD cli=new  VentasBDD ();
		 try {
			 cli.insertar(venta);
			return Response.ok().build();
		} catch (KrakedevDevException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.serverError().build();
		}
	 }
}
