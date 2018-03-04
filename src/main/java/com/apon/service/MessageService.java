package com.apon.service;

import com.apon.database.generated.tables.pojos.MessagePojo;
import com.apon.database.jooq.Context;
import com.apon.database.mydao.MessageMyDao;
import com.apon.guice.InjectContext;
import org.apache.shiro.authz.annotation.RequiresUser;

import javax.sql.DataSource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@SuppressWarnings("unchecked")
@Path("message")
public class MessageService implements IService {
    private Context context;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresUser
    @InjectContext
    public List<MessagePojo> getMessage() {
        MessageMyDao messageMyDao = new MessageMyDao(context);
        return messageMyDao.findAll();
    }

    @GET
    @Path("/error")
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresUser
    @InjectContext
    public Object throwError() throws Exception {
        throw new Exception("Catch this!");
    }

    @Override
    public Context getContext() {
        return context;
    }

    @Override
    public void setContext(Context context) {
        this.context = context;
    }
}
