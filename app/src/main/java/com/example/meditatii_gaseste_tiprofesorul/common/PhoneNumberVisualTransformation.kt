package com.example.meditatii_gaseste_tiprofesorul.common

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class PhoneNumberVisualTransformation: VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val trimmed = if (text.text.length >= 9) text.text.substring(0..8) else text.text
        var output = ""

        for (i in trimmed.indices) {
            output += trimmed[i]
            if (i % 3 == 2 && i != 8) output += " "
        }

        val phoneNumberTranslator = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                // [offset [0 - 2] remain the same]
                if (offset <= 2) return offset

                // [3 - 5] transformed to [4 - 6] respectively
                if (offset <= 5) return offset + 1

                // [6 - 8] transformed to [8 - 10] respectively
                if (offset <= 8) return offset + 2

                return 11
            }

            override fun transformedToOriginal(offset: Int): Int {
                if (offset <= 2) return offset
                if (offset <= 6) return offset -1
                if (offset <= 10) return offset - 2

                return 9
            }
        }

        return TransformedText(
            AnnotatedString(output), phoneNumberTranslator
        )
    }
}