/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.extendedTags;

import java.io.IOException;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import org.primefaces.component.column.Column;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.subtable.SubTable;
import org.primefaces.component.subtable.SubTableRenderer;

/**
 *
 * @author victor.barba
 */
public class ExSubTableRenderer extends SubTableRenderer {

    @Override
    public void encodeRow(FacesContext context, SubTable table, int rowIndex) throws IOException {
        table.setRowIndex(rowIndex);
        if (!table.isRowAvailable()) {
            return;
        }

        ResponseWriter writer = context.getResponseWriter();
        String clientId = table.getClientId(context);

        writer.startElement("tr", null);
        writer.writeAttribute("id", clientId + "_row_" + rowIndex, null);

        String rowStyleClass = rowIndex % 2 == 0 ? DataTable.ROW_CLASS + " ui-datatable-even": DataTable.ROW_CLASS + " ui-datatable-odd";
        writer.writeAttribute("class", rowStyleClass, null);

        for (Column column : table.getColumns()) {
            String style = column.getStyle();
            String styleClass = column.getStyleClass();

            writer.startElement("td", null);
            if (style != null) {
                writer.writeAttribute("style", style, null);
            }
            if (styleClass != null) {
                writer.writeAttribute("class", styleClass, null);
            }

            column.encodeAll(context);

            writer.endElement("td");
        }

        writer.endElement("tr");
    }
}
