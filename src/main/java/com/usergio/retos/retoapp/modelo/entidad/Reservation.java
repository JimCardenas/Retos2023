package com.usergio.retos.retoapp.modelo.entidad;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;


//  Esta anotación se utiliza para generar un constructor que acepta todos los argumentos de la clase.
@AllArgsConstructor
// Esta anotación se utiliza para generar un constructor sin argumentos de la clase.
@NoArgsConstructor
// Esta anotación se utiliza para generar un método toString() de la clase.
@ToString
// Esta anotación es una combinación de otras anotaciones, como @Getter, @Setter, @EqualsAndHashCode,
// @ToString y @RequiredArgsConstructor,
// y se utiliza para generar automáticamente métodos comunes de la clase, como los getters y setters.
@Data
// Esta anotación indica que la clase es una entidad que se mapea a una tabla en la base de datos.
@Entity
@Table(name="reservation") // Nombre de la tabla en la base de datos
public class Reservation implements Serializable {
    @Id // Identificador único de la entidad
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Generación automática del identificador
    private Long idReservation; // Variable que representa el identificador único de la entidad
    private Date startDate; // Variable que representa la fecha de inicio de la reserva
    private Date devolutionDate; // Variable que representa la fecha de devolución de la reserva
    private String status = "created"; // Variable que representa el estado de la reserva y
    // su valor predeterminado
    @ManyToOne // Relación muchos-a-uno con la entidad Car
    @JoinColumn(name="carId") // Nombre de la columna que representa la relación con la entidad Car
    @JsonIgnoreProperties("reservations") // Propiedades a ignorar al serializar a formato JSON
    private Car car; // Variable que representa la entidad Car asociada a la reserva
    @ManyToOne // Relación muchos-a-uno con la entidad Client
    @JoinColumn(name="clientId") // Nombre de la columna que representa la relación con la entidad Client
    @JsonIgnoreProperties({"reservations","messages"}) // Propiedades a ignorar al serializar a formato JSON
    private Client client; // Variable que representa la entidad Client asociada a la reserva
    @OneToOne(cascade = {CascadeType.PERSIST},mappedBy = "reservation") // Relación uno-a-uno
    // con la entidad Score y su mapeo
    @JsonIgnoreProperties("reservation") // Propiedades a ignorar al serializar a formato JSON
    private Score score; // Variable que representa la entidad Score asociada a la reserva
}
