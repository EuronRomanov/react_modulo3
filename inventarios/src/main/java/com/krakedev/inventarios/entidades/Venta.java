package com.krakedev.inventarios.entidades;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

public class Venta {
	private int codigo;
	private Date fecha;
	private BigDecimal total_sin_iva ;
	private BigDecimal iva;
	private BigDecimal total;
	private ArrayList<DetalleVenta> detalles;
	
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
	public BigDecimal getTotal_sin_iva() {
		return total_sin_iva;
	}
	public void setTotal_sin_iva(BigDecimal total_sin_iva) {
		this.total_sin_iva = total_sin_iva;
	}
	public BigDecimal getIva() {
		return iva;
	}
	public void setIva(BigDecimal iva) {
		this.iva = iva;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public ArrayList<DetalleVenta> getDetalles() {
		return detalles;
	}
	public void setDetalles(ArrayList<DetalleVenta> detalles) {
		this.detalles = detalles;
	}
	@Override
	public String toString() {
		return "Venta [codigo=" + codigo + ", fecha=" + fecha + ", total_sin_iva=" + total_sin_iva + ", iva=" + iva
				+ ", total=" + total + ", detalles=" + detalles + "]";
	}
	
	
}
