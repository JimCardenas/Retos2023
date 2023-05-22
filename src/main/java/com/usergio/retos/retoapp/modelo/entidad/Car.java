

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

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
// Esta anotación se utiliza para indicar el nombre de la tabla correspondiente en la base de datos.
@Table(name="car")
public class Car implements Serializable {

    // Esta anotación se utiliza para marcar el campo que actúa como clave primaria en la tabla.
    @Id
    //  Esta anotación se utiliza para indicar la estrategia de generación de valores para la clave primaria.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Este campo representa la clave primaria de la tabla.
    private Long idCar;
    // Este campo representa el nombre del automóvil.
    private String name;
    // Este campo representa la marca del automóvil.
    private String brand;
    // Este campo representa el año de fabricación del automóvil.
    private Integer year;
    // Este campo representa la descripción del automóvil.
    private String description;

    // Esta anotación se utiliza para indicar que la relación es de muchos a uno.
    @ManyToOne
    // Esta anotación se utiliza para indicar el nombre de la columna que actúa como clave foránea en la tabla.
    @JoinColumn(name="gamaId")
    // Esta anotación se utiliza para evitar la recursión infinita al serializar/deserializar
    // objetos JSON, ignorando la propiedad "cars" de la entidad "Gama".
    @JsonIgnoreProperties("cars")
    // Este campo representa la relación de muchos a uno con la entidad "Gama".
    private Gama gama;

    // Esta anotación se utiliza para indicar que la relación es de uno a muchos.
    @OneToMany(cascade = {CascadeType.PERSIST},mappedBy = "car")
    // Esta anotación se utiliza para evitar la recursión infinita al serializar/deserializar objetos JSON,
    // ignorando las propiedades "car" y "client" de la entidad "Message".
    @JsonIgnoreProperties({"car","client"})
    // Este campo representa la relación de uno a muchos con la entidad "Message".
    private List<Message> messages;

    @OneToMany(cascade = {CascadeType.PERSIST},mappedBy = "car")
    // Esta anotación se utiliza para evitar la recursión infinita al serializar/deserializar objetos JSON,
    // ignorando las propiedades "car" y "messages" de la entidad "Reservation".
    @JsonIgnoreProperties({"car","messages"})
    // Este campo representa la relación de uno a muchos con la entidad "Reservation".
    private List<Reservation> reservations;
}

