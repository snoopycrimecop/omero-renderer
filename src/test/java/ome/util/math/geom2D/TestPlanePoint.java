/*
 *   Copyright 2006 University of Dundee. All rights reserved.
 *   Use is subject to license terms supplied in LICENSE.txt
 */

package ome.util.math.geom2D;

import org.testng.Assert;
import org.testng.annotations.Test;


/**
 * Unit test for {@link PlanePoint}.
 * 
 * @author Jean-Marie Burel &nbsp;&nbsp;&nbsp;&nbsp; <a
 *         href="mailto:j.burel@dundee.ac.uk">j.burel@dundee.ac.uk</a>
 * @author <br>
 *         Andrea Falconi &nbsp;&nbsp;&nbsp;&nbsp; <a
 *         href="mailto:a.falconi@dundee.ac.uk"> a.falconi@dundee.ac.uk</a>
 * @since OME2.2
 */
public class TestPlanePoint {

    private static final int MAX_ITER = 30000; // Max iterations in a test.

    @Test
    public void testPoint() {
        PlanePoint p = new PlanePoint(Integer.MIN_VALUE, Integer.MAX_VALUE);
        Assert.assertEquals(Integer.MIN_VALUE, p.x1, 0, "Should set x1 to argument passed to constructor.");
        Assert.assertEquals(Integer.MAX_VALUE, p.x2, 0, "Should set x2 to argument passed to constructor.");
    }

    @Test
    public void testDistanceNull() {
        PlanePoint p = new PlanePoint(0, 0);
        try {
            p.distance(null);
            Assert.fail("Shouldn't accept null.");
        } catch (NullPointerException npe) {
            // Ok, expected.
        }
    }

    @Test
    public void testDistanceSqrt2() {
        double sqrt2 = Math.sqrt(2);
        PlanePoint p, q;
        for (int i = -MAX_ITER / 2; i < MAX_ITER / 2; ++i) {
            p = new PlanePoint(0 + i, 1 + i);
            q = new PlanePoint(1 + i, 0 + i);
            Assert.assertEquals(sqrt2, p.distance(q), 0, "Wrong distance [i = " + i + "].");
        }
    }

    @Test
    public void testDisance1() {
        PlanePoint p, q;
        for (int i = -MAX_ITER / 2; i < MAX_ITER / 2; ++i) {
            p = new PlanePoint(0 + i, 0 + i);
            q = new PlanePoint(0 + i, 1 + i);
            Assert.assertEquals(1, p.distance(q), 0, "Wrong distance [i = " + i + "].");
        }
    }

    @Test
    public void testDisance2() {
        PlanePoint p, q;
        for (int i = -MAX_ITER / 2; i < MAX_ITER / 2; ++i) {
            p = new PlanePoint(0 + i, 0 + i);
            q = new PlanePoint(2 + i, 0 + i);
            Assert.assertEquals(2, p.distance(q), 0, "Wrong distance [i = " + i + "].");
        }
    }

    @Test
    public void testSumNull() {
        PlanePoint p = new PlanePoint(0, 0);
        try {
            p.sum(null);
            Assert.fail("Shouldn't accept null.");
        } catch (NullPointerException npe) {
            // Ok, expected.
        }
    }

    @Test
    public void testSum() {
        PlanePoint p, q;
        for (int i = -MAX_ITER / 2; i < MAX_ITER / 2; ++i) {
            p = new PlanePoint(1234.5678 + i, -8765.4321 + i);
            q = new PlanePoint(2 + i, 0 + i);
            Assert.assertEquals(new PlanePoint(1234.5678
                    + i + 2 + i, -8765.4321 + i + i), p.sum(q), "Wrong sum [i = " + i + "].");
        }
    }

    @Test
    public void testDiffNull() {
        PlanePoint p = new PlanePoint(0, 0);
        try {
            p.diff(null);
            Assert.fail("Shouldn't accept null.");
        } catch (NullPointerException npe) {
            // Ok, expected.
        }
    }

