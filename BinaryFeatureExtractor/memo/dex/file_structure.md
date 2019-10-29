# dex

## 全体ファイル構造

![](https://i.imgur.com/xmB57fw.png)

## header

4バイトアラインメント

| Name | Size | Description |
| -------- | -------- | -------- |
| magic | ubyte[8]     | "dex\n039\0" = { 0x64 0x65 0x78 0x0a 0x30 0x33 0x39 0x00 } |
| checksum | uint | チェックサム。ファイル破損検出に使用 |
| siganture | ubyte[20] | SHA-1ハッシュ |
| file_size | uint | ヘッダ含むファイルサイズ(バイト) |
| endian_tag | uint | エンディアンタグ |
| link_size | uint | リンクセクションサイズ。このファイルが静的にリンクされていない場合0 |
| link_off | uint | ファイルの開始位置からリンクセクションまでのオフセット |
| map_off | uint | ファイルの開始位置からmap itemまでのオフセット。オフセット(非0)はdata sectionまでのオフセットであり、map_listで記述されるフォーマットである|
| string_ids_size | uint | 文字列識別子(string identifiers)リストに含まれる文字列数 |
| string_ids_off | uint | ファイル開始位置から文字列識別子リストへのオフセット。string_ids_sizeが0ならば0 |
| type_ids_size | uint | 種類識別子(type identifiers)リスト |
| type_ids_off | uint | ファイル開始位置から種類識別子リストまでのオフセット。type_ids_sizeが0ならば0 |
| proto_ids_size | uint | プロトタイプ識別子(prototype identifiers)リストの要素数 |
| proto_ids_off | uint | プロトタイプ識別子リストへのオフセット。proto_ids_sizeが0ならば0。 |
| field_ids_size | uint | フィールド識別子(field identifiers)リストの要素数 |
| field_ids_off | uint | ファイル開始位置からフィールド識別子(field identifiers)一覧へのオフセット。filed_ids_sizeが0ならば0 |
| methods_ids_size | uint | メソッド識別子(method identifiers)リストの要素数 |
| methods_ids_off | uint | ファイルの開始位置からメソッド識別子リストへのオフセット。method_ids_sizeが0ならば0 |
| class_defs_size | uint | クラス定義(class definitions）リストの要素数 |
| class_defs_off | uint | ファイル開始位置からクラス定義リストへのオフセット。class_defs_sizeが0ならば0 |
| data_size | uint | データセクションのサイズ(バイト) |
| data_off | uint | ファイル開始位置からデータセクション開始位置へのオフセット |


```cpp
struct DexFileHeader {
  uint8_t magic[8],
  uint32_t checksum,
  uint8_t  signature[20],
  uint32_t file_size,
  uint32_t header_size,
  uint32_t endian_tag,
  uint32_t link_size,
  uint32_t link_off,
  uint32_t map_off,
  uint32_t string_ids_size,
  uint32_t string_ids_off,
  uint32_t type_ids_size,
  uint32_t type_ids_off,
  uint32_t proto_ids_size,
  uint32_t proto_ids_off,
  uint32_t field_ids_size,
  uint32_t field_ids_off,
  uint32_t methods_ids_size,
  uint32_t methods_ids_off,
  uint32_t class_defs_size,
  uint32_t class_defs_off,
  uint32_t data_size,
  uint32_t data_off,
}
```

## map_list

データセクションに含まれる。  
全ファイルのリスト。


| Name | Size | Description |
| -------- | -------- | -------- |
| size     | uint     | リストサイズ |
| list | map_item[size] | リストの各要素 |

```cpp
struct {
  uint32_t size,
  MapItem** list
}
```

- map_itemフォーマット

| Name | Size | Description |
| -------- | -------- | -------- |
| type     | ushort     | 要素のタイプ |
| unused | ushort | 未使用 |
| size | uint | |
| offset | uint | ファイル開始位置からアイテムへのオフセット |

```cpp
struct MapItem {
  uint16_t type,
  uint16_t unused,
  uint32_t size,
  uint32_t offset
}
```

## string_id_item

string_idsセクションに含まれる。  

| Name | Size | Description |
| -------- | -------- | -------- |
| string_data_off | uint | ファイル開始位置からこのアイテムの文字列データへのオフセット 。オフセットはデータセクション内にあり、データは下記のstring_data_itemフォーマットである|

### string_data_item



