/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.digisign.util.template;

import java.io.IOException;
import static javassist.CtMethod.ConstParameter.string;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
import org.apache.pdfbox.pdmodel.graphics.color.PDDeviceRGB;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationWidget;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAppearanceCharacteristicsDictionary;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAppearanceStream;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDCheckBox;
import org.apache.pdfbox.pdmodel.interactive.form.PDRadioButton;
import org.apache.pdfbox.pdmodel.interactive.form.PDTextField;

/**
 *
 * @author newbiecihuy
 */
public class ElementPdfBox {
//
    public ElementPdfBox() {
    }
//      // Generate Form Labels
//    private static void addText(PDDocument document, PDPage page, String myText, float x, float y, boolean bold) {
// 
//        try {
//            //Get Content Stream for Writing Data
//            PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true);
// 
//            //Begin the Content stream 
//            contentStream.beginText(); 
// 
//            //Setting the font to the Content stream  
//            if(bold){
//                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
//            }
//            else{
//                contentStream.setFont(PDType1Font.HELVETICA, 12);
//            }
// 
//            //Setting the position for the line 
//            contentStream.newLineAtOffset(x, y);
// 
//            //Adding text in the form of string 
//            contentStream.showText(myText);      
// 
//            //Ending the content stream
//            contentStream.endText();
// 
//            //Closing the content stream
//            contentStream.close();
// 
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
// 
//    }
// 
// 
//    // Generate Form Text fields
//    private static void addField(PDAcroForm acroForm, PDPage page, String name, String defaultValue, float x, float y) {
// 
//        try {
//            // Add a form field to the form.
//            PDTextField textBox = new PDTextField(acroForm);
//            textBox.setPartialName(name);
//            acroForm.getFields().add(textBox);
// 
//            // Specify the widget annotation associated with the field
//            PDAnnotationWidget widget = textBox.getWidgets().get(0);
//            PDRectangle rect = new PDRectangle(x, y, 200, 15);
//            widget.setRectangle(rect);
//            widget.setPage(page);
// 
//            // set black border
//            // if you prefer defaults, just delete this code block
//            PDAppearanceCharacteristicsDictionary fieldAppearance
//            = new PDAppearanceCharacteristicsDictionary(new COSDictionary());
//            fieldAppearance.setBorderColour(new PDColor(new float[]{0,0,0}, PDDeviceRGB.INSTANCE));
//            // for background color if you need
//            // fieldAppearance.setBackground(new PDColor(new float[]{1,1,0}, PDDeviceRGB.INSTANCE));
//            widget.setAppearanceCharacteristics(fieldAppearance);
// 
//            // make sure the widget annotation is visible on screen and paper
//            widget.setPrinted(true);
// 
//            // Add the widget annotation to the page
//            page.getAnnotations().add(widget);
// 
//            // set the field value
//            textBox.setValue(defaultValue);
// 
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
// 
//    }
// 
//    // Generate Form Check Boxes
//    private static void addCheckBox(PDDocument document, PDAcroForm acroForm, PDPage page, String name, boolean checked, float x, float y) {
// 
//        try {
// 
//            PDRectangle rect = new PDRectangle(x, y, 15, 15);
// 
//            PDCheckBox checkbox = new PDCheckBox(acroForm);
//            checkbox.setPartialName(name);
//            PDAnnotationWidget widget = checkbox.getWidgets().get(0);
//            widget.setPage(page);
//            widget.setRectangle(rect);
//            widget.setPrinted(true);
// 
//            PDAppearanceCharacteristicsDictionary appearanceCharacteristics = new PDAppearanceCharacteristicsDictionary(new COSDictionary());
//            appearanceCharacteristics.setBorderColour(new PDColor(new float[]{1, 0, 0}, PDDeviceRGB.INSTANCE));
//            appearanceCharacteristics.setBackground(new PDColor(new float[]{1, 1, 0}, PDDeviceRGB.INSTANCE));
//            // 8 = cross; 4 = checkmark; H = star; u = diamond; n = square, l = dot
//            appearanceCharacteristics.setNormalCaption("4");
//            widget.setAppearanceCharacteristics(appearanceCharacteristics);
// 
// 
//            PDBorderStyleDictionary borderStyleDictionary = new PDBorderStyleDictionary();
//            borderStyleDictionary.setWidth(1);
//            borderStyleDictionary.setStyle(PDBorderStyleDictionary.STYLE_SOLID);
//            widget.setBorderStyle(borderStyleDictionary);
// 
//            PDAppearanceDictionary ap = new PDAppearanceDictionary();
//            widget.setAppearance(ap);
//            PDAppearanceEntry normalAppearance = ap.getNormalAppearance();
// 
//            COSDictionary normalAppearanceDict = (COSDictionary) normalAppearance.getCOSObject();
//            normalAppearanceDict.setItem(COSName.Off, createCheckBoxAppearanceStream(document, widget, false));
//            normalAppearanceDict.setItem(COSName.YES, createCheckBoxAppearanceStream(document, widget, true));
// 
//            // If we ever decide to implement a /D (down) appearance, just 
//            // replace the background colors c with c * 0.75
//            page.getAnnotations().add(checkbox.getWidgets().get(0));
//            acroForm.getFields().add(checkbox);
// 
//            if(checked){
//                checkbox.check();
//            }
//            else {
//                checkbox.unCheck();
//            }
// 
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
// 
//    }
// 
//    // Generate Form Radio Buttons
//    private static void addRadioButtons(PDDocument document, PDAcroForm acroForm, PDPage page, ArrayList</button><button> buttonList, String defaultValue, float x, float y) {
// 
//        try {
// 
//            ArrayList<string> buttonValues = new ArrayList<string>();
//            for(Button button: buttonList){
//                buttonValues.add(button.getValue());
//            }
// 
//            PDRadioButton radioButton = new PDRadioButton(acroForm);
//            radioButton.setPartialName("MyRadioButton");
//            radioButton.setExportValues(buttonValues);
// 
//            PDAppearanceCharacteristicsDictionary appearanceCharacteristics = new PDAppearanceCharacteristicsDictionary(new COSDictionary());
//            appearanceCharacteristics.setBorderColour(new PDColor(new float[] { 1, 0, 0 }, PDDeviceRGB.INSTANCE));
//            appearanceCharacteristics.setBackground(new PDColor(new float[]{0, 1, 0.3f}, PDDeviceRGB.INSTANCE));
// 
//            ArrayList<pdannotationwidget> widgets = new ArrayList<pdannotationwidget>();
//            for (int i = 0; i < buttonList.size(); i++) {
// 
//                y = y - 20;
//                PDAnnotationWidget widget = new PDAnnotationWidget();
//                widget.setRectangle(new PDRectangle(x, y, 15, 15));
//                widget.setAppearanceCharacteristics(appearanceCharacteristics);
//                PDBorderStyleDictionary borderStyleDictionary = new PDBorderStyleDictionary();
//                borderStyleDictionary.setWidth(1);
//                borderStyleDictionary.setStyle(PDBorderStyleDictionary.STYLE_SOLID);
//                widget.setBorderStyle(borderStyleDictionary);
//                widget.setPage(page);
// 
//                COSDictionary apNDict = new COSDictionary();
//                apNDict.setItem(COSName.Off, createRadioButtonAppearanceStream(document, widget, false));
//                apNDict.setItem(buttonList.get(i).getValue(), createRadioButtonAppearanceStream(document, widget, true));
// 
//                PDAppearanceDictionary appearance = new PDAppearanceDictionary();
//                PDAppearanceEntry appearanceNEntry = new PDAppearanceEntry(apNDict);
//                appearance.setNormalAppearance(appearanceNEntry);
//                widget.setAppearance(appearance);
//                widget.setAppearanceState("Off"); // don't forget this, or button will be invisible
//                widgets.add(widget);
//                page.getAnnotations().add(widget);
// 
//                addText(document, page, buttonList.get(i).getName(), x + 25 , y+3, false);
//            }
// 
//            radioButton.setWidgets(widgets);
//            acroForm.getFields().add(radioButton);
// 
//            radioButton.setValue(defaultValue);
// 
// 
// 
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
// 
//    }
//     private static PDAppearanceStream createCheckBoxAppearanceStream(
//            final PDDocument document, PDAnnotationWidget widget, boolean on) throws IOException
//    {
//        PDRectangle rect = widget.getRectangle();
//        PDAppearanceCharacteristicsDictionary appearanceCharacteristics;
//        PDAppearanceStream yesAP = new PDAppearanceStream(document);
//        yesAP.setBBox(new PDRectangle(rect.getWidth(), rect.getHeight()));
//        yesAP.setResources(new PDResources());
//        PDPageContentStream yesAPCS = new PDPageContentStream(document, yesAP);
//        appearanceCharacteristics = widget.getAppearanceCharacteristics();
//        PDColor backgroundColor = appearanceCharacteristics.getBackground();
//        PDColor borderColor = appearanceCharacteristics.getBorderColour();
//        float lineWidth = getLineWidth(widget);
//        yesAPCS.setLineWidth(lineWidth); // border style (dash) ignored
//        yesAPCS.setNonStrokingColor(backgroundColor);
//        yesAPCS.addRect(0, 0, rect.getWidth(), rect.getHeight());
//        yesAPCS.fill();
//        yesAPCS.setStrokingColor(borderColor);
//        yesAPCS.addRect(lineWidth / 2, lineWidth / 2, rect.getWidth() - lineWidth, rect.getHeight() - lineWidth);
//        yesAPCS.stroke();
//        if (!on) {
//            yesAPCS.close();
//            return yesAP;
//        }
// 
//        yesAPCS.addRect(lineWidth, lineWidth, rect.getWidth() - lineWidth * 2, rect.getHeight() - lineWidth * 2);
//        yesAPCS.clip();
// 
//        String normalCaption = appearanceCharacteristics.getNormalCaption();
//        if (normalCaption == null) {
//            normalCaption = "4"; // Adobe behaviour
//        }
// 
//        if ("8".equals(normalCaption)) {
//            // Adobe paints a cross instead of using the Zapf Dingbats cross symbol
//            yesAPCS.setStrokingColor(0f);
//            yesAPCS.moveTo(lineWidth * 2, rect.getHeight() - lineWidth * 2);
//            yesAPCS.lineTo(rect.getWidth() - lineWidth * 2, lineWidth * 2);
//            yesAPCS.moveTo(rect.getWidth() - lineWidth * 2, rect.getHeight() - lineWidth * 2);
//            yesAPCS.lineTo(lineWidth * 2, lineWidth * 2);
//            yesAPCS.stroke();
//        }
//        else {
//            // The caption is not unicode, but the Zapf Dingbats code in the PDF
//            // Thus convert it back to unicode
//            // Assume that only the first character is used.
//            String name = PDType1Font.ZAPF_DINGBATS.codeToName(normalCaption.codePointAt(0));
//            String unicode = PDType1Font.ZAPF_DINGBATS.getGlyphList().toUnicode(name);
//            Rectangle2D bounds = PDType1Font.ZAPF_DINGBATS.getPath(name).getBounds2D();
//            float size = (float) Math.min(bounds.getWidth(), bounds.getHeight()) / 1000;
//            // assume that checkmark has square size
//            // the calculations approximate what Adobe is doing, i.e. put the glyph in the middle
//            float fontSize = (rect.getWidth() - lineWidth * 2) / size * 0.6666f;
//            float xOffset = (float) (rect.getWidth() - (bounds.getWidth()) / 1000 * fontSize) / 2;
//            xOffset -= bounds.getX() / 1000 * fontSize;
//            float yOffset = (float) (rect.getHeight() - (bounds.getHeight()) / 1000 * fontSize) / 2;
//            yOffset -= bounds.getY() / 1000 * fontSize;
//            yesAPCS.setNonStrokingColor(0f);
//            yesAPCS.beginText();
//            yesAPCS.setFont(PDType1Font.ZAPF_DINGBATS, fontSize);
//            yesAPCS.newLineAtOffset(xOffset, yOffset);
//            yesAPCS.showText(unicode);
//            yesAPCS.endText();
//        }
//        yesAPCS.close();
//        return yesAP;
//    }
// 
//    private static PDAppearanceStream createRadioButtonAppearanceStream(
//            final PDDocument document, PDAnnotationWidget widget, boolean on) throws IOException {
// 
//        PDRectangle rect = widget.getRectangle();
//        PDAppearanceStream onAP = new PDAppearanceStream(document);
//        onAP.setBBox(new PDRectangle(rect.getWidth(), rect.getHeight()));
//        PDPageContentStream onAPCS = new PDPageContentStream(document, onAP);
// 
//        PDAppearanceCharacteristicsDictionary appearanceCharacteristics = widget.getAppearanceCharacteristics();
//        PDColor backgroundColor = appearanceCharacteristics.getBackground();
//        PDColor borderColor = appearanceCharacteristics.getBorderColour();
//        float lineWidth = getLineWidth(widget);
//        onAPCS.setLineWidth(lineWidth); // border style (dash) ignored
//        onAPCS.setNonStrokingColor(backgroundColor);
//        float radius = Math.min(rect.getWidth() / 2, rect.getHeight() / 2);
//        drawCircle(onAPCS, rect.getWidth() / 2, rect.getHeight() / 2, radius);
//        onAPCS.fill();
//        onAPCS.setStrokingColor(borderColor);
//        drawCircle(onAPCS, rect.getWidth() / 2, rect.getHeight() / 2, radius - lineWidth / 2);
//        onAPCS.stroke();
//        if (on)
//        {
//            onAPCS.setNonStrokingColor(0f);
//            drawCircle(onAPCS, rect.getWidth() / 2, rect.getHeight() / 2, (radius - lineWidth) / 2);
//            onAPCS.fill();
//        }
// 
//        onAPCS.close();
//        return onAP;
//    }
// 
// 
//    private static float getLineWidth(PDAnnotationWidget widget) {
// 
//        org.apache.pdfbox.pdmodel.interactive.annotation.PDBorderStyleDictionary bs = widget.getBorderStyle();
//        if (bs != null) {
//            return bs.getWidth();
//        }
//        return 1;
//    }
// 
//    private static void drawCircle(PDPageContentStream cs, float x, float y, float r) throws IOException  {
// 
//        float magic = r * 0.551784f;
//        cs.moveTo(x, y + r);
//        cs.curveTo(x + magic, y + r, x + r, y + magic, x + r, y);
//        cs.curveTo(x + r, y - magic, x + magic, y - r, x, y - r);
//        cs.curveTo(x - magic, y - r, x - r, y - magic, x - r, y);
//        cs.curveTo(x - r, y + magic, x - magic, y + r, x, y + r);
//        cs.closePath();
// 
//    }
//   
}
