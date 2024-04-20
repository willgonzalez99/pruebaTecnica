package com.eclub.models;

public class CompraProducto {
    private Long idProducto;
    private int cantidad;


    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
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
        return "CompraProducto{" +
                "idProducto=" + idProducto +
                ", cantidad=" + cantidad +
                '}';
    }
}
