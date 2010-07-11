package com.clientesjson.server.daoFactory;

import com.clientesjson.server.dao.ClienteDAO;
import com.clientesjson.server.dao.IClienteDAO;



/**
 * Factory for Data Access Objects Strategy The DAO pattern can be made highly flexible
 * by adopting the Abstract Factory [GoF] and the Factory Method [GoF] patterns.
 * When the underlying storage is not subject to change from one implementation to another,
 * this strategy can be implemented using the Factory Method pattern to produce a number of DAOs needed by the application.
 * This class is a Factory Method pattern
 *
 * @author Zathura Code Generator http://code.google.com/p/zathura
 */
public class JPADaoFactory {
    private static JPADaoFactory instance = null;

    private JPADaoFactory() {
    }

    /**
    *
    * @return JPADaoFactory
    */
    public static JPADaoFactory getInstance() {
        if (instance == null) {
            instance = new JPADaoFactory();
        }

        return instance;
    }

    /**
    * This method instantiates the IClienteDAO class for JPA
    * that is used in this applications deployment environment to access the data.
    * @return IClienteDAO implementation
    */
    public IClienteDAO getClienteDAO() {
        return new ClienteDAO();
    }
}
