# tutorial

## 概要
playの公式ドキュメントの、初めての Play アプリケーション（シンプルなTODO管理アプリケーション）の実装です。

https://www.playframework.com/documentation/ja/2.2.x/ScalaTodoList

上記を以下のバージョンで動かそうとしたところ、色々はまるポイントがあったので、備忘までにメモを残しておきます。

※ 2015/06/17現在

## バージョン
* Java1.8
* Scala2.11
* Play2.4

## 作り方
```activator new tutorial play-scala```

このプロジェクトをベースに、上記URLに従って実装。

## 修正ポイント

### library依存
```libraryDependencies ++= Seq(
  "com.typesafe.play" %% "anorm" % "2.4.0",
  evolutions
)```

※evolutionsの指定をしないと、手順通りに作ってもエボリューションが動きません。

### index.scala.htmlでのエラー
以下のimportを追加。

```@import play.api.Play.current
@import play.api.i18n.Messages.Implicits._```

### herokuへのデプロイ
#### library依存
`libraryDependencies ++= Seq(
  "postgresql" % "postgresql" % "9.1-901-1.jdbc4"
)`

### application.conf の設定
herokuデプロイ時、connection pool 初期化で失敗するので、以下の設定を追加して回避が必要です。

```db.default.hikaricp.connectionTestQuery="SELECT TRUE"```

https://www.playframework.com/documentation/tr/2.4.x/Migration24

Migration24 JDBC connection pool 参照。

※多分全体的に上記を参照すれば解決すると思います。
