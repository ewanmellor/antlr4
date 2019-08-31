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

infix operator >>> : BitwiseShiftPrecedence

func >>>(lhs: Int32, rhs: Int32) -> Int32 {
    let left = UInt32(bitPattern: lhs)
    let right = UInt32(bitPattern: rhs) % 32

    return Int32(bitPattern: left >> right)
}

func >>>(lhs: Int64, rhs: Int64) -> Int64 {
    let left = UInt64(bitPattern: lhs)
    let right = UInt64(bitPattern: rhs) % 64

    return Int64(bitPattern: left >> right)
}

func >>>(lhs: Int, rhs: Int) -> Int {
    let numberOfBits: UInt = MemoryLayout<UInt>.size == MemoryLayout<UInt64>.size ? 64 : 32

    let left = UInt(bitPattern: lhs)
    let right = UInt(bitPattern: rhs) % numberOfBits

    return Int(bitPattern: left >> right)
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
