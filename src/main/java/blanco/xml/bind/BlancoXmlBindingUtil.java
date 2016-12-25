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

import java.util.ArrayList;
import java.util.List;

import blanco.xml.bind.valueobject.BlancoXmlAttribute;
import blanco.xml.bind.valueobject.BlancoXmlCdata;
import blanco.xml.bind.valueobject.BlancoXmlCharacters;
import blanco.xml.bind.valueobject.BlancoXmlDocument;
import blanco.xml.bind.valueobject.BlancoXmlElement;
import blanco.xml.bind.valueobject.BlancoXmlNode;

/**
 * blancoXmlBinding のバリューオブジェクト表現に対する操作支援ユーティリティです。
 * 
 * @author IGA Tosiki
 */
public class BlancoXmlBindingUtil {
    /**
     * このクラスはインスタンス化はしません。
     */
    protected BlancoXmlBindingUtil() {
    }

    /**
     * 与えられたドキュメントからエレメントを取得します。
     * 
     * 最初に見つかったエレメントを戻します。
     * 
     * @param argDocument
     *            検索対象となるドキュメント。
     * @return (最初に見つかった)エレメント。エレメントが見つからなかった場合にはnullが戻ります。
     */
    public static final BlancoXmlElement getDocumentElement(
            final BlancoXmlDocument argDocument) {
        final List<blanco.xml.bind.valueobject.BlancoXmlNode> listRoot = argDocument
                .getChildNodes();
        for (int index = 0; index < listRoot.size(); index++) {
            final BlancoXmlNode objLook = listRoot.get(index);
            if (objLook instanceof BlancoXmlElement == false) {
                continue;
            }
            return (BlancoXmlElement) objLook;
        }

        // ひとつも見つかりませんでした。
        return null;
    }

    /**
     * 与えられたエレメントの子ノードからエレメントを取得します。
     * 
     * 検索に合致した全てのエレメントを戻します。
     * 
     * @param argElement
     *            検索対象となるエレメント。
     * @param argTagname
     *            検索したいタグ名。(ローカル名)
     * @return 見つかったエレメントのリスト。エレメントが見つからなかった場合には空のListが戻ります。
     */
    public static final List<blanco.xml.bind.valueobject.BlancoXmlElement> getElementsByTagName(
            final BlancoXmlElement argElement, final String argTagname) {
        if (argElement == null) {
            throw new IllegalArgumentException(
                    "BlancoXmlBindingUtil.getElementsByTagName: 引数(エレメント)にnullが与えられました。");
        }

        final List<blanco.xml.bind.valueobject.BlancoXmlElement> listResult = new ArrayList<blanco.xml.bind.valueobject.BlancoXmlElement>();

        final List<blanco.xml.bind.valueobject.BlancoXmlNode> listChild = argElement
                .getChildNodes();
        for (int index = 0; index < listChild.size(); index++) {
            final BlancoXmlNode objLook = listChild.get(index);
            if (objLook instanceof BlancoXmlElement == false) {
                continue;
            }

            final BlancoXmlElement elementLook = (BlancoXmlElement) objLook;
            if (elementLook.getLocalName().equals(argTagname)) {
                listResult.add((BlancoXmlElement) objLook);
            }
        }

        return listResult;
    }

    /**
     * 与えられたエレメントの子ノードからエレメントを取得します。
     * 
     * 最初に見つかったエレメントを戻します。複数のエレメントが合致したとしても２つ目以降は無視されます。
     * 
     * @param argElement
     *            検索対象となるエレメント。
     * @param argTagname
     *            検索したいタグ名。(ローカル名)
     * @return 最初に見つかったエレメント。エレメントが見つからなかった場合にはnullが戻ります。
     */
    public static final BlancoXmlElement getElement(
            final BlancoXmlElement argElement, final String argTagname) {
        final List<blanco.xml.bind.valueobject.BlancoXmlNode> listChild = argElement
                .getChildNodes();
        for (int index = 0; index < listChild.size(); index++) {
            final BlancoXmlNode objLook = listChild.get(index);
            if (objLook instanceof BlancoXmlElement == false) {
                continue;
            }

            final BlancoXmlElement elementLook = (BlancoXmlElement) objLook;
            if (elementLook.getLocalName().equals(argTagname)) {
                return elementLook;
            }
        }

        // ひとつも見つかりませんでした。
        return null;
    }

    /**
     * 選択されたノード(エレメントでも可)から文字列を取得します。
     * 
     * あるノードにぶらさがっている全てのテキストデータを取得する場合に利用します。
     * 
     * @param argElement
     *            対象とするターゲットエレメント
     * @return 取得されたテキスト文字列。取得されなかった場合にはnullが戻ります。
     */
    public static final String getTextContent(final BlancoXmlElement argElement) {
        if (argElement == null) {
            throw new IllegalArgumentException(
                    "ノードからテキストを取得するメソッドにnullが与えられました。null以外の値を与えるようにしてください。");
        }

        final StringBuffer result = new StringBuffer();
        boolean isProcessed = false;

        final List<blanco.xml.bind.valueobject.BlancoXmlNode> listText = argElement
                .getChildNodes();
        final int sizeChildList = listText.size();
        for (int indexChild = 0; indexChild < sizeChildList; indexChild++) {
            final BlancoXmlNode objLook = listText.get(indexChild);
            if (objLook instanceof BlancoXmlCharacters) {
                final BlancoXmlCharacters textLook = (BlancoXmlCharacters) objLook;
                result.append(textLook.getValue());
                isProcessed = true;
            } else if (objLook instanceof BlancoXmlCdata) {
                final BlancoXmlCdata cdataLook = (BlancoXmlCdata) objLook;
                result.append(getTextContent(cdataLook));
                isProcessed = true;
            }
            // BlancoXmlIgnorableWhitespace は処理対象からはずします。
        }

        if (isProcessed == false) {
            return null;
        } else {
            return result.toString();
        }
    }

