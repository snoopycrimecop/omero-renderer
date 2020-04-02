/*
 *   Copyright 2006 University of Dundee. All rights reserved.
 *   Use is subject to license terms supplied in LICENSE.txt
 */

package ome.util.mem;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Unit test for {@link CopiableArray}.
 * 
 * @author Jean-Marie Burel &nbsp;&nbsp;&nbsp;&nbsp; <a
 *         href="mailto:j.burel@dundee.ac.uk">j.burel@dundee.ac.uk</a>
 * @author <br>
 *         Andrea Falconi &nbsp;&nbsp;&nbsp;&nbsp; <a
 *         href="mailto:a.falconi@dundee.ac.uk"> a.falconi@dundee.ac.uk</a>
 * @since OME2.2
 */
public class TestCopiableArray {

    private int SIZE = 10000;

    private SimpleCopiableArray copiableArray;

    private MockBody element; // Mock to play the element role.

    @BeforeMethod
    protected void setUp() {
        copiableArray = new SimpleCopiableArray(SIZE);
        element = new MockBody();
    }

    @Test
    public void testMakeNew() {
        try {
            new SimpleCopiableArray(0);
        } catch (IllegalArgumentException e) {
            Assert.assertTrue(true, e.getMessage());
        }
    }

    @Test
    public void testSize() {
        Assert.assertEquals(
                SIZE, copiableArray.getSize(), 0, "Should setthe size to argument passed to constructor.");

    }

    @Test
    public void testSet() {
        try {
            copiableArray.set(null, SIZE);
        } catch (IllegalArgumentException e) {
            Assert.assertTrue(true, e.getMessage());
        }
        try {
            copiableArray.set(null, -1);
        } catch (IllegalArgumentException e) {
            Assert.assertTrue(true, e.getMessage());
        }
    }

    @Test
    public void testGet() {
        try {
            copiableArray.get(SIZE);
        } catch (IllegalArgumentException e) {
            Assert.assertTrue(true, e.getMessage());
        }
        try {
            copiableArray.get(-1);
        } catch (IllegalArgumentException e) {
            Assert.assertTrue(true, e.getMessage());
        }
    }

    @Test
    public void testSetAndGet() {
        for (int i = 0; i < copiableArray.getSize(); i++) {
            copiableArray.set(element, i);
        }
        for (int i = 0; i < copiableArray.getSize(); i++) {
            Assert.assertEquals(element, copiableArray.get(i), "Wrong Copiable");
            Assert.assertNotNull(copiableArray.get(i));
        }
    }

    @Test
    public void testSetAndGet1() {
        SimpleCopiableArray array = new SimpleCopiableArray(copiableArray
                .getSize());
        MockBody mb;
        for (int i = 0; i < copiableArray.getSize(); i++) {
            mb = new MockBody();
            copiableArray.set(mb, i);
            array.set(mb, i);
        }
        for (int i = 0; i < copiableArray.getSize(); i++) {
            Assert.assertSame(array.get(i), copiableArray.get(i), "Wrong Copiable");
        }
    }

    @Test
    public void testCopy() {
        try {
            copiableArray.copy(-1, 0);
        } catch (IllegalArgumentException e) {
            Assert.assertTrue(true, e.getMessage());
        }
        try {
            copiableArray.copy(SIZE, SIZE);
        } catch (IllegalArgumentException e) {
            Assert.assertTrue(true, e.getMessage());
        }
        try {
            copiableArray.copy(0, SIZE);
        } catch (IllegalArgumentException e) {
            Assert.assertTrue(true, e.getMessage());
        }
        try {
            copiableArray.copy(1, 0);
        } catch (IllegalArgumentException e) {
            Assert.assertTrue(true, e.getMessage());
        }
    }

    @Test
    public void testCopy1() {
        SimpleCopiableArray array = new SimpleCopiableArray(2);
        array.set(element, 0);
        Assert.assertEquals(element, array.get(0), "Wrong Copiable");
        Assert.assertNull(array.get(1), "Element should be null");
        element.copy(element);
        element.activate();
        array.copy(0, 1);
        element.verify();
        Assert.assertNotNull(array.get(1), "Element shouldn't be null");
        Assert.assertEquals(element, array.get(1), "Wrong Copiable");
    }

    @Test
    public void testCopyNull() {
        SimpleCopiableArray array = new SimpleCopiableArray(2);
        Assert.assertNull(array.get(0), "Element should be null");
        Assert.assertNull(array.get(1), "Element should be null");
        array.copy(0, 1);
        Assert.assertNull(array.get(1), "Element should be null");
    }

}
