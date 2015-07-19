package uk.co.skyem.random.stack1

import uk.co.skyem.random.stack1.StackEntry._
import uk.co.skyem.random.stack1.VM.{Stack, Program}

abstract class ProgramEntry {
	override def toString: String = s"ProgramEntry.${this.getClass.getSimpleName}"
	def apply(stack: Stack, program: Program, callStack: Stack): Int = apply(stack, program)
	protected def apply(stack: Stack, program: Program): Int = apply(stack)
	protected def apply(stack: Stack): Int = apply()
	protected def apply() : Int = 0
}

object ProgramEntry {

	def data(entry: StackEntry) = new data(entry)
	def skip(amount: Int)       = new skip(amount)
	def nop   = new nop
	def add   = new add
	def sub   = new sub
	def so    = new so
	def eq    = new eq
	def save  = new save
	def load  = new load
	def call  = new call
	def dup   = new dup
	def ret   = new ret
	def swp   = new swp
	def del   = new del
	def clear = new clear
	def swcs  = new swcs
	def cfcs  = new cfcs
	def ptcs  = new ptcs
	def dfcs  = new dfcs

	def replaceProgram(program: Program, replace: Program) = {
		program.clear()
		program ++= nop +: replace
	}

	class nop extends ProgramEntry

	class add extends ProgramEntry {
		override def apply(stack: Stack) = {stack.push(stack.pop + stack.pop); 0}
	}
	class sub extends ProgramEntry {
		override def apply(stack: Stack) = {stack.push(stack.pop - stack.pop); 0}
	}
	class eq extends ProgramEntry {
		override def apply(stack: Stack) = {stack.push(new StackEntry("bool", stack.pop == stack.pop)); 0}
	}
	class data(val entry: StackEntry) extends ProgramEntry {
		override def toString: String = super.toString + s"($entry)"
		override def apply(stack : Stack) = {stack.push(entry);0}
	}
	class skip(val number: Int) extends ProgramEntry {
		override def toString: String = super.toString + s"($number)"
		override def apply() = number
	}
	class so extends ProgramEntry {
		override def apply(stack: Stack) = if (stack.pop().getData.asInstanceOf[Boolean]) 1 else 0
	}
	class save extends ProgramEntry {
		override def apply(stack: Stack, program: Program) = {stack.push(new StackEntry("programPosition", program.tail.tail)); 0}
	}
	class load extends ProgramEntry {
		override def apply(stack: Stack, program: Program) = {
			replaceProgram(program, stack.pop().getData.asInstanceOf[Program])
		0}
	}
	class call extends ProgramEntry {
		override def apply(stack: Stack, program: Program, callStack: Stack) = {
			callStack.push(stackEntry("programPosition", program.tail))
			println(stack)
			println(callStack)
			replaceProgram(program, stack.pop().getData.asInstanceOf[Program])
		0}
	}
	class dup extends ProgramEntry {
		override def apply(stack: Stack) = {stack.push(stack.head); 0}
	}
	class ret extends ProgramEntry {
		override def apply(stack: Stack, program: Program, callStack: Stack) = {
			replaceProgram(program, callStack.pop().getData.asInstanceOf[Program])
		0}
	}
	class swp extends ProgramEntry {
		override def apply(stack: Stack) = {
			val a = stack.pop()
			val b = stack.pop()
			stack.push(a)
			stack.push(b)
		0}
	}
	class del extends ProgramEntry {
		override def apply(stack: Stack) = {
			stack.pop()
		0}
	}
	class clear extends ProgramEntry {
		override def apply(stack: Stack) = {
			stack.clear()
		0}
	}
	// Swap With Call Stack
	class swcs extends ProgramEntry {
		override def apply(stack: Stack, program: Program, callStack: Stack) = {
			callStack.push(stack.pop())
			stack.push(callStack.pop())
			0}
	}
	// Copy From Call Stack
	class cfcs extends ProgramEntry {
		override def apply(stack: Stack, program: Program, callStack: Stack) = {
			stack.push(callStack.head)
		0}
	}
	// Pop To Call Stack
	class ptcs extends ProgramEntry {
		override def apply(stack: Stack, program: Program, callStack: Stack) = {
			callStack.push(stack.pop())
			0}
	}
	// Delete From Call Stack
	class dfcs extends ProgramEntry {
		override def apply(stack: Stack, program: Program, callStack: Stack) = {
			callStack.pop()
			0}
	}
}

