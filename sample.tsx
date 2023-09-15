/**
 * 知識
 * 
 * 基本的にDOMに対して直接影響を与える処理は厳禁
 * 
 * 
 */

///////////////////////////////////////////////////////////////////////
///////////////	基礎的な型	///////////////////////////////////////////
///////////////////////////////////////////////////////////////////////

/**
 *  配列  
 */ 
const array: string[] = []
array.push('aa')
//Union型
const mixArrayU: (string|number)[] = ['aa',1]
//タプル
const mixArrayT: [string, number] = ['aa', 1]

/**
 * オブジェクト型
 */
const user: { name: string; age: number } = {
	name: 'yamada',
	age: 35
}
console.log(user.name)

// 「?」で省略可能になる
function printName(obj: { firstname: string, age?: number }) {
}
printName({ firstname: 'matida' })
printName({ firstname: 'matida', age: 10 })

/**
 * any
 */
// すべての型を許容する

/**
 * 関数
 */
//引数に初期値を設定可能
function hello(name: string, greeting: string = 'Hello'): string {
	return '${greeting} ${name}'
}

//引数に関数を指定可能
function printName2(firstname: string, formatter: (name: string) => string) {
	console.log(formatter(firstname))
}
function formatName(name: string): string{
	return "${name} san"
}
printName2('tama', formatName)

function genBirdInfo(name: string): string[]{
	return name.split(",")
}
function singBirds(birdInfo: (x: string) => string[]): string{
	return birdInfo("hato, kiji")[0] + "piyo"
}
console.log(singBirds(genBirdInfo))

/**
 * 型アサーション
*/
// 「as」で指定可能
const myCanvas = document.getElementById("main_cav") as HTMLCanvasElement


/**
 * 型エイリアス
 * 型の指定の別名を設ける機能
 */
type Point = {
	x: number;
	y: number;
}
function printPoint(point: Point) {
}
//　プロパティが違うとエラーになる
printPoint({ x: 100, y: 100 })

// オブジェクトのキー名を明記せず型エイリアスを定義できる
type Label = {
	[key: string]: string
}
const labels: Label = {
	topTitle: "top",
	topSub: "topのタイトル",
	topFeature: "topの機能です"
}

/**
 * インターフェイス
 * 基本的にjavaのインターフェイスクラスと同じ使用方法
 */
interface Point2 {
	x: number;
	y: number;
}
// 同じものを作成すると追加扱い
interface Point2 {
	z: number;
}

/**
 * クラス
 */
//javaと同じ
class Point3 {
	x: number;
	y: number;

	constructor(x: number = 0, y: number = 0) {
		this.x = x
		this.y = y
	}
}
//クラスはextendsで継承可能
class Point3D extends Point3 {
	z: number;

	constructor(x: number = 0, y: number = 0, z: number = 0) {
		super(x, x)
		this.z = z
	}
}
//クラスはインスタンス作成で中身を使用可能になる
const point3D = new Point3D()

/**
 * アクセス修飾子
 */
// pubic private protected が存在する

/**
 * Enum型
 */
// 特に指定しなかった場合enumは定義された順番に沿ってゼロから数字が自動的にインクリメントされて設定される
enum Direction {
	up,
	down,
	left,
	right
}
// 2が入る
let direction: Direction = Direction.left


/**
 * ジェネリクス
 * クラスや関数においてその中で使う型を抽象化し外部から具体的な型を指定できる機能
 */

class Test<A> {
	private array:A[] = []
	push(item: A) {
		this.array.push(item)
	}
	pop(): A | undefined {
		return this.array.shift()
	}
}
const test = new Test<number>()
test.push(1)

/**
 * Intersection型
 * 複数の型をマージして1つとなった型
 */
type Identity = {
	id: number;
	name: string;
}
type Contact = {
	name: string;
	email: string;
	phone: string;
}
type Employee = Identity & Contact


/**
 * リテラル型
 * 「|」でデータを区切り、決まった文字列や数値しか入らない型制御をする
 */
let postStats: "draft"| "publish" | "deleted"

//関数でも使用可能
function compare(a: string, b: string): -1 | 0 | 1 {
	return a === b ? 0: a > b ? 1 : -1
}


/**
 * never型
 * 決して発生しない値の種類を表す
 * 例えば常に例外を発生させる関数などで決して値が返されることのない戻り値の型をneverとして定義できる
 */
//具体例わからんかったから以下参考
//https://numb86-tech.hatenablog.com/entry/2020/02/06/214324


///////////////////////////////////////////////////////////////////////
///////////////	TIPS	///////////////////////////////////////////
///////////////////////////////////////////////////////////////////////

//	分割代入
const human = {
	name: "太郎",
	gender: "male",
	age: 20
};

// const humanName = human.name;
// const humanGender = human.gender;
// const humanAge = human.age;

// 上はオブジェクトを1回ずつしているが分割代入をすることで1回で済むようになる
// const { name, gender, age } = human;
// 分割代入は初期値をして可能
// const { name, gender, age = 19 } = human;
// console.log(name);

// スプレッド構文
// 取得時内部に別の配列として定義できる
// const { name, ...more } = human;
// → "太郎" {"male", 20}

///////////////////////////////////////////////////////////////////////
///////////////	状態管理系	///////////////////////////////////////////
///////////////////////////////////////////////////////////////////////


// usestateで値をセットする場合、値を引数にとってreturnした方が良い

// const [count, setCount] = useState();
// var testCount = 1;
// const countTest = () => {
// 	setCount((testCount) => testCount + 1);
// 	// →2
// 	setCount((testCount) => testCount + 1);
// 	// →3
// }
// const countTest = () => {
// 	setCount(testCount + 1);
// 	// →2
// 	setCount(testCount + 1);
// 	// →2
// }



// useEffectで終わりにreturnを書くことでアンマウント時に実行する処理を書ける
// useEffect(() => {
// 	document.body.style.backgroundColor = "light";
// 	return document.body.style.backgroundColor = "";
// },[])


