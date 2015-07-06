import org.scalatest.{Matchers, GivenWhenThen, FlatSpec}

class NaiveDuplicateRemoverTest extends FlatSpec with GivenWhenThen with Matchers {
  trait PersonData {
    def name: String
  }
  
  case class Person(name: String) extends PersonData
  case class ZeroHashPerson(name: String) extends PersonData {
    override def hashCode(): Int = 0
  }

  it should "remove duplicates from the list" in {
    Given("list with duplicates")
    val input = List(
      Person("Smill With"),
      Person("Donny Jepp"),
      Person("Smill With"),
      Person("Din Viesel"),
      Person("Din Viesel"))

    When("running the duplicate removal algorithm")
    val result = new NaiveDuplicateRemover(input).removeDuplicates()

    Then("result should have duplicates removed")
    result shouldBe List(Person("Smill With"), Person("Donny Jepp"), Person("Din Viesel"))
  }

  it should "work properly with colliding hashes" in {
    Given("dataset with coliding hashes")
    val input = List(
      Person("Smill With"),
      ZeroHashPerson("Donny Jepp"),
      ZeroHashPerson("Din Viesel"),
      Person("Smill With")
    )

    When("running the duplicate removal algorithm")
    val result = new NaiveDuplicateRemover(input).removeDuplicates()

    Then("result should have duplicates removed")
    result shouldBe List(Person("Smill With"), ZeroHashPerson("Donny Jepp"), ZeroHashPerson("Din Viesel"))
  }
}
