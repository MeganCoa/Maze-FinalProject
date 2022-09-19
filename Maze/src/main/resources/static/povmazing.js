// Original JavaScript code by Chirp Internet: chirpinternet.eu
// Please acknowledge use of this code by including this header.

function Position(x, y, z) {
  this.x = x;
  this.y = y;
  this.z = z;
  //z is direction: 0 = north, 1 = east, 2 = south, 3 = west
}

Position.prototype.toString = function() {
  return this.x + ":" + this.y + ":" + this.z;
};

function Mazing() {
	
	let mazegrid = [
			[0, 1, 3, 0, 0],
			[0, 1, 0, 0, 0],
			[0, 1, 1, 0, 0],
			[0, 1, 0, 0, 0],
			[0, 2, 0, 0, 0],
		];


  this.maze = mazegrid;
  this.heroPos = {};
//  this.heroHasKey = true;
  this.childMode = false;

  
  
  

  for(i=0; i < mazegrid.length; i++) {
    for(j=0; j < mazegrid[0].length; j++) {
      var el =  mazegrid[i][j];
      this.maze[new Position(i, j, 0)] = el;
      if(maze[i][j] == 2) {
        // place hero at entrance
        this.heroPos = new Position(i, j, 0);
      }
    }
  }

  // activate control keys
  this.keyPressHandler = this.mazeKeyPressHandler.bind(this);
  document.addEventListener("keydown", this.keyPressHandler, false);
};

Mazing.prototype.gameOver = function(text) {
  // de-activate control keys
  document.removeEventListener("keydown", this.keyPressHandler, false);
  this.setMessage(text);
};

Mazing.prototype.heroWins = function() {
  this.gameOver("you finished !!!");
};

Mazing.prototype.tryMoveHero = function(pos) {

  if("object" !== typeof this.maze[pos]) {
    return;
  }

  var nextStep = this.maze[pos].className;

  if(nextStep.match(/wall/)) {
    return;
  }
  if(nextStep.match(/exit/)) {
    
    this.heroWins();
    
  }

  // move hero one step
  this.maze[this.heroPos].classList.remove("hero");
  this.maze[pos].classList.add("hero");
  this.heroPos = pos;

  // after moving
  if(nextStep.match(/nubbin/)) {
    this.heroTakeTreasure();
    return;
  }
  if(nextStep.match(/key/)) {
    this.heroTakeKey();
    return;
  }
  if(nextStep.match(/exit/)) {
    return;
  }
  if(this.heroScore >= 1) {
    if(!this.childMode) {
      this.heroScore--;
    }
    if(!this.childMode && (this.heroScore <= 0)) {
      this.gameOver("sorry, you didn't make it");
    } else {
      this.setMessage("...");
    }
  }
};

Mazing.prototype.mazeKeyPressHandler = function(e) {
  var tryPos = new Position(this.heroPos.x, this.heroPos.y);
  switch(e.keyCode)
  {
    case 37: // left
    	if (this.heroPos.direction == 'north') {
			//set heroPos.direction to west
		}
		if (this.heroPos.direction == 'west') {
			//set heroPos.direction to south
		}
		if (this.heroPos.direction == 'south') {
			//set heroPos.direction to east
		}
		if (this.heroPos.direction == 'east') {
			//set heroPos.direction to north
		}
      break;

    case 38: // forward
    	if (this.heroPos.direction == 'north') {
			tryPos.x--;
		}
		if (this.heroPos.direction == 'west') {
			tryPos.y--;
		}
		if (this.heroPos.direction == 'south') {
			tryPos.x++;
		}
		if (this.heroPos.direction == 'east') {
			tryPos.y++;
		}
		break;
    case 39: // right
      if (this.heroPos.direction == 'north') {
			//set heroPos.direction to east
		}
		if (this.heroPos.direction == 'east') {
			//set heroPos.direction to south
		}
		if (this.heroPos.direction == 'south') {
			//set heroPos.direction to west
		}
		if (this.heroPos.direction == 'west') {
			//set heroPos.direction to north
		}
      break;

    case 40: // backward
    	if (this.heroPos.direction == 'north') {
			tryPos.x++;
		}
		if (this.heroPos.direction == 'west') {
			tryPos.y++;
		}
		if (this.heroPos.direction == 'south') {
			tryPos.x--;
		}
		if (this.heroPos.direction == 'east') {
			tryPos.y--;
		}
      break;

    default:
      return;

  }
  this.tryMoveHero(tryPos);
  e.preventDefault();
};

Mazing.prototype.setChildMode = function() {
  this.childMode = true;
  this.heroScore = 0;
  this.setMessage("collect all the treasure");
};