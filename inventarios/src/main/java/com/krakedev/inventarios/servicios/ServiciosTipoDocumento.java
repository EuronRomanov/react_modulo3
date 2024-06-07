package com.krakedev.inventarios.servicios;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.krakedev.inventarios.bdd.ProductoBDD;
import com.krakedev.inventarios.bdd.TipoDocumentoBDD;
import com.krakedev.inventarios.entidades.Producto;
import com.krakedev.inventarios.entidades.TipoDocumento;
import com.krakedev.inventarios.excepciones.KrakedevDevException;


@Path("tiposdocumento")
public class ServiciosTipoDocumento {
	
	@Path("recuperar")
	 @GET
	 @javax.ws.rs.Produces(MediaType.APPLICATION_JSON)
	 public Response obtenerTipoDocumentos(){
		 TipoDocumentoBDD cli=new TipoDocumentoBDD();
		 ArrayList<TipoDocumento>TipoDocumentos=null;
		 try {
			TipoDocumentos=cli.recuperarTodos();
			return Response.ok(TipoDocumentos).build();
		} catch (KrakedevDevException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			 return Response.serverError().build();
		}
		
	 }
	
	
	 @Path("crear")
	 @POST
	@Consumes(MediaType.APPLICATION_JSON)
	 public Response insertar(TipoDocumento tipoDocumento) {
		
		 TipoDocumentoBDD cli=new TipoDocumentoBDD();
		 try {
			 cli.insertar(tipoDocumento);
			return Response.ok().build();
		} catch (KrakedevDevException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.serverError().build();
		}
	 }
}
