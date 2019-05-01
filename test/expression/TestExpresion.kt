package expression

import org.junit.Assert
import org.junit.Test

class TestExpression {

    private val onlyAlphaNumeric = Regex("[^A-Za-z0-9 ]")
    private val onlyPositiveDoubleNumber = Regex("^(?:\\+|-)?\\d+\\.\\d*$")
    private val isANumber = Regex("^[0-9]*")

    @Test
    fun onlyAlphanumericExpressionReturnTrueWhenTheWordContainsAlphanumericCharacters() {
        var answer = "Ozzymandias"
        Assert.assertFalse(onlyAlphaNumeric.containsMatchIn(answer))
    }

    @Test
    fun onlyAlphaNumericExpressionReturnFalseWhenTheWordDoesNotContainsAlphanumericCharacters() {
        var answer = "@Asdfghy67582est. ,re¡ípl&/ace¡í"
        Assert.assertTrue(onlyAlphaNumeric.containsMatchIn(answer))
    }

    @Test
    fun onlyAlphanumericExpressionReemplaceNoAlphanumericCharacters() {
        var answer = "@Test. ,re¡ípl&/ace¡í"
        Assert.assertEquals("Test replace", onlyAlphaNumeric.replace(answer, ""))
    }

    //Cualquier cosa
    @Test
    fun onlyPositiveDoubleNumberExpressionReturnTrueWhenTheWordContainsAlphanumericCharacters() {
        var answer = "-12.00"
        Assert.assertTrue(onlyPositiveDoubleNumber.containsMatchIn(answer))
    }

    @Test
    fun onlyPositiveDoubleNumberExpressionReturnFalseWhenTheWordDoesNotContainsAlphanumericCharacters() {
        var answer = "1.200"
        Assert.assertTrue(onlyPositiveDoubleNumber.containsMatchIn(answer))
    }

    //Cualquier cosa
    @Test
    fun isANumberExpressionReturnFalseWhenTheWordDoesNotContainsANumber() {
        var answer = "1234"
        Assert.assertTrue(isANumber.containsMatchIn(answer))
    }

    //Cualquier cosa
    @Test
    fun isANumberExpressionReturnFalseWhenTheWordContainsANumber() {
        var answer = "H"
        Assert.assertTrue(isANumber.containsMatchIn(answer))
    }
}