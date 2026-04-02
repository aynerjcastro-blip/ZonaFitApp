package gm.zona_fit.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode


/**
 * *Usaremos objetos para evitar primitivos y obtener valores NULL en caso de ausencia de valor.
 * *Definimos la strategy para generar la primariKey(IDENTITY) .
 * *(@DATA)= genera metodos set y get a cualquier clase por lombok.
 * *@NoArgsConstructor: agregar el constructor vacio.
 * *@AllArgsConstructo: agregar constructor con todos los atributos.
 * *@ToString: genera dicho metodo.
 * *@EqualsAndHashCode : genera dichos metodos.
 */

public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    private Integer id;
    private String nombre;
    private String apellido;
    private Integer membresia;

}
