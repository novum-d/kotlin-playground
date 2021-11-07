# Control flow

## 【Returns and jumps】

Kotlinには3つの構造ジャンプ式があります。

* return  
  `return`は最も近い囲み関数または無名関数から戻ります。


* break  
  `break`は、最も近い囲みループを終了します。


* continue  
  `continue`は、最も近い囲みループの次のステップに進みます。

```kotlin
val s = person.name ?: return
```

これらの式は`Nothing型`です。

### [Break and continue labels]

Kotlinの式には、`label`を付けることができます。<sl>labelは、abc@やfooBar@のように、**識別子の後に@記号が続く形式**</sl>になっています。式にラベルを付けるには、その前にラベルを追加するだけです。

```kotlin
loop@ for (i in 1..100) { }
```

<l>labelで修飾されたbreakは、そのlabelでマークされたループの直後に**実行ポイントにジャンプ**</l>します。continueは、そのループの次の反復に進みます

### [Return to labels]

Kotlinでは、関数リテラル、ローカル関数、およびオブジェクト式を使用して関数をネストできます。修飾されたリターンにより、外部関数から戻ることができます。最も重要なユースケースは、ラムダ式から戻ることです。次のように書くと、returnは、囲みから最も近い関数fooから返されることを思い出してください。

**non-local return directly to the caller of foo()**

```kotlin
fun foo() {
    listOf(1, 2, 3, 4, 5).forEach {
        if (it == 3) return
        print(it)
    }
    println("this point is unreachable")
}

result...
12
```

このようなnon-localリターンは、インライン関数に渡されるラムダ式でのみサポートされることに注意してください。

**local return to the caller of the lambda**

ラムダ式から戻るには、ラベルを付けて、戻り値を修飾します

```kotlin
fun foo() {
    listOf(1, 2, 3, 4, 5).forEach lit@{
        if (it == 3) return@lit
        print(it)
    }
    println("this point is unreachable")
}

result...
1245 done with explicit label
```

または、ラムダ式を無名関数に置き換えることもできます。匿名関数のreturnステートメントは、無名関数自体から戻ります。

**local return to the caller of the anonymous function**

```kotlin
fun foo() {
    listOf(1, 2, 3, 4, 5).forEach(fun(value: Int) {
        if (value == 3) return  // local return to the caller of the anonymous function - the forEach loop
        print(value)
    })
    print(" done with anonymous function")
}

result...
1245 done with explicit label
```

前の3つの例でのローカルリターンの使用は、通常のループでのcontinueの使用と同様であることに注意してください。 breakに直接相当するものはありませんが、別のネストラムダを追加し、そこから非ローカルに戻ることでシミュレートできます。

**non-local return from the lambda passed to run**

```kotlin
fun foo() {
    run loop@{
        listOf(1, 2, 3, 4, 5).forEach {
            if (it == 3) return@loop // non-local return from the lambda passed to run
            print(it)
        }
    }
    print(" done with nested loop")
}
```

値を返すとき、パーサーは修飾された戻り値を優先します。

```kotlin
return@a 1
```

「ラベル@aで1を返す」ことを意味します。

---

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

