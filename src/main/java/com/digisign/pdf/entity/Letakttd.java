/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.digisign.pdf.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
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
import javax.persistence.NamedQueries;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author field
 */
@Entity
@Table(name = "letak_ttd")
@NamedQueries({
    @javax.persistence.NamedQuery(name = "Letakttd.findAll", query = "SELECT l FROM Letakttd l")
    ,
    @javax.persistence.NamedQuery(name = "Letakttd.findById", query = "SELECT l FROM Letakttd l where l.id = :id")
})
public class Letakttd implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "letak_ttd_seq", sequenceName = "letak_ttd_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "letak_ttd_seq")
    @Column(name = "id")
    private Long id;
    @Column(name = "ttd_ke")
    private Integer ttd_ke;
    @Column(name = "page")
    private Integer page;
    @Column(name = "lx")
    private String lx;
    @Column(name = "ly")
    private String ly;
    @Column(name = "rx")
    private String rx;
    @Column(name = "ry")
    private String ry;
    @Column(name = "createdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdate;
    @Column(name = "prf_ke")
    private Integer prf_ke;
//    @Column(name = "format_item")
//    private Integer format_item;
//    @Column(name = "format_pdf", nullable = false)
//    private Integer format_pdf;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)//FetchType.LAZY
    @JoinColumn(name = "format_item")
    private FormatItem format_item;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)//FetchType.LAZY
    @JoinColumn(name = "format_pdf")
    private FormatPDF format_pdf;

    public Letakttd() {

    }

    public Letakttd(Long id, Integer ttd_ke, Integer page, String lx, String ly, String rx, String ry, Date createdate, Integer prf_ke, FormatItem format_item, FormatPDF format_pdf) {
        this.id = id;
        this.ttd_ke = ttd_ke;
        this.page = page;
        this.lx = lx;
        this.ly = ly;
        this.rx = rx;
        this.ry = ry;
        this.createdate = createdate;
        this.prf_ke = prf_ke;
        this.format_item = format_item;
        this.format_pdf = format_pdf;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTtd_ke() {
        return ttd_ke;
    }

    public void setTtd_ke(Integer ttd_ke) {
        this.ttd_ke = ttd_ke;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public String getLx() {
        return lx;
    }

    public void setLx(String lx) {
        this.lx = lx;
    }

    public String getLy() {
        return ly;
    }

    public void setLy(String ly) {
        this.ly = ly;
    }

    public String getRx() {
        return rx;
    }

    public void setRx(String rx) {
        this.rx = rx;
    }

    public String getRy() {
        return ry;
    }

    public void setRy(String ry) {
        this.ry = ry;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Integer getPrf_ke() {
        return prf_ke;
    }

    public void setPrf_ke(Integer prf_ke) {
        this.prf_ke = prf_ke;
    }

    public FormatItem getFormat_item() {
        return format_item;
    }

    public void setFormat_item(FormatItem format_item) {
        this.format_item = format_item;
    }

    public FormatPDF getFormat_pdf() {
        return format_pdf;
    }

    public void setFormat_pdf(FormatPDF format_pdf) {
        this.format_pdf = format_pdf;
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
        if (!(object instanceof Letakttd)) {
            return false;
        }
        Letakttd other = (Letakttd) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.digisign.entity.Letakttd[ id=" + id + " ]";
    }

}
