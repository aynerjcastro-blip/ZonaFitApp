package gm.zona_fit.service;

import java.util.List;
import gm.zona_fit.model.Cliente;
import gm.zona_fit.repository.ClienteRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
/*
 * *Notacion Spring (@Service).
 */
public class ClienteServicio implements IClienteServicio {

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    /*
     * Usamos (findAll()) porque regresa todos los objetos de tipo cliente
     * en la tabla de clientes.
     */
    @Override
    public List<Cliente> listarClientes() {
        List<Cliente> clientes = clienteRepositorio.findAll();
        return clientes;
    }

    /*
     * Usamos (findById()) porque regresa un valor optional.
     * Luego usamos (orElse()) porque si no hay registros BD retorna null.
     */
    @Override
    public Cliente buscarClientePorId(Integer idCliente) {
        Cliente cliente = clienteRepositorio.findById(idCliente).orElse(null);
        return cliente;
    }

    /*
     * Usamos (save()) porque guarda el objeto de tipo cliente y internamente
     * si ID = NULL => INSERT o UPDATE.
     */

    public void guardarCliente(Cliente cliente) {
        clienteRepositorio.save(cliente);
    }

    /*
     * Usamos (delete()) porque toma el id de forma automatica y
     * si encuentra el id en la BD lo elimina.
     */
    public void eliminarCLiente(Cliente cliente) {
        clienteRepositorio.delete(cliente);
    }
}
