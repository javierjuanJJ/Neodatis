package com.mycompany.Controlador;

import com.mycompany.Modelo.Clientes;
import java.util.ArrayList;
import java.util.List;

import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

import javafx.application.Platform;

public class ClientesDAO implements GenericoDAO<Clientes> {

    protected static final String sql_select_by_PK = "SELECT * FROM empresa_ad.clientes WHERE id=?;";
    protected static final String sql_select_all = "SELECT * FROM empresa_ad.clientes;";
    protected static final String sql_UPDATE = "UPDATE `empresa_ad`.`clientes` SET `nombre`=?, `direccion`=? WHERE `id`=?;";
    protected static final String sql_INSERT = "INSERT INTO `empresa_ad`.`clientes` (`nombre`, `direccion`) VALUES (?, ?);";
    protected static final String sql_DELETE = "DELETE FROM `empresa_ad`.`clientes` WHERE `id`=?;";

    public ClientesDAO() {
        try {
            Conexion.getConnection();

        } catch (Exception e) {

            Platform.exit();
        }
    }

    public Clientes findByPK(int id) throws Exception {

        Clientes cliente_recibido = null;
        Objects<Clientes> lista = Conexion.getConnection().getObjects(Clientes.class);

        try {

            Clientes mc;
            while (lista.hasNext()) {
                mc = lista.next();

                int numero_oid = Integer.parseInt(Conexion.getConnection().getObjectId(mc).toString());

                if (numero_oid == id) {
                    cliente_recibido = new Clientes(mc);
                }

            }

        } catch (Exception e) {

        }

        return cliente_recibido;
    }

    public List<Clientes> findAll() throws Exception {
        List<Clientes> clientes_recibidos = null;
        clientes_recibidos = new ArrayList<Clientes>();

        Objects<Clientes> lista_grupos = Conexion.getConnection().getObjects(Clientes.class);

        while (lista_grupos.hasNext()) {
            clientes_recibidos.add(lista_grupos.next());
        }

        return clientes_recibidos;
    }

    public List<Clientes> findBySQL(String sqlselect) throws Exception {
        return null;
    }

    public boolean insert(Clientes t) throws Exception {

        int salida = 0;
        boolean resultado = true;
        Clientes a = new Clientes(t);
        a.setId(coger_id(findAll().size() - 1) + 1);
        Conexion.getConnection().store(a);
        Conexion.getConnection().commit();

        return resultado;
    }

    private int coger_id(int i) {
        int oid = 0;
        int c = 0;
        Objects<Clientes> lista;
        try {
            lista = Conexion.getConnection().getObjects(Clientes.class);

            Clientes mc;
            while (lista.hasNext()) {
                mc = lista.next();

                int numero_oid = Integer.parseInt(Conexion.getConnection().getObjectId(mc).toString());
                if (c == i) {
                    oid = numero_oid;
                }
                c++;
            }

        } catch (Exception e) {

        }
        return oid;
    }

    public boolean update(Clientes t) throws Exception {

        int salida = 0;
        boolean resultado = true;

        try {
            IQuery query = new CriteriaQuery(Clientes.class, Where.equal("id", t.getId()));
            Objects<Clientes> sports = Conexion.getConnection().getObjects(query);
            // Gets the first sport (there is only one!)
            Clientes volley = (Clientes) sports.getFirst();
            volley.setNombre(t.getNombre());
            volley.setDireccion(t.getDireccion());
            Conexion.getConnection().store(volley);
            Conexion.getConnection().commit();
        } catch (Exception e) {

        }
        return resultado;

    }

    public boolean delete(int id) throws Exception {
        boolean resultado = true;
        Objects<Clientes> lista = Conexion.getConnection().getObjects(Clientes.class);

        try {
            lista = Conexion.getConnection().getObjects(Clientes.class);

            Clientes mc;
            while (lista.hasNext()) {
                mc = lista.next();
                if (mc.getId() == id) {
                    Conexion.getConnection().delete(mc);
                    Conexion.getConnection().commit();
                }

            }

        } catch (Exception e) {

        }

        return resultado;

    }

}
