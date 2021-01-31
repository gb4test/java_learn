package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestForPoint {

    @Test
    public void testDistance() {
        Coord f = new Coord(0,1);
        Coord s = new Coord(3, 1);
        Assert.assertEquals(f.distance(s), 3);

        Coord g = new Coord(1,0);
        Coord a = new Coord( 1, 4);
        Assert.assertEquals(g.distance(a), 4);

        Coord h = new Coord(0,0);
        Coord q = new Coord(0, 0);
        Assert.assertEquals(h.distance(q), 0);

        Coord j = new Coord(0.1,0.1);
        Coord w = new Coord(0.1,1.1);
        Assert.assertEquals(j.distance(w), 1);

        Coord r = new Coord(0,0);
        Coord e = new Coord(0,-2);
        Assert.assertEquals(r.distance(e), 2);
    }
}
