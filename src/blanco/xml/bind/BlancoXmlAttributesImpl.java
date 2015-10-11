/*
 * blanco Framework
 * Copyright (C) 2004-2009 IGA Tosiki
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 */
/*******************************************************************************
 * Copyright (c) 2009 IGA Tosiki, NTT DATA BUSINESS BRAINS Corp.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IGA Tosiki (NTT DATA BUSINESS BRAINS Corp.) - initial API and implementation
 *******************************************************************************/
package blanco.xml.bind;

import java.util.List;

import org.xml.sax.Attributes;

import blanco.commons.util.BlancoStringUtil;
import blanco.xml.bind.valueobject.BlancoXmlAttribute;

/**
 * blancoXmlBinding で利用されるアトリビュート実装です。
 * 
 * このクラスは XML/バリューオブジェクトマッピング (X/Oマッピング) blancoXmlBinding の一部です。
 * 
 * @author IGA Tosiki
 */
public class BlancoXmlAttributesImpl implements Attributes {
    /**
     * 実際のアトリビュートのリスト。
     */
    private List<BlancoXmlAttribute> fAttrs = null;

    /**
     * アトリビュート実装のインスタンスを生成します。
     * 
     * @param attrs
     *            アトリビュートのリスト。
     */
    public BlancoXmlAttributesImpl(final List<BlancoXmlAttribute> attrs) {
        if (attrs == null) {
            throw new IllegalArgumentException(
                    "BlancoXmlMarshallerAttributesImplのコンストラクタにnullの引数が与えられました。");
        }

        fAttrs = attrs;
    }

    /**
     * リスト内にある属性の数を返します。
     * 
     * @return 属性の数。
     */
    public int getLength() {
        return fAttrs.size();
    }

    /**
     * 属性の名前空間URI を検索します。
     * 
     * @param index
     *            属性インディックス。
     * @return 名前空間URI。
     */
    public String getURI(int index) {
        final BlancoXmlAttribute attribute = getAttr(index);
        if (attribute == null) {
            return null;
        }

        return attribute.getUri();
    }

    /**
     * 属性のローカル名を検索します。
     * 
     * @param index
     *            属性インディックス。
     * @return ローカル名。
     */
    public String getLocalName(int index) {
        final BlancoXmlAttribute attribute = getAttr(index);
        if (attribute == null) {
            return null;
        }

        return attribute.getLocalName();
    }

    /**
     * 属性の XML 1.0 修飾名を検索します。
     * 
     * @param index
     *            属性インディックス。
     * @return 修飾名。
     */
    public String getQName(int index) {
        final BlancoXmlAttribute attribute = getAttr(index);
        if (attribute == null) {
            return null;
        }

        final String qName = BlancoStringUtil.null2Blank(attribute.getQName());
        if (qName.length() == 0) {
            // qNameに値が指定されていない場合にはlocalNameを戻します。
            return getLocalName(index);
        }

        return attribute.getQName();
    }

    /**
     * 属性の型を検索します。
     * 
     * @param index
     *            属性インディックス。
     * @return 属性の型。
     */
    public String getType(int index) {
        final BlancoXmlAttribute attribute = getAttr(index);
        if (attribute == null) {
            return null;
        }

        return attribute.getType();
    }

    /**
     * 属性の値を検索します。
     * 
     * @param index
     *            属性インディックス。
     * @return 属性の値。
     */
    public String getValue(int index) {
        final BlancoXmlAttribute attribute = getAttr(index);
        if (attribute == null) {
            return null;
        }

        return attribute.getValue();
    }

    /**
     * 属性のインデックスを検索します。
     * 
     * @param uri
     *            名前空間URI。
     * @param localName
     *            ローカル名。
     * @return 属性インデックス。リスト内に該当する属性が存在しない場合は -1。
     */
    public int getIndex(final String uri, final String localName) {
        final int attrSize = fAttrs.size();
        for (int index = 0; index < attrSize; index++) {
            final BlancoXmlAttribute attrLook = (BlancoXmlAttribute) fAttrs
                    .get(index);
            if (BlancoStringUtil.null2Blank(attrLook.getUri()).equals(uri)
                    && BlancoStringUtil.null2Blank(attrLook.getLocalName())
                            .equals(localName)) {
                return index;
            }
        }

        // 発見できませんでした。
        return -1;
    }

