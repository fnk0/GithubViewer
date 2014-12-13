package com.gabilheri.githubviewer.data;

import com.gabilheri.simpleorm.OrmObject;
import com.gabilheri.simpleorm.annotations.OrmField;
import com.gabilheri.simpleorm.annotations.Table;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 12/13/14.
 */
@Table(name = "dummy_extension")
public class DummyExtension extends OrmObject {

    @OrmField(name = "dname")
    private String dName;

    @OrmField(name = "price")
    private double price;

    public String getdName() {
        return dName;
    }

    public void setdName(String dName) {
        this.dName = dName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
