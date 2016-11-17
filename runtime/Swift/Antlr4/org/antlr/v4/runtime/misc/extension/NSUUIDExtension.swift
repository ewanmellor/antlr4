//
//  NSUUIDExtension.swift
//  objc2swiftwithswith
//
//  Created by janyou on 15/9/8.
//  Copyright © 2015 jlabs. All rights reserved.
//

import Foundation


private func toUUID(_ mostSigBits: Int64, _ leastSigBits: Int64) -> String {

    return (digits(mostSigBits >> 32, 8) + "-" +
        digits(mostSigBits >> 16, 4) + "-" +
        digits(mostSigBits, 4) + "-" +
        digits(leastSigBits >> 48, 4) + "-" +
        digits(leastSigBits, 12))
}

private func digits(_ val: Int64, _ digits: Int) -> String {
    let hi = Int64(1) << Int64(digits * 4)
    let intLiteral = (val & (hi - 1))
    print("IL: \(intLiteral)")
    return String(intLiteral, radix: 16)
}

extension UUID {

    public init(mostSigBits: Int64, leastSigBits: Int64) {
        let uuid: String = toUUID(mostSigBits, leastSigBits)
        print("uuid: \(uuid)")
        self.init(uuidString: uuid)!
    }


}
