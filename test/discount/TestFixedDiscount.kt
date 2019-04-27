package discount

import org.junit.Assert
import org.junit.Test

class TestFixedDiscount {

    private var fixedDiscount100 : FixedDiscount = FixedDiscount(100.0)
    private var fixedDiscount148 : FixedDiscount = FixedDiscount(148.0)
    private var fixedDiscount1000 : FixedDiscount = FixedDiscount(1000.0)
    private var fixedDiscountFool : FixedDiscount = FixedDiscount(0.0)
    private var n : Double = 1000.00

    @Test
    fun  fixedDiscountFoolSustractZero(){
        Assert.assertEquals(n, fixedDiscountFool.processDiscount(n), 0.0);
    }

    @Test
    fun  nineHundredisTheResultOffixedDiscountGood100ProcessDiscount(){
        Assert.assertEquals(900.0, fixedDiscount100.processDiscount(n), 0.0)
    }


    @Test
    fun  theResultOffixedDiscountGood148ProcessDiscountIs(){
        Assert.assertEquals(852.0, fixedDiscount148.processDiscount(n), 0.0)
    }


    @Test
    fun  theResultOffixedDiscountGood1000ProcessDiscountIsZero(){
        Assert.assertEquals(0.0, fixedDiscount1000.processDiscount(n), 0.0)
    }

}