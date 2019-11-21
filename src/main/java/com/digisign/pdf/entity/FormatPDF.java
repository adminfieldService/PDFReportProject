/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.digisign.pdf.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author field
 */
@Entity
@Table(name = "format_pdf")
public class FormatPDF implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "format_pdf_seq", sequenceName = "format_pdf_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "format_pdf_seq")
    @Column(name = "id")
    private Long id;
    @Column(name = "nama_format")
    private String nama_format;
    @Column(name = "file")
    private String file;
    @Column(name = "jml_ttd")
    private Integer jml_ttd;
    @Column(name = "createdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdate;
    @Column(name = "type_format")
    private String type_format;
    @Column(name = "prefix_param")
    private String prefix_param;
//    @Column(name = "mitra")
//    private Integer mitra;
    @JsonIgnore
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "format_pdf", cascade = CascadeType.ALL)
    private Collection<FormatItem> formatItem;
    @JsonIgnore
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "format_pdf", cascade = CascadeType.ALL)
    private Collection<Letakttd> letakttd;
    @JsonIgnore
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "format_pdf", cascade = CascadeType.ALL)
    private Collection<PDFGeneration> pdfGeneration;
    @JsonIgnore
    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToOne(cascade = CascadeType.ALL)//FetchType.LAZY
    @JoinColumn(name = "mitra")
    private Mitra mitra;

    public FormatPDF() {

    }

    public FormatPDF(Long id, String nama_format, String file, Integer jml_ttd, Date createdate, String type_format, String prefix_param, Collection<FormatItem> formatItem, Collection<Letakttd> letakttd, Collection<PDFGeneration> pdfGeneration, Mitra mitra) {
        this.id = id;
        this.nama_format = nama_format;
        this.file = file;
        this.jml_ttd = jml_ttd;
        this.createdate = createdate;
        this.type_format = type_format;
        this.prefix_param = prefix_param;
        this.formatItem = formatItem;
        this.letakttd = letakttd;
        this.pdfGeneration = pdfGeneration;
        this.mitra = mitra;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNama_format() {
        return nama_format;
    }

    public void setNama_format(String nama_format) {
        this.nama_format = nama_format;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public Integer getJml_ttd() {
        return jml_ttd;
    }

    public void setJml_ttd(Integer jml_ttd) {
        this.jml_ttd = jml_ttd;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getType_format() {
        return type_format;
    }

    public void setType_format(String type_format) {
        this.type_format = type_format;
    }

    public String getPrefix_param() {
        return prefix_param;
    }

    public void setPrefix_param(String prefix_param) {
        this.prefix_param = prefix_param;
    }

    public Collection<FormatItem> getFormatItem() {
        return formatItem;
    }

    public void setFormatItem(Collection<FormatItem> formatItem) {
        this.formatItem = formatItem;
    }

    public Collection<Letakttd> getLetakttd() {
        return letakttd;
    }

    public void setLetakttd(Collection<Letakttd> letakttd) {
        this.letakttd = letakttd;
    }

    public Collection<PDFGeneration> getPdfGeneration() {
        return pdfGeneration;
    }

    public void setPdfGeneration(Collection<PDFGeneration> pdfGeneration) {
        this.pdfGeneration = pdfGeneration;
    }

    public Mitra getMitra() {
        return mitra;
    }

    public void setMitra(Mitra mitra) {
        this.mitra = mitra;
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
        if (!(object instanceof FormatPDF)) {
            return false;
        }
        FormatPDF other = (FormatPDF) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.digisign.entity.FormatPDF[ id=" + id + " ]";
    }

}
