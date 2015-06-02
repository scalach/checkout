package test.acceptance.checkout

import org.scalatest.{FreeSpec, MustMatchers}
import org.scalatest.prop.TableDrivenPropertyChecks._
import checkout.Checkout

class Basic_pricing_specs extends FreeSpec with MustMatchers {
	val testCases = Table(
		("products", "totalPrice"),
		(Seq.empty, "£0.00"),
		(Seq("Apple"), "£0.60"),
		(Seq("Orange"), "£0.25"),
		(Seq("Apple", "Orange"), "£0.85"),
		(Seq("Apple", "Orange", "Orange"), "£1.10")
	)

	"Calculates and formats total price by summing unit prices of each individual item" in {
		forAll(testCases) { (products: Seq[String], formattedTotalPrice: String) =>
		   Checkout().calculatePriceOf(products) must equal(formattedTotalPrice)
		}
	}
}

