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
@Table(name = "pdf_generation")
public class PDFGeneration implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "pdf_generation_id_seq", sequenceName = "pdf_generation_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pdf_generation_id_seq")
    @Column(name = "id")
    private Long id;
//    @Column(name = "format_pdf")
//    private Integer format_pdf;
    @Column(name = "createdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdate;
    @Column(name = "request_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date request_time;
    @Column(name = "document")
    private String document;
    @JsonIgnore
    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToOne(cascade = CascadeType.ALL)//FetchType.LAZY
    @JoinColumn(name = "format_pdf")
    private FormatPDF format_pdf;
    @JsonIgnore
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "pdf_generation", cascade = CascadeType.ALL)
    private Collection<PDFGenerationItem> pdfGenerationItem;

    public PDFGeneration() {

    }

    public PDFGeneration(Long id, Date createdate, Date request_time, FormatPDF format_pdf, Collection<PDFGenerationItem> pdfGenerationItem) {
        this.id = id;
        this.createdate = createdate;
        this.request_time = request_time;
        this.format_pdf = format_pdf;
        this.pdfGenerationItem = pdfGenerationItem;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Date getRequest_time() {
        return request_time;
    }

    public void setRequest_time(Date request_time) {
        this.request_time = request_time;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public FormatPDF getFormat_pdf() {
        return format_pdf;
    }

    public void setFormat_pdf(FormatPDF format_pdf) {
        this.format_pdf = format_pdf;
    }

    public Collection<PDFGenerationItem> getPdfGenerationItem() {
        return pdfGenerationItem;
    }

    public void setPdfGenerationItem(Collection<PDFGenerationItem> pdfGenerationItem) {
        this.pdfGenerationItem = pdfGenerationItem;
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
        if (!(object instanceof PDFGeneration)) {
            return false;
        }
        PDFGeneration other = (PDFGeneration) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.digisign.entity.PDFGenertion[ id=" + id + " ]";
    }

}
