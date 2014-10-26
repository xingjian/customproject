package com.dhcc.gis.wmts;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
/**
 * 
* @类名: WMTSService.java 
* @包名: com.dhcc.gis.wmts 
* @描述: 处理wmts
* @作者: xingjian xingjian@yeah.net   
* @日期:2014-3-20 上午11:36:16 
* @版本: V1.0
 */
@SuppressWarnings("all")
public class WMTSService  extends HttpServlet{
    
    private String cacheDir = "/cache/";
    private Logger log = LoggerFactory.getLogger(this.getClass());
    private String useCache = "false";
    
    public void init() throws ServletException{
        if (StringUtils.isNotBlank(getInitParameter("cacheDir"))) {
            cacheDir = getInitParameter("cacheDir")+"\\cache\\";
        }
        if (StringUtils.isNotBlank(getInitParameter("useCache"))) {
            useCache = getInitParameter("useCache");
        }
    }
    
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException{
        getTile(req,res);
    }

    private void response(HttpServletResponse res,byte[] data){
        try {
            res.setStatus(HttpServletResponse.SC_OK);
            res.setContentType("application/vnd.ogc.wms_xml");
            res.setContentLength(data.length);
            res.getOutputStream().write(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   private void getTile(HttpServletRequest req,HttpServletResponse res){
	   String baseUrl = req.getParameter("mapURL");
        try {
            String x = req.getParameter("x");
            String y = req.getParameter("y");
            String z = req.getParameter("z");
            String mapType = req.getParameter("maptype");
            if (useCache.equals("true")) {
                File file = new File(cacheDir+mapType+File.separator+z+File.separator+x+File.separator+y+".png");
                if(file.exists() && file.length()>0){
                    FileInputStream fis = new FileInputStream(file);
                    BufferedInputStream fb = new BufferedInputStream(fis);
                    byte[] data = new byte[1024];
                    int c = 0;
                    while ((c=fb.read(data))!=-1){
                        res.getOutputStream().write(data,0,c);
                    }
                    fb.close();
                    res.getOutputStream().flush();
                    res.getOutputStream().close();
                }else {
                    File f = new File(cacheDir+mapType+File.separator+z+File.separator+x);
                    if(!f.exists()){
                        f.mkdirs();
                    }
                    File f1 = new File(cacheDir+mapType+File.separator+z+File.separator+x+File.separator+y+".png");
                    FileOutputStream fos = new FileOutputStream(f1);
                    BufferedOutputStream fbo = new BufferedOutputStream(fos);
                    URL url = new URL(baseUrl);
                    URLConnection urlc = url.openConnection();
                    urlc.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
                    urlc.setConnectTimeout(3000);
                    urlc.setReadTimeout(100000);
                    res.setContentType(urlc.getContentType());
                    byte[] data = new byte[1024];
                    BufferedInputStream input = new BufferedInputStream(urlc.getInputStream());
                    int c = -1;
                    while ((c=input.read(data))!=-1){
                        res.getOutputStream().write(data,0,c);
                        fbo.write(data,0,c);
                    }
                    fbo.flush();
                    fbo.close();
                    input.close();
                    res.getOutputStream().flush();
                    res.getOutputStream().close();
                }
            }else {
                URL url = new URL(baseUrl);
                URLConnection urlc = url.openConnection();
                urlc.setConnectTimeout(5000);
                urlc.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
                urlc.setReadTimeout(100000);
                res.setContentType(urlc.getContentType());
                byte[] data = new byte[1024];
                BufferedInputStream input = new BufferedInputStream(urlc.getInputStream());
                int c = -1;
                while ((c=input.read(data))!=-1){
                    res.getOutputStream().write(data,0,c);
                }
                res.getOutputStream().flush();
                res.getOutputStream().close();
                input.close();
            }
        }catch (UnknownHostException ex) {
            log.error("无法连接Google服务器!");
        }catch (Exception e) {
        }
    }
}
