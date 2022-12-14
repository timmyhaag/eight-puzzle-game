# Eight Puzzle Game
Search algorithms are implemented to solve the eight-puzzle game. The eight-puzzle game will have an initial state and goal state using a 3 x 3 panel of number 1-8. The blank tile for the game will be represented with 0 and can legally move to the left, right, up, and down. To solve the game, the depth-first algorithm and best-first-search algorithm will be implemented to detail their own solution to the problem. Depth-first focuses on examining the children and descendants of a state before examining the state’s siblings. Best-first-search will utilize lists like depth-first and use a heuristic estimate to order states in the open list. It is implemented with a priority queue and provides more flexibility as an algorithm. The heuristics included in the implementation are the number of tiles out of place, sum of distance out of place, and the number of direct tile of reversals.
