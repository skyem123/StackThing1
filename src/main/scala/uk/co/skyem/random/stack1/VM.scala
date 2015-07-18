package uk.co.skyem.random.stack1

import StackEntry._
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

	private def executeOnce(currentProgram: immutable.Queue[ProgramEntry]): immutable.Queue[ProgramEntry] = {
		var toSkip = 0
		val program = new Program() ++ currentProgram
		//println(program)
		val instruction = program.head
		println(s"Instruction $instruction")
		toSkip = instruction(stack, program, callStack)
		//println(program)
		immutable.Queue[ProgramEntry]() ++ program.tail.slice(toSkip, program.tail.length)
	}

	@tailrec
	private def executeInternal(program: immutable.Queue[ProgramEntry]): Unit = {
		if (program.isEmpty) return
		executeInternal(executeOnce(program))
	}

	def <<< (program: Seq[ProgramEntry]) : VM = {
		executeInternal(immutable.Queue(program : _*))
		this
	}


	def << (program: ProgramEntry*) : VM = {
		executeInternal(immutable.Queue(program: _*))
		this
	}

}
