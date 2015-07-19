package uk.co.skyem.random.stack1

/**
 * An entry on the stack
 */
class StackEntry(entryType: String, entryData: Any) {
	def getData: Any = entryData
	def getType: String = entryType.toLowerCase

	def +(other: StackEntry): StackEntry = {
		if (getType == "int32" && other.getType == entryType) {
			return new StackEntry(entryType, entryData.asInstanceOf[Int] + other.getData.asInstanceOf[Int])
		}
		throw new IllegalArgumentException("Cannot add a " + getType + " and a " + other.getType)
	}
	def -(other: StackEntry): StackEntry = {
		if (getType == "int32" && other.getType == entryType) {
			return new StackEntry(entryType, entryData.asInstanceOf[Int] - other.asInstanceOf[Int])
		}
		throw new IllegalArgumentException("Cannot subtract a " + getType + " and a " + other.getType)
	}
	def ==(other: StackEntry): Boolean = {
		entryData == other.getData
	}

	override def toString: String = s"StackEntry($getType, $entryData)"

}

object StackEntry {
	def stackEntry(entryType: String, entryData: Any): StackEntry = new StackEntry(entryType, entryData)
}
