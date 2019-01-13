# Tennis Scoring Challenge

This is a simple Java program that scores a Tennis game to the specification of https://github.com/DiUS/coding-tests/blob/master/dius_tennis.md

## Getting Started

### Prerequisites

Please ensure you have v10.0.2 JDK or equivalent installed on your machine to compile and run this project. You may also need to point your CLASSPATH to lib/ to get JUnit working.

## Example Input/Output

Interact with the program via something like JShell.

~~~
Match match = new Match("player 1", "player 2");
match.pointWonBy("player 1");
match.pointWonBy("player 2");
// this will return "0-0, 15-15"
match.score();

match.pointWonBy("player 1");
match.pointWonBy("player 1");
// this will return "0-0, 40-15"
match.score();

match.pointWonBy("player 2");
match.pointWonBy("player 2");
// this will return "0-0, Deuce"
match.score();

match.pointWonBy("player 1");
// this will return "0-0, Advantage player 1"
match.score();

match.pointWonBy("player 1");
// this will return "1-0, 0-0"
match.score();
~~~
