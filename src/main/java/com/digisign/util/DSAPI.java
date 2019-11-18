/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.digisign.util;

/**
 *
 * @author newbiecihuy
 */
public interface DSAPI {
//		static final String ROOTDIR=System.getProperty("catalina.home")+"/webapps/DigitalSignatureAPI2.0/";
//		static final String KMS_HOST="http://ejbca:7090/DSKeyCore";
//		static final String DOMAIN="app.digisign.id";
//		static final String DOMAINAPI="api.digisign.id";
//        static final String BILLING_HOST="http://localhost:8080";
//	    static final String SMS_API="http://localhost:7090";
//	    static final String FACE_API="http://localhost/liveness/";
//		static final String FACE_API2="http://localhost/liveness/";

//	//production
//    static final String BILLING_HOST = "http://localhost:8080";
//    static final String ROOTDIR = System.getProperty("catalina.home") + "/webapps/DigitalSignatureAPI2.1/";
//    static final String KMS_HOST = "http://ejbca:7090/DSKeyCore";
//    static final String DOMAIN = "app.digisign.id";
//    static final String DOMAINAPI = "api.digisign.id";
//    static final String DOMAINAPIWV = "wvapi.digisign.id";
//    static final String SMS_API = "https://smsservice:7272/smsdigisign";
//    static final String WEBDOMAIN = "digisign.id";
//    //static final String SMS_API="https://localhost:7272";
//    static final String FACE_API = "https://facerec/";
//    static final String FACE_API2 = "https://facerec/";
//    static final String FACE_API_WITH_KTP = "https://facerec:8811/test";
//    static final String EMAIL_API = "https://emailservice:7272/mail/api/";
//    static final String DUKCAPIL_API = "https://192.168.182.7:7070/DSSv1-0/";//https://localhost
//    static final String LOGIN = "https://app.digisign.id/login.html";
//    static final String LINK = "app.digisign.id/forgotpassword.html";
//    static final String FTPPATH = "/opt/data-DS/ftp/";
//    static final String KILLBILL = "https://localhost:1070/BillingAPI/";
//    static final String FACE_RECOGNATION = "https://facerec:8811";
//    static final String FACE_TO_FACE = "https://facerec:8811/find_fcn";
//    public final static String FILESYS_SERVER_ADDRESS = "FILESTORAGE";
//    public final static String FILESYS_USERNAME = "spinku";
//    public final static String FILESYS_PASSWORD = "kajUBy97Snbbfrq124$3sdqQ1";
//    public final static String FILESYS_DOMAIN = "WORKGROUP";
//  Testing 192.168.182.7
    static final String BILLING_HOST = "http://192.168.182.7:8080";
    static final String ROOTDIR = System.getProperty("catalina.home") + "/webapps/DigitalSignatureAPI2.0/";
    static final String KMS_HOST = "http://ejbca:7090/DSKeyCore";
    static final String LINK = "app.tandatanganku.com/forgotpassword.html";
    static final String DOMAIN = "app.tandatanganku.com";
    static final String DOMAINAPI = "api.tandatanganku.com";
    static final String DOMAINAPIWV = "wvapi.tandatanganku.com";
    static final String SMS_API = "https://192.168.182.7:7272/smsdigisign";
    static final String WEBDOMAIN = "tandatanganku.com";
    static final String FACE_API = "https://facerec/";
    static final String FACE_API2 = "https://facerec/";
    static final String FACE_API_WITH_KTP = "https://facerec:8811/test";
    static final String FACE_RECOGNATION = "https://facerec:8811";
    static final String EMAIL_API = "https://emailservice:7272/mail/api/";
    static final String DUKCAPIL_API = "https://192.168.182.7:7070/DSSv1-0/";
    static final String LOGIN = "https://app.tandatanganku.com/login.html";
    static final String FTPPATH = "/data/";
    static final String KILLBILL = "https://192.168.182.7:1070/BillingAPI/";
    static final String FACE_TO_FACE = "https://facerec:8811/find_fcn";
    static final String FILESYS_SERVER_ADDRESS = "192.168.78.16";
    static final String FILESYS_USERNAME = "spinku";
    static final String FILESYS_PASSWORD = "spinku12345";
    static final String FILESYS_DOMAIN = "WORKGROUP";
    //UAT
    /*
	    static final String BILLING_HOST="http://192.168.182.7:8080";
		static final String ROOTDIR=System.getProperty("catalina.home")+"/webapps/DigitalSignatureAPI2.0/";
		static final String KMS_HOST="http://ejbca:7090/DSKeyCore";
		static final String LINK="app.tandatanganku.com/forgotpassword.html";
		static final String DOMAIN="app.tandatanganku.com";
		static final String DOMAINAPI="apiuat.tandatanganku.com";
		static final String DOMAINAPIWV="wvuat.tandatanganku.com";
		static final String WEBDOMAIN="https://tandatanganku.com";
		static final String SMS_API="https://192.168.182.7:7272/smsdigisign";
		static final String FACE_API="https://facerec/";
		static final String FACE_API2="https://facerec/";
		static final String FACE_API_WITH_KTP="https://facerec:8811/test";
		static final String EMAIL_API="https://emailservice:7272/mail/api/";
		static final String DUKCAPIL_API="https://192.168.182.7:7070/DSSv1-0/";
		static final String LOGIN="https://app.tandatanganku.com/login.html";
		static final String FTPPATH="/opt/data-DS/ftp/";
		static final String FACE_RECOGNATION="https://facerec:8811";
		static final String KILLBILL="https://192.168.182.7:1070/BillingAPI/";
		static final String FACE_TO_FACE="https://facerec:8811/find_fcn";
		static final String FILESYS_SERVER_ADDRESS="192.168.78.16";
		static final String FILESYS_USERNAME="spinku";
		static final String FILESYS_PASSWORD="spinku12345";
		static final String FILESYS_DOMAIN="WORKGROUP";
     */
    //Development
    /*
    static final String BILLING_HOST="http://192.168.182.7:8080";
		static final String ROOTDIR=System.getProperty("catalina.home")+"/webapps/DigitalSignatureAPI2.0/";
		static final String KMS_HOST="http://ejbca:7090/DSKeyCore";
		static final String LINK="app.tandatanganku.com/forgotpassword.html";
		static final String DOMAIN="app.tandatanganku.com";
		static final String DOMAINAPI="api.tandatanganku.com";
		static final String DOMAINAPIWV="wvapi.tandatanganku.com";
//		static final String SMS_API="https://corp.tandatanganku.com";
		static final String SMS_API="https://192.168.182.7:7272/smsdigisign";
		static final String WEBDOMAIN="https://tandatanganku.com";
		static final String FACE_API="https://192.168.250.10:5555/development/facetoktp";
		static final String FACE_API2="https://192.168.250.10:5555/development/facetoktp";
		static final String FACE_API_WITH_KTP="https://192.168.250.10:5555/development/facetoface";
		static final String FACE_RECOGNATION="https://192.168.250.10:8811";
		static final String EMAIL_API="https://192.168.182.7:7272/mail_dev/api/";
		static final String DUKCAPIL_API="https://192.168.182.7:7070/DSSv1-0/";
		static final String LOGIN="https://app.tandatanganku.com/login.html";
		static final String FTPPATH="/opt/data-DS/ftp/";
		static final String KILLBILL="https://192.168.182.7:1070/BillingAPI/";
     */
}