    /**
     * 属性のインデックスを検索します。
     * 
     * @param qName
     *            修飾名。
     * @return 属性インデックス。リスト内に該当する属性が存在しない場合は -1。
     */
    public int getIndex(final String qName) {
        final int attrSize = fAttrs.size();
        for (int index = 0; index < attrSize; index++) {
            final BlancoXmlAttribute attrLook = (BlancoXmlAttribute) fAttrs
                    .get(index);
            if (BlancoStringUtil.null2Blank(attrLook.getQName()).equals(qName)) {
                return index;
            }
        }

        // 発見できませんでした。
        return -1;
    }

    /**
     * 属性の型を検索します。
     * 
     * @param uri
     *            名前空間URI。
     * @param localName
     *            ローカル名。
     * @return 属性の型。
     */
    public String getType(final String uri, final String localName) {
        final BlancoXmlAttribute attrFound = findByUriLocalName(uri, localName);
        if (attrFound == null) {
            // 発見できませんでした。
            return null;
        }

        return attrFound.getType();
    }

    /**
     * 属性の型を検索します。
     * 
     * @param qName
     *            修飾名。
     * @return 属性の型。
     */
    public String getType(final String qName) {
        final BlancoXmlAttribute attrFound = findByQName(qName);
        if (attrFound == null) {
            // 発見できませんでした。
            return null;
        }

        return attrFound.getType();
    }

    /**
     * 属性の値を検索します。
     * 
     * @param uri
     *            名前空間URI。
     * @param localName
     *            ローカル名。
     * @return 属性の値。
     */
    public String getValue(final String uri, final String localName) {
        final BlancoXmlAttribute attrFound = findByUriLocalName(uri, localName);
        if (attrFound == null) {
            // 発見できませんでした。
            return null;
        }

        return attrFound.getValue();
    }

    /**
     * 属性の値を検索します。
     * 
     * @param qName
     *            修飾名。
     * @return 属性の値。
     */
    public String getValue(final String qName) {
        final BlancoXmlAttribute attrFound = findByQName(qName);
        if (attrFound == null) {
            // 発見できませんでした。
            return null;
        }

        return attrFound.getValue();
    }

    /**
     * 特別に用意している、内部を暴露するためのメソッド。
     * 
     * 通常は、このメソッドは利用しません。
     * 
     * @return 属性リスト。
     */
    public List<BlancoXmlAttribute> getList() {
        return fAttrs;
    }

    /**
     * 指定のインディックスにある属性を取得します。
     * 
     * @param index
     *            属性インディックス。
     * @return 属性。指定のindexが範囲外の場合はnull。
     */
    private BlancoXmlAttribute getAttr(int index) {
        if (index >= fAttrs.size()) {
            return null;
        }

        return (BlancoXmlAttribute) fAttrs.get(index);
    }

    /**
     * 名前空間URIとローカル名を用いて属性を検索します。
     * 
     * @param uri
     *            名前空間URI。
     * @param localName
     *            ローカル名。
     * @return 属性。
     */
    private BlancoXmlAttribute findByUriLocalName(final String uri,
            final String localName) {
        final int attrSize = fAttrs.size();
        for (int index = 0; index < attrSize; index++) {
            final BlancoXmlAttribute attrLook = (BlancoXmlAttribute) fAttrs
                    .get(index);
            if (BlancoStringUtil.null2Blank(attrLook.getUri()).equals(uri)
                    && BlancoStringUtil.null2Blank(attrLook.getLocalName())
                            .equals(localName)) {
                return attrLook;
            }
        }

        // 発見できませんでした。
        return null;
    }

    /**
     * 修飾名を用いて属性を検索します。
     * 
     * @param qName
     *            修飾名。
     * @return 属性。
     */
    private BlancoXmlAttribute findByQName(final String qName) {
        final int attrSize = fAttrs.size();
        for (int index = 0; index < attrSize; index++) {
            final BlancoXmlAttribute attrLook = (BlancoXmlAttribute) fAttrs
                    .get(index);
            if (BlancoStringUtil.null2Blank(attrLook.getUri()).equals(qName)) {
                return attrLook;
            }
        }

        // 発見できませんでした。
        return null;
    }
}
