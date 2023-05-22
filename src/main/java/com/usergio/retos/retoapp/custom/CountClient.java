

import com.usergio.retos.retoapp.modelo.entidad.Client;
import lombok.*;

// Esta anotación es de Lombok y
// genera automáticamente un constructor que tiene argumentos para todos los campos de la clase.
@AllArgsConstructor

// Esta anotación es de Lombok y genera automáticamente un constructor sin argumentos.
@NoArgsConstructor
// Esta anotación es de Lombok y genera automáticamente el método toString() para la clase.
@ToString
//  Esta es otra anotación de Lombok que genera automáticamente los métodos equals(),
//  hashCode(), toString(), getter y setter para todos los campos de la clase.
@Data
// Esta anotación de Lombok permite construir objetos complejos con una sintaxis más legible,
// permitiendo que la clase se pueda construir con una sola línea de código y con una mayor claridad
// en la asignación de valores.
// En este caso, la clase CountClient puede construirse utilizando la sintaxis de builder().
@Builder
public class CountClient {

    // Este campo representa la cantidad total de clientes.
    private long total;

    // Este campo es un objeto Client que representa un cliente en particular.
    private Client client;
}
