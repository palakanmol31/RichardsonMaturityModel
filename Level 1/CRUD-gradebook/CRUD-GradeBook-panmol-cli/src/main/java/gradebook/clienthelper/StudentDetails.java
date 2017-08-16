/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gradebook.clienthelper;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author palak
 */
@XmlRootElement
@XmlType(propOrder={
    "id",
    "task",
    "weight",
    "grade",
    "feedback",
    "appeal"})
public class StudentDetails {
    

    private String grade;
    private String task;
    private String feedback;
    private int id ; 
   private String weight ; 
    private boolean appeal ; 
  

    public String getFeedback() {
        return feedback;
    }

    @XmlElement
    public void setFeedback(String feedback) {
       
        this.feedback = feedback;
        
    }

    public String getGrade() {
        return grade;
    }
    
    @XmlElement
    public void setAppeal(boolean appeal) {
       
        this.appeal = appeal;
        
    }

    public boolean getAppeal() {
        return appeal;
    }

    @XmlElement
    public void setGrade(String grade) {
       
        this.grade = grade;
        
    }

    public int getId() {
        return id;
    }
    
    @XmlElement
    public void setWeight(String studID) {
       
        this.weight = studID;
        
    }

    public String getWeight() {
        return weight;
    }

    @XmlAttribute
    public void setId(int id) {
       
        this.id = id;
           
    }

    @Override
    public String toString() {
        return "StudentDetails{" + "weight=" + weight + ", task=" + task + ", grade=" + grade + ", feedback=" + feedback + ", appeal=" + appeal + ", id=" + id + '}';
    }
    
    /* @Override
    public String toString() {
        return "StudentDetails{" + "task=" + task + ", grade=" + grade + ", feedback=" + feedback + ", appeal=" + appeal + ", id=" + id + '}';
    }
*/
    @XmlElement
    public void setTask(String task) {
    this.task = task ;
    }

    public String getTask() {
        return task ;
    }
}
    
