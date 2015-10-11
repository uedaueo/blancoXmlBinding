/*
 * このソースコードは blanco Frameworkにより自動生成されました。
 */
package blanco.xml.bind.valueobject;

/**
 * SAXイベントのうち prefixMappingを記憶します。このクラスは XML/バリューオブジェクトマッピング (X/Oマッピング) blancoXmlBinding の一部です。
 */
public class BlancoXmlPrefixMapping {
    /**
     * 例: rdf
     *
     * フィールド: [prefix]。
     * デフォルト: [""]。
     */
    private String fPrefix = "";

    /**
     * 例: http://www.w3.org/1999/02/22-rdf-syntax-ns#
     *
     * フィールド: [uri]。
     */
    private String fUri;

    /**
     * フィールド [prefix] の値を設定します。
     *
     * フィールドの説明: [例: rdf]。
     *
     * @param argPrefix フィールド[prefix]に設定する値。
     */
    public void setPrefix(final String argPrefix) {
        fPrefix = argPrefix;
    }

    /**
     * フィールド [prefix] の値を取得します。
     *
     * フィールドの説明: [例: rdf]。
     * デフォルト: [""]。
     *
     * @return フィールド[prefix]から取得した値。
     */
    public String getPrefix() {
        return fPrefix;
    }

    /**
     * フィールド [uri] の値を設定します。
     *
     * フィールドの説明: [例: http://www.w3.org/1999/02/22-rdf-syntax-ns#]。
     *
     * @param argUri フィールド[uri]に設定する値。
     */
    public void setUri(final String argUri) {
        fUri = argUri;
    }

    /**
     * フィールド [uri] の値を取得します。
     *
     * フィールドの説明: [例: http://www.w3.org/1999/02/22-rdf-syntax-ns#]。
     *
     * @return フィールド[uri]から取得した値。
     */
    public String getUri() {
        return fUri;
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
        buf.append("blanco.xml.bind.valueobject.BlancoXmlPrefixMapping[");
        buf.append("prefix=" + fPrefix);
        buf.append(",uri=" + fUri);
        buf.append("]");
        return buf.toString();
    }
}
