Feature: Maze

Scenario: As a world famous explorer of Mazes I would like a maze to exist so that I can navigate it
	Given Given a maze the explorer should be able to drop in to the Start point (facing north)
	Then An explorer on a maze must be able to move forward
	And An explorer on a maze must be able to turn left and right (changing direction the explorer is facing)
	And An explorer on a maze must be able to declare what is in front of them
	And An explorer on a maze must be able to declare all movement options from their given location
	And An explorer on a maze must be able to report a record of where they have been in an understandable fashion
	