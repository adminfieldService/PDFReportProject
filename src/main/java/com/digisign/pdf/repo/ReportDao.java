/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.digisign.pdf.repo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.stereotype.Repository;

/**
 *
 * @author newbiecihuy
 */
@Repository("reportDao")
public class ReportDao {

    @PersistenceContext(unitName = "pdf_PU")
    private EntityManager entityManager;

    public ReportDao() {

    }

    public byte[] getReportPdf(final JasperPrint jp) throws JRException {
        return JasperExportManager.exportReportToPdf(jp);
    }
}
