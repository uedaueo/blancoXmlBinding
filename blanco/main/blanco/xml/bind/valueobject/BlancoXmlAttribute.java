/*
 * このソースコードは blanco Frameworkにより自動生成されました。
 */
package blanco.xml.bind.valueobject;

/**
 * SAXイベントのうち 要素(Element)のなかの 属性(Attribute)を記憶します。このクラスは XML/バリューオブジェクトマッピング (X/Oマッピング) blancoXmlBinding の一部です。
 */
public class BlancoXmlAttribute {
    /**
     * 属性の名前空間URI。
     *
     * フィールド: [uri]。
     */
    private String fUri;

    /**
     * 属性のローカル名。
     *
     * フィールド: [localName]。
     */
    private String fLocalName;

    /**
     * 属性の XML 1.0 修飾名。
     *
     * フィールド: [qName]。
     */
    private String fQName;

    /**
     * 属性の型。&quot;CDATA&quot;, &quot;ID&quot;, &quot;IDREF&quot;, &quot;IDREFS&quot;, &quot;NMTOKEN&quot;, &quot;NMTOKENS&quot;, &quot;ENTITY&quot;, &quot;ENTITIES&quot;, &quot;NOTATION&quot; が格納されます。
     *
     * フィールド: [type]。
     */
    private String fType;

    /**
     * 属性の値。
     *
     * フィールド: [value]。
     */
    private String fValue;

    /**
     * フィールド [uri] の値を設定します。
     *
     * フィールドの説明: [属性の名前空間URI。]。
     *
     * @param argUri フィールド[uri]に設定する値。
     */
    public void setUri(final String argUri) {
        fUri = argUri;
    }

    /**
     * フィールド [uri] の値を取得します。
     *
     * フィールドの説明: [属性の名前空間URI。]。
     *
     * @return フィールド[uri]から取得した値。
     */
    public String getUri() {
        return fUri;
    }

    /**
     * フィールド [localName] の値を設定します。
     *
     * フィールドの説明: [属性のローカル名。]。
     *
     * @param argLocalName フィールド[localName]に設定する値。
     */
    public void setLocalName(final String argLocalName) {
        fLocalName = argLocalName;
    }

    /**
     * フィールド [localName] の値を取得します。
     *
     * フィールドの説明: [属性のローカル名。]。
     *
     * @return フィールド[localName]から取得した値。
     */
    public String getLocalName() {
        return fLocalName;
    }

    /**
     * フィールド [qName] の値を設定します。
     *
     * フィールドの説明: [属性の XML 1.0 修飾名。]。
     *
     * @param argQName フィールド[qName]に設定する値。
     */
    public void setQName(final String argQName) {
        fQName = argQName;
    }

    /**
     * フィールド [qName] の値を取得します。
     *
     * フィールドの説明: [属性の XML 1.0 修飾名。]。
     *
     * @return フィールド[qName]から取得した値。
     */
    public String getQName() {
        return fQName;
    }

    /**
     * フィールド [type] の値を設定します。
     *
     * フィールドの説明: [属性の型。"CDATA", "ID", "IDREF", "IDREFS", "NMTOKEN", "NMTOKENS", "ENTITY", "ENTITIES", "NOTATION" が格納されます。]。
     *
     * @param argType フィールド[type]に設定する値。
     */
    public void setType(final String argType) {
        fType = argType;
    }

    /**
     * フィールド [type] の値を取得します。
     *
     * フィールドの説明: [属性の型。"CDATA", "ID", "IDREF", "IDREFS", "NMTOKEN", "NMTOKENS", "ENTITY", "ENTITIES", "NOTATION" が格納されます。]。
     *
     * @return フィールド[type]から取得した値。
     */
    public String getType() {
        return fType;
    }

    /**
     * フィールド [value] の値を設定します。
     *
     * フィールドの説明: [属性の値。]。
     *
     * @param argValue フィールド[value]に設定する値。
     */
    public void setValue(final String argValue) {
        fValue = argValue;
    }

    /**
     * フィールド [value] の値を取得します。
     *
     * フィールドの説明: [属性の値。]。
     *
     * @return フィールド[value]から取得した値。
     */
    public String getValue() {
        return fValue;
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
        buf.append("blanco.xml.bind.valueobject.BlancoXmlAttribute[");
        buf.append("uri=" + fUri);
        buf.append(",localName=" + fLocalName);
        buf.append(",qName=" + fQName);
        buf.append(",type=" + fType);
        buf.append(",value=" + fValue);
        buf.append("]");
        return buf.toString();
    }
}
