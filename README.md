# QA Cloud Native Feb2021
## Shopping List: A fundemental Project 
The task set out to us for this project was to create a CRUD web application:  
* **C**reate
* **R**ead
* **U**pdate
* **D**elete  

As the brief for this project was very broad and gives us intellectual freedom given that we create a CRUD based system. I decided not to be flamboyant and simply
create an app that creates a shopping list and calculates the price of the items you need for your list.

## Design
This project is being approached using an agile framework, making use of atlassian to create a Scrum board. I created several stories based on what I want the user to experince when using the app.
I also knew that the project would be 1 table as with spring boot doing lots for you I wasnt able to have as much control over the project as I would of liked. With my limited Springboot knowledge I knew
a single table project would be best. I then created tasks to break up each of the stories into managable bites.

# Scrum Board
ScreenShot:
![Screen Shot](https://github.com/Thom-Glynn/FundementalProject/blob/main/Sprint.PNG)

## Thought Approach

When creating this project I thought to myself fill the brief to the best of my ability.
I decided on one table, store everything in users shopping basket, allow them to remove items, and allow them to change the quantity of items on the list.  
And so the shopping list was born:  
![Main Page](https://github.com/Thom-Glynn/FundementalProject/blob/main/Shopping%20List.PNG)
In order to make the shopping list feel functional I added a feature where you can check off what you picked up and it highlights the item green.
I wanted to take on personal experience of going shopping with a list but no pen and end up missing things because I was unable to tick off what I already had.
An example of that is here:  
![Check List](https://github.com/Thom-Glynn/FundementalProject/blob/main/checklist.PNG)

## Technologies used
Here is a small summary of the technologies used in the creation of this project.

# Git Hub
In this project we used Git hub to keep track of versions and updates aswell as making the project accessable for teachers.

# GCP SQL instance
Using a GCP SQL instance I created a Database and using SQL commands I created a table and populated the coloumns to allow communication with my back end to be seamless. I had to make sure my local IP 
white listed on the google Cloud platform in order to allow my back-end to communicate with the project.

# Database
The database is created with the GCP SQL instance and used for storage with the back-end portion of the application. I only created the one database as I only needed the one and only needed 

# Back-end
The back-end is used to process inputs from the Front-end and translate it from json to SQL commands to be given to the database and affect it. The tasks I had given to my Back-end were as follows:
Create (add to table), Read (Read all from table), Update/id (update a specific item by its id), delete/id (delete a specific item by its id). The back end was built the use of Maven and Sringboot building on base of
Java.

#Front-end 
The Front end utilised HTML (**H**yper**T**ext **M**arkup **L**anguage), I incorporated bootstrap libraries in order to help with presentation of the project. I also had to research on making a check box
that updated on being checked and unchecked in order to create the checking system for the shopping list. all the communication with the back end was made possible with javascript integration.
I created two HTML pages with two nav bars allowing you to travel between both seemlessly. I decided that when clicking the checklist from the home page it will open a seperate tab,
this is so when looking at the checklist and deciding that you missed something you can change tabs with ease. 

## Testing

Testing the front end was done with the help of peer programming to a sense asking for help when the silent error were blocking functions.

The back end was tested with Unit Testing and integration testing:
![junit](https://github.com/Thom-Glynn/FundementalProject/blob/main/coverage.PNG)

I was able to get 74.1% coverage but really wanted to get up to atleast 90, I was unable to and decided that getting a working version of the project with all the deliverables was more important
than expanding my knowledge of Springboot.
