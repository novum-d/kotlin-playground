# Class and Object

## 【Properties】

### [Declaring properties]

Kotlinクラスのプロパティは、`varキーワード`を使用して可変として宣言することも、`valキーワード`を使用して読み取り専用として宣言することもできます。

### [Getters and setters]

**構文**

```kotlin
var <propertyName>[: <PropertyType>] [ = <property_initializer>]
[<getter>]
[<setter>]
```

**可変プロパティ**

```kotlin
// デフォルトのゲッターとセッターがあります
var initialized = 1
// var allByDefault // ERROR: explicit initializer required, default getter and setter implied
```

**読み取り専用プロパティ**  
読み取り専用プロパティ宣言の構文は、2つの点で可変構文とは異なります。<sl>varではなくvalで始まり、**セッターを許可しません**</sl>。

```kotlin
// デフォルトのゲッターがあり、コンストラクターで初期化する必要があります
val simple: Int?
// デフォルトのゲッターがあります
val inferredType = 1
```

**カスタムアクセサ**  
プロパティの`カスタムアクセサ`を定義できます。<sl>`カスタムゲッター`を定義すると、**プロパティにアクセスするたびに呼び出されます**（このようにして、計算されたプロパティを実装できます）</sl>。

```kotlin
class Rectangle(val width: Int, val height: Int) {
    val square: Int
        get() = this.width * this.height
}
```

ゲッターから推測できる場合は、プロパティタイプを省略できます。

```kotlin
val square get() = this.width * this.height
```

<sl>`カスタムセッター`を定義すると、初期化を除いて、**プロパティに値を割り当てるたびに呼び出されます**<sl>。

```kotlin
var stringRepresentation: String
    get() = this.toString()
    set(value) {
        // 文字列を解析し、他のプロパティに値を割り当てます
        setDataFromString(value)
    }
```
慣例により、setterパラメーターの名前はvalueですが、必要に応じて別の名前を選択できます。

アクセサーに注釈を付けたり、その可視性を変更したりする必要があるが、デフォルトの実装を変更する必要がない場合は、本体を定義せずにアクセサーを定義できます。

