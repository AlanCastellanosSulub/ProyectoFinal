package application;

public class Cliente {
    private int clienteId;
    private String nombre;
    private String apellidos;
    private String direccion;

    public Cliente(int clienteId, String nombre, String apellidos, String direccion) {
        this.clienteId = clienteId;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
    }

    public int getId() {
        return clienteId;
    }

    public void setId(int clienteId) {
    	this.clienteId = clienteId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "\nCliente:\n" +
                "clienteId=" + clienteId +
                ",\n nombre='" + nombre + '\'' +
                ",\n apellidos='" + apellidos + '\'' +
                ",\n direccion='" + direccion + '\'' ;
    }
    
    
}