abstract class ProgramEntry {
	override def toString: String
}

object ProgramEntry {

	def nop: ProgramEntry.nop = new nop
	def add: ProgramEntry.add = new add
	def sub: ProgramEntry.sub = new sub
	def data(entry: StackEntry) = new data(entry)
	def skip(amount: Int) = new skip(amount)
	def so: ProgramEntry.so = new so
	def equal: ProgramEntry.equal = new equal
	def save: ProgramEntry.save = new save
	def load: ProgramEntry.load = new load

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
	class so extends ProgramEntry {
		override def toString: String = s"ProgramEntry.so"
	}
	class save extends ProgramEntry {
		override def toString: String = s"ProgramEntry.save"
	}
	class load extends ProgramEntry {
		override def toString: String = s"ProgramEntry.load"
	}
}

