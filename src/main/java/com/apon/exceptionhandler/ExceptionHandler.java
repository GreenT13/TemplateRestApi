package com.apon.exceptionhandler;

import org.apache.commons.lang.exception.ExceptionUtils;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class ExceptionHandler implements ExceptionMapper<Exception> {

    public Response toResponse(Exception e) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
        .entity(new ErrorResponse(e.getMessage(), ExceptionUtils.getStackTrace(e)))
        .type(MediaType.APPLICATION_JSON)
        .build();
    }
}
