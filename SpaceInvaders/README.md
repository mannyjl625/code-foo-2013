Space Invaders
=========
	
http://www.jsedlacek.info/~mjl288/code-foo-2013/SpaceInvaders/index.html

Controls
-----

Use left and right arrow keys to move ship left and right

Use F to fire

Controls were only tested on firefox


Summary
-----
Sadly, I was unable to get Space Invaders to a functional form, but figured showing something was better then nothing, and I could at least talk 
about where I was going with it, as well as where I went wrong and what steps I could take to make it functional.

I had attempted to make laser shot objects at the press of a button, but to prevent the user from generating as many laser shots as they wanted
I prebuild 5 laser objects, stored them in an array, and would cycle through them when the user hit. The laser shot be waiting, hidden under the 
ship sprite, and travel up the screen when the user fired. When the object passed the bounds of the canvas, the lasers position is reset back to
the ship. This method of limiting fire ended up shooting me in the foot, as for reasons I haven't had time to figure out, a strange bug of multiple
resetting shots occurs when the user fires multiple shots.



