package modelo;

import java.io.*;
import java.util.*;

public class Inventario {
    private List<Producto> productos = new ArrayList<>();
    private final String archivo = "productos.dat"; 

    // Constructor: declara excepciones
    public Inventario() throws IOException, ClassNotFoundException {
        cargarProductos();
    }

    // Agregar producto y guardar automáticamente
    public void agregarProducto(Producto p) throws IOException {
        productos.add(p);
        guardarProductos();
    }

    // lista de productos
    public List<Producto> getProductos() {
        return productos;
    }

    // Guardar productos en archivo (sin try-catch)
    private void guardarProductos() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo));
        oos.writeObject(productos);
        oos.close();
    }

    // Cargar productos desde archivo (sin try-catch)
    private void cargarProductos() throws IOException, ClassNotFoundException {
        File archivoProductos = new File(archivo);
        if (archivoProductos.exists()) {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo));
            productos = (List<Producto>) ois.readObject();
            ois.close();
        }
    }
}
