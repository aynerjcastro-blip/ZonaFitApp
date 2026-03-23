package zona_fit.datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import zona_fit.Dominio.Cliente;
import zona_fit.zonafit.conexion.Conexion;

public class ClienteDao implements IClienteDao{

    @Override
    public List<Cliente> listarClientes() {
        List<Cliente> clientes = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        Connection con = Conexion.getConexion();
        var sql = "SELECT * FROM cliente ORDER BY id";
        try {
            /*Con esto obtenemos el objeto con la sentencia SQL que queramos ejecutar(prepareStament)*/
            ps = con.prepareStatement(sql);
            /*Con esta sentencia ejecutamos la sentencia definida arriba(executeQuery) */
            rs = ps.executeQuery();
            /*Con el ciclo while iteramos los registros en caso de que existan(rs.next()) */
            while (rs.next()) {
                var cliente = new Cliente();
                /*Con esto obtenemos el primer elemento en la base de datos(cliente.setId) recuperamos y modifcamos  el (id)
                 y creamos un objeto (cliente) se aplica para los demas tambien. */
                cliente.setId(rs.getInt("id"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setMembresia(rs.getInt("membresia"));
                clientes.add(cliente);
            }
        } catch (Exception e) {
            System.out.println("Error al listar clientes: "+e.getMessage());
            e.printStackTrace();
        }
        /*LLamamos al metodo(finally) para cerrar la conexion como buena practica(close) */
        finally{
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Fallo al cerrar la conexion de la BD: "+e.getMessage());
                e.printStackTrace();
            }             
        }
        
        return clientes;
    }

    @Override
    public boolean buscarCLientePorId(Cliente cliente){
        PreparedStatement ps;
        ResultSet  rs;
        Connection con = Conexion.getConexion();
        var sql = "SELECT *FROM  cliente WHERE id = ?";
        try {
            ps = con.prepareStatement(sql);
            /*Aparte de la cadena(sql) pasamos el parametro de tipo int, con indice uno y su valor, 
            extrayendo el id del cliente */
            ps.setInt(1,cliente.getId());
            rs = ps.executeQuery();
            /*El metodo (next) sirve para saber si tenemos o no un registro para leer */
            if(rs.next()){
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setMembresia(rs.getInt("membresia"));
                return true;
            }
            
        } catch (Exception e) {
            System.out.println("Error al recuperar cliente por ID: "+e.getMessage());
            e.printStackTrace();
        }
        finally{
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar la conexion a la BD"+e.getMessage());
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean agregarCliente(Cliente cliente) {
        PreparedStatement ps; 
        Connection con = Conexion.getConexion();
        /*En la insercion de los datos solo indicamos estos tipos ya que el id posee la 
        caracterisitca de autoincrementable*/

        var sql = "INSERT INTO cliente(nombre, apellido, membresia)"+" VALUES(?, ?, ?)";
        try {
            ps = con.prepareStatement(sql);
            /*llamamos los parametros (1,2,3) y le proporcianos informarcion 
            mediante los getters*/
            ps.setString(1,cliente.getNombre());
            ps.setString(2,cliente.getApellido());
            ps.setInt(3,cliente.getMembresia());
            /*Ejecutamos la sentencia ps y retornamos true si fue exitosa*/
            ps.execute();
            return true;

        } catch (Exception e) {
            System.out.println("Error al agregar cliente: "+e.getMessage());
            e.printStackTrace();
        }
        finally{
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error al cerrrar la conexion con la BD: "+e.getMessage());
                e.printStackTrace();
            }
        }        
        return false;
        
    }

    @Override
    public boolean modificarCliente(Cliente cliente) {
        PreparedStatement ps;
        Connection con = Conexion.getConexion();
        var sql = "UPDATE cliente SET nombre=?, apellido=?, membresia=? "+" WHERE id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1,cliente.getNombre());
            ps.setString(2,cliente.getApellido());
            ps.setInt(3,cliente.getMembresia());
            ps.setInt(4, cliente.getId());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error al modificar el cliente: "+e.getMessage());
            e.printStackTrace();
        }
        finally{
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar la conexion con la BD: "+e.getMessage());
                e.printStackTrace();
            }
        }
        return false;
    }
    
    @Override
    public boolean eliminarCliente(Cliente cliente) {
        PreparedStatement ps;
        Connection con = Conexion.getConexion();
        var sql ="DELETE FROM cliente WHERE id = ? ";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1 ,cliente.getId());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error al eliminar cliente: "+e.getMessage());
            e.printStackTrace();
        }
        finally{
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar conexion con la BD: "+e.getMessage());
                e.printStackTrace();
            }
        }
        return false;    
    }

    @Override
    public boolean modificarNombre(int id, String nombre) {
        PreparedStatement ps;
        Connection con = Conexion.getConexion();
        var sql = "UPDATE cliente SET nombre=? WHERE id=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setInt(2, id);
            ps.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error al modificar el nombre del cliente: "+e.getMessage());
            e.printStackTrace();
        }
        finally{
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar la conexion con la BD: "+e.getMessage());
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean modificarApellido(int id, String apellido) {
        PreparedStatement ps;
        Connection con = Conexion.getConexion();
        var sql = "UPDATE cliente SET apellido=? WHERE id=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, apellido);
            ps.setInt(2, id);
            ps.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error al modificar el apellido del cliente: "+e.getMessage());
            e.printStackTrace();
        }
        finally{
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar la conexion con la BD: "+e.getMessage());
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean modificarMembresia(int id, int membresia) {
        PreparedStatement ps;
        Connection con = Conexion.getConexion();
        var sql = "UPDATE cliente SET membresia=? WHERE id=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, membresia);
            ps.setInt(2, id);
            ps.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error al modificar la membresia del cliente: "+e.getMessage());
            e.printStackTrace();
        }
        finally{
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar la conexion con la BD: "+e.getMessage());
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean obtenerClientePorId(Cliente cliente) {
        PreparedStatement ps;
        ResultSet rs;
        Connection con = Conexion.getConexion();
        Cliente clienteEncontrado = null;
        var sql = "SELECT * FROM cliente WHERE id=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, cliente.getId());
            rs = ps.executeQuery();
            if(rs.next()){
                clienteEncontrado = new Cliente();
                clienteEncontrado.setNombre(rs.getString("nombre"));
                clienteEncontrado.setApellido(rs.getString("apellido"));
                clienteEncontrado.setMembresia(rs.getInt("membresia"));
            }
        } catch (Exception e) {
            System.out.println("Error al obtener cliente por ID: "+e.getMessage());
            e.printStackTrace();
        }
        finally{
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar la conexion con la BD: "+e.getMessage());
                e.printStackTrace();
            }
        }
        return false;
    }
    

    }
