package com.apon.database.mydao;

import com.apon.database.generated.tables.daos.MessageDao;
import com.apon.database.jooq.Context;
import org.jooq.Configuration;

public class MessageMyDao extends MessageDao {

    public MessageMyDao(Context context) {
        super(context.getConfiguration());
    }
}
