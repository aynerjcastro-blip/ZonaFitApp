package zona_fit.presentacion;
import java.util.Scanner;

import zona_fit.Dominio.Cliente;
import zona_fit.datos.ClienteDao;
import zona_fit.datos.IClienteDao; 

public class ZonaFitApp {
    public static void main(String[] args) {
        zonaFitApp();
    }

    private static void zonaFitApp() 
    {
        var salir = false;
        var consola = new Scanner(System.in);
        //Creamos un objeto de la clase IClienteDao
         IClienteDao clienteDao = new ClienteDao();
        while (!salir) {
            try {
                
                var opcion = mostrarMenu(consola);
                salir= ejecutarOpciones(consola,opcion,clienteDao);
            } catch (Exception e) {
                System.out.println("Error al ejecutar las opciones: "+e.getMessage());
                e.printStackTrace();
            }
            System.out.println();
        }
    }

    private static int mostrarMenu(Scanner consola){
        System.out.print("""
                **ZonaFit**
                1. Listar clientes.
                2. Agregar clientes.
                3. Modificar clientes.
                4. Eliminar clientes.
                5. Salir.
                Seleccione una opcion:\s""");
                return Integer.parseInt(consola.nextLine());
    }

    private static boolean ejecutarOpciones(Scanner consola, int opcion,   IClienteDao clienteDao){
        var salir = false;

        switch (opcion) {
            case 1 -> {
                System.out.println("---Listado de Clientes---"); 
                    var clientes = clienteDao.listarClientes();
                    clientes.forEach(System.out::println);
            }
            case 2 ->{
                System.out.println("---Buscar cliente por ID---");
                System.out.println("Introduce el ID del cliente a buscar: ");
                var idCliente = Integer.parseInt(consola.nextLine());
                var cliente = new Cliente(idCliente);
                var encontrado= clienteDao.buscarCLientePorId(cliente);
                if(encontrado){
                    System.out.println("Cliente encontrado; "+cliente);
                }else{
                    System.out.println("Cliente no encontrado; "+cliente);
                }
                
            } 
            case 3 ->{
                System.out.println("--- Agregar cliente ---");
                System.out.print("Nombre:");
                var nombre = consola.nextLine();
                System.out.println("Apellido: ");
                var apellido = consola.nextLine();
                System.out.println("Membresia: ");
                var membresia = Integer.parseInt(consola.nextLine());
                //Creamos el objketo cliente sin  Id porque es insercion.
                var cliente = new Cliente(nombre,apellido,membresia);
                var agregado = clienteDao.agregarCliente(cliente);
                if(agregado){
                    System.out.println("Cliente agregado: "+cliente);
                }else{
                    System.out.println("Cliente no agregado: "+cliente);
                }
            }
            case 4 -> {
            System.out.println("--- Modificar cliente ---");
            System.out.println("Id Cliente: ");
            var idCliente = Integer.parseInt(consola.nextLine());
            System.out.print("Nuevo nombre: ");
            var nombreNuevo = consola.nextLine();
            System.out.print("Nuevo Apellido: ");
            var apellidoNuevo = consola.nextLine();
            System.out.print("Nueva membresia: "); 
            var membresiaNueva = Integer.parseInt(consola.nextLine());
            var cliente = new Cliente(idCliente,nombreNuevo,apellidoNuevo,membresiaNueva);
            var clienteModificado = clienteDao.modificarCliente(cliente);
            if(clienteModificado){
                System.out.println("Cliente modificado: "+cliente);
            }
            else{
                System.out.println("Cliente no modificado: "+cliente);
                }
            }

            case 5 ->{
                System.out.println("--- Eliminar cliente ---");
                System.out.println("Id Cliente a eliminar: ");
                var idClienteBorrar = Integer.parseInt(consola.nextLine());
                var cliente = new Cliente(idClienteBorrar);
                var eliminado = clienteDao.eliminarCliente(cliente);
                if(eliminado){
                    System.out.println("Cliente borrado: "+cliente);
                }
                else{
                    System.out.println("Cliente no encontrado: "+cliente);
                }
            }
            case 6->{
                System.out.println("Hasta Pronto");
                salir=true;
            }
            default-> System.out.println("Opcion no reconocida: "+opcion);
        }
        
        return salir;
    }

}
