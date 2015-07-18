package uk.co.skyem.random.stack1

/**
 * An entry on the stack
 */
class StackEntry(entryType: String, entryData: Any) {
	def getData: Any = entryData
	def getType: String = entryType

	def +(other: StackEntry): StackEntry = {
		if (entryType == "int32" && other.getType == entryType) {
			return new StackEntry(entryType, entryData.asInstanceOf[Int] + other.getData.asInstanceOf[Int])
		}
		throw new IllegalArgumentException("Cannot add a " + entryType + " and a " + other.getType)
	}
	def -(other: StackEntry): StackEntry = {
		if (entryType == "int32" && other.getType == entryType) {
			return new StackEntry(entryType, entryData.asInstanceOf[Int] - other.asInstanceOf[Int])
		}
		throw new IllegalArgumentException("Cannot subtract a " + entryType + " and a " + other.getType)
	}
	def ==(other: StackEntry): Boolean = {
		entryData == other.getData
	}

	override def toString: String = s"StackEntry($entryType, $entryData)"

}

object StackEntry {
	def stackEntry(entryType: String, entryData: Any): StackEntry = new StackEntry(entryType, entryData)
}
