package devops.hw1.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import back_end.Backend;
import front_end.MTGComponent;

public class MTGComponentTest {

	@Test
	public void testConstructorWindowBounds() {

		try {
			new MTGComponent(-10, 10, new Backend());
			fail("Expected IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "MTGComponent: -10 is not a valid window width");
		}

		try {
			new MTGComponent(10, -10, new Backend());
			fail("Expected IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "MTGComponent: -10 is not a valid window height");
		}

	}

	@Test
	public void testSetWindowX() {
		MTGComponent comp = new MTGComponent(10, 10, new Backend());

		try {
			comp.setWindowX(-1);
			fail("Expected IllegalArgumentException");
		} catch(IllegalArgumentException e) {
			assertEquals(e.getMessage(), "MTGComponent: -1 is not a valid window width");
		}
	}

	@Test
	public void testSetWindowY() {
		MTGComponent comp = new MTGComponent(10, 10, new Backend());

		try {
			comp.setWindowY(-1);
			fail("Expected IllegalArgumentException");
		} catch(IllegalArgumentException e) {
			assertEquals(e.getMessage(), "MTGComponent: -1 is not a valid window height");
		}
	}

}
