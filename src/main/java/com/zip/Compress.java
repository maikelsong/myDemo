package com.zip;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
/*
 * 
 * 第一，压缩算法的有效临界点。只有要压缩的数据大于这个点，压缩后的数据才会更小，反之，压缩后的数据会更加的大。
 * 我使用的zip算法这个点应该是50字节左右，因此，在我应用中，将大数据定义成50字节以上的数据。
 * 
  第二：压缩和解压的开销。服务器要压缩数据，客户端要解压数据，这个都是需要CPU开销的，特别是服务器，如果请求量大的话，
  需要为每一个响应数据进行压缩，势必降低服务器的性能。我们可以设想这样的一种情况，
  原生数据只有50字节，压缩完会有40字节，那么我们就要思考是否有必要来消耗CPU来为我们这区区的10个字节来压缩呢？
 * 
 */

public class Compress {

    private static final int BUFFER_LENGTH = 400;


    //压缩字节最小长度，小于这个长度的字节数组不适合压缩，压缩完会更大
    public static final int BYTE_MIN_LENGTH = 50;


    //字节数组是否压缩标志位
    public static final byte FLAG_GBK_STRING_UNCOMPRESSED_BYTEARRAY = 0;
    public static final byte FLAG_GBK_STRING_COMPRESSED_BYTEARRAY = 1;
    public static final byte FLAG_UTF8_STRING_COMPRESSED_BYTEARRAY = 2;
    public static final byte FLAG_NO_UPDATE_INFO = 3;

    /**
     * 数据压缩
     *
     * @param is
     * @param os
     * @throws Exception
     */
    public static void compress(InputStream is, OutputStream os)
            throws Exception {

        GZIPOutputStream gos = new GZIPOutputStream(os);

        int count;
        byte data[] = new byte[BUFFER_LENGTH];
        while ((count = is.read(data, 0, BUFFER_LENGTH)) != -1) {
            gos.write(data, 0, count);
        }

        gos.finish();

        gos.flush();
        gos.close();
    }


    /**
     * 数据解压缩
     *
     * @param is
     * @param os
     * @throws Exception
     */
    public static void decompress(InputStream is, OutputStream os)
            throws Exception {

        GZIPInputStream gis = new GZIPInputStream(is);

        int count;
        byte data[] = new byte[BUFFER_LENGTH];
        while ((count = gis.read(data, 0, BUFFER_LENGTH)) != -1) {
            os.write(data, 0, count);
        }

        gis.close();
    }

