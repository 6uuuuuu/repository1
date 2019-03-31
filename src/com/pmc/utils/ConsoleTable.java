package com.pmc.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Justerdu
 * 描述: 生成控制台表格
 * 记录：  当元素为中文时，会出现不对齐情况，主要是由于中文一个汉字的字节数虽然为2，但其在控制台所占字符空间并不为2。
 *
 */
public class ConsoleTable {

    @SuppressWarnings("rawtypes")
    private List<List> rows = new ArrayList<List>();    // table

    private int colum;    // 列长

    private int[] columLen;    // 存储每一列的长度

    private static int margin = 2;    // 内容与边框间距

    private boolean printHeader = false;    // 是否打印表头

    /**
     * @param colum
     * @param printHeader
     */
    public ConsoleTable(int colum, boolean printHeader) {
        this.printHeader = printHeader;
        this.colum = colum;
        this.columLen = new int[colum];
    }

    /**
     * Func: 添加行
     */
    public void appendRow() {
        List row = new ArrayList(colum);
        rows.add(row);
    }

    /**
     * Func: 添加列元素
     *
     * @param value
     * @return
     */
    public ConsoleTable appendColum(Object value) {
        if (value == null) {
            value = "NULL";
        }
        List row = rows.get(rows.size() - 1);    // 每一行的元素集
        row.add(value);
        int len = value.toString().getBytes().length;
        if (columLen[row.size() - 1] < len)
            columLen[row.size() - 1] = len;
        return this;    // 返回当前对象
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     * Func: 复写toString()方法，用于打印出控制台表格
     */
    public String toString() {

        StringBuilder buf = new StringBuilder();

        int sumlen = 0;
        for (int len : columLen) {
            sumlen += len;
        }
        if (printHeader)
            buf.append("|")
                    .append(printChar('=', sumlen + margin * 2 * colum
                            + (colum - 1))).append("|\n");
        else
            buf.append("|")
                    .append(printChar('-', sumlen + margin * 2 * colum
                            + (colum - 1))).append("|\n");
        for (int ii = 0; ii < rows.size(); ii++) {
            List row = rows.get(ii);
            for (int i = 0; i < colum; i++) {
                String o = "";
                if (i < row.size())
                    o = row.get(i).toString();
                buf.append('|').append(printChar(' ', margin)).append(o);
                buf.append(printChar(' ', columLen[i] - o.getBytes().length
                        + margin));
            }
            buf.append("|\n");
            if (printHeader && ii == 0)
                buf.append("|")
                        .append(printChar('=', sumlen + margin * 2 * colum
                                + (colum - 1))).append("|\n");
            else
                buf.append("|")
                        .append(printChar('-', sumlen + margin * 2 * colum
                                + (colum - 1))).append("|\n");
        }
        return buf.toString();
    }

    /**
     * Func: 打印给定长度的字符
     *
     * @param c
     * @param len
     * @return
     */
    private String printChar(char c, int len) {
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < len; i++) {
            buf.append(c);
        }
        return buf.toString();
    }

    public static void main(String[] args) {
        ConsoleTable t = new ConsoleTable(4, true);

        t.appendRow();
        t.appendColum("column 1").appendColum("column 2").appendColum("column 3").appendColum("column 4");

        t.appendRow();
        t.appendColum("1").appendColum("2").appendColum("3").appendColum("4");

        t.appendRow();
        t.appendColum("asd").appendColum("bwe").appendColum("ce").appendColum("drwrw");

        t.appendRow();
        t.appendColum("张三").appendColum("李四").appendColum("王五").appendColum("周六");
        System.out.println(t.toString());

    }
}
