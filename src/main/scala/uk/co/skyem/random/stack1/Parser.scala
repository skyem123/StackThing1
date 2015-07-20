package uk.co.skyem.random.stack1

abstract class Parser {
	
}

object ByteParser extends Parser {
	def parseBytes(bytes: TraversableOnce[Byte]) = {

	}
}

object StringParser extends Parser {
	def parseString(string: String) = {
		// TODO: Make it work with string values (don't count ; in a string as a seperator somehow)
		string.split(";|\\n").foreach((raw_statement) => {
			var statement = raw_statement.trim
			if (!statement.isEmpty) {
				println(statement)
			}
		})
	}

}
