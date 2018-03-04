package com.apon.guice;

import com.apon.database.jooq.Context;
import com.apon.log.Log;
import com.apon.service.IService;
import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class InjectContextInterceptor extends AbstractModule implements MethodInterceptor {

    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        IService service = (IService) methodInvocation.getThis();

        try {
            // Initialize the dataSource.
            DataSource dataSource = (DataSource) ((javax.naming.Context) new InitialContext().lookup("java:comp/env"))
                    .lookup("jdbc/TestingRest-db");

            // Set context based on the source on the service.
            Context context = new Context(dataSource);
            service.setContext(context);
        } catch (NamingException e) {
            Log.logError("Could not initialize context.", e);
            throw e;
        }

        // Execute the service.
        try {
            return methodInvocation.proceed();
        } catch (Exception e) {
            Log.logError("Invocation of " + methodInvocation.getThis().getClass().getName() + "."
                    + methodInvocation.getMethod().getName() + " failed." , e);

            // Something went wrong, so we want to rollback.
            service.getContext().rollback();

            // Still throw the exception.
            throw e;
        }
    }

    protected void configure() {
        bindInterceptor(Matchers.any(), Matchers.annotatedWith(InjectContext.class), this);
    }
}
