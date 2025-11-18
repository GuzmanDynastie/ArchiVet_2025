package archivet.util;

import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author guzman-dynastie
 */
public class PasswordUtil {

    /**
     * Genera el hash seguro de una contraseña en texto plano.
     *
     * @param plainPassword El password sin cifrar.
     * @return El password hasheado (String).
     */
    public static String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt(10));
    }

    /**
     * Verifica si una contraseña en texto plano coincide con el hash
     * almacenado.
     *
     * @param plainPassword El password ingresado por el usuario.
     * @param hashedPassword El hash almacenado en la DB.
     * @return true si la contraseña es correcta.
     */
    public static boolean checkPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}
