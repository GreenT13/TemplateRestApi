package com.apon.service;

import com.apon.database.jooq.Context;

public interface IService {
    Context getContext();
    void setContext(Context context);
}
