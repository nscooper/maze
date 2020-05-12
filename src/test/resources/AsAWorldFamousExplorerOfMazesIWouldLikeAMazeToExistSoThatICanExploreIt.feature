Feature: Maze

Scenario: As a world famous explorer of Mazes I would like a maze to exist so that I can explore it
	Given A Maze
	When a Maze is populated by a new MazeFile
	Then the Maze should have walls of "X"
	And empty spaces of " "
	And one 1 start point of "S"
	And only 1 Finish Point of "F"
