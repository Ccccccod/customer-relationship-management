package capstone.controller;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.springframework.boot.test.context.SpringBootTest;

import capstone.BackEndCapstoneApplication;

@RunWith(Suite.class)
@SuiteClasses({ CareerControllerTest.class, ClassificationControllerTest.class, ContactControllerTest.class,
		CustomerControllerTest.class, FieldControllerTest.class, InvoiceControllerTest.class,
		OpportunityControllerTest.class, OrderControllerTest.class, ProductControllerTest.class,
		ProductTypeControllerTest.class, RoleControllerTest.class, SourceControllerTest.class, TypeControllerTest.class,
		UserControllerTest.class })
@SpringBootTest(classes = BackEndCapstoneApplication.class)
public class ControllerTest {
	void test1() {

	}

	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(ControllerTest.class);

		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
			failure.getException().printStackTrace();
		}

		System.out.println("Test successful? " + result.wasSuccessful());
	}
}
