package archivet.util;

import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author guzman-dynastie
 */
public class PasswordUtil {

    // Genera el hash seguro de una contraseña en texto plano.
    public static String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt(10));
    }

    // Verifica si una contraseña en texto plano coincide con el hash almacenado.
    public static boolean checkPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}
