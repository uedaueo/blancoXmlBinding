/*
 * このソースコードは blanco Frameworkにより自動生成されました。
 */
package blanco.xml.bind.valueobject;

/**
 * SAXイベントのうち 要素(Node)をあらわします。このクラスは XML/バリューオブジェクトマッピング (X/Oマッピング) blancoXmlBinding の一部です。
 */
public abstract class BlancoXmlNode {
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
        buf.append("blanco.xml.bind.valueobject.BlancoXmlNode[");
        buf.append("]");
        return buf.toString();
    }
}
