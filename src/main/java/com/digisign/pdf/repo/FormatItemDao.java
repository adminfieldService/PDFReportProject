/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.digisign.pdf.repo;

import com.digisign.pdf.entity.FormatItem;
import com.digisign.util.LogSystem;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author newbi
 */
@Repository("formatItemDao")
public class FormatItemDao {

    @PersistenceContext(unitName = "pdf_PU")
    private EntityManager entityManager;

    public Long createFormatItem(FormatItem key) {
        Long idFormatItem = null;
        try {
            entityManager.persist(key);
            entityManager.flush();
            idFormatItem = key.getId();
        } catch (Exception ex) {
            LogSystem.error(getClass(), ex);
            System.out.println("ERROR: " + ex.getMessage());
        }
        return idFormatItem;
    }

    public void updateFormatItem(FormatItem dataFormatItem) {
        try {
            entityManager.merge(dataFormatItem);
        } catch (Exception ex) {
//            LogSystem.error(getClass(), e);
            System.out.println("ERROR: " + ex.getMessage());

        }
    }

    public FormatItem findById(Long idFormatItem) {
        try {
            return (FormatItem) entityManager.find(FormatItem.class, idFormatItem);
        } catch (Exception ex) {
//            LogSystem.error(getClass(), e);
            System.out.println("ERROR: " + ex.getMessage());
            return null;

        }
    }

    public List<FormatItem> selectFormatItem() {
        try {
            String sql = "from FormatItem";
            Query query = entityManager.createQuery(sql);
            return (List<FormatItem>) query.getResultList();

        } catch (Exception ex) {
//            LogSystem.error(getClass(), e);
            System.out.println("ERROR: " + ex.getMessage());
            return null;

        }
    }

    public List<FormatItem> findByIdFormatPdf(Long idFormatItem) {
        try {
//            String sql = "from FormatItem where format_pdf.id =: idformat_pdf";
            String sql = "from FormatItem as f JOIN fetch f.format_pdf as u where u.id = :idformat_pdf";
            Query query = entityManager.createQuery(sql);
            query.setParameter("idformat_pdf", idFormatItem);
            return (List<FormatItem>) query.getResultList();
        } catch (Exception ex) {
//            LogSystem.error(getClass(), e);
            System.out.println("ERROR: " + ex.getMessage());
            return null;

        }
    }

    public List<FormatItem> findByIdFormatPdf_itemName(Long idFormatItem, String itemName) {
        try {
//            String sql = "from FormatItem where format_pdf.id =: idformat_pdf";
            String sql = "from FormatItem as f JOIN fetch f.format_pdf as u where u.id = :idformat_pdf and item_name=:itemName";
            Query query = entityManager.createQuery(sql);
            query.setParameter("idformat_pdf", idFormatItem);
            query.setParameter("itemName", itemName);
            return (List<FormatItem>) query.getResultList();
        } catch (Exception ex) {
//            LogSystem.error(getClass(), e);
            System.out.println("ERROR: " + ex.getMessage());
            return null;

        }
    }

}
