package com.krakedev.inventarios.entidades;

import java.util.Date;

public class HistorialStock {
	private int codigo;
	private Date fecha;
	private String reference;
	private Producto producto;
	private int cantidad;
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	@Override
	public String toString() {
		return "HistorialStock [codigo=" + codigo + ", fecha=" + fecha + ", reference=" + reference + ", producto="
				+ producto + ", cantidad=" + cantidad + "]";
	}
	
	
}
