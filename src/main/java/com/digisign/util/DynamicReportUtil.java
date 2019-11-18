/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.digisign.util;

import java.awt.Color;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.design.JRDesignFrame;
import net.sf.jasperreports.engine.design.JRDesignGroup;
import net.sf.jasperreports.engine.design.JRDesignImage;
import net.sf.jasperreports.engine.design.JRDesignLine;
import net.sf.jasperreports.engine.design.JRDesignParameter;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JRDesignSection;
import net.sf.jasperreports.engine.design.JRDesignStaticText;
import net.sf.jasperreports.engine.design.JRDesignStyle;
import net.sf.jasperreports.engine.design.JRDesignTextField;
import net.sf.jasperreports.engine.design.JRDesignVariable;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.CalculationEnum;
import net.sf.jasperreports.engine.type.HorizontalAlignEnum;
import net.sf.jasperreports.engine.type.ModeEnum;
import net.sf.jasperreports.engine.type.PositionTypeEnum;
import net.sf.jasperreports.engine.type.ResetTypeEnum;
import net.sf.jasperreports.engine.type.ScaleImageEnum;

/**
 *
 * @author newbiecihuy
 */
public class DynamicReportUtil {

    static JRDesignImage getImage(int x_postion, int y_position, int width, int height, ScaleImageEnum scale_type, HorizontalAlignEnum align_type, String Parameter) {
        // TODO Auto-generated method stub
        JRDesignExpression expression = new JRDesignExpression();
        JRDesignImage image = new JRDesignImage(null);
        expression.setValueClass(java.lang.String.class);
        expression.setText("$P{" + Parameter + "}");
        image.setX(x_postion);
        image.setY(y_position);
        image.setWidth(width);
        image.setHeight(height);
        image.setScaleImage(scale_type);
        image.setHorizontalAlignment(align_type);
        image.setExpression(expression);
        return image;
    }

    static JRParameter getParameter(String string, Class<?> class1) {
        // TODO Auto-generated method stub
        JRDesignParameter parameter = new JRDesignParameter();
        parameter.setName(string);
        parameter.setValueClass(class1);
        return parameter;
    }

    static JRField getField(String string, Class<?> class1) {
        // TODO Auto-generated method stub
        JRDesignField field = new JRDesignField();
        field.setName(string);
        field.setValueClass(class1);
        return field;
    }

    static JRDesignElement getStaticText(int x, int y, int width, int height,
            String text, JRDesignStyle normalStyle, HorizontalAlignEnum alignType, int color_flag) {
        // TODO Auto-generated method stub
        JRDesignStaticText staticText = new JRDesignStaticText();
        staticText.setX(x);
        staticText.setY(y);
        //staticText.setStretchWithOverflow(true);
        staticText.setWidth(width);
        staticText.setHeight(height);
        staticText.setText(text);
        if (color_flag == 1) {
            staticText.setForecolor(new Color(240, 240, 240));
            staticText.setBackcolor(new Color(102, 0, 102));
        }
        staticText.setMode(ModeEnum.OPAQUE);
        staticText.setFontSize(10);
        staticText.setHorizontalAlignment(alignType);
        staticText.setStyle(normalStyle);
        return staticText;
    }

    static JRDesignTextField getTextField(int X_position, int y_position, int width, int height, HorizontalAlignEnum type, JRDesignStyle Style, int fontSize,
            String Parameter, Class<?> class1) {
        // TODO Auto-generated method stub
        JRDesignTextField textField = new JRDesignTextField();
        JRDesignExpression expression = new JRDesignExpression();
        expression.setValueClass(class1);
        expression.setText(Parameter);
        textField.setStretchWithOverflow(true);
        textField.setBlankWhenNull(true);
        textField.setX(X_position);
        textField.setY(y_position);
        textField.setWidth(width);
        textField.setHeight(height);
        textField.setHorizontalAlignment(type);
        textField.setStyle(Style);
        textField.setFontSize(fontSize);
        textField.setExpression(expression);
        return textField;
    }

    public static JRDesignStyle getNormalStyle() {
        // TODO Auto-generated method stub
        JRDesignStyle normalStyle = new JRDesignStyle();
        normalStyle.setName("Sans_Normal");
        normalStyle.setDefault(true);
        normalStyle.setBold(true);
        normalStyle.setFontName("SansSerif");
        normalStyle.setFontSize(10);
        normalStyle.setPdfFontName("Helvetica");
        normalStyle.setPdfEncoding("Cp1252");
        normalStyle.setPdfEmbedded(false);
        return normalStyle;
    }

}
