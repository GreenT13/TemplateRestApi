package com.apon.guice;

import com.apon.service.IService;
import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.matcher.Matchers;
import org.reflections.Reflections;

public class BinderModule implements Module {

    public void configure(Binder binder) {
        // Bind the classes so we can actually use method interception.
        Reflections reflections = new Reflections("com.apon.service");
        for (Class<? extends IService> myClass : reflections.getSubTypesOf(IService.class)) {
            binder.bind(myClass);
        }

        // Bind @InjectContext
        binder.bindInterceptor(Matchers.any(), Matchers.annotatedWith(InjectContext.class), new InjectContextInterceptor());
    }
}
