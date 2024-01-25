/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import ec.edu.uasb.config.ConfigurarRepositorio;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author victor.barba
 */
@Named(value = "fileDownloadView")
@RequestScoped
public class FileDownloadView {

    private StreamedContent contenido;

    public StreamedContent getContenido() {
        return contenido;
    }

    public FileDownloadView() {
        try {
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ConfigurarRepositorio configDoc = new ConfigurarRepositorio(ec.getInitParameter("directorio.principal"), ec.getInitParameter("directorio.storage"));
            String modulo = (String) ec.getInitParameter("modulo");
            String[] pathRepositorio = {"Documentos", modulo, "certificados"};
            String pathDocFirmado = configDoc.getPathDoc(pathRepositorio) + "17_NOTAS.pdf";
            System.out.println(pathDocFirmado);
            contenido = new DefaultStreamedContent(new FileInputStream(pathDocFirmado), "application/pdf", "CERTIFICADO.pdf");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileDownloadView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
