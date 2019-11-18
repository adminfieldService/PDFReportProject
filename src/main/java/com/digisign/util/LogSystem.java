/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.digisign.util;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author newbiecihuy
 */
public class LogSystem {
public static Logger log = Logger.getLogger("digisignlogger");

    public static void request(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        String logData = ipAddress + " " + request.getRequestURI();
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String formattedDate = "[" + dateFormat.format(date) + "]";

        logData += formattedDate + " [RECEIVE] ";

        boolean isMultipart = ServletFileUpload.isMultipartContent(request);

        if (!isMultipart) {
            Enumeration<String> params = request.getParameterNames();
            while (params.hasMoreElements()) {
                String paramName = params.nextElement();
                if (paramName.equals("fprt-1") || paramName.equals("fprt-2") || paramName.indexOf("foto") >= 0 || paramName.equals("pwd")) {
                    logData += "[" + paramName + "] : {WIPED};";

                } else {
                    logData += "[" + paramName + "] :\"" + request.getParameter(paramName) + "\";";
                }
            }
        } else {

            ServletFileUpload upload = new ServletFileUpload(
                    new DiskFileItemFactory());

            // parse requests
            List<FileItem> fileItems = null;
            try {
                fileItems = upload.parseRequest((RequestContext) request);
            } catch (FileUploadException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            // Process the uploaded items
            for (FileItem fileItem : fileItems) {
                // a regular form field

                if (fileItem.isFormField()) {
                    String paramName = fileItem.getFieldName();
                    if (paramName.equals("fprt-1") || paramName.equals("fprt-2") || paramName.indexOf("foto") >= 0 || paramName.equals("pwd")) {
                        logData += "[" + paramName + "] : {WIPED};";

                    } else {
                        logData += "[" + paramName + "] :\"" + fileItem.getString() + "\";";
                    }

                } else {

                    logData += "[" + fileItem.getFieldName() + "] :{" + fileItem.getName() + "," + fileItem.getSize() + "};";

                    // System.out.println(fileItem.getFieldName()+" : "+fileItem.getName()+","+fileItem.getContentType());
                }
            }

        }
        log.info(logData);
    }

    public static void request(HttpServletRequest request, List<FileItem> fileItems) {

        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        String logData = "";
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String formattedDate = dateFormat.format(date);

        // no multipart form
        if (!isMultipart) {
            request(request);

        } // multipart form
        else {

            logData += " [RCV] " + ipAddress + " " + request.getRequestURI();

            // Process the uploaded items
            for (FileItem fileItem : fileItems) {
                // a regular form field

                if (fileItem.isFormField()) {
                    String paramName = fileItem.getFieldName();
                    if (paramName.equals("fprt-1") || paramName.equals("fprt-2") || paramName.equals("foto") || paramName.indexOf("foto") >= 0 || paramName.equals("pwd")) {
                        logData += "[" + paramName + "] : {WIPED};";

                    } else {
                        logData += "[" + paramName + "] :\"" + fileItem.getString() + "\";";
                    }

                } else {

                    logData += "[" + fileItem.getFieldName() + "] :{" + fileItem.getName() + "," + fileItem.getSize() + "};";

                    // System.out.println(fileItem.getFieldName()+" : "+fileItem.getName()+","+fileItem.getContentType());
                }
            }
            log.info(logData);

        }

    }

    public static void request(HttpServletRequest request, List<FileItem> fileItems, String kelas, String kodeTRX, String trxType) {

        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        String logData = "";
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String formattedDate = dateFormat.format(date);

        // no multipart form
        if (!isMultipart) {
            request(request);

        } // multipart form
        else {

            logData += " : [RCV] " + ipAddress + " " + request.getRequestURI();

            // Process the uploaded items
            for (FileItem fileItem : fileItems) {
                // a regular form field

                if (fileItem.isFormField()) {
                    String paramName = fileItem.getFieldName();
                    if (paramName.equals("fprt-1") || paramName.equals("fprt-2") || paramName.equals("foto") || paramName.indexOf("foto") >= 0 || paramName.equals("pwd")) {
                        logData += "[" + paramName + "] : {WIPED};";

                    } else {
                        logData += "[" + paramName + "] :\"" + fileItem.getString() + "\";";
                    }

                } else {

                    logData += "[" + fileItem.getFieldName() + "] :{" + fileItem.getName() + "," + fileItem.getSize() + "};";

                    // System.out.println(fileItem.getFieldName()+" : "+fileItem.getName()+","+fileItem.getContentType());
                }
            }
            log.info(formattedDate + " " + kelas + " [API MITRA/" + trxType + "/" + kodeTRX + "]" + logData);

        }

    }

    public static void response(HttpServletRequest request, JSONObject respData) {
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        String logData = ipAddress + " " + request.getRequestURI();
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String formattedDate = "[" + dateFormat.format(date) + "]";

        logData += formattedDate + " [SEND]  { \"JSONFile\" : { ";
        Iterator<String> it = respData.keys();
        int i = 0;
        while (it.hasNext()) {
            if (i > 0) {
                logData += ", ";
            }
            String ky = it.next();
            String val = "[WIPED]";
            switch (ky) {
                case "signature-pic":
                case "fotoktp":
                case "fotodiri":
                case "file":

                    break;

                default:
                    try {
                        val = respData.getString(ky);
                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    break;
            }
            logData += "\"" + ky + "\":\"" + val + "\"";
            i++;

        }

        logData += " } }";
        log.info(logData);
    }

    public static void response(HttpServletRequest request, JSONObject respData, String kelas, String kodeTRX, String trxType) {
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        String logData = "";
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String formattedDate = dateFormat.format(date);

        logData += formattedDate + " " + kelas + " [API MITRA/" + trxType + "/" + kodeTRX + "] : [SND] " + ipAddress + " " + request.getRequestURI() + "  { \"JSONFile\" : { ";
        Iterator<String> it = respData.keys();
        int i = 0;
        while (it.hasNext()) {
            if (i > 0) {
                logData += ", ";
            }
            String ky = it.next();
            String val = "[WIPED]";
            switch (ky) {
                case "signature-pic":
                case "fotoktp":
                case "fotodiri":
                case "file":

                    break;

                default:
                    try {
                        val = respData.getString(ky);
                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    break;
            }
            logData += "\"" + ky + "\":\"" + val + "\"";
            i++;

        }

        logData += " } }";
        log.info(logData);
    }

    public static void info(HttpServletRequest request, String respData, String kelas, String kodeTRX, String trxType) {
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        String logData = ipAddress + " " + request.getRequestURI();
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String formattedDate = dateFormat.format(date);

        //logData+= formattedDate + " [CHECK] : "+respData;
        String formatLog = formattedDate + " " + kelas + " [API MITRA/" + trxType + "/" + kodeTRX + "] : [LOG] " + logData + " " + respData;

        log.info(formatLog);
    }

    public static void info(HttpServletRequest request, String respData) {
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        String logData = ipAddress + " " + request.getRequestURI();
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String formattedDate = "[" + dateFormat.format(date) + "]";

        logData += formattedDate + " [CHECK] : " + respData;
        //String formatLog=formattedDate+" INFO "+kelas+" "+kodeTRX+" [LOG] "+logData+" "+respData;

        log.info(logData);
    }

    public static void error(HttpServletRequest request, String respData, String kelas, String kodeTRX, String trxType) {
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        String logData = ipAddress + " " + request.getRequestURI();
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String formattedDate = dateFormat.format(date);

        //logData+= formattedDate + " [CHECK] : "+respData;
        String formatLog = formattedDate + " " + kelas + " [API MITRA/" + trxType + "/" + kodeTRX + "] : [ERROR] " + logData + " " + respData;

        log.error(formatLog);
    }

    public static void error(HttpServletRequest request, String respData) {
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        String logData = ipAddress + " " + request.getRequestURI();
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String formattedDate = "[" + dateFormat.format(date) + "]";

        logData += formattedDate + " [CHECK] : " + respData;

        log.error(logData);
    }

    public static void error(Class className, Exception obj) {
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String formattedDate = "[" + dateFormat.format(date) + "]";

        Logger.getLogger(className).error(formattedDate + " [CHECK] ", obj);

    }

    public static void error(Class className, Exception obj, String kelas, String kodeTRX, String trxType) {

        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String formattedDate = "[" + dateFormat.format(date) + "]";

        Logger.getLogger(className).error(formattedDate + " " + kelas + " [API MITRA/" + trxType + "/" + kodeTRX + "] : [ERROR] ", obj);

    }

    public static void info(Class className, String obj) {
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String formattedDate = "[" + dateFormat.format(date) + "]";

        Logger.getLogger(className).info(formattedDate + " [INFO] " + obj);

    }

}