    @Test
    public void testDiff() {
        PlanePoint p, q, diff = new PlanePoint(1234 - 2, -8765);
        for (int i = -MAX_ITER / 2; i < MAX_ITER / 2; ++i) {
            p = new PlanePoint(1234 + i, -8765 + i);
            q = new PlanePoint(2 + i, 0 + i);
            Assert.assertEquals(diff, p.diff(q), "Wrong sum [i = " + i + "].");
        }
    }

    @Test
    public void testScalarInteger() {
        PlanePoint p;
        for (int i = -MAX_ITER / 2; i < MAX_ITER / 2; ++i) {
            p = new PlanePoint(i, -i);
            Assert.assertEquals(
                    new PlanePoint(7 * i, -7 * i), p.scalar(7), "Wrong scalar multiplication [i = " + i + "].");
        }
    }

    @Test
    public void testScalarDouble() {
        PlanePoint p;
        for (int i = -MAX_ITER / 2; i < MAX_ITER / 2; ++i) {
            p = new PlanePoint(i, -i);
            Assert.assertEquals(
                    new PlanePoint(Math.PI * i, -Math.PI * i), p
                            .scalar(Math.PI), "Wrong scalar multiplication [i = " + i + "].");
        }
    }

    @Test
    public void testVecNull() {
        PlanePoint p = new PlanePoint(0, 0);
        try {
            p.vec(null);
            Assert.fail("Shouldn't accept null.");
        } catch (NullPointerException npe) {
            // Ok, expected.
        }
    }

    @Test
    public void testVec() {
        PlanePoint p, q, diff = new PlanePoint(2 - 1234, 8765);
        for (int i = -MAX_ITER / 2; i < MAX_ITER / 2; ++i) {
            p = new PlanePoint(1234 + i, -8765 + i);
            q = new PlanePoint(2 + i, 0 + i);
            Assert.assertEquals(diff, p.vec(q), "Wrong vec [i = " + i + "].");
        }
    }

    @Test
    public void testDotNull() {
        PlanePoint p = new PlanePoint(0, 0);
        try {
            p.dot(null);
            Assert.fail("Shouldn't accept null.");
        } catch (NullPointerException npe) {
            // Ok, expected.
        }
    }

    @Test
    public void testDot() {
        PlanePoint p, q;
        for (int i = -MAX_ITER / 2; i < MAX_ITER / 2; ++i) {
            p = new PlanePoint(1 + i, -8 + i);
            q = new PlanePoint(2 + i, 0 + i);
            Assert.assertEquals((1 + i) * (2 + i)
                    + (-8 + i) * i, p.dot(q), 0, "Wrong dot [i = " + i + "].");
        }
    }

    @Test
    public void testNormSqrt2() {
        double sqrt2 = Math.sqrt(2);
        PlanePoint p, q;
        for (int i = -MAX_ITER / 2; i < MAX_ITER / 2; ++i) {
            p = new PlanePoint(0 + i, 1 + i);
            q = new PlanePoint(1 + i, 0 + i);
            Assert.assertEquals(sqrt2, p.vec(q).norm(), 0, "Wrong norm [i = " + i + "].");
        }
    }

    @Test
    public void testNorm1() {
        PlanePoint p, q;
        for (int i = -MAX_ITER / 2; i < MAX_ITER / 2; ++i) {
            p = new PlanePoint(0 + i, 0 + i);
            q = new PlanePoint(0 + i, 1 + i);
            Assert.assertEquals(1, p.vec(q).norm(), 0, "Wrong norm [i = " + i + "].");
        }
    }

    @Test
    public void testNorm2() {
        PlanePoint p, q;
        for (int i = -MAX_ITER / 2; i < MAX_ITER / 2; ++i) {
            p = new PlanePoint(0 + i, 0 + i);
            q = new PlanePoint(2 + i, 0 + i);
            Assert.assertEquals(2, p.vec(q).norm(), 0, "Wrong norm [i = " + i + "].");
        }
    }

