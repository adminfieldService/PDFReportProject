/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.digisign.pdf.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author newbiecihuy
 */
@Entity
@Table(name = "mitra")
@NamedQueries({
    @javax.persistence.NamedQuery(name = "Mitra.findAll", query = "SELECT c FROM Mitra c")
    ,
    @javax.persistence.NamedQuery(name = "Mitra.findById", query = "SELECT c FROM Mitra c where c.id = :id")
})
public class Mitra implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "mitra_sq", sequenceName = "mitra_sq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mitra_sq")
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "verifikasi")
    private boolean verifikasi;
    @Column(name = "notifikasi")
    private boolean notifikasi;
    @Column(name = "ekyc")
    private boolean ekyc;
    @Column(name = "level")
    private String level;
    @Column(name = "activation_redirect")
    private String activation_redirect;
    @Column(name = "signing_redirect")
    private String signing_redirect;
    @Column(name = "dukcapil")
    private boolean dukcapil;
    @Column(name = "ekyc_tabungan")
    private boolean ekyc_tabungan;
    @Column(name = "user_sftp")
    private String user_sftp;
    @Column(name = "no_npwp")
    private String no_npwp;
    @Column(name = "no_siup")
    private String no_siup;
    @Column(name = "no_tdp")
    private String no_tdp;
    @Column(name = "no_akta_perusahaan")
    private String no_akta_perusahaan;
    @Column(name = "no_surat_domisili")
    private String no_surat_domisili;
    @Column(name = "i_siup")
    private String i_siup;
    @Column(name = "i_npwp")
    private String i_npwp;
    @Column(name = "i_tdp")
    private String i_tdp;
    @Column(name = "i_akta_perusahaan")
    private String i_akta_perusahaan;
    @Column(name = "i_surat_domisili")
    private String i_surat_domisili;
//    
//    @Column(name = "parent_id")
//    private Long parent_id;
//    
    @JsonIgnore
    @OneToMany(mappedBy = "parent_id", cascade = CascadeType.ALL)//, fetch = FetchType.EAGER
    private List<Mitra> child;
//    
    @JsonIgnore
    @JoinColumn(name = "parent_id")
    @ManyToOne(cascade = CascadeType.ALL)//FetchType.LAZY
    private Mitra parent_id;
//
    @JsonIgnore
    @OneToMany(mappedBy = "mitra", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Collection<FormatPDF> formatPDF;
//    
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "mitra")
    private TokenMitra tokenMitra;

