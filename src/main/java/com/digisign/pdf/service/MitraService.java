/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.digisign.pdf.service;

import com.digisign.pdf.entity.Mitra;
import com.digisign.pdf.repo.MitraDao;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author newbiecihuy
 */
@Service("mitraService")
public class MitraService {

    @Autowired
    @Qualifier("mitraDao")
    private MitraDao mitraDao;

    @Transactional
    public Mitra findById(Long id) {

        return mitraDao.findById(id);
    }
}
