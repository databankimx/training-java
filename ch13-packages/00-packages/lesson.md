## Packages

A package is a named group of classes. Its name matches the folder the files sit in, so `util` is kept in a folder called `util`. Packages keep unrelated code apart, and they stop two classes with the same name from colliding, because each class's full name includes its package.

This step has two files. `util/MathHelper.java` is in the `util` package, and `Main.java` uses it. The folder structure matters: the `util` folder sits inside `src`, next to `Main.java`.

Here's `src/util/MathHelper.java`, the file that declares the package:

```java
package util;

public class MathHelper {
    private MathHelper() {
    }

    public static int square(int n) {
        return n * n;
    }

    public static int cube(int n) {
        return n * n * n;
    }
}
```

The first line, `package util;`, puts the class in the `util` package. The class's full name is `util.MathHelper`.

Here's `src/Main.java`, which uses it:

```java
import util.MathHelper;

public class Main {
    public static void main(String[] args) {
        System.out.println("square(4) = " + MathHelper.square(4));
        System.out.println("cube(3) = " + MathHelper.cube(3));
    }
}
```

Press **Run**. The output is:

```text
square(4) = 16
cube(3) = 27
```

### Importing

`import util.MathHelper;` lets `Main` refer to the class by its short name, `MathHelper`. Without the import, the code would have to write the full name, `util.MathHelper.square(4)`, every time.

`Main` has no package line, so it belongs to the default package. The runner starts `Main`, so the entry class stays in the default package, and the packages hold the helper code. That's a choice this course makes to keep the runner simple. Real projects put `main` in a package too.

### Static methods on a helper class

`MathHelper` has a private constructor, so no one can create a `MathHelper` object. Its methods are `static`, and they're called on the class name. A class that only groups helper methods like this is usually written with a private constructor, so it can't be mistaken for an object type.

---

### Quiz

*These questions are written for this course.*

````quiz
Which folder must `MathHelper.java` sit in, given that it declares `package util;`?
○ The same folder as Main.java, with no subfolder.
→ Not quite. Java expects the folder to match the package name.
◉ A folder called util, inside src.
→ Yes. The folder structure matches the package name, so the compiler can find the class.
○ Any folder, because the package line is enough.
→ Not quite. The folder must match the package name, so the compiler and the runner find the files.
````

````quiz
What is the full name of the `square` method's class?
○ MathHelper
→ Not quite. That's the short name. The full name includes the package.
◉ util.MathHelper
→ Yes. The package name comes first, then the class name.
○ Main.MathHelper
→ Not quite. Main doesn't contain MathHelper. They are separate classes.
````

---

### Try it in the runner

Remove the `import util.MathHelper;` line from `Main`, and predict the result. The build fails, because `MathHelper` can't be found in the default package. Replace the calls with `util.MathHelper.square(4)` in place of the short name, and check that it works without the import. Restore the import and the short names before you continue.

Then change the first line of `MathHelper.java` to `package other;`, leaving it in the util folder. Predict the result. The file still compiles, but `Main`'s import no longer finds the class, so the build fails. The package name and the folder must agree. Restore `package util;` before you continue.

---

A package is a named group of classes, kept in a folder with the same name. The next step looks at the access modifiers, which decide what code in one package can see of another.
