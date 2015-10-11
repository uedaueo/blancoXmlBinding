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

import java.util.Stack;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.ext.LexicalHandler;

import blanco.xml.bind.valueobject.BlancoXmlAttribute;
import blanco.xml.bind.valueobject.BlancoXmlCdata;
import blanco.xml.bind.valueobject.BlancoXmlCharacters;
import blanco.xml.bind.valueobject.BlancoXmlComment;
import blanco.xml.bind.valueobject.BlancoXmlDocument;
import blanco.xml.bind.valueobject.BlancoXmlDtd;
import blanco.xml.bind.valueobject.BlancoXmlElement;
import blanco.xml.bind.valueobject.BlancoXmlIgnorableWhitespace;
import blanco.xml.bind.valueobject.BlancoXmlLocator;
import blanco.xml.bind.valueobject.BlancoXmlNode;
import blanco.xml.bind.valueobject.BlancoXmlPrefixMapping;

/**
 * XML から blancoXmlBinding のバリューオブジェクト表現を生成するためのコンテンツハンドラ実装です。
 * 
 * このクラスは XML/バリューオブジェクトマッピング (X/Oマッピング) blancoXmlBinding の一部です。
 * 
 * javax.xml.transform.sax.TransformerHandler の裏返しになります。
 * 
 * @author IGA Tosiki
 */
