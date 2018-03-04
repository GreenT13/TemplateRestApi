package com.apon.database.jooq;

import org.jooq.util.DefaultGeneratorStrategy;
import org.jooq.util.Definition;

public class GeneratorStrategy extends DefaultGeneratorStrategy {

    /**
     * All Pojo filenames should end with Pojo.
     * @param definition The definition.
     * @param mode The mode.
     * @return The name.
     */
    @Override
    public String getJavaClassName(Definition definition, Mode mode) {
        switch (mode){
            case POJO:
                return super.getJavaClassName(definition, mode) + "Pojo";
            default:
                return super.getJavaClassName(definition, mode);
        }
    }
}
