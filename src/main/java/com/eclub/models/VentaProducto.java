package com.eclub.models;

public class VentaProducto {

    private long idProducto;
    private int cantidad;

    public VentaProducto() {
    }

    public long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(long idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "VentaProducto{" +
                "idProducto=" + idProducto +
                ", cantidad=" + cantidad +
                '}';
    }
}
