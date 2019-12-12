/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.digisign.pdf.service;

import com.digisign.pdf.entity.Mitra;
import com.digisign.pdf.entity.TokenMitra;
import com.digisign.pdf.repo.MitraDao;
import com.digisign.pdf.repo.TokenMitraDao;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author newbiecihuy
 */
@Service("tokenMitraService")
public class TokenMitraService {

    @Autowired
    @Qualifier("tokenMitraDao")
    private TokenMitraDao tokenMitraDao;

    @Transactional
    public List<TokenMitra> selectAllToken() {

        return tokenMitraDao.selectToken();
    }

    @Transactional
    public List<TokenMitra> findByToken(String token) {
//        System.out.println("findByToken" + token);
        return tokenMitraDao.findByToken(token);
    }

    @Transactional
    public List<TokenMitra> findByIdMitra(Long idMitra) {
//        System.out.println("findByToken" + token);
        return tokenMitraDao.findByIdMitra(idMitra);
    }
}
