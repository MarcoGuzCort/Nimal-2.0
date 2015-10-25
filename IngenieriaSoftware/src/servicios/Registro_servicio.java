package servicios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelos.Registro;
/*El método guardar(), que recibirá como parámetro una instancia de conexión y un objeto Registro. Si el objeto tiene el 
valor de id nulo, va a hacer un insert, de lo contrario un update en el registro con dicho id.*/

/*El método recuperarPorId(), recibirá la conexión y un id, y nos devolverá un objeto Registro correspondiente a ese id, de 
lo contrario nos devolverá un valor null, en caso de que no encuentre ningún registro con ese id.*/

/*El método eliminar() recibirá un una instancia de conexión y el objeto Registro que debe eliminar.*/

/*recuperarTodas() también recibirá una instancia de conexión y nos devolverá una lista con todos los registros.*/

public class Registro_servicio {
   private final String tabla = "tareas";
   public void guardar(Connection conexion, Registro registro) throws SQLException{
      try{
         PreparedStatement consulta;
         if(registro.getId_registro() == null){
            consulta = conexion.prepareStatement("INSERT INTO " + this.tabla + "(nombre, fecha, medida) VALUES(?, ?, ?)");
            consulta.setString(1, registro.getNombre());
            consulta.setString(2, registro.getFecha());
            consulta.setString(3, registro.getMedida());
         }else{
            consulta = conexion.prepareStatement("UPDATE " + this.tabla + " SET nombre = ?, fecha = ?, medida = ? WHERE id_registro = ?");
            consulta.setString(1, registro.getNombre());
            consulta.setString(2, registro.getFecha());
            consulta.setString(3, registro.getMedida());
            consulta.setInt(4, registro.getId_registro());
         }
         consulta.executeUpdate();
      }catch(SQLException ex){
         throw new SQLException(ex);
      }
   }
   public Registro recuperarPorId(Connection conexion, int id_registro) throws SQLException {
      Registro registro = null;
      try{
         PreparedStatement consulta = conexion.prepareStatement("SELECT nombre, fecha, medida FROM " + this.tabla + " WHERE id_registro = ?" );
         consulta.setInt(1, id_registro);
         ResultSet resultado = consulta.executeQuery();
         while(resultado.next()){
            registro = new Registro(id_registro, resultado.getString("nombre"), resultado.getString("fecha"), resultado.getString("medida"));
         }
      }catch(SQLException ex){
         throw new SQLException(ex);
      }
      return registro;
   }
   public void eliminar(Connection conexion, Registro registro) throws SQLException{
      try{
         PreparedStatement consulta = conexion.prepareStatement("DELETE FROM " + this.tabla + " WHERE id_registro = ?");
         consulta.setInt(1, registro.getId_registro());
         consulta.executeUpdate();
      }catch(SQLException ex){
         throw new SQLException(ex);
      }
   }
   public List<Registro> recuperarTodas(Connection conexion) throws SQLException{
      List<Registro> registros = new ArrayList<>();
      try{
         PreparedStatement consulta = conexion.prepareStatement("SELECT id_registro, nombre, fecha, medida FROM " + this.tabla + " ORDER BY id_registro");
         ResultSet resultado = consulta.executeQuery();
         while(resultado.next()){
            registros.add(new Registro(resultado.getInt("id_registro"), resultado.getString("nombre"), resultado.getString("fecha"), resultado.getString("medida")));
         }
      }catch(SQLException ex){
         throw new SQLException(ex);
      }
      return registros;
   }
}
