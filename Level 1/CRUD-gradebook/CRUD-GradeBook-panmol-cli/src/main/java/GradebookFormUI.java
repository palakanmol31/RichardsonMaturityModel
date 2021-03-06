
import com.sun.jersey.api.client.ClientResponse;
import gradebook.client.StudentClient;
import gradebook.clienthelper.Converter;
import gradebook.clienthelper.StudentDetails;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author palak
 */
public class GradebookFormUI extends javax.swing.JFrame {
    private static StudentClient student; 
    /**
     * Creates new form FormUI
     */
    public GradebookFormUI() {
        initComponents();
        student = new StudentClient ();
    }
   
    private String convertFormToXMLString(){
        StudentDetails studentDetails = new StudentDetails();
        if (!id.getText().equals("")){
            studentDetails.setId(Integer.parseInt(id.getText()));
        }
      //  else
        //    studentDetails.setId(0);
        
        studentDetails.setWeight(weight.getText());
        studentDetails.setTask(task.getItemAt(task.getSelectedIndex()));
        studentDetails.setGrade(grade.getText());
        studentDetails.setFeedback(feedback.getText());
        studentDetails.setAppeal(appeal.isSelected());   
        String xmlString = Converter.convertFromObjectToXml(studentDetails, studentDetails.getClass());
        
        return xmlString;
    }
    
 
    
