package com.gabilheri.simpleorm.builders;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 12/13/14.
 */
public class ForeignKeyObject {

    private String colName;
    private String tableRef;
    private String colRef;

    public ForeignKeyObject(String colName, String tableRef) {
        this.colName = colName;
        this.tableRef = tableRef;
        this.colRef = "_ID";

    }

    public ForeignKeyObject(String colName, String tableRef, String colRef) {
        this.colName = colName;
        this.tableRef = tableRef;
        this.colRef = colRef;
    }

    public String getColName() {
        return colName;
    }

    public void setColName(String colName) {
        this.colName = colName;
    }

    public String getTableRef() {
        return tableRef;
    }

    public void setTableRef(String tableRef) {
        this.tableRef = tableRef;
    }

    public String getColRef() {
        return colRef;
    }

    public void setColRef(String colRef) {
        this.colRef = colRef;
    }

    @Override
    public String toString() {
        return "FOREIGN KEY (" + this.colName + ") REFERENCES " + this.tableRef + " (" + this.colRef + ")";
    }
}
