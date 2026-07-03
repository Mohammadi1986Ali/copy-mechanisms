# Copy Mechanisms

This project is a small Java 21 Maven application that demonstrates common ways to copy objects in Java. The examples focus on the difference between shallow copies and deep copies when an object contains nested mutable state.

## What It Demonstrates

The project compares these copy mechanisms:

- Shallow copy by reusing the same nested object reference.
- Deep copy with copy constructors.
- Deep copy with `Cloneable` and overridden `clone()` methods.
- Deep copy with Apache Commons Lang `SerializationUtils.clone()`.
- Deep copy by JSON round trip with Gson.
- Deep copy by JSON round trip with Jackson.

## Project Structure

```text
src/
  main/
    java/com/nexora/software/copymechanisms/
      Application.java
      model/
        Account.java
        Address.java
        Customer.java
        User.java
    resources/
      logback.xml
  test/
    java/com/nexora/software/copymechanisms/
      ApplicationTest.java
```

## Key Lesson

A shallow copy duplicates only the top-level object. Nested mutable objects are still shared, so changes through the original are visible through the copy.

A deep copy also duplicates nested mutable objects. After the original nested object changes, the copied object keeps its previous state.
