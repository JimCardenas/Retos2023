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
@Table(name="score") // Nombre de la tabla en la base de datos
public class Score implements Serializable {
    @Id // Identificador único de la entidad
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Generación automática del identificador
    private Long idScore; // Variable que representa el identificador único de la entidad
    private String messageText; // Variable que representa el texto del mensaje de la calificación
    private int stars; // Variable que representa la cantidad de estrellas de la calificación
    @OneToOne // Relación uno-a-uno con la entidad Reservation
    @JsonIgnoreProperties("score") // Propiedades a ignorar al serializar a formato JSON
    private Reservation reservation; // Variable que representa la entidad
    // Reservation asociada a la calificación
    }

