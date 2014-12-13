package com.gabilheri.githubviewer.data;

import com.gabilheri.simpleorm.OrmInstance;
import com.gabilheri.simpleorm.OrmObject;
import com.gabilheri.simpleorm.annotations.OrmField;
import com.gabilheri.simpleorm.annotations.Table;

import java.util.HashMap;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 12/6/14.
 */
@Table(name = "dummy_table")
public class Dummy  extends OrmInstance implements OrmObject {

    @OrmField(name = "name")
    private String name;

    @OrmField(name = "age")
    private int age;

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

    @Override
    public HashMap<String, Object> getValues() {
        HashMap<String, Object> values = new HashMap<>();
        values.put("name", getName());
        values.put("age", getAge());
        return values;
    }
}
