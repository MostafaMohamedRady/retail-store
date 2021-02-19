package com.billing.roman;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RomanNumberTest {

    @Test(expected = IllegalArgumentException.class)
    public void testRomanNumberAbove3000() {
        RomanNumber romanNumber = new RomanNumber(3001);
        romanNumber.convert();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRomanNumber0() {
        RomanNumber romanNumber = new RomanNumber(0);
        romanNumber.convert();
    }

    @Test
    public void testRomanNumber() {
        RomanNumber romanNumber = new RomanNumber(1999);
        String convert = romanNumber.convert();
        Assert.assertEquals("MCMXCIX", convert);
    }

    @Test
    public void testRomanNumber_1() {
        RomanNumber romanNumber = new RomanNumber(25);
        String convert = romanNumber.convert();
        Assert.assertEquals("XXV", convert);

    }

}