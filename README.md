# filmPortal
Film rental administration portal

This project is a Java Spring-Boot web MySQL app to play around with some features of this technology. 
To run it, import the project to your IDE, if you have MySQL create a schema called: "videodata" in your root instance. If your instance is different from "root", please go to "application.properties" file, and change the root. Please change "username" and "password" files at "application.properties".

What to do:

-make a new (basic, empty) schema at MySQL (if you have different, you have to change SQL accent at "application.properties")
                         (prefereble root instance "videodata" schema)
-go to application.properties
(-change DB root, if you have different name schema and instance)
-change password and username for your instance

How to use the app:

Run the app, and visit URL: "localhost:8081/adminportal". Sing in with the following: username="robi.raj" password="p". At the menu upside choose Film / Create a new film. Fill in the fields, upload a pic. Now go Customer / Create new customer, fill the necessary fields. Now go back to Film / View film list, and at the extreme right (you can delete film, update it, and rent it out to a Customer) "rent out" the film. Choose a user, and length of rental. This way the user earns bonus points (according to the status of the film), the film decreases the "in stock number" property, increases the "rented out" propery, and it will be a new rental at the rental list. At Rental / View rental list, you can finish, or delete the rental.

What is behind the scenes:

-I took more attention to the server side, less to the front-end
-I didn't write testing to the app
-there is no data support, you can not override rentals
-bonus points doesn't count in terms of the film's price
-There is User management, and data security (Spring Security), but only for the administrator. I encrypt password, assing role, like it's written in the big book. BUT: there is no option to create new users like this via the UI, if you want to create a new one, go to the "AdminPortalApplication.java", you will see how I created the admin user, you can create more users this way. 
(If you are interested how I do it via UI, please check this application: https://github.com/giorgiDologov/edu_e-commerce )
