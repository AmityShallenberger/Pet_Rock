Who's doing what?
 - Yunk: Output.java , make sure something for TESTER, don't do output for random events, display() and getUserInput().
 - Amitree: randomEventGenerator(), divide updateStats into the setters, output for random events,
 - Bohungus: switch statement that replaces if-else in CONTROLLER, gameEndCheck().
 - Willard: checkSaveData() in the MODEL, polish-feed-play-updateMood, global variables.
 
 
Thoughts.

Why is updateMood() commented out in PetRockMain() line 82?

The switch-statement for userInput should be its own method.
The try-catch that writes to the current json file should be its
	own method.

Need to replace code in main with written methods.

Could use some more comments.