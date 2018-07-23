package controllers.games.toolbox.sombrero;

import javafx.scene.image.ImageView;

class SombreroItem extends ImageView {
    private int rowId;
    private int columnId;
    private static SombreroItem arrow;

    SombreroItem(String url, int rowId, int columnId) {
        super(url);
        setRowId(rowId);
        setColumnId(columnId);
    }

    static SombreroItem getArrow() { return arrow; }
    static void setArrow(SombreroItem arrow, double x, double y) {
        SombreroItem.arrow = arrow;
        SombreroItem.arrow.setX(x);
        SombreroItem.arrow.setX(y);
    }

    int getRowId() { return rowId; }
    void setRowId(int rowId) { this.rowId = rowId; }
    int getColumnId() { return columnId; }
    void setColumnId(int columnId) { this.columnId = columnId; }

}
