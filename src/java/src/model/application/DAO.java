package src.model.application;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class DAO {

    private static DAO instancia;//tmb faz parte do singleton

    // <editor-fold defaultstate="collapsed" desc="Design pattern => Singleton">
    private DAO() {
    }

    public static synchronized DAO getInstance() {
        if (instancia == null) {
            instancia = new DAO();
        }
        return instancia;
    }
    // </editor-fold>

    static EntityManagerFactory entityManagerFactory;
    static EntityManager entityManager;
    static EntityTransaction transaction;

    static {
        getInstance();
    }

    public static EntityManager abrirConexao() {
        entityManagerFactory = Persistence.createEntityManagerFactory("CRUDAlunoPU");
        entityManager = entityManagerFactory.createEntityManager();
        transaction = entityManager.getTransaction();
        transaction.begin();
        return entityManager;
    }

    public static void fecharConexao() {
        transaction.commit();
        entityManager.close();
        entityManagerFactory.close();
    }

    public static void save(Object obj) {
        EntityManager manager = DAO.abrirConexao();
        try {
            manager.persist(obj);
        } catch (Exception e) {
            System.err.println("Erro ao pesistir objeto " + obj.getClass().toString());
        } finally {
            DAO.fecharConexao();
        }
    }

    public static <Tipo> Tipo getById(Class<Tipo> clazz, int id) {
        if (id <= 0) {
            return null;
        }
        EntityManager manager = DAO.abrirConexao();
        Object obj = null;
        try {
            obj = manager.find(clazz, (Integer) id);
        } catch (Exception e) {
            System.err.println("Erro ao pegar por ID objeto único " + clazz.toString());
        } finally {
            DAO.fecharConexao();
            return (Tipo) obj;
        }
    }

    public static <Tipo> Tipo getById(Class<Tipo> clazz, int id, EntityManager manager) {
        Object obj = null;
        try {
            obj = manager.find(clazz, (Integer) id);
        } catch (Exception e) {
            System.err.println("Erro ao pegar por ID objeto único (manager como parametro) " + clazz.toString());
        } finally {
            return (Tipo) obj;
        }
    }

    public static <Tipo> Tipo delete(Class<Tipo> clazz, int id) {
        if (id <= 0) {
            return null;
        }
        Object obj = null;
        try {
            EntityManager manager = DAO.abrirConexao();
            obj = manager.find(clazz, (Integer) id);
            manager.remove(((Tipo) obj));
        } catch (Exception e) {
            System.err.println("Erro ao deletar objeto único " + clazz.toString());
        } finally {
            DAO.fecharConexao();
            return (Tipo) obj;
        }
    }

    public static <Tipo> List<Tipo> getAllNativeQuery(Class<Tipo> clazz, String tableName) {
        EntityManager manager = DAO.abrirConexao();
        List<Tipo> listaGenerica = null;
        try {
            Query query = manager.createNativeQuery("SELECT * FROM " + tableName + " WHERE (1 = 1)", clazz);
            listaGenerica = query.getResultList();
        } catch (Exception e) {
            System.err.println("Erro ao pegar lista de objetos " + tableName);
        } finally {
            DAO.fecharConexao();
            return listaGenerica;
        }
    }

    public static <Tipo> Tipo getBySingleParameter(Class<Tipo> clazz, String tableName, String columnName, String parameterValue) {
        EntityManager manager = DAO.abrirConexao();
        Tipo obj = null;
        try {
            Query query = manager.createNativeQuery("SELECT * FROM " + tableName + " WHERE (" + columnName + " = ?1)", clazz);
            obj = (Tipo) query.setParameter(1, parameterValue).getSingleResult();
        } catch (Exception e) {
            System.err.println("Erro ao pegar objeto único " + tableName);
        } finally {
            DAO.fecharConexao();
            return obj;
        }
    }
    
    public static <Tipo> List<Tipo> getByResultList(Class<Tipo> clazz, String tableName, String columnName, String parameterValue) {
        EntityManager manager = DAO.abrirConexao();
        List<Tipo> list = null;
        try {
            Query query = manager.createNativeQuery("SELECT * FROM " + tableName + " WHERE (" + columnName + " = ?1)", clazz);
            list = query.setParameter(1, parameterValue).getResultList();
        } catch (Exception e) {
            System.err.println("Erro ao pegar lista de objetos " + tableName);
        } finally {
            DAO.fecharConexao();
            return list;
        }
    }

    @SafeVarargs
    @SuppressWarnings("varargs")
    public static <Tipo> List<Tipo> getByColumnNome(Class<Tipo> clazz, String tableName, String... names) {
        List<Tipo> genericList = new ArrayList<>();

        for (String name : names) {
            genericList.add(DAO.getBySingleParameter(clazz, tableName, "nome", name));
        }

        return genericList;
    }
    
    @SafeVarargs
    @SuppressWarnings("varargs")
    public static <Tipo> Set<Tipo> getSetByColumnNome(Class<Tipo> clazz, String tableName, String... names) {
        Set<Tipo> genericList = new HashSet<>();

        for (String name : names) {
            genericList.add(DAO.getBySingleParameter(clazz, tableName, "nome", name));
        }

        return genericList;
    }
}
