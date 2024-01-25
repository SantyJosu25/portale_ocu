/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.form.datamodel;

import java.io.Serializable;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author victor.barba
 */
public class ProgEncuestaDataModel extends ListDataModel<EncuProgDispo> implements Serializable, SelectableDataModel<EncuProgDispo> {

    private static final long serialVersionUID = 11L;

    public ProgEncuestaDataModel() {
    }

    public ProgEncuestaDataModel(List<EncuProgDispo> data) {
        super(data);
    }

    @Override
    public Object getRowKey(EncuProgDispo t) {
        return t.getEncuProgDispoPK();
    }

    @Override
    public EncuProgDispo getRowData(String rowKey) {
        List<EncuProgDispo> objetos = (List<EncuProgDispo>) getWrappedData();
        for (EncuProgDispo ae : objetos) {
            if (ae.getEncuProgDispoPK().toString().equals(rowKey)){
//            if (ae.getEncuProgDispoPK().getCodigoEncuesta() == Long.parseLong(rowKey.substring(0, rowKey.indexOf("-")))
//                    && ae.getEncuProgDispoPK().getAnio() == Long.parseLong(rowKey.substring(rowKey.indexOf("-") + 1))) {
                return ae;
            }
        }
        return null;
    }
}
