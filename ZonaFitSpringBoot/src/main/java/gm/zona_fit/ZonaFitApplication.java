package gm.zona_fit;

import java.util.List;
import java.util.Scanner;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ch.qos.logback.classic.Logger;
import gm.zona_fit.model.Cliente;
import gm.zona_fit.service.IClienteServicio;

@SpringBootApplication
public class ZonaFitApplication implements CommandLineRunner {
	/**
	 ** Implementamos la interface porque asi inyectamos una dependencia de services
	 * a nuestra capa de presentacion.
	 */
	@Autowired
	private IClienteServicio clienteServicio;

	/*
	 * Usamos (Logger) porque nos permite mandar a imprimir informacion a consola.
	 * Usamos la clase Logger (LoggerFactory) porque nos ayuda a solicitar el objeto
	 * de tipo Logger, indicando el .class.
	 */
	private static final Logger logger = (Logger) LoggerFactory.getLogger(ZonaFitApplication.class);
	/*
	 * Usamos lineSeparator() para imprimir un salto de linea de manera generica.
	 */
	String nl = System.lineSeparator();

	public static void main(String[] args) {
		logger.info("Iniciando App...");
		// 1. Levantamos fabrica de Spring
		SpringApplication.run(ZonaFitApplication.class, args);
		logger.info("Aplicacion Finalizada");
	}

	// 2. Luego ejecutamos el metodo run (master)
	@Override
	public void run(String... args) throws Exception {

		zonaFitApp();
	}

	private void zonaFitApp() {
		var salir = false;
		var consola = new Scanner(System.in);
		do {
			var opcion = mostrarMenu(consola);
			salir = ejecutarOpciones(consola, opcion);
			logger.info(nl);
		} while (!salir);
	}

	private int mostrarMenu(Scanner sc) {
		logger.info("""
				\n*** Aplicacion ZonaFit (Gym) ***
					1. Listar clientes.
					2. Buscar cliente.
					3. Agregar cliente.
					4. Modificar cliente.
					5. Eliminar cliente.
					Elige una opcion:
					""");
		return Integer.parseInt(sc.nextLine());
	}

	private boolean ejecutarOpciones(Scanner consola, int opcion) {
		var salir = false;
		switch (opcion) {
			case 1 -> {
				logger.info(nl + "--- Listado de clientes --- " + nl);
				List<Cliente> clientes = clienteServicio.listarClientes();
				clientes.forEach(cliente -> logger.info(cliente.toString()));
			}
			case 2 -> {
				logger.info(nl + "--- Buscar Clientes ---" + nl);
				logger.info("Ingrese el id del cliente a buscar: ");
				var idCliente = Integer.parseInt(consola.nextLine());
				Cliente cliente = clienteServicio.buscarClientePorId(idCliente);
				if (cliente != null) {
					logger.info("Cliente encontrado: " + cliente + nl);
				} else {
					logger.info("Cliente NO encontrado: " + cliente + nl);
				}
			}
			case 3 -> {
				logger.info(nl + "--- Agregar estudiante ---");
				logger.info("Nombre: ");
				var nombre = consola.nextLine();

				logger.info("Apellido: ");
				var apellido = consola.nextLine();

				logger.info("Membresia: ");
				var membresia = Integer.parseInt(consola.nextLine());

				var cliente = new Cliente();
				cliente.setNombre(nombre);
				cliente.setApellido(apellido);
				cliente.setMembresia(membresia);
				clienteServicio.guardarCliente(cliente);
				logger.info("Cliente agredado exitosamente: " + cliente + nl);

			}
			case 4 -> {
				logger.info(nl + "--- Modificar clientes ---" + nl);
				logger.info("ID del cliente: ");
				var clienteId = Integer.parseInt(consola.nextLine());
				Cliente cliente = clienteServicio.buscarClientePorId(clienteId);
				if (cliente != null) {
					logger.info("Nuevo nombre: ");
					var nuevoNombre = consola.nextLine();

					logger.info("Nuevo apelllido: ");
					var nuevoApellido = consola.nextLine();

					logger.info("Nueva membresia: ");
					var nuevaMembresia = Integer.parseInt(consola.nextLine());
					cliente.setNombre(nuevoNombre);
					cliente.setApellido(nuevoApellido);
					cliente.setMembresia(nuevaMembresia);
					clienteServicio.guardarCliente(cliente);
					logger.info("CLiente modificado exitosamente: " + cliente + nl);

				} else {
					logger.info("Cliente NO encontrado: " + cliente);
				}
			}

			case 5 -> {
				logger.info(nl + "--- Eliminar clientes ---" + nl);
				logger.info("ID del cliente a eliminar: ");
				var clienteId = Integer.parseInt(consola.nextLine());
				Cliente cliente = clienteServicio.buscarClientePorId(clienteId);
				if (cliente != null) {
					clienteServicio.eliminarCLiente(cliente);
					logger.info("Cliente eliminado correctamente: " + cliente + nl);
				} else {
					logger.info("Cliento NO encontrado: " + cliente + nl);
				}
			}
			case 6 -> {
				logger.info("Hasta pronto! " + nl + nl);
				salir = true;
			}
			default -> logger.info("Opcion invalida, ingrese otra opcion: " + opcion + nl);
		}
		return salir;
	}

}
