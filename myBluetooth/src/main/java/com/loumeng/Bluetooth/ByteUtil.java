package com.loumeng.Bluetooth;

/**
 * Created by yanfeng on 2016/05/12.
 */
public class ByteUtil {

    public static void putShort(byte b[], short s, int index) {
        b[index + 1] = (byte) (s >> 8);
        b[index] = (byte) (s);
    }

    public static short getShort(byte[] b, int index) {
        return (short) (((b[index + 1] << 8) | b[index] & 0xff));
    }

    public static void putInt(byte[] bb, int x, int index) {
        bb[index + 3] = (byte) (x >> 24);
        bb[index + 2] = (byte) (x >> 16);
        bb[index + 1] = (byte) (x >> 8);
        bb[index] = (byte) (x);
    }

    public static int getInt(byte[] bb, int index) {
        return (int) ((((bb[index + 3] & 0xff) << 24) | ((bb[index + 2] & 0xff) << 16) | ((bb[index + 1] & 0xff) << 8)
                | ((bb[index] & 0xff))));
    }

    public static void putLong(byte[] bb, long x, int index) {
        bb[index + 7] = (byte) (x >> 56);
        bb[index + 6] = (byte) (x >> 48);
        bb[index + 5] = (byte) (x >> 40);
        bb[index + 4] = (byte) (x >> 32);
        bb[index + 3] = (byte) (x >> 24);
        bb[index + 2] = (byte) (x >> 16);
        bb[index + 1] = (byte) (x >> 8);
        bb[index] = (byte) (x);
    }

    public static byte[] reverse(byte[] bytes) {
        byte[] result = new byte[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            result[i] = bytes[bytes.length - i - 1];
        }
        return result;
    }

    public static long getLong(byte[] bb, int index) {
        return ((((long) bb[index + 7] & 0xff) << 56) | (((long) bb[index + 6] & 0xff) << 48)
                | (((long) bb[index + 5] & 0xff) << 40) | (((long) bb[index + 4] & 0xff) << 32)
                | (((long) bb[index + 3] & 0xff) << 24) | (((long) bb[index + 2] & 0xff) << 16)
                | (((long) bb[index + 1] & 0xff) << 8) | (((long) bb[index] & 0xff)));
    }

    public static void putChar(byte[] bb, char ch, int index) {
        int temp = (int) ch;
        for (int i = 0; i < 2; i++) {
            bb[index + i] = new Integer(temp & 0xff).byteValue(); // 将最高位保存在最低位
            temp = temp >> 8; // 向右移8位
        }
    }

    public static char getChar(byte[] b, int index) {
        int s = 0;
        if (b[index + 1] > 0)
            s += b[index + 1];
        else
            s += 256 + b[index];
        s *= 256;
        if (b[index] > 0)
            s += b[index + 1];
        else
            s += 256 + b[index];
        char ch = (char) s;
        return ch;
    }

    public static void putFloat(byte[] bb, float x, int index) {
        int l = Float.floatToIntBits(x);
        for (int i = 0; i < 4; i++) {
            bb[index + i] = new Integer(l).byteValue();
            l = l >> 8;
        }
    }

    public static float getFloat(byte[] b, int index) {
        int l;
        l = b[index];
        l &= 0xff;
        l |= ((long) b[index + 1] << 8);
        l &= 0xffff;
        l |= ((long) b[index + 2] << 16);
        l &= 0xffffff;
        l |= ((long) b[index + 3] << 24);
        return Float.intBitsToFloat(l);
    }

    public static void putDouble(byte[] bb, double x, int index) {
        long l = Double.doubleToLongBits(x);
        for (int i = 0; i < 4; i++) {
            bb[index + i] = new Long(l).byteValue();
            l = l >> 8;
        }
    }

    /**
     * 转换一个 Integer 转hex
     *
     * @param data
     * @return
     */
    public static String intToHex(int data) {
        String hex = Integer.toHexString(data);
        return (hex.length() < 2 ? "0" + hex : hex).toUpperCase();
    }

    public static String intToBinaryHex(int data) {
        String hex = Integer.toBinaryString(data);
        if (hex.length() == 2) {
            hex = "00" + hex;
        } else if (hex.length() == 3) {
            hex = "0" + hex;
        }
        if (hex.length() == 4) {
            hex = hex.substring(0, 2) + " " + hex.substring(2, 4);
        }
        return (hex.length() < 2 ? "0" + hex : hex).toUpperCase();
    }

