package cn.yel.blockchain.impl;

import java.security.Security;
import java.security.Signature;
import java.util.ArrayList;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import cn.yel.blockchain.utils.StringUtil;

public class test2 {
	public static ArrayList<Block> blockchain = new ArrayList<Block>();
	public static int difficulty = 5;
	public static Wallet walletA;
	public static Wallet walletB;
	
	public void tryTransaction() {
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());// what's this provider?
		walletA = new Wallet();
		walletB = new Wallet();
		System.out.println("Private and public keys:");
		System.out.println(StringUtil.getStringFromKey(walletA.privateKey));
		System.out.println(StringUtil.getStringFromKey(walletB.publicKey));
		//Create a test transaction from walletA to walletB
		Transacation transacation = new Transacation(walletA, walletB, 2, null);
		transacation.generateSignature(walletA.privateKey);
		
		//verify the signature works and verify it from the public key
		System.out.println("Is Signature verified?");
		System.out.println(transacation.verifiySignature());
	}
}
