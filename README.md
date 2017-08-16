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
