/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gradebook.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author palak
 */
public class StudentClient {
    private WebResource webResource;
    private Client client;
   private static final String BASE_URI = "http://localhost:8080/CRUD-GradeBook-panmol-srv/webapi";
    
    public StudentClient(){
    ClientConfig config = new DefaultClientConfig();
        client = Client.create(config);
      webResource = client.resource(BASE_URI).path("multiple");
       //System.out.println(webResource.toString());
    }
    
    public ClientResponse createTask(Object requestEntity) {
       System.out.println("Request : Create");
        ClientResponse response = webResource.type(MediaType.APPLICATION_XML).post(ClientResponse.class, requestEntity);       
        return response ; 
    }

    public ClientResponse deleteTask(String type , String studentID) {
        System.out.println("Request : Delete"); 
        ClientResponse response =webResource.path("id/"+studentID+"/task/"+type).delete(ClientResponse.class);       
        return response ;//To change body of generated methods, choose Tools | Templates.
    }

    public <T> T retrieveTask(Class<T> responseType , String type , String studentID) {
       System.out.println("Request : Read");
        return webResource.path("id/"+studentID+"/task/"+type).accept(MediaType.APPLICATION_XML).get(responseType);     
      //  return response ;
    }

    public ClientResponse updateTask(String requestEntity , String type , String studentID) {
     System.out.println("Request : Update");
        ClientResponse response =webResource.path("id/"+studentID+"/task/"+type).type(MediaType.APPLICATION_XML).put(ClientResponse.class, requestEntity);       
        return response ;
    }
   /* public void close() {
       
        
        client.destroy();
    }
*/
    public ClientResponse appealTask(String requestEntity, String type, String studentID) {
         //To change body of generated methods, choose Tools | Templates.
       System.out.println("Request : Appeal");
         ClientResponse response =webResource.path("id/"+studentID+"/task/"+type).type(MediaType.APPLICATION_XML).put(ClientResponse.class, requestEntity);       
        return response ;
    }
}
