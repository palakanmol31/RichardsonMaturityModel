package com.mycompany.pox.foodmenu.panmol;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.core.Response;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
/**
 * Root resource (exposed at "inventory" path)
 */
@Path("inventory")
public class Server {
  //  private static final String xmlFilename = "/Users/palak/NetBeansProjects/mavenproject2/POX-FoodMenu-panmol/src/main/resources/FoodItemData.xml" ;
   
  
    @POST 
    @Consumes(MediaType.APPLICATION_XML)
    public Response handleRequestsFromClient(String requestMessage) throws IOException {
        //File p = File.createTempFile("ab", ".xml");
         Response response; 
          String res = null ; 
        try{
          Document doc = convertStringToDocument(requestMessage);
          NodeList nodeList=doc.getElementsByTagName("*");
          Element element = (Element)nodeList.item(0);
            if(element.getNodeName().equals("NewFoodItems")){
                try {
                res = addFoodItems(doc);
                }
                catch(Exception e){
                 res = "<InvalidMessage xmlns=\"http://cse564.asu.edu/PoxAssignment\"/>" ;
                }
            }
            else if(element.getNodeName().equals("SelectedFoodItems")){
               try {
                res = getFoodItems(doc);
                }
                catch(Exception e){
                 res = "<InvalidMessage xmlns=\"http://cse564.asu.edu/PoxAssignment\"/>" ;
                }
            }
            else
                res = "<InvalidMessage xmlns=\"http://cse564.asu.edu/PoxAssignment\"/>" ;
            
        //response = Response.status(Response.Status.OK).entity(res).build();
        }
        catch(Exception e) {
             res = "<InvalidMessage xmlns=\"http://cse564.asu.edu/PoxAssignment\"/>" ;
          //  response = Response.status(Response.Status.OK).entity(res).build();
        }
        finally{
        response = Response.status(Response.Status.OK).entity(res).build();
        }
        return response;        
        
    }
    
