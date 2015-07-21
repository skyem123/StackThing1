package uk.co.skyem.random.stack1

import scala.collection.mutable

abstract class Parser

object ByteParser extends Parser {
	def parseBytes(bytes: TraversableOnce[Byte]) = {

	}
}

object StringParser extends Parser {
	val STRING = "'.*'|\".*\"".r //TODO: Handle things like \"
	val STATEMENT_MARKER = ";".r
	val COMMENT_MARKER = "\\/\\/".r
	// Comments are in group 2
	val COMMENT_PATTERN = s"($STRING)|($COMMENT_MARKER.*)".r
	// end statement markers are in group 2
	val STATEMENT_SPLIT_PATTERN = s"($STRING)|($STATEMENT_MARKER)".r

	def parseString(string: String) = {
		string.split('\n').foreach((raw_line) => {
			// Now we have all the lines, remove all comments,
			val line = COMMENT_PATTERN.replaceAllIn(raw_line, "$1") + ';' // Replace everything matched with just the first group so the second group will be deleted
			//println(line)
			// then take this line without comments and split it up // TODO
			var positions = mutable.Buffer[Int]()
			STATEMENT_SPLIT_PATTERN.findAllMatchIn(line).foreach(m => {
				val position = m.start(2)
				if (position >= 0) {
					positions :+= position
				}
			})
			var statements = mutable.Buffer[String]()
			var lastPos = 0
			positions.foreach(position => {
				val statement = line.substring(lastPos, position).trim
				if (statement.nonEmpty) {
					statements :+= line.substring(lastPos, position).trim
				}
				lastPos = position + 1 // +1 gets rid of the semicolon
			})
			if (statements.nonEmpty) {
				statements.foreach(statement => {
					println(statement)
					// TODO: Convert statements into ProgramEntry objects
				})
			}
		})
	}

}
