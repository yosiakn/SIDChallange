package summit;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class MainTest {

	public static void main(String[] args) {
		Result result;

		result = JUnitCore.runClasses(CommunityTest.class);
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
		if (result.wasSuccessful()) {
			System.out.println("Community OK");
		}

		result = JUnitCore.runClasses(ServiceTest.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("Services OK");
        }
        
        result = JUnitCore.runClasses(WaterPointTest.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("Water Point OK");
        }
		
	}

}
