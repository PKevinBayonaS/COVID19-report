package com.COVID19.estadisticascoronavirus;

public class CovidDAO {
	private String state;
	private String contry;
	private int LatestCase;
	private int DiffCase;
	/**
	 * @return the diffCase
	 */
	public int getDiffCase() {
		return DiffCase;
	}
	/**
	 * @param diffCase the diffCase to set
	 */
	public void setDiffCase(int diffCase) {
		DiffCase = diffCase;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return the contry
	 */
	public String getContry() {
		return contry;
	}
	/**
	 * @param contry the contry to set
	 */
	public void setContry(String contry) {
		this.contry = contry;
	}
	/**
	 * @return the latestCase
	 */
	public int getLatestCase() {
		return LatestCase;
	}
	/**
	 * @param latestCase the latestCase to set
	 */
	public void setLatestCase(int latestCase) {
		LatestCase = latestCase;
	}
	@Override
	public String toString() {
		return "CovidDAO [state=" + state + ", contry=" + contry + ", LatestCase=" + LatestCase + "]";
	}
	
	

}
