package com.pmc.utills;

import com.pmc.bean.Clothes;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.Xpp3Driver;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 对products.xml文件进行xml解析
 */
public class ProductXmlUtils {

    public static List<Clothes> productXmlParse() {
        List<Clothes> clothesList = new ArrayList<>();
        XStream xStream = new XStream(new Xpp3Driver());
        xStream.alias("list",clothesList.getClass());
        xStream.alias("clothes", Clothes.class);
        xStream.useAttributeFor(Clothes.class, "id");
        try {
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream("products.xml"));
            clothesList = ((List<Clothes>) xStream.fromXML(bis));
            bis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return clothesList;
    }

    public static void productXmlWrite(List<Clothes> clothesList) {
        XStream xStream = new XStream(new Xpp3Driver());
        xStream.alias("list",clothesList.getClass());
        xStream.alias("clothes", Clothes.class);
        xStream.useAttributeFor(Clothes.class, "id");
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("products.xml"));
            bos.write("<?xml version = \"1.0\" encoding = \"UTF-8\" ?>".getBytes());
            xStream.toXML(clothesList,bos);
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
