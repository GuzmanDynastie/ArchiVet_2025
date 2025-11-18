package archivet;

import archivet.config.DBConnection;
import archivet.dao.UserDAO;
import archivet.model.AppointmentDTO;
import archivet.model.OwnerDTO;
import archivet.model.UserDTO;
import archivet.model.VetDoctorDTO;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 *
 * @author guzman-dynastie
 */
public class ArchiVet {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        DBConnection.getConnection();
        AppointmentDTO appointment = new AppointmentDTO(1, 1, LocalDateTime.now(), "Se anda cagando el perro", AppointmentDTO.StatusEnum.SCHEDULED, "JAJAJA");

        OwnerDTO userDTO = new OwnerDTO("Guadalajara, Jalisco.", "Emmanuel", "Guzman", "hola@universidad.com", "3344334433", UserDTO.SexEnum.MALE);
        VetDoctorDTO vetDoctorDTO = new VetDoctorDTO("12345", "Mata perros", "7:00 - 13:00 Lunes a Viernes", "Mario", "Nieves", "mario@gmail.com", "HolaMundo", "3366633666", UserDTO.SexEnum.MALE);
        UserDAO user = new UserDAO();
        user.save(userDTO);
        user.save(vetDoctorDTO);
        
        
        System.out.println(user.findAllOwners());
        System.out.println(user.findAllDoctors());
        System.out.println(user.findById(2));

        System.out.println(appointment);

        DBConnection.closeConnection();
    }

}
