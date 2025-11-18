package archivet.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author guzman-dynastie
 */
public class DBConnection {

    private static final String MACHINE = "";
    private static final String PORT = "";
    private static final String NAME_DB = "";
    private static final String URL = "jdbc:mysql://" + MACHINE + ":" + PORT + "/" + NAME_DB;
    private static final String USER = "";
    private static final String PASSWORD = "";

    // Objeto Connection estatico para mantener la unica conexion activa
    private static Connection connection = null;

    /**
     * Obtiene y retorna una unica instancia de la conexion a la bas de datos.
     * Si la conexion no existe o esta cerrada, intenta establecer una nueva.
     *
     * @return Objeto conexion activo.
     * @throws SQLException Si ocurre un error al conectar con la DB.
     */
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Conexion a la DB establecida con exito");
            } catch (SQLException e) {
                System.out.println("ERROR: No se pudo conectar a la base de datos");
                throw e;
            }
        }
        return connection;
    }

    /**
     * Cierra la conexion a la base de datos.
     */
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;      // Reinicia la conexion para permitir una reapertura.
                System.out.println("Conexion a la DB cerrada");

            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexion: " + e.getMessage());
            }
        }
    }
}
