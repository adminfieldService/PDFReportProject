/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.digisign.pdf.repo;

import com.digisign.pdf.entity.FormatPDF;
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
@Repository("formatPDFDao")
public class FormatPDFDao {

    @PersistenceContext(unitName = "pdf_PU")
    private EntityManager entityManager;
//    private Long idFormatPdf;

    public Long createFormat(FormatPDF key) {
        Long idFormatPdf = null;
        try {
            entityManager.persist(key);
            entityManager.flush();
            idFormatPdf = key.getId();
        } catch (Exception ex) {
            LogSystem.error(getClass(), ex);
            System.out.println("ERROR: " + ex.getMessage());
        }
        return idFormatPdf;
    }

    public FormatPDF findById(Long idFormatPdf) {
        try {
            return (FormatPDF) entityManager.find(FormatPDF.class, idFormatPdf);
        } catch (Exception ex) {
//            LogSystem.error(getClass(), e);
            System.out.println("ERROR: " + ex.getMessage());
            return null;

        }
    }

    public List<FormatPDF> selectFormatPDF(String nama_format, Long mitra) {
        try {
//            System.out.println(" TokenMitra mitra" + mitra);
            String sql = "from FormatPDF Where lower(nama_format) =:nama_format and mitra.id = :mitra";
//           String sql = "from FormatPDF Where mitra =:mitra";
            Query query = entityManager.createQuery(sql);
            System.out.println("selectFormatPDF sql" + sql);
            query.setParameter("nama_format", nama_format);
            query.setParameter("mitra", mitra);
            return query.getResultList();

        } catch (Exception ex) {
            LogSystem.error(getClass(), ex);
            System.out.println("ERROR: " + ex.getMessage());
            return null;

        }
    }

}
