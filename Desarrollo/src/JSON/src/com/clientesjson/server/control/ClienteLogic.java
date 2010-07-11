package com.clientesjson.server.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import com.clientesjson.modelo.Cliente;
import com.clientesjson.server.daoFactory.JPADaoFactory;
import com.clientesjson.server.entityManager.EntityManagerHelper;
import com.clientesjson.server.exception.ExceptionManager;
import com.clientesjson.server.exception.ExceptionMessages;
import com.clientesjson.server.util.Utilities;


public class ClienteLogic implements IClienteLogic {
    public List<Cliente> getCliente() throws Exception {
        List<Cliente> list = new ArrayList<Cliente>();

        try {
            list = JPADaoFactory.getInstance().getClienteDAO().findAll(0);
        } catch (Exception e) {
            throw new Exception(ExceptionManager.getInstance()
                                                .exceptionInGetAll());
        } finally {
            EntityManagerHelper.closeEntityManager();
        }

        return list;
    }

    public void saveCliente(String direccion, String email, Long id,
        String nombre, Long telefono) throws Exception {
        Cliente entity = null;

        try {
            entity = getCliente(id);

            if (entity != null) {
                throw new Exception(ExceptionMessages.ENTITY_WITHSAMEKEY);
            }

            entity = new Cliente();

            entity.setDireccion(direccion);
            entity.setEmail(email);
            entity.setId(id);
            entity.setNombre(nombre);
            entity.setTelefono(telefono);

            EntityManagerHelper.beginTransaction();
            JPADaoFactory.getInstance().getClienteDAO().save(entity);
            EntityManagerHelper.commit();
        } catch (Exception e) {
            EntityManagerHelper.rollback();
            throw e;
        } finally {
            EntityManagerHelper.closeEntityManager();
        }
    }

    public void deleteCliente(Long id) throws Exception {
        Cliente entity = null;

        if (id == null) {
            throw new Exception(ExceptionMessages.VARIABLE_NULL);
        }

        entity = getCliente(id);

        if (entity == null) {
            throw new Exception(ExceptionMessages.ENTITY_NULL);
        }

        try {
            EntityManagerHelper.beginTransaction();
            JPADaoFactory.getInstance().getClienteDAO().delete(entity);
            EntityManagerHelper.commit();

            EntityManagerHelper.closeEntityManager();
        } catch (Exception e) {
            throw e;
        } finally {
            EntityManagerHelper.closeEntityManager();
        }
    }

    public void updateCliente(String direccion, String email, Long id,
        String nombre, Long telefono) throws Exception {
        Cliente entity = null;

        try {
            entity = getCliente(id);

            if (entity == null) {
                throw new Exception(ExceptionMessages.ENTITY_NOENTITYTOUPDATE);
            }

            entity.setDireccion(direccion);
            entity.setEmail(email);
            entity.setId(id);
            entity.setNombre(nombre);
            entity.setTelefono(telefono);

            EntityManagerHelper.beginTransaction();
            JPADaoFactory.getInstance().getClienteDAO().update(entity);
            EntityManagerHelper.commit();

            EntityManagerHelper.closeEntityManager();
        } catch (Exception e) {
            EntityManagerHelper.rollback();
            throw e;
        } finally {
            EntityManagerHelper.closeEntityManager();
        }
    }

    public Cliente getCliente(Long id) throws Exception {
        Cliente entity = null;

        try {
            entity = JPADaoFactory.getInstance().getClienteDAO().findById(id);
        } catch (Exception e) {
            throw new Exception(ExceptionManager.getInstance()
                                                .exceptionFindingEntity("Cliente"));
        } finally {
            EntityManagerHelper.closeEntityManager();
        }

        return entity;
    }

    public List<Cliente> findPageCliente(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        List<Cliente> entity = null;

        try {
            entity = JPADaoFactory.getInstance().getClienteDAO()
                                  .findPageCliente(sortColumnName,
                    sortAscending, startRow, maxResults);
        } catch (Exception e) {
            throw new Exception(ExceptionManager.getInstance()
                                                .exceptionFindingEntity("Cliente"));
        } finally {
            EntityManagerHelper.closeEntityManager();
        }

        return entity;
    }

    public Long findTotalNumberCliente() throws Exception {
        Long entity = null;

        try {
            entity = JPADaoFactory.getInstance().getClienteDAO()
                                  .findTotalNumberCliente();
        } catch (Exception e) {
            throw new Exception(ExceptionManager.getInstance()
                                                .exceptionFindingEntity("Cliente Count"));
        } finally {
            EntityManagerHelper.closeEntityManager();
        }

        return entity;
    }

