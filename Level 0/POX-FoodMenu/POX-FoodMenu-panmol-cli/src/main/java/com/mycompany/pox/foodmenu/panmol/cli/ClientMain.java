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
import java.util.Scanner;

/**
 *
 * @author palak
 */
public class ClientMain {
    
    public static void main( String[] args ){
        
        String responseMessage ; 
        ClientHelper connection = new ClientHelper();
   
        System.out.println("\n\n******************************************");
        System.out.println("Add an item:");
        responseMessage = connection.request("Add.xml");
        System.out.println("\nResponse message:  ");
        System.out.println(responseMessage);
        System.out.println("******************************************");
           
        System.out.println("\n\n******************************************") ;       
        System.out.println("Invalid tag item:  ");
        responseMessage = connection.request("InvalidTagAdd.xml");
        System.out.println("\nResponse Message: ");
        System.out.println(responseMessage);
        System.out.println("******************************************") ; 
        
        System.out.println("\n\n******************************************") ; 
        System.out.println("Missing Tagname on adding an item:");
        responseMessage = connection.request("MissingTagAdd.xml");
        System.out.println("\nResponse Message: ");
        System.out.println(responseMessage);
        System.out.println("******************************************") ; 
        
        System.out.println("\n******************************************") ; 
        System.out.println("Add food  that exists: ");
        responseMessage = connection.request("FoodExists.xml");
        System.out.println("\nResponse Message: ");
        System.out.println(responseMessage);
        System.out.println("******************************************") ; 
        
       System.out.println("\n\n******************************************") ;  
        System.out.println("Search items: ");
        responseMessage = connection.request("Search.xml");
        System.out.println("\nResponse Message: ");
        System.out.println(responseMessage);
         System.out.println("******************************************") ; 
         
         System.out.println("\n\n******************************************") ;  
        System.out.println("Search items where some does not exists: ");
        responseMessage = connection.request("SearchExists.xml");
        System.out.println("\nResponse Message: ");
        System.out.println(responseMessage);
         System.out.println("******************************************") ; 
        
         System.out.println("\n\n******************************************") ; 
        System.out.println("Invalid Tag Search: ");
        responseMessage = connection.request("InvalidTagSearch.xml");
        System.out.println("\nResponse Message: ");
        System.out.println(responseMessage);
        System.out.println("******************************************") ; 
        
        System.out.println("\n\n******************************************") ; 
        System.out.println("Invalid XML parsing:");
        responseMessage = connection.request("InvalidXML.xml");
        System.out.println("\nResponse Message: ");
        System.out.println(responseMessage);
        System.out.println("******************************************") ; 
        
    
    }
}
