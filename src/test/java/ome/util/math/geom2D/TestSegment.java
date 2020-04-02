/*
 *   Copyright 2006 University of Dundee. All rights reserved.
 *   Use is subject to license terms supplied in LICENSE.txt
 */

package ome.util.math.geom2D;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Unit test for {@link Segment}.
 * 
 * @author Jean-Marie Burel &nbsp;&nbsp;&nbsp;&nbsp; <a
 *         href="mailto:j.burel@dundee.ac.uk">j.burel@dundee.ac.uk</a>
 * @author <br>
 *         Andrea Falconi &nbsp;&nbsp;&nbsp;&nbsp; <a
 *         href="mailto:a.falconi@dundee.ac.uk"> a.falconi@dundee.ac.uk</a>
 * @since OME2.2
 */
public class TestSegment {

    private static final int MAX_ITER = 30000; // Max iterations in a test.

    private static final int INTERVAL = 10;

    @Test
    public void testSegmentBadArgs() {
        try {
            new Segment(1, 1, 1, 1);
            Assert.fail("Shouldn't allow same points.");
        } catch (IllegalArgumentException iae) {
            // Ok, expected.
        }
    }

    @Test
    public void testSegment() {
        Segment r = new Segment(0, 0, 1, 1);
        Assert.assertEquals(0.0, r.originX1, "Shouldn't change the originX1.");
        Assert.assertEquals(0.0, r.originX2, "Shouldn't change the originX2.");
    }

    @Test
    public void testGetPointXAxis() {
        PlanePoint p;
        Segment r = new Segment(0, 0, 1, 0);
        double d;
        for (int i = 0; i < INTERVAL; ++i) {
            d = (double) i / INTERVAL;
            p = new PlanePoint(d, 0);
            Assert.assertEquals(p, r.getPoint(d), "Wrong point [i = " + i + "].");
        }
    }

    @Test
    public void testGetPointYAxis() {
        PlanePoint p;
        Segment r = new Segment(0, 0, 0, 1);
        double d;
        for (int i = 0; i < INTERVAL; ++i) {
            d = (double) i / INTERVAL;
            p = new PlanePoint(0, d);
            Assert.assertEquals(p, r.getPoint(d), "Wrong point [i = " + i + "].");
        }
    }

    @Test
    public void testGetPointParallelXAxis() {
        PlanePoint p;
        Segment r = new Segment(0, 1, 1, 1);
        double d;
        for (int i = 0; i < INTERVAL; ++i) {
            d = (double) i / INTERVAL;
            p = new PlanePoint(d, 1);
            Assert.assertEquals(p, r.getPoint(d), "Wrong point [i = " + i + "].");
        }
    }

    @Test
    public void testGetPointParallelYAxis() {
        PlanePoint p;
        Segment r = new Segment(1, 0, 1, 1);
        double d;
        for (int i = 0; i < INTERVAL; ++i) {
            d = (double) i / INTERVAL;
            p = new PlanePoint(1, d);
            Assert.assertEquals(p, r.getPoint(d), "Wrong point [i = " + i + "].");
        }
    }

    @Test
    public void testLies1() {
        Segment r = new Segment(0, 1, 1, 1);
        double d;
        for (int i = 0; i < INTERVAL; ++i) {
            d = (double) i / INTERVAL;
            Assert.assertTrue(r.lies(d, 1), "Actually lies on r [i = " + i + "].");
            Assert.assertFalse(r.lies(d, 0), "Doesn't lie on r [i = " + i + "].");
        }
    }

    @Test
    public void testLies2() {
        Segment r = new Segment(1, 0, 1, 1);
        double d;
        for (int i = 0; i < INTERVAL; ++i) {
            d = (double) i / INTERVAL;
            Assert.assertTrue(r.lies(1, d), "Actually lies on r [i = " + i + "].");
            Assert.assertFalse(r.lies(d, 0), "Doesn't lie on r [i = " + i + "].");
        }
    }

    @Test
    public void testLies3() {
        Segment r = new Segment(0, 0, 1, 1);
        double d;
        for (int i = 1; i < INTERVAL; ++i) {
            d = (double) i / INTERVAL;
            Assert.assertTrue(r.lies(d, d), "Actually lies on r [i = " + i + "].");
            Assert.assertFalse(r.lies(d, 0), "Doesn't lie on r [i = " + i + "].");
        }
    }

    @Test
    public void testEquals() {
        PlanePoint o = new PlanePoint(0, 0), p = new PlanePoint(1, 1);
        Segment r = new Segment(0, 0, 1, 1);
        Assert.assertFalse(r.equals(null), "Should never be equal to null.");
        Assert.assertFalse(r.equals(new Object()), "Should never be equal to a different type.");
        Assert.assertFalse(r.equals(new Line(o, p, p)), "Should never be equal if different origin.");
        Assert.assertFalse(r.equals(new Line(p, o)), "Should never be equal if different direction.");
    }

    @Test
    public void testHashCodeDiffCalls() {
        Segment r = new Segment(500, -30000, 0, 0);
        int h = r.hashCode();
        for (int i = 0; i < MAX_ITER; ++i) {
            Assert.assertEquals(h, r.hashCode(), "Should return same value across different calls.");
        }
    }

    @Test
    public void testHashCodeObjectEquality() {
        Segment r, s;
        for (int i = -MAX_ITER / 2; i < MAX_ITER / 2; ++i) {
            r = new Segment(i, -i, i + 1, -i + 1);
            s = new Segment(i, -i, i + 1, -i + 1);
            Assert.assertEquals(r.hashCode(), s.hashCode(), "Should return same value for equal objects [i = " + i
                    + "].");
        }
    }

}
