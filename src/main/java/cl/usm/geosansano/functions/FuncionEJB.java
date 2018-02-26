/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.usm.geosansano.functions;

/**
 *
 * @author Alexander Riquelme
 */
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class FuncionEJB {

    private static final String EJB_CONTEXT;

    static {
        try {
            EJB_CONTEXT = "java:global/" + new InitialContext().lookup("java:app/AppName") + "/";
        } catch (NamingException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    private FuncionEJB() {
    }

    @SuppressWarnings("unchecked")
    public static <T> T lookup(Class<T> ejbClass) {
        String jndiName = EJB_CONTEXT + ejbClass.getSimpleName();

        try {
            return (T) new InitialContext().lookup(jndiName);
        } catch (NamingException e) {
            throw new IllegalArgumentException(
                    String.format("Cannot find EJB class %s in JNDI %s", ejbClass, jndiName), e);
        }
    }
}
