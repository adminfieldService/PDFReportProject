/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.digisign.pdf.service;

import com.digisign.pdf.entity.FormatItem;
import com.digisign.pdf.entity.FormatPDF;
import com.digisign.pdf.entity.PDFGeneration;
import com.digisign.pdf.repo.FormatItemDao;
import com.digisign.pdf.repo.PDFGenerationDao;
import java.util.List;
import javax.transaction.Transactional;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author newbi
 */
@Service("formatItemService")
public class FormatItemService {

    @Autowired
    @Qualifier("formatItemDao")
    private FormatItemDao formatItemDao;

    @Transactional
    public Long createFormatItem(FormatItem key) {
        return formatItemDao.createFormatItem(key);
    }

    @Transactional
    public void updateFormatItem(FormatItem key) {
        formatItemDao.updateFormatItem(key);
    }

    @Transactional
    public List<FormatItem> selectFormatItem() {

        return formatItemDao.selectFormatItem();
    }

    @Transactional
    public FormatItem findById(Long idFormatItem) {
        return formatItemDao.findById(idFormatItem);
    }

    @Transactional
    public List<FormatItem> findByIdFormatPdf(Long idFormatItem) {

        return formatItemDao.findByIdFormatPdf(idFormatItem);
    }

    @Transactional
    public List<FormatItem> findByIdFormatPdf_itemName(Long idFormatItem, String itemName) {

        return formatItemDao.findByIdFormatPdf_itemName(idFormatItem, itemName);
    }
}
