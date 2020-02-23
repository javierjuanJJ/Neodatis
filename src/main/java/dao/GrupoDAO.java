/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.mycompany.Controlador.Conexion;
import com.mycompany.Controlador.GenericoDAO;
import com.mycompany.Modelo.Articulos;
import com.mycompany.Modelo.Grupos;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Platform;
import org.neodatis.odb.ODB;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

/**
 *
 * @author batoi
 */
public class GrupoDAO implements GenericoDAO<Grupos> {

    private ODB conexion;

    public GrupoDAO() throws Exception {
        conexion = Conexion.getConnection();
    }

    @Override
    public Grupos findByPK(int id) throws Exception {
        IQuery query = new CriteriaQuery(Grupos.class, Where.equal("id", id));
        Objects<Grupos> Grupos = Conexion.getConnection().getObjects(query);
        return (Grupos) Grupos.getFirst();
    }

    @Override
    public List<Grupos> findAll() throws Exception {
        List<Grupos> grupos_recibidos = new ArrayList<Grupos>();
        Objects<Grupos> lista_grupos = conexion.getObjects(Grupos.class);

        while (lista_grupos.hasNext()) {
            grupos_recibidos.add(lista_grupos.next());
        }
        return grupos_recibidos;
    }

    @Override
    public List<Grupos> findBySQL(String sqlselect) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean insert(Grupos t) throws Exception {
        boolean resultado = true;
        conexion.store(t);
        conexion.commit();

        return resultado;
    }

    @Override
    public boolean update(Grupos t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
