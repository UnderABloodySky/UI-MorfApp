package discount

import org.junit.Assert
import org.junit.Test

class  TestPercentageDiscount {

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
        Assert.assertEquals(1.98, percentageDiscount1.processDiscount(2.0), 0.0)
        Assert.assertEquals(222.75, percentageDiscount1.processDiscount(225.0), 0.0)
        Assert.assertEquals(1316.70, percentageDiscount1.processDiscount(1330.0), 0.0)
        Assert.assertEquals(123480.72, percentageDiscount1.processDiscount(124728.0), 0.0)
    }

    @Test
    fun thePercentageDidscount48ProcessingDiscountIsSustractAlwaysThefourtyEightPorcentage(){
        Assert.assertEquals(6.24, percentageDiscount48.processDiscount(12.0), 0.0)
        Assert.assertEquals(234.0, percentageDiscount48.processDiscount(450.0), 0.0)
        Assert.assertEquals(1383.33, percentageDiscount48.processDiscount(2660.25), 0.0)
        Assert.assertEquals(520000.0, percentageDiscount48.processDiscount(1000000.0), 0.0)
    }


    @Test
    fun thePercentageDiscount100processingDiscount100IsAlwaysZero(){
//        Assert.assertEquals(0.0, percentageDiscount100.processDiscount(1.0), 0.0)
        Assert.assertEquals(0.0, percentageDiscount100.processDiscount(10.0), 0.0)
        Assert.assertEquals(0.0, percentageDiscount100.processDiscount(100.0), 0.0)
        Assert.assertEquals(0.0, percentageDiscount100.processDiscount(10000000.0), 0.0)
    }

    @Test
    fun thePercentageDidscount0DiscountIsCorrect(){
        Assert.assertEquals(0.0, percentageDiscount0.discount(12.0), 0.0)
        Assert.assertEquals(0.0, percentageDiscount0.discount(450.0), 0.0)
        Assert.assertEquals(0.0, percentageDiscount0.discount(2660.25), 0.0)
        Assert.assertEquals(0.0, percentageDiscount0.discount(1000000.0), 0.0)
    }

    @Test
    fun thePercentageDidscount1DiscountIsCorrect(){
        Assert.assertEquals(0.12, percentageDiscount1.discount(12.0), 0.0)
        Assert.assertEquals(4.5, percentageDiscount1.discount(450.0), 0.0)
        Assert.assertEquals(26.6025, percentageDiscount1.discount(2660.25), 0.0)
        Assert.assertEquals(10000.0, percentageDiscount1.discount(1000000.0), 0.0)
    }

    @Test
    fun thePercentageDiscount48DiscountIsCorrect(){
        Assert.assertEquals(5.76, percentageDiscount48.discount(12.0), 0.0)
        Assert.assertEquals(216.0, percentageDiscount48.discount(450.0), 0.0)
        Assert.assertEquals(1276.92, percentageDiscount48.discount(2660.25), 0.0)
        Assert.assertEquals(480000.0, percentageDiscount48.discount(1000000.0), 0.0)
    }

    @Test
    fun thePercentageDidscount100DiscountIsCorrect(){
        Assert.assertEquals(12.0, percentageDiscount100.discount(12.0), 0.0)
        Assert.assertEquals(450.0, percentageDiscount100.discount(450.0), 0.0)
        Assert.assertEquals(2660.25, percentageDiscount100.discount(2660.25), 0.0)
        Assert.assertEquals(1000000.0, percentageDiscount100.discount(1000000.0), 0.0)
    }
}