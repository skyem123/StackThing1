class ProgramEntry {
	override def toString: String = s"ProgramEntry"
}

object ProgramEntry {
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
	class then() extends ProgramEntry {
		override def toString: String = s"ProgramEntry.then"
	}
}