    /**
     * 数据压缩
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static byte[] byteCompress(byte[] data) throws Exception {
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        // 压缩
        compress(bais, baos);

        byte[] output = baos.toByteArray();

        baos.flush();
        baos.close();

        bais.close();

        return output;
    }


    /**
     * 数据解压缩
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static byte[] byteDecompress(byte[] data) throws Exception {
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        // 解压缩

        decompress(bais, baos);

        data = baos.toByteArray();

        baos.flush();
        baos.close();

        bais.close();

        return data;
    }

    public static void main(String[] args) throws Exception {
        String str = "aadfasdfqrewdfakjkfasjdkfrwieifdfkj" +
                "adkfjaksdfjaksdf" +
                "adfakjdfkajdfaakdfjakdjfakdjfakjqiriasdfkjhqiiasfkhrikjfaksdfjkaadfakjdfk" +
                "ajdfaakdfjakdjfakdjfakjqiriasdfkjhqiiasfkhrik" +
                "adfakjdfkajdfaakdfjakdjfakdjfakjqiriasdfkjhqiiasfkhrikjfaksdfjkaadfakjdfkajdfaakdfjakdjfakdjfakjqiriasdfkjhqiiasfkhrikjfaksdfjkaadfakjdfkajdfaakdfjakdjfakdjfakjqiriasdf" +
                "kjhqiiasfkhrikjfaksdfjkaadfakjdfkajdfaakdfjakdjfakdjfakjqiriasdfkjhqiiasfkhrikjfaksdfjkajfaksdfjkaadfakjdfkajdfaakdfjakdjfakdjfakjqiriasdfkjhqiiasfkhrikjfaksdfjkaadfakjdfkajdfaakdfjakdjfakdjfakjqiriasdfkjhqiiasfkhrikjfaksdfjkaadfakjdfkajdfaakdfjakdjfakdjfakjqiriasdfkjhqii" +
                "kjhqiiasfkhrikjfaksdfjkaadfakjdfkajdfaakdfjakdjfakdjfakjqiriasdfkjhqiiasfkhrikjfaksdfjkajfaksdfjkaadfakjdfkajdfaakdfjakdjfakdjfakjqiriasdfkjhqiiasfkhrikjfaksdfjkaadfakjdfkajdfaakdfjakdjfakdjfakjqiriasdfkjhqiiasfkhrikjfaksdfjkaadfakjdfkajdfaakdfjakdjfakdjfakjqiriasdfkjhqiiasfkhrikjfaksdfjkaadfakjdfkajdfaakdfjakdjfakdjfakjqiriasdfkjhqiiasfkhrikjfaksdfjkakjhqiiasfkhrikjfaksdfjkaadfakjdfkajdfaakdfjakdjfakdjfakjqiriasdfkjhqiiasfkhrikjfaksdfjkajfaksdfjkaadfakjdfkajdfaakdfjakdjfakdjfakjqiriasdfkjhqiiasfkhrikjfaksdfjkaadfakjdfkajdfaakdfjakdjfakdjfakjqiriasdfkjhqiiasfkhrikjfaksdfjkaadfakjdfkajdfaakdfjakdjfakdjfakjqiriasdfkjhqiiasfkhrikjfaksdfjkaadfakjdfkajdfaakdfjakdjfakdjfakjqiriasdfkjhqiiasfkhrikjfaksdfjkakjhqiiasfkhrikjfaksdfjkaadfakjdfkajdfaakdfjakdjfakdjfakjqiriasdfkjhqiiasfkhrikjfaksdfjkajfaksdfjkaadfakjdfkajdfaakdfjakdjfakdjfakjqiriasdfkjhqiiasfkhrikjfaksdfjkaadfakjdfkajdfaakdfjakdjfakdjfakjqiriasdfkjhqiiasfkhrikjfaksdfjkaadfakjdfkajdfaakdfjakdjfakdjfakjqiriasdfkjhqiiasfkhrikjfaksdfjkaadfakjdfkajdfaakdfjakdjfakdjfakjqiriasdfkjhqiiasfkhrikjfaksdfjkakjhqiiasfkhrikjfaksdfjkaadfakjdfkajdfaakdfjakdjfakdjfakjqiriasdfkjhqiiasfkhrikjfaksdfjkajfaksdfjkaadfakjdfkajdfaakdfjakdjfakdjfakjqiriasdfkjhqiiasfkhrikjfaksdfjkaadfakjdfkajdfaakdfjakdjfakdjfakjqiriasdfkjhqiiasfkhrikjfaksdfjkaadfakjdfkajdfaakdfjakdjfakdjfakjqiriasdfkjhqiiasfkhrikjfaksdfjkaadfakjdfkajdfaakdfjakdjfakdjfakjqiriasdfkjhqiiasfkhrikjfaksdfjkakjhqiiasfkhrikjfaksdfjkaadfakjdfkajdfaakdfjakdjfakdjfakjqiriasdfkjhqiiasfkhrikjfaksdfjkajfaksdfjkaadfakjdfkajdfaakdfjakdjfakdjfakjqiriasdfkjhqiiasfkhrikjfaksdfjkaadfakjdfkajdfaakdfjakdjfakdjfakjqiriasdfkjhqiiasfkhrikjfaksdfjkaadfakjdfkajdfaakdfjakdjfakdjfakjqiriasdfkjhqiiasfkhrikjfaksdfjkaadfakjdfkajdfaakdfjakdjfakdjfakjqiriasdfkjhqiiasfkhrikjfaksdfjkakjhqiiasfkhrikjfaksdfjkaadfakjdfkajdfaakdfjakdjfakdjfakjqiriasdfkjhqiiasfkhrikjfaksdfjkajfaksdfjkaadfakjdfkajdfaakdfjakdjfakdjfakjqiriasdfkjhqiiasfkhrikjfaksdfjkaadfakjdfkajdfaakdfjakdjfakdjfakjqiriasdfkjhqiiasfkhrikjfaksdfjkaadfakjdfkajdfaakdfjakdjfakdjfakjqiriasdfkjhqiiasfkhrikjfaksdfjkaadfakjdfkajdfaakdfjakdjfakdjfakjqiriasdfkjhqiiasfkhrikjfaksdfjkakjhqiiasfkhrikjfaksdfjkaadfakjdfkajdfaakdfjakdjfakdjfakjqiriasdfkjhqiiasfkhrikjfaksdfjkajfaksdfjkaadfakjdfkajdfaakdfjakdjfakdjfakjqiriasdfkjhqiiasfkhrikjfaksdfjkaadfakjdfkajdfaakdfjakdjfakdjfakjqiriasdfkjhqiiasfkhrikjfaksdfjkaadfakjdfkajdfaakdfjakdjfakdjfakjqiriasdfkjhqiiasfkhrikjfaksdfjkaadfakjdfkajdfaakdfjakdjfakdjfakjqiriasdfkjhqiiasfkhrikjfaksdfjkakjhqiiasfkhrikjfaksdfjkaadfakjdfkajdfaakdfjakdjfakdjfakjqiriasdfkjhqiiasfkhrikjfaksdfjkajfaksdfjkaadfakjdfkajdfaakdfjakdjfakdjfakjqiriasdfkjhqiiasfkhrikjfaksdfjkaadfakjdfkajdfaakdfjakdjfakdjfakjqiriasdfkjhqiiasfkhrikjfaksdfjkaadfakjdfkajdfaakdfjakdjfakdjfakjqiriasdfkjhqiiasfkhrikjfaksdfjkaadfakjdfkajdfaakdfjakdjfakdjfakjqiriasdfkjhqiiasfkhrikjfaksdfjkakjhqiiasfkhrikjfaksdfjkaadfakjdfkajdfaakdfjakdjfakdjfakjqiriasdfkjhqiiasfkhrikjfaksdfjkajfaksdfjkaadfakjdfkajdfaakdfjakdjfakdjfakjqiriasdfkjhqiiasfkhrikjfaksdfjkaadfakjdfkajdfaakdfjakdjfakdjfakjqiriasdfkjhqiiasfkhrikjfaksdfjkaadfakjdfkajdfaakdfjakdjfakdjfakjqiriasdfkjhqiiasfkhrikjfaksdfjkaadfakjdfkajdfaakdfjakdjfakdjfakjqiriasdfkjhqiiasfkhrikjfaksdfjkakjhqiiasfkhrikjfaksdfjkaadfakjdfkajdfaakdfjakdjfakdjfakjqiriasdfkjhqiiasfkhrikjfaksdfjkajfaksdfjkaadfakjdfkajdfaakdfjakdjfakdjfakjqiriasdfkjhqiiasfkhrikjfaksdfjkaadfakjdfkajdfaakdfjakdjfakdjfakjqiriasdfkjhqiiasfkhrikjfaksdfjkaadfakjdfkajdfaakdfjakdjfakdjfakjqiria" +
                "adfakjdfkajdfaakdfjakdjfakdjfakjqiriasdfkjhqiiasfkhrikjfaksdfjkaadfakjdfkajdfaakdfjakdjfakdjfakjqiriasdfkjhqiiasfkhrikjfaksdfjkaadfakjdfkajdfaakdfjakdjfakdjfakjqiriasdfadfakjdfkajdfaakdfjakdjfakdjfakjqiriasdfkjhqiiasfkhrikjfaksdfjkaadfakjdfkajdfaakdfjakdjfakdjfakjqiriasdfkjhqiiasfkhrikjfaksdfjkaadfakjdfkajdfaakdfjakdjfakdjfakjqiriasdfadfakjdfkajdfaakdfjakdjfakdjfakjqiriasdfkjhqiiasfkhrikjfaksdfjkaadfakjdfkajdfaakdfjakdjfakdjfakjqiriasdfkjhqiiasfkhrikjfaksdfjkaadfakjdfkajdfaakdfjakdjfakdjfakjqiriasdfadfakjdfkajdfaakdfjakdjfakdjfakjqiriasdfkjhqiiasfkhrikjfaksdfjkaadfakjdfkajdfaakdfjakdjfakdjfakjqiriasdfkjhqiiasfkhrikjfaksdfjkaadfakjdfkajdfaakdfjakdjfakdjfakjqiriasdfadfakjdfkajdfaakdfjakdjfakdjfakjqiriasdfkjhqiiasfkhrikjfaksdfjkaadfakjdfkajdfaakdfjakdjfakdjfakjqiriasdfkjhqiiasfkhrikjfaksdfjkaadfakjdfkajdfaakdfjakdjfakdjfakjqiriasdfadfakjdfkajdfaakdfjakdjfakdjfakjqiriasdfkjhqiiasfkhrikjfaksdfjkaadfakjdfkajdfaakdfjakdj" +
                "fakdjfakjqiriasdfkjhqiiasfkhrikjfaksdfjkaadfakjdfkajdfaakdfjakdjfakdjfakjqiriasdfsdfkjhqiiasfkhrikjfaksdfjkaadfakjdfkajdfaakdfjakdjfakdjfakjqiriasdfkjhqiiasfkhrikjfaksdfjkaasfkhrikjfaksdfjkaadfakjdfkajdfaakdfjakdjfakdjfakjqiriasdfkjhqiiasfkhrikjfaksdfjka";
        byte[] source = str.getBytes();
        System.out.println(source.length);

        byte[] target = Compress.byteCompress(source);
        System.out.println(target.length);

        byte[] target2 = Compress.byteDecompress(target);
        System.out.println(target2.length);

        System.out.println(new String(target2));


    }


}