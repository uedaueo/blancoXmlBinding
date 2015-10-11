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

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import blanco.xml.bind.valueobject.BlancoXmlDocument;

/**
 * XML から blancoXmlBinding バリューオブジェクト表現を生成するためのクラスです。
 * 
 * このクラスは XML/バリューオブジェクトマッピング (X/Oマッピング) blancoXmlBinding の一部です。
 * 
 * @author IGA Tosiki
 */
public class BlancoXmlUnmarshaller {
    /**
     * XMLからJavaオブジェクトを生成します。
     * 
     * 参考: http://java.sun.com/webservices/docs/1.6/api/javax/xml/bind/
     * Unmarshaller.html
     * 
     * @param fileInput
     *            XMLファイル。
     * @return blancoXmlとしてのオブジェクト。
     */
    public BlancoXmlDocument unmarshal(final File fileInput) {
        if (fileInput.exists() == false) {
            throw new IllegalArgumentException("ファイル["
                    + fileInput.getAbsolutePath() + "]は見つかりません。");
        }
        if (fileInput.isDirectory()) {
            throw new IllegalArgumentException("ファイル["
                    + fileInput.getAbsolutePath() + "]は実際にはディレクトリです。");
        }
        if (fileInput.canRead() == false) {
            throw new IllegalArgumentException("ファイル["
                    + fileInput.getAbsolutePath() + "]は読み込むことができません。");
        }

        InputStream inStream = null;

        try {
            inStream = new BufferedInputStream(new FileInputStream(fileInput));

            return unmarshal(inStream);
        } catch (IOException e) {
            throw new IllegalArgumentException("処理中に入出力例外が発生しました。: "
                    + e.toString(), e);
        } finally {
            if (inStream != null) {
                try {
                    inStream.close();
                } catch (IOException e) {
                    throw new IllegalArgumentException(
                            "入力ストリームのクローズ処理中に入出力例外が発生しました。: " + e.toString(), e);
                }
            }
        }
    }

    /**
     * XMLからJavaオブジェクトを生成します。
     * 
     * 参考: http://java.sun.com/webservices/docs/1.6/api/javax/xml/bind/
     * Unmarshaller.html
     * 
     * @param inStream
     *            XML入力ストリーム。
     * @return blancoXmlとしてのオブジェクト。
     */
    public BlancoXmlDocument unmarshal(final InputStream inStream) {
        if (inStream == null) {
            throw new IllegalArgumentException(
                    "BlancoXmlUnmarshaller#unmarshal: 入力ストリームに null が与えられました。");
        }

        final BlancoXmlUnmarshallerContentHandler handler = new BlancoXmlUnmarshallerContentHandler();

        try {
            // XMLパーサの設定をコントロールしたいので、XMLReaderを優先して利用。
            XMLReader reader = null;
            try {
                reader = XMLReaderFactory.createXMLReader();
            } catch (SAXException e) {
                Logger.getLogger("blanco.xml.bind").fine(
                        "XMLReader の取得に失敗しました: " + e.toString());

                // 環境によっては(JDK 1.4.2で稀に?) XMLReaderの取得に失敗する場合があります。

                // XMLReaderFactory.createXMLReader() 呼び出し時に、
                // org.xml.sax.SAXException: System property org.xml.sax.driver
                // not specified が発生することが確認されています。
                final SAXResult result = new SAXResult(handler);
                result.setHandler(handler);
                result.setLexicalHandler(handler);
                final TransformerFactory tf = TransformerFactory.newInstance();
                try {
                    final Transformer transformer = tf.newTransformer();
                    transformer.transform(new StreamSource(inStream), result);
                    return handler.getDocument();

                } catch (TransformerException e2) {
                    throw new IllegalArgumentException("処理中に XML 解析例外が発生しました。"
                            + e2.toString(), e2);
                }
            }

            // XMLReaderを無事に取得できた場合には、こちらを通ります。

            try {
                // 外部のDTDを読み込まなくするための呪文。
                reader
                        .setFeature(
                                "http://apache.org/xml/features/nonvalidating/load-external-dtd",
                                false);
            } catch (SAXNotRecognizedException e) {
                Logger.getLogger("blanco.xml.bind").finest(
                        "外部 DTD を読み込まなくするための設定の実施中に例外が発生しました: " + e.toString());
            }

            reader.setContentHandler(handler);
            reader.setDTDHandler(handler);
            // コメントを処理するためにプロパティを設定。
            reader.setProperty("http://xml.org/sax/properties/lexical-handler",
                    handler);

            // DTD 宣言は現在は処理していません。
            // これは、現在の処理範囲を javax.xml.transform.sax.TransformerHandler
            // と限定しているためです。
            // reader.setProperty(
            // "http://xml.org/sax/properties/declaration-handler",
            // handler);

            reader.parse(new InputSource(inStream));
            return handler.getDocument();

        } catch (SAXException e) {
            throw new IllegalArgumentException("処理中に XML 解析例外が発生しました。: "
                    + e.toString(), e);
        } catch (IOException e) {
            throw new IllegalArgumentException("処理中に入出力例外が発生しました。: "
                    + e.toString(), e);
        } finally {
            if (inStream != null) {
                try {
                    inStream.close();
                } catch (IOException e) {
                    throw new IllegalArgumentException(
                            "入力ストリームのクローズ処理中に入出力例外が発生しました。: " + e.toString(), e);
                }
            }
        }
    }
}
