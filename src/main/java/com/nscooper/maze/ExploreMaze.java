package com.nscooper.maze;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.jboss.logging.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableMap;
import com.nscooper.maze.capture.MazeFileLoader;
import com.nscooper.maze.exceptions.InvalidMazeConfigException;

import net.logstash.logback.marker.Markers;

public class ExploreMaze {

	private static final Logger log = LoggerFactory.getLogger(ExploreMaze.class);

	public static void main(String[] args) {
		MDC.put("RunID", UUID.randomUUID());
		
		log.info(
				Markers.appendEntries( 
				ImmutableMap.builder().putAll(System.getProperties()).build()),
				"Welcome to Maze Explorer, from Nick Cooper");
		
		if (args.length<1 || args.length>1) {
			log.error("1st and only argument must be maze file path!");
			System.exit(-1);
		}

		List<String> coordinates = null;
		try {
			coordinates = new Explorer().exploreMaze(MazeFileLoader.loadMazeIntoTable(args[0]));
			log.info("These are the sequence of coordinates, from the start to the exit point of the submitted maze");
			coordinates.forEach(c -> log.info(c));
			log.info("Finish point found in {} steps", coordinates.size());
		} catch (IOException | InvalidMazeConfigException e) {
			log.error(e.getLocalizedMessage(), e);
		}
	}
}