//    @OneToMany(mappedBy = "parent_id", cascade = {CascadeType.ALL})
//    private Collection<Mitra> children;
//    @ManyToOne()
//    private Mitra parent_id;
    public Mitra() {
    }

    public Mitra(Long id, String name, boolean verifikasi, boolean notifikasi, boolean ekyc, String level, String activation_redirect, String signing_redirect, boolean dukcapil, boolean ekyc_tabungan, String user_sftp, String no_npwp, String no_siup, String no_tdp, String no_akta_perusahaan, String no_surat_domisili, String i_siup, String i_npwp, String i_tdp, String i_akta_perusahaan, String i_surat_domisili, List<Mitra> child, Mitra parent_id, Collection<FormatPDF> formatPDF, TokenMitra tokenMitra) {
        this.id = id;
        this.name = name;
        this.verifikasi = verifikasi;
        this.notifikasi = notifikasi;
        this.ekyc = ekyc;
        this.level = level;
        this.activation_redirect = activation_redirect;
        this.signing_redirect = signing_redirect;
        this.dukcapil = dukcapil;
        this.ekyc_tabungan = ekyc_tabungan;
        this.user_sftp = user_sftp;
        this.no_npwp = no_npwp;
        this.no_siup = no_siup;
        this.no_tdp = no_tdp;
        this.no_akta_perusahaan = no_akta_perusahaan;
        this.no_surat_domisili = no_surat_domisili;
        this.i_siup = i_siup;
        this.i_npwp = i_npwp;
        this.i_tdp = i_tdp;
        this.i_akta_perusahaan = i_akta_perusahaan;
        this.i_surat_domisili = i_surat_domisili;
        this.child = child;
        this.parent_id = parent_id;
        this.formatPDF = formatPDF;
        this.tokenMitra = tokenMitra;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVerifikasi() {
        return verifikasi;
    }

    public void setVerifikasi(boolean verifikasi) {
        this.verifikasi = verifikasi;
    }

    public boolean isNotifikasi() {
        return notifikasi;
    }

    public void setNotifikasi(boolean notifikasi) {
        this.notifikasi = notifikasi;
    }

    public boolean isEkyc() {
        return ekyc;
    }

    public void setEkyc(boolean ekyc) {
        this.ekyc = ekyc;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getActivation_redirect() {
        return activation_redirect;
    }

    public void setActivation_redirect(String activation_redirect) {
        this.activation_redirect = activation_redirect;
    }

    public String getSigning_redirect() {
        return signing_redirect;
    }

    public void setSigning_redirect(String signing_redirect) {
        this.signing_redirect = signing_redirect;
    }

    public boolean isDukcapil() {
        return dukcapil;
    }

    public void setDukcapil(boolean dukcapil) {
        this.dukcapil = dukcapil;
    }

    public boolean isEkyc_tabungan() {
        return ekyc_tabungan;
    }

    public void setEkyc_tabungan(boolean ekyc_tabungan) {
        this.ekyc_tabungan = ekyc_tabungan;
    }

    public String getUser_sftp() {
        return user_sftp;
    }

    public void setUser_sftp(String user_sftp) {
        this.user_sftp = user_sftp;
    }

    public String getNo_npwp() {
        return no_npwp;
    }

    public void setNo_npwp(String no_npwp) {
        this.no_npwp = no_npwp;
    }

    public String getNo_siup() {
        return no_siup;
    }

    public void setNo_siup(String no_siup) {
        this.no_siup = no_siup;
    }

    public String getNo_tdp() {
        return no_tdp;
    }

    public void setNo_tdp(String no_tdp) {
        this.no_tdp = no_tdp;
    }

    public String getNo_akta_perusahaan() {
        return no_akta_perusahaan;
    }

    public void setNo_akta_perusahaan(String no_akta_perusahaan) {
        this.no_akta_perusahaan = no_akta_perusahaan;
    }

    public String getNo_surat_domisili() {
        return no_surat_domisili;
    }

    public void setNo_surat_domisili(String no_surat_domisili) {
        this.no_surat_domisili = no_surat_domisili;
    }

    public String getI_siup() {
        return i_siup;
    }

    public void setI_siup(String i_siup) {
        this.i_siup = i_siup;
    }

    public String getI_npwp() {
        return i_npwp;
    }

    public void setI_npwp(String i_npwp) {
        this.i_npwp = i_npwp;
    }

    public String getI_tdp() {
        return i_tdp;
    }

    public void setI_tdp(String i_tdp) {
        this.i_tdp = i_tdp;
    }

    public String getI_akta_perusahaan() {
        return i_akta_perusahaan;
    }

    public void setI_akta_perusahaan(String i_akta_perusahaan) {
        this.i_akta_perusahaan = i_akta_perusahaan;
    }

    public String getI_surat_domisili() {
        return i_surat_domisili;
    }

    public void setI_surat_domisili(String i_surat_domisili) {
        this.i_surat_domisili = i_surat_domisili;
    }

    public List<Mitra> getChild() {
        return child;
    }

    public void setChild(List<Mitra> child) {
        this.child = child;
    }

    public Mitra getParent_id() {
        return parent_id;
    }

    public void setParent_id(Mitra parent_id) {
        this.parent_id = parent_id;
    }

    public Collection<FormatPDF> getFormatPDF() {
        return formatPDF;
    }

    public void setFormatPDF(Collection<FormatPDF> formatPDF) {
        this.formatPDF = formatPDF;
    }

    public TokenMitra getTokenMitra() {
        return tokenMitra;
    }

    public void setTokenMitra(TokenMitra tokenMitra) {
        this.tokenMitra = tokenMitra;
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
        if (!(object instanceof Mitra)) {
            return false;
        }
        Mitra other = (Mitra) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.digisign.entity.Mitra[ id=" + id + " ]";
    }

}
