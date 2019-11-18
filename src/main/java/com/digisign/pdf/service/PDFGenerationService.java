/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.digisign.pdf.service;

import com.digisign.pdf.entity.FormatPDF;
import com.digisign.pdf.entity.PDFGeneration;
import com.digisign.pdf.repo.PDFGenerationDao;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author newbiecihuy
 */
@Service("pdfGenerationService")
public class PDFGenerationService {

    @Autowired
    @Qualifier("pdfGenerationDao")
    private PDFGenerationDao pdfGenerationDao;

    @Transactional
    public Long generatePDF(PDFGeneration pdfGenerate) {
        return pdfGenerationDao.generatePDF(pdfGenerate);
    }

    @Transactional
    public void udatePDF(PDFGeneration pdfGenerate) {
        pdfGenerationDao.updatePDF(pdfGenerate);
    }

    @Transactional
    public PDFGeneration findById(Long idpdfGenerate) {
       return pdfGenerationDao.findById(idpdfGenerate);
    }

    @Transactional
    public List<PDFGeneration> findByFormatPDF(Long idFormatPDF) {
        return pdfGenerationDao.findByFormatPDF(idFormatPDF);
    }

    @Transactional
    public List<PDFGeneration> selectPdfGeneration() {

        return pdfGenerationDao.selectPdfGeneration();

    }
//    OutputStream outputStream = response.getOutputStream();
//
//    Map parameters = (Map) model.get("parameters");
//
//    URL reportTemplate = getClass().getClassLoader().getResource("pathToFile/file.jrxml");
//    JasperReport jasperReport = JasperCompileManager.compileReport(reportTemplate.getPath());
//    jasperPrint  = JasperFillManager.fillReport(jasperReport, parameters);
//
//    JasperExportManager.exportReportToPdfStream (jasperPrint, outputStream);
}
