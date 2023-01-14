package org.stranger2015.opencv.fic.core.geom;

/*
 * Copyright (c) 2016 Vivid Solutions.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * and Eclipse Distribution License v. 1.0 which accompanies this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v20.html
 * and the Eclipse Distribution License is available at
 *
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

import static org.stranger2015.opencv.fic.core.geom.Location.*;

/**
 * Models a <b>Dimensionally Extended Nine-Intersection Model (DE-9IM)</b> matrix.
 * DE-9IM matrix values (such as "212FF1FF2")
 * specify the topological relationship between two {@link Geometry}s.
 * This class can also represent matrix patterns (such as "T*T******")
 * which are used for matching instances of DE-9IM matrices.
 * <p>
 * DE-9IM matrices are 3x3 matrices with integer entries.
 * The matrix indices {0,1,2} represent the topological locations
 * that occur in a geometry (Interior, Boundary, Exterior).
 * These are provided by the constants
 * {@link Location#INTERIOR}, {@link Location#BOUNDARY}, and {@link Location#EXTERIOR}.
 * <p>
 * When used to specify the topological relationship between two geometries,
 * the matrix entries represent the possible dimensions of each intersection:
 * {@link Dimension#A} = 2, {@link Dimension#L} = 1, {@link Dimension#P} = 0 and {@link Dimension#FALSE} = -1.
 * When used to represent a matrix pattern entries can have the additional values
 * {@link Dimension#TRUE} {"T") and {@link Dimension#DONTCARE} ("*").
 * <p>
 * For a description of the DE-9IM and the spatial predicates derived from it,
 * see the following references:
 * <ul>
 * <li><i><a href="http://www.opengis.org/techno/specs.htm">
 * OGC 99-049 OpenGIS Simple Features Specification for SQL</a></i>
 * , Section 2.1.13</li>
 * <li><i><a href="http://portal.opengeospatial.org/files/?artifact_id=25355">
 * OGC 06-103r4 OpenGIS Implementation Standard for Geographic information - Simple feature access - Part 1: Common architecture</a></i>
 * , Section 6.1.15 (which provides some further details on certain predicate specifications).
 * </li>
 * <li>Wikipedia article on <a href="https://en.wikipedia.org/wiki/DE-9IM">DE-9IM</a></li>
 * </ul>
 * <p>
 * Methods are provided to:
 *  <UL>
 *    <LI>set and query the elements of the matrix in a convenient fashion
 *    <LI>convert to and from the standard string representation (specified in
 *    SFS Section 2.1.13.2).
 *    <LI>test if a matrix matches a given pattern string.
 *    <li>test if a matrix (possibly with geometry dimensions) matches a standard named spatial predicate
 *  </UL>
 *
 * @version 1.8
 */
public
class IntersectionMatrix implements Cloneable {
    /**
     * Internal representation of this <code>IntersectionMatrix1</code>.
     */
    private final int[][] matrix;

    /**
     * Creates an <code>IntersectionMatrix1</code> with <code>FALSE</code>
     * dimension values.
     */
    public
    IntersectionMatrix () {
        matrix = new int[3][3];
        setAll(Dimension.FALSE);
    }

    /**
     * Creates an <code>IntersectionMatrix1</code> with the given dimension
     * symbols.
     *
     * @param elements a String of nine dimension symbols in row major order
     */
    public
    IntersectionMatrix ( String elements ) {
        this();
        set(elements);
    }

    /**
     * Creates an <code>IntersectionMatrix1</code> with the same elements as
     * <code>other</code>.
     *
     * @param other an <code>IntersectionMatrix1</code> to copy
     */
    public
    IntersectionMatrix ( IntersectionMatrix other ) {
        this();
        matrix[INTERIOR][INTERIOR] = other.matrix[INTERIOR][INTERIOR];
        matrix[INTERIOR][BOUNDARY] = other.matrix[INTERIOR][BOUNDARY];
        matrix[INTERIOR][EXTERIOR] = other.matrix[INTERIOR][EXTERIOR];
        matrix[BOUNDARY][INTERIOR] = other.matrix[BOUNDARY][INTERIOR];
        matrix[BOUNDARY][BOUNDARY] = other.matrix[BOUNDARY][BOUNDARY];
        matrix[BOUNDARY][EXTERIOR] = other.matrix[BOUNDARY][EXTERIOR];
        matrix[EXTERIOR][INTERIOR] = other.matrix[EXTERIOR][INTERIOR];
        matrix[EXTERIOR][BOUNDARY] = other.matrix[EXTERIOR][BOUNDARY];
        matrix[EXTERIOR][EXTERIOR] = other.matrix[EXTERIOR][EXTERIOR];
    }

