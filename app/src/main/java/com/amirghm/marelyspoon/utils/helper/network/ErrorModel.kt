package com.amirghm.marelyspoon.utils.helper.network

import androidx.annotation.Keep
import com.amirghm.marelyspoon.utils.helper.network.ErrorStatus

/**
 * Default error model that comes from server if something goes wrong with a repository call
 */
@Keep
data class ErrorModel(
    val message: String?,
    val code: Int?,
    @Transient var errorStatus: ErrorStatus
) {
    constructor(errorStatus: ErrorStatus) : this(null, null, errorStatus)
}