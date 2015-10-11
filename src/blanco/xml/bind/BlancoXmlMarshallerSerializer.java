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

import javax.xml.transform.sax.TransformerHandler;

import org.xml.sax.Locator;
import org.xml.sax.SAXException;

import blanco.commons.util.BlancoStringUtil;
import blanco.xml.bind.valueobject.BlancoXmlAttribute;
import blanco.xml.bind.valueobject.BlancoXmlCdata;
import blanco.xml.bind.valueobject.BlancoXmlCharacters;
import blanco.xml.bind.valueobject.BlancoXmlComment;
import blanco.xml.bind.valueobject.BlancoXmlDocument;
import blanco.xml.bind.valueobject.BlancoXmlDtd;
import blanco.xml.bind.valueobject.BlancoXmlElement;
import blanco.xml.bind.valueobject.BlancoXmlIgnorableWhitespace;
import blanco.xml.bind.valueobject.BlancoXmlNode;
import blanco.xml.bind.valueobject.BlancoXmlPrefixMapping;

/**
 * blancoXmlBinding のバリューオブジェクト表現から XML を生成するためのシリアライザ実装です。
 * 
 * このクラスは XML/バリューオブジェクトマッピング (X/Oマッピング) blancoXmlBinding の一部です。
 * 
 * @author IGA Tosiki
 */
public class BlancoXmlMarshallerSerializer {
    /**
     * 内部的に利用する出力用SAXハンドラ<br>
     * 連結先のストリームは外部でcloseされる必要があります。
     */
    private TransformerHandler fSaxHandler;

    /**
     * SAXシリアライザを用いた直列化クラスを生成します。
     * 
     * @param argHandler
     *            SAXシリアライザのハンドラ。
     */
    public BlancoXmlMarshallerSerializer(final TransformerHandler argHandler) {
        fSaxHandler = argHandler;
    }

    /**
     * 与えられたDocumentをSAXシリアライザへと展開します。
     * 
     * @param document
     *            ドキュメントオブジェクト。
     * @throws SAXException
     *             SAX例外が発生した場合。
     */
    public void serialize(final BlancoXmlDocument document) throws SAXException {
        expandLocator(document);

        fSaxHandler.startDocument();

        // プレフィックス・マッピングを展開。
        for (final BlancoXmlPrefixMapping prefixMapping : document
                .getPrefixMappings()) {
            fSaxHandler.startPrefixMapping(BlancoStringUtil
                    .null2Blank(prefixMapping.getPrefix()), BlancoStringUtil
                    .null2Blank(prefixMapping.getUri()));
            fSaxHandler.endPrefixMapping(BlancoStringUtil
                    .null2Blank(prefixMapping.getPrefix()));
        }

        // 最初の改行。
        // ポイント: ローカルシステムの改行コードによらずに、\n を出力します。
        fSaxHandler.characters("\n".toCharArray(), 0, 1);

        final int sizeChildNodes = document.getChildNodes().size();
        for (int index = 0; index < sizeChildNodes; index++) {
            final BlancoXmlNode objLook = document.getChildNodes().get(index);
            if (objLook instanceof BlancoXmlDtd) {
                expandDtd((BlancoXmlDtd) objLook);
            } else if (objLook instanceof BlancoXmlElement) {
                expandElement((BlancoXmlElement) objLook);
            } else {
                throw new IllegalArgumentException(
                        "BlancoXmlMarshallerSerializer: 想定しない箇所を通過しました。");
            }
        }

        fSaxHandler.endDocument();
    }

    /**
     * ロケータを展開します。
     * 
     * @param document
     *            ドキュメントオブジェクト。
     */
    private void expandLocator(final BlancoXmlDocument document) {
        if (document.getLocator() != null) {
            fSaxHandler.setDocumentLocator(new Locator() {
                public String getPublicId() {
                    // Locatorの各メソッドはnullを戻す可能性があります。
                    return document.getLocator().getPublicId();
                }

                public String getSystemId() {
                    // Locatorの各メソッドはnullを戻す可能性があります。
                    return document.getLocator().getSystemId();
                }

                public int getLineNumber() {
                    return document.getLocator().getLineNumber();
                }

                public int getColumnNumber() {
                    return document.getLocator().getColumnNumber();
                }
            });
        }
    }

    /**
     * DTDを展開します。
     * 
     * @param argDtd
     *            DTD情報。
     * @throws SAXException
     *             SAX例外が発生した場合。
     */
    private void expandDtd(final BlancoXmlDtd argDtd) throws SAXException {
        fSaxHandler.startDTD(argDtd.getName(), argDtd.getPublicId(), argDtd
                .getSystemId());
        fSaxHandler.endDTD();
    }

