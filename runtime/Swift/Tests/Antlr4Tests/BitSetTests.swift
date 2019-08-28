/// Copyright (c) 2012-2017 The ANTLR Project. All rights reserved.
/// Use of this file is governed by the BSD 3-clause license that
/// can be found in the LICENSE.txt file in the project root.

import XCTest
@testable import Antlr4

class BitSetTests: XCTestCase {
    func testMisc() throws {
        let s = BitSet()
        XCTAssertEqual(s.firstSetBit(), -1)
        XCTAssertEqual(s.cardinality(), 0)
        XCTAssertEqual(s.size(), 64)
        XCTAssertEqual(s.description, "{}")

        try s.set(2)
        XCTAssertEqual(s.description, "{2}")
        try s.set(4)
        try s.set(10)
        XCTAssertEqual(s.description, "{2, 4, 10}")
        try s.clear(4)
        XCTAssertEqual(s.description, "{2, 10}")
        try s.set(93)
        XCTAssertEqual(s.description, "{2, 10, 93}")
        try s.set(64)
        XCTAssertEqual(s.description, "{2, 10, 64, 93}")
        XCTAssertEqual(s.firstSetBit(), 2)
        XCTAssertEqual(s.cardinality(), 4)
        XCTAssertEqual(s.size(), 128)

        s.clear()
        XCTAssertEqual(s.description, "{}")
        XCTAssertEqual(s.firstSetBit(), -1)
        XCTAssertEqual(s.cardinality(), 0)
        XCTAssertEqual(s.size(), 128)
    }

    func testClear() throws {
        let s = BitSet()
        try s.set(0, 4)

        try s.clear(0)
        try s.clear(3, 5)
        try s.clear(7)

        XCTAssertEqual(s.description, "{1, 2}")
    }

    func testFlip() throws {
        let s = BitSet()
        try s.set(0, 4)

        try s.flip(0)
        try s.flip(3, 5)
        try s.flip(7)

        XCTAssertEqual(s.description, "{1, 2, 4, 7}")
    }

    func testAnd() throws {
        let s1 = BitSet()
        try s1.set(0, 4)
        let s2 = BitSet()
        try s2.set(5, 8)
        XCTAssertEqual(s1.description, "{0, 1, 2, 3}")
        XCTAssertEqual(s2.description, "{5, 6, 7}")

        s1.and(s2)
        XCTAssertEqual(s1.description, "{}")
    }

    func testOr() throws {
        let s1 = BitSet()
        try s1.set(0, 4)
        let s2 = BitSet()
        try s2.set(5, 8)
        XCTAssertEqual(s1.description, "{0, 1, 2, 3}")
        XCTAssertEqual(s2.description, "{5, 6, 7}")

        s1.or(s2)
        XCTAssertEqual(s1.description, "{0, 1, 2, 3, 5, 6, 7}")
    }
}
