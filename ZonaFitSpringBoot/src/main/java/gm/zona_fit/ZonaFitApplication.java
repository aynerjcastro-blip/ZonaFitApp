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
	/*
	 *	Usamos lineSeparator() para imprimir un salto de linea de manera generica.	
	 */
	String nl = System.lineSeparator();
	public static void main(String[] args) {
		logger.info("Iniciando App...");
		// 1. Levantamos fabrica de Spring
		SpringApplication.run(ZonaFitApplication.class, args);
		logger.info("Aplicacion Finalizada");
	}
	//2. Luego ejecutamos el metodo run (master)
	@Override
	public void run(String... args) throws Exception {
		
		zonaFitApp();
	}
	private void zonaFitApp(){
		var salir = false;
		var consola = new Scanner(System.in);
		do {
			var opcion = mostrarMenu(consola);
			salir = ejecutarOpciones(consola,opcion);
			logger.info(nl);
		} while (!salir);
	}
	private int mostrarMenu(Scanner sc){
		logger.info("""
			\n*** Aplicacion ZonaFit (Gym) ***
				1. Listar clientes.
				2. Buscar cliente.
				3. Agregar cliente.
				4. Modificar cliente.
				5. Eliminar cliente.
				T¿Elige una opcion:
				\s""");
				return Integer.parseInt(sc.nextLine());
	}	

	private boolean ejecutarOpciones(Scanner consola, int opcion){
		return false;
	}
}
