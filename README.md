![CI](https://github.com/H-Shen/Caches/workflows/Caches%20CI/badge.svg)

#### The implementation of Arbitrary-Precision Fraction in Java v0.01

The project includes the implementation of Arbitrary-precision fraction, utilizing BigIntegers for numerator and denominator. Fraction is always kept in lowest terms. Fraction is immutable and guaranteed not to have a null numerator or denominator. Denominator will always be positive (thus sign is carried by numerator), the denominator is not allowed to be zero.

#### NOTICE

* This implementation is working for the coding competition purpose.

* Users can remove/update any part for the coding competition.

#### How to test?

Run instructions below in a terminal

```sh
git clone https://github.com/H-Shen/Rational.git
cd Rational
mvn build
mvn test
mvn clean
```

#### Usage

```java
Rational a = new Rational(1, 2);
Rational b = new Rational(3, 4);
Rational c = Rational.ZERO;

System.out.println(a.add(a)); // 1/2 + 1/2 = 1/1
System.out.println(a.add(b)); // 1/2 + 3/4 = 5/4
System.out.println(b.add(c)); // 3/4 + 0/1 = 3/4
System.out.println(c.add(a)); // 0/1 + 1/2 = 1/2

System.out.println(a.subtract(a)); // 1/2 - 1/2 = 0/1
System.out.println(a.subtract(b)); // 1/2 - 3/4 = -1/4
System.out.println(b.subtract(c)); // 3/4 - 0/1 = 3/4
System.out.println(c.subtract(a)); // 0/1 - 1/2 = -1/2

System.out.println(a.multiply(a)); // (1/2) * (1/2) = 1/4
System.out.println(a.multiply(b)); // (1/2) * (3/4) = 3/8
System.out.println(b.multiply(c)); // (3/4) * (0/1) = 0/1
System.out.println(c.multiply(a)); // (0/1) * (1/2) = 0/1

System.out.println(a.divide(a)); // (1/2) / (1/2) = 1/1
System.out.println(a.divide(b)); // (1/2) / (3/4) = 2/3
//System.out.println(b.divide(c)); // => ArithmeticException - divide by zero
System.out.println(c.divide(a)); // (0/1) / (1/2) = 0/1

System.out.println(a.negate()); // -1/2
System.out.println(a.max(b));   // 3/4
System.out.println(a.min(c));   // 0
System.out.println(b.doubleValue());  // 0.75
System.out.println(Rational.MINUS_ONE.abs());    // 1
```

#### Requirements

java version "1.8.0_161"

Java(TM) SE Runtime Environment (build 1.8.0_161-b12)

Java HotSpot(TM) 64-Bit Server VM (build 25.161-b12, mixed mode) 

The project has been tested with:

*   JUnit 5

*   Apache Maven 3.6.3
