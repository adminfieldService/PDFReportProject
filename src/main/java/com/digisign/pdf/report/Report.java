/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.digisign.pdf.report;

import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.builders.StyleBuilder;
import ar.com.fdvs.dj.domain.constants.Border;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.constants.Transparency;
import ar.com.fdvs.dj.domain.constants.VerticalAlign;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import com.digisign.pdf.entity.FormatItem;
import com.digisign.pdf.entity.FormatPDF;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.design.JRDesignParameter;
import net.sf.jasperreports.engine.design.JRDesignSection;
import net.sf.jasperreports.engine.design.JRDesignStaticText;
import net.sf.jasperreports.engine.design.JRDesignStyle;
import net.sf.jasperreports.engine.design.JRDesignTextField;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.HorizontalTextAlignEnum;

/**
 *
 * @author newbiecihuy
 */
public class Report {

    private final Collection<FormatItem> list;

    public Report(Collection<FormatItem> c) {
        list = new ArrayList<>(c);
    }

    public JasperPrint getReport() throws ColumnBuilderException, JRException, ClassNotFoundException {
        Style headerStyle = createHeaderStyle();
        Style detailTextStyle = createDetailTextStyle();
        Style detailNumberStyle = createDetailNumberStyle();
        DynamicReport dynaReport = getReport(headerStyle, detailTextStyle, detailNumberStyle);
        JasperPrint jp = DynamicJasperHelper.generateJasperPrint(dynaReport, new ClassicLayoutManager(),
                new JRBeanCollectionDataSource(list));
        return jp;
    }

    private Style createHeaderStyle() {
        return new StyleBuilder(true)
                .setFont(Font.TIMES_NEW_ROMAN_MEDIUM)
                .setBorder(Border.THIN())
                .setBorderBottom(Border.PEN_2_POINT())
                .setBorderColor(Color.BLACK)
                .setBackgroundColor(Color.ORANGE)
                .setTextColor(Color.BLACK)
                .setHorizontalAlign(HorizontalAlign.CENTER)
                .setVerticalAlign(VerticalAlign.MIDDLE)
                .setTransparency(Transparency.OPAQUE)
                .build();
    }

    private Style createDetailTextStyle() {
        return new StyleBuilder(true)
                .setFont(Font.VERDANA_MEDIUM)
                .setBorder(Border.DOTTED())
                .setBorderColor(Color.BLACK)
                .setTextColor(Color.BLACK)
                .setHorizontalAlign(HorizontalAlign.LEFT)
                .setVerticalAlign(VerticalAlign.MIDDLE)
                .setPaddingLeft(5)
                .build();
    }

    private Style createDetailNumberStyle() {
        return new StyleBuilder(true)
                .setFont(Font.VERDANA_MEDIUM)
                .setBorder(Border.DOTTED())
                .setBorderColor(Color.BLACK)
                .setTextColor(Color.BLACK)
                .setHorizontalAlign(HorizontalAlign.RIGHT)
                .setVerticalAlign(VerticalAlign.MIDDLE)
                .setPaddingRight(5)
                .setPattern("#,##0.00")
                .build();
    }

