package archivet.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author guzman-dynastie
 */
public class DBConnection {

    private static final String MACHINE = "localhost";
    private static final String PORT = "3306";
    private static final String NAME_DB = "archivet_db";
    private static final String URL = "jdbc:mysql://" + MACHINE + ":" + PORT + "/" + NAME_DB;
<<<<<<< HEAD
<<<<<<< Updated upstream
    private static final String USER = "";
    private static final String PASSWORD = "";
=======
    private static final String USER = "root";
    private static final String PASSWORD = "50p0rt3Guzm";
>>>>>>> Stashed changes
=======
    private static final String USER = "root";
    private static final String PASSWORD = "50p0rt3Guzm@n";
>>>>>>> 27bfb8a (feat: Se agregan nuevas funciones y estructuras.)

    // Objeto Connection estatico para mantener la unica conexion activa
    private static Connection connection = null;

    // Obtiene y retorna la unica instancia de la conexion a la base de datos (Singleton).
    // Este metodo implementa el patron de inicializacion perezosa (Lazy Initialization)
    // con "Double-Checked Locking" para asegurar que la creacion de la conexion
    // sea segura para hilos (thread-safe).
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            synchronized (DBConnection.class) {
                if (connection == null || connection.isClosed()) {
                    try {
                        connection = DriverManager.getConnection(URL, USER, PASSWORD);
                        System.out.println("Conexion a la DB establecida con exito");
                    } catch (SQLException e) {
                        System.out.println("ERROR: No se pudo conectar a la base de datos");
                        throw e;
                    }
                }
            }
        }
        return connection;
    }

    // Cierra la conexion a la base de datos.
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
