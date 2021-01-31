package ru.stqa.pft.sandbox.ex2;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTest {

    @Test
    public void testDistance() {
        Coordinates f = new Coordinates(0,1);
        Coordinates s = new Coordinates(3, 1);
        Assert.assertEquals(f.distance(s), 3);

        Coordinates g = new Coordinates(1,0);
        Coordinates a = new Coordinates( 1, 4);
        Assert.assertEquals(g.distance(a), 4);

        Coordinates h = new Coordinates(0,0);
        Coordinates q = new Coordinates(0, 0);
        Assert.assertEquals(h.distance(q), 0);

        Coordinates j = new Coordinates(0.1,0.1);
        Coordinates w = new Coordinates(0.1,1.1);
        Assert.assertEquals(j.distance(w), 1);

        Coordinates r = new Coordinates(0,0);
        Coordinates e = new Coordinates(0,-2);
        Assert.assertEquals(r.distance(e), 2);
    }
}
