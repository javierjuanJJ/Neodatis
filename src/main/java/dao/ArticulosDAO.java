package dao;

import com.mycompany.Controlador.Conexion;
import com.mycompany.Controlador.GenericoDAO;
import com.mycompany.Modelo.Articulos;
import com.mycompany.Modelo.Grupos;
import java.util.ArrayList;
import java.util.List;

import org.neodatis.odb.OID;
import org.neodatis.odb.Objects;

import javafx.application.Platform;
import org.neodatis.odb.ODB;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

public class ArticulosDAO implements GenericoDAO<Articulos> {

    private ODB conexion;

    public ArticulosDAO() throws Exception {
        conexion = Conexion.getConnection();
    }

    public Articulos findByPK(int id_articulo) throws Exception {
        IQuery query = new CriteriaQuery(Articulos.class, Where.equal("id", id_articulo));
        Objects<Articulos> articulo = conexion.getObjects(query);
        return (Articulos) articulo.getFirst();
    }

    public List<Articulos> findAll() throws Exception {
        List<Articulos> articulos_recibidos = new ArrayList();

        Objects<Articulos> lista_grupos = conexion.getObjects(Articulos.class);

        while (lista_grupos.hasNext()) {
            articulos_recibidos.add(lista_grupos.next());
        }

        return articulos_recibidos;
    }

    public List<Articulos> findBySQL(String sqlselect) throws Exception {
        return null;
    }

    public boolean insert(Articulos t) throws Exception {
        boolean resultado = true;
        OID oid = conexion.store(t);
        t.setId((int) oid.getObjectId());
        System.out.print(t.getId());
        conexion.commit();

        return resultado;
    }

    public boolean update(Articulos t) throws Exception {
        boolean resultado = true;
        Articulos articulo = (Articulos) findByPK(t.getId());
        articulo.setNombre(t.getNombre());
        articulo.setGrupo(t.getGrupo());
        articulo.setPrecio(t.getPrecio());
        articulo.setStock(t.getStock());
        conexion.store(articulo);
        conexion.commit();

        return resultado;

    }

    public boolean delete(int id_articulo) throws Exception {
        boolean resultado = true;
        conexion.delete(findByPK(id_articulo));
        conexion.commit();
        return resultado;

    }

}
