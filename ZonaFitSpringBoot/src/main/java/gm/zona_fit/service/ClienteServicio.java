package gm.zona_fit.service;

import java.util.List;
import gm.zona_fit.model.Cliente;
import org.springframework.stereotype.Service;

@Service
/*
 * *Notacion Spring (@Service).
 */
public class ClienteServicio implements IClienteServicio {

    @Override
    public List<Cliente> listarClientes() {
        return null;

    }

    @Override
    public Cliente buscarClientePorId(Cliente cliente) {
        return null;

    }

    public void guardarCliente(Cliente cliente) {
        return;
    }

    public void eliminarCLiente(Cliente cliente) {
        return;
    }
}
