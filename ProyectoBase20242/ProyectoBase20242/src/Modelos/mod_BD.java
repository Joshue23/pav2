package Modelos;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.util.function.Function;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class mod_BD {

    private Connection conexion;
    private Statement sentenciaSQL;
    private ResultSet resultSet;

    public mod_BD() {
    }

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

    public Statement getSentenciaSQL() {
        return sentenciaSQL;
    }

    public void setSentenciaSQL(Statement sentenciaSQL) {
        this.sentenciaSQL = sentenciaSQL;
    }

    public ResultSet getResultSet() {
        return resultSet;
    }

    public void setResultSet(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    public boolean conectarBD() {
        int gestorBD = Modelos.mod_General.gestorBD;
        String classNombre = "";
        String cadenaConexion = "";
        // Datos para SQL Server
        String servidor = "localhost";
        String baseDatosSQL = "bd2024";
        String usuarioSQL = "usr2024";
        String claveSQL = "123456789";

        // Datos para MySQL
        String servidorMDB = "localhost";
        String baseDatosMDB = "bd2024";
        String usuarioMDB = "root";
        String claveMDB = "";

        try {
            if (gestorBD == 1) { // MySQL
                Modelos.mod_General.nombregestorBD = "MySQL";
                classNombre = "org.mariadb.jdbc.Driver";
                cadenaConexion = "jdbc:mariadb://" + servidorMDB + ":3306/" + baseDatosMDB + "?user=" + usuarioMDB + "&password=" + claveMDB;
            } else { // SQL Server
                Modelos.mod_General.nombregestorBD = "SQL Server";
                classNombre = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
                cadenaConexion = "jdbc:sqlserver://" + servidor + ":1433;database=" + baseDatosSQL + ";user=" + usuarioSQL + ";password=" + claveSQL + ";encrypt=false;loginTimeout=30";
            }
            Class.forName(classNombre);
            this.conexion = DriverManager.getConnection(cadenaConexion);
            this.conexion.setAutoCommit(false);
            return true;
        } catch (Exception e) {
            System.out.println("Error de conexión: " + e.getMessage());
            return false;
        }
    }

    public void DesconectarBD() {
        try {
            if (this.conexion != null && !this.conexion.isClosed()) {
                this.conexion.close();
            }
        } catch (Exception e) {
            System.out.println("Error al desconectar: " + e.getMessage());
        }
    }

    public void commit() {
        try {
            this.conexion.commit();
        } catch (Exception e) {
            System.out.println("Error en commit: " + e.getMessage());
        }
    }

    public void rollback() {
        try {
            this.conexion.rollback();
        } catch (Exception e) {
            System.out.println("Error en rollback: " + e.getMessage());
        }
    }

    public void ejecutarConsultaSQL(String cadenaSQL) {
        try {
            this.sentenciaSQL = this.conexion.createStatement();
            this.resultSet = this.sentenciaSQL.executeQuery(cadenaSQL);
        } catch (Exception e) {
            System.out.println("Error en consulta: " + e.getMessage());
        }
    }

    public int ejecutarSQL(String cadenaSQL, boolean controlaCommit) {
        int filas = 0;
        try {
            this.sentenciaSQL = this.conexion.createStatement();
            filas = this.sentenciaSQL.executeUpdate(cadenaSQL);
            if (controlaCommit) {
                this.commit();
            }
        } catch (Exception e) {
            System.out.println("Error en ejecución SQL: " + e.getMessage());
        }
        return filas;
    }

    public <T> ObservableList<T> getlistaConsultar(String cadenaSQL, Function<ResultSet, T> mapper) {
        ObservableList<T> ObsListAux = FXCollections.observableArrayList();
        try {
            mod_BD objBD = new mod_BD();
            objBD.conectarBD();
            objBD.ejecutarConsultaSQL(cadenaSQL);
            ResultSet rs = objBD.getResultSet();

            while (rs.next()) {
                T obj = mapper.apply(rs);
                ObsListAux.add(obj);
            }
            rs.close();
            objBD.DesconectarBD();
        } catch (Exception e) {
            System.out.println("Error en obtener lista: " + e.getMessage());
        }
        return ObsListAux;
    }

    public boolean fun_Ejetutar(String cadenaSQL) {
        try {
            mod_BD objBD = new mod_BD();
            objBD.conectarBD();
            int filas = objBD.ejecutarSQL(cadenaSQL, true);
            objBD.DesconectarBD();
            return filas > 0;
        } catch (Exception e) {
            System.out.println("Error en ejecutar función: " + e.getMessage());
            return false;
        }
    }
}
