/*
 * このソースコードは blanco Frameworkにより自動生成されました。
 */
package blanco.xml.bind.valueobject;

import java.util.List;

/**
 * SAXイベントのうち 要素(Element)を記憶します。このクラスは XML/バリューオブジェクトマッピング (X/Oマッピング) blancoXmlBinding の一部です。
 */
public class BlancoXmlElement extends BlancoXmlNode {
    /**
     * 名前空間URI。
     *
     * フィールド: [uri]。
     */
    private String fUri;

    /**
     * ローカル名。
     *
     * フィールド: [localName]。
     */
    private String fLocalName;

    /**
     * 前置修飾子付きの修飾名。
     *
     * フィールド: [qName]。
     */
    private String fQName;

    /**
     * blanco.xml.BlancoXmlAttributeのリスト。
     *
     * フィールド: [atts]。
     * デフォルト: [new java.util.ArrayList<blanco.xml.bind.valueobject.BlancoXmlAttribute>()]。
     */
    private List<blanco.xml.bind.valueobject.BlancoXmlAttribute> fAtts = new java.util.ArrayList<blanco.xml.bind.valueobject.BlancoXmlAttribute>();

    /**
     * 子ノードのリスト。
     *
     * フィールド: [childNodes]。
     * デフォルト: [new java.util.ArrayList<blanco.xml.bind.valueobject.BlancoXmlNode>()]。
     */
    private List<blanco.xml.bind.valueobject.BlancoXmlNode> fChildNodes = new java.util.ArrayList<blanco.xml.bind.valueobject.BlancoXmlNode>();

    /**
     * フィールド [uri] の値を設定します。
     *
     * フィールドの説明: [名前空間URI。]。
     *
     * @param argUri フィールド[uri]に設定する値。
     */
    public void setUri(final String argUri) {
        fUri = argUri;
    }

    /**
     * フィールド [uri] の値を取得します。
     *
     * フィールドの説明: [名前空間URI。]。
     *
     * @return フィールド[uri]から取得した値。
     */
    public String getUri() {
        return fUri;
    }

    /**
     * フィールド [localName] の値を設定します。
     *
     * フィールドの説明: [ローカル名。]。
     *
     * @param argLocalName フィールド[localName]に設定する値。
     */
    public void setLocalName(final String argLocalName) {
        fLocalName = argLocalName;
    }

    /**
     * フィールド [localName] の値を取得します。
     *
     * フィールドの説明: [ローカル名。]。
     *
     * @return フィールド[localName]から取得した値。
     */
    public String getLocalName() {
        return fLocalName;
    }

    /**
     * フィールド [qName] の値を設定します。
     *
     * フィールドの説明: [前置修飾子付きの修飾名。]。
     *
     * @param argQName フィールド[qName]に設定する値。
     */
    public void setQName(final String argQName) {
        fQName = argQName;
    }

    /**
     * フィールド [qName] の値を取得します。
     *
     * フィールドの説明: [前置修飾子付きの修飾名。]。
     *
     * @return フィールド[qName]から取得した値。
     */
    public String getQName() {
        return fQName;
    }

    /**
     * フィールド [atts] の値を設定します。
     *
     * フィールドの説明: [blanco.xml.BlancoXmlAttributeのリスト。]。
     *
     * @param argAtts フィールド[atts]に設定する値。
     */
    public void setAtts(final List<blanco.xml.bind.valueobject.BlancoXmlAttribute> argAtts) {
        fAtts = argAtts;
    }

    /**
     * フィールド [atts] の値を取得します。
     *
     * フィールドの説明: [blanco.xml.BlancoXmlAttributeのリスト。]。
     * デフォルト: [new java.util.ArrayList<blanco.xml.bind.valueobject.BlancoXmlAttribute>()]。
     *
     * @return フィールド[atts]から取得した値。
     */
    public List<blanco.xml.bind.valueobject.BlancoXmlAttribute> getAtts() {
        return fAtts;
    }

    /**
     * フィールド [childNodes] の値を設定します。
     *
     * フィールドの説明: [子ノードのリスト。]。
     *
     * @param argChildNodes フィールド[childNodes]に設定する値。
     */
    public void setChildNodes(final List<blanco.xml.bind.valueobject.BlancoXmlNode> argChildNodes) {
        fChildNodes = argChildNodes;
    }

    /**
     * フィールド [childNodes] の値を取得します。
     *
     * フィールドの説明: [子ノードのリスト。]。
     * デフォルト: [new java.util.ArrayList<blanco.xml.bind.valueobject.BlancoXmlNode>()]。
     *
     * @return フィールド[childNodes]から取得した値。
     */
    public List<blanco.xml.bind.valueobject.BlancoXmlNode> getChildNodes() {
        return fChildNodes;
    }

    /**
     * このバリューオブジェクトの文字列表現を取得します。
     *
     * <P>使用上の注意</P>
     * <UL>
     * <LI>オブジェクトのシャロー範囲のみ文字列化の処理対象となります。
     * <LI>オブジェクトが循環参照している場合には、このメソッドは使わないでください。
     * </UL>
     *
     * @return バリューオブジェクトの文字列表現。
     */
    @Override
    public String toString() {
        final StringBuffer buf = new StringBuffer();
        buf.append("blanco.xml.bind.valueobject.BlancoXmlElement[");
        buf.append("uri=" + fUri);
        buf.append(",localName=" + fLocalName);
        buf.append(",qName=" + fQName);
        buf.append(",atts=" + fAtts);
        buf.append(",childNodes=" + fChildNodes);
        buf.append("]");
        return buf.toString();
    }
}
