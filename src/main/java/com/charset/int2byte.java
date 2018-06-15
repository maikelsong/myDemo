package com.charset;

/**
 * 【【1个字节8位】】
 * <p/>
 * 注意微软的计算器有 【 四字；双字；字；字节 】
 * 类型说明符　　　　数的范围 　　　　		分配字节数 【1个■表示一个字节】
 * byte 1字节，char 2字节，int 4字节
 * java中一个汉字占2个字节
 * <p/>
 * 整数类型（Integer）：byte， short， int， long
 * 　　　　　浮点类型（Floating）：float，double
 * 　　　　　字符类型（Textual）：char
 * 　　　　　布尔类型（Logical）：boolean
 *
 * @author raolesong
 * @version V 1.0 Dec 12, 2013
 */


public class int2byte {


    

    /**
     * 将int转化成byte[]
     * 高位在在最后，但c语言是高位在最前面，可以参考tcpSendDataPackage的ByteUtils
     *
     * @param res 要转化的整数
     * @return 对应的byte[]
     */
    public static byte[] int2byte(int res) {
        byte[] targets = new byte[4];
        targets[0] = (byte) (res & 0xff);// 最低位
        targets[1] = (byte) ((res >> 8) & 0xff);// 次低位
        targets[2] = (byte) ((res >> 16) & 0xff);// 次高位
        targets[3] = (byte) (res >>> 24);// 最高位,无符号右移。
        return targets;
    }

    /**
     * 将byte[]转化成int
     *
     * @param res 要转化的byte[]
     * @return 对应的整数
     */
    public static int byte2int(byte[] res) {
        int targets = (res[0] & 0xff) | ((res[1] << 8) & 0xff00) | ((res[2] << 24) >>> 8) | (res[3] << 24);
        return targets;
    }
    
    
    public static void main(String[] args) {
    	byte[] bs = int2byte.int2byte(3);
    	System.out.println(bs.length);
    }
    

}

/*
 * Java虚拟机对基本类型的操作基本都是在栈上完成的（这个是可信的，因为不是我说的）。我们知道，Java在处理一个语句的时候，首先它会先把用到的操作数压到栈中，然后再从栈中弹出进行计算，
 * 最后将结果再压回到栈中。任何对byte的操作也会如此。因此，Java对byte类型操作之前会将其压入到栈中。实际上，Java压入栈的不是byte类型，而是一个标准的int类型（32位，byte是8位），
 * 也就是说，Java虚拟机将我们短小可爱的byte类型压入栈之前，会先把它拉长成int类型再压栈。不过事实上在压栈之前也是int类型.这样一来，我们不管是在栈里还是从栈里弹出的byte类型实际
 * 上都是用int的长度存储的。这也就是我为什么说，Java虚拟机中没有byte类型。因为它们都被变成了int。
 * 
 * 
 * int？还是byte？这么说来在Java虚拟机中处理来处理去的都是32位长的int，那么byte怎么办？换句话说，如果我们看到一个32位的int，那我们应该管它叫int呢还是叫byte呢？
 * 对于这个问题，我个人的答案是你叫丫虾米丫就是虾米。举个例子来说吧：现在栈顶端有两只。。。恩。。。32位长的。。。恩。。。你明白我的意思。你想对它们进行相加运算。在这个时候你的
 * 作用就很明显了，当你对虚拟机说把它们俩给我相加成一个整数，那么Java虚拟机会弹出这两个东西，然后相加，将结果以int类型压回到栈中。但是如果你对虚拟机说：把这两个byte相加
 * 成一个byte或者把它们俩相加成一个byte，那么Java虚拟机还是会弹出这两个东西相加，只不过前面那句会先将它们俩转换成byte再变成int，然后相加；而后面那句会直接相加。两句的
 * 最后结果都是将相加的和先转换成byte然后在变成int压入栈中。
	那么，类型转换呢？这个总该是一个byte了吧！  
 	可惜，我只能说类型转换的过程中会出现真正的byte，但是它活不到最后就被拉长了。举个例子吧，看看下面我从有意义的程序中找出的两句毫无意义的代码吧：  
 * 
 * 
 * 
 * 
 * 
 * 	int a = 1;
			byte b = (byte)a;        
好吧，我承认会这么写的代码，程序也不会有意义到哪儿去。但是我们就事论事。当我刚开始看到这个的时候，我非常兴奋的认为上面的那个变量b总应该是byte了吧。如果你和我一样，那么恭喜你离天才又进了一步。我只能说答案是否定的。不是为了打击你，而确确实实是否定的。是的，第二句在执行的时候确实产生了一个byte，但是很不幸，它没能活到最后。最终它被拉长成了int压入了栈中，用来做为byte变量b的值。虽然它被拉长成了32位的int，但是毕竟它是byte来的，所以身上还是有byte的血统的。怎么说呢，那就是它是被虚拟机带着符号扩展出来的。这个很好理解，byte本身就是8位0或者1的组合，你就是把8位上每一位0或者1拉的再长，充其量也就是长的长一些的0或者1的byte。所以要想变成32位，你得给byte填补24位进去。那么这24位从哪里来呢？Java虚拟机的做法就是从byte的符号位（也就是最高位）来。这就是所谓的带符号扩展。就拿上面的程序举例子吧，将1压缩成byte用二进制来看是00000001，这个我想大家都不陌生。接下来就是扩展，我们byte的符号位是正，也就是0，那么Java虚拟机就会用0来填充剩下的24位，结果就是00000000000000000000000000000001。自己数一下看我是不是漏掉了。  
 	
	大家可能觉得我举的例子有些太简单了，好吧，我来说一个难的。让byte变量b等于-1。当然，不是简简单单的从-1的int类型变成-1的byte类型，而是找一个正整数的int类型，让Java虚拟机截短成-1的byte类型。那么这个正整数是几呢？说实话，我拿高级计算器试了一天，最后从google上找到了它：2147483647。只要把上面语句中a的值改成这个，byte变量b的值就会是-1。非常简单，我觉得不需要解释。对不起，我有点儿得瑟和臭屁了。我还是解释一下吧：那个2147483647整数的二进制是这样的：01111111111111111111111111111111，仔细数，是32位。现在我们要把它强制转换成byte，只有8位，所以Java虚拟机不假思索的给咱们砍掉24位，剩下8位都是1：11111111，这个当然就是那个-1了。什么？你说不是？是-127？不不不不，不要忘了，Java虚拟机中使用补码来表示的，你看到的是补码。这次再算算，-1了吧。好了，接下来就是扩充回int类型了。简单的把符号位复制24个出来就好了，结果就是11111111111111111111111111111111。这个是几？自己算吧。  
  	总结,好了，说了这么多，我们也看到了，虽然Java虚拟机中的操作数可以是一个byte，但是不管是运算也好还是类型转换也好，最终的结果都是int。至于在执行过程中如何区别，那就全靠写程序的人自己了。如果你自己都模棱两可的话，不要指望Java虚拟机会明白你的意思。
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */
