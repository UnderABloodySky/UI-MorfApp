package discount

import org.junit.Assert
import org.junit.Test

class TestPercentageDiscount {

    private var percentageDiscount0 : PercentageDiscount = PercentageDiscount("ejemploA", 0.0)
    private var percentageDiscount1 : PercentageDiscount = PercentageDiscount("ejemploB", 1.0)
    private var percentageDiscount48 : PercentageDiscount = PercentageDiscount("ejemploC", 48.0)
    private var percentageDiscount100 : PercentageDiscount = PercentageDiscount("ejemploD", 100.0)


    @Test
    fun thePercentageDidscount0ProcessingDiscountIsAlwaysTheSameNumber(){
        Assert.assertEquals(0.0, percentageDiscount0.processDiscount(0.0), 0.0)
        Assert.assertEquals(1.0, percentageDiscount0.processDiscount(1.0), 0.0)
        Assert.assertEquals(10.0, percentageDiscount0.processDiscount(10.0), 0.0)
        Assert.assertEquals(1000.0, percentageDiscount0.processDiscount(1000.0), 0.0)
    }


    @Test
    fun thePercentageDidscount1ProcessingDiscountIsSustractAlwaysTheOnePorcentage(){
//        Assert.assertEquals(0.0, percentageDiscount1.processDiscount(0.0), 0.0)
//        Assert.assertEquals(1.0, percentageDiscount1.processDiscount(1.0), 0.0)
//        Assert.assertEquals(10.0, percentageDiscount1.processDiscount(10.0), 0.0)
//        Assert.assertEquals(1000.0, percentageDiscount1.processDiscount(1000.0), 0.0)
    }


    @Test
    fun thePercentageDidscount48ProcessingDiscountIsSustractAlwaysThefourtyEightPorcentage(){
//        Assert.assertEquals(0.0, percentageDiscount48.processDiscount(0.0), 0.0)
//        Assert.assertEquals(1.0, percentageDiscount48.processDiscount(1.0), 0.0)
//        Assert.assertEquals(10.0, percentageDiscount48.processDiscount(10.0), 0.0)
//        Assert.assertEquals(1000.0, percentageDiscount48.processDiscount(1000.0), 0.0)
    }


    @Test
    fun thePercentageDiscount100processingDiscount100IsAlwaysZero(){
//        Assert.assertEquals(0.0, percentageDiscount100.processDiscount(1.0), 0.0)
        Assert.assertEquals(0.0, percentageDiscount100.processDiscount(10.0), 0.0)
        Assert.assertEquals(0.0, percentageDiscount100.processDiscount(100.0), 0.0)
        Assert.assertEquals(0.0, percentageDiscount100.processDiscount(10000000.0), 0.0)
    }
}