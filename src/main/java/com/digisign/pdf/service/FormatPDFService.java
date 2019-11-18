/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.digisign.pdf.service;

import com.digisign.pdf.entity.FormatItem;
import com.digisign.pdf.entity.FormatPDF;
import com.digisign.pdf.repo.FormatItemDao;
import com.digisign.pdf.repo.FormatPDFDao;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author newbiecihuy
 */
@Service("formatPDFService")
public class FormatPDFService {

    @Autowired
    @Qualifier("formatPDFDao")
    private FormatPDFDao formatPDFDao;

    @Transactional
    public Long createFormat(FormatPDF formatPDF) {
        return formatPDFDao.createFormat(formatPDF);
    }

    @Transactional
    public FormatPDF findById(Long idFormatPdf) {
        return formatPDFDao.findById(idFormatPdf);
    }

    @Transactional
    public List<FormatPDF> selectFormatPDF(String nama_format, Long mitra) {

        return formatPDFDao.selectFormatPDF(nama_format, mitra);
    }
}