    /**
     * エレメントを展開します。
     * 
     * @param element
     *            エレメント。
     * @throws SAXException
     *             SAX例外が発生した場合。
     */
    private void expandElement(final BlancoXmlElement element)
            throws SAXException {
        if (BlancoStringUtil.null2Blank(element.getLocalName()).length() == 0
                && BlancoStringUtil.null2Blank(element.getQName()).length() == 0) {
            throw new IllegalArgumentException(
                    "localNameもQNameも指定されていないElementがあります。処理中断します。");
        }

        String qName = element.getQName();
        if (BlancoStringUtil.null2Blank(qName).length() == 0) {
            // QNameに何も指定されていない場合にはlocalNameの値をセットします。
            qName = element.getLocalName();
        }

        // アトリビュートについても QNameが無指定の場合には localNameを複写するなどnull対策をほどこします。
        for (int index = 0; index < element.getAtts().size(); index++) {
            final BlancoXmlAttribute attrLook = (BlancoXmlAttribute) element
                    .getAtts().get(index);
            attrLook.setLocalName(BlancoStringUtil.null2Blank(attrLook
                    .getLocalName()));
            attrLook.setType(BlancoStringUtil.null2Blank(attrLook.getType()));
            attrLook.setValue(BlancoStringUtil.null2Blank(attrLook.getValue()));

            if (BlancoStringUtil.null2Blank(attrLook.getQName()).length() == 0) {
                // QNameに何も指定されていない場合にはlocalNameの値をセットします。
                attrLook.setQName(attrLook.getLocalName());
            }
            if (attrLook.getQName().length() == 0) {
                // それでもQNameがブランクの場合。
                throw new IllegalArgumentException(
                        "アトリビュートのQNameがnullのまま与えられました。localNameからも導出できませんでした。:"
                                + element.toString());
            }
        }

        // 指定が無い場合にはnullではなく空の文字列を渡す必要がある点に注意。
        fSaxHandler.startElement(BlancoStringUtil.null2Blank(element.getUri()),
                BlancoStringUtil.null2Blank(element.getLocalName()), qName,
                new BlancoXmlAttributesImpl(element.getAtts()));

        // 子ノードを展開します。
        final int sizeChildNodes = element.getChildNodes().size();
        for (int index = 0; index < sizeChildNodes; index++) {
            final BlancoXmlNode objLook = element.getChildNodes().get(index);
            if (objLook instanceof BlancoXmlElement) {
                expandElement((BlancoXmlElement) objLook);
            } else if (objLook instanceof BlancoXmlCharacters) {
                expandCharacters((BlancoXmlCharacters) objLook);
            } else if (objLook instanceof BlancoXmlIgnorableWhitespace) {
                expandIgnorableWhitespace((BlancoXmlIgnorableWhitespace) objLook);
            } else if (objLook instanceof BlancoXmlComment) {
                expandComment((BlancoXmlComment) objLook);
            } else if (objLook instanceof BlancoXmlCdata) {
                expandCdata((BlancoXmlCdata) objLook);
            } else {
                throw new IllegalArgumentException(
                        "BlancoXmlMarshallerSerializer: 想定しない箇所を通過しました。");
            }
        }

        // 指定が無い場合にはnullではなく空の文字列を渡す必要がある点に注意。
        fSaxHandler.endElement(BlancoStringUtil.null2Blank(element.getUri()),
                BlancoStringUtil.null2Blank(element.getLocalName()), qName);
    }

    /**
     * 文字データを展開します。
     * 
     * @param argCharacters
     *            文字データ。
     * @throws SAXException
     *             SAX例外が発生した場合。
     */
    private void expandCharacters(final BlancoXmlCharacters argCharacters)
            throws SAXException {
        if (argCharacters == null) {
            throw new IllegalArgumentException(
                    "expandCharactersの引数にnullが与えられました。");
        }
        if (argCharacters.getValue() == null) {
            // nullが指定されている場合には "" を代入して例外が発生しないようにします。
            argCharacters.setValue("");
        }

        final char[] buf = argCharacters.getValue().toCharArray();
        fSaxHandler.characters(buf, 0, buf.length);
    }

    /**
     * 無視可能な空白データを展開します。
     * 
     * @param argIgnorableWhitespace
     *            無視可能な空白データ。
     * @throws SAXException
     *             SAX例外が発生した場合。
     */
    private void expandIgnorableWhitespace(
            final BlancoXmlIgnorableWhitespace argIgnorableWhitespace)
            throws SAXException {
        final char[] buf = argIgnorableWhitespace.getValue().toCharArray();
        fSaxHandler.ignorableWhitespace(buf, 0, buf.length);
    }

    /**
     * コメントを展開します。
     * 
     * @param argComment
     *            コメントオブジェクト。
     * @throws SAXException
     *             SAX例外が発生した場合。
     */
    private void expandComment(final BlancoXmlComment argComment)
            throws SAXException {
        final char[] buf = argComment.getValue().toCharArray();
        fSaxHandler.comment(buf, 0, buf.length);
    }

    /**
     * CDATAを展開します。
     * 
     * @param argCdata
     *            CDATAオブジェクト。
     * @throws SAXException
     *             SAX例外が発生した場合。
     */
    private void expandCdata(final BlancoXmlCdata argCdata) throws SAXException {
        fSaxHandler.startCDATA();

        // 子ノードを展開します。
        final int sizeChildNodes = argCdata.getChildNodes().size();
        for (int index = 0; index < sizeChildNodes; index++) {
            final BlancoXmlNode objLook = argCdata.getChildNodes().get(index);
            if (objLook instanceof BlancoXmlElement) {
                expandElement((BlancoXmlElement) objLook);
            } else if (objLook instanceof BlancoXmlCharacters) {
                expandCharacters((BlancoXmlCharacters) objLook);
            } else if (objLook instanceof BlancoXmlIgnorableWhitespace) {
                expandIgnorableWhitespace((BlancoXmlIgnorableWhitespace) objLook);
            } else if (objLook instanceof BlancoXmlComment) {
                expandComment((BlancoXmlComment) objLook);
            } else {
                throw new IllegalArgumentException(
                        "BlancoXmlMarshallerSerializer: 想定しない箇所を通過しました。");
            }
        }

        fSaxHandler.endCDATA();
    }
}