    /**
     * Adds one matrix to another.
     * Addition is defined by taking the maximum dimension value of each position
     * in the summand matrices.
     *
     * @param im the matrix to add
     */
    public
    void add ( IntersectionMatrix im ) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                setAtLeast(i, j, im.get(i, j));
            }
        }
    }

    /**
     * Tests if the dimension value matches <tt>TRUE</tt>
     * (i.e.  has value 0, 1, 2 or TRUE).
     *
     * @param actualDimensionValue a number that can be stored in the <code>IntersectionMatrix1</code>
     *                             . Possible values are <code>{TRUE, FALSE, DONTCARE, 0, 1, 2}</code>.
     * @return true if the dimension value matches TRUE
     */
    public static
    boolean isTrue ( int actualDimensionValue ) {
        return actualDimensionValue >= 0 || actualDimensionValue == Dimension.TRUE;
    }

    /**
     * Tests if the dimension value satisfies the dimension symbol.
     *
     * @param actualDimensionValue    a number that can be stored in the <code>IntersectionMatrix1</code>
     *                                . Possible values are <code>{TRUE, FALSE, DONTCARE, 0, 1, 2}</code>.
     * @param requiredDimensionSymbol a character used in the string
     *                                representation of an <code>IntersectionMatrix1</code>. Possible values
     *                                are <code>{T, F, * , 0, 1, 2}</code>.
     * @return true if the dimension symbol matches
     * the dimension value
     */
    public static
    boolean matches ( int actualDimensionValue, char requiredDimensionSymbol ) {
        if (requiredDimensionSymbol == Dimension.SYM_DONTCARE) {
            return true;
        }
        if (requiredDimensionSymbol == Dimension.SYM_TRUE && (actualDimensionValue >= 0 || actualDimensionValue
                == Dimension.TRUE)) {
            return true;
        }
        if (requiredDimensionSymbol == Dimension.SYM_FALSE && actualDimensionValue == Dimension.FALSE) {
            return true;
        }
        if (requiredDimensionSymbol == Dimension.SYM_P && actualDimensionValue == Dimension.P) {
            return true;
        }
        if (requiredDimensionSymbol == Dimension.SYM_L && actualDimensionValue == Dimension.L) {
            return true;
        }
        return requiredDimensionSymbol == Dimension.SYM_A && actualDimensionValue == Dimension.A;
    }

    /**
     * Tests if each of the actual dimension symbols in a matrix string satisfies the
     * corresponding required dimension symbol in a pattern string.
     *
     * @param actualDimensionSymbols   nine dimension symbols to validate.
     *                                 Possible values are <code>{T, F, * , 0, 1, 2}</code>.
     * @param requiredDimensionSymbols nine dimension symbols to validate
     *                                 against. Possible values are <code>{T, F, * , 0, 1, 2}</code>.
     * @return true if each of the required dimension
     * symbols encompass the corresponding actual dimension symbol
     */
    public static
    boolean matches ( String actualDimensionSymbols, String requiredDimensionSymbols ) {
        IntersectionMatrix m = new IntersectionMatrix(actualDimensionSymbols);
        return m.matches(requiredDimensionSymbols);
    }

    /**
     * Changes the value of one of this <code>IntersectionMatrix1</code>s
     * elements.
     *
     * @param row            the row of this <code>IntersectionMatrix1</code>,
     *                       indicating the interior, boundary or exterior of the first <code>Geometry</code>
     * @param column         the column of this <code>IntersectionMatrix1</code>,
     *                       indicating the interior, boundary or exterior of the second <code>Geometry</code>
     * @param dimensionValue the new value of the element
     */
    public
    void set ( int row, int column, int dimensionValue ) {
        matrix[row][column] = dimensionValue;
    }

    /**
     * Changes the elements of this <code>IntersectionMatrix1</code> to the
     * dimension symbols in <code>dimensionSymbols</code>.
     *
     * @param dimensionSymbols nine dimension symbols to which to set this <code>IntersectionMatrix1</code>
     *                         s elements. Possible values are <code>{T, F, * , 0, 1, 2}</code>
     */
    public
    void set ( String dimensionSymbols ) {
        for (int i = 0; i < dimensionSymbols.length(); i++) {
            int row = i / 3;
            int col = i % 3;
            matrix[row][col] = Dimension.toDimensionValue(dimensionSymbols.charAt(i));
        }
    }

    /**
     * Changes the specified element to <code>minimumDimensionValue</code> if the
     * element is less.
     *
     * @param row                   the row of this <code>IntersectionMatrix1</code>
     *                              , indicating the interior, boundary or exterior of the first <code>Geometry</code>
     * @param column                the column of this <code>IntersectionMatrix1</code>
     *                              , indicating the interior, boundary or exterior of the second <code>Geometry</code>
     * @param minimumDimensionValue the dimension value with which to compare the
     *                              element. The order of dimension values from least to greatest is
     *                              <code>{DONTCARE, TRUE, FALSE, 0, 1, 2}</code>.
     */
    public
    void setAtLeast ( int row, int column, int minimumDimensionValue ) {
        if (matrix[row][column] < minimumDimensionValue) {
            matrix[row][column] = minimumDimensionValue;
        }
    }

    /**
     * If row &gt;= 0 and column &gt;= 0, changes the specified element to <code>minimumDimensionValue</code>
     * if the element is less. Does nothing if row &lt;0 or column &lt; 0.
     *
     * @param row                   the row of this <code>IntersectionMatrix1</code>
     *                              , indicating the interior, boundary or exterior of the first <code>Geometry</code>
     * @param column                the column of this <code>IntersectionMatrix1</code>
     *                              , indicating the interior, boundary or exterior of the second <code>Geometry</code>
     * @param minimumDimensionValue the dimension value with which to compare the
     *                              element. The order of dimension values from least to greatest is
     *                              <code>{DONTCARE, TRUE, FALSE, 0, 1, 2}</code>.
     */
    public
    void setAtLeastIfValid ( int row, int column, int minimumDimensionValue ) {
        if (row >= 0 && column >= 0) {
            setAtLeast(row, column, minimumDimensionValue);
        }
    }

    /**
     * For each element in this <code>IntersectionMatrix1</code>, changes the
     * element to the corresponding minimum dimension symbol if the element is
     * less.
     *
     * @param minimumDimensionSymbols nine dimension symbols with which to
     *                                compare the elements of this <code>IntersectionMatrix1</code>. The
     *                                order of dimension values from least to greatest is <code>{DONTCARE, TRUE, FALSE, 0, 1, 2}</code>
     *                                .
     */
    public
    void setAtLeast ( String minimumDimensionSymbols ) {
        for (int i = 0; i < minimumDimensionSymbols.length(); i++) {
            int row = i / 3;
            int col = i % 3;
            setAtLeast(row, col, Dimension.toDimensionValue(minimumDimensionSymbols.charAt(i)));
        }
    }

    /**
     * Changes the elements of this <code>IntersectionMatrix1</code> to <code>dimensionValue</code>
     * .
     *
     * @param dimensionValue the dimension value to which to set this <code>IntersectionMatrix1</code>
     *                       s elements. Possible values <code>{TRUE, FALSE, DONTCARE, 0, 1, 2}</code>
     *                       .
     */
    public
    void setAll ( int dimensionValue ) {
        for (int ai = 0; ai < 3; ai++) {
            for (int bi = 0; bi < 3; bi++) {
                matrix[ai][bi] = dimensionValue;
            }
        }
    }

    /**
     * Returns the value of one of this matrix
     * entries.
     * The value of the provided index is one of the
     * values from the {@link Location} class.
     * The value returned is a constant
     * from the {@link Dimension} class.
     *
     * @param row    the row of this <code>IntersectionMatrix1</code>, indicating
     *               the interior, boundary or exterior of the first <code>Geometry</code>
     * @param column the column of this <code>IntersectionMatrix1</code>,
     *               indicating the interior, boundary or exterior of the second <code>Geometry</code>
     * @return the dimension value at the given matrix position.
     */
    public
    int get ( int row, int column ) {
        return matrix[row][column];
    }

    /**
     * Tests if this matrix matches <code>[FF*FF****]</code>.
     *
     * @return <code>true</code> if the two <code>Geometry</code>s related by
     * this matrix are disjoint
     */
    public
    boolean isDisjoint () {
        return
                matrix[INTERIOR][INTERIOR] == Dimension.FALSE &&
                        matrix[INTERIOR][BOUNDARY] == Dimension.FALSE &&
                        matrix[BOUNDARY][INTERIOR] == Dimension.FALSE &&
                        matrix[BOUNDARY][BOUNDARY] == Dimension.FALSE;
    }

    /**
     * Tests if <code>isDisjoint</code> returns false.
     *
     * @return <code>true</code> if the two <code>Geometry</code>s related by
     * this matrix intersect
     */
    public
    boolean isIntersects () {
        return !isDisjoint();
    }

    /**
     * Tests if this matrix matches
     * <code>[FT*******]</code>, <code>[F**T*****]</code> or <code>[F***T****]</code>.
     *
     * @param dimensionOfGeometryA the dimension of the first <code>Geometry</code>
     * @param dimensionOfGeometryB the dimension of the second <code>Geometry</code>
     * @return <code>true</code> if the two <code>Geometry</code>
     * s related by this matrix touch; Returns false
     * if both <code>Geometry</code>s are points.
     */
    public
    boolean isTouches ( int dimensionOfGeometryA, int dimensionOfGeometryB ) {
        if (dimensionOfGeometryA > dimensionOfGeometryB) {
            //no need to get transpose because pattern matrix is symmetrical
            return isTouches(dimensionOfGeometryB, dimensionOfGeometryA);
        }
        if ((dimensionOfGeometryA == Dimension.A && dimensionOfGeometryB == Dimension.A) ||
                (dimensionOfGeometryA == Dimension.L && dimensionOfGeometryB == Dimension.L) ||
                (dimensionOfGeometryA == Dimension.L && dimensionOfGeometryB == Dimension.A) ||
                (dimensionOfGeometryA == Dimension.P && dimensionOfGeometryB == Dimension.A) ||
                (dimensionOfGeometryA == Dimension.P && dimensionOfGeometryB == Dimension.L)) {
            return matrix[INTERIOR][INTERIOR] == Dimension.FALSE &&
                    (isTrue(matrix[INTERIOR][BOUNDARY])
                            || isTrue(matrix[BOUNDARY][INTERIOR])
                            || isTrue(matrix[BOUNDARY][BOUNDARY]));
        }
        return false;
    }

    /**
     * Tests whether this geometry crosses the
     * specified geometry.
     * <p>
     * The <code>crosses</code> predicate has the following equivalent definitions:
     * <ul>
     * <li>The geometries have some but not all interior points in common.
     * <li>The DE-9IM Intersection Matrix for the two geometries matches
     *   <ul>
     *    <li><code>[T*T******]</code> (for P/L, P/A, and L/A situations)
     *    <li><code>[T*****T**]</code> (for L/P, L/A, and A/L situations)
     *    <li><code>[0********]</code> (for L/L situations)
     *   </ul>
     * </ul>
     * For any other combination of dimensions this predicate returns <code>false</code>.
     * <p>
     * The SFS defined this predicate only for P/L, P/A, L/L, and L/A situations.
     * JTS extends the definition to apply to L/P, A/P and A/L situations as well.
     * This makes the relation symmetric.
     *
     * @param dimensionOfGeometryA the dimension of the first <code>Geometry</code>
     * @param dimensionOfGeometryB the dimension of the second <code>Geometry</code>
     * @return <code>true</code> if the two <code>Geometry</code>s
     * related by this matrix cross.
     */
    public
    boolean isCrosses ( int dimensionOfGeometryA, int dimensionOfGeometryB ) {
        if ((dimensionOfGeometryA == Dimension.P && dimensionOfGeometryB == Dimension.L) ||
                (dimensionOfGeometryA == Dimension.P && dimensionOfGeometryB == Dimension.A) ||
                (dimensionOfGeometryA == Dimension.L && dimensionOfGeometryB == Dimension.A)) {
            return isTrue(matrix[INTERIOR][INTERIOR]) &&
                    isTrue(matrix[INTERIOR][EXTERIOR]);
        }
        if ((dimensionOfGeometryA == Dimension.L && dimensionOfGeometryB == Dimension.P) ||
                (dimensionOfGeometryA == Dimension.A && dimensionOfGeometryB == Dimension.P) ||
                (dimensionOfGeometryA == Dimension.A && dimensionOfGeometryB == Dimension.L)) {
            return isTrue(matrix[INTERIOR][INTERIOR]) &&
                    isTrue(matrix[EXTERIOR][INTERIOR]);
        }
        if (dimensionOfGeometryA == Dimension.L && dimensionOfGeometryB == Dimension.L) {
            return matrix[INTERIOR][INTERIOR] == 0;
        }

        return false;
    }

    /**
     * Tests whether this matrix matches <code>[T*F**F***]</code>.
     *
     * @return <code>true</code> if the first <code>Geometry</code> is within
     * the second
     */
    public
    boolean isWithin () {
        return isTrue(matrix[INTERIOR][INTERIOR]) &&
                matrix[INTERIOR][EXTERIOR] == Dimension.FALSE &&
                matrix[BOUNDARY][EXTERIOR] == Dimension.FALSE;
    }

    /**
     * Tests whether this matrix matches [T*****FF*[.
     *
     * @return <code>true</code> if the first <code>Geometry</code> contains the
     * second
     */
    public
    boolean isContains () {
        return isTrue(matrix[INTERIOR][INTERIOR]) &&
                matrix[EXTERIOR][INTERIOR] == Dimension.FALSE &&
                matrix[EXTERIOR][BOUNDARY] == Dimension.FALSE;
    }

    /**
     * Tests if this matrix matches
     * <code>[T*****FF*]</code>
     * or <code>[*T****FF*]</code>
     * or <code>[***T**FF*]</code>
     * or <code>[****T*FF*]</code>
     *
     * @return <code>true</code> if the first <code>Geometry</code> covers the
     * second
     */
    public
    boolean isCovers () {
        boolean hasPointInCommon =
                isTrue(matrix[INTERIOR][INTERIOR])
                        || isTrue(matrix[INTERIOR][BOUNDARY])
                        || isTrue(matrix[BOUNDARY][INTERIOR])
                        || isTrue(matrix[BOUNDARY][BOUNDARY]);

        return hasPointInCommon &&
                matrix[EXTERIOR][INTERIOR] == Dimension.FALSE &&
                matrix[EXTERIOR][BOUNDARY] == Dimension.FALSE;
    }

    /**
     * Tests if this matrix matches
     * <code>[T*F**F***]</code>
     * or <code>[*TF**F***]</code>
     * or <code>[**FT*F***]</code>
     * or <code>[**F*TF***]</code>
     *
     * @return <code>true</code> if the first <code>Geometry</code>
     * is covered by the second
     */
    public
    boolean isCoveredBy () {
        boolean hasPointInCommon =
                isTrue(matrix[INTERIOR][INTERIOR])
                        || isTrue(matrix[INTERIOR][BOUNDARY])
                        || isTrue(matrix[BOUNDARY][INTERIOR])
                        || isTrue(matrix[BOUNDARY][BOUNDARY]);

        return hasPointInCommon &&
                matrix[INTERIOR][EXTERIOR] == Dimension.FALSE &&
                matrix[BOUNDARY][EXTERIOR] == Dimension.FALSE;
    }

    /**
     * Tests whether the argument dimensions are equal and
     * this matrix matches the pattern <tt>[T*F**FFF*]</tt>.
     * <p>
     * <b>Note:</b> This pattern differs from the one stated in
     * <i>Simple feature access - Part 1: Common architecture</i>.
     * That document states the pattern as <tt>[TFFFTFFFT]</tt>.  This would
     * specify that
     * two identical <tt>POINT</tt>s are not equal, which is not desirable behaviour.
     * The pattern used here has been corrected to compute equality in this situation.
     *
     * @param dimensionOfGeometryA the dimension of the first <code>Geometry</code>
     * @param dimensionOfGeometryB the dimension of the second <code>Geometry</code>
     * @return <code>true</code> if the two <code>Geometry</code>s
     * related by this matrix are equal; the
     * <code>Geometry</code>s must have the same dimension to be equal
     */
    public
    boolean isEquals ( int dimensionOfGeometryA, int dimensionOfGeometryB ) {
        if (dimensionOfGeometryA != dimensionOfGeometryB) {
            return false;
        }
        return isTrue(matrix[INTERIOR][INTERIOR]) &&
                matrix[INTERIOR][EXTERIOR] == Dimension.FALSE &&
                matrix[BOUNDARY][EXTERIOR] == Dimension.FALSE &&
                matrix[EXTERIOR][INTERIOR] == Dimension.FALSE &&
                matrix[EXTERIOR][BOUNDARY] == Dimension.FALSE;
    }

    /**
     * Tests if this matrix matches
     * <UL>
     * <LI><tt>[T*T***T**]</tt> (for two points or two surfaces)
     * <LI><tt>[1*T***T**]</tt> (for two curves)
     * </UL>.
     *
     * @param dimensionOfGeometryA the dimension of the first <code>Geometry</code>
     * @param dimensionOfGeometryB the dimension of the second <code>Geometry</code>
     * @return <code>true</code> if the two <code>Geometry</code>s
     * related by this matrix overlap. For this
     * function to return <code>true</code>, the <code>Geometry</code>s must
     * be two points, two curves or two surfaces.
     */
    public
    boolean isOverlaps ( int dimensionOfGeometryA, int dimensionOfGeometryB ) {
        if ((dimensionOfGeometryA == Dimension.P && dimensionOfGeometryB == Dimension.P) ||
                (dimensionOfGeometryA == Dimension.A && dimensionOfGeometryB == Dimension.A)) {
            return isTrue(matrix[INTERIOR][INTERIOR])
                    && isTrue(matrix[INTERIOR][EXTERIOR])
                    && isTrue(matrix[EXTERIOR][INTERIOR]);
        }
        if (dimensionOfGeometryA == Dimension.L && dimensionOfGeometryB == Dimension.L) {
            return matrix[INTERIOR][INTERIOR] == 1
                    && isTrue(matrix[INTERIOR][EXTERIOR])
                    && isTrue(matrix[EXTERIOR][INTERIOR]);
        }
        return false;
    }

    /**
     * Tests whether this matrix matches the given matrix pattern.
     *
     * @param pattern A pattern containing nine dimension symbols with which to
     *                compare the entries of this matrix. Possible
     *                symbol values are <code>{T, F, * , 0, 1, 2}</code>.
     * @return <code>true</code> if this matrix matches the pattern
     */
    public
    boolean matches ( String pattern ) {
        if (pattern.length() != 9) {
            throw new IllegalArgumentException("Should be length 9: " + pattern);
        }
        for (int ai = 0; ai < 3; ai++) {
            for (int bi = 0; bi < 3; bi++) {
                if (!matches(matrix[ai][bi], pattern.charAt(3 * ai +
                        bi))) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Transposes this IntersectionMatrix1.
     *
     * @return this <code>IntersectionMatrix1</code> as a convenience
     */
    public
    IntersectionMatrix transpose () {
        int temp = matrix[1][0];
        matrix[1][0] = matrix[0][1];
        matrix[0][1] = temp;
        temp = matrix[2][0];
        matrix[2][0] = matrix[0][2];
        matrix[0][2] = temp;
        temp = matrix[2][1];
        matrix[2][1] = matrix[1][2];
        matrix[1][2] = temp;

        return this;
    }

    /**
     * Returns a nine-character <code>String</code> representation of this <code>IntersectionMatrix1</code>
     * .
     *
     * @return the nine dimension symbols of this <code>IntersectionMatrix1</code>
     * in row-major order.
     */
    public
    String toString () {
        StringBuilder builder = new StringBuilder("123456789");
        for (int ai = 0; ai < 3; ai++) {
            for (int bi = 0; bi < 3; bi++) {
                builder.setCharAt(3 * ai + bi, Dimension.toDimensionSymbol(matrix[ai][bi]));
            }
        }
        return builder.toString();
    }

    /**
     * @return
     */
    @Override
    public
    IntersectionMatrix clone () {
        IntersectionMatrix result;
        try {
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            result = (IntersectionMatrix) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }

        return result;
    }
}