    /**
     * エレメントから指定のタグ名の文字列を読み込みます。
     * 
     * @param elementTarget
     *            対象とするターゲットエレメント
     * @param tagName
     *            タグ名
     * @return 取得されたテキスト文字列。取得されなかった場合にはnullが戻ります。
     */
    public static final String getTextContent(
            final BlancoXmlElement elementTarget, final String tagName) {
        if (elementTarget == null) {
            throw new IllegalArgumentException(
                    "エレメントからテキストを取得するメソッドにエレメントとしてnullが与えられました。null以外の値を与えるようにしてください。");
        }
        if (tagName == null) {
            throw new IllegalArgumentException(
                    "エレメントからテキストを取得するメソッドにタグ名としてnullが与えられました。null以外の値を与えるようにしてください。");
        }

        final StringBuffer result = new StringBuffer();
        boolean isProcessed = false;

        final List<blanco.xml.bind.valueobject.BlancoXmlElement> listElementTarget = getElementsByTagName(
                elementTarget, tagName);
        final int sizeList = listElementTarget.size();
        for (int index = 0; index < sizeList; index++) {
            final BlancoXmlNode nodeLook = listElementTarget.get(index);
            if (nodeLook instanceof BlancoXmlElement) {
                final BlancoXmlElement elementLook = (BlancoXmlElement) nodeLook;

                final List<blanco.xml.bind.valueobject.BlancoXmlNode> listText = elementLook
                        .getChildNodes();
                final int sizeChildList = listText.size();
                for (int indexChild = 0; indexChild < sizeChildList; indexChild++) {
                    final BlancoXmlNode nodeChild = listText.get(indexChild);
                    if (nodeChild instanceof BlancoXmlCharacters) {
                        final BlancoXmlCharacters textLook = (BlancoXmlCharacters) nodeChild;
                        result.append(textLook.getValue());
                        isProcessed = true;
                    } else if (nodeChild instanceof BlancoXmlCdata) {
                        final BlancoXmlCdata cdataLook = (BlancoXmlCdata) nodeChild;
                        result.append(getTextContent(cdataLook));
                        isProcessed = true;
                    }
                    // BlancoXmlIgnorableWhitespace は処理対象からはずします。
                }
            }
        }

        if (isProcessed == false) {
            return null;
        } else {
            return result.toString();
        }
    }

    /**
     * 与えられた CDATA からテキストを読み出します。
     * 
     * @param argCdata
     *            CDATA。
     * @return テキスト。
     */
    public static final String getTextContent(final BlancoXmlCdata argCdata) {
        if (argCdata == null) {
            throw new IllegalArgumentException(
                    "CDATAからテキストを取得するメソッドにnullが与えられました。null以外の値を与えるようにしてください。");
        }

        final StringBuffer result = new StringBuffer();
        boolean isProcessed = false;

        final List<blanco.xml.bind.valueobject.BlancoXmlNode> listText = argCdata
                .getChildNodes();
        final int sizeChildList = listText.size();
        for (int indexChild = 0; indexChild < sizeChildList; indexChild++) {
            final BlancoXmlNode objLook = listText.get(indexChild);
            if (objLook instanceof BlancoXmlCharacters) {
                final BlancoXmlCharacters textLook = (BlancoXmlCharacters) objLook;
                result.append(textLook.getValue());
                isProcessed = true;
            } else if (objLook instanceof BlancoXmlCdata) {
                final BlancoXmlCdata cdataLook = (BlancoXmlCdata) objLook;
                for (int index = 0; index < cdataLook.getChildNodes().size(); index++) {

                    result.append(getTextContent(cdataLook));
                }
                isProcessed = true;
            }
            // BlancoXmlIgnorableWhitespace は処理対象からはずします。
        }

        if (isProcessed == false) {
            return null;
        } else {
            return result.toString();
        }
    }

    /**
     * エレメントから与えられたQNameによるアトリビュート値を取得します。
     * 
     * @param argElement
     *            エレメント。
     * @param argQName
     *            名前。
     * @return アトリビュート値。
     */
    public static final String getAttribute(final BlancoXmlElement argElement,
            final String argQName) {
        for (int indexAttr = 0; indexAttr < argElement.getAtts().size(); indexAttr++) {
            final BlancoXmlAttribute attr = argElement.getAtts().get(indexAttr);
            if (argQName.equals(attr.getQName())) {
                return attr.getValue();
            }
        }

        // 発見できませんでした。
        return null;
    }
}
