package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestForPoint {

    @Test
    public void testDistance() {
        Point f = new Point(0,1);
        Point s = new Point(3, 1);
        Assert.assertEquals(f.distance(s), 3);

        Point g = new Point(1,0);
        Point a = new Point( 1, 4);
        Assert.assertEquals(g.distance(a), 4);

        Point h = new Point(0,0);
        Point q = new Point(0, 0);
        Assert.assertEquals(h.distance(q), 0);

        Point j = new Point(0.1,0.1);
        Point w = new Point(0.1,1.1);
        Assert.assertEquals(j.distance(w), 1);

        Point r = new Point(0,0);
        Point e = new Point(0,-2);
        Assert.assertEquals(r.distance(e), 2);
    }
}
