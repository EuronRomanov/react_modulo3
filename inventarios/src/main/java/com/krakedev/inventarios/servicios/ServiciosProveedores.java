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


import com.krakedev.inventarios.bdd.ProveedoresBDD;
import com.krakedev.inventarios.entidades.Proveedor;
import com.krakedev.inventarios.excepciones.KrakedevDevException;




@Path("proveedores")
public class ServiciosProveedores {
	
	
	@Path("buscar/{sub}")
	 @GET
	 @javax.ws.rs.Produces(MediaType.APPLICATION_JSON)
	 public Response buscar(@PathParam("sub") String subCadena){
		 ProveedoresBDD cli=new ProveedoresBDD ();
		 ArrayList<Proveedor>clientes=null;
		 try {
			clientes=cli.buscar(subCadena);
			return Response.ok(clientes).build();
		} catch (KrakedevDevException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			 return Response.serverError().build();
		}
		
	 }
	
	 @Path("crear")
	 @POST
	@Consumes(MediaType.APPLICATION_JSON)
	 public Response insertar(Proveedor proveedor) {
		
		 ProveedoresBDD cli=new ProveedoresBDD ();
		 try {
			 cli.insertar(proveedor);
			return Response.ok().build();
		} catch (KrakedevDevException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.serverError().build();
		}
	 }
}