    private AbstractColumn createColumn(String property, Class<?> type, String title, int width, Style headerStyle, Style detailStyle)
            throws ColumnBuilderException {
        return ColumnBuilder.getNew()
                .setColumnProperty(property, type.getName())
                .setTitle(title)
                .setWidth(Integer.valueOf(width))
                .setStyle(detailStyle)
                .setHeaderStyle(headerStyle)
                .build();
    }

//    private JasperDesign design(String property, Class<?> type, String title, int width, Style headerStyle, Style detailStyle) throws JRException {
//        JasperDesign jasDes = new JasperDesign();
//        jasDes.setName("myreport");
//        jasDes.setPageWidth(595);
//        jasDes.setPageHeight(842);
//        jasDes.setLeftMargin(20);
//        jasDes.setRightMargin(20);
//        jasDes.setTopMargin(20);
//        jasDes.setBottomMargin(20);
//        jasDes.setColumnWidth(555);
//
//        // Style
//        JRDesignStyle mystyle = new JRDesignStyle();
//        mystyle.setName("mystyle");
//        mystyle.setDefault(true);
//        mystyle.setFontName("DejaVu Sans");
//        mystyle.setFontSize(22f);
//        mystyle.setPdfFontName("Helvetica");
//        mystyle.setPdfEncoding("UTF-8");
//        jasDes.addStyle(mystyle);
//
//        // Fields
//        JRDesignField field1 = new JRDesignField();
//        field1.setName("id");
//        field1.setValueClass(String.class);
//        jasDes.addField(field1);
//
//        JRDesignField field2 = new JRDesignField();
//        field2.setName("name");
//        field2.setValueClass(String.class);
//        jasDes.addField(field2);
//
//        JRDesignField field3 = new JRDesignField();
//        field3.setName("price");
//        field3.setValueClass(String.class);
//        jasDes.addField(field3);
//
//        // Parameter
//        JRDesignParameter par = new JRDesignParameter();
//        par.setName("CarPrice");
//        par.setValueClass(Integer.class);
//        jasDes.addParameter(par);
//
//        // Query
////        JRDesignQuery query = new JRDesignQuery();
////        query.setText("SELECT * FROM Cars WHERE Price > $P{CarPrice}");
////        jasDes.setQuery(query);
//        // Title
//        JRDesignBand titleBand = new JRDesignBand();
//        titleBand.setHeight(50);
//
//        JRDesignStaticText titleText = new JRDesignStaticText();
//        titleText.setText("Expensive cars");
//        titleText.setX(0);
//        titleText.setY(10);
//        titleText.setWidth(515);
//        titleText.setHeight(30);
//        titleText.setHorizontalTextAlign(HorizontalTextAlignEnum.CENTER);
//        titleText.setFontSize(22f);
//        titleBand.addElement(titleText);
//        jasDes.setTitle(titleBand);
//
//        // Detail
//        JRDesignBand detailBand = new JRDesignBand();
//        detailBand.setHeight(60);
//
//        JRDesignTextField tf1 = new JRDesignTextField();
//        tf1.setBlankWhenNull(true);
//        tf1.setX(0);
//        tf1.setY(10);
//        tf1.setWidth(60);
//        tf1.setHeight(30);
//        tf1.setHorizontalTextAlign(HorizontalTextAlignEnum.CENTER);
//        tf1.setStyle(mystyle);
//        tf1.setExpression(new JRDesignExpression("$F{id}"));
//        detailBand.addElement(tf1);
//
//        JRDesignTextField tf2 = new JRDesignTextField();
//        tf2.setBlankWhenNull(true);
//        tf2.setX(80);
//        tf2.setY(10);
//        tf2.setWidth(120);
//        tf2.setHeight(30);
//        tf2.setHorizontalTextAlign(HorizontalTextAlignEnum.LEFT);
//        tf2.setStyle(mystyle);
//        tf2.setExpression(new JRDesignExpression("$F{name}"));
//        detailBand.addElement(tf2);
//
//        JRDesignTextField tf3 = new JRDesignTextField();
//        tf3.setBlankWhenNull(true);
//        tf3.setX(200);
//        tf3.setY(10);
//        tf3.setWidth(100);
//        tf3.setHeight(30);
//        tf3.setHorizontalTextAlign(HorizontalTextAlignEnum.RIGHT);
//        tf3.setStyle(mystyle);
//        tf3.setExpression(new JRDesignExpression("$F{price}"));
//        detailBand.addElement(tf3);
//
//        ((JRDesignSection) jasDes.getDetailSection()).addBand(detailBand);
//
//        return jasDes;
//    }

    private DynamicReport getReport(Style headerStyle, Style detailTextStyle, Style detailNumStyle)
            throws ColumnBuilderException, ClassNotFoundException, JRException {

        DynamicReportBuilder report = new DynamicReportBuilder();
        AbstractColumn columnEmpNo = createColumn("empNo", Integer.class, "Employee Number", 30, headerStyle, detailTextStyle);
        AbstractColumn columnName = createColumn("name", String.class, "Name", 30, headerStyle, detailTextStyle);
        AbstractColumn columnSalary = createColumn("salary", Integer.class, "Salary", 30, headerStyle, detailNumStyle);
        AbstractColumn columnCommission = createColumn("commission", Float.class, "Commission", 30, headerStyle, detailNumStyle);
        report.addColumn(columnEmpNo)
                .addColumn(columnName)
                .addColumn(columnSalary)
                .addColumn(columnCommission);
        StyleBuilder titleStyle = new StyleBuilder(true);
        titleStyle.setHorizontalAlign(HorizontalAlign.CENTER);
        titleStyle.setFont(new Font(20, null, true));
        // you can also specify a font from the classpath, eg:
        // titleStyle.setFont(new Font(20, "/fonts/someFont.ttf", true));

        StyleBuilder subTitleStyle = new StyleBuilder(true);
        subTitleStyle.setHorizontalAlign(HorizontalAlign.CENTER);
        subTitleStyle.setFont(new Font(Font.MEDIUM, null, true));

        report.setTitle("Digising Report");
        report.setTitleStyle(titleStyle.build());
        report.setSubtitle("Commission received by");
        report.setSubtitleStyle(subTitleStyle.build());
        report.setUseFullPageWidth(true);
        return report.build();
    }
}
