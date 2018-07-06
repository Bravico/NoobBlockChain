package cn.yel.blockchain.impl;

import java.util.ArrayList;

import com.google.gson.GsonBuilder;

public class Test1 {
	public void tryEasyBlock() {
		Block rootBlock = new Block("I am the first block", "0");
		System.out.println("Hash block 1 = " + rootBlock.hash);

		Block secondBlock = new Block("I am the second block", rootBlock.hash);
		System.out.println("Hash block 2 = " + secondBlock.hash);

		Block thirdBlock = new Block("Hey im the third block", secondBlock.hash);
		System.out.println("Hash block 3 = " + thirdBlock.hash);
	}

	public static ArrayList<Block> blockchain = new ArrayList<Block>();

	public void tryGsonBlock() {

		// add our blocks to the blockchain ArrayList:
		blockchain.add(new Block("Hi im the first block", "0"));
		blockchain.add(new Block("Yo im the second block", blockchain.get(blockchain.size() - 1).hash));
		blockchain.add(new Block("Hey im the third block", blockchain.get(blockchain.size() - 1).hash));

		String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
		System.out.println(blockchainJson);

	}
	//使用Proof of Work去保证后面一个区块的运力和服务的可用性以及不容易篡改性
	//验证blockchain是否是验证通过的链
	public static Boolean isChainValid() {
		Block currentBlock; 
		Block previousBlock;
		String hashTarget = new String(new char[difficulty]).replace('\0', '0');
		
		//loop through blockchain to check hashes:
		for(int i=1; i < blockchain.size(); i++) {
			currentBlock = blockchain.get(i);
			previousBlock = blockchain.get(i-1);
			//compare registered hash and calculated hash:
			if(!currentBlock.hash.equals(currentBlock.calculateHash()) ){
				System.out.println("Current Hashes not equal");			
				return false;
			}
			//compare previous hash and registered previous hash
			if(!previousBlock.hash.equals(currentBlock.previousHash) ) {
				System.out.println("Previous Hashes not equal");
				return false;
			}
			//check if hash is solved
			if(!currentBlock.hash.substring( 0, difficulty).equals(hashTarget)) {
				System.out.println("This block hasn't been mined");
				return false;
			}
		}
		return true;
	}
	
	public static int difficulty=5; //设置挖掘矿的复杂度
	public void tryMining() {
		blockchain.add(new Block("Hi im the first block", "0"));
		System.out.println("Trying to Mine block 1... ");
		blockchain.get(0).mineBlock(difficulty);
		
		blockchain.add(new Block("Yo im the second block",blockchain.get(blockchain.size()-1).hash));
		System.out.println("Trying to Mine block 2... ");
		blockchain.get(1).mineBlock(difficulty);
		
		blockchain.add(new Block("Hey im the third block",blockchain.get(blockchain.size()-1).hash));
		System.out.println("Trying to Mine block 3... ");
		blockchain.get(2).mineBlock(difficulty);	
		
		System.out.println("\nBlockchain is Valid: " + isChainValid());
		
		String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
		System.out.println("\nThe block chain: ");
		System.out.println(blockchainJson);
	}
}
