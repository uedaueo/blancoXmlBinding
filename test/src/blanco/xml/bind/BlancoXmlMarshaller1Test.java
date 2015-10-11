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

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import junit.framework.TestCase;
import blanco.xml.bind.valueobject.BlancoXmlDocument;

public class BlancoXmlMarshaller1Test extends TestCase {
    private static final String TARGET = "build.xml";

    public void testParse() throws Exception {
        final BlancoXmlDocument document = new BlancoXmlUnmarshaller()
                .unmarshal(new File(TARGET));

        if (document.getLocator() != null) {
            System.out.println(document.getLocator());
        }

        System.out.println(document);

        new File("./tmp").mkdirs();
        final OutputStream outStream = new FileOutputStream(
                "./tmp/BlancoXmlMarshaller1Test.xml");
        new BlancoXmlMarshaller().marshal(document, outStream);
        outStream.flush();
        outStream.close();
    }
}
