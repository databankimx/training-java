## Greeting card: walkthrough

The program reads the name with `nextLine`, so spaces in the name are kept. It then reads the age with `nextInt`:

```java
String name = input.nextLine();
int age = input.nextInt();
System.out.println("Hello, " + name + ".");
System.out.println("Next year you will be " + (age + 1) + ".");
```

The name is read first. If the age were read first, the `nextLine` call for the name would pick up the leftover newline and return an empty string, which is the problem the user input lesson describes.

The sentence about next year uses `age + 1`. The brackets make sure the addition happens before the number is joined to the text. Without them, `"... " + age + 1` would print the age followed by a 1, such as `361`.
