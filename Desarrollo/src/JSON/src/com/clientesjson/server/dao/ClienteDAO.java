package com.clientesjson.server.dao;


import java.math.BigDecimal;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.clientesjson.modelo.Cliente;
import com.clientesjson.server.entityManager.EntityManagerHelper;


/**
 * A data access object (DAO) providing persistence and search support for
 * Cliente entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 *
 * @see lidis.Cliente
 */
public class ClienteDAO implements IClienteDAO {
    // property constants

    //public static final String  DIRECCION = "direccion";
    public static final String DIRECCION = "direccion";

    //public static final String  EMAIL = "email";
    public static final String EMAIL = "email";

    //public static final String  ID = "id";
    public static final String ID = "id";

    //public static final String  NOMBRE = "nombre";
    public static final String NOMBRE = "nombre";

    //public static final Long  TELEFONO = "telefono";
    public static final String TELEFONO = "telefono";

    private EntityManager getEntityManager() {
        return EntityManagerHelper.getEntityManager();
    }

    /**
     * Perform an initial save of a previously unsaved Cliente entity. All
     * subsequent persist actions of this entity should use the #update()
     * method. This operation must be performed within the a database
     * transaction context for the entity's data to be permanently saved to the
     * persistence store, i.e., database. This method uses the
     * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
     * operation.
     *
     * <pre>
     * EntityManagerHelper.beginTransaction();
     * ClienteDAO.save(entity);
     * EntityManagerHelper.commit();
     * </pre>
     *
     * @param entity
     *            Cliente entity to persist
     * @throws RuntimeException
     *             when the operation fails
     */
    public void save(Cliente entity) {
        EntityManagerHelper.log("saving Cliente instance", Level.INFO, null);

        try {
            getEntityManager().persist(entity);
            EntityManagerHelper.log("save successful", Level.INFO, null);
        } catch (RuntimeException re) {
            EntityManagerHelper.log("save failed", Level.SEVERE, re);
            throw re;
        }
    }

    /**
     * Delete a persistent Cliente entity. This operation must be performed
     * within the a database transaction context for the entity's data to be
     * permanently deleted from the persistence store, i.e., database. This
     * method uses the
     * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
     * operation.
     *
     * <pre>
     * EntityManagerHelper.beginTransaction();
     * ClienteDAO.delete(entity);
     * EntityManagerHelper.commit();
     * entity = null;
     * </pre>
     *
     * @param entity
     *            Cliente entity to delete
     * @throws RuntimeException
     *             when the operation fails
     */
    public void delete(Cliente entity) {
        EntityManagerHelper.log("deleting Cliente instance", Level.INFO, null);

        try {
            entity = getEntityManager()
                         .getReference(Cliente.class, entity.getId());
            getEntityManager().remove(entity);
            EntityManagerHelper.log("delete successful", Level.INFO, null);
        } catch (RuntimeException re) {
            EntityManagerHelper.log("delete failed", Level.SEVERE, re);
            throw re;
        }
    }

    /**
     * Persist a previously saved Cliente entity and return it or a copy of it
     * to the sender. A copy of the Cliente entity parameter is returned when
     * the JPA persistence mechanism has not previously been tracking the
     * updated entity. This operation must be performed within the a database
     * transaction context for the entity's data to be permanently saved to the
     * persistence store, i.e., database. This method uses the
     * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
     * operation.
     *
     * <pre>
     * EntityManagerHelper.beginTransaction();
     * entity = ClienteDAO.update(entity);
     * EntityManagerHelper.commit();
     * </pre>
     *
     * @param entity
     *            Cliente entity to update
     * @return Cliente the persisted Cliente entity instance, may not be the
     *         same
     * @throws RuntimeException
     *             if the operation fails
     */
    public Cliente update(Cliente entity) {
        EntityManagerHelper.log("updating Cliente instance", Level.INFO, null);

        try {
            Cliente result = getEntityManager().merge(entity);
            EntityManagerHelper.log("update successful", Level.INFO, null);

            return result;
        } catch (RuntimeException re) {
            EntityManagerHelper.log("update failed", Level.SEVERE, re);
            throw re;
        }
    }

    public Cliente findById(Long id) {
        EntityManagerHelper.log("finding Cliente instance with id: " + id,
            Level.INFO, null);

        try {
            Cliente instance = getEntityManager().find(Cliente.class, id);

            return instance;
        } catch (RuntimeException re) {
            EntityManagerHelper.log("find failed", Level.SEVERE, re);
            throw re;
        }
    }

    /**
     * Find all  Cliente entities with a specific property value.
     *
     * @param propertyName
     *            the metaData.name of the  Cliente property to query
     * @param value
     *            the property value to match
     * @return List< Cliente> found by query
     */
    @SuppressWarnings("unchecked")
    public List<Cliente> findByProperty(String propertyName, final Object value) {
        EntityManagerHelper.log("finding  Cliente instance with property: " +
            propertyName + ", value: " + value, Level.INFO, null);

        try {
            final String queryString = "select model from  Cliente model where model." +
                propertyName + "= :propertyValue";
            Query query = getEntityManager().createQuery(queryString);
            query.setParameter("propertyValue", value);

            return query.getResultList();
        } catch (RuntimeException re) {
            EntityManagerHelper.log("find by property metaData.name failed",
                Level.SEVERE, re);
            throw re;
        }
    }

