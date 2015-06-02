package checkout

object Checkout {
	def apply() = new Checkout()
}

class Checkout {
	def calculatePriceOf(products: Seq[String]): String = {
		val apples = products.count(_ == "Apple")
		val oranges = products.count(_ == "Orange")
		val totalPriceInPounds =  ((apples * 60) + (oranges * 25)).toDouble / 100
		f"£$totalPriceInPounds%1.2f"
	}
}