    public static double getDouble(byte[] b, int index) {
        long l;
        l = b[0];
        l &= 0xff;
        l |= ((long) b[1] << 8);
        l &= 0xffff;
        l |= ((long) b[2] << 16);
        l &= 0xffffff;
        l |= ((long) b[3] << 24);
        l &= 0xffffffffl;
        l |= ((long) b[4] << 32);
        l &= 0xffffffffffl;
        l |= ((long) b[5] << 40);
        l &= 0xffffffffffffl;
        l |= ((long) b[6] << 48);
        l &= 0xffffffffffffffl;
        l |= ((long) b[7] << 56);
        return Double.longBitsToDouble(l);
    }

    /**
     * byte数组转换成16进制
     *
     * @param data
     * @return
     */
    public static String byteToHex(byte[] data) {
        return byteToHex(data, true);
    }

    /**
     * byte数组转换成16进制
     *
     * @param data
     * @param hasBlank 是否加空格
     * @return
     */
    public static String byteToHex(byte[] data, boolean hasBlank) {

        StringBuilder stringBuilder = new StringBuilder("");
        if (data == null)
            return "";

        if (hasBlank) {
            for (int i = 0; i < data.length; i++) {
                stringBuilder.append(byteToHex(data[i]) + " ");
            }
        } else {
            for (int i = 0; i < data.length; i++) {
                stringBuilder.append(byteToHex(data[i]));
            }
        }
        return stringBuilder.toString().trim().toUpperCase();
    }

    public static String getDataByIndex(byte[] data, int index) {
        if (data == null || index >= data.length || index < 0) {
            return "";
        }
        // int va = data[index] & 0xFF;
        // String hv = Integer.toHexString(va);
        // if (hv.length() < 2) {
        // return "0" + hv;
        // } else {
        // return hv;
        // }
        return byteToHex(data[index]);
    }

    /**
     * short类型转换成byte数组
     *
     * @param param
     * @return
     */
    public static byte[] shortToByteArr(short param) {// 转化成小段模式输出
        byte[] arr = new byte[2];
        arr[1] = (byte) ((param >> 8) & 0xff);
        arr[0] = (byte) (param & 0xff);
        return arr;
    }

    /**
     * int类型转换成byte数组
     *
     * @param param
     * @return
     */
    public static byte[] intToByteArr(int param) {// 小端模式存储
        byte[] arr = new byte[4];
        arr[3] = (byte) ((param >> 24) & 0xff);
        arr[2] = (byte) ((param >> 16) & 0xff);
        arr[1] = (byte) ((param >> 8) & 0xff);
        arr[0] = (byte) (param & 0xff);
        return arr;
    }

    /**
     * long类型转换成byte数组
     *
     * @param param
     * @return
     */
    public static byte[] longToByteArr(long param) {
        byte[] arr = new byte[8];
        arr[0] = (byte) ((param >> 56) & 0xff);
        arr[1] = (byte) ((param >> 48) & 0xff);
        arr[2] = (byte) ((param >> 40) & 0xff);
        arr[3] = (byte) ((param >> 32) & 0xff);
        arr[4] = (byte) ((param >> 24) & 0xff);
        arr[5] = (byte) ((param >> 16) & 0xff);
        arr[6] = (byte) ((param >> 8) & 0xff);
        arr[7] = (byte) (param & 0xff);
        return arr;
    }

    /**
     * 获取数据中从起始位到终止位的数据
     * getDataByIndex(byte[] data, int index)的扩展
     */
    public static String getDataByIndex(byte[] data, int start, int end) {
        if (start >= data.length || start < 0 || start > end) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        for (int i = start; i <= end; i++) {
            result.append(byteToHex(data[i]));
            if (i != end) {
                result.append(" ");
            }
        }
        return String.valueOf(result);
    }

    /**
     * 字符到字节转换
     *
     * @param ch
     * @return
     */
    public static byte[] charToByteArr(char ch) {
        byte[] b = new byte[2];
        int temp = (int) ch;
        b[0] = (byte) (temp >> 8 & 0xff);
        b[1] = (byte) (temp & 0xff);
        return b;
    }

    /**
     * double转换byte数组
     *
     * @param param
     * @return byte数组
     */
    public static byte[] doubleToByteArr(double param) {
        byte[] b = new byte[8];
        long l = Double.doubleToLongBits(param);
        for (int i = 0; i < b.length; i++) {
            b[i] = new Long(l).byteValue();
            l = l >> 8;
        }
        return b;
    }

    /**
     * float转换byte数组
     *
     * @param param
     * @return byte数组
     */
    public static byte[] floatToByteArr(float param) {
        byte[] b = new byte[4];
        int l = Float.floatToIntBits(param);
        for (int i = 0; i < b.length; i++) {
            b[i] = new Integer(l).byteValue();
            l = l >> 8;
        }
        return b;
    }

