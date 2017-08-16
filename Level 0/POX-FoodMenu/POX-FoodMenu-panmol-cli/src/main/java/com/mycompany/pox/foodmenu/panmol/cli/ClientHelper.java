/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pox.foodmenu.panmol.cli;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.logging.Level;

import javax.ws.rs.core.MediaType;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 *
 * @author palak
 */

class ClientHelper{
    
  //  private static final Logger LOG = LoggerFactory.getLogger(ClientHelper.class);
    private static WebResource webResource = null;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/FoodItems/webapi";

    public ClientHelper() {
      

        ClientConfig config = new DefaultClientConfig();
        client = Client.create(config);

        webResource = client.resource(BASE_URI).path("inventory");
       // LOG.debug("webResource = {}", webResource.getURI());
    }
    public static String request(String filename) {
        String requestMessage = null;
        String responseMessage ;
        try {
            requestMessage = convertXml(ClientHelper.class.getClassLoader().getResourceAsStream(filename));
        } catch (ParserConfigurationException ex) {
          // java.util.logging.Logger.getLogger(ClientHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
       System.out.println(requestMessage);
       ClientResponse response = webResource.type(MediaType.APPLICATION_XML).post(ClientResponse.class,requestMessage);
        responseMessage = response.getEntity(String.class);
       return responseMessage ; 
    }
    
  
    public static String convertXml(InputStream xmlFilename) throws ParserConfigurationException{
     DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = dbFactory.newDocumentBuilder();
        Document xmlDom = null;
        try {
            xmlDom = docBuilder.parse(xmlFilename);
        } catch (SAXException ex) {
           // java.util.logging.Logger.getLogger(ClientHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            //java.util.logging.Logger.getLogger(ClientHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return convertDocumentToString(xmlDom);
    }
    private static String convertDocumentToString(Document doc) {
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer;
        try {
            transformer = tf.newTransformer();
            // below code to remove XML declaration
            // transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(doc), new StreamResult(writer));
            String output = writer.getBuffer().toString();
            return output;
        } catch (TransformerException e) {
          //  e.printStackTrace();
        }
        
        return null;
    }
}