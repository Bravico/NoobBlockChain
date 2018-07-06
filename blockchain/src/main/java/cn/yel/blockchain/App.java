package cn.yel.blockchain;

import cn.yel.blockchain.impl.Test1;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	Test1 test1 = new Test1();
//    	test1.tryEasyBlock();
//    	test1.tryGsonBlock();
    	test1.tryMining();
    }
}
