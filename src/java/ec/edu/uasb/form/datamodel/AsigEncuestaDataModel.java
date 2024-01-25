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
public class AsigEncuestaDataModel extends ListDataModel<EncuAsigDispo> implements Serializable, SelectableDataModel<EncuAsigDispo> {

    private static final long serialVersionUID = 1L;

    public AsigEncuestaDataModel() {
    }

    public AsigEncuestaDataModel(List<EncuAsigDispo> data) {
        super(data);
    }

    @Override
    public Object getRowKey(EncuAsigDispo t) {
        return t.getEncuAsigDispoPK();
    }

    @Override
    public EncuAsigDispo getRowData(String rowKey) {
        List<EncuAsigDispo> objetos = (List<EncuAsigDispo>) getWrappedData();
        for (EncuAsigDispo ae : objetos) {
            if (ae.getEncuAsigDispoPK().toString().equals(rowKey)) {
//            if (ae.getEncuAsigDispoPK().getCodigoMateria() == Long.parseLong(rowKey.substring(0, rowKey.indexOf("-")))
//                    && ae.getEncuAsigDispoPK().getCodigoProfesor() == Long.parseLong(rowKey.substring(rowKey.indexOf("-") + 1))) {
                return ae;
            }
        }
        return null;
    }
}
