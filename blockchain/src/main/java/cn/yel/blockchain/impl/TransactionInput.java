package cn.yel.blockchain.impl;

public class TransactionInput {
	public String transactionOutputId; //Reference to TransactionOutputs ->  transactionId
	public TransactionOutput UTXO;  //prepared for the next Transation...
	
	public TransactionInput(String transactionOutputId) {
		this.transactionOutputId = transactionOutputId;
	}
}
