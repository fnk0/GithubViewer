package gabilheri.com.simpleorm.test_annotations;

import com.gabilheri.simpleorm.annotations.Increments;
import com.gabilheri.simpleorm.annotations.NotNull;
import com.gabilheri.simpleorm.annotations.OrmField;
import com.gabilheri.simpleorm.annotations.Table;
import com.gabilheri.simpleorm.annotations.Unique;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 12/6/14.
 */
@Table(name = "some_data")
public class SomeData {

    @NotNull
    @Unique
    @Increments
    @OrmField(name = "id")
    private long id;

    @OrmField(name = "name")
    private String name;

    @OrmField(name = "age")
    private int age;

    @OrmField(name = "rel", referenceTable = "relational_data", foreignKey = true)
    private RelationalData rel;

    public SomeData() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public RelationalData getRel() {
        return rel;
    }

    public void setRel(RelationalData rel) {
        this.rel = rel;
    }
}
