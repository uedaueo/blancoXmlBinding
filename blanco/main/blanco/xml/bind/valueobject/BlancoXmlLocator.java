/*
 * このソースコードは blanco Frameworkにより自動生成されました。
 */
package blanco.xml.bind.valueobject;

/**
 * SAXイベントのうち Locatorを記憶します。このクラスは XML/バリューオブジェクトマッピング (X/Oマッピング) blancoXmlBinding の一部です。
 */
public class BlancoXmlLocator {
    /**
     * 文書イベントの公開識別子。
     *
     * フィールド: [publicId]。
     */
    private String fPublicId;

    /**
     * 文書イベントのシステム識別子。
     *
     * フィールド: [systemId]。
     */
    private String fSystemId;

    /**
     * 文書イベントが終了する行番号。
     *
     * フィールド: [lineNumber]。
     * デフォルト: [-1]。
     */
    private int fLineNumber = -1;

    /**
     * 文書イベントが終了する列番号。
     *
     * フィールド: [columnNumber]。
     * デフォルト: [-1]。
     */
    private int fColumnNumber = -1;

    /**
     * フィールド [publicId] の値を設定します。
     *
     * フィールドの説明: [文書イベントの公開識別子。]。
     *
     * @param argPublicId フィールド[publicId]に設定する値。
     */
    public void setPublicId(final String argPublicId) {
        fPublicId = argPublicId;
    }

    /**
     * フィールド [publicId] の値を取得します。
     *
     * フィールドの説明: [文書イベントの公開識別子。]。
     *
     * @return フィールド[publicId]から取得した値。
     */
    public String getPublicId() {
        return fPublicId;
    }

    /**
     * フィールド [systemId] の値を設定します。
     *
     * フィールドの説明: [文書イベントのシステム識別子。]。
     *
     * @param argSystemId フィールド[systemId]に設定する値。
     */
    public void setSystemId(final String argSystemId) {
        fSystemId = argSystemId;
    }

    /**
     * フィールド [systemId] の値を取得します。
     *
     * フィールドの説明: [文書イベントのシステム識別子。]。
     *
     * @return フィールド[systemId]から取得した値。
     */
    public String getSystemId() {
        return fSystemId;
    }

    /**
     * フィールド [lineNumber] の値を設定します。
     *
     * フィールドの説明: [文書イベントが終了する行番号。]。
     *
     * @param argLineNumber フィールド[lineNumber]に設定する値。
     */
    public void setLineNumber(final int argLineNumber) {
        fLineNumber = argLineNumber;
    }

    /**
     * フィールド [lineNumber] の値を取得します。
     *
     * フィールドの説明: [文書イベントが終了する行番号。]。
     * デフォルト: [-1]。
     *
     * @return フィールド[lineNumber]から取得した値。
     */
    public int getLineNumber() {
        return fLineNumber;
    }

    /**
     * フィールド [columnNumber] の値を設定します。
     *
     * フィールドの説明: [文書イベントが終了する列番号。]。
     *
     * @param argColumnNumber フィールド[columnNumber]に設定する値。
     */
    public void setColumnNumber(final int argColumnNumber) {
        fColumnNumber = argColumnNumber;
    }

    /**
     * フィールド [columnNumber] の値を取得します。
     *
     * フィールドの説明: [文書イベントが終了する列番号。]。
     * デフォルト: [-1]。
     *
     * @return フィールド[columnNumber]から取得した値。
     */
    public int getColumnNumber() {
        return fColumnNumber;
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
        buf.append("blanco.xml.bind.valueobject.BlancoXmlLocator[");
        buf.append("publicId=" + fPublicId);
        buf.append(",systemId=" + fSystemId);
        buf.append(",lineNumber=" + fLineNumber);
        buf.append(",columnNumber=" + fColumnNumber);
        buf.append("]");
        return buf.toString();
    }
}
