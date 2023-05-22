package com.usergio.retos.retoapp.modelo.entidad;

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
// esta anotación indica que esta clase de entidad se mapeará a
// una tabla llamada "client" en la base de datos.
    @Table(name="client")
//  define la clase Client que implementa la interfaz Serializable.
    public class Client implements Serializable {
    // esta anotación indica que el atributo que la sigue es la clave primaria de la entidad.
        @Id
        //  esta anotación indica que la generación de valores de clave primaria
        //  se realizará mediante una estrategia de identidad.
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        // atributo que representa la clave primaria de la tabla.
        private Long idClient;
        // atributo que representa el correo electrónico del cliente.
        private String email;
        // atributo que representa la contraseña del cliente.
        private String password;
        // atributo que representa el nombre del cliente.
        private String name;
        // atributo que representa la edad del cliente.
        private int age;

        //esta anotación indica que la relación con la tabla
        // Message es de uno a muchos y se mapea por el atributo client de la entidad Message.
    @OneToMany(cascade = {CascadeType.PERSIST},mappedBy = "client")
    // esta anotación indica que se deben ignorar las propiedades client de la entidad
    // Message en la serialización a JSON para evitar una referencia circular.
    @JsonIgnoreProperties("client")
    // atributo que representa la lista de mensajes enviados por el cliente.
    private List<Message> messages;

    // esta anotación indica que la relación con la tabla Reservation
    // es de uno a muchos y se mapea por el atributo client de la entidad Reservation.
    @OneToMany(cascade = {CascadeType.PERSIST},mappedBy = "client")
    // esta anotación indica que se deben ignorar las propiedades client de la entidad
    // Reservation en la serialización a JSON para evitar una referencia circular.
    @JsonIgnoreProperties("client")
    // atributo que representa la lista de reservas realizadas por el cliente.
    private List<Reservation> reservations;
}
