package archivet.dao;

import archivet.model.AppointmentDTO;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author guzman-dynastie
 */
public interface IAppointmentDAO {

    /**
     * Guarda una nueva cita en la base de datos. Al finalizar, el objeto
     * AppointmentDTO en memoria se actualiza con el ID autogenerado.
     *
     * * @param appointment El objeto AppointmentDTO a guardar (sin ID).
     * @return true si la inserción fue exitosa.
     * @throws SQLException Si ocurre un error durante la operación de base de
     * datos (ej. la clave foránea petId o vetDoctorId no existe).
     */
    boolean save(AppointmentDTO appointment) throws SQLException;

    /**
     * Recupera una lista de citas programadas para un doctor específico en una
     * fecha dada. Se utiliza para verificar la disponibilidad en la agenda.
     *
     * * @param vetDoctorId El ID del médico veterinario.
     * @param date La fecha específica a consultar (LocalDate).
     * @return Lista de AppointmentDTOs encontrados para esa fecha/doctor.
     * @throws SQLException Si ocurre un error durante la consulta.
     */
    List<AppointmentDTO> findByDoctorAndDate(int vetDoctorId, LocalDate date) throws SQLException;

    /**
     * Recupera todas las citas registradas para una mascota específica,
     * ordenadas por fecha descendente.
     *
     * * @param petId El ID de la mascota cuyo historial de citas se desea
     * obtener.
     * @return Lista de AppointmentDTOs asociados a la mascota.
     * @throws SQLException Si ocurre un error durante la consulta.
     */
    List<AppointmentDTO> findByPetId(int petId) throws SQLException;

    /**
     * Actualiza todos los campos de una cita existente. El objeto
     * AppointmentDTO debe contener un ID válido.
     *
     * * @param appointment El objeto AppointmentDTO con los nuevos datos.
     * @return true si la actualización fue exitosa (se modificó una fila).
     * @throws SQLException Si ocurre un error durante la operación de base de
     * datos.
     */
    boolean update(AppointmentDTO appointment) throws SQLException;

    /**
     * Elimina una cita de la base de datos utilizando su ID primario.
     *
     * * @param id El ID (appointmentId) de la cita a eliminar.
     * @return true si la eliminación fue exitosa (se borró una fila).
     * @throws SQLException Si ocurre un error durante la operación de base de
     * datos.
     */
    boolean delete(int id) throws SQLException;

    /**
     * Recupera todas las citas registradas en el sistema.
     *
     * * @return Una lista de todos los AppointmentDTOs.
     * @throws SQLException Si ocurre un error durante la consulta.
     */
    List<AppointmentDTO> findAll() throws SQLException;

}
