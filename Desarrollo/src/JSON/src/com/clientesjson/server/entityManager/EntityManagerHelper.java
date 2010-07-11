package com.clientesjson.server.entityManager;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


/**
 *
 * @author Zathura Code Generator http://code.google.com/p/zathura
 *
 */
public class EntityManagerHelper {
    private static final EntityManagerFactory emf;
    private static final ThreadLocal<EntityManager> threadLocal;
    private static final Logger logger;

    static {
        emf = Persistence.createEntityManagerFactory("ClientesJSON");
        threadLocal = new ThreadLocal<EntityManager>();
        logger = Logger.getLogger("ClientesJSON");
        logger.setLevel(Level.ALL);
    }

    public static EntityManager getEntityManager() {
        EntityManager manager = threadLocal.get();

        if ((manager == null) || !manager.isOpen()) {
            manager = emf.createEntityManager();
            threadLocal.set(manager);
        }

        return manager;
    }

    public static void closeEntityManager() {
        EntityManager em = threadLocal.get();
        threadLocal.set(null);

        if (em != null) {
            em.close();
        }
    }

    public static void beginTransaction() {
        getEntityManager().getTransaction().begin();
    }

    public static void commit() {
        getEntityManager().getTransaction().commit();
    }

    public static void rollback() {
        if ((getEntityManager().getTransaction() != null) &&
                (getEntityManager().getTransaction().isActive() == true)) {
            getEntityManager().getTransaction().rollback();
        }
    }

    public static Query createQuery(String query) {
        return getEntityManager().createQuery(query);
    }

    public static void log(String info, Level level, Throwable ex) {
        logger.log(level, info, ex);
    }
}
