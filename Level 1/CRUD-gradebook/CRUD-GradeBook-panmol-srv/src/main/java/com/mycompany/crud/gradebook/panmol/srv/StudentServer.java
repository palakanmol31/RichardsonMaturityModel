/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.crud.gradebook.panmol.srv;

import com.mycompany.crud.gradebook.panmol.serverhelper.Converter;
import com.mycompany.crud.gradebook.panmol.serverhelper.StudentDetails;
import java.net.URI;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBException;

/**
 *
 * @author palak
 */
@Path("multiple")
public class StudentServer {
    // private StudentDetails serverHelper ; 
    public static HashMap<Integer,StudentDetails> sID = new HashMap<Integer,StudentDetails>();  
    
    @Context
    private UriInfo context;
    
    
    
    public StudentServer(){
    System.out.println("Request To Server");
    }
   
    @POST
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_XML)
    public Response createFunction(String content) {
        Response response ; 
        StudentDetails student   ;
       
       // 
           
    
           try{
                //sID.put(studentId);
                student = (StudentDetails) Converter.convertFromXmlToObject(content, StudentDetails.class); 
               if(student.getId()==0 || student.getTask().equals("Select Task")){
                response = Response.status(Response.Status.BAD_REQUEST).entity(content).build();
                    return response ; 
                }
                
                if(!sID.isEmpty()){    
                Iterator<Integer> it = sID.keySet().iterator();
                while(it.hasNext()){
                int key = it.next();
                StudentDetails stud = sID.get(key);
                if(student.getTask().equals(stud.getTask())) {
                    response = Response.status(Response.Status.CONFLICT).entity(content).build();
                    return response ; 
                } 
             }
            }
                
                 
                  /*  Random randomGenerator = new Random();
                    int studentId = Math.abs(randomGenerator.nextInt(1000));*/
              
              //      student.setId(studentId);
                    String xmlString = Converter.convertFromObjectToXml(student, StudentDetails.class);
                    sID.put(student.getId(),student);
                    //URI locationURI = URI.create(context.getAbsolutePath() + "/" + Integer.toString(studentId));
                    URI locationURI = URI.create(context.getAbsolutePath() + "/id/" + Integer.toString(student.getId())+ "/task/"+Integer.toString(setMenuDetails(student.getTask())));
                    System.out.println(locationURI);
                    response = Response.status(Response.Status.CREATED).location(locationURI).entity(xmlString).build();
                    
            } catch (JAXBException e) {
                
               // System.out.println("JAXBE : " + e);
                response = Response.status(Response.Status.BAD_REQUEST).entity(content).build();
            } catch (RuntimeException e) {
               
                response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(content).build();
            }
        
               
        return response;
    }
    
    @GET
    @Path("id/{id}/task/{type}")
    @Produces(MediaType.APPLICATION_XML)
    public Response getResource(@PathParam("type") String type, @PathParam("id") String id) {
        StudentDetails student = null ;
        
        Response response=null;
        
        if (sID.isEmpty()){
            response = Response.status(Response.Status.GONE).entity("No student details to return").build();
        } else {
            if(sID.containsKey(Integer.parseInt(id))){
                student = sID.get(Integer.parseInt(id));
                if(type.equals(Integer.toString(setMenuDetails(student.getTask())))){
                String xmlString = Converter.convertFromObjectToXml(student, StudentDetails.class);
                response = Response.status(Response.Status.OK).entity(xmlString).build();
                }
                else {
                response = Response.status(Response.Status.NOT_FOUND).entity("No student entry").build();
            }
            } else {
                response = Response.status(Response.Status.NOT_FOUND).entity("No student entry").build();
            }
        }        
        return response;
    }
    
    
    @PUT
    @Path("id/{id}/task/{type}")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response updateResource(@PathParam("type") String type, @PathParam("id") String id, String content) {
        StudentDetails student = null; 
        
        Response response;
        
        if (!sID.isEmpty() && sID.containsKey(Integer.parseInt(id))){
            try {
                student = (StudentDetails) Converter.convertFromXmlToObject(content, StudentDetails.class);
                StudentDetails st =  sID.get(Integer.parseInt(id));
                
                /*if(student.getAppeal()){
                    if(!st.getAppeal()){
                        st.setAppeal(true);
                        sID.put(Integer.parseInt(id), st);
                        String xmlString = Converter.convertFromObjectToXml(st, StudentDetails.class);

                        response = Response.status(Response.Status.OK).entity(xmlString).build();
                        return response; 
                    }
                    
                }
                */
                
              if(student.getTask().equals("Select Task")){
                    response = Response.status(Response.Status.BAD_REQUEST).entity(content).build();
                    return response; 
                }
              if(!Integer.toString(setMenuDetails(st.getTask())).equals(type)){
                    response = Response.status(Response.Status.CONFLICT).entity(content).build();
                    return response; 
                }
                
             if(student.getAppeal()){
                    if(!st.getAppeal()){
                        st.setAppeal(true);
                    }
                    sID.put(Integer.parseInt(id), st);
                    String xmlString = Converter.convertFromObjectToXml(st, StudentDetails.class);

                    response = Response.status(Response.Status.OK).entity(xmlString).build();
                    return response; 
                }
                else if(!student.getAppeal() && st.getAppeal()){
                    student.setAppeal(true);
                    sID.put(Integer.parseInt(id), student);
                    String xmlString = Converter.convertFromObjectToXml(student, StudentDetails.class);

                    response = Response.status(Response.Status.OK).entity(xmlString).build();
                    return response; 
                }
              
                
                sID.put(Integer.parseInt(id), student);
                String xmlString = Converter.convertFromObjectToXml(student, StudentDetails.class);
                
                response = Response.status(Response.Status.OK).entity(xmlString).build();
            } catch (JAXBException e) {
                
                response = Response.status(Response.Status.BAD_REQUEST).entity(content).build();
            } catch (RuntimeException e) {
                
                response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(content).build();
            }
        } else {
                      
            response = Response.status(Response.Status.CONFLICT).entity(content).build();
        }

        
        return response;
    }

   @DELETE
    @Path("id/{id}/task/{type}")
    @Produces(MediaType.APPLICATION_XML)
    public Response deleteResource(@PathParam("type") String type , @PathParam("id") String id) {
      
       // StudentDetails student = null ; 
        Response response=null;
        
        if (sID.isEmpty()){
            response = Response.status(Response.Status.GONE).entity("No record to delete").build();
            return response ;
        } else {
            if (sID.containsKey( Integer.parseInt(id))){
                if((Integer.toString(setMenuDetails(sID.get(Integer.parseInt(id)).getTask()))).equals(type)){
                sID.remove(Integer.parseInt(id)) ; 
                response = Response.status(Response.Status.NO_CONTENT).entity("Record Deleted").build();
            }
                else {
                response = Response.status(Response.Status.NOT_FOUND).entity("Record not found").build();
            }
            } else {
                response = Response.status(Response.Status.NOT_FOUND).entity("Record not found").build();
            }
        }       
        return response;
    }
    
    
    private static int setMenuDetails(String selectedItem){
        int item = 0; 
        if(selectedItem.equals("Select Task"))
            item = 0 ;
        else if(selectedItem.equals("Assignment"))
            item = 1; 
        else if(selectedItem.equals("Lab"))
            item = 2; 
        else if(selectedItem.equals("Quiz"))
            item = 3 ; 
        else if(selectedItem.equals("Midterm")) 
            item = 4 ; 
        else if(selectedItem.equals("Project"))  
            item = 5 ;  
        
         
        
        return item ; 
    }
    
}
