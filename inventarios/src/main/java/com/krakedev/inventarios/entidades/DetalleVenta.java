package com.krakedev.inventarios.entidades;

import java.math.BigDecimal;

public class DetalleVenta {
	private int codigo;
    private Venta cabeceraVentas;
	private Producto producto;
	private int cantidad;
	private BigDecimal precioVenta;
	private BigDecimal subtotal;
	private BigDecimal subtotalIva;
	
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public Venta getCabeceraVentas() {
		return cabeceraVentas;
	}
	public void setCabeceraVentas(Venta cabeceraVentas) {
		this.cabeceraVentas = cabeceraVentas;
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
	public BigDecimal getPrecioVenta() {
		return precioVenta;
	}
	public void setPrecioVenta(BigDecimal precioVenta) {
		this.precioVenta = precioVenta;
	}
	public BigDecimal getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}
	public BigDecimal getSubtotalIva() {
		return subtotalIva;
	}
	public void setSubtotalIva(BigDecimal subtotalIva) {
		this.subtotalIva = subtotalIva;
	}
	@Override
	public String toString() {
		return "DetalleVenta [codigo=" + codigo + ", cabeceraVentas=" + cabeceraVentas + ", producto=" + producto
				+ ", cantidad=" + cantidad + ", precioVenta=" + precioVenta + ", subtotal=" + subtotal
				+ ", subtotalIva=" + subtotalIva + "]";
	}
	
	
	
	
}
