/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.digisign.pdf.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author field
 */
@Entity
@Table(name = "pdf_generation_item")
public class PDFGenerationItem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "pdf_generation_item_id_seq", sequenceName = "pdf_generation_item_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pdf_generation_item_id_seq")
    @Column(name = "id")
    private Long id;
//    @Column(name = "format_item")
//    private Integer format_item;
    @Column(name = "value")
    private String value;
//    @Column(name = "pdf_generation")
//    private Integer pdf_generation;
    @JsonIgnore
    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToOne(cascade = CascadeType.ALL)//FetchType.LAZY
    @JoinColumn(name = "format_item")
    private FormatItem format_item;
    @JsonIgnore
    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToOne(cascade = CascadeType.ALL)//FetchType.LAZY
    @JoinColumn(name = "pdf_generation")
    private PDFGeneration pdf_generation;

    public PDFGenerationItem() {

    }

    public PDFGenerationItem(Long id, String value, FormatItem format_item, PDFGeneration pdf_generation) {
        this.id = id;
        this.value = value;
        this.format_item = format_item;
        this.pdf_generation = pdf_generation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public FormatItem getFormat_item() {
        return format_item;
    }

    public void setFormat_item(FormatItem format_item) {
        this.format_item = format_item;
    }

    public PDFGeneration getPdf_generation() {
        return pdf_generation;
    }

    public void setPdf_generation(PDFGeneration pdf_generation) {
        this.pdf_generation = pdf_generation;
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
        if (!(object instanceof PDFGenerationItem)) {
            return false;
        }
        PDFGenerationItem other = (PDFGenerationItem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.digisign.entity.PDFGenerationItem[ id=" + id + " ]";
    }

}
