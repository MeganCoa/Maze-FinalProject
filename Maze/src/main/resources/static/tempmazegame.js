// Original JavaScript code by Chirp Internet: chirpinternet.eu
// Please acknowledge use of this code by including this header.
function onGenerateMaze(mazegrid){
	let width = document.getElementById("field_width").value;
	let height = document.getElementById("field_height").value;
	
	 //let Maze = new MazeBuilder( Number (width), Number (height));
	  //Maze.placeKey();
	  //Maze.display("maze_container");
	  //var maze = new Mazing("maze");
	  alert("Hey");

}

class MazeBuilder {

  constructor(width, height) {

    this.width = width;
    this.height = height;

    this.cols = 2 * this.width + 1;
    this.rows = 2 * this.height + 1;

    this.maze = this.initArray([]);

    // place initial walls
    this.maze.forEach((row, r) => {
      row.forEach((cell, c) => {
        switch(r)
        {
          case 0:
          case this.rows - 1:
            this.maze[r][c] = ["wall"];
            break;

          default:
            if((r % 2) == 1) {
              if((c == 0) || (c == this.cols - 1)) {
                this.maze[r][c] = ["wall"];
              }
            } else if(c % 2 == 0) {
              this.maze[r][c] = ["wall"];
            }

        }
      });

      if(r == 0) {
        // place exit in top row
        let doorPos = this.posToSpace(this.rand(1, this.width));
        this.maze[r][doorPos] = ["door", "exit"];
      }

      if(r == this.rows - 1) {
        // place entrance in bottom row
        let doorPos = this.posToSpace(this.rand(1, this.width));
        this.maze[r][doorPos] = ["door", "entrance"];
      }

    });

    // start partitioning
    this.partition(1, this.height - 1, 1, this.width - 1);
  }

  initArray(value) {
    return new Array(this.rows).fill().map(() => new Array(this.cols).fill(value));
  }

  rand(min, max) {
    return min + Math.floor(Math.random() * (1 + max - min));
  }

  posToSpace(x) {
    return 2 * (x-1) + 1;
  }

  posToWall(x) {
    return 2 * x;
  }

  inBounds(r, c) {
    if((typeof this.maze[r] == "undefined") || (typeof this.maze[r][c] == "undefined")) {
      return false; // out of bounds
    }
    return true;
  }

  shuffle(array) {
    // sauce: https://stackoverflow.com/a/12646864
    for(let i = array.length - 1; i > 0; i--) {
      const j = Math.floor(Math.random() * (i + 1));
      [array[i], array[j]] = [array[j], array[i]];
    }
    return array;
  }

  
  isGap(...cells) {
    return cells.every((array) => {
      let row, col;
      [row, col] = array;
      if(this.maze[row][col].length > 0) {
        if(!this.maze[row][col].includes("door")) {
          return false;
        }
      }
      return true;
    });
  }

 

  display(id) {

    this.parentDiv = document.getElementById(id);

    if(!this.parentDiv) {
      alert("Cannot initialise maze - no element found with id \"" + id + "\"");
      return false;
    }

    while(this.parentDiv.firstChild) {
      this.parentDiv.removeChild(this.parentDiv.firstChild);
    }

    const container = document.createElement("div");
    container.id = "maze";
    //container.dataset.steps = this.totalSteps;

    this.maze.forEach((row) => {
      let rowDiv = document.createElement("div");
      row.forEach((cell) => {
        let cellDiv = document.createElement("div");
        if(cell) {
          cellDiv.className = cell.join(" ");
        }
        rowDiv.appendChild(cellDiv);
      });
      container.appendChild(rowDiv);
    });

    this.parentDiv.appendChild(container);

    return true;
  }

}
