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

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;

import org.xml.sax.SAXException;

import blanco.commons.util.BlancoStringUtil;
import blanco.xml.bind.valueobject.BlancoXmlDocument;

/**
 * blancoXmlBinding のバリューオブジェクト表現から XML を生成するためのクラスです。
 * 
 * このクラスは XML/バリューオブジェクトマッピング (X/Oマッピング) blancoXmlBinding の一部です。
 * 
 * @author IGA Tosiki
 */
public class BlancoXmlMarshaller {
    /**
     * 内部的に利用する出力用SAXハンドラ
     * 
     * 連結先のストリームは外部でcloseなどの処理が実施される必要があります。
     */
    private TransformerHandler fSaxHandler;

    /**
     * JavaオブジェクトからXMLを生成します。
     * 
     * @param document
     *            blancoXmlとしてのオブジェクト。
     * @param outFile
     *            XML出力先ファイル。
     */
    public void marshal(final BlancoXmlDocument document, final File outFile) {
        if (document == null) {
            throw new IllegalArgumentException(
                    "BlancoXmlMarshaller#marshal: 入力 XML ドキュメントに null が渡されました。");
        }
        if (outFile == null) {
            throw new IllegalArgumentException(
                    "BlancoXmlMarshaller#marshal: 出力先 XML ファイルに null が渡されました。");
        }
        if (outFile.exists()) {
            if (outFile.canWrite() == false) {
                throw new IllegalArgumentException("出力先 XML ファイル["
                        + outFile.getName() + "]は書き込むことができません。");
            }
        }

        try {
            final OutputStream outStream = new BufferedOutputStream(
                    new FileOutputStream(outFile));
            try {
                marshal(document, outStream);

                outStream.flush();
            } finally {
                outStream.close();
            }
        } catch (IOException ex) {
            throw new IllegalArgumentException(
                    "BlancoXmlMarshaller#marshal: ファイル出力に失敗しました。"
                            + ex.toString(), ex);
        }
    }

    /**
     * JavaオブジェクトからXMLを生成します。
     * 
     * 参考:
     * http://java.sun.com/webservices/docs/1.6/api/javax/xml/bind/Marshaller
     * .html
     * 
     * @param document
     *            blancoXmlとしてのオブジェクト。
     * @param outStream
     *            XML出力先ストリーム。
     */
    public void marshal(final BlancoXmlDocument document,
            final OutputStream outStream) {
        final TransformerFactory tf = TransformerFactory.newInstance();
        final SAXTransformerFactory saxTf = (SAXTransformerFactory) tf;
        try {
            fSaxHandler = saxTf.newTransformerHandler();
            if (BlancoStringUtil.null2Blank(document.getVersion()).length() > 0) {
                fSaxHandler.getTransformer().setOutputProperty("version",
                        document.getVersion());
            }
            if (BlancoStringUtil.null2Blank(document.getEncoding()).length() > 0) {
                fSaxHandler.getTransformer().setOutputProperty("encoding",
                        document.getEncoding());
            }
        } catch (TransformerConfigurationException e) {
            throw new IllegalArgumentException(
                    "BlancoXmlMarshaller#marshal: トランスフォーマーハンドラ生成に失敗しました。: "
                            + e.toString(), e);
        }

        fSaxHandler.setResult(new StreamResult(outStream));

        try {
            new BlancoXmlMarshallerSerializer(fSaxHandler).serialize(document);
        } catch (SAXException e) {
            throw new IllegalArgumentException(
                    "BlancoXmlMarshaller#marshal: オブジェクトから XML への変換過程で例外が発生しました。: "
                            + e.toString(), e);
        }

        // 最後にハンドラを開放します。
        fSaxHandler = null;
    }
}
