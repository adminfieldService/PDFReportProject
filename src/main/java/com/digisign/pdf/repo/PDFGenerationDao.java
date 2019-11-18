/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.digisign.pdf.repo;

import com.digisign.pdf.entity.FormatItem;
import com.digisign.pdf.entity.PDFGeneration;
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
@Repository("pdfGenerationDao")
public class PDFGenerationDao {

    @PersistenceContext(unitName = "pdf_PU")
    private EntityManager entityManager;

    public Long generatePDF(PDFGeneration pdfGenertate) {
        Long idpdfGenerate = null;
        try {
            entityManager.persist(pdfGenertate);
            entityManager.flush();
            idpdfGenerate = pdfGenertate.getId();
        } catch (Exception ex) {
            LogSystem.error(getClass(), ex);
            System.out.println("ERROR: " + ex.getMessage());
        }
        return idpdfGenerate;
    }

    public void updatePDF(PDFGeneration pdfGenertate) {
        try {
            entityManager.merge(pdfGenertate);
        } catch (Exception ex) {
//            LogSystem.error(getClass(), e);
            System.out.println("ERROR: " + ex.getMessage());

        }
    }

    public PDFGeneration findById(Long idpdfGenerate) {
        try {
            return (PDFGeneration) entityManager.find(PDFGeneration.class, idpdfGenerate);
        } catch (Exception ex) {
//            LogSystem.error(getClass(), e);
            System.out.println("ERROR: " + ex.getMessage());
            return null;

        }
    }

    public List<PDFGeneration> findByFormatPDF(Long idFormatPDF) {
        try {
            String sql = "from PDFGeneration as f JOIN fetch f.format_pdf as u where u.id = :idformat_pdf";
            Query query = entityManager.createQuery(sql);
            query.setParameter("idformat_pdf", idFormatPDF);
            return (List<PDFGeneration>) query.getResultList();
        } catch (Exception ex) {
//            LogSystem.error(getClass(), e);
            System.out.println("ERROR: " + ex.getMessage());
            return null;

        }
    }

    public List<PDFGeneration> selectPdfGeneration() {
        try {
            String sql = "from PDFGeneration";
            Query query = entityManager.createQuery(sql);
            return (List<PDFGeneration>) query.getResultList();

        } catch (Exception ex) {
//            LogSystem.error(getClass(), e);
            System.out.println("ERROR: " + ex.getMessage());
            return null;

        }
    }

}
