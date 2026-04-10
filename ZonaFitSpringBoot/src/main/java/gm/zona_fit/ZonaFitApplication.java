package gm.zona_fit;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import gm.zona_fit.service.IClienteServicio;

@SpringBootApplication
public class ZonaFitApplication {
	/**
	 **Implementamos la interface porque asi inyectamos una dependencia de services a nuestra capa de presentacion.
	 */
	@Autowired
	private IClienteServicio clienteServicio;

	

	public static void main(String[] args) {
		SpringApplication.run(ZonaFitApplication.class, args);

	}

}
