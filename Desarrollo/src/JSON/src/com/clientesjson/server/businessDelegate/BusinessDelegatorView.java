package com.clientesjson.server.businessDelegate;


import java.math.BigDecimal;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.clientesjson.modelo.Cliente;
import com.clientesjson.server.control.ClienteLogic;
import com.clientesjson.server.control.IClienteLogic;


/**
 * Use a Business Delegate to reduce coupling between presentation-tier clients and business services.
 * The Business Delegate hides the underlying implementation details of the business service, such as lookup and access details of the EJB architecture.
 *
 * The Business Delegate acts as a client-side business abstraction; it provides an abstraction for, and thus hides,
 * the implementation of the business services. Using a Business Delegate reduces the coupling between presentation-tier clients and
 * the system's business services. Depending on the implementation strategy, the Business Delegate may shield clients from possible
 * volatility in the implementation of the business service API. Potentially, this reduces the number of changes that must be made to the
 * presentation-tier client code when the business service API or its underlying implementation changes.
 *
 * However, interface methods in the Business Delegate may still require modification if the underlying business service API changes.
 * Admittedly, though, it is more likely that changes will be made to the business service rather than to the Business Delegate.
 *
 * Often, developers are skeptical when a design goal such as abstracting the business layer causes additional upfront work in return
 * for future gains. However, using this pattern or its strategies results in only a small amount of additional upfront work and provides
 * considerable benefits. The main benefit is hiding the details of the underlying service. For example, the client can become transparent
 * to naming and lookup services. The Business Delegate also handles the exceptions from the business services, such as java.rmi.Remote
 * exceptions, Java Messages Service (JMS) exceptions and so on. The Business Delegate may intercept such service level exceptions and
 * generate application level exceptions instead. Application level exceptions are easier to handle by the clients, and may be user friendly.
 * The Business Delegate may also transparently perform any retry or recovery operations necessary in the event of a service failure without
 * exposing the client to the problem until it is determined that the problem is not resolvable. These gains present a compelling reason to
 * use the pattern.
 *
 * Another benefit is that the delegate may cache results and references to remote business services. Caching can significantly improve performance,
 * because it limits unnecessary and potentially costly round trips over the network.
 *
 * A Business Delegate uses a component called the Lookup Service. The Lookup Service is responsible for hiding the underlying implementation
 * details of the business service lookup code. The Lookup Service may be written as part of the Delegate, but we recommend that it be
 * implemented as a separate component, as outlined in the Service Locator pattern (See "Service Locator" on page 368.)
 *
 * When the Business Delegate is used with a Session Facade, typically there is a one-to-one relationship between the two.
 * This one-to-one relationship exists because logic that might have been encapsulated in a Business Delegate relating to its interaction
 * with multiple business services (creating a one-to-many relationship) will often be factored back into a Session Facade.
 *
 * Finally, it should be noted that this pattern could be used to reduce coupling between other tiers, not simply the presentation and the
 * business tiers.
 *
 * @author Zathura Code Generator http://code.google.com/p/zathura
 *
 */
public class BusinessDelegatorView {
    private BusinessDelegatorView() {
    }

    public static List<Cliente> getCliente() throws Exception {
        IClienteLogic clienteLogic = new ClienteLogic();

        return clienteLogic.getCliente();
    }

    public static void saveCliente(String direccion, String email, Long id,
        String nombre, Long telefono) throws Exception {
        IClienteLogic clienteLogic = new ClienteLogic();
        clienteLogic.saveCliente(direccion, email, id, nombre, telefono);
        
    }

    public static void deleteCliente(Long id) throws Exception {
        IClienteLogic clienteLogic = new ClienteLogic();
        clienteLogic.deleteCliente(id);
    }

    public static void updateCliente(String direccion, String email, Long id,
        String nombre, Long telefono) throws Exception {
        IClienteLogic clienteLogic = new ClienteLogic();
        clienteLogic.updateCliente(direccion, email, id, nombre, telefono);
    }

    public static Cliente getCliente(Long id) throws Exception {
        IClienteLogic clienteLogic = new ClienteLogic();
        Cliente cliente = null;

        try {
            cliente = clienteLogic.getCliente(id);
        } catch (Exception e) {
            throw e;
        }

        return cliente;
    }

    public static List<Cliente> findByCriteriaInCliente(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        IClienteLogic clienteLogic = new ClienteLogic();

        return clienteLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public static List<Cliente> findPageCliente(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        IClienteLogic clienteLogic = new ClienteLogic();

        return clienteLogic.findPageCliente(sortColumnName, sortAscending,
            startRow, maxResults);
    }

    public static Long findTotalNumberCliente() throws Exception {
        IClienteLogic clienteLogic = new ClienteLogic();

        return clienteLogic.findTotalNumberCliente();
    }
}
