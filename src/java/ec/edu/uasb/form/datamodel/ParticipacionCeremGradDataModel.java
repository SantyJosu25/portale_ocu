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
public class ParticipacionCeremGradDataModel extends ListDataModel<ParticipacionCeremGradDispo> implements Serializable, SelectableDataModel<ParticipacionCeremGradDispo> {

    private static final long serialVersionUID = 11L;

    public ParticipacionCeremGradDataModel() {
    }

    public ParticipacionCeremGradDataModel(List<ParticipacionCeremGradDispo> data) {
        super(data);
    }

    @Override
    public Object getRowKey(ParticipacionCeremGradDispo t) {
        return t.getParticipacionCeremGradDispoPK();
    }

    @Override
    public ParticipacionCeremGradDispo getRowData(String rowKey) {
        List<ParticipacionCeremGradDispo> objetos = (List<ParticipacionCeremGradDispo>) getWrappedData();
        for (ParticipacionCeremGradDispo ae : objetos) {
            if (ae.getParticipacionCeremGradDispoPK().toString().equals(rowKey)){
//            if (ae.getEncuProgDispoPK().getCodigoEncuesta() == Long.parseLong(rowKey.substring(0, rowKey.indexOf("-")))
//                    && ae.getEncuProgDispoPK().getAnio() == Long.parseLong(rowKey.substring(rowKey.indexOf("-") + 1))) {
                return ae;
            }
        }
        return null;
    }
}
