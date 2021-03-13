import java.io.Serializable;
import java.math.BigInteger;

/**
 * Arbitrary-precision fraction, utilizing BigIntegers for numerator and
 * denominator. Fraction is always kept in lowest terms. Fraction is
 * immutable and guaranteed not to have a null numerator or denominator.
 * Denominator will always be positive (so sign is carried by numerator,
 * and a zero-denominator is not allowed).
 * <p>
 * NOTICE:
 * This short implementation is working for the coding competition purpose.
 * Users can remove any part in the competition if they think the part is useless.
 *
 * @author Haohu Shen <a href="https://github.com/H-Shen">https://github.com/H-Shen</a>
 * @version 0.01
 */
public class Rational extends Number implements Comparable<Rational>, Serializable {

    /**
     * A fraction representing "0".
     */
    public static final Rational ZERO = new Rational(0, 1);

    /**
     * A fraction representing "1".
     */
    public static final Rational ONE = new Rational(1, 1);

    /**
     * A fraction representing "2".
     */
    public static final Rational TWO = new Rational(2, 1);

    /**
     * A fraction representing "-1".
     */
    public static final Rational MINUS_ONE = new Rational(-1, 1);

    /**
     * A fraction representing "1/3".
     */
    public static final Rational ONE_THIRD = new Rational(1, 3);

    /**
     * A fraction representing "3/5".
     */
    public static final Rational THREE_FIFTHS = new Rational(3, 5);

    /**
     * A fraction representing "3/4".
     */
    public static final Rational THREE_QUARTERS = new Rational(3, 4);

    /**
     * A fraction representing "1/4".
     */
    public static final Rational ONE_QUARTER = new Rational(1, 4);

    /**
     * A fraction representing "1/5".
     */
    public static final Rational ONE_FIFTH = new Rational(1, 5);

    /**
     * A fraction representing "4/5".
     */
    public static final Rational FOUR_FIFTHS = new Rational(4, 5);

    /**
     * A fraction representing "1/2".
     */
    public static final Rational ONE_HALF = new Rational(1, 2);

    /**
     * A fraction representing "2/3".
     */
    public static final Rational TWO_THIRDS = new Rational(2, 3);

    /**
     * A fraction representing "2/5".
     */
    public static final Rational TWO_FIFTHS = new Rational(2, 5);

    /**
     * A fraction representing "10".
     */
    public static final Rational TEN = new Rational(10, 1);

    /**
     * A fraction representing "1/10".
     */
    public static final Rational TEN_HALF = new Rational(1, 10);

    /**
     * Serializable version identifier.
     */
    private static final long serialVersionUID = -8293979095700333158L;

    /**
     * The numerator.
     */
    private final BigInteger numerator;

    /**
     * The denominator.
     */
    private final BigInteger denominator;

    /**
     * Create a {@link Rational} given the numerator and denominator as
     * {@code BigInteger}. The {@link Rational} is reduced to lowest terms.
     *
     * @param numerator   the numerator, must not be {@code null}.
     * @param denominator the denominator, must not be {@code null}.
     * @throws ArithmeticException  if the denominator is zero.
     * @throws NullPointerException if either of the arguments is null
     */
    public Rational(BigInteger numerator, BigInteger denominator) {
        if (numerator == null) {
            throw new NullPointerException();
        }
        if (denominator == null) {
            throw new NullPointerException();
        }
        if (denominator.signum() == 0) {
            throw new ArithmeticException();
        }
        if (numerator.signum() == 0) {
            this.numerator = BigInteger.ZERO;
            this.denominator = BigInteger.ONE;
        } else {
            // reduce the numerator and the denominator by their greatest common divisor
            final BigInteger gcd = numerator.gcd(denominator);
            numerator = numerator.divide(gcd);
            denominator = denominator.divide(gcd);
            // move sign to numerator
            if (denominator.signum() == -1) {
                denominator = denominator.negate();
                numerator = numerator.negate();
            }
            this.numerator = numerator;
            this.denominator = denominator;
        }
    }

    /**
     * <p>
     * Create a {@link Rational} given the numerator and denominator as simple
     * {@code long}. The {@link Rational} is reduced to lowest terms.
     * </p>
     *
     * @param numerator   the numerator.
     * @param denominator the denominator.
     */
    public Rational(final long numerator, final long denominator) {
        this(new BigInteger(String.valueOf(numerator)), new BigInteger(String.valueOf(denominator)));
    }

