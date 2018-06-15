package com.charset;

import java.util.Arrays;

/**
 * 阶段一	ASCII：  计算机刚开始只支持英语，其它语言不能够在计算机上存储和显示。
 * 阶段二	ANSI编码（本地化）
 * 为使计算机支持更多语言，通常使用 0x80~0xFF 范围的 2 个字节来表示 1 个字符。比如：汉字 '中' 在中文操作系统中，使用 [0xD6,0xD0] 这两个字节存储。
 * 不同的国家和地区制定了不同的标准，由此产生了 GB2312, BIG5, JIS 等各自的编码标准。这些使用 2 个字节来代表一个字符的各种汉字延伸编码方式，
 * 称为 ANSI 编码。在简体中文系统下，ANSI 编码代表 GB2312 编码，在日文操作系统下，ANSI 编码代表 JIS 编码。
 * 不同 ANSI 编码之间互不兼容，当信息在国际间交流时，无法将属于两种语言的文字，存储在同一段 ANSI 编码的文本中。
 * 阶段三	UNICODE（国际化）
 * 为了使国际间信息交流更加方便，国际组织制定了 UNICODE 字符集，为各种语言中的每一个字符设定了统一并且唯一的数字编号，
 * 以满足跨语言、跨平台进行文本转换、处理的要求。
 * <p/>
 * ==============================
 * <p/>
 * 1个字节=8位
 * 1 本机win系统是gbk编码,所以str.getBytes("gbk")和str.getBytes()得到的“byte数组”是一样的;
 * 2 当一个“byte数组”要还原成字符串[new String(byte[])]，一定要指定该byte数组[即该byte数组生成的编码]的编码，
 * 要不能会出现乱码。
 * <p/>
 * =============================
 * 字符集与编码
 * <p/>
 * 各个国家和地区所制定的不同 ANSI 编码标准中，都只规定了各自语言所需的“字符”。
 * 比如：汉字标准（GB2312）中没有规定韩国语字符怎样存储。这些 ANSI 编码标准所规定的内容包含两层含义：
 * 使用哪些字符。也就是说哪些汉字，字母和符号会被收入标准中。所包含“字符”的集合就叫做“字符集”。
 * 规定每个“字符”分别用一个字节还是多个字节存储，用哪些字节来存储，这个规定就叫做“编码”。
 * 各个国家和地区在制定编码标准的时候，“字符的集合”和“编码”一般都是同时制定的。
 * 因此，平常我们所说的“字符集”，比如：GB2312, GBK, JIS 等，除了有“字符的集合”这层含义外，同时也包含了“编码”的含义。
 * “UNICODE 字符集”包含了各种语言中使用到的所有“字符”。用来给 UNICODE 字符集编码的标准有很多种，
 * 比如：UTF-8, UTF-7, UTF-16, UnicodeLittle, UnicodeBig 等。
 * ===============================
 * <p/>
 * 1. ISO-8859-1【  单字节字符编码  】
 * 最简单的编码规则，每一个字节直接作为一个 UNICODE 字符。比如，[0xD6, 0xD0] 这两个字节，
 * 通过 iso-8859-1 转化为字符串时，将直接得到 [0x00D6, 0x00D0] 两个 UNICODE 字符，即 "ÖÐ"。
 * 反之，将 UNICODE 字符串通过 iso-8859-1 转化为字节串时，只能正常转化 0~255 范围的字符。
 * <p/>
 * 2. ANSI 编码	GB2312,
 * BIG5,
 * Shift_JIS,
 * ISO-8859-2 ……
 * 把 UNICODE 字符串通过 ANSI 编码转化为“字节串”时，根据各自编码的规定，一个 UNICODE 字符可能转化成一个字节或多个字节。
 * 反之，将字节串转化成字符串时，也可能多个字节转化成一个字符。比如，[0xD6, 0xD0] 这两个字节，通过 GB2312 转化为字符串时，
 * 将得到 [0x4E2D] 一个字符，即 '中' 字。
 * “ANSI 编码”的特点：
 * 1. 这些“ANSI 编码标准”都只能处理各自语言范围之内的 UNICODE 字符。
 * 2. “UNICODE 字符”与“转换出来的字节”之间的关系是人为规定的。
 * <p/>
 * 3. UNICODE 编码	UTF-8,
 * UTF-16,
 * UnicodeBig ……
 * 与“ANSI 编码”类似的，把字符串通过 UNICODE 编码转化成“字节串”时，一个 UNICODE 字符可能转化成一个字节或多个字节。
 * 与“ANSI 编码”不同的是：
 * 1. 这些“UNICODE 编码”能够处理所有的 UNICODE 字符。
 * 2. “UNICODE 字符”与“转换出来的字节”之间是可以通过计算得到的。
 *
 * @author raolesong
 * @version V 1.0 May 30, 2012
 *          我们实际上没有必要去深究每一种编码具体把某一个字符编码成了哪几个字节，
 *          我们只需要知道“编码”的概念就是把“字符”转化成“字节”就可以了。对于“UNICODE 编码”，
 *          由于它们是可以通过计算得到的，因此，在特殊的场合，我们可以去了解某一种“UNICODE 编码”是怎样的规则。
 *          <p/>
 *          <p/>
 *          当 UNICODE 被支持后，Java 中的 String 是以字符的“序号”来存储的，不是以“某种编码的字节”来存储的，
 *          因此已经不存在“字符串的编码”这个概念了。只有在“字符串”与“字节串”转化时，
 *          或者，将一个“字节串”当成一个 ANSI 字符串时，才有编码的概念。
 *          <p/>
 *          <p/>
 *          在java中，一个汉字占2个字节
 *          <p/>
 *          请参考 ” 字符-字节- 编码.docx “
 *          <p/>
 *          GB2312是中国规定的汉字编码，也可以说是简体中文的字符集编码;GBK 是 GB2312的扩展 ,除了兼容GB2312外，它还能显示繁体中文，还有日文的假名
 */
