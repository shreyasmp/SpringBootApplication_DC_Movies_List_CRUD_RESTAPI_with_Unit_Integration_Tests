# SpringBootApplication_DC_Movies_List_CRUD_RESTAPI_with_Unit_Integration_Tests
  
This is a spring boot application that does the REST operations on creating, updating, retrieving and deleting the 
movie's list.

In order to test this sprint boot application, use Postman latest edition available

Once you have Postman up and setup, 

### For GET Request:
Setup a Request with GET and enter this url to start up: 

[http://localhost:8080/movies/](http://localhost:8080/movies/)

 and hit Send button
 
 you should see UNAuthorized error, 
 
 So in Postman select Basic Authentication under Auth below the URL,
 
 and enter user name as <b> bwayne </b> and password as <b> 123456 </b>
 
 You should be able to see the list of DC Movies shown in postman
 
 #### HTTPStatus Code for GET Success: 200 OK 
 
 ### For POST Request:
 
 With same url: 
 
 [http://localhost:8080/movies/](http://localhost:8080/movies/)
 
 Now click on, Body under the URL in Postman and enter this Json Object
 
    {
        "movieName": "Batman: Beyond the future",
        "movieRanking": 12
    }
    
and then Hit Send, you should see a success message that object is created successfully

#### HTTPStatus Code for POST Success: 201 Created

### For PUT Request: 

The URL changes for PUT since you know which object to update or change: 

[http://localhost:8080/movies/movieRanking/12](http://localhost:8080/movies/movieRanking/12)

Then in Body of Postman Request, change the name of movie or movie ranking to your will

    {
        "movieName": "Batman: Beyond the future",
        "movieRanking": 5
    }

Here in this case we change only the ranking of movie and click on SEND button, you should 
see a success message

#### HTTPStatus Code for PUT Success: 200 OK

### For DELETE Request: 

URL for delete remains similar to Put request but with the movieRanking of desired movie you may 
want to delete


[http://localhost:8080/movies/movieRanking/8](http://localhost:8080/movies/movieRanking/8)

Just hit the SEND button and no need to add anything in Request body for delete

#### HTTPStatus Code for DELETE Success: 202 Accepted