    private static Document convertStringToDocument(String xmlStr) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
        DocumentBuilder builder;  
        try  
        {  
            builder = factory.newDocumentBuilder();  
            Document doc = builder.parse( new InputSource( new StringReader( xmlStr ) ) ); 
            return doc;
        } catch (Exception e) {  
           // e.printStackTrace();  
        } 
        return null;
    }

    private String addFoodItems(Document doc) throws SAXException, IOException {
        System.out.println("Inside add food items : 1");
        String response = "" , country = "" , name = "";
        String category = "" , price = ""; 
        String description = "";
        NodeList foodItems=doc.getElementsByTagName("FoodItem");
        Node p=foodItems.item(0);                    
            if(p.getNodeType()==Node.ELEMENT_NODE){
                Element foodItem = (Element)p;
                country=foodItem.getAttribute("country");
                name= foodItem.getElementsByTagName("name").item(0).getTextContent();
                category= foodItem.getElementsByTagName("category").item(0).getTextContent();
                price= foodItem.getElementsByTagName("price").item(0).getTextContent();
                description= foodItem.getElementsByTagName("description").item(0).getTextContent();
            }
        String searchID = searchID(country , name , category) ; 
            if(searchID.split(",")[0].equals("false")){
               
                String itemID=null ; 
            try {
                itemID = add(country , name , category , description , price);
                
            } catch (ParserConfigurationException ex) {
             //   Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
             response = "<InvalidMessage xmlns=\"http://cse564.asu.edu/PoxAssignment\"/>";
                
            }
                
                response = "<FoodItemAdded xmlns=”http://cse564.asu.edu/PoxAssignment”>\n" +
"    <FoodItemId>"+itemID+"</FoodItemId>\n" +
"</FoodItemAdded>" ;

            }
            else if(searchID.split(",")[0].equals("true")){
               
          response =  "<FoodItemExists xmlns=”http://cse564.asu.edu/PoxAssignment”>\n" +
"    <FoodItemId>"+searchID.split(",")[1]+"</FoodItemId>\n" +
"</FoodItemExists>" ;
            }
   

               
        return response; 
    }

    private String getFoodItems(Document doc) {
       String response = "" ;
       NodeList FoodItemList=doc.getElementsByTagName("FoodItemId");
       if(FoodItemList.getLength()!=0){
       for(int i=0;i<FoodItemList.getLength();i++){     
            Node p=FoodItemList.item(i);                    
            if(p.getNodeType()==Node.ELEMENT_NODE){
                Element foodItem = (Element)p;
                response = response + getByID(foodItem.getTextContent());
            }
       }            
        return "<RetrievedFoodItems xmlns=\"http://cse564.asu.edu/PoxAssignment\">\n" + response +
"</RetrievedFoodItems>" ;  //To change body of generated methods, choose Tools | Templates.
    }
       else
           return "<InvalidMessage xmlns=\"http://cse564.asu.edu/PoxAssignment\"/>" ;
    }

    private String searchID(String country, String name, String category) throws SAXException, IOException {
      //  System.out.println("Inside Search") ; 
       DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder= factory.newDocumentBuilder();
             URL url = getClass().getResource("/FoodItemData.xml");
        File xF = new File(url.getFile());
        InputStream xmlFilename = new FileInputStream(xF);
            Document doc=builder.parse(xmlFilename);
            //xmlFilename.reset();
            //doc.getDocumentElement().normalize();
            NodeList FoodItemList=doc.getElementsByTagName("FoodItem");
            // sss = sss + "In searchID" ;
            for(int i=0;i<FoodItemList.getLength();i++){
                Node p=FoodItemList.item(i);
                if(p.getNodeType()==Node.ELEMENT_NODE){
                    Element foodItem = (Element)p;
                    // sss = sss + " Value " + foodItem.getElementsByTagName("id") ;
                    if(foodItem.getAttribute("country").equals(country) &&(foodItem.getElementsByTagName("name").item(0)!=null)&& foodItem.getElementsByTagName("name").item(0).getTextContent().equals(name) && foodItem.getElementsByTagName("category").item(0).getTextContent().equals(category))
                        return  "true,"+foodItem.getElementsByTagName("id").item(0).getTextContent() ;
                }
            }
            
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    return "false";
    }
       // return searchResult ; 
    

    private String add(String country, String name, String category, String description, String price) throws ParserConfigurationException, IOException {
      //System.out.println("Inside Add") ; 
        int newID  =  idGeneration(country) ;
        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        DocumentBuilder builder= factory.newDocumentBuilder();
        Document doc = null;
        try {
             URL url = getClass().getResource("/FoodItemData.xml");
    File xF = new File(url.getFile());    
    InputStream xmlFilename = new FileInputStream(xF);
            doc = builder.parse(xmlFilename);
         //   xmlFilename.reset();
        } catch (SAXException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        
        Element root1=doc.getDocumentElement();
        
        Element root=doc.createElement("FoodItem");
        root.setAttribute("country", country);
        root1.appendChild(root);
        
        Element id=doc.createElement("id");
        id.appendChild(doc.createTextNode(newID+""));
        root.appendChild(id);
        
        Element nameTag=doc.createElement("name");
        nameTag.appendChild(doc.createTextNode(name));
        root.appendChild(nameTag);
        
        Element desc1=doc.createElement("description");
        desc1.appendChild(doc.createTextNode(description));
        root.appendChild(desc1);
        
        Element categoryName=doc.createElement("category");
        categoryName.appendChild(doc.createTextNode(category));
        root.appendChild(categoryName);
        
        Element priceName=doc.createElement("price");
        priceName.appendChild(doc.createTextNode(price));
        root.appendChild(priceName);
        
       TransformerFactory factory1 = TransformerFactory.newInstance();
       Transformer transformer = null;
       try {
           transformer = factory1.newTransformer();
       } catch (TransformerConfigurationException ex) {
           java.util.logging.Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
       }
        DOMSource domSource = new DOMSource(doc);
        URL url = getClass().getResource("/FoodItemData.xml");
        File xF = new File(url.getFile());
       // URL resource = Server.class.getResource("/FoodItemData.xml");
        StreamResult streamResult = new StreamResult(xF);
//       xmlFilename.reset();
        try {
           transformer.transform(domSource, streamResult);
          
       } catch (TransformerException ex) {
           java.util.logging.Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
       }
        return Integer.toString(newID);
       //return "101";
    }

    private int idGeneration(String country) throws IOException {
        
        System.out.println("Inside Id generation");
        HashMap<String,Integer> countryList=new HashMap<String,Integer>();
        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder= factory.newDocumentBuilder();
            try {
                 URL url = getClass().getResource("/FoodItemData.xml");
    File xF = new File(url.getFile());
            
    InputStream xmlFilename = new FileInputStream(xF);
                Document doc=builder.parse(xmlFilename);
                //xmlFilename.reset();
                NodeList FoodItemList=doc.getElementsByTagName("FoodItem");
                for(int i=0;i<FoodItemList.getLength();i++){                    
                    Node p=FoodItemList.item(i);                    
                    if(p.getNodeType()==Node.ELEMENT_NODE){
                        Element foodItem = (Element)p;
                        if(countryList.containsKey(foodItem.getAttribute("country"))) 
                            countryList.put(foodItem.getAttribute("country"),countryList.get(foodItem.getAttribute("country"))+1) ;  
                        else
                            countryList.put(foodItem.getAttribute("country"),Integer.parseInt(foodItem.getElementsByTagName("id").item(0).getTextContent()));
                        
                    }   
                }
            } catch (SAXException ex) {
             java.util.logging.Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
               java.util.logging.Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (ParserConfigurationException ex) {
           java.util.logging.Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        if(countryList.containsKey(country))
            return countryList.get(country) + 1 ;
        else
            return (countryList.size()+1)*100;
    }

    private String getByID(String requestID) {
        //String response = "" ; 
        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder= factory.newDocumentBuilder();
            try {
                 URL url = getClass().getResource("/FoodItemData.xml");
    File xF = new File(url.getFile());
            
    InputStream xmlFilename = new FileInputStream(xF);
                Document doc=builder.parse(xmlFilename);
              //  xmlFilename.reset();
                NodeList FoodItemList=doc.getElementsByTagName("FoodItem");
                for(int i=0;i<FoodItemList.getLength();i++){                    
                    Node p=FoodItemList.item(i);                    
                    if(p.getNodeType()==Node.ELEMENT_NODE){
                        Element foodItem = (Element)p;
                        if(foodItem.getElementsByTagName("id").item(0).getTextContent().equals(requestID))
                            return "    <FoodItem country=\""+ foodItem.getAttribute("country")+"\">\n" +
      "        <id>"+foodItem.getElementsByTagName("id").item(0).getTextContent()+"</id>\n" +
      "        <name>"+foodItem.getElementsByTagName("name").item(0).getTextContent()+"</name>\n" +
       "        <description>"+foodItem.getElementsByTagName("description").item(0).getTextContent()+"</description>\n" +
        "        <category>"+foodItem.getElementsByTagName("category").item(0).getTextContent()+"</category>\n" +
    "        <price>"+ foodItem.getElementsByTagName("price").item(0).getTextContent() +"</price>\n" +
    "    </FoodItem>\n";
                    }
                }
            }
        catch (SAXException ex) {
                java.util.logging.Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (ParserConfigurationException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    return "    <InvalidFoodItem>\n" +
"         <FoodItemId>"+requestID+"</FoodItemId>\n" +
"    </InvalidFoodItem>\n";
    }
    
}
