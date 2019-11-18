/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.digisign.pdf.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author newbiecihuy
 */
@Entity
@Table(name = "token_mitra")
@NamedQueries({
    @javax.persistence.NamedQuery(name = "TokenMitra.findAll", query = "SELECT t FROM TokenMitra t")
    ,
    @javax.persistence.NamedQuery(name = "TokenMitra.findById", query = "SELECT t FROM TokenMitra t where t.id = :id")
})
public class TokenMitra implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "token_mitra_sq", sequenceName = "token_mitra_sq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "token_mitra_sq")
    @Column(name = "id")
    private Long id;
    @OneToOne
    @JoinColumn(name = "mitra", referencedColumnName = "id")
    private Mitra mitra;
    @Column(name = "token")
    private String token;
    @Column(name = "status_aktif")
    private boolean status_aktif;
    @Column(name = "create_date")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date create_date;
    @Column(name = "update_date")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date update_date;

    public TokenMitra() {

    }

    public TokenMitra(Long id, Mitra mitra, String token, boolean status_aktif, Date create_date, Date update_date) {
        this.id = id;
        this.mitra = mitra;
        this.token = token;
        this.status_aktif = status_aktif;
        this.create_date = create_date;
        this.update_date = update_date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Mitra getMitra() {
        return mitra;
    }

    public void setMitra(Mitra mitra) {
        this.mitra = mitra;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isStatus_aktif() {
        return status_aktif;
    }

    public void setStatus_aktif(boolean status_aktif) {
        this.status_aktif = status_aktif;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public Date getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(Date update_date) {
        this.update_date = update_date;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TokenMitra)) {
            return false;
        }
        TokenMitra other = (TokenMitra) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.digisign.pdf.entity.TokenMitra[ id=" + id + " ]";
    }

}