    /**
     * <p>
     * Access the numerator as a <code>BigInteger</code>.
     * </p>
     *
     * @return the numerator as a <code>BigInteger</code>.
     */
    public BigInteger getNumerator() {
        return numerator;
    }

    /**
     * <p>
     * Access the denominator as a <code>BigInteger</code>.
     * </p>
     *
     * @return the denominator as a <code>BigInteger</code>.
     */
    public BigInteger getDenominator() {
        return denominator;
    }

    /**
     * <p>
     * Adds the value of this fraction to another, returning the result in
     * reduced form.
     * </p>
     *
     * @param o the {@link Rational} to add, must not be <code>null</code>.
     * @return a {@link Rational} instance with the resulting values.
     * @throws NullPointerException if the {@link Rational} is {@code null}.
     */
    public Rational add(final Rational o) {
        if (o == null) {
            throw new NullPointerException();
        }
        BigInteger newNumerator   = numerator.multiply(o.denominator).add(denominator.multiply(o.numerator));
        BigInteger newDenominator = denominator.multiply(o.denominator);
        return new Rational(newNumerator, newDenominator);
    }

    /**
     * <p>
     * Subtract the value from this fraction to another, returning the result in
     * reduced form.
     * </p>
     *
     * @param o the {@link Rational} to subtract, must not be <code>null</code>.
     * @return a {@link Rational} instance with the resulting values.
     * @throws NullPointerException if the {@link Rational} is {@code null}.
     */
    public Rational subtract(final Rational o) {
        if (o == null) {
            throw new NullPointerException();
        }
        BigInteger newNumerator   = numerator.multiply(o.denominator).subtract(denominator.multiply(o.numerator));
        BigInteger newDenominator = denominator.multiply(o.denominator);
        return new Rational(newNumerator, newDenominator);
    }

    /**
     * <p>
     * Multiplies the value of this fraction to another, returning the result in
     * reduced form.
     * </p>
     *
     * @param o the {@link Rational} to multiply, must not be <code>null</code>.
     * @return a {@link Rational} instance with the resulting values.
     * @throws NullPointerException if the {@link Rational} is {@code null}.
     */
    public Rational multiply(final Rational o) {
        if (o == null) {
            throw new NullPointerException();
        }
        BigInteger newNumerator   = numerator.multiply(o.numerator);
        BigInteger newDenominator = denominator.multiply(o.denominator);
        return new Rational(newNumerator, newDenominator);
    }

    /**
     * <p>
     * Divide the value of this fraction by another, returning the result in
     * reduced form.
     * </p>
     *
     * @param o Fraction to divide by, must not be {@code null}.
     * @return a {@link Rational} instance with the resulting values.
     * @throws NullPointerException if the {@code o} is {@code null}.
     * @throws ArithmeticException  if the fraction to divide by is zero
     */
    public Rational divide(final Rational o) {
        if (o == null) {
            throw new NullPointerException();
        }
        if (o.numerator.signum() == 0) {
            throw new ArithmeticException("divide by zero");
        }
        BigInteger newNumerator   = numerator.multiply(o.denominator);
        BigInteger newDenominator = denominator.multiply(o.numerator);
        return new Rational(newNumerator, newDenominator);
    }

    /**
     * <p>
     * Return the additive inverse of this fraction, returning the result in
     * reduced form.
     * </p>
     *
     * @return the negation of this fraction.
     */
    public Rational negate() {
        return new Rational(numerator.negate(), denominator);
    }

    /**
     * <p>
     * Return the positive equivalent of this fraction, returning the result in
     * reduced form.
     * </p>
     *
     * @return the positive equivalent of this fraction.
     */
    public Rational abs() {
        return numerator.signum() == 1 ? this : this.negate();
    }

    /**
     * <p>
     * Return the boolean indicates if the {@link Rational} is also an integer.
     * </p>
     *
     * @return true if {@link Rational} and is an integer
     */
    public boolean isInteger() {
        return denominator.equals(BigInteger.ONE);
    }