    private void populateForm(ClientResponse clientResponse, String val){
         
                
        
        try{
          if(val.equals("norm")){  
              String entity = clientResponse.getEntity(String.class);

            if ((clientResponse.getStatus() == Response.Status.OK.getStatusCode()) ||
                (clientResponse.getStatus() == Response.Status.CREATED.getStatusCode())){
                StudentDetails studentDetails = (StudentDetails)Converter.convertFromXmlToObject(entity,StudentDetails.class);
                
               
                id.setText(Integer.toString(studentDetails.getId()));
                weight.setText(studentDetails.getWeight());
                //task.setText(studentDetails.getTask());
                task.setSelectedItem(studentDetails.getTask());
                grade.setText(studentDetails.getGrade());
                feedback.setText(studentDetails.getFeedback());
                appealCheck.setSelected(studentDetails.getAppeal());
                
            } else {
                weight.setText("");
                //task.setText("");
                task.setSelectedIndex(0);
                grade.setText("");
                feedback.setText("");
               appealCheck.setSelected(false);
            }
            
            id.setEnabled(true);
                weight.setEnabled(true);
                task.setEnabled(true);
                grade.setEnabled(true);
                feedback.setEnabled(true);
                mediaType.setEnabled(true);
                location.setEnabled(true);
            
            // Populate HTTP Header Information
           //if(clientResponse!=null)
           
           message.setText(entity);
           System.out.println("Response : \n" + entity);
            statusCode.setText(Integer.toString(clientResponse.getStatus()));
            mediaType.setText(clientResponse.getType().toString());
            statusInfo.setText(clientResponse.getStatusInfo().toString());
            // The Location filed is only populated when a Resource is created
            if (clientResponse.getStatus() == Response.Status.CREATED.getStatusCode()){
                location.setText(clientResponse.getLocation().toString());
                System.out.println(location.getText());
            } else {
                location.setText("");
            }
          }
          else{
              task.setEnabled(false);
            grade.setEnabled(false);
            feedback.setEnabled(false);
          weight.setText("");
           //     task.setText("");
           task.setSelectedIndex(0);
                grade.setText("");
                feedback.setText("");
            
            
            // Populate HTTP Header Information
            statusCode.setText(Integer.toString(clientResponse.getStatus()));
           
            //mediaType.setText(clientResponse.getType().toString());
            statusInfo.setText(clientResponse.getStatusInfo().toString());
            location.setText("");
            mediaType.setText("");

          }
        } catch (JAXBException e){
            e.printStackTrace();
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popupMenu1 = new java.awt.PopupMenu();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        update = new javax.swing.JRadioButton();
        statusCode = new javax.swing.JTextField();
        delete = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        location = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        mediaType = new javax.swing.JTextField();
        id = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        create = new javax.swing.JRadioButton();
        grade = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        read = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        feedback = new javax.swing.JTextField();
        statusInfo = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        intructor = new javax.swing.JCheckBox();
        stud = new javax.swing.JCheckBox();
        appeal = new javax.swing.JRadioButton();
        appealCheck = new javax.swing.JCheckBox();
        jLabel12 = new javax.swing.JLabel();
        task = new javax.swing.JComboBox<>();
        jj = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        message = new javax.swing.JTextArea();
        jLabel13 = new javax.swing.JLabel();
        weight = new javax.swing.JTextField();

        popupMenu1.setLabel("popupMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        buttonGroup1.add(update);
        update.setText("Update");
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });

        statusCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statusCodeActionPerformed(evt);
            }
        });

        buttonGroup1.add(delete);
        delete.setText("Delete");
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });

        jLabel7.setText("Location");

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel2.setText("Student Details");

        jLabel8.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel8.setText("HTTP Header Info");

        jLabel3.setText("Student ID ");

        location.setEditable(false);
        location.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                locationActionPerformed(evt);
            }
        });

        jLabel4.setText("Work Task");

        jLabel9.setText("Media Type");

        jLabel6.setText("Grade");

        mediaType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mediaTypeActionPerformed(evt);
            }
        });

        id.setName("IdField"); // NOI18N
        id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel1.setText("Action");

        buttonGroup1.add(create);
        create.setText("Create");
        create.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createActionPerformed(evt);
            }
        });

        grade.setToolTipText("dd/MM/yyyy HH:mm:ss");
        grade.setName("PriorityField"); // NOI18N
        grade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gradeActionPerformed(evt);
            }
        });

        jButton1.setText("Submit");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(read);
        read.setText("Read");
        read.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                readActionPerformed(evt);
            }
        });

        jLabel5.setText("Status Code");

        jLabel10.setText("Feedback");

        feedback.setToolTipText("dd/MM/yyyy HH:mm:ss");
        feedback.setName("PriorityField"); // NOI18N
        feedback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                feedbackActionPerformed(evt);
            }
        });

        statusInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statusInfoActionPerformed(evt);
            }
        });

        jLabel11.setText("Status Info");

        buttonGroup2.add(intructor);
        intructor.setText("Instructor");
        intructor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                intructorActionPerformed(evt);
            }
        });

        buttonGroup2.add(stud);
        stud.setText("Student");
        stud.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studActionPerformed(evt);
            }
        });

        buttonGroup1.add(appeal);
        appeal.setText("Appeal");
        appeal.setEnabled(false);
        appeal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                appealActionPerformed(evt);
            }
        });

        appealCheck.setText("Grade Appeal");
        appealCheck.setEnabled(false);
        appealCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                appealCheckActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel12.setText(" Gradebook ");

        task.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Task", "Assignment", "Lab", "Quiz", "Midterm", "Project" }));
        task.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                taskActionPerformed(evt);
            }
        });

        jj.setText("Response");

        message.setEditable(false);
        message.setColumns(20);
        message.setRows(5);
        message.setAutoscrolls(false);
        jScrollPane1.setViewportView(message);

        jLabel13.setText("% Weightage");

        weight.setName("IdField"); // NOI18N
        weight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                weightActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(create)
                                .addGap(136, 136, 136)
                                .addComponent(jLabel3))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(read)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(appeal)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel10))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(update)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel13))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(delete)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel6)))
                                .addGap(11, 11, 11)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(feedback, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(grade, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                                .addComponent(id, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                                .addComponent(task, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(6, 6, 6)
                                    .addComponent(weight, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE))))
                        .addContainerGap(185, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(226, 226, 226))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButton1)
                                    .addComponent(jLabel8)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(statusCode, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(statusInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(mediaType))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(27, 27, 27)
                                .addComponent(location, javax.swing.GroupLayout.PREFERRED_SIZE, 423, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 6, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(183, 183, 183)
                        .addComponent(intructor)
                        .addGap(58, 58, 58)
                        .addComponent(stud, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(269, 269, 269)
                        .addComponent(jLabel12)))
                .addGap(242, 242, 242))
            .addGroup(layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jj)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(appealCheck)
                .addGap(303, 303, 303))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(intructor)
                    .addComponent(stud))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(create, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(read)
                    .addComponent(task, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(update)
                    .addComponent(jLabel13)
                    .addComponent(weight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(delete)
                    .addComponent(grade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(appeal, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(feedback, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(1, 1, 1)
                .addComponent(appealCheck)
                .addGap(10, 10, 10)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(mediaType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(statusCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(location, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jj))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(148, 148, 148))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
     
        
       clearTextField();
        id.setEnabled(true);
       weight.setEnabled(true);
       task.setEnabled(true);
       grade.setEnabled(true);
       feedback.setEnabled(true);
       
       
      
        // appeal.setSelected(false);
        // appeal.setEnabled(false);
        
     //  appealCheck.setEnabled(false);
       //appealCheck.setSelected(false);
    }//GEN-LAST:event_updateActionPerformed

    private void statusCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statusCodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_statusCodeActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        clearTextField();
        id.setEnabled(true);
      weight.setEnabled(false);
       task.setEnabled(true);
       grade.setEnabled(false);
       feedback.setEnabled(false);
     
      
         //appeal.setEnabled(false);
       
      // appealCheck.setEnabled(false);
       //appealCheck.setSelected(false);
    }//GEN-LAST:event_deleteActionPerformed

    private void locationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_locationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_locationActionPerformed

    private void mediaTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mediaTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mediaTypeActionPerformed

    private void idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idActionPerformed

    private void gradeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gradeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_gradeActionPerformed

    private void createActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createActionPerformed
     clearTextField();
       id.setEnabled(true);
      weight.setEnabled(true);
       task.setEnabled(true);
       grade.setEnabled(true);
       feedback.setEnabled(true);
       
    
      // appealCheck.setEnabled(false);
       //appealCheck.setSelected(false);
         
    }//GEN-LAST:event_createActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      
        String studentID = id.getText();
        String type = Integer.toString(task.getSelectedIndex()); 

        if (create.isSelected()){
            

            ClientResponse clientResponse = student.createTask(this.convertFormToXMLString());
           // System.out.print(clientResponse.getEntity(String.class));
            this.populateForm(clientResponse,"norm");
        } else if (read.isSelected()) {
           

            ClientResponse clientResponse = student.retrieveTask(ClientResponse.class, type, studentID);

            this.populateForm(clientResponse,"norm");
        } else if (update.isSelected()) {
            

            ClientResponse clientResponse = student.updateTask(this.convertFormToXMLString(), type , studentID);

            this.populateForm(clientResponse,"norm");
        } else if (delete.isSelected()) {
            
            ClientResponse clientResponse = student.deleteTask(type, studentID);
            this.populateForm(clientResponse,"del");
           // this.populateForm(clientResponse);
        }
        else if (appeal.isSelected()) {
            
            ClientResponse clientResponse = student.appealTask(this.convertFormToXMLString(),type,studentID);
           
           this.populateForm(clientResponse,"norm");
        }
        buttonGroup1.clearSelection();
        
     //  appeal.setEnabled(false);
      //id.setEnabled(true);
       //studName.setEnabled(true);
       //task.setEnabled(true);
       //grade.setEnabled(true);
       //feedback.setEnabled(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void readActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_readActionPerformed
      
     clearTextField();
       id.setEnabled(true);
      weight.setEnabled(false);
       task.setEnabled(true);
       grade.setEnabled(false);
       feedback.setEnabled(false);
    //   appealCheck.setEnabled(false);
     //  appealCheck.setSelected(false);
    }//GEN-LAST:event_readActionPerformed

    private void feedbackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_feedbackActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_feedbackActionPerformed

    private void statusInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statusInfoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_statusInfoActionPerformed

    private void studActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studActionPerformed
        // TODO add your handling code here:
       clearTextField();
         create.setEnabled(false);
         read.setEnabled(true);
         delete.setEnabled(false);
         update.setEnabled(false);
        // read.setSelected(true);
         appeal.setEnabled(true);
          buttonGroup1.clearSelection();
        
    }//GEN-LAST:event_studActionPerformed

    private void intructorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_intructorActionPerformed
        // TODO add your handling code here:
        clearTextField();
       
        appeal.setEnabled(false);
        create.setEnabled(true);
        read.setEnabled(true);
        delete.setEnabled(true);
        update.setEnabled(true);
        buttonGroup1.clearSelection();
     //   create.setSelected(true);
        
    }//GEN-LAST:event_intructorActionPerformed

    private void appealActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_appealActionPerformed
        // TODO add your handling code here:
        
       clearTextField();
       id.setEnabled(true);
      weight.setEnabled(false);
       task.setEnabled(true);
       grade.setEnabled(false);
       feedback.setEnabled(false);
    //   appealCheck.setEnabled(true);
      
    }//GEN-LAST:event_appealActionPerformed

    private void appealCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_appealCheckActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_appealCheckActionPerformed

    private void taskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_taskActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_taskActionPerformed

    private void weightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_weightActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_weightActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GradebookFormUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GradebookFormUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GradebookFormUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GradebookFormUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GradebookFormUI().setVisible(true);
            }
        });
    }
