/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.digisign.pdf.controller;

import com.digisign.pdf.entity.FormatItem;
import com.digisign.pdf.entity.FormatPDF;
import com.digisign.pdf.entity.Letakttd;
import com.digisign.pdf.entity.Mitra;
import com.digisign.pdf.entity.PDFGeneration;
import com.digisign.pdf.entity.PDFGenerationItem;
import com.digisign.pdf.entity.TokenMitra;
import com.digisign.pdf.service.FormatItemService;
import com.digisign.pdf.service.FormatPDFService;
import com.digisign.pdf.service.LetakTandaTanganService;
import com.digisign.pdf.service.MitraService;
import com.digisign.pdf.service.PDFGenerationItemService;
import com.digisign.pdf.service.PDFGenerationService;
import com.digisign.pdf.service.ReportService;
import com.digisign.pdf.service.TokenMitraService;
import com.digisign.util.LogSystem;
import com.digisign.util.Samba;
import com.google.common.net.UrlEscapers;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.logging.Level;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
import net.sf.json.JSONObject;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONSerializer;
import org.apache.commons.io.FileUtils;
import org.jpos.iso.ISOUtil;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author newbiecihuy
 */
//@RequestMapping(value = {"/controller"})
//@RequestMapping("/api")
@RestController
public class ControllerPDF {

    static String basepath = "/opt/data-DS/UploadFile/";
    static String basepathPreReg = "/opt/pdf_creation_system/";
    final static Logger log = Logger.getLogger("digisignlogger");
    Date tgl = new Date();
    SimpleDateFormat sdfDate2 = new SimpleDateFormat("yyyyMMddHHmmssSSSSSS");
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat jamFormat = new SimpleDateFormat("HH:mm:ss");
    Date now = new Date();
    Date time_now = new Date();

    String refTrx = "pdf_creation_system" + sdfDate2.format(tgl);
    String kelas = "com.digisign.pdf.controller.ControllerPDF";
    String trxType = "ControllerPDF";
//    
//  @Autowired
    private final FormatPDFService formatPDFService;
    private final FormatItemService formatItemService;
    private final MitraService mitraService;
    private final ReportService reportService;
    private final PDFGenerationService pdfGenerationService;
    private final PDFGenerationItemService pdfGenerationItemService;
    private final LetakTandaTanganService letakTandaTanganService;
    private final TokenMitraService tokenMitraService;
    private final ApplicationContext context;

