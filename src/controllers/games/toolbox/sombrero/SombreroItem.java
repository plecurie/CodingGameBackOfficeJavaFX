package controllers.games.toolbox.sombrero;

import javafx.scene.image.ImageView;

class SombreroItem extends ImageView {
    private int rowId;
    private int columnId;

    SombreroItem(String url, int rowId, int columnId) {
        super(url);
        setRowId(rowId);
        setColumnId(columnId);
    }

    int getRowId() { return rowId; }
    void setRowId(int rowId) { this.rowId = rowId; }
    int getColumnId() { return columnId; }
    void setColumnId(int columnId) { this.columnId = columnId; }

}
