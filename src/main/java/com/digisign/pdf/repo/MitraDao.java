/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.digisign.pdf.repo;

import com.digisign.pdf.entity.Letakttd;
import com.digisign.pdf.entity.Mitra;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

/**
 *
 * @author newbiecihuy
 */
@Repository("mitraDao")
public class MitraDao {

    @PersistenceContext(unitName = "pdf_PU")
    private EntityManager entityManager;

    public Mitra findById(Long id_mitra) {
        try {
//            return entityManager.createNamedQuery("Mitra.findById").setParameter("id", id_mitra).getResultList();
            return (Mitra) entityManager.find(Mitra.class, id_mitra);
        } catch (Exception ex) {
//            LogSystem.error(getClass(), e);
            System.out.println("ERROR: " + ex.getMessage());
            return null;

        }
    }
}
