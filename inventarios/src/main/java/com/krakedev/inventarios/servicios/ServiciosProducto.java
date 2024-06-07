package com.krakedev.inventarios.servicios;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.krakedev.inventarios.bdd.ProductoBDD;
import com.krakedev.inventarios.bdd.ProveedoresBDD;
import com.krakedev.inventarios.entidades.Producto;
import com.krakedev.inventarios.entidades.Proveedor;
import com.krakedev.inventarios.excepciones.KrakedevDevException;
@Path("productos")
public class ServiciosProducto {
	@Path("buscar/{sub}")
	 @GET
	 @javax.ws.rs.Produces(MediaType.APPLICATION_JSON)
	 public Response buscar(@PathParam("sub") String subCadena){
		 ProductoBDD cli=new ProductoBDD ();
		 ArrayList<Producto>productos=null;
		 try {
			productos=cli.buscar(subCadena);
			return Response.ok(productos).build();
		} catch (KrakedevDevException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			 return Response.serverError().build();
		}
		
	 }
	
	 @Path("crear")
	 @POST
	@Consumes(MediaType.APPLICATION_JSON)
	 public Response insertar(Producto producto) {
		
		 ProductoBDD cli=new ProductoBDD ();
		 try {
			 cli.insertar(producto);
			return Response.ok().build();
		} catch (KrakedevDevException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.serverError().build();
		}
	 }
}
