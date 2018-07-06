package cn.yel.blockchain.impl;

import java.util.Date;

import cn.yel.blockchain.utils.StringUtil;

public class Block {
	public String hash;//signature
	public String previousHash;
	private String data;
	private long timeStamp;
	private int nonce; //nonce为挖掘数据的难度，难度随链的增长而增长
	public Block(String data, String timeStamp) {
		super();
		this.data = data;
		this.previousHash = previousHash;
		this.timeStamp = new Date().getTime();
		this.hash = calculateHash();
	}
	
	public String calculateHash() {
		String calculatedhash = StringUtil.applySha256(previousHash+Long.toString(timeStamp)+Integer.toString(nonce)+data);
		return calculatedhash;
	}
	
	//
	public void mineBlock(int difficulty) {
		//-----------------------------------------------------------------
		//共识代码块，如何mine block的方式
		String target = new String(new char[difficulty]).replace('\0', '0'); //Create a string with difficulty * "0" 
		
		while(!hash.substring( 0, difficulty).equals(target)) {
			nonce ++;
			hash = calculateHash();
		}
		//------------------------------------------------------------------
		System.out.println("Block Mined!!! : " + hash);
	}

}
