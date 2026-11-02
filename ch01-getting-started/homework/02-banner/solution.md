## Banner: walkthrough

The box is three lines. The top and bottom borders are nine stars, which is the width of the middle line, and the middle line is the word between a star and a space at each side:

```java
public class Main {
    public static void main(String[] args) {
        System.out.println("*********");
        System.out.println("* HELLO *");
        System.out.println("*********");
    }
}
```

The middle line has seven characters inside the stars, which is the five letters of HELLO and a space on each side. The border has to be the same width as that line, or the box won't close.
