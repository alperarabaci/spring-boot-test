package online.wordyourself;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class GreetingTest {

	Greeting greeting;
	
	@BeforeAll
	public static void beforeClass() {
		System.out.println("Before - I am only called Once!!!");
	}
	
	@BeforeEach
	void setUp() {
		System.out.println("--- --- ---");
		System.out.println("In Before Each...");
		greeting = new Greeting();
	}
	
	@Test
	@Order(1)
	void testHelloWorld() {		
		System.out.println(greeting.helloWorld());
	}

	@Test
	@Order(2)
	void testHelloWorldString1() {		
		System.out.println(greeting.helloWorld("Alper"));
	}
	
	@Test
	@Order(2)
	void testHelloWorldString2() {		
		System.out.println(greeting.helloWorld("Ugur"));
	}
	
	@AfterEach
	void tearDown() {
		System.out.println("In After Each...");			
	}
	
	@AfterAll
	public static void afterClass() {
		System.out.println("After - I am only called Once!!!");
	}
	
	
}
