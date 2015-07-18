import scala.annotation.tailrec
import scala.collection.immutable
import scala.collection.mutable

class VM {

	// The stack where all the operations take place
	var stack = mutable.Stack[StackEntry]()

	def add(): Unit = stack.push(stack.pop + stack.pop)
	def subtract(): Unit = stack.push(stack.pop - stack.pop)
	def equal(): Unit = stack.push(new StackEntry("bool", stack.pop == stack.pop))
	def data(stackEntry: StackEntry): Unit = stack.push(stackEntry)
	def nop(): Unit = {}
	def skip(amount: Int): Int = amount

	def executeOnce(programEntry: ProgramEntry): Int = {
		var toSkip = 0
		programEntry match {
			case e: ProgramEntry.nop => this.nop()
			case e: ProgramEntry.add => this.add()
			case e: ProgramEntry.sub => this.subtract()
			case e: ProgramEntry.equal => this.equal()
			case e: ProgramEntry.data => this.data(e.data)
			case e: ProgramEntry.skip => toSkip = this.skip(e.instructions)
		}
		toSkip
	}

	@tailrec
	private def executeInternal(program: immutable.Queue[ProgramEntry]): Unit = {
		if (program.isEmpty) return
		executeInternal(program.tail.slice(executeOnce(program.head), program.tail.length))
	}

	def execute(program: immutable.Queue[ProgramEntry]): Unit = {
		executeInternal(program)
	}


}
