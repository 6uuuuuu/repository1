package com.pmc.utills;

import com.pmc.bean.Clothes;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.Xpp3Driver;

import java.util.ArrayList;
import java.util.List;

/**
 * 对products.xml文件进行xml解析
 */
public class ProductXmlUtils {

    public static List<Clothes> productXmlParse() {
        List<Clothes> clothesList = new ArrayList<>();
        XStream xStream = new XStream(new Xpp3Driver());
        return clothesList;
    }
}