    /**
     * Find all Cliente entities with a specific property value.
     *
     * @param propertyName
     *            the name of the Cliente property to query
     * @param value
     *            the property value to match
     * @param rowStartIdxAndCount
     *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
     *            row index in the query result-set to begin collecting the
     *            results. rowStartIdxAndCount[1] specifies the the maximum
     *            number of results to return.
     * @return List<Cliente> found by query
     */
    @SuppressWarnings("unchecked")
    public List<Cliente> findByProperty(String propertyName,
        final Object value, final int... rowStartIdxAndCount) {
        EntityManagerHelper.log("finding Cliente instance with property: " +
            propertyName + ", value: " + value, Level.INFO, null);

        try {
            final String queryString = "select model from Cliente model where model." +
                propertyName + "= :propertyValue";
            Query query = getEntityManager().createQuery(queryString);
            query.setParameter("propertyValue", value);

            if ((rowStartIdxAndCount != null) &&
                    (rowStartIdxAndCount.length > 0)) {
                int rowStartIdx = Math.max(0, rowStartIdxAndCount[0]);

                if (rowStartIdx > 0) {
                    query.setFirstResult(rowStartIdx);
                }

                if (rowStartIdxAndCount.length > 1) {
                    int rowCount = Math.max(0, rowStartIdxAndCount[1]);

                    if (rowCount > 0) {
                        query.setMaxResults(rowCount);
                    }
                }
            }

            return query.getResultList();
        } catch (RuntimeException re) {
            EntityManagerHelper.log("find by property name failed",
                Level.SEVERE, re);
            throw re;
        }
    }

    public List<Cliente> findByDireccion(Object direccion,
        int... rowStartIdxAndCount) {
        return findByProperty(DIRECCION, direccion, rowStartIdxAndCount);
    }

    public List<Cliente> findByDireccion(Object direccion) {
        return findByProperty(DIRECCION, direccion);
    }

    public List<Cliente> findByEmail(Object email, int... rowStartIdxAndCount) {
        return findByProperty(EMAIL, email, rowStartIdxAndCount);
    }

    public List<Cliente> findByEmail(Object email) {
        return findByProperty(EMAIL, email);
    }

    public List<Cliente> findById(Object id, int... rowStartIdxAndCount) {
        return findByProperty(ID, id, rowStartIdxAndCount);
    }

    public List<Cliente> findById(Object id) {
        return findByProperty(ID, id);
    }

    public List<Cliente> findByNombre(Object nombre, int... rowStartIdxAndCount) {
        return findByProperty(NOMBRE, nombre, rowStartIdxAndCount);
    }

    public List<Cliente> findByNombre(Object nombre) {
        return findByProperty(NOMBRE, nombre);
    }

    public List<Cliente> findByTelefono(Object telefono,
        int... rowStartIdxAndCount) {
        return findByProperty(TELEFONO, telefono, rowStartIdxAndCount);
    }

    public List<Cliente> findByTelefono(Object telefono) {
        return findByProperty(TELEFONO, telefono);
    }

    /**
     * Find all Cliente entities.
     *
     * @param rowStartIdxAndCount
     *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
     *            row index in the query result-set to begin collecting the
     *            results. rowStartIdxAndCount[1] specifies the the maximum
     *            count of results to return.
     * @return List<Cliente> all Cliente entities
     */
    @SuppressWarnings("unchecked")
    public List<Cliente> findAll(final int... rowStartIdxAndCount) {
        EntityManagerHelper.log("finding all Cliente instances", Level.INFO,
            null);

        try {
            final String queryString = "select model from Cliente model";
            Query query = getEntityManager().createQuery(queryString);

            if ((rowStartIdxAndCount != null) &&
                    (rowStartIdxAndCount.length > 0)) {
                int rowStartIdx = Math.max(0, rowStartIdxAndCount[0]);

                if (rowStartIdx > 0) {
                    query.setFirstResult(rowStartIdx);
                }

                if (rowStartIdxAndCount.length > 1) {
                    int rowCount = Math.max(0, rowStartIdxAndCount[1]);

                    if (rowCount > 0) {
                        query.setMaxResults(rowCount);
                    }
                }
            }

            return query.getResultList();
        } catch (RuntimeException re) {
            EntityManagerHelper.log("find all failed", Level.SEVERE, re);
            throw re;
        }
    }

    public List<Cliente> findByCriteria(String whereCondition) {
        try {
            String where = ((whereCondition == null) ||
                (whereCondition.length() == 0)) ? "" : ("where " +
                whereCondition);

            final String queryString = "select model from Cliente model " +
                where;

            Query query = getEntityManager().createQuery(queryString);

            List<Cliente> entitiesList = query.getResultList();

            return entitiesList;
        } catch (RuntimeException re) {
            EntityManagerHelper.log("find By Criteria in Cliente failed",
                Level.SEVERE, re);
            throw re;
        }
    }

    public List<Cliente> findPageCliente(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults) {
        if ((sortColumnName != null) && (sortColumnName.length() > 0)) {
            try {
                String queryString = "select model from Cliente model order by model." +
                    sortColumnName + " " + (sortAscending ? "asc" : "desc");

                return getEntityManager().createQuery(queryString)
                           .setFirstResult(startRow).setMaxResults(maxResults)
                           .getResultList();
            } catch (RuntimeException re) {
                throw re;
            }
        } else {
            try {
                String queryString = "select model from Cliente model";

                return getEntityManager().createQuery(queryString)
                           .setFirstResult(startRow).setMaxResults(maxResults)
                           .getResultList();
            } catch (RuntimeException re) {
                throw re;
            }
        }
    }

    @SuppressWarnings("unchecked")
    public Long findTotalNumberCliente() {
        try {
            String queryString = "select count(*) from Cliente model";

            return (Long) getEntityManager().createQuery(queryString)
                              .getSingleResult();
        } catch (RuntimeException re) {
            throw re;
        }
    }
}
