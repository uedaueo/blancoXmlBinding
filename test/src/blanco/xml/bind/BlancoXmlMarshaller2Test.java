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

import junit.framework.TestCase;
import blanco.xml.bind.valueobject.BlancoXmlAttribute;
import blanco.xml.bind.valueobject.BlancoXmlCdata;
import blanco.xml.bind.valueobject.BlancoXmlCharacters;
import blanco.xml.bind.valueobject.BlancoXmlDocument;
import blanco.xml.bind.valueobject.BlancoXmlElement;

public class BlancoXmlMarshaller2Test extends TestCase {
    public void testParse() throws Exception {
        final BlancoXmlDocument document = new BlancoXmlDocument();

        final BlancoXmlElement element = new BlancoXmlElement();
        element.setQName("AAA");
        document.getChildNodes().add(element);

        final BlancoXmlAttribute attr = new BlancoXmlAttribute();
        element.getAtts().add(attr);
        attr.setQName("abc");
        attr.setValue("値");
        attr.setType("CDATA");

        final BlancoXmlElement element2 = new BlancoXmlElement();
        element.getChildNodes().add(element2);
        element2.setQName("DEF");

        final BlancoXmlCharacters characters = new BlancoXmlCharacters();
        characters.setValue("これは値です。");
        element2.getChildNodes().add(characters);

        final BlancoXmlCdata cdata = new BlancoXmlCdata();
        final BlancoXmlCharacters characters2 = new BlancoXmlCharacters();
        cdata.getChildNodes().add(characters2);
        characters2.setValue("CDATA中の値です。");
        element2.getChildNodes().add(cdata);

        element2.getChildNodes().add(characters);

        new File("./tmp").mkdirs();
        new BlancoXmlMarshaller().marshal(document, new File(
                "./tmp/BlancoXmlMarshaller2Test.xml"));

    }
}
