package com.krakedev.inventarios.servicios;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.krakedev.inventarios.bdd.PedidosBDD;
import com.krakedev.inventarios.bdd.ProveedoresBDD;
import com.krakedev.inventarios.entidades.Pedido;
import com.krakedev.inventarios.entidades.Proveedor;
import com.krakedev.inventarios.excepciones.KrakedevDevException;

@Path("pedidos")
public class ServiciosPedidos {
	 @Path("registrar")
	 @POST
	@Consumes(MediaType.APPLICATION_JSON)
	 public Response insertar(Pedido pedido) {
		
		 PedidosBDD cli=new PedidosBDD ();
		 try {
			 cli.insertar(pedido);
			return Response.ok().build();
		} catch (KrakedevDevException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.serverError().build();
		}
	 }
	 
	 @Path("recibir")
	 @PUT
	@Consumes(MediaType.APPLICATION_JSON)
	 public Response actualizar(Pedido pedido) {
		
		 PedidosBDD cli=new PedidosBDD ();
		 try {
			 cli.actuaizar(pedido);
			return Response.ok().build();
		} catch (KrakedevDevException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.serverError().build();
		}
	 }
	 
}
