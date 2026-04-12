package gm.zona_fit;


import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ch.qos.logback.classic.Logger;
import gm.zona_fit.service.IClienteServicio;

@SpringBootApplication
public class ZonaFitApplication  implements CommandLineRunner{
	/**
	 **Implementamos la interface porque asi inyectamos una dependencia de services a nuestra capa de presentacion.
	 */
	@Autowired
	private IClienteServicio clienteServicio;

	/*
	* Usamos (Logger) porque nos permite mandar a imprimir informacion a consola.
	* Usamos la clase Logger (LoggerFactory) porque nos ayuda a solicitar el objeto de tipo Logger, indicando el .class. 
	 */
	private static final Logger logger = (Logger) LoggerFactory.getLogger(ZonaFitApplication.class);

	public static void main(String[] args) {
		logger.info("Iniciando App...");
		// 1. Levantamos fabrica de Spring
		SpringApplication.run(ZonaFitApplication.class, args);
		logger.info("Aplicacion Finalizada");
	}
	//2. Luego ejecutamos el metodo run (master)
	@Override
	public void run(String... args) throws Exception {
		logger.info("*** Aplicacion ZonaFit (Gym) ***");
		logger.info("1. Agregar clientes.");
		logger.info("2. Listar todos los clientes.");
		logger.info("3. Modificar clientes");
		logger.info("4. Buscar Cliente.");
		logger.info("5. Eliminar cliente.");
		logger.info("6. Salir");
	}
}
