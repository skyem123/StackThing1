package uk.co.skyem.random.stack1

import uk.co.skyem.random.stack1.VM.Program

import scala.annotation.tailrec
import scala.collection.{immutable, mutable}

object VM {
	type Stack = mutable.Stack[StackEntry]
	type Program = mutable.Queue[ProgramEntry]
}

class VM {

	// The stack where all the operations take place
	var stack = mutable.Stack[StackEntry]()
	// another stack used for the call opcode
	var callStack = mutable.Stack[StackEntry]()

	private def executeOnce(program: Program): Program = {
		var toSkip = 0
		val instruction = program.head
		println(s"Instruction $instruction")
		toSkip = instruction(stack, program, callStack)
		program.tail.slice(toSkip, program.tail.length)
	}

	@tailrec
	private def executeInternal(program: Program): Unit = {
		if (program.isEmpty) return
		executeInternal(executeOnce(program))
	}

	def <<< (program: Seq[ProgramEntry]) : VM = {
		executeInternal(new Program ++= program)
		this
	}


	def << (program: ProgramEntry*) : VM = {
		executeInternal(new Program ++= program)
		this
	}

}
