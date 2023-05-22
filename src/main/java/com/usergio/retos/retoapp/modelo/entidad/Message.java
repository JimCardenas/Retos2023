    package com.usergio.retos.retoapp.modelo.entidad;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

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
@Table(name="message") // Nombre de la tabla en la base de datos
public class Message implements Serializable {
    @Id // Identificador único de la entidad
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Generación automática del identificador
    private Long idMessage; // Variable que representa el identificador único de la entidad
    private String messageText; // Variable que representa el texto del mensaje
    @ManyToOne // Relación muchos-a-uno con la entidad Car
    @JoinColumn(name = "carId") // Nombre de la columna que representa la relación con la entidad Car
    @JsonIgnoreProperties({"messages","reservations"}) // Propiedades a ignorar al serializar a formato JSON
    private Car car; // Variable que representa la entidad Car asociada al mensaje
    @ManyToOne // Relación muchos-a-uno con la entidad Client
    @JoinColumn(name = "clientId") // Nombre de la columna que representa la relación con la entidad Client
    @JsonIgnoreProperties({"messages","reservations"}) // Propiedades a ignorar al serializar a formato JSON
    private Client client; // Variable que representa la entidad Client asociada al mensaje

}
