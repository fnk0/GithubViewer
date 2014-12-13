package com.gabilheri.githubviewer.data;

import com.gabilheri.simpleorm.OrmObject;
import com.gabilheri.simpleorm.annotations.OrmField;
import com.gabilheri.simpleorm.annotations.Table;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 12/6/14.
 */
@Table(name = "dummy_table")
public class Dummy extends OrmObject {

    @OrmField(name = "name")
    private String name;

    @OrmField(name = "age")
    private int age;

    @OrmField(name = "dummy_ext_id", foreignKey = true, referenceTable = "dummy_extension")
    private DummyExtension dummyExtension;

    public Dummy() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public DummyExtension getDummyExtension() {
        return dummyExtension;
    }

    public void setDummyExtension(DummyExtension dummyExtension) {
        this.dummyExtension = dummyExtension;
    }
}
