package com.zhanghf.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhanghf
 * @version 1.0
 * @date 16:38 2020/2/10
 */
public class XmlMapUtils {

    /**
     * @param xml XML内容
     * @return map
     */
    public static Map<String, Object> xmlToMap(String xml) {
        Document doc = null;
        try {
            doc = DocumentHelper.parseText(xml);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        Map<String, Object> map = new HashMap<>();
        if (doc == null) {
            return map;
        }
        Element rootElement = doc.getRootElement();
        element2map(rootElement, map);
        return map;
    }

    /**
     * Element类提供的方法
     * getQName()	元素的QName对象
     * getNamespace()	元素所属的Namespace对象
     * getNamespacePrefix()	元素所属的Namespace对象的prefix
     * getNamespaceURI()	元素所属的Namespace对象的URI
     * getName()	元素的local name
     * getQualifiedName()	元素的qualified name
     * getText()	元素所含有的text内容，如果内容为空则返回一个空字符串而不是null
     * getTextTrim()	元素所含有的text内容，其中连续的空格被转化为单个空格，该方法不会返回null
     * attributeIterator()	元素属性的iterator，其中每个元素都是Attribute对象
     * attributeValue()	元素的某个指定属性所含的值
     * elementIterator()	元素的子元素的iterator，其中每个元素都是Element对象
     * element()	元素的某个指定（qualified name或者local name）的子元素
     * elementText()	元素的某个指定（qualified name或者local name）的子元素中的text信息
     * getParent	元素的父元素
     * getPath()	元素的XPath表达式，其中父元素的qualified name和子元素的qualified name之间使用"/"分隔
     * isTextOnly()	是否该元素只含有text或是空元素
     * isRootElement()	是否该元素是XML树的根节点
     *
     * @param element 元素
     * @param map     map
     */
    private static void element2map(Element element, Map<String, Object> map) {
        if (null == element) {
            return;
        }
        String name = element.getName();
        if (element.isTextOnly()) {
            map.put(name, element.getText());
        } else {
            Map<String, Object> mapSub = new HashMap<>();
            List<Element> elements = element.elements();
            for (Element elementSub : elements) {
                element2map(elementSub, mapSub);
            }
            Object object = map.get(name);
            if (null == object) {
                map.put(name, mapSub);
            } else {
                if (object instanceof List<?>) {
                    ((List) object).add(mapSub);
                } else {
                    List<Object> listSub = new ArrayList<>();
                    listSub.add(object);
                    listSub.add(mapSub);
                    map.put(name, listSub);
                }
            }
        }
    }

}
