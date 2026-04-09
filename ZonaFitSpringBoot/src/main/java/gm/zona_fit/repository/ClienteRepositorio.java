
package gm.zona_fit.repository;
import gm.zona_fit.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * *Extendemos de la clase JpaRepository porque nos regresa un objeto 
 * * Y su primraryKey por lo que la clase ClienteRepositorio es participe
 * * de la fabrica Spring.
 * 
 */
public interface ClienteRepositorio extends JpaRepository<Cliente,Integer>{
    
}
