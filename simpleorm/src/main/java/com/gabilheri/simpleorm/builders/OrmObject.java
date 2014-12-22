package com.gabilheri.simpleorm.builders;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 12/13/14.
 */
public class OrmObject<T> {

    public static final String ID_FIELD = "_ID";
    private Class<T> aClass;
    private long _ID;

    public OrmObject(Class<T> aClass) {
        this.aClass = aClass;
    }

    public T build() throws InstantiationException, IllegalAccessException {
        return aClass.newInstance();
    }

    public OrmObject() {
    }

    public long getId() {
        return _ID;
    }

    public void setId(long _ID) {
        this._ID = _ID;
    }
}