public void clearTextField(){

       id.setText("");
      weight.setText("");
       //task.setText("");
       task.setSelectedIndex(0);
       grade.setText("");
       feedback.setText("");
        statusInfo.setText("");
       statusCode.setText("");
       location.setText("");
       mediaType.setText("");
       message.setText("");
       appealCheck.setSelected(false);
      
}
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton appeal;
    private javax.swing.JCheckBox appealCheck;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JRadioButton create;
    private javax.swing.JRadioButton delete;
    private javax.swing.JTextField feedback;
    private javax.swing.JTextField grade;
    private javax.swing.JTextField id;
    private javax.swing.JCheckBox intructor;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jj;
    private javax.swing.JTextField location;
    private javax.swing.JTextField mediaType;
    private javax.swing.JTextArea message;
    private java.awt.PopupMenu popupMenu1;
    private javax.swing.JRadioButton read;
    private javax.swing.JTextField statusCode;
    private javax.swing.JTextField statusInfo;
    private javax.swing.JCheckBox stud;
    private javax.swing.JComboBox<String> task;
    private javax.swing.JRadioButton update;
    private javax.swing.JTextField weight;
    // End of variables declaration//GEN-END:variables

    private void populateDeleteForm(ClientResponse clientResponse) {
       //  studName.setEnabled(false);
        String entity = clientResponse.getEntity(String.class);
        
        
        task.setEnabled(false);
            grade.setEnabled(false);
            feedback.setEnabled(false);
         //   studName.setText("");
           //     task.setText("");
           task.setSelectedIndex(0);
                grade.setText("");
                feedback.setText("");
            
            
            // Populate HTTP Header Information
            statusCode.setText(Integer.toString(clientResponse.getStatus()));
           message.setText(entity);
            //mediaType.setText(clientResponse.getType().toString());
            statusInfo.setText(clientResponse.getStatusInfo().toString());
            location.setText("");
            mediaType.setText("");
           // if(!clientResponse.getEntity(String.class).isEmpty())
           // message.setText(clientResponse.getEntity(String.class));
            // The Location filed is only populated when a Resource is created
            
            
        //To change body of generated methods, choose Tools | Templates.
    }
   
    

}