public class T {

    public static void main(String[] args) throws Exception {

        //因为在JAVA中，一个char是2个字节（byte），而一个中文汉字是一个字符，也是2个字节。 请参考 “ java各数据类型占字节数.jpg ”
        char c = '中';
//		char c2 = "中";
//		char c3 = 'zh'; 
        char c4 = 'c';

//        System.out.println("c占字节=" + Character.toString(c).getBytes().length);
//        System.out.println("c4占字节=" + Character.toString(c4).getBytes().length);


        byte b = 'a'; //而英文字母都是一个字节的，因此它也能保存到一个byte里，一个中文汉字却不能。
//		byte b2 = '中';
//		byte b3 = "a";

        //符号：英文标点占一个字节，中文标点占两个字节。举例：英文句号“.”占1个字节的大小，中文句号“。”占2个字节的大小
        char dian = '.';
        char dian2 = '。'; //这个是中文下的点
//        System.out.println("dian占字节=" + Character.toString(dian).getBytes().length);
//        System.out.println("dian2占字节=" + Character.toString(dian2).getBytes().length);


        String str = "张3";

        byte[] bs = str.getBytes("gbk"); //类似gbk,因为本机win系统是gbk编码 2个字节
        System.out.println("GBK: " + Arrays.toString(bs));
        System.out.println(new String(bs));


        byte[] bs2 = str.getBytes("utf-8"); //3个字节
        System.out.println("utf-8: " + Arrays.toString(bs2));
        System.out.println(new String(bs2, "utf-8"));


        byte[] bs3 = str.getBytes("ISO-8859-1");  //ISO-8859-1是 单字节字符编码
        System.out.println("ISO-8859-1: " + Arrays.toString(bs3)); //结果[63, 63, 49, 50, 51, 97]，每一个字节直接作为一个 UNICODE 字符
        String _s1 = new String(bs3);
        System.out.println(_s1);
        System.out.println(new String(_s1.getBytes("ISO-8859-1"), "GBK"));

    }

}

/*
 * 
 * 
 * 误解：“ISO-8859-1 是国际编码？”

			非也。iso-8859-1 只是单字节字符集中最简单的一种，也就是“字节编号”与“UNICODE 字符编号”一致的那种编码规则。
			当我们要把一个“字节串”转化成“字符串”，而又不知道它是哪一种 ANSI 编码时，先暂时地把“每一个字节”作为“一个字符”进行转化，
			不会造成信息丢失。然后再使用 bytes = string.getBytes("iso-8859-1") 的方法可恢复到原始的字节串。
   误解：“Java 中，怎样知道某个字符串的内码？”
			Java 中，字符串类 java.lang.String 处理的是 UNICODE 字符串，不是 ANSI 字符串。我们只需要把字符串作为“抽象的符号的串”来看待。
			因此不存在字符串的内码的问题。
 * 
 * 
 * 

**** 我们需要使用 bytes = string.getBytes("iso-8859-1") 得到原始的字节串 ****

当页面中的表单提交字符串时，首先把字符串按照当前页面的编码，转化成字节串。
然后再将每个字节转化成 "%XX" 的格式提交到 Web 服务器。比如，一个编码为 GB2312 的页面，提交 "中" 这个字符串时，提交给服务器的内容为 "%D6%D0"。

在服务器端，Web 服务器把收到的 "%D6%D0" 转化成 [0xD6, 0xD0] 两个字节，然后再根据 GB2312 编码规则得到 "中" 字。

在 Tomcat 服务器中，request.getParameter() 得到乱码时，常常是因为前面提到的“误解一”造成的。默认情况下，
当提交 "%D6%D0" 给 Tomcat 服务器时，request.getParameter() 将返回 [0x00D6, 0x00D0] 两个 UNICODE 字符，而不是返回一个 "中" 字符。因此
，我们需要使用 bytes = string.getBytes("iso-8859-1") 得到原始的字节串，再用 string = new String(bytes, "GB2312") 重新得到正确的字符串 "中"。

*/

