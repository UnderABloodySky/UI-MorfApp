package discount

import org.junit.Assert
import org.junit.Test

private class TestFixedDiscount {

    private var fixedDiscountGood100 : FixedDiscount = FixedDiscount("ejemplo", 100.0)
    private var fixedDiscountGood148 : FixedDiscount = FixedDiscount("ejemplo", 14800.0)
    private var fixedDiscountGood1000 : FixedDiscount = FixedDiscount("ejemplo", 100.0)
    private var fixedDiscountFool : FixedDiscount = FixedDiscount("ejemplo", 0.0)
    private var n : Double = 1000 as Double

    @Test
    fun  fixedDiscountFoolSustractZero(){
        Assert.assertEquals(1000.0, fixedDiscountFool.processDiscount(n))
    }

    @Test
    fun  nineHundredisTheResultOffixedDiscountGood100ProcessDiscount(){
        Assert.assertEquals(900.0, fixedDiscountGood100.processDiscount(n))
    }


    @Test
    fun  theResultOffixedDiscountGood148ProcessDiscountIs(){
        Assert.assertEquals(852.0, fixedDiscountGood148.processDiscount(n))
    }


    @Test
    fun  theResultOffixedDiscountGood1000ProcessDiscountIsZero(){
        Assert.assertEquals(0.0, fixedDiscountGood1000.processDiscount(n))
    }

}