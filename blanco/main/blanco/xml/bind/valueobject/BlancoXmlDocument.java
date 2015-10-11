/*
 * このソースコードは blanco Frameworkにより自動生成されました。
 */
package blanco.xml.bind.valueobject;

import java.util.List;

/**
 * SAXイベントのうち ドキュメントを記憶します。このクラスは XML/バリューオブジェクトマッピング (X/Oマッピング) blancoXmlBinding の一部です。
 */
public class BlancoXmlDocument extends BlancoXmlNode {
    /**
     * このドキュメントに連なる子ノードを格納します。ドキュメントは多くの場合ひとつのルートエレメントを格納しています。場合により DTD関連情報なども格納されます。
     *
     * フィールド: [childNodes]。
     * デフォルト: [new java.util.ArrayList<blanco.xml.bind.valueobject.BlancoXmlNode>()]。
     */
    private List<blanco.xml.bind.valueobject.BlancoXmlNode> fChildNodes = new java.util.ArrayList<blanco.xml.bind.valueobject.BlancoXmlNode>();

    /**
     * ロケーション。
     *
     * フィールド: [locator]。
     */
    private BlancoXmlLocator fLocator;

    /**
     * prefixMappingのリストを格納します。
     *
     * フィールド: [prefixMappings]。
     * デフォルト: [new java.util.ArrayList<blanco.xml.bind.valueobject.BlancoXmlPrefixMapping>()]。
     */
    private List<blanco.xml.bind.valueobject.BlancoXmlPrefixMapping> fPrefixMappings = new java.util.ArrayList<blanco.xml.bind.valueobject.BlancoXmlPrefixMapping>();

    /**
     * XML のバージョン。1.1 などを指定。
     *
     * フィールド: [version]。
     */
    private String fVersion;

    /**
     * XML のエンコーディング
     *
     * フィールド: [encoding]。
     */
    private String fEncoding;

    /**
     * フィールド [childNodes] の値を設定します。
     *
     * フィールドの説明: [このドキュメントに連なる子ノードを格納します。ドキュメントは多くの場合ひとつのルートエレメントを格納しています。場合により DTD関連情報なども格納されます。]。
     *
     * @param argChildNodes フィールド[childNodes]に設定する値。
     */
    public void setChildNodes(final List<blanco.xml.bind.valueobject.BlancoXmlNode> argChildNodes) {
        fChildNodes = argChildNodes;
    }

    /**
     * フィールド [childNodes] の値を取得します。
     *
     * フィールドの説明: [このドキュメントに連なる子ノードを格納します。ドキュメントは多くの場合ひとつのルートエレメントを格納しています。場合により DTD関連情報なども格納されます。]。
     * デフォルト: [new java.util.ArrayList<blanco.xml.bind.valueobject.BlancoXmlNode>()]。
     *
     * @return フィールド[childNodes]から取得した値。
     */
    public List<blanco.xml.bind.valueobject.BlancoXmlNode> getChildNodes() {
        return fChildNodes;
    }

    /**
     * フィールド [locator] の値を設定します。
     *
     * フィールドの説明: [ロケーション。]。
     *
     * @param argLocator フィールド[locator]に設定する値。
     */
    public void setLocator(final BlancoXmlLocator argLocator) {
        fLocator = argLocator;
    }

    /**
     * フィールド [locator] の値を取得します。
     *
     * フィールドの説明: [ロケーション。]。
     *
     * @return フィールド[locator]から取得した値。
     */
    public BlancoXmlLocator getLocator() {
        return fLocator;
    }

    /**
     * フィールド [prefixMappings] の値を設定します。
     *
     * フィールドの説明: [prefixMappingのリストを格納します。]。
     *
     * @param argPrefixMappings フィールド[prefixMappings]に設定する値。
     */
    public void setPrefixMappings(final List<blanco.xml.bind.valueobject.BlancoXmlPrefixMapping> argPrefixMappings) {
        fPrefixMappings = argPrefixMappings;
    }

    /**
     * フィールド [prefixMappings] の値を取得します。
     *
     * フィールドの説明: [prefixMappingのリストを格納します。]。
     * デフォルト: [new java.util.ArrayList<blanco.xml.bind.valueobject.BlancoXmlPrefixMapping>()]。
     *
     * @return フィールド[prefixMappings]から取得した値。
     */
    public List<blanco.xml.bind.valueobject.BlancoXmlPrefixMapping> getPrefixMappings() {
        return fPrefixMappings;
    }

    /**
     * フィールド [version] の値を設定します。
     *
     * フィールドの説明: [XML のバージョン。1.1 などを指定。]。
     *
     * @param argVersion フィールド[version]に設定する値。
     */
    public void setVersion(final String argVersion) {
        fVersion = argVersion;
    }

    /**
     * フィールド [version] の値を取得します。
     *
     * フィールドの説明: [XML のバージョン。1.1 などを指定。]。
     *
     * @return フィールド[version]から取得した値。
     */
    public String getVersion() {
        return fVersion;
    }

    /**
     * フィールド [encoding] の値を設定します。
     *
     * フィールドの説明: [XML のエンコーディング]。
     *
     * @param argEncoding フィールド[encoding]に設定する値。
     */
    public void setEncoding(final String argEncoding) {
        fEncoding = argEncoding;
    }

    /**
     * フィールド [encoding] の値を取得します。
     *
     * フィールドの説明: [XML のエンコーディング]。
     *
     * @return フィールド[encoding]から取得した値。
     */
    public String getEncoding() {
        return fEncoding;
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
        buf.append("blanco.xml.bind.valueobject.BlancoXmlDocument[");
        buf.append("childNodes=" + fChildNodes);
        buf.append(",locator=" + fLocator);
        buf.append(",prefixMappings=" + fPrefixMappings);
        buf.append(",version=" + fVersion);
        buf.append(",encoding=" + fEncoding);
        buf.append("]");
        return buf.toString();
    }
}