public class BlancoXmlUnmarshallerContentHandler implements ContentHandler,
        LexicalHandler, DTDHandler {
    /**
     * メモリ効率のためなどに文字列のキャッシュなどを実験しましたが、むしろ悪化しました。<br>
     * 現時点では小細工はせずに、処理の改善に注力することとします。
     * 
     * 効果のなかった実装は、以下のような Mapをもちいた 文字列共有化。 // private Map<String, String>
     * fStringMap = new HashMap<String, String>();
     */

    /**
     * ルートドキュメントを記憶します。
     */
    protected final BlancoXmlDocument fDocument = new BlancoXmlDocument();

    /**
     * 現在処理中のポイントを記憶します。
     */
    protected final Stack<BlancoXmlNode> fDocumentElementStack = new Stack<BlancoXmlNode>();

    /**
     * コンテントハンドラオブジェクトを新規作成します。
     */
    public BlancoXmlUnmarshallerContentHandler() {
        fDocumentElementStack.push(fDocument);
    }

    /**
     * ルートドキュメントを取得します。
     * 
     * @return XML ドキュメント。
     */
    public BlancoXmlDocument getDocument() {
        return fDocument;
    }

    // ------------------------------------------------
    // ここからは ContentHandler のためのメソッド。
    // ------------------------------------------------

    /**
     * ContentHandler のドキュメント開始イベント。
     */
    public void startDocument() throws SAXException {
        // 既にpush済みです。
    }

    /**
     * ContentHandler のドキュメント終了イベント。
     */
    public void endDocument() throws SAXException {
        fDocumentElementStack.pop();
    }

    /**
     * ContentHandler のロケータイベント。
     * 
     * @param argLocator
     *            SAX ドキュメントイベントの位置。
     */
    public void setDocumentLocator(final Locator argLocator) {
        final BlancoXmlLocator locator = new BlancoXmlLocator();

        // 現在処理中のオブジェクトを取得します。
        final BlancoXmlNode objCurrent = fDocumentElementStack.peek();
        if (objCurrent instanceof BlancoXmlDocument == false) {
            throw new IllegalArgumentException(
                    "BlancoXmlUnmarshallerContentHandler: Documentではないものに対して Locatorをセットしようとしました。");
        }

        ((BlancoXmlDocument) objCurrent).setLocator(locator);

        locator.setPublicId(argLocator.getPublicId());
        locator.setSystemId(argLocator.getSystemId());
        locator.setLineNumber(argLocator.getLineNumber());
        locator.setColumnNumber(argLocator.getColumnNumber());
    }

    /**
     * ContentHandler の PrefixMapping 開始イベント。
     * 
     * @param prefix
     *            名前空間プレフィックス。
     * @param uri
     *            プレフィックスがマップされた名前空間 URI。
     */
    public void startPrefixMapping(final String prefix, final String uri)
            throws SAXException {
        final BlancoXmlPrefixMapping prefixMapping = new BlancoXmlPrefixMapping();
        prefixMapping.setPrefix(prefix);
        prefixMapping.setUri(uri);

        // 現在処理中のオブジェクトを取得します。
        final BlancoXmlNode objCurrent = fDocumentElementStack.peek();
        if (objCurrent instanceof BlancoXmlDocument) {
            ((BlancoXmlDocument) objCurrent).getPrefixMappings().add(
                    prefixMapping);
        } else if (objCurrent instanceof BlancoXmlElement) {
            // 要素の場合の startPrefixMapping は無視します。
        } else {
            throw new IllegalArgumentException(
                    "BlancoXmlUnmarshallerContentHandler: Document でも Element でもないものに対して prefixMapping をセットしようとしました。");
        }
    }

    /**
     * ContentHandler の PrefixMapping 終了イベント。
     * 
     * @param prefix
     *            名前空間プレフィックス。
     */
    public void endPrefixMapping(final String prefix) throws SAXException {
    }

    /**
     * ContentHandler の要素開始イベント。
     * 
     * @param uri
     *            プレフィックスがマップされた名前空間 URI。
     * @param localName
     *            ローカル名。
     * @param qName
     *            プレフィックス付き修飾名。
     * @param atts
     *            アトリビュート一覧。
     */
    public void startElement(final String uri, final String localName,
            final String qName, final Attributes atts) throws SAXException {
        final BlancoXmlElement element = new BlancoXmlElement();

        // 現在処理中のオブジェクトを取得します。
        final BlancoXmlNode objCurrent = fDocumentElementStack.peek();
        if (objCurrent instanceof BlancoXmlElement) {
            final BlancoXmlElement elementParent = ((BlancoXmlElement) objCurrent);
            elementParent.getChildNodes().add(element);
        } else if (objCurrent instanceof BlancoXmlDocument) {
            ((BlancoXmlDocument) objCurrent).getChildNodes().add(element);
        } else {
            throw new IllegalArgumentException(
                    "BlancoXmlUnmarshallerContentHandler: 想定されない型["
                            + objCurrent.getClass().getName()
                            + "]に対して Elementを追加しようとしました。");
        }

        // エレメントをコピーします。
        element.setUri(uri);
        element.setLocalName(localName);
        element.setQName(qName);

        // アトリビュートのコピーを行います。
        copyAttributes(atts, element);

        fDocumentElementStack.push(element);
    }

    /**
     * アトリビュートのコピーを行います。
     * 
     * @param atts
     *            アトリビュート一覧。
     * @param element
     *            複写先のエレメント。
     */
    protected void copyAttributes(final Attributes atts,
            final BlancoXmlElement element) {
        final int attrLength = atts.getLength();
        for (int index = 0; index < attrLength; index++) {
            final BlancoXmlAttribute attribute = new BlancoXmlAttribute();
            element.getAtts().add(attribute);

            attribute.setUri(atts.getURI(index));
            attribute.setLocalName(atts.getLocalName(index));
            attribute.setQName(atts.getQName(index));
            attribute.setType(atts.getType(index));
            attribute.setValue(atts.getValue(index));
        }
    }

    /**
     * ContentHandler の要素終了イベント。
     * 
     * @param uri
     *            プレフィックスがマップされた名前空間 URI。
     * @param localName
     *            ローカル名。
     * @param qName
     *            プレフィックス付き修飾名。
     */
    public void endElement(final String uri, final String localName,
            final String qName) throws SAXException {
        fDocumentElementStack.pop();
    }

    /**
     * ContentHandler の文字イベント。
     * 
     * @param ch
     * @param start
     * @param length
     */
    public void characters(final char[] ch, final int start, final int length)
            throws SAXException {
        final BlancoXmlCharacters characters = new BlancoXmlCharacters();

        // 現在処理中のオブジェクトを取得します。
        final BlancoXmlNode objCurrent = fDocumentElementStack.peek();
        if (objCurrent instanceof BlancoXmlElement) {
            ((BlancoXmlElement) objCurrent).getChildNodes().add(characters);
        } else if (objCurrent instanceof BlancoXmlCdata) {
            ((BlancoXmlCdata) objCurrent).getChildNodes().add(characters);
        } else if (objCurrent instanceof BlancoXmlDocument) {
            ((BlancoXmlDocument) objCurrent).getChildNodes().add(characters);
        } else {
            throw new IllegalArgumentException(
                    "BlancoXmlUnmarshallerContentHandler: 想定されない型["
                            + objCurrent.getClass().getName()
                            + "]に対して Charactersを追加しようとしました。");
        }

        characters.setValue(new String(ch, start, length));
    }

    /**
     * ContentHandler の無視可能文字イベント。
     * 
     * @param ch
     * @param start
     * @param length
     */
    public void ignorableWhitespace(final char[] ch, final int start,
            final int length) throws SAXException {
        final BlancoXmlIgnorableWhitespace ignorableWhitespace = new BlancoXmlIgnorableWhitespace();

        // 現在処理中のオブジェクトを取得します。
        final BlancoXmlNode objCurrent = fDocumentElementStack.peek();
        if (objCurrent instanceof BlancoXmlElement) {
            ((BlancoXmlElement) objCurrent).getChildNodes().add(
                    ignorableWhitespace);
        } else if (objCurrent instanceof BlancoXmlCdata) {
            ((BlancoXmlDocument) objCurrent).getChildNodes().add(
                    ignorableWhitespace);
        } else if (objCurrent instanceof BlancoXmlDocument) {
            ((BlancoXmlDocument) objCurrent).getChildNodes().add(
                    ignorableWhitespace);
        } else {
            throw new IllegalArgumentException(
                    "BlancoXmlUnmarshallerContentHandler: 想定されない型["
                            + objCurrent.getClass().getName()
                            + "]に対して BlancoXmlIgnorableWhitespaceを追加しようとしました。");
        }

        ignorableWhitespace.setValue(new String(ch, start, length));
    }

    /**
     * ContentHandler の processingInstruction イベント。
     * 
     * @param target
     * @param data
     */
    public void processingInstruction(final String target, final String data)
            throws SAXException {
        // このイベントは現時点での仕様では無視します。
    }

    /**
     * ContentHandler の skippedEntity イベント。
     * 
     * @param name
     */
    public void skippedEntity(final String name) throws SAXException {
        // このイベントは現時点での仕様では無視します。
    }

    // ------------------------------------------------
    // ここからは LexicalHandler のためのメソッド。
    // ------------------------------------------------

    /**
     * LexicalHandler の DTD 開始イベント。
     * 
     * @param name
     * @param publicId
     * @param systemId
     */
    public void startDTD(final String name, final String publicId,
            final String systemId) throws SAXException {
        final BlancoXmlDtd dtd = new BlancoXmlDtd();

        // 現在処理中のオブジェクトを取得します。
        final BlancoXmlNode objCurrent = fDocumentElementStack.peek();
        if (objCurrent instanceof BlancoXmlDocument) {
            ((BlancoXmlDocument) objCurrent).getChildNodes().add(dtd);
        } else {
            throw new IllegalArgumentException(
                    "BlancoXmlUnmarshallerContentHandler: 想定されない型["
                            + objCurrent.getClass().getName()
                            + "]に対して BlancoXmlDtdを追加しようとしました。");
        }
    }

    /**
     * LexicalHandler の DTD 終了イベント。
     */
    public void endDTD() throws SAXException {
    }

    /**
     * LexicalHandler の Entity 開始イベント。
     * 
     * @param name
     *            エンティティ名。
     */
    public void startEntity(final String name) throws SAXException {
        // 現在の仕様では、サポート外です。
    }

    /**
     * LexicalHandler の Entity 終了イベント。
     * 
     * @param name
     *            エンティティ名。
     */
    public void endEntity(final String name) throws SAXException {
        // 現在の仕様では、サポート外です。
    }

    /**
     * LexicalHandler の CDATA 開始イベント。
     */
    public void startCDATA() throws SAXException {
        final BlancoXmlCdata cdata = new BlancoXmlCdata();

        // 現在処理中のオブジェクトを取得します。
        final BlancoXmlNode objCurrent = fDocumentElementStack.peek();
        if (objCurrent instanceof BlancoXmlElement) {
            final BlancoXmlElement elementParent = ((BlancoXmlElement) objCurrent);
            elementParent.getChildNodes().add(cdata);
        } else if (objCurrent instanceof BlancoXmlDocument) {
            ((BlancoXmlDocument) objCurrent).getChildNodes().add(cdata);
        } else {
            throw new IllegalArgumentException(
                    "BlancoXmlUnmarshallerContentHandler: 想定されない型["
                            + objCurrent.getClass().getName()
                            + "]に対して CDATAを追加しようとしました。");
        }

        fDocumentElementStack.push(cdata);
    }

    /**
     * LexicalHandler の CDATA 終了イベント。
     */
    public void endCDATA() throws SAXException {
        fDocumentElementStack.pop();
    }

    /**
     * LexicalHandler の コメントイベント。
     * 
     * @param ch
     * @param start
     * @param length
     */
    public void comment(final char[] ch, final int start, final int length)
            throws SAXException {
        final BlancoXmlComment comment = new BlancoXmlComment();

        // 現在処理中のオブジェクトを取得します。
        final BlancoXmlNode objCurrent = fDocumentElementStack.peek();
        if (objCurrent instanceof BlancoXmlElement) {
            ((BlancoXmlElement) objCurrent).getChildNodes().add(comment);
        } else if (objCurrent instanceof BlancoXmlDocument) {
            ((BlancoXmlDocument) objCurrent).getChildNodes().add(comment);
        } else {
            throw new IllegalArgumentException(
                    "BlancoXmlUnmarshallerContentHandler: 想定されない型["
                            + objCurrent.getClass().getName()
                            + "]に対して BlancoXmlCommentを追加しようとしました。");
        }

        comment.setValue(new String(ch, start, length));
    }

    // ------------------------------------------------
    // ここからは DTDHandler のためのメソッド。
    // ------------------------------------------------

    public void notationDecl(final String name, final String publicId,
            final String systemId) throws SAXException {
        // TODO 未実装。
    }

    public void unparsedEntityDecl(final String name, final String publicId,
            final String systemId, final String notationName)
            throws SAXException {
        // TODO 未実装。
    }
}
