# ☕ Java Cheat Sheet

---

# 1️⃣ Klassen & Aufbau

## 🔹 Klassenbenennung (Konventionen)

| Element   | Schreibweise   | Beispiel                |
| --------- | -------------- | ----------------------- |
| Klasse    | **PascalCase** | `Person`, `BankAccount` |
| Methode   | **camelCase**  | `calculateSum()`        |
| Variable  | **camelCase**  | `firstName`             |
| Konstante | **UPPER_CASE** | `MAX_VALUE`             |
| Paket     | lowercase      | `com.example.app`       |

---

## 🔹 Grundstruktur einer Klasse

```java
public class Person {

    // Attribute (Felder)
    private String name;
    private int age;

    // Konstruktor
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Methode
    public void sayHello() {
        System.out.println("Hallo, ich bin " + name);
    }

    // Getter
    public String getName() {
        return name;
    }
}
```

---

# 2️⃣ Datentypen

## 🔹 Primitive Datentypen

| Typ       | Größe      | Beispiel             |
| --------- | ---------- | -------------------- |
| `byte`    | 8 Bit      | `byte b = 10;`       |
| `short`   | 16 Bit     |                      |
| `int`     | 32 Bit     | `int x = 5;`         |
| `long`    | 64 Bit     | `long l = 100L;`     |
| `float`   | 32 Bit     | `float f = 1.5f;`    |
| `double`  | 64 Bit     | `double d = 3.14;`   |
| `char`    | 16 Bit     | `char c = 'A';`      |
| `boolean` | true/false | `boolean ok = true;` |

---

## 🔹 Referenztypen

* `String`
* Arrays
* Klassen
* Interfaces
* Enums

Beispiel:

```java
String name = "Max";
```

---

## 🔹 Wichtige String-Methoden

```java
length()
charAt(int index)
substring(int begin, int end)
equals(String other)
toLowerCase()
toUpperCase()
contains(String s)
replace(String a, String b)
```

---

# 3️⃣ static vs non-static

## 🔹 static

* Gehört zur **Klasse**
* Kein Objekt notwendig
* Wird einmal im Speicher gespeichert

```java
public class MathUtils {
    public static int add(int a, int b) {
        return a + b;
    }
}
```

Aufruf:

```java
MathUtils.add(5, 3);
```

---

## 🔹 non-static

* Gehört zum **Objekt**
* Braucht eine Instanz

```java
Person p = new Person("Max", 20);
p.sayHello();
```

---

## 🔥 Merksatz:

> static → Klassenebene
> non-static → Objektebene

---

# 4️⃣ OOP – Objektorientierte Programmierung

## 🔹 Was ist OOP?

Programmieren mit **Objekten**, die:

* Zustand (Attribute)
* Verhalten (Methoden)

haben.

---

## 🔹 Die 4 Prinzipien

### 1️⃣ Kapselung (Encapsulation)

Daten verstecken → `private` + Getter/Setter

---

### 2️⃣ Vererbung (Inheritance)

```java
class Animal {
    void makeSound() {
        System.out.println("Sound");
    }
}

class Dog extends Animal {
    void bark() {
        System.out.println("Wuff");
    }
}
```

---

### 3️⃣ Polymorphismus

```java
Animal a = new Dog();
a.makeSound();
```

---

### 4️⃣ Abstraktion

```java
abstract class Shape {
    abstract double calculateArea();
}
```

---

# 5️⃣ Kontrollstrukturen

## 🔹 if / else

```java
if (x > 10) {
    System.out.println("Groß");
} else {
    System.out.println("Klein");
}
```

---

## 🔹 switch

```java
switch (day) {
    case 1:
        System.out.println("Montag");
        break;
    default:
        System.out.println("Unbekannt");
}
```

---

## 🔹 Schleifen

### for

```java
for (int i = 0; i < 10; i++) {
    System.out.println(i);
}
```

### while

```java
while (x < 5) {
    x++;
}
```

### do-while

```java
do {
    x++;
} while (x < 5);
```

---

# 6️⃣ Arrays

```java
int[] numbers = new int[5];
numbers[0] = 10;
```

Oder:

```java
int[] nums = {1, 2, 3};
```

Schleife:

```java
for (int n : nums) {
    System.out.println(n);
}
```

---

# 7️⃣ Methoden

```java
public int add(int a, int b) {
    return a + b;
}
```

### Parameter vs Argument

```java
add(5, 3);
```

* 5,3 = Argumente
* a,b = Parameter

---

# 8️⃣ Sichtbarkeiten (Modifier)

| Modifier    | Bedeutung         |
| ----------- | ----------------- |
| `public`    | überall sichtbar  |
| `private`   | nur in der Klasse |
| `protected` | Paket + Vererbung |
| default     | nur im Paket      |

---

# 9️⃣ Konstruktor

```java
public Person(String name) {
    this.name = name;
}
```

Kein Rückgabetyp!

---

# 🔟 Exceptions

```java
try {
    int x = 5 / 0;
} catch (ArithmeticException e) {
    System.out.println("Fehler!");
}
```

Eigene Exception:

```java
throw new IllegalArgumentException("Falscher Wert");
```

---

# 1️⃣1️⃣ Collections (Basics)

## ArrayList

```java
import java.util.ArrayList;

ArrayList<String> list = new ArrayList<>();
list.add("Hallo");
list.get(0);
list.remove(0);
```

---

# 1️⃣2️⃣ Wrapper Klassen

Primitive → Objekt

| Primitive | Wrapper |
| --------- | ------- |
| int       | Integer |
| double    | Double  |
| boolean   | Boolean |

Beispiel:

```java
Integer.parseInt("123");
```

---

# 1️⃣3️⃣ equals vs ==

