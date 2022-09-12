package co.grandcircus.Maze.IconSearch;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Assets {
@JsonProperty("cleaned")
private Integer cleaned;
@JsonProperty("size")
private Integer size;
@JsonProperty("url")
private String url;


public Integer getCleaned() {
	return cleaned;
}
public void setCleaned(Integer cleaned) {
	this.cleaned = cleaned;
}
public Integer getSize() {
	return size;
}
public void setSize(Integer size) {
	this.size = size;
}
public String getUrl() {
	return url;
}
public void setUrl(String url) {
	this.url = url;
}

public Assets(Integer cleaned, Integer size, String url) {
	super();
	this.cleaned = cleaned;
	this.size = size;
	this.url = url;
}
public Assets() {
	
}


}
