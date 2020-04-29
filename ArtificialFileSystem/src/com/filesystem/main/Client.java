package com.filesystem.main;



import com.filesystem.part1.Builder;
import com.filesystem.part1.Parser;
import com.filesystem.part1.ConcreteBuilder;
import com.filesystem.part1.FileSystem;

/**
 * @author yadhi
 *
 */
public class Client {

	public static void main(String[] args) {
		Builder builder = new ConcreteBuilder();							//object for builder
		Parser inputparser = new Parser(builder);							//passing builder object to parser to look for concrete methods
		inputparser.buildFileSystem("script2.txt");							//passing input file to the parser to build the file system
		FileSystem artificialFileSystem=inputparser.getFileSystemDisk();	//For any further use							
	}
}
