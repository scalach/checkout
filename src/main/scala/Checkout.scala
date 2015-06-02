package checkout

/*
	Notes for reviewer
	~~~~~~~~~~~~~~~~~~

	I'm definitely aware of the multiple responsibilities this class has (price lookup, price calculation, discount calculation, price formatting).
	Please see the design-decisions.txt for my justification of this current implementation.
*/
object Checkout {
	def apply() = new Checkout()
}

class Checkout {
	private val appleUnitPrice = 60
	private val orangeUnitPrice = 25

	def calculatePriceOf(products: Seq[String]): String = {
		val apples = products.count(_ == "Apple")
		val oranges = products.count(_ == "Orange")
		val subTotal =  ((apples * appleUnitPrice) + (oranges * orangeUnitPrice))
		val discounts = calculateAppleDiscounts(apples) + calculateOrangeDiscounts(oranges)
		val totalPriceInPounds = (subTotal - discounts).toDouble / 100
		f"£$totalPriceInPounds%1.2f"
	}

	private def calculateAppleDiscounts(numberOfApples: Int) = (numberOfApples / 2) * appleUnitPrice

	private def calculateOrangeDiscounts(numberOfOranges: Int) = (numberOfOranges / 3) * orangeUnitPrice
}