# 08-Java-Advanced-Features-Coding

Now that you've finished the Design Patterns and Good Practices module, the time has come to 
start practicing a lot more the knowledge and topics that you've gone through.
In this module the main focus will be just working on more and more complex applications
that will allow you to repeat a lot more, most of the concepts that you've learnt in Java Advanced Features.

By the end of the module you'll be much more familiar with a lot of the concepts.

## Exercises
1. SDA Scheduler application
2. Vending Machine application
3. Vehicles IO application
4. Additional exercises can be found [here](https://gitlab.com/sda-international/program/java/08.1-java-advanced-features-coding/wikis/uploads/5a286657b466e6a141ba3e6695754ef5/08_Coding_Java_Advanced_Features.pdf)


Good luck!


## SDA Scheduler application requirements

**Requirements**

Create a class hierarchy

    Person.java - firstname
                - lastname
                - dateOfBirth
    Trainer.java (extends Person) - isAuthorized (boolean)
    Student.java (extends Person) - hasPreviousJavaKnowledge (boolean)

Create a Group class which has

    name (Java2Gdansk, Tester3Bucharest, etc)
    one trainer
    a list of students
    
* Manually initialize 15 students; 4 groups and 3 trainers;
* Store all students in a list; all groups in a list; all trainers in a list;
* Asign a trainer to each group
* Asign 2-3 students to each group
* Ensure the fact that a group will only have distinct students (How would you do that?)
* Ensure the fact that a group will only have a maximum of 5 students; When you try to add a 6th one throw an MaximumNumberOfStudentsReached exception
* Display all students in a group sorted alphabetically by lastName
* Display the group with the maximum number of students
* Display all students younger than 25, from all groups
* Display all students grouped by trainer that teaches to them (eg. Trainer1 - stud1, stud3, stud4; Trainer2 - stud2, stud 10) - regardless of the group they're part of (If you were to store this information in a data structure what would you use?)
* Display all students with previous java knowledge
* Display the group with the highest number of students with no previous java knowledge
* Remove all the students younger than 20 from all groups

## Vending Machine application requirements

**Requirements**

Implement a traditional vending machine which:
* Allow user to select products - the menu is displayed in console.
* Allow user to select what coins to insert - menu is displayed in console.
* Allow user to take refund by canceling the request.
* Return selected product and remaining change if any.
* The state of the vending machine is saved in a file on the disk - a json file.
* Vending Machine has the product menu configurable - new products can be added in the json file.
* Vending Machine is configurable on what coins it accepts - new coins can be added in the json file.

For guidance you can check the code from this repository to see how these requirements were implemented.

## Vehicle IO application requirements

**Requirements**

Download the following [text file](https://gitlab.com/sda-international/program/java/java-advanced-features-coding/-/wikis/uploads/b1c570a66dd95b678372bfceb702cda6/vehicles.txt). 

Build an OOP hierarchy while considering the following types of objects:

    Cars
        Brand
        Model
        Price
        TopSpeed
        Transmission(Manual/Automatic)
        Shape(Coupe/Sedan/Wagon)

    Motorcycles 
        Brand
        Model
        Price
        TopSpeed
        Shape(Chopper/Cruiser/Enduro)
        
    Tractors
        Brand
        Model
        Price
        MaxPulledWeight
        

* Read vehicles.txt and create objects of the proper type
* Count the number of cars, motorcycles, tractors
* Count how many vehicles of each brand are there
* Sort the cars by price
* Sort the choppers by top speed
* Display each category of vehicles in separate files 


## Project setup
Follow these steps in order to run the project from this repository on you local machine.

1. Java 8 or above is required.
2. Make sure you have maven installed.
3. Clone the repository on your local machine.
4. Copy the [json file](https://gitlab.com/sda-international/program/java/java-advanced-features-coding/blob/master/src/main/resources/sampleFiles/vendingMachine.json)
 in *D* partition. If you don't have that partition then make sure you change
the *FILE_PATH* from *DbService.java* accordingly.
5. Build the project *mvn clean install*.
6. Import the project in Intellij.
7. Run the application from *Main.java*.
