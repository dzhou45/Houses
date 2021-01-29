# Building and Running

To build the solution, first navigate to the directory where you want the project and clone the repository

Enter command:

git clone https://github.com/dzhou45/Houses.git

Your project will be built automatically

To run the solution, right click VgsiExerciseApplication and click run.
VgsiExerciseApplication is located under /src/main/java/com.example.VGSI.Exercise

This will start the server, which is hosted on http://localhost:8080

To run tests, run HouseControllerTest under /src/test/java/com.example.VGSI.Exercise.controllers

API EndPoints:

GET  http://localhost:8080/api/houses           get all houses

GET  http://localhost:8080/api/houses/{houseId} get specific house

PUT  http://localhost:8080/api/houses/{houseId} update specific house

# Improvements

If persistent storage were required, you would want to connect this server to a database. This would improve performance as well when the data sets get large.

# API Design Choices

When a client uses a get request for a specific house id, and that house id does not exist, for example api/houses/11, I have chosen to send back an empty response. 
This is because there is nothing wrong with the get request, there is just no data for that specific house. Outputting a 404 error may potentially be confusing to the user.

# Security

Currently the data is stored in plaintext, but encryption could be used to make the data more secure. Currently, there are no users with different permissions, but in the future if there were, I would need to implement token authentication and make sure each user can only access the information they have authorization to access.
