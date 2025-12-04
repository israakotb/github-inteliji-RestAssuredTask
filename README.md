This code implements API testing for a user management system using the BDD style to test the test flow on A RestAPI

Test Flow:
  Retrieve existing users (GET)
  Create a new user and capture its ID (POST)
  Update the created user's information (PUT)
  Delete the user (DELETE)
  Verify deletion by checking the user no longer exists (GET)

	
Tools & Libraries Used
  RestAssured - Java library for API testing with BDD-style syntax
  TestNG - Testing framework for test execution and dependencies  
