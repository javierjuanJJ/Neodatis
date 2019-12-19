package com.mycompany.Controlador;

import com.mycompany.Modelo.Articulos;
import com.mycompany.Modelo.Grupos;
import java.util.ArrayList;
import java.util.List;

import org.neodatis.odb.OID;
import org.neodatis.odb.Objects;

import javafx.application.Platform;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

public class ArticulosDAO implements GenericoDAO<Articulos> {

    private static final String sql_select_by_PK = "SELECT * FROM empresa_ad.articulos WHERE id=?;";
    private static final String sql_select_by_PK_grupos = "SELECT * FROM empresa_ad.grupos WHERE id=?;";
    private static final String sql_select_all = "SELECT * FROM empresa_ad.articulos;";
    private static final String sql_select_all_grupos = "SELECT * FROM empresa_ad.grupos;";
    private static final String sql_UPDATE = "UPDATE `empresa_ad`.`articulos` SET `nombre`=?, `precio`=?, `codigo`=?, `grupo`=? WHERE `id`=?;";
    private static final String sql_INSERT = "INSERT INTO `empresa_ad`.`articulos` (`nombre`, `precio`, `codigo`, `grupo`) VALUES (?, ?, ?, ?);";
    private static final String sql_DELETE = "DELETE FROM `empresa_ad`.`articulos` WHERE `id`=?;";
    private static final String sql_INSERT_GRUPO = "INSERT INTO `empresa_ad`.`grupos` (`descripcion`) VALUES (?);";

    public ArticulosDAO() {
        try {
            Conexion.getConnection();

        } catch (Exception e) {

            Platform.exit();
        }
    }

    public Articulos findByPK(int id_articulo) throws Exception {
        Articulos articulo_recibido = null;

        Objects<Articulos> lista = Conexion.getConnection().getObjects(Articulos.class);

        try {

            Articulos mc;
            while (lista.hasNext()) {
                mc = lista.next();

                int numero_oid = Integer.parseInt(Conexion.getConnection().getObjectId(mc).toString());

                if (numero_oid == id_articulo) {
                    articulo_recibido = new Articulos(mc);
                }

            }

        } catch (Exception e) {

        }

        return articulo_recibido;
    }

    public Grupos findByPK_grupos(int id_grupo) throws Exception {
        Grupos articulo_recibido = null;

        Objects<Grupos> lista = Conexion.getConnection().getObjects(Grupos.class);

        try {

            Grupos mc;
            while (lista.hasNext()) {
                mc = lista.next();

                int numero_oid = Integer.parseInt(Conexion.getConnection().getObjectId(mc).toString());

                if (numero_oid == id_grupo) {
                    articulo_recibido = new Grupos(mc);
                }

            }

        } catch (Exception e) {

        }

        return articulo_recibido;
    }

    public List<Articulos> findAll() throws Exception {
        List<Articulos> articulos_recibidos = new ArrayList();

        Objects<Articulos> lista_grupos = Conexion.getConnection().getObjects(Articulos.class);

        while (lista_grupos.hasNext()) {
            articulos_recibidos.add(lista_grupos.next());
        }

        return articulos_recibidos;
    }

    public List<Grupos> findAll_grupos() throws Exception {
        List<Grupos> grupos_recibidos = new ArrayList<Grupos>();
        Objects<Grupos> lista_grupos = Conexion.getConnection().getObjects(Grupos.class);

        while (lista_grupos.hasNext()) {
            grupos_recibidos.add(lista_grupos.next());
        }
        return grupos_recibidos;
    }

    public List<Articulos> findBySQL(String sqlselect) throws Exception {
        return null;
    }

    public boolean insert(Articulos t) throws Exception {
        boolean resultado = true;
        Articulos a = new Articulos(t);
        a.setId(coger_id(findAll().size() - 1));
        Conexion.getConnection().store(a);
        Conexion.getConnection().commit();

        return resultado;
    }

    private int coger_id(int i) {
        int oid = 0;
        int c = 0;
        Objects<Articulos> lista;
        try {
            lista = Conexion.getConnection().getObjects(Articulos.class);

            Articulos mc;
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

    public boolean update(Articulos t) throws Exception {
        int salida = 0;
        boolean resultado = true;

        try {
            IQuery query = new CriteriaQuery(Articulos.class, Where.equal("id", t.getId()));
            Objects<Articulos> sports = Conexion.getConnection().getObjects(query);
            // Gets the first sport (there is only one!)
            Articulos volley = (Articulos) sports.getFirst();
            volley.setNombre(t.getNombre());
            volley.setGrupo(t.getGrupo());
            volley.setPrecio(t.getPrecio());
            volley.setStock(t.getStock());
            Conexion.getConnection().store(volley);
            Conexion.getConnection().commit();
        } catch (Exception e) {

        }

        return resultado;

    }

    public boolean delete(int id_articulo) throws Exception {
        int salida = 0;
        boolean resultado = true;
        Objects<Articulos> lista = Conexion.getConnection().getObjects(Articulos.class);

        try {
            lista = Conexion.getConnection().getObjects(Articulos.class);

            Articulos mc;
            while (lista.hasNext()) {
                mc = lista.next();

                if (mc.getId() == id_articulo) {
                    OID id = Conexion.getConnection().delete(mc);
                    Conexion.getConnection().commit();
                }

            }

        } catch (Exception e) {

        }

        return resultado;

    }

    public boolean insert(Grupos t) throws Exception {

        int salida = 0;
        boolean resultado = true;
        Conexion.getConnection().store(t);
        Conexion.getConnection().commit();
        return resultado;
    }

}
