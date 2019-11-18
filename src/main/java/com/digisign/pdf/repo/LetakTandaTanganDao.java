/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.digisign.pdf.repo;

/**
 *
 * @author newbiecihuy
 */
import com.digisign.pdf.entity.Letakttd;
import com.digisign.pdf.entity.PDFGeneration;
import com.digisign.util.LogSystem;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

@Repository("letakTandaTanganDao")
public class LetakTandaTanganDao {

    @PersistenceContext(unitName = "pdf_PU")
    private EntityManager entityManager;

    public Long createLetakTTD(Letakttd letakttd) {
        Long idpletakttd = null;
        try {
            entityManager.persist(letakttd);
            entityManager.flush();
            idpletakttd = letakttd.getId();
        } catch (Exception ex) {
            LogSystem.error(getClass(), ex);
            System.out.println("ERROR: " + ex.getMessage());
        }
        return idpletakttd;
    }

    public void updateLetakTTD(Letakttd letakttd) {
        try {
            entityManager.merge(letakttd);
        } catch (Exception ex) {
//            LogSystem.error(getClass(), e);
            System.out.println("ERROR: " + ex.getMessage());

        }
    }

    public Letakttd findById(Long id_letak_ttd) {
        try {
//            return entityManager.createNamedQuery("Letakttd.findById").setParameter("id", id_letak_ttd).getResultList();
            return (Letakttd) entityManager.find(Letakttd.class, id_letak_ttd);
        } catch (Exception ex) {
//            LogSystem.error(getClass(), e);
            System.out.println("ERROR: " + ex.getMessage());
            return null;

        }
    }

    public List<Letakttd> findByFormatPDF_format_item(Long idformat_pdf, Long id_formatItem) {
        try {
            String sql = "from Letakttd as l JOIN FETCH l.format_pdf as u "
                    + " JOIN FETCH l.format_item as f  where u.id = :idformat_pdf and f.id=:id_formatItem";
            Query query = entityManager.createQuery(sql);
            query.setParameter("idformat_pdf", idformat_pdf);
            query.setParameter("id_formatItem", id_formatItem);
            return (List<Letakttd>) query.getResultList();

        } catch (Exception ex) {
//            LogSystem.error(getClass(), e);
            System.out.println("ERROR: " + ex.getMessage());
            return null;

        }
    }
}
