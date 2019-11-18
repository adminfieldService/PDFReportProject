/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.digisign.pdf.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Collection;
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
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author field
 */
@Entity
@Table(name = "format_item")
public class FormatItem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "format_item_seq", sequenceName = "format_item_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "format_item_seq")
    @Column(name = "id")
    private Long id;
//    @Column(name = "format_pdf", nullable = false)
//    private Integer format_pdf;
    @Column(name = "item_name")
    private String item_name;
    @Column(name = "item_format")
    private String item_format;
    @Column(name = "format_format_style")
    private String format_format_style;
    @Column(name = "max_char")
    private Integer max_char;
    @Column(name = "value")
    private String value;
    @Column(name = "isstatic")
    private boolean isstatic;
    @JsonIgnore
    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)//FetchType.LAZY
    @JoinColumn(name = "format_pdf")
    private FormatPDF format_pdf;
    @JsonIgnore
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "format_item", cascade = CascadeType.ALL)
    private Collection<Letakttd> letakttd;
    @JsonIgnore
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "format_item", cascade = CascadeType.ALL)
    private Collection<PDFGenerationItem> pdfGenerationItem;

    public FormatItem() {

    }

    public FormatItem(Long id, String item_name, String item_format, String format_format_style, Integer max_char, String value, boolean isstatic, FormatPDF format_pdf, Collection<Letakttd> letakttd, Collection<PDFGenerationItem> pdfGenerationItem) {
        this.id = id;
        this.item_name = item_name;
        this.item_format = item_format;
        this.format_format_style = format_format_style;
        this.max_char = max_char;
        this.value = value;
        this.isstatic = isstatic;
        this.format_pdf = format_pdf;
        this.letakttd = letakttd;
        this.pdfGenerationItem = pdfGenerationItem;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_format() {
        return item_format;
    }

    public void setItem_format(String item_format) {
        this.item_format = item_format;
    }

    public String getFormat_format_style() {
        return format_format_style;
    }

    public void setFormat_format_style(String format_format_style) {
        this.format_format_style = format_format_style;
    }

    public Integer getMax_char() {
        return max_char;
    }

    public void setMax_char(Integer max_char) {
        this.max_char = max_char;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isIsstatic() {
        return isstatic;
    }

    public void setIsstatic(boolean isstatic) {
        this.isstatic = isstatic;
    }

    public FormatPDF getFormat_pdf() {
        return format_pdf;
    }

    public void setFormat_pdf(FormatPDF format_pdf) {
        this.format_pdf = format_pdf;
    }

    public Collection<Letakttd> getLetakttd() {
        return letakttd;
    }

    public void setLetakttd(Collection<Letakttd> letakttd) {
        this.letakttd = letakttd;
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
        if (!(object instanceof FormatItem)) {
            return false;
        }
        FormatItem other = (FormatItem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.digisign.entity.FormatItem[ id=" + id + " ]";
    }

}
