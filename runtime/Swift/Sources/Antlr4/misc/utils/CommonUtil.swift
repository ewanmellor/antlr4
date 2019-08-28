/* Copyright (c) 2012-2017 The ANTLR Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.txt file in the project root.
 */

//
//  CommonUtil.swift
//   antlr.swift
//
//  Created by janyou on 15/9/4.
//

import Foundation

func errPrint(_ msg: String) {
    fputs(msg + "\n", stderr)
}

public func +(lhs: String, rhs: Token) -> String {
    return lhs + rhs.description
}

public func +(lhs: Token, rhs: String) -> String {
    return lhs.description + rhs
}

func toInt(_ c: Character) -> Int {
    return c.unicodeValue
}

func toInt32(_ data: [Character], _ offset: Int) -> Int {
    return data[offset].unicodeValue | (data[offset + 1].unicodeValue << 16)
}

func toLong(_ data: [Character], _ offset: Int) -> Int64 {
    let mask: Int64 = 0x00000000FFFFFFFF
    let lowOrder: Int64 = Int64(toInt32(data, offset)) & mask
    return lowOrder | Int64(toInt32(data, offset + 2) << 32)
}

func toUUID(_ data: [Character], _ offset: Int) -> UUID {
    let leastSigBits: Int64 = toLong(data, offset)
    let mostSigBits: Int64 = toLong(data, offset + 4)
    return UUID(mostSigBits: mostSigBits, leastSigBits: leastSigBits)
}
