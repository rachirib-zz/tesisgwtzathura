package com.clientesjson.server.dao;


import java.math.BigDecimal;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.clientesjson.modelo.Cliente;


/**
 * Interface for ClienteDAO.
 *
*/
public interface IClienteDAO {
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
     * IClienteDAO.save(entity);
     * EntityManagerHelper.commit();
     * </pre>
     *
     * @param entity
     *            Cliente entity to persist
     * @throws RuntimeException
     *             when the operation fails
     */
    public void save(Cliente entity);

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
     * IClienteDAO.delete(entity);
     * EntityManagerHelper.commit();
     * entity = null;
     * </pre>
     *
     * @param entity
     *            Cliente entity to delete
     * @throws RuntimeException
     *             when the operation fails
     */
    public void delete(Cliente entity);

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
     * entity = IClienteDAO.update(entity);
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
    public Cliente update(Cliente entity);

    public Cliente findById(Long id);

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
     *            count of results to return.
     * @return List<Cliente> found by query
     */
    public List<Cliente> findByProperty(String propertyName, Object value,
        int... rowStartIdxAndCount);

    public List<Cliente> findByCriteria(String whereCondition);

    public List<Cliente> findByDireccion(Object direccion);

    public List<Cliente> findByDireccion(Object direccion,
        int... rowStartIdxAndCount);

    public List<Cliente> findByEmail(Object email);

    public List<Cliente> findByEmail(Object email, int... rowStartIdxAndCount);

    public List<Cliente> findById(Object id);

    public List<Cliente> findById(Object id, int... rowStartIdxAndCount);

    public List<Cliente> findByNombre(Object nombre);

    public List<Cliente> findByNombre(Object nombre, int... rowStartIdxAndCount);

    public List<Cliente> findByTelefono(Object telefono);

    public List<Cliente> findByTelefono(Object telefono,
        int... rowStartIdxAndCount);

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
    public List<Cliente> findAll(int... rowStartIdxAndCount);

    public List<Cliente> findPageCliente(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults);

    public Long findTotalNumberCliente();
}
