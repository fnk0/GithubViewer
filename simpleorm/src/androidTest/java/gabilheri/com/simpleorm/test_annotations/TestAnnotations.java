package gabilheri.com.simpleorm.test_annotations;

import android.test.AndroidTestCase;

import com.gabilheri.simpleorm.annotations.OrmField;
import com.gabilheri.simpleorm.annotations.Table;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 12/6/14.
 */
public class TestAnnotations extends AndroidTestCase {

    private static final String LOG_TAG = TestAnnotations.class.getSimpleName();

    public void testAnnotations() {
        SomeData someData = new SomeData();
        testSomeData(someData.getClass());
    }

    static void testSomeData(Class<?> data) {

        Annotation[] annotations = data.getAnnotations();
        assertEquals(annotations.length, 1);
        assertEquals(annotations[0].annotationType(), Table.class);

        Table t = data.getAnnotation(Table.class);

        assertEquals(t.name(), "some_data");

        List<Annotation> ls = new ArrayList<>();
        for(Field f : data.getDeclaredFields()) {
            ls.addAll(Arrays.asList(f.getDeclaredAnnotations()));

            if(f.getName().equals("rel")) {
                assertEquals(f.getDeclaredAnnotations()[0].annotationType(), OrmField.class);
                OrmField orm = f.getAnnotation(OrmField.class);
                assertEquals(orm.name(), "rel");
                assertEquals(orm.foreignKey(), true);
                assertEquals(orm.referenceTable(), "relational_data");
            }
        }

        assertEquals(ls.size(), 7);

    }
}
