package com.clientesjson.server.control;


import java.math.BigDecimal;

import java.util.*;

import com.clientesjson.modelo.Cliente;


public interface IClienteLogic {
    public List<Cliente> getCliente() throws Exception;

    public void saveCliente(String direccion, String email, Long id,
        String nombre, Long telefono) throws Exception;

    public void deleteCliente(Long id) throws Exception;

    public void updateCliente(String direccion, String email, Long id,
        String nombre, Long telefono) throws Exception;

    public Cliente getCliente(Long id) throws Exception;

    public List<Cliente> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Cliente> findPageCliente(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberCliente() throws Exception;
}
