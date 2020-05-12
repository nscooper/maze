package com.nscooper.maze;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources", glue = "com.nscooper.maze.stepdefs")
public class TestRunner_MazeTest {

}