    /**
     * 将2字节的byte数组转成short值
     *
     * @param b
     * @return
     */
    public static short byteArrToShort(byte[] b) {
        byte[] a = new byte[2];
        int i = a.length - 1, j = b.length - 1;
        for (; i >= 0; i--, j--) {
            // 从b的尾部(即int值的低位)开始copy数据
            if (j >= 0) {
                a[i] = b[j];
            } else {
                // 如果b.length不足2,则将高位补0
                a[i] = 0;
            }
        }
        // &0xff将byte值无差异转成int,避免Java自动类型提升后,会保留高位的符号位
        int v0 = (a[0] & 0xff) << 8;
        int v1 = (a[1] & 0xff);
        return (short) (v0 + v1);
    }

    /**
     * 将4字节的byte数组转成int值
     *
     * @param b
     * @return
     */
    public static int byteArrToInt(byte[] b) {
        byte[] a = new byte[4];
        int i = a.length - 1, j = b.length - 1;
        for (; i >= 0; i--, j--) {
            // 从b的尾部(即int值的低位)开始copy数据
            if (j >= 0) {
                a[i] = b[j];
            } else {
                // 如果b.length不足4,则将高位补0
                a[i] = 0;
            }
        }
        // &0xff将byte值无差异转成int,避免Java自动类型提升后,会保留高位的符号位
        int v0 = (a[0] & 0xff) << 24;
        int v1 = (a[1] & 0xff) << 16;
        int v2 = (a[2] & 0xff) << 8;
        int v3 = (a[3] & 0xff);
        return v0 + v1 + v2 + v3;
    }

    /**
     * 将8字节的byte数组转成long值
     *
     * @param b
     * @return
     */
    public static long byteArrToLong(byte[] b) {
        byte[] a = new byte[8];
        int i = a.length - 1, j = b.length - 1;
        for (; i >= 0; i--, j--) {
            // 从b的尾部(即int值的低位)开始copy数据
            if (j >= 0) {
                a[i] = b[j];
            } else {
                // 如果b.length不足4,则将高位补0
                a[i] = 0;
            }
        }
        // &0xff将byte值无差异转成int,避免Java自动类型提升后,会保留高位的符号位
        long v0 = (long) (a[0] & 0xff) << 56;
        long v1 = (long) (a[1] & 0xff) << 48;
        long v2 = (long) (a[2] & 0xff) << 40;
        long v3 = (long) (a[3] & 0xff) << 32;
        long v4 = (long) (a[4] & 0xff) << 24;
        long v5 = (long) (a[5] & 0xff) << 16;
        long v6 = (long) (a[6] & 0xff) << 8;
        long v7 = (long) (a[7] & 0xff);
        return v0 + v1 + v2 + v3 + v4 + v5 + v6 + v7;
    }

    /**
     * 将2字节的byte数组转成字符值
     *
     * @param b
     * @return
     */
    public static char byteArrToChar(byte[] b) {
        byte[] a = new byte[2];
        int i = a.length - 1, j = b.length - 1;
        for (; i >= 0; i--, j--) {
            // 从b的尾部(即int值的低位)开始copy数据
            if (j >= 0) {
                a[i] = b[j];
            } else {
                // 如果b.length不足2,则将高位补0
                a[i] = 0;
            }
        }
        // &0xff将byte值无差异转成int,避免Java自动类型提升后,会保留高位的符号位
        int v0 = (a[0] & 0xff) << 8;
        int v1 = (a[1] & 0xff);
        return (char) (v0 + v1);
    }

    /**
     * byte数组到double转换
     *
     * @param b
     * @return double
     */
    public static double byteArrToDouble(byte[] b) {
        long l;
        l = b[0];
        l &= 0xff;
        l |= ((long) b[1] << 8);
        l &= 0xffff;
        l |= ((long) b[2] << 16);
        l &= 0xffffff;
        l |= ((long) b[3] << 24);
        l &= 0xffffffffl;
        l |= ((long) b[4] << 32);
        l &= 0xffffffffffl;
        l |= ((long) b[5] << 40);
        l &= 0xffffffffffffl;
        l |= ((long) b[6] << 48);
        l &= 0xffffffffffffffl;
        l |= ((long) b[7] << 56);
        return Double.longBitsToDouble(l);
    }

    /**
     * byte数组到float转换
     *
     * @param b
     * @return float
     */
    public static float byteArrToFloat(byte[] b) {
        int l;
        l = b[0];
        l &= 0xff;
        l |= ((long) b[1] << 8);
        l &= 0xffff;
        l |= ((long) b[2] << 16);
        l &= 0xffffff;
        l |= ((long) b[3] << 24);
        return Float.intBitsToFloat(l);
    }

    /**
     * 转换一个byte为hex
     *
     * @param data
     * @return
     */
    public static String byteToHex(byte data) {
        String hex = Integer.toHexString(data & 0xFF);
        return hex.length() < 2 ? "0" + hex : hex;
    }

