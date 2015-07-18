class ProgramEntry {
	override def toString: String = s"ProgramEntry"
}

object ProgramEntry {

	def nop: ProgramEntry.nop = new nop
	def add: ProgramEntry.add = new add
	def sub: ProgramEntry.sub = new sub
	def data(entry: StackEntry) = new data(entry)
	def skip(amount: Int) = new skip(amount)
	def then: ProgramEntry.then = new then
	def equal: ProgramEntry.equal = new equal

	class nop extends ProgramEntry {
		override def toString: String = s"ProgramEntry.nop"
	}
	class add extends ProgramEntry {
		override def toString: String = s"ProgramEntry.add"
	}
	class sub extends ProgramEntry {
		override def toString: String = s"ProgramEntry.sub"
	}
	class equal extends ProgramEntry {
		override def toString: String = s"ProgramEntry.equal"
	}
	class data(entry: StackEntry) extends ProgramEntry {
		def data = entry
		override def toString: String = s"ProgramEntry.data($entry)"
	}
	class skip(number: Int) extends ProgramEntry {
		def instructions = number
		override def toString: String = s"ProgramEntry.skip($number)"
	}
	class then extends ProgramEntry {
		override def toString: String = s"ProgramEntry.then"
	}
}

