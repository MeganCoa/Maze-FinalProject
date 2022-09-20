// Original JavaScript code by Chirp Internet: chirpinternet.eu
// Please acknowledge use of this code by including this header.

function Position(x, y, direction) {
  this.x = x;
  this.y = y;
  this.direction = direction;
  
}

Position.prototype.toString = function() {
  return this.x + ":" + this.y + ":" + this.direction;
};

function Mazing() {
	
	this.mazegrid = [
			[0, 1, 3, 0, 0],
			[0, 1, 0, 0, 0],
			[0, 1, 1, 0, 0],
			[0, 1, 0, 0, 0],
			[0, 2, 0, 0, 0],
		];


  this.maze = this.mazegrid;
  this.heroPos = {};
//  this.heroHasKey = true;
  this.childMode = false;

  
  
  

  for(i=0; i < this.mazegrid.length; i++) {
    for(j=0; j < this.mazegrid[0].length; j++) {
      var el =  this.mazegrid[i][j];
      this.maze[new Position(i, j, "none")] = el;
      if(maze[i][j] == 2) {
        // place hero at entrance
        this.heroPos = new Position(i, j, "north");
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

	let nextStep = this.maze[pos.x][pos.y];

  if(nextStep === 0) {
    return;
  }
  if(nextStep === 3) {
    
    this.heroWins();
    
  }

  // move hero one step
  this.heroPos = pos;

  if(nextStep === 3) {
    return;
  }
};

Mazing.prototype.mazeKeyPressHandler = function(e) {
  var tryPos = new Position(this.heroPos.x, this.heroPos.y, this.heroPos.direction);
  switch(e.keyCode)
  {
    case 37: // left
    	if (this.heroPos.direction == 'north') {
			tryPos.direction = "west";
		}
		if (this.heroPos.direction == 'west') {
			tryPos.direction = "south";
		}
		if (this.heroPos.direction == 'south') {
			tryPos.direction = "east";
		}
		if (this.heroPos.direction == 'east') {
			tryPos.direction = "north";
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
			tryPos.direction = "east";
		}
		if (this.heroPos.direction == 'east') {
			tryPos.direction = "south";
		}
		if (this.heroPos.direction == 'south') {
			tryPos.direction = "west";
		}
		if (this.heroPos.direction == 'west') {
			tryPos.direction = "north";
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
		
		
		//after he moves, it should do a function that creates his view
		//first checks his direction
		//then loops through the relevant grid
		//creates two boolean arrays, one for blocks, one for goal
		//in the html, these booleans go in order for the images of the blocks
  }
  this.tryMoveHero(tryPos);
  this.setPOV(this.heroPos);
  e.preventDefault();
};

Mazing.prototype.POVdisplay = function(showBlock, showGoal) {
	document.getElementById('stat').style.display = 'none';
	document.getElementById('stat').style.display = 'none';
	document.getElementById('stat').style.display = 'none';
	document.getElementById('stat').style.display = 'none';
	document.getElementById('stat').style.display = 'none';
	document.getElementById('stat').style.display = 'none';
	document.getElementById('stat').style.display = 'none';
	document.getElementById('stat').style.display = 'none';
	document.getElementById('stat').style.display = 'none';
}

Mazing.prototype.setPOV = function(heroPos) {
	let showBlock = [];
	let showGoal = [];
	
	switch (hero.direction) {
	

		case "north": //4 rows, 3 columns
			{
				for (let i = hero.row; i > hero.row - 4; i--) {
				
					for (let j = hero.column - 1; j < hero.column + 2; j++) {
						if (this.mazegrid[i][j] !== undefined) {
							if (mazegrid[i][j] === 0) {
								showBlock.push(true);
							} else {
								showBlock.push(false);
							}
							if (mazegrid[i][j] === 3) {
								showGoal.push(true);
							} else {
								showGoal.push(false);
							}
						}
					}
				}
				break;
			}
		case "south": //4 rows, 3 columns
			{
				for (let i = hero.row; i < hero.row + 4; i++) {
					for (let j = hero.column + 1; j > hero.column - 2; j--) {
						if (this.mazegrid[i][j] !== undefined) {
							if (mazegrid[i][j] === 0) {
								showBlock.push(true);
							} else {
								showBlock.push(false);
							}
							if (mazegrid[i][j] === 3) {
								showGoal.push(true);
							} else {
								showGoal.push(false);
							}
						}
					}
				}
				break;	
			}
			
		case "east":
			{
				for (let i = hero.row - 1; i < hero.row + 2; i++) {
					for (let j = hero.column; j < hero.column + 4; j++) {
						if (this.mazegrid[i][j] !== undefined) {
							if (mazegrid[i][j] === 0) {
								showBlock.push(true);
							} else {
								showBlock.push(false);
							}
							if (mazegrid[i][j] === 3) {
								showGoal.push(true);
							} else {
								showGoal.push(false);
							}
						}
					}
				}
				break;
			}
	
		case "west":
		{
			for (let i = hero.row + 1; i < hero.row - 2; i--) {
				for (let j = hero.column; j < hero.column - 4; j--) {
					if (this.mazegrid[i][j] !== undefined) {
							if (mazegrid[i][j] === 0) {
								showBlock.push(true);
							} else {
								showBlock.push(false);
							}
							if (mazegrid[i][j] === 3) {
								showGoal.push(true);
							} else {
								showGoal.push(false);
							}
						}
				}
			}
			break;
		}
		default:
			break;
	}
}

Mazing.prototype.setChildMode = function() {
  this.childMode = true;
  this.heroScore = 0;
  this.setMessage("collect all the treasure");
};