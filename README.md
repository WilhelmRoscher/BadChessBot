# BadChessBot

This is a chess bot using the Minimax-Algorithm. In the future there will be different bots using different Algorithms.

## Why is it bad?
* horribly inefficient implementation
     * RAM-Usage for calculating 3 moves ahead: 10GB
* no chess specific optimizations

## Todos
* more efficient implementation - It should be possible to calculate 5 moves ahead.
* Unit Tests with JUnit
* implement a proper Game Class to handle matches
* enable Human vs Bot games

## Motivation
It will likely never become a "GoodChessBot", but thats not the goal. My objective for this project is to learn more about the Java-Language and the concepts of object oriented programming.

## Future Perspectives
* enable GUI
* write different chess bots and let them play against each other
     * Alpa-Beta-Algorithm
     * Artificial Intelligence Bots (using Games against each other for training)
* connect to LiChess via the Bot API
