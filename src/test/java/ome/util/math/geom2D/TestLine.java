/*
 *   Copyright 2006 University of Dundee. All rights reserved.
 *   Use is subject to license terms supplied in LICENSE.txt
 */

package ome.util.math.geom2D;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Unit test for {@link Line}.
 * 
 * @author Jean-Marie Burel &nbsp;&nbsp;&nbsp;&nbsp; <a
 *         href="mailto:j.burel@dundee.ac.uk">j.burel@dundee.ac.uk</a>
 * @author <br>
 *         Andrea Falconi &nbsp;&nbsp;&nbsp;&nbsp; <a
 *         href="mailto:a.falconi@dundee.ac.uk"> a.falconi@dundee.ac.uk</a>
 * @since OME2.2
 */
public class TestLine {

    private static final int MAX_ITER = 30000; // Max iterations in a test.

    @Test
    public void testLineBadArgs() {
        try {
            new Line(null, null);
            Assert.fail("Shouldn't allow nulls.");
        } catch (NullPointerException npe) {
            // Ok, expected.
        }
        try {
            new Line(null, new PlanePoint(0, 0));
            Assert.fail("Shouldn't allow null origin.");
        } catch (NullPointerException npe) {
            // Ok, expected.
        }
        try {
            new Line(new PlanePoint(0, 0), null);
            Assert.fail("Shouldn't allow null head.");
        } catch (NullPointerException npe) {
            // Ok, expected.
        }
        try {
            new Line(new PlanePoint(1, 1), new PlanePoint(1, 1));
            Assert.fail("Shouldn't allow same points.");
        } catch (IllegalArgumentException iae) {
            // Ok, expected.
        }
    }

    @Test
    public void testLineBadArgs2() {
        try {
            new Line(null, null, null);
            Assert.fail("Shouldn't allow nulls.");
        } catch (NullPointerException npe) {
            // Ok, expected.
        }
        try {
            new Line(null, new PlanePoint(0, 0), new PlanePoint(0, 1));
            Assert.fail("Shouldn't allow null tail.");
        } catch (NullPointerException npe) {
            // Ok, expected.
        }
        try {
            new Line(new PlanePoint(0, 0), null, new PlanePoint(0, 1));
            Assert.fail("Shouldn't allow null head.");
        } catch (NullPointerException npe) {
            // Ok, expected.
        }
        try {
            new Line(new PlanePoint(0, 0), new PlanePoint(0, 1), null);
            Assert.fail("Shouldn't allow null origin.");
        } catch (NullPointerException npe) {
            // Ok, expected.
        }
        try {
            new Line(new PlanePoint(1, 1), new PlanePoint(1, 1),
                    new PlanePoint(0, 0));
            Assert.fail("Shouldn't allow same head and tail.");
        } catch (IllegalArgumentException iae) {
            // Ok, expected.
        }
    }

    @Test
    public void testLine() {
        PlanePoint o = new PlanePoint(0, 0), p = new PlanePoint(1, 1), u = new PlanePoint(
                1 / Math.sqrt(2), 1 / Math.sqrt(2));
        Line r = new Line(o, p);
        Assert.assertEquals(o, r.origin, "Shouldn't change the origin.");
        Assert.assertEquals(u, r.direction, "Calculated wrong unit vector.");
    }

    @Test
    public void testLine2() {
        PlanePoint o = new PlanePoint(0, 0), p = new PlanePoint(1, 1), u = new PlanePoint(
                1 / Math.sqrt(2), 1 / Math.sqrt(2));
        Line r = new Line(o, p, o);
        Assert.assertEquals(o, r.origin, "Shouldn't change the origin.");
        Assert.assertEquals(u, r.direction, "Calculated wrong unit vector.");
    }

    @Test
    public void testGetPointXAxis() {
        PlanePoint o = new PlanePoint(0, 0), p = new PlanePoint(1, 0);
        Line r = new Line(o, p);
        for (int i = -MAX_ITER / 2; i < MAX_ITER / 2; ++i) {
            p = new PlanePoint(i, 0);
            Assert.assertEquals(p, r.getPoint(i), "Wrong point [i = " + i + "].");
        }
    }

    @Test
    public void testGetPointYAxis() {
        PlanePoint o = new PlanePoint(0, 0), p = new PlanePoint(0, 1);
        Line r = new Line(o, p);
        for (int i = -MAX_ITER / 2; i < MAX_ITER / 2; ++i) {
            p = new PlanePoint(0, i);
            Assert.assertEquals(p, r.getPoint(i), "Wrong point [i = " + i + "].");
        }
    }

    @Test
    public void testGetPointParallelXAxis() {
        PlanePoint o = new PlanePoint(0, 1), p = new PlanePoint(-1, 1);
        Line r = new Line(o, p);
        for (int i = -MAX_ITER / 2; i < MAX_ITER / 2; ++i) {
            p = new PlanePoint(-i, 1); // Orientation is from right to left.
            Assert.assertEquals(p, r.getPoint(i), "Wrong point [i = " + i + "].");
        }
    }

    @Test
    public void testGetPointParallelYAxis() {
        PlanePoint o = new PlanePoint(1, 0), p = new PlanePoint(1, 1);
        Line r = new Line(o, p);
        for (int i = -MAX_ITER / 2; i < MAX_ITER / 2; ++i) {
            p = new PlanePoint(1, i);
            Assert.assertEquals(p, r.getPoint(i), "Wrong point [i = " + i + "].");
        }
    }

