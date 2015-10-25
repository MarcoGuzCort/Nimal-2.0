package modelos;
public class Registro {
   private final Integer id_registro;
   private String nombre;
   //hacer clase Fecha despues
   private String fecha;
   //medida puede estar en int y agregar a la Base de Datos el "ml" despues
   private String medida;
   public Registro() {
      this.id_registro = null;
      this.nombre = null;
      this.fecha = null;
      this.medida = null;
   }
   public Registro(Integer id_registro, String nombre, String fecha, String medida) {
      this.id_registro = id_registro;
      this.nombre = nombre;
      this.fecha = fecha;
      this.medida = medida;
   }
   public Integer getId_registro() {
      return id_registro;
   }
   public String getNombre() {
      return nombre;
   }
   public String getFecha() {
      return fecha;
   }
   public String getMedida() {
      return medida;
   }
   public void setNombre(String nombre) {
      this.nombre = nombre;
   }
   public void setFecha(String fecha) {
      this.fecha = fecha;
   }
   public void setMedida(String medida) {
      this.medida = medida;
   }
   @Override
   public String toString() {
      return "Tarea{" + "id_registro=" + id_registro + ", nombre o descripcion=" + nombre + ", fecha AA/MM/DD=" + fecha + ", medida del medicamento=" + medida+ '}';
   } 
}