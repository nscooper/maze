package com.nscooper.maze.capture;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableMap;
import com.nscooper.maze.Maze;
import com.nscooper.maze.exceptions.InvalidMazeConfigException;

import net.logstash.logback.marker.Markers;

public class MazeFileLoader {
	private static final Logger log = LoggerFactory.getLogger(MazeFileLoader.class);
	
	public static final Maze loadMazeIntoTable(String filePath) throws InvalidMazeConfigException, IOException{
		
		Path path = Paths.get(filePath);
		List<String> contents = Files.readAllLines(path);
		Maze maze = new Maze();

		int lineCount=0;
		int charCount=0;
		for(String line:contents) {
			for(char chr : line.toCharArray()) {
				maze.put(lineCount, charCount++, chr+"");
			}
			lineCount++;
			charCount=0;
		}

		log.info(Markers.appendEntries( 
				ImmutableMap.builder()
				.put("Input file path", path.toAbsolutePath())
				.put("Maze line count", lineCount+"")
				.build()),
				"Input file holding Maze successfully loaded");

		return maze;
	}
}