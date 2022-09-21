// Original JavaScript code by Chirp Internet: chirpinternet.eu
// Please acknowledge use of this code by including this header.

function onGeneratePOVMaze(){
	
	var maze = new Mazing();
}

function Position(x, y, direction) {
  this.x = x;
  this.y = y;
  this.direction = direction;
  
}

Position.prototype.toString = function() {
  return this.x + ":" + this.y + ":" + this.direction;
};

function Mazing(mazegrid) {
	
	this.mazegrid = mazegrid;
	


  this.maze = this.mazegrid;
  this.heroPos = {};
//  this.heroHasKey = true;
  this.childMode = false;

  for(i=0; i < this.mazegrid.length; i++) {
	
    for(j=0; j < this.mazegrid[0].length; j++) {
      
      if(this.mazegrid[i][j] === 2) {
		
        // place hero at entrance
        this.heroPos = new Position(i, j, "north");
        this.setPOV(this.heroPos);
        
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
  
};

Mazing.prototype.heroWins = function() {
	
	alert('you won!');
  this.gameOver("you finished !!!");
};

Mazing.prototype.tryMoveHero = function(pos) {


	let nextStep = this.mazegrid[pos.x][pos.y];

  if(nextStep === 0) {
    return;
  }
  if(nextStep === 3) {
    alert("You won!")
    
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
	let blocks = [];
	blocks.push('0L');
	blocks.push('blank');
	blocks.push('0R');
	blocks.push('1L');
	blocks.push('1');
	blocks.push('1R');
	blocks.push('2L');
	blocks.push('2');
	blocks.push('2R');
	blocks.push('3L');
	blocks.push('3');
	blocks.push('3R');
	blocks.push('4L');
	blocks.push('4');
	blocks.push('4R');
	
	for (let i = 0; i < blocks.length; i++) {
		if (blocks[i] != 'blank') {
			if (showBlock[i]) {
				document.getElementById(blocks[i]).style.display = 'block';
			} else {
				document.getElementById(blocks[i]).style.display = 'none';
			}
		}
	}
	
	let goals = [];
	goals.push('0LG');
	goals.push('blank');
	goals.push('0RG');
	goals.push('1LG');
	goals.push('1G');
	goals.push('1RG');
	goals.push('2LG');
	goals.push('2G');
	goals.push('2RG');
	goals.push('3LG');
	goals.push('3G');
	goals.push('3RG');
	goals.push('4LG');
	goals.push('4G');
	goals.push('4RG');

	for (let i = 0; i < goals.length; i++) {
		if (goals[i] != 'blank') {
			if (showGoal[i]) {
				document.getElementById(goals[i]).style.display = 'block';
			} else {
				document.getElementById(goals[i]).style.display = 'none';
			}
		}	
	}
	
}

Mazing.prototype.setPOV = function(heroPos) {
	let showBlock = [];
	let showGoal = [];
	
	
	switch (heroPos.direction) {
	
		
		case "north": //4 rows, 3 columns
			{
				for (let i = heroPos.x; i > heroPos.x - 5; i--) {
					for (let j = heroPos.y - 1; j < heroPos.y + 2; j++) {
						
						if (this.mazegrid[i] !== undefined && this.mazegrid[i][j] !== undefined) {
							if (this.mazegrid[i][j] === 0) {
								showBlock.push(true);
							} else {
								showBlock.push(false);
							}
							if (this.mazegrid[i][j] === 3) {
								showGoal.push(true);
							} else {
								showGoal.push(false);
							}
						} else {
							showBlock.push(true);
							showGoal.push(false);
						}
					}
				}
				break;
			}
		case "south": //4 rows, 3 columns
			{
				for (let i = heroPos.x; i < heroPos.x + 5; i++) {
					for (let j = heroPos.y + 1; j > heroPos.y - 2; j--) {
						if (this.mazegrid[i] !== undefined && this.mazegrid[i][j] !== undefined) {
							if (this.mazegrid[i][j] === 0) {
								showBlock.push(true);
							} else {
								showBlock.push(false);
							}
							if (this.mazegrid[i][j] === 3) {
								showGoal.push(true);
							} else {
								showGoal.push(false);
							}
						} else {
							showBlock.push(true);
							showGoal.push(false);
						}
					}
				}
				break;	
			}
			
		case "east":
			{
				for (let j = heroPos.y; j < heroPos.y + 5; j++) {
					for (let i = heroPos.x - 1; i < heroPos.x + 2; i++) {
						if (this.mazegrid[i] !== undefined && this.mazegrid[i][j] !== undefined) {
							if (this.mazegrid[i][j] === 0) {
								showBlock.push(true);
								
							} else {
								showBlock.push(false);
							}
							if (this.mazegrid[i][j] === 3) {
								showGoal.push(true);
							} else {
								showGoal.push(false);
							}
						} else {
							showBlock.push(true);
							showGoal.push(false);
						}
					}
				}
				
				break;
			}
	
		case "west":
		{
			for (let j = heroPos.y; j > heroPos.y - 5; j--) {
				for (let i = heroPos.x + 1; i > heroPos.x - 2; i--) {
					if (this.mazegrid[i] !== undefined && this.mazegrid[i][j] !== undefined) {
							if (this.mazegrid[i][j] === 0) {
								showBlock.push(true);
							} else {
								showBlock.push(false);
							}
							if (this.mazegrid[i][j] === 3) {
								showGoal.push(true);
							} else {
								showGoal.push(false);
							}
						} else {
							showBlock.push(true);
							showGoal.push(false);
						}
				}
			}
			break;
		}
		default:
		
			break;
	}
	
	this.POVdisplay(showBlock, showGoal);
}

Mazing.prototype.setChildMode = function() {
  this.childMode = true;
  this.heroScore = 0;
  this.setMessage("collect all the treasure");
};