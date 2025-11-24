package archivet.service.interfaces;

import archivet.model.AppointmentDTO;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author guzman-dynastie
 */
public interface IAppointmentService {

    // Guarda una nueva cita en la base de datos.
    boolean save(AppointmentDTO appointment) throws SQLException;

    // Recupera una lista de citas programadas para un doctor específico en una fecha dada.
    List<AppointmentDTO> findByDoctorAndDate(int vetDoctorId, LocalDate date) throws SQLException;

    // Recupera todas las citas registradas para una mascota específica, ordenadas por fecha descendente.
    List<AppointmentDTO> findByPetId(int petId) throws SQLException;

    // Actualiza todos los campos de una cita existente.
    boolean update(AppointmentDTO appointment) throws SQLException;

    // Elimina logicamente una cita de la base de datos utilizando su ID primario.
    boolean softDelete(int id) throws SQLException;

    // Recupera todas las citas registradas en el sistema.
    List<AppointmentDTO> findAll() throws SQLException;

}
