/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.digisign.pdf.service;

import com.digisign.pdf.entity.Letakttd;
import com.digisign.pdf.repo.LetakTandaTanganDao;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author newbiecihuy
 */
@Service("letakTandaTanganService")
public class LetakTandaTanganService {

    @Autowired
    @Qualifier("letakTandaTanganDao")
    private LetakTandaTanganDao letakTandaTanganDao;

    @Transactional
    public Long createLetakTTD(Letakttd letakttd) {
        return letakTandaTanganDao.createLetakTTD(letakttd);
    }

    @Transactional
    public Letakttd findById(Long id_letak_ttd) {
        return letakTandaTanganDao.findById(id_letak_ttd);
    }

    @Transactional
    public List<Letakttd> findByFormatPDF_format_item(Long idformat_pdf, Long id_formatItem) {
        return letakTandaTanganDao.findByFormatPDF_format_item(idformat_pdf, id_formatItem);
    }

}
