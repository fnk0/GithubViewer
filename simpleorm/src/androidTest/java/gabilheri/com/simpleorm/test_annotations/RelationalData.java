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
@Table(name = "relational_data")
public class RelationalData {

    @Unique
    @NotNull
    @Increments
    @OrmField(name = "id")
    private long id;

    @OrmField(name = "related_data")
    private String relatedData;
}
