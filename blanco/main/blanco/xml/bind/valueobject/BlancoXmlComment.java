package blanco.xml.bind.valueobject;

/**
 * SAXイベントのうち コメントを記憶します。このクラスは XML/バリューオブジェクトマッピング (X/Oマッピング) blancoXmlBinding の一部です。
 */
public class BlancoXmlComment extends BlancoXmlNode {
    /**
     * コメントデータ。
     *
     * フィールド: [value]。
     */
    private String fValue;

    /**
     * フィールド [value] の値を設定します。
     *
     * フィールドの説明: [コメントデータ。]。
     *
     * @param argValue フィールド[value]に設定する値。
     */
    public void setValue(final String argValue) {
        fValue = argValue;
    }

    /**
     * フィールド [value] の値を取得します。
     *
     * フィールドの説明: [コメントデータ。]。
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
        buf.append("blanco.xml.bind.valueobject.BlancoXmlComment[");
        buf.append("value=" + fValue);
        buf.append("]");
        return buf.toString();
    }

    /**
     * このバリューオブジェクトを指定のターゲットに複写します。
     *
     * <P>使用上の注意</P>
     * <UL>
     * <LI>オブジェクトのシャロー範囲のみ複写処理対象となります。
     * <LI>オブジェクトが循環参照している場合には、このメソッドは使わないでください。
     * </UL>
     *
     * @param target target value object.
     */
    public void copyTo(final BlancoXmlComment target) {
        if (target == null) {
            throw new IllegalArgumentException("Bug: BlancoXmlComment#copyTo(target): argument 'target' is null");
        }

        // No needs to copy parent class.

        // Name: fValue
        // Type: java.lang.String
        target.fValue = this.fValue;
    }
}
