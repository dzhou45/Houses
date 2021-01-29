# Building and Running

To build the solution, first clone this repository by navigating to the directory in which you want this project to be located.

Enter command:

git clone https://github.com/dzhou45/Houses.git

Your project will be built automatically.

To run the solution, right click VgsiExerciseApplication and click run.
VgsiExerciseApplication is located under /src/main/java/com.example.VGSI.Exercise

To run tests, run HouseControllerTest under /src/test/java/com.example.VGSI.Exercise.controllers

# API Design Choices

When a client uses a get request for a specific house id, and that house id does not exist, for example api/houses/11, I have chosen to send back an empty response. 
This is because there is nothing wrong with the get request, there is just no data for that specific house. Outputting an error may potentially be confusing to the user when their request is in a correct format.
