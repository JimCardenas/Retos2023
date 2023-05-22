package com.usergio.retos.retoapp.modelo.entidad;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

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
//  se utiliza para generar automáticamente un constructor con patrón de diseño builder,
//  lo que permite crear objetos de la clase de forma más cómoda y legible.
@Builder
// se utiliza para indicar el nombre de la tabla de la base de datos a la que está asociada la clase.
@Table(name="gama")
public class Gama implements Serializable {

    // se utiliza para indicar que el campo "idGama"
    // es el identificador único de la entidad en la base de datos.
    @Id
    // se utiliza para indicar que el valor del identificador
    // se generará automáticamente en la base de datos.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // se define una variable de tipo Long llamada "idGama"
    // que será el identificador único de la entidad de la clase.
    private Long idGama;
    //  se define una variable de tipo String llamada "name"
    //  que representará el nombre de la entidad de la clase.
    private String name;
    // se define una variable de tipo String llamada
    // "description" que representará la descripción de la entidad de la clase.
    private String description;

    // se define una relación de uno-a-muchos entre la clase actual (Gama) y otra entidad llamada Car,
    // que se mapea mediante la anotación @OneToMany. La propiedad "cascade" indica que al persistir
    // una entidad Gama, se persistirán también todas las entidades Car asociadas a ella.
    // La propiedad "mappedBy" indica que la relación se mapea mediante el campo "gama" de la entidad Car.
    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "gama")
    // se anota la variable "cars" con @JsonIgnoreProperties para que se ignore la propiedad "gama"
    // cuando se serializa la entidad a formato JSON, evitando así problemas de referencia circular.
    @JsonIgnoreProperties("gama")
    // se define una variable de tipo List<Car> llamada "cars"
    // que representará todas las entidades Car asociadas a una entidad Gama.
    private List<Car> cars;

}
