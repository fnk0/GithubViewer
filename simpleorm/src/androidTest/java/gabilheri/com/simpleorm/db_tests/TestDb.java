package gabilheri.com.simpleorm.db_tests;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import android.util.Log;

import com.gabilheri.simpleorm.SimpleOrmOpenHelper;
import com.gabilheri.simpleorm.TableBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 12/6/14.
 */
public class TestDb extends AndroidTestCase {

    private static final String LOG_TAG = TestDb.class.getSimpleName();

    public void testCreateDb() {

        SimpleOrmOpenHelper openHelper = new SimpleOrmOpenHelper(getContext(), null) {
            @Override
            public List<Class<?>> getTables() {
                List<Class<?>> tables = new ArrayList<>();
                tables.add(Person.class);
                return tables;
            }
        };

        assertEquals(openHelper.getTables().size(), 1);

        Log.d(LOG_TAG, "Creating table...");

        SQLiteDatabase db = openHelper.getWritableDatabase();

        TableBuilder.deleteTables(openHelper.getTables(), db, getContext());

        db = openHelper.getWritableDatabase();

        assertEquals(true, db.isOpen());

        ContentValues personValues = createPersonValues();
        assertTrue(personValues != null);
        long rowId;
        rowId = db.insert("person_table", null, personValues);
        assertTrue(rowId != -1);

        openHelper.close();
    }

    static ContentValues createPersonValues() {

        ContentValues personValues = new ContentValues();
        personValues.put("name", "Marcus");
        personValues.put("age", 26);
        personValues.put("birthday", "April 16, 1988");

        return personValues;
    }
}