    public ControllerPDF(FormatPDFService formatPDFService, FormatItemService formatItemService, MitraService mitraService, ReportService reportService, PDFGenerationService pdfGenerationService, PDFGenerationItemService pdfGenerationItemService, LetakTandaTanganService letakTandaTanganService, TokenMitraService tokenMitraService, ApplicationContext context) {
        this.formatPDFService = formatPDFService;
        this.formatItemService = formatItemService;
        this.mitraService = mitraService;
        this.reportService = reportService;
        this.pdfGenerationService = pdfGenerationService;
        this.pdfGenerationItemService = pdfGenerationItemService;
        this.letakTandaTanganService = letakTandaTanganService;
        this.tokenMitraService = tokenMitraService;
        this.context = context;
    }

//    @GetMapping("/api/dataFile")
//    public String getDataFile() {
//
//        String message = "";
//        String jwtToken = "";
//        Object sing = "";
//        String email = "";
//        String type = "";
//        String role = "";//user
//        JSONObject jsonobj = new JSONObject();
////        
//        sing = SignatureAlgorithm.HS256;
//        jwtToken = Jwts.builder().setSubject(email).claim("roles", role).setIssuedAt(new Date())
//                .signWith((SignatureAlgorithm) sing, "secretkey").compact();
//        type = "JWT";
//        message = "Hello Wolrd";
//        jsonobj.put("typ", type);
//        jsonobj.put("message", message);
//        jsonobj.put("token", jwtToken);
//
//        return jsonobj.toString();
//    }
    @GetMapping("/api/pdf_generate.html")
    public List<PDFGeneration> pdfGenerate() {

        return pdfGenerationService.selectPdfGeneration();
    }

//    @GetMapping(path = "/api/pdfReport.html")
    @PostMapping(path = "/api/pdfReport.html")
    @ResponseBody
//    public void getPdf(@PathVariable String jrxml, HttpServletResponse response) throws Exception {
    public String pdfReport(HttpServletRequest request, HttpServletResponse response) throws Exception {//, @RequestBody FormatPDF pdfFormat
        System.out.println("insert method pdfReport");
        JSONObject obj = (JSONObject) JSONSerializer.toJSON(request.getParameter("jsonfield"));
        JSONObject jsonobj = new JSONObject();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd_MM_HH_mm_ss");
        Date tgl = new Date();
        String time = dateFormat.format(tgl);
        //Parameters Set
        Long idmitra = 0l;
        Long idformatPdf = 0l;
        Long id_format_item = 0l;
        Long id_Pdfgeneration_item = 0l;
        String nama_pdf = null;
        String nama_file = null;
        String prefix_param = null;
        String type_pdf = null;
        String mitraName = null;
        String typefile = "download";
        String nama_format = "";//pdfFormat.getNama_format()
        String item_name = "";
        String item_format = "";
        String item_format_style = "";
        String max_char = "";
        String value = "";
        String value_genertaion_item = "";
        boolean isStatic = false;
        Mitra mitra = null;
        PDFGenerationItem dataPdfGenerationItem = null;
        List<FormatPDF> formatPdf = null;
        Long idMitra = 0l;
//        String token = request.getHeader("authorization");

//        if (token != null) {
////            System.out.println("token != null");
//            String[] split = token.split(" ");
//            if (split.length == 2) {
//                if (split[0].equals("Bearer")) {
//                    token = split[1];
//                }
//            }
////            System.out.println("isi token" + token);
//
//            try {
//                jsonobj.put("refTrx", refTrx);
//            } catch (JSONException e2) {
//                // TODO Auto-generated catch block
//                e2.printStackTrace();
//            }
//
//            List<TokenMitra> tm = tokenMitraService.findByToken(token.toLowerCase());//
//            if (tm != null && !tm.isEmpty()) {//
//                LogSystem.info(request, "ada Token = " + token, kelas, refTrx, trxType);
//                mitra = tm.get(0).getMitra();
//
//            } else {
////                System.out.println("Token null = " + token + kelas + refTrx + trxType);
//                LogSystem.error(request, "Token null ", kelas, refTrx, trxType);
//
//                jsonobj.put("result", "55");
//                jsonobj.put("notif", "token salah");
//                LogSystem.response(request, jsonobj, kelas, refTrx, trxType);
//                return jsonobj.toString();
//            }
//            idmitra = mitra.getId();
//            mitraName = mitra.getName();
//
//            System.out.println("idmitra" + idmitra);
//            System.out.println("isi nama_format" + nama_format);
//            
        if (obj.size() > 0) {

            for (int i = 0; i < obj.size(); i++) {

                JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON(obj.getString("JSONFile"));
                nama_format = jsonObject.getString("nama_format");
                idMitra = Long.parseLong(jsonObject.getString("id_mitra"));
                JSONArray jsonArray = (JSONArray) JSONSerializer.toJSON(jsonObject.getString("items"));//jsonObject.get("items");
                List<TokenMitra> tm = tokenMitraService.findByIdMitra(idMitra);//

                if (tm != null && !tm.isEmpty()) {//
                    LogSystem.info(request, "Mitra Found= " + tm.get(0).getMitra(), kelas, refTrx, trxType);
                    mitra = tm.get(0).getMitra();

                } else {

                    LogSystem.error(request, "Mitra Not Found ", kelas, refTrx, trxType);

                    jsonobj.put("result", "55");
                    jsonobj.put("notif", "token salah");
                    LogSystem.response(request, jsonobj, kelas, refTrx, trxType);
                    return jsonobj.toString();
                }

                idmitra = mitra.getId();
                mitraName = mitra.getName();
//
                System.out.println("idmitra" + idmitra);
                System.out.println("isi nama_format" + nama_format);
//                
                formatPdf = formatPDFService.selectFormatPDF(nama_format.toLowerCase(), idmitra);
//                
                if (formatPdf.isEmpty()) {

                    jsonobj.put("result", "55");
                    jsonobj.put("message", "Fail");
                    jsonobj.put("notif", "Request Format PDF tidak ditemukan");

                } else {

                    for (int j = 0; j < jsonArray.size(); j++) {

                        JSONObject itemJsonObject = jsonArray.getJSONObject(j);
                        FormatItem dataFormatItem = new FormatItem();
                        dataPdfGenerationItem = new PDFGenerationItem();

//                        System.out.println("isi Json Array : " + item_name + "," + item_format + "," + max_char + "," + value + "," + isStatic);
                        if (itemJsonObject.containsKey("item_name")) {
                            if (itemJsonObject.getString("item_name") != null) {
                                item_name = itemJsonObject.getString("item_name");
                                dataFormatItem.setItem_name(item_name);
                                dataFormatItem.setFormat_pdf(formatPdf.get(0));
                            } else {
                                item_name = null;
                            }
                        } else {
                            item_name = null;
                        }
                        if (itemJsonObject.containsKey("item_format")) {
                            if (itemJsonObject.getString("item_format") != null) {
                                item_format = itemJsonObject.getString("item_format");
                                dataFormatItem.setItem_format(item_format);
                            } else {
                                item_format = null;
                            }
                        } else {
                            item_format = null;
                        }
                        if (itemJsonObject.containsKey("item_format_style")) {
                            if (itemJsonObject.getString("item_format_style") != null) {
                                item_format_style = itemJsonObject.getString("item_format_style");
                                dataFormatItem.setFormat_format_style(item_format_style);
                            } else {
                                item_format_style = null;
                            }

                        } else {
                            item_format_style = null;
                        }
                        if (itemJsonObject.containsKey("max_char")) {
                            if (itemJsonObject.getString("max_char") != null) {
                                max_char = itemJsonObject.getString("max_char");
                                dataFormatItem.setMax_char(Integer.parseInt(max_char));
                            } else {
                                max_char = null;
                            }
                        } else {
                            max_char = null;
                        }
//                        if (itemJsonObject.getString("value") != null) {
//                            value = itemJsonObject.getString("value");
//                        } else {
//                            value = null;
//                        }

//                        if (itemJsonObject.getBoolean("isStatic") == true) {
//                            isStatic = true;
//                            if (itemJsonObject.getString("value") != null) {
//                                value = itemJsonObject.getString("value");
//                                dataFormatItem.setIsstatic(isStatic);
//                                dataFormatItem.setValue(value);
//                            }
//                        } else {
//                            isStatic = false;
//                            dataFormatItem.setIsstatic(isStatic);
//                            if (itemJsonObject.getString("value") != null) {
//                                dataFormatItem.setIsstatic(isStatic);
//                                value_genertaion_item = itemJsonObject.getString("value");
//                                dataPdfGenerationItem.setValue(value_genertaion_item);
//
//                            }
//
//                        }
                        if (itemJsonObject.getString("value") != null) {
                            isStatic = false;
                            dataFormatItem.setIsstatic(isStatic);
//                                dataFormatItem.setValue(value);
                            value_genertaion_item = itemJsonObject.getString("value");
                            dataPdfGenerationItem.setValue(value_genertaion_item);
                        }
                        List<FormatItem> cekFormatItem = formatItemService.findByIdFormatPdf_itemName(formatPdf.get(0).getId(), item_name);
                        if (cekFormatItem != null && !cekFormatItem.isEmpty()) {
                            FormatItem updateDataFormatItem = formatItemService.findById(cekFormatItem.get(0).getId());
                            formatItemService.updateFormatItem(updateDataFormatItem);
                            dataPdfGenerationItem.setFormat_item(updateDataFormatItem);
                            id_Pdfgeneration_item = pdfGenerationItemService.generatePDFItem(dataPdfGenerationItem);
                        } else {

                            id_format_item = formatItemService.createFormatItem(dataFormatItem);
                            dataPdfGenerationItem.setFormat_item(dataFormatItem);
                            id_Pdfgeneration_item = pdfGenerationItemService.generatePDFItem(dataPdfGenerationItem);
                        }

                    }
                }
                idformatPdf = formatPdf.get(0).getId();
                nama_pdf = formatPdf.get(0).getNama_format();
                type_pdf = formatPdf.get(0).getType_format();
                nama_file = formatPdf.get(0).getFile();
                prefix_param = formatPdf.get(0).getPrefix_param();
//                
                System.out.println("prefix_param" + prefix_param);
                jsonobj.put("result", "00");
                jsonobj.put("message", "Success");

                generatePDF(dataPdfGenerationItem, response, jsonobj, idformatPdf, mitraName, idmitra, id_format_item, id_Pdfgeneration_item, nama_pdf, nama_file, time, prefix_param);
            }

//            formatPdf = formatPDFService.selectFormatPDF(nama_format.toLowerCase(), idmitra);
//                return jsonobj.toString();
            return jsonobj.toString();
        }
//        } catch (IOException | JRException e) {
//            LogSystem.error(getClass(), e, kelas, refTrx, trxType);
////			error (context, e.getMessage());
////            context.getSyslog().error (e);
////			log.error(e);
//            // JSONObject jo=new JSONObject();
//            try {
//                jsonobj.put("result", "05");
//                jsonobj.put("notif", "Request Data tidak ditemukan");
////                context.put("trxjson", new JSONObject().put("JSONFile", jsonobj).toString());
////                LogSystem.response(request, jsonobj, kelas, refTrx, trxType);
//            } catch (JSONException e1) {
//                // TODO Auto-generated catch block
//                e1.printStackTrace();
//            }
//        }

        return jsonobj.toString();
    }

//    @PostMapping(path = "/api/generatePDF.html")
//    @ResponseBody
    public String generatePDF(PDFGenerationItem updatePdfGenerationItem, HttpServletResponse response, JSONObject json, Long idformatPdf, String mitraName, Long idmitra, Long idFormat_item, Long idPdfGeneration_item, String namaPDF, String filePDF, String time, String prefix_param) throws JRException {
        InputStream inputStream = null;
        String cekLink = null;
        try {
            System.out.println("insert method generatePDF");
            sdf.format(now);
            jamFormat.format(time_now);
            FormatPDF formatPDF = null;
            PDFGeneration dataGeneratePDF = new PDFGeneration();
            FormatItem item = null;
//            Letakttd dataLetakttd = null;
            Resource resource = context.getResource("classpath:reports/" + filePDF + ".jrxml");//jasper

//           System.out.println("resource" + resource);
            //Compile to jasperReport
            inputStream = resource.getInputStream();
            JasperReport report = JasperCompileManager.compileReport(inputStream);
            String path = "/opt/pdf_file/" + idmitra + "/";
//            String path_img = "/opt/img_pdf/img/";
            String imgName = "logo_baru_ACC-1410.png";
            String file = "../img/" + imgName;
//            Resource file = context.getResource("classpath:reports/" + imgName);//jasper
            byte[] fileContent = FileUtils.readFileToByteArray(new File(file));
            String encodedString = Base64.getEncoder().encodeToString(fileContent);
//          byte[] bi = image.getBytes();
            byte[] byteArray = new byte[102400];
//            File fileTo = new File(file);
            File directory = new File(path);
            if (!directory.exists()) {
                directory.mkdirs();
            }
//        
//        JSONObject jsonobj = new JSONObject();
            Samba samba = new Samba();
            JSONArray arrayItem = new JSONArray();
            JSONArray arrayletakTTD = new JSONArray();
            String fileName = null;
//

            Map data = new HashMap();
            List fieldService = new ArrayList();
            Map<String, Object> params = new HashMap<>();
            List<Letakttd> letakTtd = null;
            List<FormatItem> formatItem = formatItemService.findByIdFormatPdf(idformatPdf);//
//            System.out.println("formatItem" + formatItem);
            params.put("formatItem : ", formatItem);
            Long idGeneratePdf = 0l;
            if (formatItem != null && !formatItem.isEmpty()) {

//                String name = UrlEscapers.urlFragmentEscaper().escape(formatItem.get(0).getFormat_pdf().getMitra().getId() + "_" + time + "_" + formatItem.get(0).getFormat_pdf().getNama_format() + ".pdf");
//                generatePDF = new PDFGeneration();
                dataGeneratePDF.setFormat_pdf(formatItem.get(0).getFormat_pdf());
                dataGeneratePDF.setCreatedate(now);
                dataGeneratePDF.setRequest_time(time_now);
                dataGeneratePDF.setDocument(null);

                idGeneratePdf = pdfGenerationService.generatePDF(dataGeneratePDF);
                String name = UrlEscapers.urlFragmentEscaper().escape(formatItem.get(0).getFormat_pdf().getMitra().getId() + "_" + idGeneratePdf.toString() + "_" + formatItem.get(0).getFormat_pdf().getNama_format() + ".pdf");
//                System.out.println("name document" + name);
//                
                PDFGeneration updateLinkPDFGeneration = pdfGenerationService.findById(idGeneratePdf);
                updateLinkPDFGeneration.setDocument(path + name);
                pdfGenerationService.udatePDF(updateLinkPDFGeneration);
//              

                updatePdfGenerationItem = pdfGenerationItemService.findById(idPdfGeneration_item);
//                int i = pdfGenerationItemService.setIdPdfGnerate(idGeneratePdf);
//                System.out.println("return i:" + i);
//                updatePdfGenerationItem.setPdf_generation(updateLinkPDFGeneration);
                pdfGenerationItemService.updatePDFGenertateItem(updatePdfGenerationItem);
//                
                letakTtd = letakTandaTanganService.findByFormatPDF(formatItem.get(0).getFormat_pdf().getId());

//                }
                PDFGenerationItem pdfItem = null;
                for (int a = 0; a < formatItem.size(); a++) {

                    JSONObject obj = new JSONObject();
//                    FormatItem item = formatItem.get(a);
                    item = formatItem.get(a);

//                  System.out.println("for() formatItem" + formatItem);
                    obj.put("id_format_item", item.getId());
                    obj.put("format_pdf", item.getFormat_pdf().getId());
                    obj.put("item_name", item.getItem_name());
//                    data.put("item_name", item.getItem_name());
                    obj.put("item_format", item.getItem_format());
                    obj.put("item_format_style", item.getFormat_format_style());
                    obj.put("max_char", item.getMax_char());
                    obj.put("isStatic", item.isIsstatic());
//                    
                    if (item.isIsstatic() == false || item.isIsstatic() == true || item.isIsstatic()) {
                        List<PDFGenerationItem> listgeneratePDFItem = pdfGenerationItemService.findByFormatItem(item.getId());
//                        System.out.println("imagePath" + fileTo.getFile());

//                        byte[] imageInByte = ISOUtil.hex2byte(file.toString());
//                        FileOutputStream fop = new FileOutputStream(file.getFile());
//
//                        fop.write(imageInByte);
//                        fop.flush();
//                        fop.close();
                        if (listgeneratePDFItem != null && !listgeneratePDFItem.isEmpty()) {
                            for (int b = 0; b < listgeneratePDFItem.size(); b++) {

                                pdfItem = listgeneratePDFItem.get(b);
//                              System.out.println("prefix_param + item.getItem_name(), pdfItem.getValue() :" + prefix_param + item.getItem_name() + pdfItem.getValue());
                                data.put("logoImage", encodedString);
                                data.put(prefix_param + item.getItem_name(), pdfItem.getValue());
                            }

                        }
                    }

                    arrayItem.add(obj);
//                    dataLetakttd = new Letakttd();
//                    dataLetakttd.setCreatedate(tgl);
//                    dataLetakttd.setFormat_pdf(formatItem.get(0).getFormat_pdf());
//                    dataLetakttd.setTtd_ke(1);
                }
                if (letakTtd == null && letakTtd.isEmpty()) {
                    json.put("result", "55");
                    json.put("message", "Fail");
                    json.put("notif", "Request Format PDF tidak ditemukan");
                }
                for (int c = 0; c < letakTtd.size(); c++) {
                    System.out.println("for() letakTtd" + letakTtd);
                    JSONObject obj = new JSONObject();
                    Letakttd dataLetakttd = letakTtd.get(c);

                    obj.put("letakttd", dataLetakttd.getId());
                    obj.put("page", dataLetakttd.getPage());
                    obj.put("lx", dataLetakttd.getLx());
                    obj.put("ly", dataLetakttd.getLy());
                    obj.put("rx", dataLetakttd.getRx());
                    obj.put("ry", dataLetakttd.getRy());

                    arrayletakTTD.add(obj);
                }
//              Data source Set
                fieldService.add(data);
//                JRDataSource dataSource = new JRBeanCollectionDataSource(formatItem);
                JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(fieldService);
                params.put("datasource", dataSource);
                //Make jasperPrint
//                System.out.println("dataSource" + dataSource);
                JasperPrint jasperPrint = JasperFillManager.fillReport(report, params, dataSource);//new JREmptyDataSource()
                System.out.println("jasperPrint" + jasperPrint);
//                System.out.println("jasperPrint" + jasperPrint);
//                
//              List<JRPrintPage> page1 = jasperPrint.getPages();
//                JRPrintPage page = jasperPrint.getPages().get(0);
//                List pageElements = page.getElements();
//                JRPrintPage page = jasperPrint.getPages().get(0);
//                List pageElements = page.getElements();
//                System.out.println("pageElements 10" + pageElements.get(10));
//                System.out.println("pageElements 11" + pageElements.get(11));
////                
                //Media Type
                response.setContentType(MediaType.APPLICATION_PDF_VALUE);
                JasperExportManager.exportReportToPdfFile(jasperPrint, path + name);

//               
//                
                String linkDoc = null;
                String str = null;
                byte[] input_file = null;
                byte[] encodedBytes = null;
                cekLink = dataGeneratePDF.getDocument();
//                File namaFile = null;
//                List<FileItem> fileSave = new ArrayList<FileItem>();
                try {
                    input_file = Files.readAllBytes(Paths.get(dataGeneratePDF.getDocument()));
                    encodedBytes = Base64.getEncoder().encode(input_file);
                    linkDoc = new String(encodedBytes);

//                        byte[] decodedBytes = Base64.getDecoder().decode(encodedString.getBytes());
//                        str = new String(decoder);
                } catch (IOException ex) {
                    java.util.logging.Logger.getLogger(ControllerPDF.class.getName()).log(Level.SEVERE, null, ex);
                }
//                
//                for (FileItem fileItem : fileSave) {
//                    LogSystem.info(request, fileItem.getFieldName(), kelas, refTrx, trxType);
//                }
                File namaFile = new File(dataGeneratePDF.getDocument());
                samba.write(input_file, path + namaFile.getName());
//                if () {
//                System.out.println("samba.toString()" + samba.toString());
//                PDFGeneration updatePDF = pdfGenerationService.findById(idGeneratePdf);
//                if (updatePDF != null) {
//                    updatePDF.setDocument(samba.toString());
//                }
//                }
//                JRPrintPage page = jasperPrint.getPages().get(0);
//                List pageElements = page.getElements();
//                System.out.println("pageElements 10" + pageElements.get(10));
//                System.out.println("pageElements 11" + pageElements.get(11));
//                List<JRPrintPage> pages = jasperPrint.getPages();
//                for (Iterator<JRPrintPage> pageIt = pages.iterator(); pageIt.hasNext();) {
//                    JRPrintPage page = pageIt.next();
//                    System.out.println("pageElements 50:" + page.getElements().get(50).getSourceElementId() + "," + page.getElements().get(50).getX() + "," + page.getElements().get(50).getY());
//                    System.out.println("pageElements 51:" + page.getElements().get(51).getSourceElementId() + "," + page.getElements().get(51).getX() + "," + page.getElements().get(51).getY());
//                }
//                
                json.put("result", "00");
                json.put("message", "Success");
//              json.put("mitra_id", item.getFormat_pdf().getMitra().getId());
                json.put("fileName", name);//linkDoc
                json.put("file", linkDoc);//linkDoc
                json.put("items", arrayletakTTD);
            } else {
                json.put("result", "55");
                json.put("message", "Fail");
                json.put("notif", "Request Format PDF tidak ditemukan");
                json.put("item", arrayletakTTD);
            }

        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(ControllerPDF.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                inputStream.close();
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(ControllerPDF.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        File f = new File(cekLink);
        f.delete();
        return json.toString();
    }

    /*
     *https://www.mysamplecode.com/2019/05/generate-pdf-form-in-java-apache-pdfbox.html
     */
//    @GetMapping(path = "/api/formPdf.html")
//    @ResponseBody
//    public void formPdf(HttpServletResponse response) throws Exception {
//        try (final PDDocument document = new PDDocument()) {
//            PDPage page = new PDPage(PDRectangle.A4);
//            document.addPage(page);
//
//            PDAcroForm acroForm = new PDAcroForm(document);
////            
//            document.getDocumentCatalog().setAcroForm(acroForm);
//            PDCheckBox checkbox = new PDCheckBox(acroForm);
//            PDRadioButton radioButton = new PDRadioButton(acroForm);
//
//            Button button = new Button();
//            PDFont font = PDType1Font.HELVETICA;
//            PDResources resources = new PDResources();
//            resources.put(COSName.getPDFName("Helv"), font);
//            acroForm.setDefaultResources(resources);
//
//            PDTextField textField = new PDTextField(acroForm);
//            textField.setPartialName("SampleField");
//
//            String defaultAppearance = "/Helv 12 Tf 0 0 1 rg";
//            textField.setDefaultAppearance(defaultAppearance);
//            int x = 25;
//            int y = 750;
//
//            Util.addText(document, page, "Registration Form", x, y, true);
//            y = y - 30;
//            Util.addText(document, page, "Name", x, y, false);
//            Util.addField(acroForm, page, "name", "Your Name", x + 75, y);
//            y = y - 20;
//            Util.addText(document, page, "Address", x, y, false);
//            Util.addField(acroForm, page, "address", "", x + 75, y);
//            y = y - 20;
//            Util.addText(document, page, "Phone No", x, y, false);
//            Util.addField(acroForm, page, "phone", " ", x + 75, y);
//
//            y = y - 30;
//            Util.addText(document, page, "Hobbies", x, y, true);
//            y = y - 20;
//            Util.addCheckBox(document, acroForm, page, "sports", false, x, y, checkbox);
//            Util.addText(document, page, "Sports", x + 25, y, false);
//            y = y - 20;
//            Util.addCheckBox(document, acroForm, page, "cooking", false, x, y, checkbox);
//            Util.addText(document, page, "Cooking", x + 25, y, false);
//            y = y - 20;
//            Util.addCheckBox(document, acroForm, page, "music", true, x, y, checkbox);
//            Util.addText(document, page, "Music", x + 25, y, false);
//            y = y - 20;
//            Util.addCheckBox(document, acroForm, page, "travel", false, x, y, checkbox);
//            Util.addText(document, page, "Travelling", x + 25, y, false);
//
//            y = y - 30;
//            Util.addText(document, page, "Gender", x, y, true);
//
////            ArrayList<button> genderList = new ArrayList</button ><button>();
//////            Button button = new Button();
////            button.setName("Male");
////            button.setValue("male");
////            genderList.add(button);
////            button = new Button();
////            button.setName("Female");
////            button.setValue("female");
////            genderList.add(button);
////            button = new Button();
////            button.setName("Other");
////            button.setValue("other");
////            genderList.add(button);
////            Util.addRadioButtons(document, acroForm, page, genderList,"male", x, y);
//            acroForm.getFields().add(textField);
//
//            PDAnnotationWidget widget = textField.getWidgets().get(0);
//            PDRectangle rect = new PDRectangle(50, 750, 200, 50);
//            widget.setRectangle(rect);
//            widget.setPage(page);
//
//            PDAppearanceCharacteristicsDictionary fieldAppearance = new PDAppearanceCharacteristicsDictionary(new COSDictionary());
//            fieldAppearance.setBorderColour(new PDColor(new float[]{0, 1, 0}, PDDeviceRGB.INSTANCE));
//            fieldAppearance.setBackground(new PDColor(new float[]{1, 1, 0}, PDDeviceRGB.INSTANCE));
//            widget.setAppearanceCharacteristics(fieldAppearance);
//
//            widget.setPrinted(true);
//
//            page.getAnnotations().add(widget);
//
//            textField.setValue("Sample Field");
//
//            document.save("/opt/pdf/sample_form.pdf");
//            document.close();
//        } catch (IOException e) {
//            System.err.println("Exception while trying to create pdf document - " + e);
//        }
//    }
}
