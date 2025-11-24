package archivet.dao.interfaces;

import archivet.dao.AppointmentDAO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author guzman-dynastie
 */
public interface IAppointmentDAO {

    // Guarda una nueva cita en la base de datos.
    boolean save(AppointmentDAO appointment) throws SQLException;

    // Modifica los datos de una cita existente (tabla APPOINTMENT).
    boolean update(AppointmentDAO appointment) throws SQLException;

    // Elimina logicamente una cita por su ID primario.
    boolean delete(int id) throws SQLException;
    
    // Activa logicamente una cita por su ID primario.
    boolean activate(int id) throws SQLException;

    // Obtiene una lista de todas las citas por ID del doctor.
    List<AppointmentDAO> findAllAppointmentsByDoctorId(int id) throws SQLException;
    
    // Obtiene una lista de todas las citas por ID del propietario.
    List<AppointmentDAO> findAllAppointmentsByOwnerId(int id) throws SQLException;

    // Obtiene una lista de todas las citas.
    List<AppointmentDAO> findAllAppointments() throws SQLException;

}
