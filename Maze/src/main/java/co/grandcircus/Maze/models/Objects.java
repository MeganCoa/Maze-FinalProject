package co.grandcircus.Maze.models;

import java.util.List;

public class Objects {
private List<Assets> assets;

public List<Assets> getAssets() {
	return assets;
}

public void setAssets(List<Assets> assets) {
	this.assets = assets;
}

public Objects(List<Assets> assets) {
	super();
	this.assets = assets;
}
public Objects() {

}

}
