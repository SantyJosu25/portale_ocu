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
public class EncuestaTutoDataModel extends ListDataModel<EncuTutoDispo> implements Serializable, SelectableDataModel<EncuTutoDispo> {

    private static final long serialVersionUID = 11L;

    public EncuestaTutoDataModel() {
    }

    public EncuestaTutoDataModel(List<EncuTutoDispo> data) {
        super(data);
    }

    @Override
    public Object getRowKey(EncuTutoDispo t) {
        return t.getEncuTutoDispoPK();
    }

    @Override
    public EncuTutoDispo getRowData(String rowKey) {
        List<EncuTutoDispo> objetos = (List<EncuTutoDispo>) getWrappedData();
        for (EncuTutoDispo ae : objetos) {
            if (ae.getEncuTutoDispoPK().toString().equals(rowKey)){
//            if (ae.getEncuProgDispoPK().getCodigoEncuesta() == Long.parseLong(rowKey.substring(0, rowKey.indexOf("-")))
//                    && ae.getEncuProgDispoPK().getAnio() == Long.parseLong(rowKey.substring(rowKey.indexOf("-") + 1))) {
                return ae;
            }
        }
        return null;
    }
}
