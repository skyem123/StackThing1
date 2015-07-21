package uk.co.skyem.random.stack1

import scala.collection.mutable

abstract class Parser

object ByteParser extends Parser {
	def parseBytes(bytes: TraversableOnce[Byte]) = {

	}
}

object StringParser extends Parser {
	val STRING = "'.*'|\".*\"".r //TODO: Handle things like \"
	val STATEMENT_MARKER = ";"
	val COMMENT_MARKER = "\\/\\/".r
	// Comments are in group 2
	val COMMENT_PATTERN = s"($STRING)|($COMMENT_MARKER.*)".r
	// end statement markers are in group 2
	val LINE_SPLIT_PATTERN = s"($STRING)|($STATEMENT_MARKER)".r
	val STATEMENT_SPLIT_PATTERN = "\\s".r

	def parseString(string: String) = {
		string.split('\n').foreach((raw_line) => {
			// Now we have all the lines, remove all comments,
			val line = COMMENT_PATTERN.replaceAllIn(raw_line, "$1") + STATEMENT_MARKER // Replace everything matched with just the first group so the second group will be deleted
			//println(line)
			// then take this line without comments and split it up // TODO
			var positions = mutable.Buffer[Int]()
			LINE_SPLIT_PATTERN.findAllMatchIn(line).foreach(m => {
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
				lastPos = position + STATEMENT_MARKER.length // make sure to get rid of the semicolon as well
			})
			if (statements.nonEmpty) {
				statements.foreach(statement => {
					println(statement)
					// First split the statements up into parts
					val split_statment = STATEMENT_SPLIT_PATTERN.split(statement)
					val instruction = split_statment.head
					val arguments = split_statment.tail
					// TODO: Convert statements into ProgramEntry objects
				})
			}
		})
	}

}
