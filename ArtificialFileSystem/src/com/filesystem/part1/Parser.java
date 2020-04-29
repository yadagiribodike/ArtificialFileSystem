package com.filesystem.part1;

import java.io.File;
import java.util.Scanner;

/**
 * @author yadhi
 *
 */
public class Parser {

	private Builder builder;

	public Parser(Builder builder) {
		this.builder = builder;
	}

	public FileSystem getFileSystemDisk() {
		return builder.getFileSystemDisk();
	}

	public void buildFileSystem(String commandFile) {
		String string;
		try (Scanner sc = new Scanner(new File(commandFile))) {				//Takes input from the file that is given by client
			while (sc.hasNextLine() && (string = sc.nextLine()) != null) {	//Reading contents of the file line by line
				String command[] = string.split(" ", 2);					
				String commandName = command[0].toLowerCase();

				System.out.println(builder.getFileSystemDisk().pwd() + string);  //Printing the command in the present working directory
				
				switch (commandName) {
					case "cd": {
						builder.cd(command[1]);									//Calling cd method of ConcreteBuilder
						break;
					}
					case "mkdir": {
						builder.mkdir(command[1]);								//Calling mkdir method of ConcreteBuilder
						break;
					}
					case "create": {
						String fileName = command[1].split(" ")[0];
						long size = Long.parseLong(command[1].split(" ")[1]);
						builder.create(fileName, size);							//Calling create method of ConcreteBuilder
						break;
					}
					case "del": {
						builder.del(command[1]);								//Calling del method of ConcreteBuilder
						break;
					}
					case "ls": {
						if (command.length > 1) {
							builder.ls(command[1]);								//Calling ls method of ConcreteBuilder
						} else {
							builder.ls();
						}
						break;
					}
					case "size": {
						builder.size(command[1]);								//Calling size method of ConcreteBuilder
						break;
					}
					case "resize": {
						String fileName = command[1].split(" ")[0];
						long size = Long.parseLong(command[1].split(" ")[1]);
						builder.resize(fileName, size);							//Calling resize method of ConcreteBuilder
						break;
					}
					case "exit": {
						builder.exit();											//Calling exit method of ConcreteBuilder
						break;
					}
					default:
						System.out.println("Command is not defined. Please enter a proper command.");
				}
			}
			sc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
