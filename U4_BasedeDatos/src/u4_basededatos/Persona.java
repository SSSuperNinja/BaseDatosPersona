package u4_basededatos;
public class Persona {
    int id;
    String nombre;
    String domicilio;
    String telefono;
    
    public Persona(int i, String n, String d, String t){
        id = i;
        nombre = n;
        domicilio = d;
        telefono = t;
    }
    
    public String[] toRenglon(){
        String[] vector = new String[4];
        vector[0]=""+id;
        vector[1]=nombre;
        vector[2]=domicilio;
        vector[3]=telefono;
        
        return vector;
    }
}
