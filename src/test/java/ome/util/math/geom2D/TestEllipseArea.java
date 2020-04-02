/*
 *   Copyright 2006 University of Dundee. All rights reserved.
 *   Use is subject to license terms supplied in LICENSE.txt
 */

package ome.util.math.geom2D;

import java.awt.Rectangle;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Unit test for {@link EllipseArea}.
 * 
 * @author Jean-Marie Burel &nbsp;&nbsp;&nbsp;&nbsp; <a
 *         href="mailto:j.burel@dundee.ac.uk">j.burel@dundee.ac.uk</a>
 * @author <br>
 *         Andrea Falconi &nbsp;&nbsp;&nbsp;&nbsp; <a
 *         href="mailto:a.falconi@dundee.ac.uk"> a.falconi@dundee.ac.uk</a>
 * @since OME2.2
 */
public class TestEllipseArea {
    private static final int LENGTH = 4, MAX_ITER = 1000;

    public void TestEllipse() {
        EllipseArea area = new EllipseArea(0, 0, Integer.MIN_VALUE,
                Integer.MAX_VALUE);
        Rectangle r = area.getBounds();
        Assert.assertEquals(0, r.x, 0, "Should set x to argument passed to constructor.");
        Assert.assertEquals(0, r.y, 0, "Should set y to argument passed to constructor.");
        Assert.assertEquals(Integer.MIN_VALUE, r.width, 0, "Should set w to argument passed to constructor.");
        Assert.assertEquals(Integer.MAX_VALUE, r.height, 0, "Should set h to argument passed to constructor.");
    }

    @Test
    public void testSetBounds() {
        EllipseArea area = new EllipseArea(0, 0, 1, 1);
        area.setBounds(0, 0, 2, 2);
        Rectangle r = area.getBounds();
        Assert.assertEquals(0, r.x, 0, "Should set x to argument passed to setBounds() method.");
        Assert.assertEquals(0, r.y, 0, "Should set y to argument passed to setBounds() method.");
        Assert.assertEquals(2, r.width, 0, "Should set w to argument passed to setBounds() method.");
        Assert.assertEquals(2, r.height, 0, "Should set h to argument passed to setBounds() method.");
    }

    @Test
    public void testScale() {
        EllipseArea area = new EllipseArea(0, 0, 1, 1);
        double j;
        Rectangle r = area.getBounds();
        Rectangle rScale;
        for (int i = 0; i < MAX_ITER; i++) {
            j = (double) i / MAX_ITER;
            area.scale(j);
            rScale = area.getBounds();
            Assert.assertEquals(rScale.x, (int) (r.x * j), 0, "Wrong scale x [i = " + i + "].");
            Assert.assertEquals((int) (r.y * j), 0, rScale.y,  "Wrong scale y [i = " + i + "].");
            Assert.assertEquals(rScale.width, (int) (r.width * j), 0, "Wrong scale w [i = " + i + "].");
            Assert.assertEquals(rScale.height, (int) (r.height * j), 0, "Wrong scale h [i = " + i + "].");
        }
    }

    @Test
    public void testPlanePoints1() {
        EllipseArea area = new EllipseArea(0, 0, 1, 1);
        // Empty array
        PlanePoint[] points = area.getPoints();
        Assert.assertEquals(points.length, 0, 0, "Wrong size of the array");
    }

    @Test
    public void testPlanePoints2() {
        // Ellipse which only contains the origin.
        EllipseArea area = new EllipseArea(-1, -1, 2, 2);
        PlanePoint[] points = area.getPoints();
        Assert.assertEquals(1, points.length, 0, "Wrong size of the array");
        Assert.assertEquals(0, points[0].x1, 0, "Wrong x coordinate");
        Assert.assertEquals(0, points[0].x2, 0, "Wrong y coordinate");
    }

    @Test
    public void testPlanePoints3() {
        // Ellipse containing 9 points.
        // (-1, 1), (0, 1), (1, 1), (-1, 0), (0, 0), (0, 1)
        // (-1, -1), (0, -1), (1, -1)
        EllipseArea area = new EllipseArea(-2, -2, LENGTH, LENGTH);
        PlanePoint[] points = area.getPoints();
        Assert.assertEquals(2 * LENGTH + 1, points.length,0, "Wrong size of the array");
        PlanePoint point;
        int k = -1, j = -1, l = 1;
        for (int i = 0; i < points.length; i++) {
            point = points[i];
            Assert.assertEquals(k, point.x1, 0, "Wrong x coordinate");
            Assert.assertEquals(j, point.x2, 0, "Wrong y coordinate");
            if (i == l * (LENGTH - 1) - 1) {
                l++;
                j++;
                k = -2;
            }
            k++;
        }
    }

    @Test
    public void testOnBoundaries() {
        EllipseArea area = new EllipseArea(-1, -1, 2, 2);
        Assert.assertFalse(area.onBoundaries(0, 0));
    }

}
