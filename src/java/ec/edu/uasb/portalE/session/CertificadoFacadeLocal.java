/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.portalE.session;

import ec.edu.uasb.portalE.entities.Certificado;
import ec.edu.uasb.seg.entities.Usuario;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;

/**
 *
 * @author victor.barba
 */
@Local
public interface CertificadoFacadeLocal {

    void create(Certificado certificado);

    void edit(Certificado certificado);

    void remove(Certificado certificado);

    Certificado find(Object id);

    List<Certificado> findAll();

    List<Certificado> findRange(int[] range);

    int count();

    public Certificado findByNroCertif(String nroCertif);

    public List<Certificado> findAllBy(Usuario user);

    public BigDecimal findPersonaBy(String id);

    public void genDocuParaFirma(String rep, String docuXfirmar, String URLVerif, Map<String, Object> params, Certificado certificado) throws Exception;

    public Certificado findByCerNumero(String cerNumero);

}
