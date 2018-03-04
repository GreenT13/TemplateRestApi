package com.apon.filter;

import com.apon.log.Log;
import com.apon.resteasy.Filter;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import java.io.IOException;

@SuppressWarnings("unused")
@Filter
public class RequestFilter implements ContainerRequestFilter {

    public void filter(ContainerRequestContext containerRequestContext) {
        Log.logDebug("Look at my filter woop.");
    }
}
