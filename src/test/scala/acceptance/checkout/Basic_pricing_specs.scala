package test.acceptance.checkout

import org.scalatest.{FreeSpec, MustMatchers}
import org.scalatest.prop.TableDrivenPropertyChecks._

class Basic_pricing_specs extends FreeSpec with MustMatchers {
	val testCases = Table(
		("products", "totalPrice"),
		(Seq.empty, "£0.00"),
		(Seq("Apple"), "£0.60"),
		(Seq("Orange"), "£0.25"),
		(Seq("Apple", "Apple"), "£1.20"),
		(Seq("Apple", "Orange"), "£0.85"),
		(Seq("Apple", "Apple", "Orange", "Apple"), "£2.05"),
		(Seq("Orange", "Orange", "Apple", "Orange"), "£1.35"),
		(Seq("Orange", "Apple", "Orange", "Apple", "Orange"), "£1.95")
	)

	"Calculates and formats total price by summing unit prices of each individual item" in {
		forAll(testCases) { (products: Seq[String], formattedTotalPrice: String) =>
		   Checkout().calculatePriceOf(products) must equal(formattedTotalPrice)
		}
	}
}

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