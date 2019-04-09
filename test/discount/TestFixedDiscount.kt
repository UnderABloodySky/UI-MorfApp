package discount

import org.junit.Assert
import org.junit.Test

private class TestFixedDiscount {

    private var fixedDiscountGood : FixedDiscount = FixedDiscount("ejemplo", 100.0)
    private var fixedDiscountFool : FixedDiscount = FixedDiscount("ejemplo", 0.0)
    private var n : Double = 1000 as Double

    @Test
    fun  fixedDiscountFoolSustractZero(){
        Assert.assertEquals(1000.0, fixedDiscountFool.processDiscount(n))
    }

    @Test
    fun  nineHundredisTheResultOffixedDiscountGoodProcessDiscount(){
        Assert.assertEquals(900.0, fixedDiscountGood.processDiscount(n))
    }

}