package api.test;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints2;
import api.payload.User;

import io.restassured.response.Response;

public class UserTests2 {
	
	Faker faker;
	
	User userPayload;
	
	public Logger logger;
	
	@BeforeClass
	public void setup() {
		
		faker = new Faker();
		userPayload = new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		//logs
		
		logger = LogManager.getLogger(this.getClass());
		logger.debug("debugging ..........");
		
		
	}
	
	@Test(priority=1)
	public void testPostUser(){
		
		logger.info("******Creating user***********");
		Response response = UserEndPoints2.createUser(userPayload);
		
		response.then().log().all();
		
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		
		logger.info("************ User is created******************");
		
	}
	
	
	@Test(priority=2)
	public void testGetUserByName() {
		Response response = UserEndPoints2.readUser(this.userPayload.getUsername());
		
		response.then().log().all();
		
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		 
		
	}
	
	@Test(priority=3)
	public void testUpdateUserByName() {
		
		//update data using payload
		
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
         Response response = UserEndPoints2.updateUser(this.userPayload.getUsername(), userPayload);
		
		response.then().log().all();
		response.then().log().body();
		
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		
		//Checking the data after update
        Response responseAfterUpdate = UserEndPoints2.readUser(this.userPayload.getUsername());
		
    //    responseAfterUpdate.then().log().all();
		
		AssertJUnit.assertEquals(responseAfterUpdate.getStatusCode(), 200);				
	}
	
	@Test(priority=4)
	public void testDeleteUserByName() {
		
		Response response = UserEndPoints2.deleteUser(this.userPayload.getUsername());
		response.then().log().all();
		
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		
	}

}
