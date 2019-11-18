/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.digisign.pdf.repo;

import com.digisign.pdf.entity.PDFGenerationItem;
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
@Repository("pdfGenerationItemDao")
public class PDFGenerationItemDao {

    @PersistenceContext(unitName = "pdf_PU")
    private EntityManager entityManager;

    public Long generatePDFItem(PDFGenerationItem pdfGenertateItem) {
        Long idpdfGenerateItem = null;
        try {
            entityManager.persist(pdfGenertateItem);
            entityManager.flush();
            idpdfGenerateItem = pdfGenertateItem.getId();
        } catch (Exception ex) {
            LogSystem.error(getClass(), ex);
            System.out.println("ERROR: " + ex.getMessage());
        }
        return idpdfGenerateItem;
    }

    public void updatePDFGenertateItem(PDFGenerationItem pdfGenertateItem) {
        try {
            entityManager.merge(pdfGenertateItem);
        } catch (Exception ex) {
//            LogSystem.error(getClass(), e);
            System.out.println("ERROR: " + ex.getMessage());

        }
    }

    public PDFGenerationItem findById(Long idpdfGenerateItem) {
        try {
            return (PDFGenerationItem) entityManager.find(PDFGenerationItem.class, idpdfGenerateItem);
        } catch (Exception ex) {
//            LogSystem.error(getClass(), e);
            System.out.println("ERROR: " + ex.getMessage());
            return null;

        }
    }

    public List<PDFGenerationItem> findByFormatItem(Long idFormatItem) {
        try {
            String sql = "from PDFGenerationItem as f JOIN fetch f.format_item as u where u.id = :idFormatItem";
            Query query = entityManager.createQuery(sql);
            query.setParameter("idFormatItem", idFormatItem);
            return (List<PDFGenerationItem>) query.getResultList();
        } catch (Exception ex) {
//            LogSystem.error(getClass(), e);
            System.out.println("ERROR: " + ex.getMessage());
            return null;

        }
    }
}
