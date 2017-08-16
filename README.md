# RichardsonMaturityModel

## Level0/POX-FoodMenu

### Build and Run Instructions – 
1. git clone https://github.com/palakanmol31/POX-FoodMenu
2. In the unzipped directory, you would now see two directories - POX-FoodMenu-panmol (this is the server) and POX-FoodMenu-panmol-cli (this is the client).
3. Load the Server (POX-FoodMenu-panmol) and Client (POX-FoodMenu-panmol-cli) on netbeans.
4. Clean and build the server files (POX-FoodMenu-panmol). Once build is successful, run the project. 
5. A URL will open on the browser. 
6. Similarly, build the client files (POX-FoodMenu-panmol-cli). Once done, run it. (Note that this has some demo files in resources with each type of case. You can add your requests in any of these files and check the output. Here naming of file is just for showing formatted output on console. You can add in any file and correspondingly see output. )
7. After you run the client, output would be displayed in the console in the format of 
   Title of what the request is doing -
   Sending Request – 
    (XML of the request)

   Received Response - 
     (XML of the response)

       For example to add a FoodItem the output in the console would be like – 

       Add an item - 
       Sending request - 
              <NewFoodItems xmlns="http://cse564.asu.edu/PoxAssignment">
              <FoodItem country="GB">
              <name>Cornish Pasty</name>
              <description>Tender cubes of steak, potatoes and swede wrapped in flakey short crust pastry.  Seasoned with lots of pepper.  Served with mashed potatoes, peas and a side of gravy</description>
              <category>Dinner</category>
              <price>15.95</price>
              </FoodItem>
              </NewFoodItems>


    Response - 
       <FoodItemExists xmlns="http://cse564.asu.edu/PoxAssignment">
               <FoodItemId>123</FoodItemId>
       </FoodItemExists>


8. Following is what each file contains : 
a. Add.xml – This contains the new item that has to be added (for this item country name, category and name all are different.  ) . An id will be generated and this item will be added. 
b. FoodExists.xml – Add an item that already exists in xml. 
c. InvalidTagAdd.xml – If we spell the tag wrong. Eg: FoodItem is writted as FD.
d. MissingTagAdd.xml – This has some missing attributes in xml . 
e. Search.xml : Search for an item in FoodItemData.xml
f. SearchExists.xml – Search for an item that does not exists in the file. 
g. InvalidXML.xml – Parse Error. Some tags not closed. 

9. All the cases mentioned in the instructions file have been handled. 

## CRUD-gradebook

### How to run the code
1. git clone https://github.com/palakanmol31/CRUD-gradebook
2. Import the zip file on Netbeans. 
3. Clean and build and run the server .
4. Clean and build and run the client. 
5. Make sure student id is in string. Student id and task is required to do crud operations around the resource.


### Test Cases 

1.  Create – Select Instructor and tap create radio button. 
1) 201 , CREATED -  Add id and select a work task,then tap submit . 
2) 409 , CONFLICT – add a work task and student id that already exists and tap submit.
3) 400, BAD REQUEST – Do not add any work task or student name. 
4) 500, INTERNAL SERVER ERROR – Any runtime exception. 

2. Read – Select instructor or student and tap read radio button.
1) 410, GONE –  without creating or after deleting all ids, try reading an id. 
2) 200 , OK –  add a valid id and work task and tap submit
3) 404, NOT FOUND – add any random id that does not exists 
     
3.  Update – Select instructor and tap update button.
1) 200 , Ok – add a valid id and work task that exists, update the details and then tap submit .
2) 409 , CONFLICT –add an id or work task that does not exists
3) 400, BAD REQUEST – Do not add any work task or student name. 
4) 500, INTERNAL SERVER ERROR – Any runtime exception. 

4. Delete – Select instructor and tap delete radio button
1) 204 – NO Content – add id and work task that exists in the database
2) 404, NOT FOUND – add any random id that does not exists 
3) 410, GONE –  without creating or after deleting all ids, try reading an id. 

5. Appeal – Select student and tap appeal radio button
1)  200 , Ok – add a valid id that exists and also a task and tap submit. An appeal will be filed.
2) 409 , CONFLICT –add an id and work task that does not exists
3) 400, BAD REQUEST – Do not add any work task or student name. 
4) 500, INTERNAL SERVER ERROR – Any runtime exception. 



