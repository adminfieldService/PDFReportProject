/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.digisign.pdf.service;

import com.digisign.pdf.entity.PDFGenerationItem;
import com.digisign.pdf.repo.PDFGenerationDao;
import com.digisign.pdf.repo.PDFGenerationItemDao;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author newbiecihuy
 */
@Service("pdfGenerationItemService")
public class PDFGenerationItemService {

    @Autowired
    @Qualifier("pdfGenerationItemDao")
    private PDFGenerationItemDao pdfGenerationItemDao;

    @Transactional
    public Long generatePDFItem(PDFGenerationItem pdfGenerate) {
        return pdfGenerationItemDao.generatePDFItem(pdfGenerate);
    }

    @Transactional
    public void updatePDFGenertateItem(PDFGenerationItem pdfGenerateItem) {
        pdfGenerationItemDao.updatePDFGenertateItem(pdfGenerateItem);
    }

    @Transactional
    public PDFGenerationItem findById(Long idpdfGenerateItem) {

        return pdfGenerationItemDao.findById(idpdfGenerateItem);
    }

    @Transactional
    public List<PDFGenerationItem> findByFormatItem(Long idFormatItem) {
        return pdfGenerationItemDao.findByFormatItem(idFormatItem);
    }

    @Transactional
    public int setIdPdfGnerate(Long idPdfGenerate) {
        return pdfGenerationItemDao.setIdPdfGnerate(idPdfGenerate);
    }
}
