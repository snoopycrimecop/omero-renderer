/*
 *   Copyright 2006 University of Dundee. All rights reserved.
 *   Use is subject to license terms supplied in LICENSE.txt
 */

package ome.util.math.geom2D;

import java.awt.Rectangle;

import org.testng.Assert;
import org.testng.annotations.Test;


/**
 * Unit test for {@link RectangleArea}.
 * 
 * @author Jean-Marie Burel &nbsp;&nbsp;&nbsp;&nbsp; <a
 *         href="mailto:j.burel@dundee.ac.uk">j.burel@dundee.ac.uk</a>
 * @author <br>
 *         Andrea Falconi &nbsp;&nbsp;&nbsp;&nbsp; <a
 *         href="mailto:a.falconi@dundee.ac.uk"> a.falconi@dundee.ac.uk</a>
 * @since OME2.2
 */
public class TestRectangleArea {

    private static final int MAX_ITER = 100;

    @Test
    public void testRectangle1() {
        RectangleArea area = new RectangleArea();
        Rectangle r = area.getBounds();
        Assert.assertEquals(0, r.x, 0, "Should set x to 0.");
        Assert.assertEquals(0, r.y, 0, "Should set y to 0.");
        Assert.assertEquals(0, r.width, 0, "Should set w to 0.");
        Assert.assertEquals(0, r.height, 0, "Should set h to 0.");
    }

    @Test
    public void testRectangle2() {
        RectangleArea area = new RectangleArea(Integer.MIN_VALUE,
                Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
        Rectangle r = area.getBounds();
        Assert.assertEquals(Integer.MIN_VALUE, r.x, 0, "Should set x to argument passed to constructor.");
        Assert.assertEquals(Integer.MAX_VALUE, r.y, 0, "Should set y to argument passed to constructor.");
        Assert.assertEquals(Integer.MIN_VALUE, r.width, 0, "Should set w to argument passed to constructor.");
        Assert.assertEquals(Integer.MAX_VALUE, r.height, 0, "Should set h to argument passed to constructor.");
    }

    @Test
    public void testSetBounds() {
        RectangleArea area = new RectangleArea();
        area.setBounds(Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE,
                Integer.MAX_VALUE);
        Rectangle r = area.getBounds();
        Assert.assertEquals(Integer.MIN_VALUE, r.x, 0, "Should set x to argument passed to setBounds() method.");
        Assert.assertEquals(Integer.MAX_VALUE, r.y, 0, "Should set y to argument passed to setBounds() method.");
        Assert.assertEquals(Integer.MIN_VALUE, r.width, 0, "Should set w to argument passed to setBounds() method.");
        Assert.assertEquals(Integer.MAX_VALUE, r.height, 0, "Should set h to argument passed to setBounds() method.");
    }

    @Test
    public void testScale() {
        RectangleArea area = new RectangleArea(0, 0, 1, 1);
        double j;
        Rectangle r = area.getBounds();
        Rectangle rScale;
        for (int i = 0; i < MAX_ITER; i++) {
            j = (double) i / MAX_ITER;
            area.scale(j);
            rScale = area.getBounds();
            Assert.assertEquals(rScale.x, (int) (r.x * j), 0, "Wrong scale x [i = " + i + "].");
            Assert.assertEquals(rScale.y, (int) (r.y * j), 0, "Wrong scale y [i = " + i + "].");
            Assert.assertEquals(rScale.width, (int) (r.width * j), 0, "Wrong scale w [i = " + i + "].");
            Assert.assertEquals(rScale.height, (int) (r.height * j), 0, "Wrong scale h [i = " + i + "].");
        }
    }

    @Test
    public void testPlanePoints1() {
        RectangleArea area = new RectangleArea();
        PlanePoint[] points = area.getPoints();
        // Empty array
        Assert.assertEquals(0, points.length, 0, "Wrong size of the array");
    }

    @Test
    public void testPlanePoints2() {
        RectangleArea area = new RectangleArea(0, 0, MAX_ITER, MAX_ITER);
        PlanePoint[] points = area.getPoints();
        Assert.assertEquals(MAX_ITER * MAX_ITER, points.length, 0, "Wrong size of the array");
    }

    @Test
    public void testPlanePoints3() {
        RectangleArea area = new RectangleArea(0, 0, MAX_ITER, MAX_ITER);
        PlanePoint[] points = area.getPoints();
        PlanePoint point;
        int k = 0, j = 0, l = 1;
        for (int i = 0; i < points.length; i++) {
            point = points[i];
            Assert.assertEquals(k, point.x1, 0, "Wrong x coordinate");
            Assert.assertEquals(j, point.x2, 0, "Wrong y coordinate");
            if (i == l * MAX_ITER - 1) {
                l++;
                j++;
                k = -1;
            }
            k++;
        }
    }

    @Test
    public void testOnBoundaries() {
        RectangleArea area = new RectangleArea(0, 0, MAX_ITER, MAX_ITER);
        for (int i = 1; i < MAX_ITER; i++) {
            Assert.assertTrue(area.onBoundaries(i, 0));
            Assert.assertTrue(area.onBoundaries(i, MAX_ITER));
            Assert.assertTrue(area.onBoundaries(MAX_ITER, i));
            Assert.assertTrue(area.onBoundaries(0, i));
            Assert.assertFalse(area.onBoundaries(i, i));
        }
    }

}
