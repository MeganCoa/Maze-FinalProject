package co.grandcircus.Maze.IconSearch;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IconSearchResponse {
@JsonProperty("data")
private Data data;
@JsonProperty("meta")
private Meta meta;

public Data getData() {
	return data;
}

public Meta getMeta() {
	return meta;
}

public void setMeta(Meta meta) {
	this.meta = meta;
}

public void setData(Data data) {
	this.data = data;
}


}
