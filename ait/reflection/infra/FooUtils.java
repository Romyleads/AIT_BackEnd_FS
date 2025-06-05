package ait.reflection.infra;

import ait.reflection.model.Foo;

public class FooUtils {
  public static void caller (String functionName, String arg) {
    Foo foo = new Foo();

    switch (functionName) {
      case "f1":
        foo.f1(arg);
        break;
      case "f2":
        foo.f2(arg);
        break;
      case "f3":
        foo.f3(arg);
        break;
default:
System.out.println(functionName + "does not exist");
    }


  }
}