    @Test
    public void testNormalize() {
        PlanePoint p, u;
        double n;
        for (int i = 1; i < MAX_ITER / 2; ++i) {
            p = new PlanePoint(0 + i, 0 + i);
            n = Math.sqrt(2 * i * i);
            u = new PlanePoint(i / n, i / n);
            Assert.assertEquals(u, p.normalize(), "Wrong unit vector [i = " + i + "].");
        }

        p = new PlanePoint(0, 0);
        Assert.assertEquals(p, p.normalize(), "Null vector can't be normalized.");

        u = new PlanePoint(0, -1);
        for (int i = -MAX_ITER / 2; i < 0; ++i) {
            p = new PlanePoint(0, 0 + i);
            Assert.assertEquals(u, p.normalize(), "Wrong unit vector [i = " + i + "].");
        }
    }

    @Test
    public void testAngleNull() {
        PlanePoint p = new PlanePoint(0, 0);
        try {
            p.angle(null);
            Assert.fail("Shouldn't accept null.");
        } catch (NullPointerException npe) {
            // Ok, expected.
        }
    }

    @Test
    public void testAngleNullVector() {
        PlanePoint p = new PlanePoint(0, 0);
        try {
            p.angle(p);
            Assert.fail("Angle is not defined for a null vector.");
        } catch (IllegalArgumentException iae) {
            // Ok, expected.
        }
        try {
            p.angle(new PlanePoint(1, 1));
            Assert.fail("Angle is not defined for a null vector.");
        } catch (IllegalArgumentException iae) {
            // Ok, expected.
        }
        try {
            new PlanePoint(1, 1).angle(p);
            Assert.fail("Angle is not defined for a null vector.");
        } catch (IllegalArgumentException iae) {
            // Ok, expected.
        }
    }

    @Test
    public void testAngle() {
        PlanePoint xAxis = new PlanePoint(1, 0), p;

        Assert.assertEquals(0, xAxis.angle(xAxis), 0, "Should be 0.");

        p = new PlanePoint(0, 1);
        Assert.assertEquals(Math.PI / 2, xAxis.angle(p), 0, "Should be PI/2.");

        p = new PlanePoint(-1, 0);
        Assert.assertEquals(Math.PI, xAxis.angle(p), 0, "Should be PI.");

        p = new PlanePoint(0, -1);
        Assert.assertEquals(Math.PI / 2, xAxis.angle(p), 0, "Should be PI/2.");
    }

    @Test
    public void testEquals() {
        PlanePoint p = new PlanePoint(0, 0);
        Assert.assertFalse(p.equals(null), "Should never be equal to null.");
        Assert.assertFalse(p.equals(new Object()), "Should never be equal to a different type.");
        Assert.assertFalse(p.equals(new PlanePoint(-1, 0)), "Should never be equal if different x1.");
        Assert.assertFalse(p.equals(new PlanePoint(0, 9)), "Should never be equal if different x2.");
        Assert.assertFalse(p.equals(new PlanePoint(-1, 1)), "Should never be equal if different x1 and x2.");
        Assert.assertTrue(p.equals(new PlanePoint(0, 0)), "Object identity should never matter.");
    }

    @Test
    public void testHashCodeDiffCalls() {
        PlanePoint p = new PlanePoint(500, -30000);
        int h = p.hashCode();
        for (int i = 0; i < MAX_ITER; ++i) {
            Assert.assertEquals(h, p.hashCode(), "Should return same value across different calls.");
        }
    }

    @Test
    public void testHashCodeObjectEquality() {
        PlanePoint p, q;
        for (int i = -MAX_ITER / 2; i < MAX_ITER / 2; ++i) {
            p = new PlanePoint(i, -i);
            q = new PlanePoint(i, -i);
            Assert.assertEquals(p.hashCode(), q.hashCode(), "Should return same value for equal objects [i = " + i
                    + "].");
        }
    }

}
