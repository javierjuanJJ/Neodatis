package dao;

import com.mycompany.Controlador.Conexion;
import com.mycompany.Controlador.GenericoDAO;
import com.mycompany.Modelo.Articulos;
import com.mycompany.Modelo.Clientes;
import java.util.ArrayList;
import java.util.List;

import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

import org.neodatis.odb.ODB;
import org.neodatis.odb.OID;

public class ClientesDAO implements GenericoDAO<Clientes> {

    private final ODB conexion;

    public ClientesDAO() throws Exception {
        conexion = Conexion.getConnection();
    }

    @Override
    public Clientes findByPK(int id) throws Exception {
        IQuery query = new CriteriaQuery(Clientes.class, Where.equal("id", id));
        Objects<Clientes> Cliente = Conexion.getConnection().getObjects(query);
        return (Clientes) Cliente.getFirst();
    }

    @Override
    public List<Clientes> findAll() throws Exception {
        List<Clientes> Clientes_recibidos = new ArrayList();
        Objects<Clientes> lista_grupos = conexion.getObjects(Clientes.class);
        while (lista_grupos.hasNext()) {
            Clientes_recibidos.add(lista_grupos.next());
        }

        return Clientes_recibidos;
    }

    @Override
    public List<Clientes> findBySQL(String sqlselect) throws Exception {
        return null;
    }

    @Override
    public boolean insert(Clientes t) throws Exception {
        boolean resultado = true;
        OID oid = conexion.store(t);
        t.setId((int) oid.getObjectId());
        conexion.commit();
        

        return resultado;
    }

    @Override
    public boolean update(Clientes t) throws Exception {

        boolean resultado = true;

        Clientes cliente = findByPK(t.getId());
        cliente.setNombre(t.getNombre());
        cliente.setDireccion(t.getDireccion());
        conexion.store(cliente);
        conexion.commit();
        return resultado;

    }

    @Override
    public boolean delete(int id) throws Exception {
        boolean resultado = true;
        conexion.delete(findByPK(id));
        conexion.commit();
        return resultado;

    }

}