    @Test
    public void testLiesNull() {
        PlanePoint o = new PlanePoint(0, 1), p = new PlanePoint(1, 1);
        Line r = new Line(o, p);
        try {
            r.lies(null);
            Assert.fail("Souldn't accept null.");
        } catch (NullPointerException npe) {
            // Ok, expected.
        }
        try {
            r.lies(null, true);
            Assert.fail("Souldn't accept null.");
        } catch (NullPointerException npe) {
            // Ok, expected.
        }
        try {
            r.lies(null, false);
            Assert.fail("Souldn't accept null.");
        } catch (NullPointerException npe) {
            // Ok, expected.
        }
    }

    @Test
    public void testLies1() {
        PlanePoint o = new PlanePoint(0, 1), p = new PlanePoint(1, 1);
        Line r = new Line(o, p);
        int i;
        for (i = -MAX_ITER / 2; i < 0; ++i) {
            p = new PlanePoint(i, 1);
            Assert.assertTrue(r.lies(p), "Actually lies on r [i = " + i + "].");
            Assert.assertTrue(
                    r.lies(p, false), "Actually lies on negative half of r [i = " + i + "].");

            p = new PlanePoint(i, 0);
            Assert.assertFalse(r.lies(p), "Doesn't lie on r [i = " + i + "].");
            Assert.assertFalse(r.lies(p, true), "Doesn't lie on r [i = " + i + "].");
        }
        for (; i < MAX_ITER / 2; ++i) {
            p = new PlanePoint(i, 1);
            Assert.assertTrue(r.lies(p), "Actually lies on r [i = " + i + "].");
            Assert.assertTrue(
                    r.lies(p, true), "Actually lies on positive half of r [i = " + i + "].");

            p = new PlanePoint(i, 0);
            Assert.assertFalse(r.lies(p), "Doesn't lie on r [i = " + i + "].");
            Assert.assertFalse(r.lies(p, false), "Doesn't lie on r [i = " + i + "].");
        }
    }

    @Test
    public void testLies2() {
        PlanePoint o = new PlanePoint(-1, 0), p = new PlanePoint(-1, -1);
        Line r = new Line(o, p);
        int i;
        for (i = -MAX_ITER / 2; i <= 0; ++i) {
            p = new PlanePoint(-1, i);
            Assert.assertTrue(r.lies(p), "Actually lies on r [i = " + i + "].");
            Assert.assertTrue(r.lies(p, true),
                    "Actually lies on positive half of r [i = " + i + "].");

            p = new PlanePoint(0, i);
            Assert.assertFalse(r.lies(p), "Doesn't lie on r [i = " + i + "].");
            Assert.assertFalse(r.lies(p, true), "Doesn't lie on r [i = " + i + "].");
        }
        for (; i < MAX_ITER / 2; ++i) {
            p = new PlanePoint(-1, i);
            Assert.assertTrue(r.lies(p), "Actually lies on r [i = " + i + "].");
            Assert.assertTrue(r.lies(p, false),
                    "Actually lies on negative half of r [i = " + i + "].");

            p = new PlanePoint(0, i);
            Assert.assertFalse(r.lies(p), "Doesn't lie on r [i = " + i + "].");
            Assert.assertFalse(r.lies(p, false), "Doesn't lie on r [i = " + i + "].");
        }
    }

    @Test
    public void testLies3() {
        PlanePoint o = new PlanePoint(0, 0), p = new PlanePoint(1, 1);
        Line r = new Line(o, p);
        int i;
        for (i = -MAX_ITER / 2; i <= 0; ++i) {
            p = new PlanePoint(-i, -i);
            Assert.assertTrue(r.lies(p), "Actually lies on r [i = " + i + "].");
            Assert.assertTrue(r.lies(p, true),
                    "Actually lies on negative half of r [i = " + i + "].");
            p = new PlanePoint(1, i);
            Assert.assertFalse(r.lies(p), "Doesn't lie on r [i = " + i + "].");
            Assert.assertFalse(r.lies(p, true), "Doesn't lie on r [i = " + i + "].");
        }
        for (; i < MAX_ITER / 2; ++i) {
            p = new PlanePoint(i, i);
            Assert.assertTrue(r.lies(p), "Actually lies on r [i = " + i + "].");
            Assert.assertTrue(r.lies(p, true), "Actually lies on positive half of r [i = " + i + "].");
            p = new PlanePoint(0, -i);
            Assert.assertFalse(r.lies(p), "Doesn't lie on r [i = " + i + "].");
            Assert.assertFalse(r.lies(p, false), "Doesn't lie on r [i = " + i + "].");
        }
    }

    @Test
    public void testEquals() {
        PlanePoint o = new PlanePoint(0, 0), p = new PlanePoint(1, 1);
        Line r = new Line(o, p);
        Assert.assertFalse(r.equals(null), "Should never be equal to null.");
        Assert.assertFalse(r.equals(new Object()), "Should never be equal to a different type.");
        Assert.assertFalse(r.equals(new Line(o, p, p)), "Should never be equal if different origin.");
        Assert.assertFalse(r.equals(new Line(p, o)), "Should never be equal if different direction.");
    }

    @Test
    public void testHashCodeDiffCalls() {
        PlanePoint p = new PlanePoint(500, -30000), q = new PlanePoint(0, 0);
        Line r = new Line(p, q);
        int h = r.hashCode();
        for (int i = 0; i < MAX_ITER; ++i) {
            Assert.assertEquals(h, r.hashCode(), "Should return same value across different calls.");
        }
    }

    @Test
    public void testHashCodeObjectEquality() {
        PlanePoint p, q;
        Line r, s;
        for (int i = -MAX_ITER / 2; i < MAX_ITER / 2; ++i) {
            p = new PlanePoint(i, -i);
            q = new PlanePoint(i + 1, -i + 1);
            r = new Line(p, q);
            s = new Line(p, q);
            Assert.assertEquals(r.hashCode(), s.hashCode(), "Should return same value for equal objects [i = " + i
                    + "].");
        }
    }

}
