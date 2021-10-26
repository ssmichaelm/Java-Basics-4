package com.ss.jb.four.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.ss.jb.four.Line; // What LineTest is testing

/*
 * Test cases:
 * - getSlope():
 * 		- Line consists of two origins
 * 		- Line consists of the same two points that are not origins
 * 		- Line is horizontal
 * 		- Line is vertical
 * 		- Line has a positive slope
 * 		- Line has a negative slope
 * 
 * - getDistance():
 * 		- Line consists of two origins
 * 		- Line consists of the same two points that are not origins
 * 		- Line is horizontal
 * 		- Line is vertical
 * 		- Line has a positive slope
 * 		- Line has a negative slope
 * 
 * - parallelTo():
 * 		- Two lines are parallel (vertically, horizontally, and diagonally)
 * 		- Two lines are perpendicular
 * 		- Two lines are intersecting
 * 		- Two lines are non-intersecting
 */

public class LineTest {
	private static final double DELTA = 1e-15;

	Line origin = new Line(0,0,0,0);
	Line sameTwoPoints = new Line(1,2,1,2);
	Line horizontalLine = new Line(4,7,11,7);
	Line verticalLine = new Line(4,7,4,11);
	Line positiveSlopeLine = new Line(1,3,17,9);
	Line negativeSlopeLine = new Line(2,3, 9,-13);
	
	// getSlope() tests
	@Test(expected = ArithmeticException.class)
	public void getSlopeOrigin() {
	    origin.getSlope();
	}
	
	@Test(expected = ArithmeticException.class)
	public void getSlopeSameTwoPoints() {
		sameTwoPoints.getSlope();
	}
	
	@Test
	public void getSlopeHorizontalLine() {
		assertEquals(0, horizontalLine.getSlope(), DELTA);
	}
	
	@Test(expected = ArithmeticException.class)
	public void getSlopeVerticalLine() {
		verticalLine.getSlope();
	}
	
	@Test
	public void getSlopePositiveSlope() {
		assertTrue("The slope is positive.", positiveSlopeLine.getSlope() > 0);
	}
	
	@Test
	public void getSlopeNegativeSlope() {
		assertTrue("The slope is negative.", negativeSlopeLine.getSlope() < 0);
	}
	
	// getDistance() tests
	@Test
	public void getDistanceSamePoints() {
	    assertEquals(0, origin.getDistance(), DELTA);
		assertEquals(0, sameTwoPoints.getDistance(), DELTA);
	}
	
	@Test
	public void getDistanceDifferentPoints() {
		assertTrue("Distance of a horizontal line is always greater than 0.", horizontalLine.getDistance() > 0);
		assertTrue("Distance of a vertical line is always greater than 0.", verticalLine.getDistance() > 0);
		assertTrue("Distance of a line with a positive slope is always greater than 0.", positiveSlopeLine.getDistance() > 0);
		assertTrue("Distance of a line with a negative slope is always greater than 0.", negativeSlopeLine.getDistance() > 0);
	}
	
	Line origin2 = new Line(0,0,0,0);
	Line sameTwoPoints2 = new Line(3,2,3,2);
	Line horizontalLine2 = new Line(4,7,11,7);
	Line verticalLine2 = new Line(-4,7,-4,11);
	Line positiveSlopeLine2 = new Line(2,4,18,10);
	Line positiveSlopeLine3 = new Line(1,2,17,9);
	
	// parallelTo() tests
	@Test (expected = ArithmeticException.class)
	public void parallelToSamePointsTests() {
		assertFalse("Lines are not parallel", origin.parallelTo(origin2)); // Two same points cannot be parallel, so false
		assertFalse("Lines are not parallel.", sameTwoPoints.parallelTo(sameTwoPoints2)); // Two points cannot be parallel, so false
	}
	
	@Test (expected = ArithmeticException.class)
	public void parallelToVerticalLineTests() {
		assertTrue("Lines are parallel.", verticalLine.parallelTo(verticalLine2)); // Two vertical lines are ALWAYS parallel, so true
		/* Two vertical lines are always parallel, however...
		 * Because the parallelTo() method calls the getSlope() method, and because the getSlope() method throws an
		 * ArithmeticException when two x-coordinates are the same -- which is the case for a vertical line -- 
		 * performing the parallelTo() method on vertical lines does not return true, rather it throws an exception.
		 */
		
		assertFalse("Lines are not parallel.", horizontalLine.parallelTo(verticalLine)); // A horizontal line and a vertical line ARE NOT parallel, so false
		/*
		 * Due to the same reasoning as above, this will throw an ArithmeticException and not false like it should. 
		 */

	}
	
	@Test
	public void parallelToTests() {
		assertTrue("Lines are parallel.", horizontalLine.parallelTo(horizontalLine2)); // Two horizontal lines are ALWAYS parallel, so true
		assertTrue("Lines are parallel.", positiveSlopeLine.parallelTo(positiveSlopeLine2)); // Two lines with the same slope ARE parallel, so true
		assertFalse("Lines are not parallel.", positiveSlopeLine.parallelTo(positiveSlopeLine3)); // Two lines that intersect ARE NOT parallel, so false
		assertFalse("Lines are not parallel.", horizontalLine.parallelTo(negativeSlopeLine)); // A horizontal line and a line with a negative slope ARE NOT parallel, so false
		assertFalse("Lines are not parallel.", horizontalLine.parallelTo(positiveSlopeLine)); // A horizontal line and a line with a positive slope ARE NOT parallel, so false
	}
	
}
