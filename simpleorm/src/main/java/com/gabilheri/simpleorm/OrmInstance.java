package com.gabilheri.simpleorm;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 12/13/14.
 */
public class OrmInstance<T> {

    private Class<T> aClass;

    public OrmInstance(Class<T> aClass) {
        this.aClass = aClass;
    }

    public T build() throws InstantiationException, IllegalAccessException {
        return aClass.newInstance();
    }

    public OrmInstance() {
    }
}
