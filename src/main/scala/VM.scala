import scala.annotation.tailrec
import scala.collection.immutable
import scala.collection.mutable

class VM {

	// The stack where all the operations take place
	var stack = mutable.Stack[StackEntry]()

	def add(): Unit = stack.push(stack.pop + stack.pop)
	def sub(): Unit = stack.push(stack.pop - stack.pop)
	def equal(): Unit = stack.push(new StackEntry("bool", stack.pop == stack.pop))
	def data(stackEntry: StackEntry): Unit = stack.push(stackEntry)
	def nop(): Unit = {}
	def skip(amount: Int): Int = amount
	def so(): Int = if (stack.pop().getData.asInstanceOf[Boolean]) 1 else 0
	def save(currentProgram: immutable.Queue[ProgramEntry]): Unit = stack.push(new StackEntry("programPosition", currentProgram.tail.tail))
	def load(): immutable.Queue[ProgramEntry] = stack.pop().getData.asInstanceOf[immutable.Queue[ProgramEntry]]

	def executeOnce(currentProgram: immutable.Queue[ProgramEntry]): immutable.Queue[ProgramEntry] = {
		var toSkip = 0
		var program = currentProgram
		val instruction = program.head
		println(s"Instruction $instruction")
		instruction match {
			case e: ProgramEntry.nop => this.nop()
			case e: ProgramEntry.add => this.add()
			case e: ProgramEntry.sub => this.sub()
			case e: ProgramEntry.equal => this.equal()
			case e: ProgramEntry.data => this.data(e.data)
			case e: ProgramEntry.skip => toSkip = this.skip(e.instructions)
			case e: ProgramEntry.so => toSkip = this.so()
			case e: ProgramEntry.save => this.save(program)
			case e: ProgramEntry.load => program = ProgramEntry.nop +: this.load()
		}
		program.tail.slice(toSkip, program.tail.length)
	}

	@tailrec
	private def executeInternal(program: immutable.Queue[ProgramEntry]): Unit = {
		if (program.isEmpty) return
		executeInternal(executeOnce(program))
	}

	def apply(program: Seq[ProgramEntry]) = {
		executeInternal(immutable.Queue(program : _*))
	}

	/*
	def apply(program: ProgramEntry*): Unit = {
		executeInternal(immutable.Queue(program : _*))
	}
	*/


}
