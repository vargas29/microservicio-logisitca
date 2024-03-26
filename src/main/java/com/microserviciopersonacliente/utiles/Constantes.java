package com.microserviciopersonacliente.utiles;

public final class Constantes {

    // Constantes relacionadas con la validación de productos
    public static final int MAX_LONGITUD_ID_PRODUCTO = 10;
    public static final int MAX_LONGITUD_NOMBRE_PRODUCTO = 100;
    public static final int MAX_LONGITUD_DESCRIPCION_PRODUCTO = 255;

    // Constantes relacionadas con la validación de clientes
    public static final int MAX_LONGITUD_NOMBRE_CLIENTE = 50;
    public static final int MAX_LONGITUD_DIRECCION_CLIENTE = 100;
    public static final int MAX_LONGITUD_CIUDAD_CLIENTE = 50;
    public static final int MAX_LONGITUD_PAIS_CLIENTE = 50;
    public static final int MAX_LONGITUD_TELEFONO_CLIENTE = 15;
    public static final int MAX_LONGITUD_EMAIL_CLIENTE = 100;

    // Otros tipos de constantes
    public static final double DESCUENTO_PRODUCTOS_SUPERIOR_10_UNIDADES = 0.05;
    public static final String FORMATO_PLACA_VEHICULO = "[A-Z]{3}\\d{3}";
    public static final String FORMATO_NUMERO_GUIA = "\\w{10}";

    public static final String NOT_FOUND_PRODUCTO = "No se encontro el producto: ";
    public static final String ERROR_FOUND_PRODUCTO = "No se debe especificar el ID al crear un nuevo producto.";
    public static final String NOT_FOUND_CUSTOMER= "No se encontró ningún cliente con ID.";

    // Evita que se instancie la clase Constantes
    private Constantes() {
        throw new AssertionError("No se permite instanciar la clase Constantes");
    }
}
