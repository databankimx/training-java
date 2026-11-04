## Immutable objects

Sometimes the safest way to share an object is to make sure nobody can change it. An object whose contents are fixed when it's created can be passed to any number of holders, and none of them can surprise the others. This is the Java counterpart of the hand-off idea in the rest of this chapter: nothing needs to be handed over, because there's nothing that can change.

A record is immutable by design. Its components are fixed when it's created, so a change means a new record. Here's the record from `src/Main.java`:

```java
record Point(int x, int y) {
    Point moved(int dx) {
        return new Point(x + dx, y);
    }
}
```

`moved` doesn't change the point it's called on. It returns a new point, and the original stays the same.

### Copying a list on the way in

A class that keeps a list must be careful when the list comes from outside. If it keeps the caller's list, the caller can still change it later, and the class's contents change without the class knowing. The fix is to copy the list when the object is created. `List.copyOf` makes a copy that can't be changed:

```java
static class Team {
    private final List<String> members;

    Team(List<String> members) {
        this.members = List.copyOf(members);
    }

    List<String> members() {
        return members;
    }
}
```

The program creates a team, then changes the caller's list:

```java
List<String> names = new ArrayList<>(List.of("Ada", "Grace"));
Team team = new Team(names);
names.add("Alan");
System.out.println("Team size after the caller adds: " + team.members().size());
```

Press **Run**. The output is:

```text
Team size after the caller adds: 2
Changing the team list: UnsupportedOperationException
Point: Point[x=1, y=2]
Moved point: Point[x=4, y=2]
Original unchanged: Point[x=1, y=2]
```

The team still has two members, because it kept its own copy. Adding to the list it returns throws an `UnsupportedOperationException`, since the copy can't be changed. The record shows the same idea for a value: the change makes a new point, and the old one is unchanged.

### Why this matters for the rest of the chapter

Copies and hand-offs are needed when an object can change. When an object can't change, a reference to it is safe to share, and no copy or hand-off is needed. Records and `List.copyOf` are the usual tools for making that so.

---

### Quiz

*These questions are written for this course.*

````quiz
Why does `Team` copy its list when it is created?
○ So that the list can be sorted faster.
→ Not quite. The copy is about who can change the list, not its speed.
◉ So that the caller can't change the team's members later by changing the list it passed in.
→ Yes. The copy keeps the team's contents separate from the caller's list.
○ Because Java requires every constructor to copy its arguments.
→ Not quite. The class copies because its own contents must stay fixed.
````

````quiz
What does `original.moved(3)` do to `original`?
○ It changes original's x component to 4.
→ Not quite. A record can't be changed, so the method returns a new record.
◉ Nothing. It returns a new point, and original is unchanged.
→ Yes. Records are immutable, so the original keeps its values.
○ It deletes original.
→ Not quite. Objects are not deleted by a method call. The collector frees them later, once they're unreachable.
````

---

### Try it in the runner

Add `team.members().add("Bob")` without the `try` block, and predict the result before you press **Run**. The build succeeds, since the method compiles, but the program stops with an `UnsupportedOperationException` when it runs. Put the `try` back before you continue.

---

An immutable object can be shared safely, and a change to one gives a new object. The rest of this chapter looks at what to do when an object must change, and who may hold it while it does.
