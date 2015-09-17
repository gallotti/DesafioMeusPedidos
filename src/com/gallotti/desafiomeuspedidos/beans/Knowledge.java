package com.gallotti.desafiomeuspedidos.beans;

public class Knowledge {

	private String name,type;
	private int score = 0;
	

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "Knowledge : " + getName() + "\nScore : " + getScore();
	}
}
