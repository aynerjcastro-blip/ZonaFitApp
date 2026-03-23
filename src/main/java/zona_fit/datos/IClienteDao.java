package zona_fit.datos;
import zona_fit.Dominio.Cliente;
import java.util.List;

public interface IClienteDao {
    List<Cliente> listarClientes();
    boolean buscarCLientePorId(Cliente cliente);
    boolean obtenerClientePorId(Cliente cliente);
    boolean agregarCliente(Cliente cliente);
    boolean modificarCliente(Cliente cliente);
    boolean eliminarCliente(Cliente cliente);
    boolean modificarNombre(int id, String nombre);
    boolean modificarApellido(int id, String apellido);
    boolean modificarMembresia(int id, int membresia);
}
