import ProgramEntry._
import StackEntry._

import scala.collection.immutable.Queue

/**
 * Main entry point of the program
 */
object Main extends App {
	val test = new VM()
	/*
	println(test.stack)
	test.stack.push(new StackEntry("32int", 42))
	test.stack.push(new StackEntry("32int", 42))
	println(test.stack)
	test.add()
	println(test.stack)
	test.stack.push(new StackEntry("32int", 84))
	println(test.stack)
	test.equal()
	println(test.stack)
	*/

	test.stack.push(stackEntry("int32", 1))
	test.stack.push(stackEntry("int32", 1))
	test.add()
	println(test.stack)
	test.stack.clear()

	var program = Queue[ProgramEntry](
		data(stackEntry("int32", 4)),
		data(stackEntry("int32", 2)),
		add,
		data(stackEntry("int32", 6)),
		equal,
		then,
		skip(1),
			data(stackEntry("int32", 42))

	)
	println(program)
	test(program)
	println(test.stack)
}
