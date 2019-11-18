/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.digisign.pdf.service;

import com.digisign.pdf.repo.ReportDao;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author newbiecihuy
 */
@Service("reportService")
public class ReportService {

    @Autowired
    @Qualifier("reportDao")
    private ReportDao reportDao;

     public byte[] reportPdf(final JasperPrint jp) throws JRException {
        return reportDao.getReportPdf(jp);
    }
}
