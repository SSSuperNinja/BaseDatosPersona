package u4_basededatos;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseDatos {
   Connection conexion;
    Statement transaccion;
    ResultSet cursor;
    
    String cadenaConexion = "jdbc:mysql://b0uacqbv2uyfe6frxkxh-mysql.services.clever-cloud.com:3306/b0uacqbv2uyfe6frxkxh?zeroDateTimeBehavior=CONVERT_TO_NULL";
    String usuario = "ugapamqofmkbddar";
    String pass = "rgla4Fjlo61gLrvlQS5O";
    
    public BaseDatos(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(cadenaConexion, usuario, pass);
            transaccion = conexion.createStatement();
            
        }catch(SQLException e){
            
        } catch(ClassNotFoundException e){
            
        }
    }
    
    public boolean insertar(Persona p){
        String SQL_Insertar = "INSERT INTO `Persona` (`ID`, `NOMBRE`, `DOMICILIO`, `TELEFONO`) VALUES (NULL, '%NOM%', '%DOM%', '%TEL%');";
        
        SQL_Insertar = SQL_Insertar.replace("%NOM%", p.nombre);
        SQL_Insertar = SQL_Insertar.replace("%DOM%", p.domicilio);
        SQL_Insertar = SQL_Insertar.replace("%TEL%", p.telefono);
        
        try {
            transaccion.execute(SQL_Insertar);
        } catch (SQLException e) {
            return false;
        }
        
        return true;
    }
    public ArrayList<Persona> mostrarTodos(){
        ArrayList<Persona> datos = new ArrayList<Persona>();
        String SQL_consulta = "SELECT * FROM `Persona`";
        
       try {
           //RESULTSET = Variable que maneja las tuplas resultantes
           cursor = transaccion.executeQuery(SQL_consulta);
           if(cursor.next()){
               do{
                   Persona p = new Persona
                   (cursor.getInt(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
                   datos.add(p);
               }while(cursor.next());
           }
       } catch (SQLException ex) {
           Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
       }
       return datos;
    }
    
    public Persona obtenerPorID(String IDaBuscar){
        String SQL_consulta = "SELECT * FROM `Persona` WHERE `ID`="+IDaBuscar;
        
       try {
           //RESULTSET = Variable que maneja las tuplas resultantes
           cursor = transaccion.executeQuery(SQL_consulta);
           if(cursor.next()){
                   Persona p = new Persona
                   (cursor.getInt(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
                   return p;
           }
       } catch (SQLException ex) {
           Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
       }
       return new Persona(-1,"","","");
    }
    
    public boolean eliminar(String IDaEliminar){
       try {
           String SQL_eliminar = "DELETE FROM `Persona` WHERE `ID`= "+IDaEliminar;
           transaccion.execute(SQL_eliminar);
       } catch (SQLException ex) {
           Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
       }
       return true;
    }
    public boolean actualizar(Persona p){
        //Su codigo es similar a insertar
        String SQL_Actualizar = "UPDATE `Persona` SET `NOMBRE`='%NOM%', `DOMICILIO`='%DOM%', `TELEFONO`='%TEL%' WHERE  `ID`="+p.id;
        
        SQL_Actualizar = SQL_Actualizar.replace("%NOM%", p.nombre);
        SQL_Actualizar = SQL_Actualizar.replace("%DOM%", p.domicilio);
        SQL_Actualizar = SQL_Actualizar.replace("%TEL%", p.telefono);
        
        try {
            transaccion.executeUpdate(SQL_Actualizar);
        } catch (SQLException e) {
            return false;
        }
        
        return true;
    }
}