```java
String a = "Hi";
String b = "Hi";

a == b      // Referenzvergleich
a.equals(b) // Inhaltsvergleich
```

👉 Für Objekte immer `equals()` benutzen!

---

# 1️⃣4️⃣ final

```java
final int x = 10;
```

* Variable kann nicht geändert werden
* Methode kann nicht überschrieben werden
* Klasse kann nicht vererbt werden

---

# 1️⃣5️⃣ Main Methode

```java
public static void main(String[] args) {

}
```

* Einstiegspunkt
* static → ohne Objekt aufrufbar

---

# 🧠 Wichtige Grundkonzepte

* Java ist **stark typisiert**
* Alles ist in Klassen organisiert
* Primitive ≠ Objekte
* Speicher: Stack vs Heap
* Garbage Collector verwaltet Speicher

---

# 🎓 Typische Anfängerfehler

❌ `==` statt `equals()`
❌ `null` nicht prüfen
❌ static und non-static verwechseln
❌ Klammern bei `if` vergessen
❌ ArrayIndexOutOfBounds

---

# 1️⃣6️⃣ OOP -- Vertiefung

## 🔹 SOLID Prinzipien

**S -- Single Responsibility Principle**\
Eine Klasse sollte genau eine Verantwortung haben.

**O -- Open/Closed Principle**\
Offen für Erweiterung, geschlossen für Modifikation.

**L -- Liskov Substitution Principle**\
Subklassen müssen Basisklassen ersetzbar machen.

**I -- Interface Segregation Principle**\
Viele kleine Interfaces statt eines großen.

**D -- Dependency Inversion Principle**\
Abhängigkeiten von Abstraktionen, nicht von konkreten Klassen.

---

# 1️⃣7️⃣ Interfaces

``` java
public interface Drawable {
    void draw();
}

public class Circle implements Drawable {
    public void draw() {
        System.out.println("Drawing circle");
    }
}
```

### Default & Static Methods (ab Java 8)

``` java
default void info() {
    System.out.println("Info");
}

static void helper() {
    System.out.println("Helper");
}
```

---

# 1️⃣8️⃣ Abstrakte Klassen

``` java
public abstract class Shape {
    abstract double area();

    public void print() {
        System.out.println("Shape");
    }
}
```

Unterschied zu Interface: - Kann Konstruktoren haben - Kann Attribute
besitzen - Mehrere Interfaces möglich, aber nur eine abstrakte Klasse

---

# 1️⃣9️⃣ Generics

``` java
public class Box<T> {
    private T value;

    public void set(T value) { this.value = value; }
    public T get() { return value; }
}
```

## Bounded Generics

``` java
<T extends Number>
```

---

# 2️⃣0️⃣ Collections Framework

## List

-   ArrayList
-   LinkedList

## Set

-   HashSet
-   TreeSet

## Map

-   HashMap
-   TreeMap

### Beispiel

``` java
Map<String, Integer> map = new HashMap<>();
map.put("A", 1);
map.get("A");
map.containsKey("A");
```

---

# 2️⃣1️⃣ equals() & hashCode()

Immer zusammen überschreiben:

``` java
@Override
public boolean equals(Object o) { }

@Override
public int hashCode() { }
```

Regel: Gleiche Objekte → gleicher HashCode

---

# 2️⃣2️⃣ Streams API

``` java
List<Integer> nums = List.of(1,2,3,4);

nums.stream()
    .filter(n -> n % 2 == 0)
    .map(n -> n * 2)
    .forEach(System.out::println);
```

Wichtige Methoden: - filter() - map() - reduce() - collect() - sorted()

---

# 2️⃣3️⃣ Lambda Expressions

``` java
(a, b) -> a + b
n -> n * 2
() -> System.out.println("Hi")
```

Functional Interface:

``` java
@FunctionalInterface
interface Calculator {
    int calculate(int a, int b);
}
```

---

# 2️⃣4️⃣ Exception Handling (Advanced)

## Eigene Exception

``` java
public class MyException extends Exception {
    public MyException(String message) {
        super(message);
    }
}
```

## try-with-resources

``` java
try (BufferedReader br = new BufferedReader(new FileReader("file.txt"))) {
    br.readLine();
}
```

---

# 2️⃣5️⃣ Design Patterns (Basics)

## Singleton

``` java
public class Singleton {
    private static final Singleton instance = new Singleton();
    private Singleton() {}
    public static Singleton getInstance() {
        return instance;
    }
}
```

## Factory Pattern

``` java
public class ShapeFactory {
    public static Shape create(String type) {
        if(type.equals("circle")) return new Circle();
        return null;
    }
}
```

---

# 2️⃣6️⃣ Enums

``` java
public enum Day {
    MONDAY, TUESDAY
}
```

Mit Konstruktor:

``` java
public enum Level {
    LOW(1), HIGH(2);

    private int value;

    Level(int value) { this.value = value; }
}
```

---

# 2️⃣7️⃣ Multithreading Basics

``` java
class MyThread extends Thread {
    public void run() {
        System.out.println("Running");
    }
}
```

Oder:

``` java
Runnable r = () -> System.out.println("Thread");
new Thread(r).start();
```

---

# 2️⃣8️⃣ File I/O (NIO)

``` java
Files.readAllLines(Path.of("file.txt"));
Files.write(Path.of("file.txt"), List.of("Hello"));
```

---

# 2️⃣9️⃣ Optional

``` java
Optional<String> name = Optional.of("Max");
name.ifPresent(System.out::println);
```

---

# 3️⃣0️⃣ Clean Code Basics

-   Kleine Methoden
-   Sinnvolle Namen
-   Keine Magic Numbers
-   DRY (Don't Repeat Yourself)
-   KISS (Keep It Simple)

---