    /**
     * Returns the smaller of this and {@code o}. If they have equal value, this is returned.
     *
     * @param o the {@link Rational} to compare to this
     * @return smaller of this and {@code o}
     * @throws NullPointerException if the {@code o} is {@code null}.
     */
    public Rational min(final Rational o) {
        if (o == null) {
            throw new NullPointerException();
        }
        return compareTo(o) <= 0 ? this : o;
    }

    /**
     * Returns the greater of this and {@code o}. If they have equal value, this is returned.
     *
     * @param o the {@link Rational} to compare to this
     * @return greater of this and {@code o}
     * @throws NullPointerException if the {@code o} is {@code null}.
     */
    public Rational max(final Rational o) {
        if (o == null) {
            throw new NullPointerException();
        }
        return compareTo(o) >= 0 ? this : o;
    }

    /**
     * <p>
     * Returns the <code>String</code> representing this fraction, ie
     * "numerator/denominator" or just "numerator" if the denominator is one.
     * </p>
     *
     * @return a string representation of the fraction.
     */
    @Override
    public String toString() {
        if (numerator.signum() == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(numerator);
        if (denominator.equals(BigInteger.ONE)) {
            return sb.toString();
        }
        sb.append('/');
        sb.append(denominator);
        return sb.toString();
    }

    /**
     * <p>
     * Compares this and {@code o} based on size.
     * </p>
     *
     * @param o the {@link Rational} to compare to.
     * @return -1 if this is less than {@code o}, +1 if this is greater
     * than {@code o}, 0 if they are equal.
     * @throws NullPointerException if the {@code o} is {@code null}.
     */
    @Override
    public int compareTo(final Rational o) {
        if (o == null) {
            throw new NullPointerException();
        }
        BigInteger a = numerator.multiply(o.denominator);
        BigInteger b = denominator.multiply(o.numerator);
        return a.compareTo(b);
    }

    /**
     * <p>
     * Test for the equality of two fractions. If the lowest term numerator and
     * denominators are the same for both fractions, the two fractions are
     * considered to be equal.
     * </p>
     *
     * @param o fraction to test for equality to this fraction, can be <code>null</code>.
     * @return true if two fractions are equal, false if object is
     * <code>null</code>, not an instance of {@link Rational}, or not
     * equal to this fraction instance.
     */
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Rational obj = (Rational) o;
        return this.numerator.equals(obj.numerator) && this.denominator.equals(obj.denominator);
    }

    /**
     * <p>
     * Gets a hashCode for the fraction.
     * </p>
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return 37 * (37 * 17 + numerator.hashCode()) + denominator.hashCode();
    }

    /**
     * <p>
     * Gets the fraction as an {@code int}. This returns the whole number part
     * of the fraction.
     * </p>
     *
     * @return the whole number fraction part.
     */
    @Override
    public int intValue() {
        return numerator.divide(denominator).intValue();
    }

    /**
     * <p>
     * Gets the fraction as a {@code long}. This returns the whole number part
     * of the fraction.
     * </p>
     *
     * @return the whole number fraction part.
     */
    @Override
    public long longValue() {
        return numerator.divide(denominator).longValue();
    }

    /**
     * <p>
     * Gets the fraction as a {@code float}. This calculates the fraction as
     * the numerator divided by denominator.
     * </p>
     *
     * @return the fraction as a {@code float}.
     */
    @Override
    public float floatValue() {
        return (float) doubleValue();
    }

    /**
     * <p>
     * Gets the fraction as a {@code double}. This calculates the fraction as
     * the numerator divided by denominator.
     * </p>
     *
     * @return the fraction as a {@code double}
     */
    @Override
    public double doubleValue() {
        return isInteger() ? numerator.doubleValue() : numerator.doubleValue() / denominator.doubleValue();
    }

    /**
     * <p>
     * Returns a <code>Rational</code> whose value is
     * <tt>(this<sup>exponent</sup>)</tt>, returning the result in reduced form.
     * </p>
     *
     * @param exponent exponent to which this <code>Rational</code> is to be raised.
     * @return <tt>this<sup>exponent</sup></tt> as a <code>Rational</code>.
     */
    public Rational pow(final int exponent) {
        if (exponent == 0) {
            return ONE;
        }
        if (exponent < 0) {
            return new Rational(denominator.pow(-exponent), numerator.pow(-exponent));
        }
        return new Rational(numerator.pow(exponent), denominator.pow(exponent));
    }
}