    /**
    *
    * @param varibles
    *            este arreglo debera tener:
    *
    * [0] = String variable = (String) varibles[i]; representa como se llama la
    * variable en el pojo
    *
    * [1] = Boolean booVariable = (Boolean) varibles[i + 1]; representa si el
    * valor necesita o no ''(comillas simples)usado para campos de tipo string
    *
    * [2] = Object value = varibles[i + 2]; representa el valor que se va a
    * buscar en la BD
    *
    * [3] = String comparator = (String) varibles[i + 3]; representa que tipo
    * de busqueda voy a hacer.., ejemplo: where nombre=william o where nombre<>william,
    * en este campo iria el tipo de comparador que quiero si es = o <>
    *
    * Se itera de 4 en 4..., entonces 4 registros del arreglo representan 1
    * busqueda en un campo, si se ponen mas pues el continuara buscando en lo
    * que se le ingresen en los otros 4
    *
    *
    * @param variablesBetween
    *
    * la diferencia son estas dos posiciones
    *
    * [0] = String variable = (String) varibles[j]; la variable ne la BD que va
    * a ser buscada en un rango
    *
    * [1] = Object value = varibles[j + 1]; valor 1 para buscar en un rango
    *
    * [2] = Object value2 = varibles[j + 2]; valor 2 para buscar en un rango
    * ejempolo: a > 1 and a < 5 --> 1 seria value y 5 seria value2
    *
    * [3] = String comparator1 = (String) varibles[j + 3]; comparador 1
    * ejemplo: a comparator1 1 and a < 5
    *
    * [4] = String comparator2 = (String) varibles[j + 4]; comparador 2
    * ejemplo: a comparador1>1  and a comparador2<5  (el original: a > 1 and a <
    * 5) *
    * @param variablesBetweenDates(en
    *            este caso solo para mysql)
    
    *  [0] = String variable = (String) varibles[k]; el nombre de la variable que hace referencia a
    *            una fecha
    *
    * [1] = Object object1 = varibles[k + 2]; fecha 1 a comparar(deben ser
    * dates)
    *
    * [2] = Object object2 = varibles[k + 3]; fecha 2 a comparar(deben ser
    * dates)
    *
    * esto hace un between entre las dos fechas.
    *
    * @return lista con los objetos que se necesiten
    * @throws Exception
    */
    public List<Cliente> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<Cliente> list = new ArrayList<Cliente>();

        String where = new String();
        String tempWhere = new String();

        if (variables != null) {
            for (int i = 0; i < variables.length; i++) {
                String variable = (String) variables[i];
                Boolean booVariable = (Boolean) variables[i + 1];
                Object value = variables[i + 2];
                String comparator = (String) variables[i + 3];

                if (value != null) {
                    if (booVariable.booleanValue()) {
                        tempWhere = (tempWhere.length() == 0)
                            ? ("(model." + variable + " " + comparator + " \'" +
                            value + "\' )")
                            : (tempWhere + " AND (model." + variable + " " +
                            comparator + " \'" + value + "\' )");
                    } else {
                        tempWhere = (tempWhere.length() == 0)
                            ? ("(model." + variable + " " + comparator + " " +
                            value + " )")
                            : (tempWhere + " AND (model." + variable + " " +
                            comparator + " " + value + " )");
                    }
                }

                i = i + 3;
            }
        }

        if (variablesBetween != null) {
            for (int j = 0; j < variablesBetween.length; j++) {
                String variable = (String) variablesBetween[j];
                Object value = variablesBetween[j + 1];
                Object value2 = variablesBetween[j + 2];
                String comparator1 = (String) variablesBetween[j + 3];
                String comparator2 = (String) variablesBetween[j + 4];

                if (variable != null) {
                    tempWhere = (tempWhere.length() == 0)
                        ? ("(" + value + " " + comparator1 + " model." +
                        variable + " and model." + variable + " " +
                        comparator2 + " " + value2 + " )")
                        : (tempWhere + " AND (" + value + " " + comparator1 +
                        " model." + variable + " and model." + variable + " " +
                        comparator2 + " " + value2 + " )");
                }

                j = j + 4;
            }
        }

        if (variablesBetweenDates != null) {
            for (int k = 0; k < variablesBetweenDates.length; k++) {
                String variable = (String) variablesBetweenDates[k];
                Object object1 = variablesBetweenDates[k + 1];
                Object object2 = variablesBetweenDates[k + 2];
                String value = null;
                String value2 = null;

                if (variable != null) {
                    try {
                        Date date1 = (Date) object1;
                        Date date2 = (Date) object2;
                        value = Utilities.formatDateWithoutTimeInAStringForBetweenWhere(date1);
                        value2 = Utilities.formatDateWithoutTimeInAStringForBetweenWhere(date2);
                    } catch (Exception e) {
                        list = null;
                        throw e;
                    }

                    tempWhere = (tempWhere.length() == 0)
                        ? ("(model." + variable + " between \'" + value +
                        "\' and \'" + value2 + "\')")
                        : (tempWhere + " AND (model." + variable +
                        " between \'" + value + "\' and \'" + value2 + "\')");
                }

                k = k + 2;
            }
        }

        if (tempWhere.length() == 0) {
            where = null;
        } else {
            where = "(" + tempWhere + ")";
        }

        try {
            list = JPADaoFactory.getInstance().getClienteDAO()
                                .findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            EntityManagerHelper.closeEntityManager();
        }

        return list;
    }
}
