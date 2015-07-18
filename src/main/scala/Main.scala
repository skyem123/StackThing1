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

	test.stack.push(new StackEntry("int32", 1))
	test.stack.push(new StackEntry("int32", 1))
	test.add()
	println(test.stack)
	test.stack.clear()

	var program = Queue[ProgramEntry](
		new ProgramEntry.data(new StackEntry("int32", 4)),
		new ProgramEntry.data(new StackEntry("int32", 2)),
		new ProgramEntry.skip(1),
		new ProgramEntry.add,
		new ProgramEntry.add
	)
	println(program)
	test.execute(program)
	println(test.stack)
}
