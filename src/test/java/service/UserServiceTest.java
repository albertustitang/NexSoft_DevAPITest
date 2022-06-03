package service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTimeout;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import model.User;

public class UserServiceTest {

	UserService userService;
	String firstname;
	String lastname;
	String email;
	String password;
	String repeatPassword;

	@BeforeEach
	public void init() {
		userService = new UserServiceImp();// polymorph
		firstname = "Albertus";
		lastname = "Titan";
		email = "titan@email.com";
		password = "123456";
		repeatPassword = "123456";
	}

	// green test/ positive test
	@DisplayName("User object is created")
	@Test
	@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
	public void test1_CreateUser_WhenDetailsProvide_ReturnUserObject() {
		// arrange

		// act
		User user = userService.createUser(firstname, lastname, email, password, repeatPassword);

		// assert
		assertNotNull(user, "if createUser success shouldn't return null");
		assertEquals(firstname, user.getFirstName(), "User first name is incorrect");
		assertEquals(lastname, user.getLastName(), "User last name is incorrect");
		assertEquals(email, user.getEmail(), "User email is incorrect");
		assertNotNull(user.getId(), "User id cannot null");

		assertTimeout(Duration.ofMillis(500), () -> {
			try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}

	// red test
	@DisplayName("Empty First Name cause exception ")
	@Test
	public void test2_CreateUser_WhenFirstNameIsEmpty_ThrowsIllegalException() {

		// arrange
		UserService userService = new UserServiceImp();// polymorph
		firstname = "";

		String expectedExceptionMessage = "User first name is empty";

		// act
		IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
			userService.createUser(firstname, lastname, email, password, repeatPassword);
		}, "Empty First Name cause Illegal Argument Exception");

		// assert
		assertEquals(expectedExceptionMessage, thrown.getMessage());

	}

	// red test
	@DisplayName("Empty Last Name cause exception ")
	@Test
	public void test_3CreateUser_WhenLastNameIsEmpty_ThrowsIllegalException() {

		// arrange
		UserService userService = new UserServiceImp();// polymorph
		lastname = "";

		String expectedExceptionMessage = "User last name is empty";

		// act
		IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
			userService.createUser(firstname, lastname, email, password, repeatPassword);
		}, "Empty last Name cause Illegal Argument Exception");

		// assert
		assertEquals(expectedExceptionMessage, thrown.getMessage());

	}

}
