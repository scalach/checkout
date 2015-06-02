package test.acceptance.checkout

import org.scalatest.{FreeSpec, MustMatchers}
import org.scalatest.prop.TableDrivenPropertyChecks._
import checkout.Checkout

class Special_offer_specs extends FreeSpec with MustMatchers {
	import TestCases._

	"Applies a buy one get one free discount for all apples to the total price" in {
		forAll(appleSpecialOfferTestCases) { (products: Seq[String], formattedTotalPriceWithDiscounts: String) =>
			Checkout().calculatePriceOf(products) must equal(formattedTotalPriceWithDiscounts)
		}
	}

	"Applies a 3 for 2 discount for all oranges to the total price" in {
		forAll(orangeSpecialOfferTestCases) { (products: Seq[String], formattedTotalPriceWithDiscounts: String) =>
			Checkout().calculatePriceOf(products) must equal(formattedTotalPriceWithDiscounts)
		}
	}

	"Applies apple bogof discounts and orange 3 for 2 discounts to the same total price" in {
		forAll(mixedItemSpecialOfferTestCases) { (products: Seq[String], formattedTotalPriceWithDiscounts: String) =>
			Checkout().calculatePriceOf(products) must equal(formattedTotalPriceWithDiscounts)
		}
	}

	object TestCases {
		val appleSpecialOfferTestCases = Table(
			("products", "totalPrice"),
			(Seq("Apple"), "£0.60"),
			(Seq("Apple", "Apple"), "£0.60"),
			(Seq("Apple", "Apple", "Apple"), "£1.20"), 
			(apples(7), "£2.40"),
			(apples(36), "£10.80")
		)

		val orangeSpecialOfferTestCases = Table(
			("products", "totalPrice"),
			(Seq("Orange"), "£0.25"),
			(Seq("Orange", "Orange"), "£0.50"),
			(oranges(3), "£0.50"),
			(oranges(5), "£1.00"),
			(oranges(6), "£1.00"),
			(oranges(50), "£8.50")
		)	

		val mixedItemSpecialOfferTestCases = Table(
			("products", "totalPrice"),
			(apples(2) ++ oranges(3), "£1.10"),
			(apples(6) ++ oranges(12), "£3.80")
		)

		def apples(amount: Int): Seq[String] = (1 to amount).map { n => "Apple"}
		def oranges(amount: Int): Seq[String] = (1 to amount).map { n => "Orange"}
	}

}