/*
 * このソースコードは blanco Frameworkにより自動生成されました。
 */
package blanco.xml.bind.valueobject;

/**
 * SAXイベントのうち DTDを記憶します。このクラスは XML/バリューオブジェクトマッピング (X/Oマッピング) blancoXmlBinding の一部です。
 */
public class BlancoXmlDtd extends BlancoXmlNode {
    /**
     * 表記法名。
     *
     * フィールド: [name]。
     */
    private String fName;

    /**
     * 表記法の公開識別子。
     *
     * フィールド: [publicId]。
     */
    private String fPublicId;

    /**
     * 表記法のシステム識別子。
     *
     * フィールド: [systemId]。
     */
    private String fSystemId;

    /**
     * フィールド [name] の値を設定します。
     *
     * フィールドの説明: [表記法名。]。
     *
     * @param argName フィールド[name]に設定する値。
     */
    public void setName(final String argName) {
        fName = argName;
    }

    /**
     * フィールド [name] の値を取得します。
     *
     * フィールドの説明: [表記法名。]。
     *
     * @return フィールド[name]から取得した値。
     */
    public String getName() {
        return fName;
    }

    /**
     * フィールド [publicId] の値を設定します。
     *
     * フィールドの説明: [表記法の公開識別子。]。
     *
     * @param argPublicId フィールド[publicId]に設定する値。
     */
    public void setPublicId(final String argPublicId) {
        fPublicId = argPublicId;
    }

    /**
     * フィールド [publicId] の値を取得します。
     *
     * フィールドの説明: [表記法の公開識別子。]。
     *
     * @return フィールド[publicId]から取得した値。
     */
    public String getPublicId() {
        return fPublicId;
    }

    /**
     * フィールド [systemId] の値を設定します。
     *
     * フィールドの説明: [表記法のシステム識別子。]。
     *
     * @param argSystemId フィールド[systemId]に設定する値。
     */
    public void setSystemId(final String argSystemId) {
        fSystemId = argSystemId;
    }

    /**
     * フィールド [systemId] の値を取得します。
     *
     * フィールドの説明: [表記法のシステム識別子。]。
     *
     * @return フィールド[systemId]から取得した値。
     */
    public String getSystemId() {
        return fSystemId;
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
        buf.append("blanco.xml.bind.valueobject.BlancoXmlDtd[");
        buf.append("name=" + fName);
        buf.append(",publicId=" + fPublicId);
        buf.append(",systemId=" + fSystemId);
        buf.append("]");
        return buf.toString();
    }
}
