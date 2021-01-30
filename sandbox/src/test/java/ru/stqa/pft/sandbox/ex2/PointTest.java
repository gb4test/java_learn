package ru.stqa.pft.sandbox.ex2;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTest {

    @Test
    public void testDistance() {
        Coordinates f = new Coordinates(0,1,3,1);
        Assert.assertEquals(f.distance(), 3);

        Coordinates g = new Coordinates(1,0,1,4);
        Assert.assertEquals(g.distance(), 4);

        Coordinates h = new Coordinates(0,0,0,0);
        Assert.assertEquals(h.distance(), 0);

        Coordinates j = new Coordinates(0.1,0.1,0.1,1.1);
        Assert.assertEquals(j.distance(), 1);
    }
}
