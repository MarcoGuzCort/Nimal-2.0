package servicios;
import java.sql.*;
//getConnection() primer parametro localhost, segundo parametro usuario y tercero contraseña
//obtener() va a devolvernos una instancia de conexión y de no haber una previa va crear una; cnx = DriverManager.getConnection("jdbc:mysql://localhost/java_mysql", "root", "");
//cerrar() como su nombre lo indica, cerrará la conexión, en caso de que haya alguna abierta.
public class Conexion {
   private static Connection cnx = null;
   public static Connection obtener() throws SQLException, ClassNotFoundException {
      if (cnx == null) {
         try {
            Class.forName("com.mysql.jdbc.Driver");
            cnx = DriverManager.getConnection("jdbc:mysql://localhost/java_mysql", "root", "");
         } catch (SQLException ex) {
            throw new SQLException(ex);
         } catch (ClassNotFoundException ex) {
            throw new ClassCastException(ex.getMessage());
         }
      }
      return cnx;
   }
   public static void cerrar() throws SQLException {
      if (cnx != null) {
         cnx.close();
      }
   }
}
