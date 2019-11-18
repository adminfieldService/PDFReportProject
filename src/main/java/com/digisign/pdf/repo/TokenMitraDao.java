/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.digisign.pdf.repo;

import com.digisign.pdf.entity.FormatPDF;
import com.digisign.pdf.entity.TokenMitra;
import com.digisign.util.LogSystem;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author newbiecihuy
 */
@Repository("tokenMitraDao")
public class TokenMitraDao {

    @PersistenceContext(unitName = "pdf_PU")
    private EntityManager entityManager;

    public List<TokenMitra> findByToken(String token) {
        try {
//            System.out.println(" TokenMitra findByToken" + token);
            String sql = "from TokenMitra where lower(token) = :token and status_aktif='true'";
            Query query = entityManager.createQuery(sql);
            query.setParameter("token", token);
            if (query.getResultList().isEmpty() || query.getResultList() == null) {
                return null;
            } else {
                return query.getResultList();
            }
        } catch (Exception e) {
//	    	e.printStackTrace();
            LogSystem.error(getClass(), e);
            System.out.println("TokenMitra findByToken" + e.toString());
            return null;
        }
    }

    public List<TokenMitra> selectToken() {
        try {
            String sql = "from TokenMitra";
            Query query = entityManager.createQuery(sql);
            return (List<TokenMitra>) query.getResultList();

        } catch (Exception ex) {
            LogSystem.error(getClass(), ex);
            System.out.println("ERROR: " + ex.getMessage());
            return null;

        }
    }

}
