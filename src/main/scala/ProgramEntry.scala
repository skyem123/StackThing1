abstract class ProgramEntry {
	override def toString: String
}

object ProgramEntry {

	def data(entry: StackEntry) = new data(entry)
	def skip(amount: Int)       = new skip(amount)
	def nop   = new nop
	def add   = new add
	def sub   = new sub
	def so    = new so
	def equal = new equal
	def save  = new save
	def load  = new load
	def call  = new call
	def dup   = new dup
	def ret   = new ret

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
	class call extends ProgramEntry {
		override def toString: String = s"ProgramEntry.call"
	}
	class dup extends ProgramEntry {
		override def toString: String = s"ProgramEntry.dup"
	}
	class ret extends ProgramEntry {
		override def toString: String = s"ProgramEntry.ret"
	}
}