    public static int byteToInt(byte data) {
        return data & 0xFF;
    }

    public static int bytesWapulong(byte[] seed) {
        byte tmp;

        for (int i = 0; i < seed.length / 2; i++) {
            tmp = seed[i];
            seed[i] = seed[seed.length - 1 - i];
            seed[seed.length - 1 - i] = tmp;
        }
        return (seed[0] & 0xff) | ((seed[1] & 0xff) << 8) | ((seed[2] & 0xff) << 16) | ((seed[3] & 0xff) << 24);
    }

    public static long hexToLong(String string) {
        try {
            string = string.replaceAll(" ", "");
            return Long.valueOf(string, 16);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return -1;
    }


    /**
     * Convert hex string to byte[]
     *
     * @param hexString the hex string编写十六进制的字符串转换为byte数组的函数： public byte[]
     *                  hexStringToBytes(String hexString); 每两个字符表示转化为一个字节，返回字节数组。
     *                  例：字符串"ABCDEF" 转化为byte数组 {0xAB,0xCD,0xEF} 字符串"01" 转化为byte数组 {0x01}
     * @return byte[]s
     */
    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase(); // 16进制没有大小之分。
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | (charToByte(hexChars[pos + 1]) & 0xff)); // 字符占据的字节不是唯一。
        }
        return d;
    }

    /**
     * Convert char to byte
     *
     * @param c char
     * @return byte 字符转字节。
     */
    public static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    /**
     * 异或校验checksum
     *
     * @param datas
     * @return
     */
    public static byte getXor(byte[] datas) {
        byte temp = datas[0];
        for (int i = 1; i < datas.length; i++) {
            temp ^= datas[i];
        }
        return temp;
    }

    /**
     * Convert hex string to byte[] public byte[] hexStringToBytes(String
     * hexString); 每两个字符表示转化为一个字节，返回字节数组。 例：字符串"ABCDEF" 转化为byte数组 {0xAB,0xCD,0xEF}
     * 字符串"01" 转化为byte数组 {0x01}
     *
     * @return byte[]s
     */
    public static String bytesToHexString(byte[] src) {

        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();

    }

    public static String bytesToHexString(Byte[] src) {

        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();

    }

    public static String toHexWithBlank(byte[] a) {
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < a.length; i++) {
            String s = Integer.toHexString(a[i] & 0xff).toUpperCase();
            if (s.length() < 2) {
                b.append('0');
            }
            b.append(s).append(' ');
        }
        return b.toString();
    }

    public static String byteToAscIIStr(byte[] bytes, int start) {
        StringBuilder ver = new StringBuilder();
        if (bytes != null) {
            // 去掉第一位正响应
            for (int i = start; i < bytes.length; i++) {
                String hex = ByteUtil.getDataByIndex(bytes, i);
                ver.append((char) Integer.parseInt(hex, 16));
            }
        }
        return ver.toString();
    }


    /**
     * 数据复制，用于多帧回复的拼帧
     *
     * @param dest
     * @param index
     * @param origin
     * @return 多帧数据数组填充下标
     */
    public static int dataCopy(byte[] dest, int index, byte[] origin) {
        for (int i = 0; i < origin.length; i++) {
            if (index < dest.length) {
                dest[index] = origin[i];
            }
            index++;
        }
        return index;
    }

    public static byte[] Byte2byte(Byte[] Bytes) {
        byte[] bytes = new byte[Bytes.length];
        for (int i = 0; i < Bytes.length; i++) {
            bytes[i] = Bytes[i];
        }
        return bytes;
    }

    public static Byte[] byte2Byte(byte[] bytes) {
        Byte[] Bytes = new Byte[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            Bytes[i] = bytes[i];
        }
        return Bytes;
    }

    /**
     * System.arraycopy()方法
     *
     * @param bt1
     * @param bt2
     * @return
     */
    public static byte[] byteMerger(byte[] bt1, byte[] bt2) {
        byte[] bt3 = new byte[bt1.length + bt2.length];
        System.arraycopy(bt1, 0, bt3, 0, bt1.length);
        System.arraycopy(bt2, 0, bt3, bt1.length, bt2.length);
        return bt3;
    }

    /**
     * byte数组中取int数值，本方法适用于(低位在前，高位在后)的顺序
     */
    public static int bytesToInt(byte[] src, int offset) {
        int value;
        value = (int) ((src[offset] & 0xFF) | ((src[offset + 1] & 0xFF) << 8) | ((src[offset + 2] & 0xFF) << 16)
                | ((src[offset + 3] & 0xFF) << 24));
        return value;
    }
}
